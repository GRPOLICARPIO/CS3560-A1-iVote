// QuestionInfo class is for the questions - single or multiple choice etc.

import java.util.List;

public class QuestionInfo {
    // define the types of questions
    enum QuestionType {
        SINGLE_CHOICE,
        MULTIPLE_CHOICE
    }

    QuestionType type;
    List<String> candidateAnswers;

    // constructor to init the question type and candidate answers
    public QuestionInfo(QuestionType type, List<String> candidateAnswers) {
        this.type = type;
        this.candidateAnswers = candidateAnswers;
    }

    // get the question type
    public QuestionType getType() {
        return type;
    }

    // get the candidate answers
    public List<String> getCandidateAnswers() {
        return candidateAnswers;
    }
}
