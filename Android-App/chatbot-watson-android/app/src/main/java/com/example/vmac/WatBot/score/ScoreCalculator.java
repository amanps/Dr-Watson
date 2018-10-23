import java.util.ArrayList;

public interface ScoreCalculator {

    public int getScore(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms, ArrayList<String> ResponsedAnswer,
            String CorrectAnswer);

    public String MissSymptoms(ArrayList<String> AllSymptoms,
            ArrayList<String> AskedSymptoms);
}