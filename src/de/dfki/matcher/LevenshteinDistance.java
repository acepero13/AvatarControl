package de.dfki.matcher;

/**
 * Created by alvaro on 6/6/17.
 */
public class LevenshteinDistance {

    private static int[] costs;


    public static int distance(Sentence firstSentence, Sentence secondeSentence){
        costs = new int [secondeSentence.getTotalWords()];
        initializeCosts();
        computeCosts(firstSentence, secondeSentence);
        int cost =  costs[secondeSentence.getTotalWords()-1];
        return cost == 0 ? 1 : cost;
    }

    private static void computeCosts(Sentence firstSentence, Sentence secondSentence) {
        for(int i = 1; i < firstSentence.getTotalWords(); i++ ){
            costs[0]  = i;
            computeAgainstSecondSentence(firstSentence, secondSentence, i);
        }
    }

    private static void computeAgainstSecondSentence(Sentence firstSentence, Sentence secondSentence, int i) {
        int nw = i - 1;
        for (int j = 1; j < secondSentence.getTotalWords(); j++){
            int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]),
                    firstSentence.wordAt(i - 1).equals(secondSentence.wordAt(j - 1)) ? nw : nw + 1);
            nw = costs[j];
            costs[j] = cj;
        }
    }

    private static void initializeCosts() {
        for(int j = 0; j < costs.length; j++){
            costs[j] = j;
        }
    }
}
