package io.fabianterhorst.fastlayout.processor;

public class LayoutParamEntry {

    private Object value;

    private boolean paramValue;

    private boolean rule;

    private boolean number;

    public LayoutParamEntry(Object value, boolean paramValue, boolean rule) {
        this.value = value;
        this.paramValue = paramValue;
        this.rule = rule;
        this.number = false;
    }

    public LayoutParamEntry(Object value, boolean paramValue, boolean rule, boolean number) {
        this.value = value;
        this.paramValue = paramValue;
        this.rule = rule;
        this.number = number;
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

    public void setNumber(boolean number) {
        this.number = number;
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

    public boolean isNumber() {
        return number;
    }
}
