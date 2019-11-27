package quentin.testcreator.models;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;

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

    @Override
    public Document addToPDF(Document doc, int number)  throws IOException {

        Table table = new Table(1);
        Cell questionCell = new Cell();
        questionCell.add( new Paragraph(number + ". " + this.getQuestionText()) );
        questionCell.setBorder(Border.NO_BORDER);
        questionCell.setTextAlignment(TextAlignment.LEFT);
        table.addHeaderCell(  questionCell   );

        for (int i = 0; i < this.getNumLines(); i++ ) {
            Cell cell = new Cell().add(new Paragraph("___________________________________________"
                                                    + "___________________________________"));
            cell.setPaddingTop(20);
            cell.setTextAlignment(TextAlignment.LEFT);
            cell.setBorder(Border.NO_BORDER);
            table.addCell(cell);
        }
        doc.add(table);
        return doc;
    }
}
