package quentin.testcreator.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Question {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String questionText;

    Question(){

        questionText = "";
    }
    Question(String questionText){

        this.questionText = questionText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return questionText;
    }

    public void setText(String text) {
        this.questionText = text;
    }
}
