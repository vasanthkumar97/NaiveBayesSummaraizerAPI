package com.summarizer.summarizer;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SentencePreprocessor {
	
	List<String> stopwords;
    Classifier<String, String> bayes;
    /**
	 * Default no-argument constructor.
	 * Reads the stop word list.
	 */
	public SentencePreprocessor() {
        bayes =
                new BayesClassifier<String, String>();
		stopwords = readStopwords();
	}

	/**
	 * Performs standard text preprocessing tasks on tokenized sentences.
	 * Stop word removal, case normalization, punctuation removal.
	 * 
	 * @param 	document	List of list of strings representing document.
	 * @return List of list of lower-case strings with stop words and punctuation removed.
	 */
	public List<List<String>> process(List<List<String>> document) {
		return removeStopwords(removePunctuation(makeLowercase(document)));
	}
	
	/**
	 * Removes words that appear in the stop word list.
	 * 
	 * @param 	document	List of lists of words in sentences.
	 * @return	Sentence list with stopwords removed.
	 */
	public List<List<String>> removeStopwords(List<List<String>> document) {
		
		List<List<String>> processed = new ArrayList<List<String>>();
		
		for (int i=0; i<document.size(); i++) {
			List<String> oldSentence = document.get(i);
			List<String> newSentence = new ArrayList<String>();
			
			for (int j=0; j<oldSentence.size(); j++) {



//				if (!stopwords.contains(oldSentence.get(j))) {
//					newSentence.add(oldSentence.get(j));
//				}
                if(bayes.classify(Arrays.asList(oldSentence.get(j))).getProbability()<0.6)
                {
                    newSentence.add(oldSentence.get(j));
                }
			}
			
			processed.add(newSentence);
		}
		
		return processed;
	}
	
	/**
	 * Removes tokens that represent punctuation.
	 * 
	 * @param 	document	List of lists of words in sentences.
	 * @return	List of sentences with punctuation removed.
	 */
	public List<List<String>> removePunctuation(List<List<String>> document) {
			
		// Each sentence is a list of Strings and
		// the document is a list of sentences.
		List<List<String>> processed = new ArrayList<List<String>>();
			
		// Make a regex pattern to match strings with letters or numbers.
		Pattern notPuncPattern = Pattern.compile("[A-Za-z0-9]+");
			
		for (List<String> sentence: document) {
			List<String> newTokens = new ArrayList<String>();
				
			// If a string has letters or numbers, it is not just
			// punctuation, so add it to the list.
			for (String word : sentence) {
				Matcher matcher = notPuncPattern.matcher(word);
					
				if (matcher.find()) {
					newTokens.add(word);
				}
			}
				
			processed.add(newTokens);
		}
			
		return processed;
	}
	
	/**
	 * Makes all words in all sentences lower-case.
	 * 
	 * @param 	document	List of lists of words in sentences.
	 * @return List of sentences with all words lower-case.
	 */
	public List<List<String>> makeLowercase(List<List<String>> document) {
		
		List<List<String>> processed = new ArrayList<List<String>>();
		
		for (int i=0; i<document.size(); i++) {
			List<String> oldSentence = document.get(i);
			List<String> newSentence = new ArrayList<String>();
			
			for (int j=0; j<oldSentence.size(); j++) {
				String word = oldSentence.get(j).toLowerCase();
				newSentence.add(word);
			}
			
			processed.add(newSentence);
		}
		
		return processed;
	}

	/**
	 * Reads stop words from file.
	 * File format: one word per line.
	 * 
	 * @return	List of stop words.
	 */
	public List<String> readStopwords() {
		
		String stopword = null;
		List<String> stopwords = new ArrayList<String>();

		String fileName = "/home/zemoso/pjct/summarizer/src/main/java/com/summarizer/summarizer/stoplist.txt";

		try {
			InputStream inputStream = new FileInputStream(fileName);

			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			while ((stopword = bufferedReader.readLine()) != null) {
				stopwords.add(stopword);
                bayes.learn("stopword", Arrays.asList(stopword.split("\\s")));

            }
		} catch (IOException e) {
			e.printStackTrace();
		}

		catch (NullPointerException e) {
			e.printStackTrace();
		}
        fileName = "/home/zemoso/pjct/summarizer/src/main/java/com/summarizer/summarizer/keywords.txt";

        try {
            InputStream inputStream = new FileInputStream(fileName);
            String word;
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((word=bufferedReader.readLine()) != null) {
                word=word.replaceAll("[0-9]+","");
                word=word.replaceAll("\\s","");
                word=word.replaceAll("\\.","");

                bayes.learn("keyword", Arrays.asList(word.split("\\s")));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        catch (NullPointerException e) {
            e.printStackTrace();
        }
		return stopwords;
	}
}
