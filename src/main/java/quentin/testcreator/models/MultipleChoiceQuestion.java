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

    @Override
    public Document addToPDF(Document doc, int number)  throws IOException {
        char[] letters = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        Table table = new Table(2);

        Cell questionCell = new Cell();
        questionCell.add( new Paragraph(number + ". " + this.getQuestionText()) );
        questionCell.setBorder(Border.NO_BORDER);
        questionCell.setTextAlignment(TextAlignment.LEFT);
        table.addHeaderCell(  questionCell   );

        for (int i = 0; i < this.getChoices().length; i++ ) {
            Cell cell = new Cell().add(new Paragraph(letters[i] + ". " + this.getChoices()[i]));
            cell.setPaddingRight(10);
            cell.setPaddingLeft(10);
            cell.setTextAlignment(TextAlignment.LEFT);
            cell.setBorder(Border.NO_BORDER);
            table.addCell(cell);
        }
        doc.add(table);
        return doc;
    }
}
