package io.fabianterhorst.fastlayout.converters;

import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 18.05.16.
 */
@Converter
public class SizeConverter extends LayoutConverter {

    @Override
    public LayoutAttribute onConvertLayoutAttribute(Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:layout_width":
                if(isString) {
                    return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_1, String.valueOf(attributeValue).toUpperCase());
                } else {
                    return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_1,attributeValue);
                }
            case "android:layout_height":
                if(isString) {
                    return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_2, String.valueOf(attributeValue).toUpperCase());
                } else {
                    return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_2,attributeValue);
                }
            case "android:layout_weight":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_3, attributeValue);
        }
        return null;
    }
}
