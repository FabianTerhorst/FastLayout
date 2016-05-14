package ${package};

import android.content.Context;
import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

import io.fabianterhorst.fastlayout.ILayout;

public class LayoutCache {

    private static LayoutCache mInstance;

    private final HashMap<String, ILayout> mLayouts;

    private final Context mContext;

    <#list layouts?keys as key>
    public static final String ${key} = "${layouts[key].name}";

    </#list>
    @StringDef({<#list layouts?keys as key>${key},</#list>})
    @Retention(RetentionPolicy.SOURCE)
    public @interface LayoutName {
    }

    public LayoutCache(Context context) {
        mLayouts = new HashMap<>();
        mContext = context;
    }

    public static LayoutCache getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LayoutCache(context);
        }
        return mInstance;
    }

    public <T> T getLayout(@LayoutName String name) {
        if (mLayouts.containsKey(name)) {
            return (T) mLayouts.get(name).clone();
        }
        ILayout layout = null;
        switch (name) {
        <#list layouts?keys as key>
            case ${key}:
                layout = new ${layouts[key].name}(mContext);
                break;
        </#list>
        }
        mLayouts.put(name, layout.clone());
        return (T) layout;
    }
}