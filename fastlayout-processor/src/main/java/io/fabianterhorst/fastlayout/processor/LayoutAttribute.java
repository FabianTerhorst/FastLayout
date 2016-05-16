package io.fabianterhorst.fastlayout.processor;

/**
 * Created by fabianterhorst on 17.05.16.
 */
public class LayoutAttribute {

    private Object value;

    private boolean string;

    public LayoutAttribute(Object value, boolean string) {
        this.value = value;
        this.string = string;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setString(boolean string) {
        this.string = string;
    }

    public Object getValue() {
        return value;
    }

    public boolean isString() {
        return string;
    }
}
