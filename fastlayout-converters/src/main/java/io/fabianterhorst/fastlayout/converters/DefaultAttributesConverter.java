package io.fabianterhorst.fastlayout.converters;

import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 18.05.16.
 */
@Converter
public class DefaultAttributesConverter extends LayoutConverter {

    @Override
    public LayoutAttribute onConvertLayoutAttributeValue(String attributeValue, String attributeName) {
        switch (attributeName) {
            case "android:gravity":
            case "android:foregroundGravity":
                return super.onConvertLayoutAttribute(attributeValue, "Gravity." + attributeValue.toUpperCase(), attributeName, false);
            case "android:layout_gravity":
                return onConvertLayoutAttribute(attributeValue, "Gravity." + attributeValue.toUpperCase(), attributeName, false);
            case "android:backgroundTintMode":
            case "android:foregroundTintMode":
                return super.onConvertLayoutAttribute(attributeValue, "android.graphics.PorterDuff.Mode." + attributeValue.toUpperCase(), attributeName, false);
            case "android:accessibilityLiveRegion":
                return super.onConvertLayoutAttribute(attributeValue, "View.ACCESSIBILITY_LIVE_REGION_" + attributeValue.toUpperCase(), attributeName, false);
            case "android:drawingCacheQuality":
                return super.onConvertLayoutAttribute(attributeValue, "View.DRAWING_CACHE_QUALITY_" + attributeValue.toUpperCase(), attributeName, false);
            //Todo : viewgroup attribute
            case "android:descendantFocusability":
                return super.onConvertLayoutAttribute(attributeValue, "ViewGroup.FOCUS_" + stringToConstant(attributeValue).toUpperCase(), attributeName, false);
            case "android:importantForAccessibility":
                return super.onConvertLayoutAttribute(attributeValue, "View.IMPORTANT_FOR_ACCESSIBILITY_" + stringToConstant(attributeValue).toUpperCase(), attributeName, false);
            case "android:visibility":
                return super.onConvertLayoutAttribute(attributeValue, "View." + attributeValue.toUpperCase(), attributeName, false);
        }
        return super.onConvertLayoutAttributeValue(attributeValue, attributeName);
    }

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, String attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "style":
                return new LayoutAttribute(LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_3, attributeValue);
            case "android:theme":
                return new LayoutAttribute(LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_1, "new android.view.ContextThemeWrapper(" + "getContext(), " + attributeValue + ")");
            case "android:layout_gravity":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM, attribute(attributeName.replace("android:layout_", ""), attributeValue));
            case "android:background":
                if (String.valueOf(attributeStartValue).startsWith("R.")) {
                    return new LayoutAttribute(setter("BackgroundResource", attributeStartValue, false));
                }
                break;
            case "android:textSize":
                if (attributeStartValue.endsWith("sp")) {
                    return new LayoutAttribute(setter("TextSize", "TypedValue.COMPLEX_UNIT_SP," + attributeStartValue.replace("sp", ""), false));
                } else if (attributeStartValue.endsWith("dip") || attributeStartValue.endsWith("dp")) {
                    return new LayoutAttribute(setter("TextSize", "TypedValue.COMPLEX_UNIT_DIP," + attributeStartValue.replace("dip", "").replace("dp", ""), false));
                }
                //Todo : viewgroup attribute
            case "android:animateLayoutChanges":
                if (Boolean.valueOf(attributeValue)) {
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
