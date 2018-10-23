import java.util.ArrayList;

public class ScoreCalculate implements ScoreCalculator {

    @Override
    public int getScore(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms, ArrayList<String> ResponsedAnswer,
            String CorrectAnswer) {
        int score = 0;
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
                    score += 40 / countTotal;
                    correctSymptoms++;
                }
                if (ResponsedAnswer.get(0).equals(CorrectAnswer)) {
                    score += 30;
                    score += (countTotal - correctSymptoms) * 30 / countTotal;
                }
            }
        }
        return score;
    }

    @Override
    public String MissSymptoms(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms) {
        String Message = "";
        ArrayList<String> notAsked = AllSymptoms;
        for (int i = 0; i < AllSymptoms.size(); i++) {
            if (AllSymptoms.contains(AskedSymptoms.get(i))) {
                notAsked.remove(AskedSymptoms.get(i));
            }
        }
        for (int i = 0; i < notAsked.size(); i++) {
            String Symptom = notAsked.get(i).substring(0, 1).toUpperCase()
                    + notAsked.get(i).substring(1);
            Symptom.replace("_", " ");
            Message = "You missed the following symptoms: " + Symptom;
            if(i!=notAsked.size()-1){
                Message+=", ";
            }else{
                Message+=".";
            }
        }
        return Message;
    }
}
