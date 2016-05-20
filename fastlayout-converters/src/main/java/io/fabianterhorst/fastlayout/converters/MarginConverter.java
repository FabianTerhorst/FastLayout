package io.fabianterhorst.fastlayout.converters;

import java.util.ArrayList;
import java.util.List;

import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 18.05.16.
 */
@Converter
public class MarginConverter extends LayoutConverter {

    private Object margin[] = new Object[]{null, null, null, null};//left,top,right,bottom

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:layout_marginLeft":
                margin = new Object[]{attributeValue, margin[1], margin[2], margin[3]};
                return new LayoutAttribute();
            case "android:layout_marginTop":
                margin = new Object[]{margin[0], attributeValue, margin[2], margin[3]};
                return new LayoutAttribute();
            case "android:layout_marginRight":
                margin = new Object[]{margin[0], margin[1], attributeValue, margin[3]};
                return new LayoutAttribute();
            case "android:layout_marginBottom":
                margin = new Object[]{margin[0], margin[1], margin[2], attributeValue};
                return new LayoutAttribute();
            case "android:layout_margin":
                margin = new Object[]{attributeValue, attributeValue, attributeValue, attributeValue};
                return new LayoutAttribute();
            case "android:layout_marginEnd":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM, setter(attributeToName(attributeName).replace("Layout", ""), attributeValue, false));
            case "android:layout_marginStart":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM, setter(attributeToName(attributeName).replace("Layout", ""), attributeValue, false));
        }
        return null;
    }

    @Override
    public List<LayoutAttribute> onFinish() {
        List<LayoutAttribute> finished = new ArrayList<>();
        if (margin[0] != null || margin[1] != null || margin[2] != null || margin[3] != null) {
            for (int i = 0; i < margin.length; i++) {
                Object current = margin[i];
                if (current == null) {
                    margin[i] = 0;
                }
            }
            finished.add(new LayoutAttribute(LayoutAttribute.Type.PARAM, "setMargins(" + margin[0] + "," + margin[1] + "," + margin[2] + "," + margin[3] + ")"));
        }
        return finished;
    }
}
