package es.uji.daal.easyrent.pdf_view;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import es.uji.daal.easyrent.model.BookingProposal;
import es.uji.daal.easyrent.model.Invoice;
import es.uji.daal.easyrent.utils.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Alberto on 18/06/2016.
 */
public class InvoiceView extends AbstractITextPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Invoice invoice = (Invoice) model.get("invoice");
        BookingProposal proposal = invoice.getProposal();

        document.add(new Paragraph(String.format("Booking Invoice number %05d", invoice.getNumber())));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {1.0f, 6.0f, 1.0f, 2.0f});
        table.setSpacingBefore(10);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(BaseColor.WHITE);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(BaseColor.ORANGE);
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Item nº", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Item description", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Item quantity", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Item price", font));
        table.addCell(cell);

        cell.setBackgroundColor(BaseColor.WHITE);
        table.addCell("1");
        table.addCell("One day property rental");
        int numberOfDays = DateUtils.daysBetweenDates(proposal.getStartDate(), proposal.getEndDate()) + 1;
        table.addCell(Integer.toString(numberOfDays));
        table.addCell(String.format("%.2f€", proposal.getTotalAmount() / (float) numberOfDays));

        table.addCell("2");
        table.addCell("One day property rental");
        table.addCell("1");
        table.addCell("0.00 €");

        document.add(table);
    }
}
