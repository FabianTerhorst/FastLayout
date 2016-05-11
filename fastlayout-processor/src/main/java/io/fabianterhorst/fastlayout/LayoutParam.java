package io.fabianterhorst.fastlayout;

public class LayoutParam {

    public static final int LEFT_OF = 0;
    public static final int RIGHT_OF = 1;
    public static final int ABOVE = 2;
    public static final int BELOW = 3;
    public static final int ALIGN_BASELINE = 4;
    public static final int ALIGN_LEFT = 5;
    public static final int ALIGN_TOP = 6;
    public static final int ALIGN_RIGHT = 7;
    public static final int ALIGN_BOTTOM = 8;
    public static final int ALIGN_PARENT_LEFT = 9;
    public static final int ALIGN_PARENT_TOP = 10;
    public static final int ALIGN_PARENT_RIGHT = 11;
    public static final int ALIGN_PARENT_BOTTOM = 12;
    public static final int CENTER_IN_PARENT = 13;
    public static final int CENTER_HORIZONTAL = 14;
    public static final int CENTER_VERTICAL = 15;
    public static final int START_OF = 16;
    public static final int END_OF = 17;
    public static final int ALIGN_START = 18;
    public static final int ALIGN_END = 19;
    public static final int ALIGN_PARENT_START = 20;
    public static final int ALIGN_PARENT_END = 21;

    private String name;

    private String width;

    private String height;

    private Object[] padding;

    private Object[] margins;

    private Object weight;

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setPadding(Object paddingLeft, Object paddingTop, Object paddingRight, Object paddingBottom) {
        this.padding = new Object[]{paddingLeft == null ? 0 : paddingLeft, paddingTop == null ? 0 : paddingTop, paddingRight == null ? 0 : paddingRight, paddingBottom == null ? 0 : paddingBottom};
    }

    public void setMargins(Object marginLeft, Object marginTop, Object marginRight, Object marginBottom) {
        this.margins = new Object[]{marginLeft == null ? 0 : marginLeft, marginTop == null ? 0 : marginTop, marginRight == null ? 0 : marginRight, marginBottom == null ? 0 : marginBottom};
    }

    public void setWeight(Object weight) {
        this.weight = weight;
    }

    public Object getWeight() {
        return weight;
    }

    public Object[] getPadding() {
        return padding;
    }

    public Object[] getMargins() {
        return margins;
    }

    public String getName() {
        return name;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
