package io.fabianterhorst.fastlayout.converters;

/**
 * Created by fabianterhorst on 17.05.16.
 */
public class LayoutAttribute {

    private Object value;

    private boolean string;

    private Type type;

    public enum Type {
        ASSIGNED, LAYOUT, PARAM, PARAM_CONSTRUCTOR_1, PARAM_CONSTRUCTOR_2, PARAM_CONSTRUCTOR_3
    }

    public LayoutAttribute() {
        this.type = Type.ASSIGNED;
    }

    public LayoutAttribute(Type type, Object value, boolean string) {
        this.value = value;
        this.string = string;
        this.type = type;
    }

    public LayoutAttribute(Object value, boolean string) {
        this.value = value;
        this.string = string;
        this.type = Type.LAYOUT;
    }

    public void setType(Type type) {
        this.type = type;
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

    public Type getType() {
        return type;
    }
}
