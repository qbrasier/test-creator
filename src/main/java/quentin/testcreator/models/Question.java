package quentin.testcreator.models;

public abstract class Question {
    private int id;
    private String text;

    Question(){

        text = "";
    }
    Question(String questionText){

        text = questionText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
