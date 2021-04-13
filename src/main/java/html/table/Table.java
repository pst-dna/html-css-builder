package html.table;

import html.AttributeShortcuts;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Table implements AttributeShortcuts<Table> {

    private final Element element;
    private final Element selected;

    public Table(Element parent) {
        this.element = parent.appendElement("table");
        this.selected = this.element;
    }

    public Table attribute(String key, String value) {
        element.attr(key, value);
        return this;
    }

    public Element parent() {
        return element.parent();
    }

    public TablePart thead() {
        Elements elements = element.getElementsByTag("thead");
        Element e = elements.size() > 0 ? elements.first() : element.appendElement("thead");
        return new TablePart(this, e);
    }

    public TablePart tbody() {
        Elements elements = element.getElementsByTag("tbody");
        Element e = elements.size() > 0 ? elements.first() : element.appendElement("tbody");
        return new TablePart(this, e);
    }

    public TableRow<Table> tr() {
        return new TableRow<>(this, selected.appendElement("tr"));
    }
}
