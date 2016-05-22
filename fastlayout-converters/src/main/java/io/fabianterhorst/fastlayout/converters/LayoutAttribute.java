package io.fabianterhorst.fastlayout.converters;

/**
 * Created by fabianterhorst on 17.05.16.
 */
public class LayoutAttribute {

    private Object value;

    private Type type;

    public enum Type {
        ASSIGNED, LAYOUT, PARAM, PARAM_CONSTRUCTOR_1, PARAM_CONSTRUCTOR_2, PARAM_CONSTRUCTOR_3, LAYOUT_CONSTRUCTOR_2, LAYOUT_CONSTRUCTOR_3
    }

    public LayoutAttribute() {
        this.type = Type.ASSIGNED;
    }

    public LayoutAttribute(Type type, Object value) {
        this.value = value;
        this.type = type;
    }

    public LayoutAttribute(Object value) {
        this.value = value;
        this.type = Type.LAYOUT;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }
}
