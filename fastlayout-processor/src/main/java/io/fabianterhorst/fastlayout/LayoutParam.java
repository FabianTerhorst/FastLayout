package io.fabianterhorst.fastlayout;

//Todo : rename to LayoutParams
public class LayoutParam {

    private String name;

    private String width;

    private String height;

    private String[] padding;

    private String[] margins;

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public void setPadding(String paddingLeft, String paddingTop, String paddingRight, String paddingBottom) {
        this.padding = new String[]{paddingLeft == null ? "0" : paddingLeft, paddingTop == null ? "0" : paddingTop, paddingRight == null ? "0" : paddingRight, paddingBottom == null ? "0" : paddingBottom};
    }

    public void setMargins(String marginLeft, String marginTop, String marginRight, String marginBottom) {
        this.margins = new String[]{marginLeft == null ? "0" : marginLeft, marginTop == null ? "0" : marginTop, marginRight == null ? "0" : marginRight, marginBottom == null ? "0" : marginBottom};
    }

    public String[] getPadding() {
        return padding;
    }

    public String[] getMargins() {
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
