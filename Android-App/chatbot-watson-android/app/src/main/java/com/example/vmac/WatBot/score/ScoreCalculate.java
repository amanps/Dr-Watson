package com.example.vmac.WatBot.score;

import java.util.ArrayList;

public class ScoreCalculate implements ScoreCalculator {

    @Override
    public String getScore(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms, ArrayList<String> ResponsedAnswer,
            String CorrectAnswer) {
        int score = 0;
        String corrAns = CorrectAnswer;
        int correctSymptoms = 0;
        int countTotal = AllSymptoms.size();
        int countAsked = AskedSymptoms.size();
        int checkedAllSym = 60;
        String message = "Your score is ";
        String finalMesg = "";
        int index = corrAns.indexOf('_');
        try {
            if (index >= 0) {
                corrAns = corrAns.substring(0, 1).toUpperCase()
                        + corrAns.substring(1, index) + ' '
                        + corrAns.substring(index + 1, index + 2).toUpperCase()
                        + corrAns.substring(index + 2);
            } else {
                corrAns = corrAns.substring(0, 1).toUpperCase()
                        + corrAns.substring(1);
            }
            finalMesg += "It seems like you did not identify the correct disease, "
                    + corrAns + ".\n";
        } catch (IndexOutOfBoundsException e) {
            System.err
                    .println("CorrectionAnswerNullException: " + e.getMessage()
                            + "\n" + "Your correct answer can not be Null\n");
        }

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
        message += MissSymptoms(AllSymptoms, AskedSymptoms);
        return message;
    }

    public static String MissSymptoms(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms) {
        String Message = "Unfortunately, you missed the following symptoms: ";
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
            } else {
                System.err.println("\nSymptomNullException: "
                        + "One or more of your syptoms are null. Symptoms can not be Null\n");
            }
            Message += Symptom;
            if (i != notAsked.size() - 1 && notAsked.get(i).length() != 0) {
                Message += ", ";
            } else if (i == notAsked.size() - 1) {
                Message += ".";
            }
        }
        return Message;
    }
}
