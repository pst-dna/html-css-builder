package html.style;

import java.util.Map;
import java.util.TreeMap;

public class Selector {

    private final String name;
    private final Style parent;
    private final Map<String, String> properties;

    public Selector(Style parent, String name) {
        this.name = name;
        this.parent = parent;
        this.properties = new TreeMap<>();
    }

    public Selector property(String key, String value) {
        properties.put(key, value);
        return this;
    }

    public Style parent() {
        return parent;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder
                .append('\t')
                .append(name)
                .append(" {");

        properties.keySet().forEach(key -> builder
                .append("\t\t")
                .append(key)
                .append(": ")
                .append(properties.get(key))
                .append(";\n"));

        builder.append("\t}\n");

        return builder.toString();
    }
}
