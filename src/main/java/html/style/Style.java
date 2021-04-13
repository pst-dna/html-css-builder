package html.style;

import java.util.Map;
import java.util.TreeMap;

public class Style {

    private final Map<String, Selector> selectors = new TreeMap<>();

    public Selector select(String key) {
        selectors.putIfAbsent(key, new Selector(this, key));
        return selectors.get(key);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        selectors.keySet().forEach(key -> builder.append(selectors.get(key).toString()));
        return builder.toString();
    }
}
