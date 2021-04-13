package pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;

public class PdfBuilder {

    public static final boolean LANDSCAPE = true;
    public static final boolean PORTRAIT = false;

    private final File dest;
    private final ByteArrayOutputStream bout;
    private final Document document;
    private final PdfWriter writer;

    public PdfBuilder(File dest) throws FileNotFoundException, DocumentException {
        this.dest = dest;
        this.bout = new ByteArrayOutputStream();
        this.document = new Document();
        this.writer = PdfWriter.getInstance(document, bout);
    }

    public void nextPage(boolean orientation, InputStream in) throws IOException {
        if (orientation)
            document.setPageSize(PageSize.A4.rotate());
        else
            document.setPageSize(PageSize.A4);
        if (document.isOpen())
            document.newPage();
        else
            document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, in);
    }

    public void close() throws IOException, DocumentException {
        document.close();
        writer.close();

        PdfReader reader = new PdfReader(bout.toByteArray());
        FileOutputStream out = new FileOutputStream(dest);

        PdfStamper stamper = new PdfStamper(reader, out);
        PdfContentByte canvas;
        int pages = reader.getNumberOfPages();
        for (int i = 1; i <= pages; i++) {
            canvas = stamper.getUnderContent(i);
            Phrase phrase = Phrase.getInstance(String.format("Page %d of %d", i, pages));
            Rectangle r = reader.getPageSizeWithRotation(i);
            float offset = r.getWidth() > r.getHeight() ? ColumnText.getWidth(phrase) + 32 : ColumnText.getWidth(phrase);
            phrase.getFont().setSize(10);
            ColumnText.showTextAligned(canvas, i, phrase, r.getWidth() - offset, 32, 0);
        }
        stamper.close();
        reader.close();
    }
}
