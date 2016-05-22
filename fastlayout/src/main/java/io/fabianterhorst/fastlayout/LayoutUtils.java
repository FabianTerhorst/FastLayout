package io.fabianterhorst.fastlayout;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;

public class LayoutUtils {

    public static int getAttrInt(Context context, int attr) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attr, typedValue, true);
        return typedValue.data;
    }

    public static Drawable getAttrDrawable(Context context, int attr) {
        TypedArray ta = context.getTheme().obtainStyledAttributes(new int[]{attr});
        Drawable drawable = ta.getDrawable(0);
        ta.recycle();
        return drawable;
    }

    public static LayoutTransition getDisabledLayoutTransition() {
        if(Build.VERSION.SDK_INT > 10) {
            LayoutTransition layoutTransition = new LayoutTransition();
            if (Build.VERSION.SDK_INT >= 17) {
                layoutTransition.disableTransitionType(LayoutTransition.DISAPPEARING);
            }
            return layoutTransition;
        }
        return null;
    }
}
