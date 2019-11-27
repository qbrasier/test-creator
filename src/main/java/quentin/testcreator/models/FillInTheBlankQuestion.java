package quentin.testcreator.models;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.IOException;

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

    @Override
    public Document addToPDF(Document doc)  throws IOException {
        String print = this.getQuestionText();
        String blank = "";
        for(int i = 0; i < this.getCorrectAnswer().length(); i++)
            blank += "__";
        print = print.replace(this.getCorrectAnswer(), blank);
        doc.add( new Paragraph(print));
        return doc;
    }
}
