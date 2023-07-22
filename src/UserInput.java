import java.util.Scanner;

public class UserInput {

    private static Scanner scanner = new Scanner(System.in);

    public static byte askSurveyQuestion(int questionNumber, SurveyQuestion surveyQuestion) {
        System.out.println();
        System.out.println(questionNumber + ") " + surveyQuestion.question);
        char option = 'a';
        for (String answer : surveyQuestion.answers) {
            System.out.println("   " + option + ") " + answer);
            option++;
        }
        String userInput;
        do {
            System.out.println();
            System.out.print("Enter a, b, or c: ");
            userInput = scanner.nextLine().toLowerCase();
        } while (!userInput.equals("a") && !userInput.equals("b") && !userInput.equals("c"));
        return (byte) (userInput.codePointAt(0) - 'a');
    }

    public static Party askPartyAffiliation() {
        System.out.println();
        System.out.println("What is your political party affiliation?");
        System.out.println("   a) Democrat");
        System.out.println("   b) Republican");
        System.out.println("   c) Libertarian");
        System.out.println("   d) Green");
        String userInput;
        do {
            System.out.println();
            System.out.print("Enter a, b, c, or d: ");
            userInput = scanner.nextLine().toLowerCase();
        } while (!userInput.equals("a") && !userInput.equals("b") && !userInput.equals("c") && !userInput.equals("d"));
        int partyIndex = userInput.codePointAt(0) - 'a';
        return Party.values()[partyIndex];
    }

}
