package io.fabianterhorst.fastlayout.sample;

import io.fabianterhorst.fastlayout.converters.LayoutConverter;
import io.fabianterhorst.fastlayout.converters.LayoutAttribute;
import io.fabianterhorst.fastlayout.annotations.Converter;

/**
 * Created by fabianterhorst on 20.05.16.
 */
@Converter(converter = MyLayoutConverter.class)
public class MyLayoutConverter extends LayoutConverter {

    public MyLayoutConverter() {

    }

    @Override
    public LayoutAttribute onConvertLayoutAttribute(String attributeValue, String attributeName, boolean isString) {
        /*switch (attributeName) {
            case "android:layout_marginLeft":
                return new LayoutAttribute(LayoutAttribute.Type.PARAM, setter("", "", false), attributeValue, false);
        }*/
        return null;
    }
}
