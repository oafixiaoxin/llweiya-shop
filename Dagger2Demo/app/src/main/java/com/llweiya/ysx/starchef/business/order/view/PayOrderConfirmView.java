package com.llweiya.ysx.starchef.business.order.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.bartoszlipinski.viewpropertyobjectanimator.ViewPropertyObjectAnimator;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.common.utils.UIUtil;

public class PayOrderConfirmView extends LinearLayout {
    private int parentLayoutHeight = UIUtil.dip2pixel(getContext(), 495);

    private LinearLayout llayoutRoot;
    private LinearLayout llayoutMain;
    private View viewCover;

    public PayOrderConfirmView(Context context) {
        this(context, null, 0);
    }

    public PayOrderConfirmView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayOrderConfirmView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_pay_order_detail, this);

        llayoutRoot = findViewById(R.id.llayout_root);
        llayoutMain = findViewById(R.id.llayout_main);
        viewCover = findViewById(R.id.view_cover);
        viewCover.setAlpha(0.3f);

        initListener();
    }

    private void initListener() {
        viewCover.setOnClickListener(v -> {
            hideLayout();
        });
    }

    public boolean isShow() {
        return llayoutMain.getHeight() > 0;
    }

    public void showLayout() {
        if (!isShow()) {
            ObjectAnimator layoutAnimator = ViewPropertyObjectAnimator
                    .animate(llayoutMain)
                    .height(parentLayoutHeight)
                    .get();
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(layoutAnimator);
            animatorSet.setDuration(200);
            animatorSet.start();

            viewCover.setVisibility(VISIBLE);
//            this.requestFocus();
        }
    }

    public void hideLayout() {
        if (isShow()) {
            ObjectAnimator layoutAnimator = ViewPropertyObjectAnimator
                    .animate(llayoutMain)
                    .height(0)
                    .get();
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(layoutAnimator);
            animatorSet.setDuration(200);
            animatorSet.start();

            viewCover.setVisibility(GONE);
        }
    }

}