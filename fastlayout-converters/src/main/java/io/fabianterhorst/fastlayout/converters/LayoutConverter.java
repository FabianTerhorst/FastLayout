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
        LayoutAttribute layoutAttribute = new LayoutAttribute(attributeValue);
        if (attribute.startsWith("@+id/") || attribute.startsWith("@id/")) {
            return onConvertLayoutAttribute("R.id" + attribute.replace("@+id/", ".").replace("@id/", "."), attributeName, false);
        } else if (attribute.startsWith("@dimen/")) {
            return onConvertLayoutAttribute("(int) getContext().getResources().getDimension(R.dimen." + attribute.replace("@dimen/", "") + ")", attributeName, false);
        } else if (attribute.startsWith("@string/")) {
            return onConvertLayoutAttribute("getContext().getString(R.string." + attribute.replace("@string/", "") + ")", attributeName, false);
        } else if (attribute.endsWith("dp") && isNumber(attribute.replace("dp", ""))) {
            return onConvertLayoutAttribute("(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, " + attribute.replace("dp", "") + ", getResources().getDisplayMetrics())", attributeName, false);
        } /*else if (attribute.startsWith("?attr/") && attributeName != null && attributeName.equals("Background")) {
            layoutAttribute = new LayoutAttribute("LayoutUtils.getAttrDrawable(getContext(), R.attr." + attribute.replace("?attr/", "") + ")", false);
        } else if (attribute.startsWith("?attr/")) {
            layoutAttribute = new LayoutAttribute("LayoutUtils.getAttrInt(getContext(), R.attr." + attribute.replace("?attr/", "") + ")", false);
        } */ else if (attribute.equals("false") || attribute.equals("true")) {
            return onConvertLayoutAttribute(attribute, attributeName, false);
        } else if (attribute.endsWith("sp") && isNumber(attribute.replace("sp", ""))) {
            return onConvertLayoutAttribute("(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, " + attribute.replace("sp", "") + ", Resources.getSystem().getDisplayMetrics())", attributeName, false);
        } else if (isNumber(attribute)) {
            return onConvertLayoutAttribute(attributeValue, attributeName, false);
        }
        return onConvertLayoutAttribute(layoutAttribute.getValue(), attributeName, true);
    }

    public LayoutAttribute onConvertLayoutAttribute(Object attributeValue, String attributeName, boolean isString) {
        /*attributeName = attributeName.split(":")[1];
        String[] split = attributeName.split("_");
        attributeName = "";
        for (String refactor : split) {
            attributeName += capitalize(refactor);
        }*/
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
