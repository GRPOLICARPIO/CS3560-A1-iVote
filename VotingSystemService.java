import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VotingSystemService {
    QuestionInfo question;
    Map<String, List<String>> submissions;

    // constructor to init the voting service with a question
    public VotingSystemService(QuestionInfo question) {
        this.question = question;
        this.submissions = new HashMap<String, List<String>>();
    }

    // submit a student answers
    public void submitAnswer(StudentInfo student, List<String> answers) {
        // if the question is single choice and multiple answers are given = invalid
        if (question.getType() == QuestionInfo.QuestionType.SINGLE_CHOICE && answers.size() > 1) {
            System.out.println("invalid submission: only one answer allowed for single choice question.");
            return;
        }

        // check if the answers are valid options
        for (String answer : answers) {
            if (!question.getCandidateAnswers().contains(answer)) {
                System.out.println("invalid submission: " + answer + " is not a valid answer.");
                return;
            }
        }

        // record the student answers
        submissions.put(student.getId(), answers);
    }

    // print the statistics of the submissions
    public void printStatistics() {
        // initialize a map to count the results
        Map<String, Integer> resultCount = new HashMap<String, Integer>();
        for (String answer : question.getCandidateAnswers()) {
            resultCount.put(answer, 0);
        }

        // count submissions
        for (List<String> answers : submissions.values()) {
            for (String answer : answers) {
                resultCount.put(answer, resultCount.get(answer) + 1);
            }
        }

        // print results
        for (String answer : resultCount.keySet()) {
            System.out.println(answer + " : " + resultCount.get(answer));
        }
    }
}
