package io.fabianterhorst.fastlayout.converters;

/**
 * Created by fabianterhorst on 17.05.16.
 */
public class LayoutAttribute {

    private String value;

    private Type type;

    public enum Type {
        ASSIGNED, LAYOUT, PARAM, PARAM_CONSTRUCTOR_1, PARAM_CONSTRUCTOR_2, PARAM_CONSTRUCTOR_3, LAYOUT_CONSTRUCTOR_2, LAYOUT_CONSTRUCTOR_3
    }

    public LayoutAttribute() {
        this.type = Type.ASSIGNED;
    }

    public LayoutAttribute(Type type, String value) {
        this.value = value;
        this.type = type;
    }

    public LayoutAttribute(String value) {
        this.value = value;
        this.type = Type.LAYOUT;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }
}
