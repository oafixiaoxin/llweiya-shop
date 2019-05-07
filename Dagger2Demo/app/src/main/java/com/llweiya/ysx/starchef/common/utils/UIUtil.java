package com.llweiya.ysx.starchef.common.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import com.llweiya.ysx.starchef.R;

public class UIUtil {

    //图太难看，方法OK
    public static Drawable actionBarBackArrow(Context context) {
        final Drawable upArrow = context.getResources().getDrawable(R.drawable.ic_actionbar_back);
        upArrow.setColorFilter(context.getResources().getColor(R.color.llweiya_text_color_black), PorterDuff.Mode.SRC_ATOP);
        return upArrow;
    }

    /**
     * 将dip转换为px
     *
     * @param context 上下文
     * @param dp      dp值
     * @return px值
     */
    public static int dip2pixel(Context context, float dp) {
        return Math.round(context.getResources().getDisplayMetrics().density * dp);
    }

}
