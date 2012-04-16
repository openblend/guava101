package org.openblend.guava101.support;

/**
 * @author <a href="mailto:ales.justin@jboss.org">Ales Justin</a>
 */
public class ComplexValue {
    private String string;

    private ComplexValue(String reverse) {
        this.string = reverse;
    }

    public static ComplexValue hereBeDragons(String key) {
        return new ComplexValue(new StringBuilder(key).reverse().toString());
    }

    public String getString() {
        return string;
    }
}
