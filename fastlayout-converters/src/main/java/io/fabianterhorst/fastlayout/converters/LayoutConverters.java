package io.fabianterhorst.fastlayout.converters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fabianterhorst on 18.05.16.
 */
public class LayoutConverters {

    private List<LayoutConverter> converters;

    public LayoutConverters() {
    }

    public void setAll(List<LayoutConverter> converters) {
        this.converters = converters;
    }

    public LayoutAttribute convert(Object attributeValue, String attributeName, List<LayoutAttribute> attributes) {
        LayoutAttribute layoutAttribute = null;
        for (LayoutConverter layoutConverter : converters) {
            if (layoutAttribute == null) {
                layoutAttribute = layoutConverter.convert(attributeValue, attributeName);
                if (layoutAttribute != null && (layoutAttribute.getType() == LayoutAttribute.Type.ASSIGNED)) {
                    break;
                } else if (layoutAttribute != null && checkIfConstructorTypeIsAlreadyDefined(layoutAttribute.getType(), attributes)) {
                    return new LayoutAttribute();
                }
            }
        }
        return layoutAttribute;
    }

    private boolean checkIfConstructorTypeIsAlreadyDefined(LayoutAttribute.Type type, List<LayoutAttribute> attributes) {
        if (type == LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_3
                || type == LayoutAttribute.Type.PARAM_CONSTRUCTOR_1
                || type == LayoutAttribute.Type.PARAM_CONSTRUCTOR_2
                || type == LayoutAttribute.Type.PARAM_CONSTRUCTOR_3) {
            checkAttributesForType(type, attributes);
        }
        return false;
    }

    private boolean checkAttributesForType(LayoutAttribute.Type type, List<LayoutAttribute> attributes) {
        for (LayoutAttribute attribute : attributes) {
            if (attribute.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public List<LayoutAttribute> finish(List<LayoutAttribute> attributes) {
        List<LayoutAttribute> finished = new ArrayList<>();
        for (LayoutConverter layoutConverter : converters) {
            List<LayoutAttribute> currentlyFinished = layoutConverter.finish();
            if (currentlyFinished != null) {
                finished.addAll(currentlyFinished);
            }
        }
        if (!checkAttributesForType(LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_2, attributes)) {
            finished.add(new LayoutAttribute(LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_2, "null"));
        }
        if (!checkAttributesForType(LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_3, attributes)) {
            finished.add(new LayoutAttribute(LayoutAttribute.Type.LAYOUT_CONSTRUCTOR_3, "0"));
        }
        return finished;
    }
}
