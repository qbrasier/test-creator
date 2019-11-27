package quentin.testcreator.models;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Question {
    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private String questionText;

    //@NotNull
    @ManyToOne
    private Test test;

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

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String text) {
        this.questionText = text;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Document addToPDF(Document doc, int number) throws IOException {
        doc.add( new Paragraph(this.getQuestionText()));
        return doc;
    }
}
