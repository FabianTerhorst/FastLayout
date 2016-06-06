package io.fabianterhorst.fastlayout.converters;

import java.util.ArrayList;
import java.util.List;

import io.fabianterhorst.fastlayout.annotations.Converter;

@Converter
public class CardViewLayoutConverter extends LayoutConverter {

    private Object padding[] = new Object[]{null, null, null, null};//left,top,right,bottom

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, String attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "app:cardCornerRadius":
                return new LayoutAttribute(setter("Radius", attributeValue, false));
            case "app:cardMaxElevation":
                return new LayoutAttribute(setter("MaxCardElevation", attributeValue, false));
            case "app:cardUseCompatPadding":
                return new LayoutAttribute(setter("UseCompatPadding", attributeValue, false));
            case "app:cardPreventCornerOverlap":
                return new LayoutAttribute(setter("PreventCornerOverlap", attributeValue, false));
            case "app:contentPaddingLeft":
                padding = new Object[]{attributeValue, padding[1], padding[2], padding[3]};
                return new LayoutAttribute();
            case "app:contentPaddingTop":
                padding = new Object[]{padding[0], attributeValue, padding[2], padding[3]};
                return new LayoutAttribute();
            case "app:contentPaddingRight":
                padding = new Object[]{padding[0], padding[1], attributeValue, padding[3]};
                return new LayoutAttribute();
            case "app:contentPaddingBottom":
                padding = new Object[]{padding[0], padding[1], padding[2], attributeValue};
                return new LayoutAttribute();
            case "app:contentPadding":
                padding = new Object[]{attributeValue, attributeValue, attributeValue, attributeValue};
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
            finished.add(new LayoutAttribute(LayoutAttribute.Type.LAYOUT, setter("ContentPadding", padding[0] + "," + padding[1] + "," + padding[2] + "," + padding[3], false)));
        }
        return finished;
    }
}
