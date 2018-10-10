package com.example.vmac.WatBot;

import java.util.ArrayList;

public class Symptom {

    String type;
    ArrayList<String> positive;
    ArrayList<String> negative;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getPositive() {
        return positive;
    }

    public void setPositive(ArrayList<String> positive) {
        this.positive = positive;
    }

    public ArrayList<String> getNegative() {
        return negative;
    }

    public void setNegative(ArrayList<String> negative) {
        this.negative = negative;
    }
}
