package html;

import html.style.Style;
import html.table.Table;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pdf.PdfBuilder;

import java.io.ByteArrayInputStream;
import java.io.File;

public class Example {

    public static void main(String[] args) throws Exception {
        Document page = Jsoup.parse("<html><head></head><body></body></html>");
        page.head().appendElement("title").text("Example page");

        Style style = new Style();
        style.select("h1").property("text-color", "red");
        style.select("table, th, td")
                .property("border-width", "1px")
                .property("border-collapse", "collapse");

        page.head().appendElement("style").text(style.toString());

        Table table = new Table(page.body());

        table.thead().tr().th().attribute("colspan", "3");
        table.thead().tr().ths("Number", "Uppercase", "Lowercase");

        table.tbody().tr().td("1").td("A").td("a");
        table.tbody()
                .tr().tds("2", "B", "b")
                .parent()
                .tr().td().text("3").parent().td("C").tds("c");

        System.out.println(page.outerHtml());

        PdfBuilder builder = new PdfBuilder(new File("./example.pdf"));
        builder.nextPage(PdfBuilder.PORTRAIT, new ByteArrayInputStream(page.outerHtml().getBytes()));
        builder.close();
    }
}
