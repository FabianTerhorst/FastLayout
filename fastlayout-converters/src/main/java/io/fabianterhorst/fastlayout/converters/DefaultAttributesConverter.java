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
            case "android:gravity":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:layout_gravity":
                return onConvertLayoutAttribute(String.valueOf(attributeValue), "Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:backgroundTintMode":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "android.graphics.PorterDuff.Mode." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:accessibilityLiveRegion":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "View.ACCESSIBILITY_LIVE_REGION_" + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:drawingCacheQuality":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "View.DRAWING_CACHE_QUALITY_" + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            //Todo : viewgroup attribute
            case "android:descendantFocusability":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "ViewGroup.FOCUS_" + stringToConstant(String.valueOf(attributeValue)).toUpperCase(), attributeName, false);
        }
        return super.onConvertLayoutAttributeValue(attributeValue, attributeName);
    }

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "style":
                return new LayoutAttribute(LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_3, attributeValue);
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
