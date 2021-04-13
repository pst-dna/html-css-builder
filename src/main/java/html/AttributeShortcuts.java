package html;

public interface AttributeShortcuts<T> {

    T attribute(String key, String value);

    default T id(String value) {
        return attribute("id", value);
    }

    default T clazz(String value) {
        return attribute("class", value);
    }
}
