package quentin.testcreator.models;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class EssayQuestion extends Question {
    @NotNull
    @Min(1)
    @Max(100)
    private int numLines;

    public EssayQuestion(){
        super();
    }

    public EssayQuestion(String questionText, int numLines){
        super(questionText);
        this.numLines = numLines;
    }

    public int getNumLines() {
        return numLines;
    }

    public void setNumLines(int numLines) {
        this.numLines = numLines;
    }
}
