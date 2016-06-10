package ${package};

import android.content.Context;
import android.support.annotation.StringDef;
import android.support.annotation.NonNull;
import android.view.View;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

import io.fabianterhorst.fastlayout.ILayout;

public class LayoutCache {

    private static LayoutCache mInstance;

    private final HashMap<String, ILayout> mLayouts;

    <#list layouts?keys as key>
    public static final String ${key} = "${layouts[key].name}";

    </#list>
    @StringDef({<#list layouts?keys as key>${key},</#list>})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LayoutName {
    }

    public LayoutCache() {
        mLayouts = new HashMap<>();
    }

    public static LayoutCache getInstance() {
        if (mInstance == null) {
            mInstance = new LayoutCache();
        }
        return mInstance;
    }

    public <T extends View> T getLayout(@NonNull Context context, @LayoutName String name) {
        if (mLayouts.containsKey(name)) {
            return (T) mLayouts.get(name).clone();
        }
        ILayout layout = null;
        switch (name) {
        <#list layouts?keys as key>
            case ${key}:
                layout = new ${layouts[key].name}(context);
                break;
        </#list>
        }
        mLayouts.put(name, layout.clone());
        return (T) layout;
    }
}
