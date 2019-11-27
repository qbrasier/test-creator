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
    @Override
    public Document addToPDF(Document doc, int number)  throws IOException {

        Table table = new Table(4);

        Cell numCell = new Cell();
        numCell.add(new Paragraph(number + ". "));
        numCell.setBorder(Border.NO_BORDER);
        numCell.setPaddingLeft(0);
        numCell.setTextAlignment(TextAlignment.LEFT);
        table.addCell(numCell);

        String[] tf = {"True", "False"};
        for (int i = 0; i < tf.length; i++ ) {
            Cell cell = new Cell().add(new Paragraph( tf[i]));
            cell.setPaddingRight(10);
            cell.setTextAlignment(TextAlignment.LEFT);
            cell.setBorder(Border.NO_BORDER);
            table.addCell(cell);
        }
        Cell answerCell = new Cell();
        answerCell.add(new Paragraph(this.getQuestionText()));
        answerCell.setBorder(Border.NO_BORDER);
        table.addCell(answerCell);
        doc.add(table);
        return doc;
    }
}
