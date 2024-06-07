// SimulationController is the main class that runs the whole simulation

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SimulationController {
    public static void main(String[] args) {
        // creating question type and configuring answers
        QuestionInfo.QuestionType questionType = QuestionInfo.QuestionType.MULTIPLE_CHOICE;
        List<String> candidateAnswers = Arrays.asList("A", "B", "C", "D");

        // configuring questions
        QuestionInfo question = new QuestionInfo(questionType, candidateAnswers);
        VotingSystemService votingService = new VotingSystemService(question);

        // random generator for studnets and answers
        Random random = new Random();
        int numberOfStudents = 20;
        List<StudentInfo> students = new ArrayList<StudentInfo>();
        for (int i = 0; i < numberOfStudents; i++) {
            // unique id assignment
            String studentId = UUID.randomUUID().toString();
            StudentInfo student = new StudentInfo(studentId);
            students.add(student);
        }

        // submitting answers
        for (StudentInfo student : students) {
            List<String> answers = new ArrayList<String>();
            generateRandomAnswers(candidateAnswers, random, answers);

            if (answers.isEmpty()) {
                answers.add(candidateAnswers.get(random.nextInt(candidateAnswers.size())));
            }

            // submit
            votingService.submitAnswer(student, answers);
        }
        
        votingService.printStatistics();
    }

    // helper to generate random answers
    private static void generateRandomAnswers(List<String> candidateAnswers, Random random, List<String> answers) {
        for (String candidateAnswer : candidateAnswers) {
            if (random.nextBoolean()) {
                answers.add(candidateAnswer);
            }
        }
    }
}
