package io.fabianterhorst.fastlayout.converters;

import java.util.ArrayList;
import java.util.List;

import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 18.05.16.
 */
@Converter
public class PaddingConverter extends LayoutConverter {

    private Object padding[] = new Object[]{null, null, null, null};//left,top,right,bottom

    private Object paddingRelative[] = new Object[]{null, null};//left,top,right,bottom

    @Override
    public LayoutAttribute onConvertLayoutAttribute(Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:paddingLeft":
                padding = new Object[]{attributeValue, padding[1], padding[2], padding[3]};
                return new LayoutAttribute();
            case "android:paddingTop":
                padding = new Object[]{padding[0], attributeValue, padding[2], padding[3]};
                return new LayoutAttribute();
            case "android:paddingRight":
                padding = new Object[]{padding[0], padding[1], attributeValue, padding[3]};
                return new LayoutAttribute();
            case "android:paddingBottom":
                padding = new Object[]{padding[0], padding[1], padding[2], attributeValue};
                return new LayoutAttribute();
            case "android:paddingStart":
                paddingRelative = new Object[]{attributeValue, padding[1], padding[2], padding[3] != null ? padding[3] : paddingRelative[1]};
                return new LayoutAttribute();
            case "android:paddingEnd":
                paddingRelative = new Object[]{padding[0] != null ? padding[0] : paddingRelative[0], padding[1], attributeValue, padding[3]};
                return new LayoutAttribute();
            case "android:padding":
                padding = new Object[]{attributeValue, attributeValue, attributeValue, attributeValue};
                paddingRelative = new Object[]{attributeValue, attributeValue, attributeValue, attributeValue};
                return new LayoutAttribute();
        }
        return null;
    }

    @Override
    public List<LayoutAttribute> onFinish() {
        List<LayoutAttribute> finished = new ArrayList<>();
        if (padding[0] != null || padding[1] != null || padding[2] != null || padding[3] != null) {
            for (int i = 0; i < padding.length; i++) {
                Object current = padding[i];
                if (current == null) {
                    padding[i] = 0;
                }
            }
            finished.add(new LayoutAttribute(LayoutAttribute.Type.LAYOUT, "setPadding(" + padding[0] + "," + padding[1] + "," + padding[2] + "," + padding[3] + ")"));
        }
        if (paddingRelative[0] != null || paddingRelative[1] != null) {
            for (int i = 0; i < padding.length; i++) {
                Object current = padding[i];
                if (current == null) {
                    padding[i] = 0;
                }
            }
            for (int i = 0; i < paddingRelative.length; i++) {
                Object current = paddingRelative[i];
                if (current == null) {
                    paddingRelative[i] = 0;
                }
            }
            finished.add(new LayoutAttribute(LayoutAttribute.Type.LAYOUT, "setPaddingRelative(" + paddingRelative[0] + "," + padding[1] + "," + padding[2] + "," + paddingRelative[1] + ")"));
        }
        return finished;
    }
}
