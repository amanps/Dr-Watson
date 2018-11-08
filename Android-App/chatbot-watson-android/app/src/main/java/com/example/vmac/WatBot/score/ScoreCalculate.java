package com.example.vmac.WatBot.score;

import java.util.ArrayList;

public class ScoreCalculate implements ScoreCalculator {

    @Override
    public int getScore(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms, ArrayList<String> ResponsedAnswer,
            String CorrectAnswer) {
        int score = 0;
        int correctSymptoms = 0;
        int countTotal = AllSymptoms.size();
        int countAsked = AskedSymptoms.size();

        if (ResponsedAnswer.size() > 1) {
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
        } else if (ResponsedAnswer.size() == 1) {
            for (int i = 0; i < countAsked; i++) {
                if (AllSymptoms.contains(AskedSymptoms.get(i))) {
                    score += 30 / countTotal;
                    correctSymptoms++;
                }
            }
            if (ResponsedAnswer.get(0).equals(CorrectAnswer)) {
                score += 50;
                score += (correctSymptoms) * 20 / countAsked;
            }
        }
        return score;
    }
    
    @Override
    public String MissSymptoms(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms) {
        String Message = "You missed the following symptoms: ";
        String Symptom = "";
        ArrayList<String> notAsked = AllSymptoms;
        for (int i = 0; i < AskedSymptoms.size(); i++) {
            if (AllSymptoms.contains(AskedSymptoms.get(i))) {
                notAsked.remove(AskedSymptoms.get(i));
            }
        }
        if (notAsked.size() == 0) {
            Message = "You've got all of the symptoms. Great Job!";
            return Message;
        }
        for (int i = 0; i < notAsked.size(); i++) {
            if (notAsked.get(i).length() > 1) {
                Symptom = notAsked.get(i).substring(0, 1).toUpperCase()
                        + notAsked.get(i).substring(1);
                int index = Symptom.indexOf('_');
                if (index >= 0) {
                    Symptom = notAsked.get(i).substring(0, 1).toUpperCase()
                            + notAsked.get(i).substring(1, index) + ' '
                            + notAsked.get(i).substring(index + 1);
                }
            } else if (notAsked.get(i).length() == 1) {
                Symptom = notAsked.get(i).substring(0, 1).toUpperCase();
                int index = Symptom.indexOf('_');
                if (index >= 0) {
                    Symptom = " ";
                }
            }
            Message += Symptom;
            if (i != notAsked.size() - 1) {
                Message += ", ";
            } else {
                Message += ".";
            }
        }
        return Message;
    }
}
