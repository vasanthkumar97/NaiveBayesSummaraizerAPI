package com.summarizer.algorithm;


public class IndexValuePair implements Comparable<IndexValuePair>{

	double value;
	int index;
	
	// Comparison takes place on value.
	public int compareTo(IndexValuePair c) {
		return Double.compare(this.value, c.value);
	}
}