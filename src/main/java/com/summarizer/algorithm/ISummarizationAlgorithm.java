package com.summarizer.algorithm;

import java.util.List;


public interface ISummarizationAlgorithm {

	/**
	 * Summarizes pre-tokenized document.
	 * 
	 * @param 	sentences	List of tokenized sentences.
	 * @param 	percentage	Percentage of original sentences to use in summary.
	 * @return	List of indices of sentences included in summary.
	 */
	public List<Integer> getSelection(List<List<String>> sentences, int percentage);
}
