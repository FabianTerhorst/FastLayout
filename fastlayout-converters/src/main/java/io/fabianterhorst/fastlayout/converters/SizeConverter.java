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
                return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_1, String.valueOf(attributeValue).toUpperCase(), false);
            case "android:layout_height":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_2, String.valueOf(attributeValue).toUpperCase(), false);
            case "android:layout_weight":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM_CONSTRUCTOR_3, attributeValue, false);
        }
        return null;
    }
}
