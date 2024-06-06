import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SimulationController {
    public static void main(String[] args) {
        // step 1: create a question type and configure the answers
        QuestionInfo.QuestionType questionType = QuestionInfo.QuestionType.MULTIPLE_CHOICE;
        List<String> candidateAnswers = Arrays.asList("A", "B", "C", "D");

        // step 2: configure the question for the VotingService
        QuestionInfo question = new QuestionInfo(questionType, candidateAnswers);
        VotingSystemService votingService = new VotingSystemService(question);

        // step 3: randomly generate a number of students and their answers
        Random random = new Random();
        int numberOfStudents = 20;
        List<StudentInfo> students = new ArrayList<StudentInfo>();
        for (int i = 0; i < numberOfStudents; i++) {
            // generate a unique id for each student
            String studentId = UUID.randomUUID().toString();
            StudentInfo student = new StudentInfo(studentId);
            students.add(student);
        }

        // step 4: submit answers for each student
        for (StudentInfo student : students) {
            // generate random answers for each student
            List<String> answers = new ArrayList<String>();
            generateRandomAnswers(candidateAnswers, random, answers);

            if (answers.isEmpty()) {
                answers.add(candidateAnswers.get(random.nextInt(candidateAnswers.size())));
            }

            // submit
            votingService.submitAnswer(student, answers);
        }

        // step 5: print the results
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
