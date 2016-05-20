package io.fabianterhorst.fastlayout.converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianterhorst on 20.05.16.
 */
public class TextViewLayoutConverter extends LayoutConverter {

    private Object drawable[] = new Object[]{null, null, null, null};//left,top,right,bottom

    private Object drawableRelative[] = new Object[]{null, null};//start,end

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:drawablePadding":
               return new LayoutAttribute(setter("CompoundDrawablePadding", attributeValue, false));
            case "android:drawableTintMode":
                return new LayoutAttribute(setter("CompoundDrawableTintMode", "android.graphics.PorterDuff.Mode." + String.valueOf(attributeValue).toUpperCase(), false));
            case "android:drawableTint":
                return new LayoutAttribute(setter("CompoundDrawableTintList", attributeValue, false));
            case "android:drawableLeft":
                drawable = new Object[]{attributeValue, drawable[1], drawable[2], drawable[3]};
                return new LayoutAttribute();
            case "android:drawableTop":
                drawable = new Object[]{drawable[0], attributeValue, drawable[2], drawable[3]};
                return new LayoutAttribute();
            case "android:drawableRight":
                drawable = new Object[]{drawable[0], drawable[1], attributeValue, drawable[3]};
                return new LayoutAttribute();
            case "android:drawableBottom":
                drawable = new Object[]{drawable[0], drawable[1], drawable[2], attributeValue};
                return new LayoutAttribute();
            case "android:drawableStart":
                drawableRelative = new Object[]{attributeValue, drawable[1], drawable[2], drawableRelative[1] != null ? drawableRelative[1] : drawable[3]};
                return new LayoutAttribute();
            case "android:drawableEnd":
                drawableRelative = new Object[]{drawableRelative[0] != null ? drawableRelative[0] : drawable[0], drawable[1], attributeValue, drawable[3]};
                return new LayoutAttribute();
        }
        return null;
    }

    @Override
    public List<LayoutAttribute> onFinish() {
        List<LayoutAttribute> finished = new ArrayList<>();
        if (drawable[0] != null || drawable[1] != null || drawable[2] != null || drawable[3] != null) {
            finished.add(new LayoutAttribute("setCompoundDrawables(" + drawable[0] + "," + drawable[1] + "," + drawable[2] + "," + drawable[3] + ")"));
        }
        if (drawableRelative[0] != null || drawableRelative[1] != null) {
            finished.add(new LayoutAttribute("setCompoundDrawablesRelative(" + drawableRelative[0] + "," + drawable[1] + "," + drawable[2] + "," + drawableRelative[3] + ")"));
        }
        return finished;
    }
}
