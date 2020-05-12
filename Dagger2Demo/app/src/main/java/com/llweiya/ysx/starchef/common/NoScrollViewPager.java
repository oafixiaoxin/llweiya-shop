package com.llweiya.ysx.starchef.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.llweiya.ysx.starchef.R;

public class NoScrollViewPager extends ViewPager {
    private Boolean isScrollable;

    public NoScrollViewPager(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public NoScrollViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NoScrollViewPager);
        isScrollable = typedArray.getBoolean(R.styleable.NoScrollViewPager_scrollable, true);
        typedArray.recycle();
    }
}
