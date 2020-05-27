package com.llweiya.ysx.starchef.common.view.customview;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

public class DrawableUtil {

    public static Drawable getSelector(int normalColor, int selectedColor, int disabledColor, int radiu, float[] radius) {
        Drawable drawable = createShape(normalColor, radiu, radius, disabledColor > 0);
        Drawable selectedDrawable = null;
        if (selectedColor != 0) {
            selectedDrawable = createShape(selectedColor, radiu, radius, disabledColor > 0);
        }
        Drawable disabledDrawable = null;
        if (disabledColor != 0) {
            disabledDrawable = createShape(disabledColor, radiu, radius, disabledColor > 0);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        if (null != selectedDrawable) {
            stateListDrawable.addState(new int[]{android.R.attr.state_selected}, selectedDrawable);
        }
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled}, drawable);
        if (null != disabledDrawable) {
            stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, disabledDrawable);
        }
        return stateListDrawable;
    }

    private static GradientDrawable createShape(int color, int radiu, float[] radius, boolean isDisabled) {
        GradientDrawable drawable = new GradientDrawable();
        if (radiu > 0) {
            drawable.setCornerRadius(radiu);
        } else {
            drawable.setCornerRadii(radius);
        }
        if (isDisabled) {
            drawable.setStroke(1, color);
        }
        drawable.setColor(color);
        return drawable;
    }

}
