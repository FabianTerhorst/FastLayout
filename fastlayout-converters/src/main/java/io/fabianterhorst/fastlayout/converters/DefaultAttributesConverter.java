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
            case "android:foregroundGravity":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:layout_gravity":
                return onConvertLayoutAttribute(String.valueOf(attributeValue), "Gravity." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:backgroundTintMode":
            case "android:foregroundTintMode":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "android.graphics.PorterDuff.Mode." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:accessibilityLiveRegion":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "View.ACCESSIBILITY_LIVE_REGION_" + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:drawingCacheQuality":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "View.DRAWING_CACHE_QUALITY_" + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            //Todo : viewgroup attribute
            case "android:descendantFocusability":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "ViewGroup.FOCUS_" + stringToConstant(String.valueOf(attributeValue)).toUpperCase(), attributeName, false);
            case "android:importantForAccessibility":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "View.IMPORTANT_FOR_ACCESSIBILITY_" + stringToConstant(String.valueOf(attributeValue)).toUpperCase(), attributeName, false);
        }
        return super.onConvertLayoutAttributeValue(attributeValue, attributeName);
    }

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "style":
            case "android:theme":
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
            //Todo : viewgroup
            case "android:animateLayoutChanges":
                if (Boolean.valueOf(String.valueOf(attributeValue))) {
                    return new LayoutAttribute(setter("LayoutTransition", "new android.animation.LayoutTransition()", false));
                } else {
                    return new LayoutAttribute(setter("LayoutTransition", "LayoutUtils.getDisabledLayoutTransition()", false));
                }
            case "android:transformPivotX":
                return new LayoutAttribute(setter("PivotX", attributeValue, false));
            case "android:transformPivotY":
                return new LayoutAttribute(setter("PivotY", attributeValue, false));

        }
        //Todo : list with all
        if (attributeName.startsWith("android:nextFocus")) {
            return new LayoutAttribute(setter(attributeToName(attributeName) + "Id", attributeStartValue, false));
        }
        return null;
    }
}
