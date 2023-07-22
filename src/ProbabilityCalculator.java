import java.util.HashMap;
import java.util.Map;

public class ProbabilityCalculator {

    private final Map<Party, PartyManager> partyManagerMap;

    public ProbabilityCalculator(Map<Party, PartyManager> partyManagerMap) {
        this.partyManagerMap = partyManagerMap;
    }

    public void recalculateProbabilities(int questionIndex, int answerIndex) {
        // Calculate P(Answer) and cache the results of P(Answer|Party)
        // calculation for each party.
        double totalProbabilityOfAnswer = 0;
        Map<Party, Double> probabilityOfAnswerGivenPartyMap = new HashMap<>();
        for (Party party : Party.values()) {
            PartyManager partyManager = partyManagerMap.get(party);
            double probabilityOfAnswerGivenParty = partyManager.calculateProbabilityOfAnswerGivenParty(questionIndex, answerIndex);
            totalProbabilityOfAnswer += partyManager.getProbability() * probabilityOfAnswerGivenParty;
            probabilityOfAnswerGivenPartyMap.put(party, probabilityOfAnswerGivenParty);
        }

        // Calculate P(Party|Answer) for each party.
        for (Party party : Party.values()) {
            PartyManager partyManager = partyManagerMap.get(party);
            // Use Bayes' Rule to calculate the probability of this party
            // given the answer provided by the user.
            double probabilityOfPartyGivenAnswer =
                partyManager.getProbability()
                * probabilityOfAnswerGivenPartyMap.get(party)
                / totalProbabilityOfAnswer;
            // Update the "prior probability" for this party.
            partyManager.setProbability(probabilityOfPartyGivenAnswer);
        }
    }

}
