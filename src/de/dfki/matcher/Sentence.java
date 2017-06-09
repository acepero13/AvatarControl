package de.dfki.matcher;

import java.util.LinkedList;

/**
 * Created by alvaro on 6/6/17.
 */
public class Sentence {
    private final String originalSentence;
    private final String sentence;
    private int totalWords;
    private LinkedList<Word> words = new LinkedList<>();

    Sentence(String sentence) {
        this.originalSentence = sentence;
        this.sentence = sentence.toLowerCase();
        splitSentenceIntoWords(sentence);
        this.totalWords = this.totalWords > 0 ? totalWords : 1;
    }

    private void splitSentenceIntoWords(String sentence) {
        String[] loweredWords = sentence.toLowerCase().split(" ");
        this.totalWords = 0;
        for (String s : loweredWords) {
            Word word = new Word(s);
            words.add(word);
            this.totalWords++;
        }
    }

    float computeSimilarityIndex(Sentence toCompare) {
        float frequency = computeFrequency(toCompare);
        int distance = LevenshteinDistance.distance(this, toCompare);
        log(toCompare, distance);
        return frequency / (this.totalWords * distance);
    }

    private void log(Sentence toCompare, int distance) {
        System.out.println("******************************\n");
        System.out.println("Original: " + originalSentence + "\n");
        System.out.println("To Comare: " + toCompare.originalSentence + "\n");
        System.out.println(distance + "\n-------------");
    }

    private float computeFrequency(Sentence toCompare) {
        float frequency = 0;
        for (Word word : words) {
            frequency = updateFrequency(toCompare, frequency, word);
        }
        return frequency;
    }

    private float updateFrequency(Sentence toCompare, float frequency, Word word) {
        if (toCompare.hasWord(word)) {
            frequency++;
        }
        return frequency;
    }

    private boolean hasWord(Word word) {
        return words.contains(word);
    }

    public int getTotalWords() {
        return totalWords;
    }

    public String toString() {
        return originalSentence;
    }

    public Word wordAt(int pos) {
        return words.get(pos);
    }
}
