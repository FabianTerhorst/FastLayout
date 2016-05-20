package io.fabianterhorst.fastlayout.converters;

import java.util.List;

import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 18.05.16.
 */
@Converter
public class LayoutConverter {

    public LayoutAttribute convert(Object attributeValue, String attributeName) {
        return onConvertLayoutAttributeValue(attributeValue, attributeName);
    }

    public List<LayoutAttribute> finish() {
        return onFinish();
    }

    public LayoutAttribute onConvertLayoutAttributeValue(Object attributeValue, String attributeName) {
        String attribute = String.valueOf(attributeValue);
        boolean isString = true;

        if ((attribute.startsWith("@") || attribute.startsWith("?")) && attribute.contains("/")) {
            String[] attributeSplit = attribute.split("/");
            String type = attributeSplit[0].replace("@+", "").replace("@", "").replace("?", "");
            attribute = "R." + type + "." + attributeSplit[1];
            isString = false;
        }

        if (attribute.startsWith("R.dimen.")) {
            return onConvertLayoutAttribute(attribute, "(int) getContext().getResources().getDimension(" + attribute + ")", attributeName, false);
        } else if (attribute.startsWith("R.string.")) {
            return onConvertLayoutAttribute(attribute, "getContext().getString(" + attribute + ")", attributeName, false);
        } else if (attribute.startsWith("R.drawable.") || attribute.startsWith("R.mipmap.") || attribute.startsWith("R.attr.")) {
            return onConvertLayoutAttribute(attribute, "LayoutUtils.getAttrDrawable(getContext(), " + attribute + ")", attributeName, false);
        } else if (attribute.endsWith("dp") && isNumber(attribute.replace("dp", ""))) {
            return onConvertLayoutAttribute(attribute, "(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, " + attribute.replace("dp", "") + ", getResources().getDisplayMetrics())", attributeName, false);
        } else if (attribute.equals("false") || attribute.equals("true")) {
            return onConvertLayoutAttribute(attribute, attributeName, false);
        } else if (attribute.endsWith("sp") && isNumber(attribute.replace("sp", ""))) {
            return onConvertLayoutAttribute(attribute, "(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, " + attribute.replace("sp", "") + ", Resources.getSystem().getDisplayMetrics())", attributeName, false);
        } else if (isNumber(attribute)) {
            return onConvertLayoutAttribute(attributeValue, attributeName, false);
        }
        return onConvertLayoutAttribute(attribute, attributeName, isString);
    }

    public LayoutAttribute onConvertLayoutAttribute(Object attributeValue, String attributeName, boolean isString) {
        return onConvertLayoutAttribute(String.valueOf(attributeValue), attributeValue, attributeName, isString);
    }

    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, Object attributeValue, String attributeName, boolean isString) {
        attributeName = attributeToName(attributeName);
        return new LayoutAttribute(setter(attributeName, attributeValue, isString));
    }

    public String attributeToName(String attribute) {
        attribute = attribute.split(":")[1];
        String[] split = attribute.split("_");
        attribute = "";
        for (String refactor : split) {
            attribute += capitalize(refactor);
        }
        return attribute;
    }

    public List<LayoutAttribute> onFinish() {
        return null;
    }

    public String setter(String name, Object value, boolean isString) {
        return "set" + name + (isString ? "(\"" : "(") + value + (isString ? "\")" : ")");
    }

    public String attribute(String name, Object value) {
        return name + " = " + value;
    }

    private boolean isNumber(Object text) {
        try {
            Integer.parseInt(String.valueOf(text));
            return true;
        } catch (NumberFormatException ignore) {
            return false;
        }
    }

    private static String capitalize(String name) {
        if (name != null && name.length() != 0) {
            char[] chars = name.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        } else {
            return name;
        }
    }
}
