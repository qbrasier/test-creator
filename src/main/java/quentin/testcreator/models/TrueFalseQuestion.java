package quentin.testcreator.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class TrueFalseQuestion extends Question{
    @NotNull
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer ){
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    public TrueFalseQuestion(){
        super();
    }

    public boolean isCorrectAnswer() {
        return correctAnswer;
    }
    public boolean getAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean answer) {
        this.correctAnswer = answer;
    }
}
