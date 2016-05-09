package io.fabianterhorst.fastlayout;

//Todo : rename to LayoutParams
public class LayoutParam {

    private String name;

    private String width;

    private String height;

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public void setHeight(String height) {
        this.height = height;
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
