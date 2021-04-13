package html.table;

import html.AttributeShortcuts;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Element;

import java.util.Arrays;

@AllArgsConstructor
public class TableRow<P> implements AttributeShortcuts<TableRow<P>> {

    private final P parent;
    private final Element selected;

    public TableRow<P> attribute(String key, String value) {
        selected.attr(key, value);
        return this;
    }

    public P parent() {
        return parent;
    }

    public TableRow<P> ths(String... texts) {
        Arrays.stream(texts).forEach(this::th);
        return this;
    }

    public TableRow<P> th(String format, Object... args) {
        return th(String.format(format, args));
    }

    public TableRow<P> th(String text) {
        return th().text(text).parent();
    }

    public TableCell<P> th() {
        return new TableCell<>(this, selected.appendElement("th"));
    }

    public TableRow<P> tds(String... texts) {
        Arrays.stream(texts).forEach(this::td);
        return this;
    }

    public TableRow<P> td(String format, Object... args) {
        return td(String.format(format, args));
    }

    public TableRow<P> td(String text) {
        return td().text(text).parent();
    }

    public TableCell<P> td() {
        return new TableCell<>(this, selected.appendElement("td"));
    }
}