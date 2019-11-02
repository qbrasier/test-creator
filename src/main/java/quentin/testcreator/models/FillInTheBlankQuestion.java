package quentin.testcreator.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class FillInTheBlankQuestion extends Question {

    //this one is gonna be weird because im just gonna omit the correct answer from the question text when displaying the test
    //so it would be better to think of "correctAnswer" as "word to be blanked out"

    @NotNull
    private String correctAnswer;

    public FillInTheBlankQuestion(){
        super();
    }

    public FillInTheBlankQuestion(String questionText, String correctAnswer){
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
