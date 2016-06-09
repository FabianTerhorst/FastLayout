package io.fabianterhorst.fastlayout;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.util.HashMap;

public class LayoutUtils {

    private static final HashMap<Float, Integer> pxForDp = new HashMap<>();
    private static final HashMap<Float, Integer> pxForSp = new HashMap<>();

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
        if (Build.VERSION.SDK_INT > 10) {
            LayoutTransition layoutTransition = new LayoutTransition();
            if (Build.VERSION.SDK_INT >= 17) {
                layoutTransition.disableTransitionType(LayoutTransition.DISAPPEARING);
            }
            return layoutTransition;
        }
        return null;
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(float dp, Context context) {
        if (pxForDp.containsKey(dp)) {
            return pxForDp.get(dp);
        }
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int)(dp * (metrics.densityDpi / 160f));
        pxForDp.put(dp, px);
        return px;
    }

    /**
     * This method converts sp unit to equivalent pixels, depending on device scaled density.
     *
     * @param sp      A value in sp (scaled density pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to sp depending on device density
     */
    public static int convertSpToPixel(float sp, Context context) {
        if (pxForSp.containsKey(sp)) {
            return pxForSp.get(sp);
        }
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int)(sp * metrics.scaledDensity);
        pxForSp.put(sp, px);
        return px;
    }
}
