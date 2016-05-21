package io.fabianterhorst.fastlayout.converters;

import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 21.05.16.
 */
@Converter
public class LinearLayoutConverter extends LayoutConverter {

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:orientation":
                return super.onConvertLayoutAttribute(String.valueOf(attributeValue), "LinearLayout." + String.valueOf(attributeValue).toUpperCase(), attributeName, false);
            case "android:divider":
                return new LayoutAttribute(setter("DividerDrawable", attributeValue, false));
            case "android:showDividers":
                return new LayoutAttribute(setter("ShowDividers", "LinearLayout.SHOW_DIVIDER_" + String.valueOf(attributeValue).toUpperCase(), false));
        }
        return null;
    }
}
