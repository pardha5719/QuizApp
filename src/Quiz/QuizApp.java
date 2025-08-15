package Quiz;

public class QuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.loadQuestions();
        quiz.run();
    }
}
