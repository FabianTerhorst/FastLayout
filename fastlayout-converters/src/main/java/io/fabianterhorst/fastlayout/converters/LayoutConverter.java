package io.fabianterhorst.fastlayout.converters;

import java.util.List;

/**
 * Created by fabianterhorst on 18.05.16.
 */
public class LayoutConverter {

    public LayoutAttribute convert(Object attributeValue, String attributeName) {
        return onConvertLayoutAttributeValue(attributeValue, attributeName);
    }

    public List<LayoutAttribute> finish() {
        return onFinish();
    }

    public LayoutAttribute onConvertLayoutAttributeValue(Object attributeValue, String attributeName) {
        String attribute = String.valueOf(attributeValue);
        LayoutAttribute layoutAttribute = new LayoutAttribute(attributeValue, true);
        if (attribute.startsWith("@+id/") || attribute.startsWith("@id/")) {
            layoutAttribute = new LayoutAttribute("R.id" + attribute.replace("@+id/", ".").replace("@id/", "."), false);
        } else if (attribute.startsWith("@dimen/")) {
            layoutAttribute = new LayoutAttribute("(int) getContext().getResources().getDimension(R.dimen." + attribute.replace("@dimen/", "") + ")", false);
        } else if (attribute.startsWith("@string/")) {
            layoutAttribute = new LayoutAttribute("getContext().getString(R.string." + attribute.replace("@string/", "") + ")", false);
        } else if (attribute.endsWith("dp") && isNumber(attribute.replace("dp", ""))) {
            layoutAttribute = new LayoutAttribute("(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, " + attribute.replace("dp", "") + ", getResources().getDisplayMetrics())", false);
        } /*else if (attribute.startsWith("?attr/") && attributeName != null && attributeName.equals("Background")) {
            layoutAttribute = new LayoutAttribute("LayoutUtils.getAttrDrawable(getContext(), R.attr." + attribute.replace("?attr/", "") + ")", false);
        } else if (attribute.startsWith("?attr/")) {
            layoutAttribute = new LayoutAttribute("LayoutUtils.getAttrInt(getContext(), R.attr." + attribute.replace("?attr/", "") + ")", false);
        } */ else if (attribute.equals("false") || attribute.equals("true")) {
            layoutAttribute = new LayoutAttribute(attribute, false);
        } else if (attribute.endsWith("sp") && isNumber(attribute.replace("sp", ""))) {
            layoutAttribute = new LayoutAttribute("(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, " + attribute.replace("sp", "") + ", Resources.getSystem().getDisplayMetrics())", false);
        } else if (isNumber(attribute)) {
            layoutAttribute = new LayoutAttribute(attributeValue, false);
        }
        return onConvertLayoutAttribute(layoutAttribute.getValue(), attributeName, layoutAttribute.isString());
    }

    public LayoutAttribute onConvertLayoutAttribute(Object attributeValue, String attributeName, boolean isString) {
        /*attributeName = attributeName.split(":")[1];
        String[] split = attributeName.split("_");
        attributeName = "";
        for (String refactor : split) {
            attributeName += capitalize(refactor);
        }*/
        attributeName = attributeToName(attributeName);
        return new LayoutAttribute(setter(attributeName, attributeValue, isString), isString);
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
