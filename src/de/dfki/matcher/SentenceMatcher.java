package de.dfki.matcher;

import java.util.LinkedList;

/**
 * Created by alvaro on 6/6/17.
 */
public class SentenceMatcher {
    public static final double MATCHING_TRESHOLD = 0.10;
    private String match = "";
    private float highestMatchIndex = -1000;
    private LinkedList<Sentence> sentences = new LinkedList<>();

    public SentenceMatcher(LinkedList<String> options) {
        buildSentences(options);
    }

    private void buildSentences(LinkedList<String> options) {
        for (String sentence: options ) {
            sentences.add(new Sentence(sentence));
        }
    }

    public void match(String text) {
        Sentence toMatch = new Sentence(text);
        for (Sentence option: sentences  ) {
            float similarityIndex = option.computeSimilarityIndex(toMatch);
            updateIndex(option, similarityIndex);
            System.out.println(similarityIndex);
        }
    }

    private void updateIndex(Sentence sentence, float similarityIndex) {
        if(similarityIndex > highestMatchIndex){
            highestMatchIndex = similarityIndex;
            match = sentence.toString();
        }
    }

    public boolean hasMatch() {
        return highestMatchIndex > MATCHING_TRESHOLD;
    }

    public String getMatch() {
        return match;
    }
}
