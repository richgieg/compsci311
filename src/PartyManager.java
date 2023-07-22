import java.util.List;

public class PartyManager {

    public final Party party;
    private double probability;
    private final String filePath;
    private List<byte[]> previousSurveys;

    public PartyManager(Party party, double probability) {
        this.party = party;
        this.probability = probability;
        filePath = "data/" + party.name().toLowerCase() + ".bin";
        previousSurveys = SurveyIO.readSurveysFromBinaryFile(filePath);
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double calculateProbabilityOfAnswerGivenParty(int questionIndex, int answerIndex) {
        int tally = 0;
        for (byte[] surveyAnswers : previousSurveys) {
            if (surveyAnswers[questionIndex] == answerIndex) {
                tally++;
            }
        }
        return (double) tally / previousSurveys.size();
    }

    public void saveSurveyAnswerIndices(byte[] surveyAnswerIndices) {
        this.previousSurveys.add(surveyAnswerIndices);
        SurveyIO.writeSurveysToBinaryFile(previousSurveys, filePath);
    }

}
