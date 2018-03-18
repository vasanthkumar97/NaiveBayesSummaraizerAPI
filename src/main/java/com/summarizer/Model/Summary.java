package com.summarizer.Model;

public class Summary {
    String summary;
    String keywords;
    public Summary(String summary) {
        this.summary = summary;
    }

    public Summary(String summary, String keywords) {
        this.summary = summary;
        this.keywords = keywords;
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
