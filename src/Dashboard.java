import java.text.DecimalFormat;
import java.util.Map;

public class Dashboard {

    private final Map<Party, PartyManager> partyManagerMap;

    public Dashboard(Map<Party, PartyManager> partyManagerMap) {
        this.partyManagerMap = partyManagerMap;
    }

    public void printPartyProbabilities() {
        System.out.println();
        for (Party party : Party.values()) {
            System.out.print("[" + party.name() + ": " + new DecimalFormat("0.00").format(partyManagerMap.get(party).getProbability() * 100) + "%] ");
        }
        System.out.println();
    }

    public void printThankYou() {
        System.out.println();
        System.out.println();
        System.out.println("Thank you for taking this survey!");
        System.out.println();
        System.out.println("Your answers have been saved so this program can make better guesses in the future.");
        System.out.println();
    }

}
