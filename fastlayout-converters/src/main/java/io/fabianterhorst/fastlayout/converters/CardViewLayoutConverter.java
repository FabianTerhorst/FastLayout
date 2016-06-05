package io.fabianterhorst.fastlayout.converters;

import io.fabianterhorst.fastlayout.annotations.Converter;

@Converter
public class CardViewLayoutConverter extends LayoutConverter {
    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeStartValue, String attributeValue, String attributeName, boolean isString) {
        switch (attributeName) {
            case "app:cardCornerRadius":
                return new LayoutAttribute(setter("Radius", attributeValue, false));
            case "app:cardMaxElevation":
                return new LayoutAttribute(setter("MaxCardElevation", attributeValue, false));
            case "app:cardUseCompatPadding":
                return new LayoutAttribute(setter("UseCompatPadding", attributeValue, false));
            case "app:cardPreventCornerOverlap":
                return new LayoutAttribute(setter("PreventCornerOverlap", attributeValue, false));
        }
        return null;
    }
}
