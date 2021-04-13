package html.table;

import html.AttributeShortcuts;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Element;

@AllArgsConstructor
public class TableCell<P> implements AttributeShortcuts<TableCell<P>> {

    private final TableRow<P> parent;
    private final Element selected;

    public TableCell<P> attribute(String key, String value) {
        selected.attr(key, value);
        return this;
    }

    public TableCell<P> text(String format, Object... args) {
        return text(String.format(format, args));
    }

    public TableCell<P> text(String text) {
        selected.text(text);
        return this;
    }

    public TableRow<P> parent() {
        return parent;
    }
}