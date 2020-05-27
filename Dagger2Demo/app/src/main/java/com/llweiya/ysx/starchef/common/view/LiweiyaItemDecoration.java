package com.llweiya.ysx.starchef.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.llweiya.ysx.starchef.common.utils.UIUtil;

public class LiweiyaItemDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    private int deviderSpace = -1;

    private int padding = -99;

    private Boolean headerDecorationVisible = false;


    public LiweiyaItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);//系统属性中获取
        a.recycle();

        mOrientation = VERTICAL_LIST;
        deviderSpace = mDivider.getIntrinsicHeight();
    }

    public LiweiyaItemDecoration(Drawable divider, int orientation, int space, int padding) {
        this.deviderSpace = space;
        this.mDivider = divider;
        this.padding = padding;
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }
    }

    private boolean hasCustomPadding() {
        return padding != -99;
    }

    /**
     * 绘制纵向列表时的分隔线  这时分隔线是横着的
     * 每次 left相同，top根据child变化，right相同，bottom也变化
     *
     * @param c
     * @param parent
     */
    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = hasCustomPadding() ? padding : parent.getPaddingLeft();
        final int right = parent.getWidth() - (hasCustomPadding() ? padding : parent
                .getPaddingRight());

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            if (parent.getAdapter() instanceof BaseQuickAdapter) {
                // Footer的上下不需要分隔线
                if (parent.getAdapter().getItemViewType(i) == BaseQuickAdapter.FOOTER_VIEW) {
                    continue;
                }
                if (i + 1 < childCount
                        && parent.getAdapter().getItemViewType(i + 1) == BaseQuickAdapter
                        .FOOTER_VIEW) {
                    continue;
                }
                // Header 不需要分隔线
                if (parent.getAdapter().getItemViewType(i) == BaseQuickAdapter.HEADER_VIEW && !headerDecorationVisible) {
                    continue;
                }
            }

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + deviderSpace;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    /**
     * 绘制横向列表时的分隔线  这时分隔线是竖着的
     * l、r 变化； t、b 不变
     *
     * @param c
     * @param parent
     */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = hasCustomPadding() ? padding : parent.getPaddingTop();
        final int bottom = parent.getHeight() - (hasCustomPadding() ? padding : parent
                .getPaddingBottom());

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + deviderSpace;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, deviderSpace);
        } else {
            outRect.set(0, 0, deviderSpace, 0);
        }
    }

    public static class Builder {
        private Drawable divider;
        private int orientation = LinearLayoutManager.VERTICAL;
        private int deviderSpace = -1;
        private Context context;
        private int padding = -99;
        private Boolean headerDecorationVisible = false;

        public Builder setDivider(Drawable divider) {
            this.divider = divider;
            return this;
        }

        public Builder setOrientation(int orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder setDeviderSpace(int deviderSpace) {
            this.deviderSpace = deviderSpace;
            return this;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setColor(@ColorInt int color) {
            this.divider = new ColorDrawable(color);
            return this;
        }

        public Builder setPadding(int padding) {
            this.padding = padding;
            return this;
        }

        public Builder setHeaderDecorationVisible(Boolean headerDecorationVisible) {
            this.headerDecorationVisible = headerDecorationVisible;
            return this;
        }

        public LiweiyaItemDecoration build() {
            if (context == null) {
                throw new IllegalStateException("Context required.");
            }

            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }

            if (divider == null) {
                final TypedArray a = context.obtainStyledAttributes(ATTRS);
                divider = a.getDrawable(0);//系统属性中获取
                a.recycle();
            }

            if (deviderSpace < 0) {
                deviderSpace = UIUtil.dip2pixel(context, 1);
            }

            LiweiyaItemDecoration itemDecoration = new LiweiyaItemDecoration(divider, orientation, deviderSpace, padding);
            itemDecoration.headerDecorationVisible = this.headerDecorationVisible;

            return itemDecoration;
        }
    }
}
