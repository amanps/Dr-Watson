package com.example.vmac.WatBot.score;

import java.util.ArrayList;

public interface ScoreCalculator {

    public int getScore(ArrayList<String> allSymptoms,
            ArrayList<String> askedSymptoms, ArrayList<String> ResponsedAnswer,
            String CorrectAnswer);


}