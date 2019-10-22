package quentin.testcreator.models;

public class TrueFalseQuestion extends Question{
    private boolean answer;

    TrueFalseQuestion(String questionText, boolean answer){
        super(questionText);
        this.answer = answer;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
