package com.example.vmac.WatBot;

import java.util.ArrayList;

public class ConversationJson {

    ArrayList<Symptom> symptoms;
    ArrayList<String> age;
    ArrayList<ConversationDisease> diseases;
    Name name;

    public ArrayList<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(ArrayList<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public ArrayList<String> getAge() {
        return age;
    }

    public void setAge(ArrayList<String> age) {
        this.age = age;
    }

    public ArrayList<ConversationDisease> getDiseases() {
        return diseases;
    }

    public void setDiseases(ArrayList<ConversationDisease> diseases) {
        this.diseases = diseases;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
