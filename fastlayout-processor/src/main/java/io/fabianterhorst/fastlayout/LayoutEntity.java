package io.fabianterhorst.fastlayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LayoutEntity {

    private String id;

    private String name;

    private List<LayoutEntity> children;

    private LayoutParam layoutParams;

    private boolean hasChildren;

    private Map<Object, LayoutParamEntry> layoutParamsList;

    private boolean relative;

    public LayoutEntity() {
        children = new ArrayList<>();
        //Todo : ArrayMap ? !
        layoutParamsList = new HashMap<>();
        hasChildren = false;
        relative = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public void addLayoutParam(Object name, Object value, boolean paramValue, boolean rule) {
        layoutParamsList.put(name, new LayoutParamEntry(value, paramValue, rule));
    }

    public void addLayoutParam(Object name, Object value, boolean paramValue, boolean rule, boolean number) {
        layoutParamsList.put(name, new LayoutParamEntry(value, paramValue, rule, number));
    }

    public void setLayoutParams(LayoutParam layoutParams) {
        this.layoutParams = layoutParams;
    }

    public void addChild(LayoutEntity child) {
        children.add(child);
    }

    public void addChildren(List<LayoutEntity> child) {
        children.addAll(child);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LayoutParam getLayoutParams() {
        return layoutParams;
    }

    public Map<Object, LayoutParamEntry> getLayoutParamsList() {
        return layoutParamsList;
    }

    public List<LayoutEntity> getChildren() {
        return children;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setRelative(boolean relative) {
        this.relative = relative;
    }

    public boolean isRelative() {
        return relative;
    }
}
