public class SurveyQuestion {
    public final String question;
    public final String[] answers;

    public SurveyQuestion(String question, String answer1, String answer2, String answer3) {
        this.question = question;
        this.answers = new String[] { answer1, answer2, answer3 };
    }
}
