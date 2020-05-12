package com.llweiya.ysx.starchef.common.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.llweiya.ysx.starchef.R;

/**
 * Created by ysx on 2018/9/6.
 */

public class CustomToast {

    private WindowManager.LayoutParams mParams;
    private WindowManager mWindowManager;
    private View mView;
    private int mDuration;
    private Handler mHandler;

//    private final int mType;
    private static final int TIME_LONG = 3500;
    private static final int TIME_SHORT = 2000;

    private CustomToast(@NonNull Context context, @NonNull String text, int duration) {
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int layoutId = R.layout.toast_custom;

        mView = LayoutInflater.from(context).inflate(layoutId, null);
        ((TextView) mView.findViewById(R.id.text)).setText(text);
        mDuration = (duration == Toast.LENGTH_LONG ? TIME_LONG : TIME_SHORT);
        mParams = new WindowManager.LayoutParams();
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.format = PixelFormat.TRANSLUCENT;
        mParams.windowAnimations = android.R.style.Animation_Toast;
        mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
        mParams.setTitle("Toast");
        mParams.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
                | WindowManager.LayoutParams.FLAG_TOUCHABLE_WHEN_WAKING;
        mHandler = new Handler();
    }

    public static CustomToast makeText(@NonNull Context context, @NonNull String text, int duration) {
        return new CustomToast(context, text, duration);
    }

    public CustomToast setDuration(int duration) {
        if (duration == Toast.LENGTH_SHORT) {
            mDuration = TIME_SHORT;
        } else if (duration == Toast.LENGTH_LONG) {
            mDuration = TIME_LONG;
        } else {
            mDuration = duration;
        }
        return this;
    }

    public CustomToast setIcon(int resId) {
        ImageView imageView = mView.findViewById(R.id.icon);
        imageView.setBackgroundResource(resId);
        imageView.setVisibility(View.VISIBLE);
        return this;
    }

    public CustomToast setAnimations(int animations) {
        mParams.windowAnimations = animations;
        return this;
    }

    public CustomToast setColor(int colorRes) {
        GradientDrawable drawable = (GradientDrawable) mView.getBackground();
        drawable.setColor(mView.getContext().getResources().getColor(colorRes));
        return this;
    }

    public CustomToast setBackground(Drawable drawable) {
        mView.setBackground(drawable);
        return this;
    }

    public CustomToast setGravity(int gravity, int xOffset, int yOffset) {
        final Configuration config = mView.getContext().getResources().getConfiguration();
        final int g = Gravity.getAbsoluteGravity(gravity, config.getLayoutDirection());
        mParams.gravity = g;
        if ((g & Gravity.HORIZONTAL_GRAVITY_MASK) == Gravity.FILL_HORIZONTAL) {
            mParams.horizontalWeight = 1.0f;
        }
        if ((g & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.FILL_VERTICAL) {
            mParams.verticalWeight = 1.0f;
        }
        mParams.x = xOffset;
        mParams.y = yOffset;
        return this;
    }

    public CustomToast setMargin(float horizontalMargin, float verticalMargin) {
        mParams.verticalMargin = verticalMargin;
        mParams.horizontalMargin = horizontalMargin;
        return this;
    }

    public CustomToast setText(int resId) {
        ((TextView) mView.findViewById(R.id.text)).setText(resId);
        return this;
    }

    public CustomToast setText(@NonNull CharSequence charSequence) {
        ((TextView) mView.findViewById(R.id.text)).setText(charSequence);
        return this;
    }

    public void show() {
        if (mView.getParent() != null) {
            mWindowManager.removeView(mView);
        }
        mWindowManager.addView(mView, mParams);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cancel();
            }
        }, mDuration);
    }

    public void cancel() {
        mWindowManager.removeView(mView);
        mParams = null;
        mWindowManager = null;
        mView = null;
        mHandler = null;
    }

    public void showWarning() {
        setIcon(R.drawable.ic_error);
        show();
    }

    public void showError() {
        setIcon(R.drawable.ic_clear);
        show();
    }

}
