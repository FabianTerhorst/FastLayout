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

    public LayoutAttribute convert(Object attributeValue, String attributeName) {
        LayoutAttribute layoutAttribute = null;
        for (LayoutConverter layoutConverter : converters) {
            if (layoutAttribute == null) {
                layoutAttribute = layoutConverter.convert(attributeValue, attributeName);
                if(layoutAttribute != null && layoutAttribute.getType() == LayoutAttribute.Type.ASSIGNED) {
                    break;
                }
            }
        }
        return layoutAttribute;
    }

    public List<LayoutAttribute> finish() {
        List<LayoutAttribute> finished = new ArrayList<>();
        for (LayoutConverter layoutConverter : converters) {
            List<LayoutAttribute> currentlyFinished = layoutConverter.finish();
            if(currentlyFinished != null) {
                finished.addAll(currentlyFinished);
            }
        }
        return finished;
    }
}
