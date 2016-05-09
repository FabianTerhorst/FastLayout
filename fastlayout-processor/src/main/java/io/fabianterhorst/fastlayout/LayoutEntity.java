package io.fabianterhorst.fastlayout;

import java.util.ArrayList;
import java.util.List;

public class LayoutEntity {

    private String id;

    private String name;

    private List<LayoutEntity> children;

    private LayoutParam layoutParams;

    private boolean hasChildren;

    public LayoutEntity(){
        children = new ArrayList<>();
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

    public List<LayoutEntity> getChildren() {
        return children;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }
}
