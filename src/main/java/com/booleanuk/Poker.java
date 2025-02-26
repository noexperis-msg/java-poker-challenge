package com.booleanuk;

import java.util.Arrays;

public class Poker {
    String[] ranks;
    int[] values;

    public Poker() {
        this.ranks = new String[]{"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
        this.values = new int[]{2,3,4,5,6,7,8,9,10,11,12,13,14};
    }

    public String[] winningPair(String[] firstHand, String[] secondHand) {
        // Implement the winningPair logic here and return the array containing the winning pair to make the tests pass.
        // You can replace the following return value with something appropriate
        if (!isPair(firstHand) && !isPair(secondHand)) {
            return new String[]{};
        } else if (!isPair(firstHand)) {
            return secondHand;
        } else if (!isPair(secondHand)) {
            return firstHand;
        }

        int score1 = calculateScore(firstHand);
        int score2 = calculateScore(secondHand);

        if (score1 == score2) {
            return new String[]{};
        }
        return (score1 > score2 ? firstHand : secondHand);
    }

    // Extension 1
    public String[] winningPairFromArray(String[][] hands) {
        // Implement the winningPairFromArray logic here and return the array containing the winning pair to make the tests pass.
        // You can replace the following return value with something appropriate

        int[] scores = getScoresFromHandsArray(hands);

        int highestScore = getHighestScore(scores);
        if (highestScore == 0) {
            return new String[]{};
        }

        int winnerIndex = getHighestIndex(scores);
        return hands[winnerIndex];
    }

    // Extension 2
    public String[] winningThreeCardHand(String[][] hands) {
        // Implement the winningThreeCardHand logic here and return the array containing the winning hand to make the tests pass.
        // You can replace the following return value with something appropriate
        int[] scores = getScoresFromHandsArray(hands);
        int highestScore = getHighestScore(scores);

        // FIXME Wont always work
        // TODO Get two different scores[] one for pairs and one for trios
        for (String[] hand : hands) {
            System.out.println(Arrays.toString(hand));
            if (isThree(hand)) {
                return hand;
            }
        }

        if (highestScore == 0) {
            return new String[]{};
        }

        int winnerIndex = getHighestIndex(scores);
        return hands[winnerIndex];
    }

    // Extension 3
    public String[] extensionThreeMethods(String[][] hands) {
        // Completely replace this method with suitable methods to solve Extension 3
        // You will also need to add the relevant tests to Extension3Test.java
        // Rerun code from ext 2
        return winningThreeCardHand(hands);
    }

    private int[] getScoresFromHandsArray(String[][] hands) {
        int[] scores = new int[hands.length];

        for (int i = 0; i < hands.length; i++) {
            if (!isPair(hands[i])) {
                // Set score to 0 if hand has no pair
                scores[i] = 0;
            } else {
                // Calculate and set score of each hand
                scores[i] = calculateScore(hands[i]);
            }
        }
        return scores;
    }

    private int getHighestIndex(int[] scores) {
        int maxIndex = 0;
        int maxValue = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > maxValue) {
                maxValue = scores[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private int getHighestScore(int[] scores) {
        int maxValue = 0;

        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > maxValue) {
                maxValue = scores[i];
            }
        }
        return maxValue;
    }

    private int calculateScore(String[] hand) {
        int index = -1;
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i].equals(hand[0])) {
                // Should always be a pair here
                index = i;
            }
        }
        return values[index];
    }

    private boolean isPair(String[] hand) {
        int len = hand.length;

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (hand[i].equals(hand[j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isThree(String[] hand) {
        if (hand.length != 3) {
            return false;
        }
        String firstElement = hand[0];

        for (int i = 1; i < hand.length; i++) {
            if (!firstElement.equals(hand[i])) {
                return false;
            }
        }
        return true;
    }
}
