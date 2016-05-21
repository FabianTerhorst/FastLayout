package io.fabianterhorst.fastlayout.converters;

/**
 * Created by fabianterhorst on 21.05.16.
 */

public class LinearLayoutConverter extends LayoutConverter {

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "android:divider":
                return new LayoutAttribute(setter("DividerDrawable", attributeValue, false));
            case "android:showDividers":
                return new LayoutAttribute(setter("ShowDividers", "LinearLayout.SHOW_DIVIDER_" + String.valueOf(attributeValue).toUpperCase(), false));
        }
        return null;
    }
}
