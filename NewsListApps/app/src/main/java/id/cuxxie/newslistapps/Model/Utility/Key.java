package id.cuxxie.newslistapps.Model.Utility;

/**
 * Created by hendri on 9/8/17.
 */

public enum Key {
    ARTICLE("article"), SOURCE("source"),
    ARTICLES("articles"), SOURCES("sources"),
    CATEGORY("category");
    private final String stringValue;
    Key(final String s) { stringValue = s; }
    public String toString() { return stringValue; }
}
