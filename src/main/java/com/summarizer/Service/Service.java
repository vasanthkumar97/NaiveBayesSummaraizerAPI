package com.summarizer.Service;

import com.summarizer.Model.InputModel;
import com.summarizer.Model.Summary;
import com.summarizer.summarizer.DocumentSummarizer;
import com.summarizer.summarizer.KeywordExtractor;
import com.summarizer.summarizer.SentencePreprocessor;
import com.summarizer.summarizer.SentenceSegmenter;

@org.springframework.stereotype.Service
public class Service {
    public Summary getSummary(int percentage, InputModel input) {
        SentenceSegmenter seg = new SentenceSegmenter();
        SentencePreprocessor prep = new SentencePreprocessor();
        DocumentSummarizer docsum = new DocumentSummarizer(seg, prep);
        KeywordExtractor keyext = new KeywordExtractor(seg, prep);
        String inputText=input.getInputText();
        String summarisedText = docsum.summarize(inputText, percentage);
        summarisedText=summarisedText.replaceAll("null","");
        String keywords = keyext.extract(summarisedText);
        Summary summary=new Summary(summarisedText,keywords);

        return summary;
    }
}
