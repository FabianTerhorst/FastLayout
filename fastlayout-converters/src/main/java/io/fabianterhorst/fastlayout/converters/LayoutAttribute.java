package io.fabianterhorst.fastlayout.converters;

/**
 * Created by fabianterhorst on 17.05.16.
 */
public class LayoutAttribute {

    private String value;

    private Type type;

    private boolean last;

    public enum Type {
        ASSIGNED, LAYOUT, PARAM, PARAM_CONSTRUCTOR_1(1),
        PARAM_CONSTRUCTOR_2(2), PARAM_CONSTRUCTOR_3(3),
        LAYOUT_CONSTRUCTOR_1(1), LAYOUT_CONSTRUCTOR_2(2), LAYOUT_CONSTRUCTOR_3(3);
        private int index;

        Type() {
            index = 0;
        }

        Type(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    public LayoutAttribute() {
        this.type = Type.ASSIGNED;
        this.last = false;
    }

    public LayoutAttribute(Type type, String value) {
        this.value = value;
        this.type = type;
    }

    public LayoutAttribute(String value) {
        this.value = value;
        this.type = Type.LAYOUT;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isLast() {
        return last;
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

    public boolean isLayoutConstructor() {
        return type == Type.LAYOUT_CONSTRUCTOR_1 ||
                type == Type.LAYOUT_CONSTRUCTOR_2 || type == Type.LAYOUT_CONSTRUCTOR_3;
    }

    public boolean isParamsConstructor() {
        return type == Type.PARAM_CONSTRUCTOR_1 ||
                type == Type.PARAM_CONSTRUCTOR_2 || type == Type.PARAM_CONSTRUCTOR_3;
    }
}
