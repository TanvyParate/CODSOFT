import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

class Question {
    String questionText;
    ArrayList<String> options;
    int correctOptionIndex;

    public Question(String questionText, ArrayList<String> options, int correctOptionIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
}

class Quiz {
    ArrayList<Question> questions;
    int score;

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println("\n" + question.questionText);

            for (int i = 0; i < question.options.size(); i++) {
                System.out.println((i + 1) + ". " + question.options.get(i));
            }

            int userAnswer = -1;
            System.out.print("Enter your answer (1-" + question.options.size() + "): ");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up! Moving to the next question.");
                    scanner.nextLine(); // Consume the newline character
                    timer.cancel();
                }
            }, 10000); // 10 seconds timeout

            try {
                userAnswer = scanner.nextInt();
                timer.cancel();
            } catch (Exception e) {
                // Handle non-integer input
                System.out.println("Invalid input. Moving to the next question.");
                scanner.nextLine(); // Consume the newline character
            }

            if (userAnswer == question.correctOptionIndex + 1) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect! The correct answer was: " + (question.correctOptionIndex + 1) + "\n");
            }
        }

        System.out.println("Quiz completed! Your final score is: " + score + " out of " + questions.size());
    }
}

public class QuizAppTimer {
    public static void main(String[] args) {
        // Create quiz questions
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", new ArrayList<>(List.of("Berlin", "Paris", "London", "Madrid")), 1));
        questions.add(new Question("Which programming language is Java?", new ArrayList<>(List.of("Scripting", "Compiled", "Interpreted", "Markup")), 2));
        questions.add(new Question("What is the largest mammal?", new ArrayList<>(List.of("Elephant", "Blue Whale", "Giraffe", "Gorilla")), 1));

        // Create a quiz
        Quiz quiz = new Quiz(questions);

        // Start the quiz
        quiz.startQuiz();
    }
}
