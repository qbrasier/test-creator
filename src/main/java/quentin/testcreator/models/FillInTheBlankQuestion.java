package quentin.testcreator.models;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

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
    public Document addToPDF(Document doc, int number)  throws IOException {
        String print = number + ". " + this.getQuestionText();
        String blank = "";
        for(int i = 0; i < this.getCorrectAnswer().length(); i++)
            blank += "__";
        print = print.replace(this.getCorrectAnswer(), blank);

        Cell cell = new Cell().add(new Paragraph(print));
        cell.setTextAlignment(TextAlignment.LEFT);
        cell.setPaddingLeft(0);
        cell.setBorder(Border.NO_BORDER);
        Table table = new Table(1);

        table.addCell(cell);
        doc.add( table);
        return doc;
    }
}
