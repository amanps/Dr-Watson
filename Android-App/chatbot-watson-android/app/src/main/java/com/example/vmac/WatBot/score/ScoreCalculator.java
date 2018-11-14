package com.example.vmac.WatBot.score;

import java.util.ArrayList;

public interface ScoreCalculator {

    public String getScore(ArrayList<String> allSymptoms,
            ArrayList<String> askedSymptoms, ArrayList<String> ResponsedAnswer,
            String CorrectAnswer);
}
