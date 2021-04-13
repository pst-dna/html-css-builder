package html.table;

import html.AttributeShortcuts;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Element;

@AllArgsConstructor
public class TablePart implements AttributeShortcuts<TablePart> {

    private final Table parent;
    private final Element selected;

    public TablePart attribute(String key, String value) {
        selected.attr(key, value);
        return this;
    }

    public Table parent() {
        return parent;
    }

    public TableRow<TablePart> tr() {
        return new TableRow<>(this, selected.appendElement("tr"));
    }
}