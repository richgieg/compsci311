import java.util.HashMap;
import java.util.Map;

public class Survey {

    public static void main(String[] args) {
        // Used info from the following page to compute the prior probabilities
        // for these four parties:
        // https://ballot-access.org/2022/12/27/December-2022-ballot-access-news-print-edition/
        Map<Party, PartyManager> partyManagerMap = new HashMap<>() {{
            put(Party.DEMOCRAT, new PartyManager(Party.DEMOCRAT, 0.5603));
            put(Party.REPUBLICAN, new PartyManager(Party.REPUBLICAN, 0.4282));
            put(Party.LIBERTARIAN, new PartyManager(Party.LIBERTARIAN, 0.0087));
            put(Party.GREEN, new PartyManager(Party.GREEN, 0.0028));
        }};

        Dashboard dashboard = new Dashboard(partyManagerMap);
        ProbabilityCalculator probabilityCalculator = new ProbabilityCalculator(partyManagerMap);

        byte[] surveyAnswerIndices = new byte[SurveyQuestions.questions.length];
        for (int questionIndex = 0; questionIndex < SurveyQuestions.questions.length; questionIndex++) {
            dashboard.printPartyProbabilities();
            SurveyQuestion surveyQuestion = SurveyQuestions.questions[questionIndex];
            surveyAnswerIndices[questionIndex] = UserInput.askSurveyQuestion(questionIndex + 1, surveyQuestion);
            probabilityCalculator.recalculateProbabilities(questionIndex, surveyAnswerIndices[questionIndex]);
        }

        dashboard.printPartyProbabilities();

        Party party = UserInput.askPartyAffiliation();
        partyManagerMap.get(party).saveSurveyAnswerIndices(surveyAnswerIndices);

        dashboard.printThankYou();
    }

}
