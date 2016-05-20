package io.fabianterhorst.fastlayout.converters;

import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 18.05.16.
 */
@Converter
public class DefaultAttributesConverter extends LayoutConverter {

    @Override
    public LayoutAttribute onConvertLayoutAttributeValue(Object attributeValue, String attributeName) {
        switch (attributeName) {
            case "android:orientation":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "LinearLayout." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:gravity":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:layout_gravity":
                return onConvertLayoutAttribute(String.valueOf(attributeValue), "Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
        }
        return super.onConvertLayoutAttributeValue(attributeValue, attributeName);
    }

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:layout_gravity":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM, attribute(attributeName.replace("android:layout_", ""), attributeValue));
            case "android:background":
                if (String.valueOf(attributeStartValue).startsWith("R.")) {
                    return new LayoutAttribute(setter("BackgroundResource", attributeStartValue, false));
                }
                break;
            case "android:textSize":
                return new LayoutAttribute(setter("TextSize", "TypedValue.COMPLEX_UNIT_SP," + attributeStartValue.replace("sp", ""), false));
        }
        //Todo : list with all
        if(attributeName.startsWith("android:nextFocus")) {
            return new LayoutAttribute(setter(attributeToName(attributeName) + "Id", attributeStartValue, false));
        }
        return null;
    }
}
