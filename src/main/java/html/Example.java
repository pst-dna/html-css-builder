package html;

import html.table.Table;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Example {

    public static void main(String[] args) {
        Document page = Jsoup.parse("<html><head></head><body></body></html>");
        page.head().appendElement("title").text("Example page");
        
        Table table = new Table(page.body());

        table.thead().tr().th().attribute("colspan", "3");
        table.thead().tr().ths("Number", "Uppercase", "Lowercase");

        table.tbody().tr().td("1").td("A").td("a");
        table.tbody()
                .tr().tds("2", "B", "b")
                .parent()
                .tr().td().text("3").parent().td("C").tds("c");

        System.out.println(page.outerHtml());
    }
}
