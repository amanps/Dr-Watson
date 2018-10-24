package com.example.vmac.WatBot.score;

import java.util.ArrayList;

public class ScoreCalculate implements ScoreCalculator {

    @Override
    public int getScore(ArrayList<String> allSymptoms,
                        ArrayList<String> askedSymptoms, ArrayList<String> ResponsedAnswer,
                        String CorrectAnswer) {
        int score = 0;
        int countTotal = allSymptoms.size();
        int countAsked = askedSymptoms.size();

        for (int i = 0; i < ResponsedAnswer.size(); i++) {
            if (ResponsedAnswer.get(i).equals(CorrectAnswer)) {
                score += 50;
                int scorePercentage = (countTotal - countAsked) * 100
                        / countTotal;
                if (scorePercentage > 90) {
                    score = 100;
                } else {
                    score += scorePercentage / 2;
                }
            } else {
                score -= 10;
            }
        }
        return score;
    }
}
