package com.summarizer;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
