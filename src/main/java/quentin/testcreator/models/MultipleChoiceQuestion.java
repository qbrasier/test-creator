package quentin.testcreator.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class MultipleChoiceQuestion extends Question {

    @NotNull
    private int correctAnswer;
    //@NotNull
    private String[] choices;

    public MultipleChoiceQuestion(String questionText, int correctAnswer, String[] choices){
        super(questionText);
        this.correctAnswer = correctAnswer;
        this.choices = choices;

    }
    public MultipleChoiceQuestion(){
        super();
    }
    
    public void setCorrectAnswer(int i){
        correctAnswer = i;
    }

    public int getCorrectAnswer(){
        return correctAnswer;
    }

    public boolean checkAnswer(int i){
        if(i == correctAnswer){
            return true;
        }
        return false;
    }

    public String[] getChoices(){
        return choices;
    }

    public void setChoices(String[] newChoices){
        this.choices = newChoices;
    }

}
