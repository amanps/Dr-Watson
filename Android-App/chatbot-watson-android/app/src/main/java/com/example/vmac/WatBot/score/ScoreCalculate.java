package com.example.vmac.WatBot.score;

import java.util.ArrayList;

public class ScoreCalculate implements ScoreCalculator {

    @Override
    public String getScore(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms, ArrayList<String> ResponsedAnswer,
            String CorrectAnswer) {
        int score = 0;
        int correctSymptoms = 0;
        int countTotal = AllSymptoms.size();
        int countAsked = AskedSymptoms.size();
        if (countTotal == 0 || CorrectAnswer.length() == 0) {
            return "Whoops, that didn't work. Please try again.";
        }
        if (countAsked == 0) {
            return "Your score is 0.\nYou failed to ask the patient about any symptoms.";
        }
        int checkedAllSym = 60;
        String message = "Your score is ";
        String finalMesg = "";
        String[] corrAns = CorrectAnswer.split("_");
        String ansMsg = "";
        for (int i = 0; i < corrAns.length; i++) {
            ansMsg += ' ' + corrAns[i].substring(0, 1).toUpperCase()
                    + corrAns[i].substring(1);
        }
        finalMesg += "It seems like you did not identify the correct disease,"
                + ansMsg + ".\n";

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
        } else if (ResponsedAnswer.size() == 1)

        {
            if (ResponsedAnswer.get(0).equals(CorrectAnswer)) {
                finalMesg = "Congratulations! You have identified the diesease correctly. \n";
                checkedAllSym = 30;
                score += 50;
            }
            for (int i = 0; i < countAsked; i++) {
                if (AllSymptoms.contains(AskedSymptoms.get(i))) {
                    score += checkedAllSym / countTotal;
                    correctSymptoms++;
                }
            }
            score += (correctSymptoms) * 20 / countAsked;
        }

        message += score + ". \n";
        message += finalMesg;
        message +=

                MissSymptoms(AllSymptoms, AskedSymptoms);
        return message;
    }

    public static String MissSymptoms(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms) {
        String Message = "Unfortunately, you missed the following symptoms:";
        String Symptom = "";
        ArrayList<String> notAsked = AllSymptoms;
        for (int i = 0; i < AskedSymptoms.size(); i++) {
            if (AllSymptoms.contains(AskedSymptoms.get(i))) {
                notAsked.remove(AskedSymptoms.get(i));
            }
        }
        if (notAsked.size() == 0) {
            Message = "You correctly investigated all possible symptoms. Great Job!";
            return Message;
        }
        for (int i = 0; i < notAsked.size(); i++) {
            if (notAsked.get(i).length() > 0) {
                String[] sym = notAsked.get(i).split("_");
                for (int j = 0; j < sym.length; j++) {
                    Symptom += ' ' + sym[j].substring(0, 1).toUpperCase()
                            + sym[j].substring(1);
                }
            } else {
                Symptom = "";
            }
            Message += Symptom;
            Symptom = "";
            if (i != notAsked.size() - 1 && notAsked.get(i).length() > 0) {
                Message += ",";
            } else if (i == notAsked.size() - 1) {
                Message += ".";
            }
        }
        return Message;
    }
}
