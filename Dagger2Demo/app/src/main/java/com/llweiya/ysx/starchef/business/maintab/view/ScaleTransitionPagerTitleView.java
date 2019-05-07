package com.llweiya.ysx.starchef.business.maintab.view;

import android.content.Context;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
    private float mMinScale = 0.75f;

    private OnItemClickListener onItemClickListener;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);    // 实现颜色渐变
        setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
        setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);

        //会连续触发两次，bug
        if (null != onItemClickListener) {
            onItemClickListener.onTextClick(index);
        }
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);    // 实现颜色渐变
        setScaleX(1.0f + (mMinScale - 1.0f) * leavePercent);
        setScaleY(1.0f + (mMinScale - 1.0f) * leavePercent);
    }

    public interface OnItemClickListener {
        void onTextClick(int index);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public float getMinScale() {
        return mMinScale;
    }

    public void setMinScale(float minScale) {
        mMinScale = minScale;
    }
}
