package io.fabianterhorst.fastlayout;

public class LayoutParam {

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
