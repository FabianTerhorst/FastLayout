package io.fabianterhorst.fastlayout.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.fabianterhorst.fastlayout.converters.LayoutAttribute;

public class LayoutEntity {

    private String id;

    private String name;

    private List<LayoutEntity> children;

    private String layoutParamsName;

    private boolean hasChildren;

    private List<LayoutAttribute> attributes;

    private String rootLayout;

    public LayoutEntity() {
        children = new ArrayList<>();
        attributes = new ArrayList<>();
        hasChildren = false;
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

    public void setRootLayout(String rootLayout) {
        this.rootLayout = rootLayout;
    }

    public void addAttribute(LayoutAttribute attribute) {
        this.attributes.add(attribute);
    }

    public void addAllAttributes(List<LayoutAttribute> attributes) {
        this.attributes.addAll(attributes);
    }

    public void setLayoutParamsName(String layoutParamsName) {
        this.layoutParamsName = layoutParamsName;
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

    public String getLayoutParamsName() {
        return layoutParamsName;
    }

    public List<LayoutEntity> getChildren() {
        return children;
    }

    public String getRootLayout() {
        return rootLayout;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public List<LayoutAttribute> getAttributes() {
        return attributes;
    }
}
