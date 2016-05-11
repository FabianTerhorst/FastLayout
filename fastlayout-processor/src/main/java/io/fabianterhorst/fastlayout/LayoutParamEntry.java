package io.fabianterhorst.fastlayout;

public class LayoutParamEntry {

    private Object value;

    private boolean paramValue;

    private boolean rule;

    public LayoutParamEntry(Object value, boolean paramValue, boolean rule) {
        this.value = value;
        this.paramValue = paramValue;
        this.rule = rule;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setParamValue(boolean paramValue) {
        this.paramValue = paramValue;
    }

    public void setRule(boolean rule) {
        this.rule = rule;
    }

    public Object getValue() {
        return value;
    }

    public boolean isParamValue() {
        return paramValue;
    }

    public boolean isRule() {
        return rule;
    }
}
