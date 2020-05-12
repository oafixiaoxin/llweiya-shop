package com.llweiya.ysx.starchef.common.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class StaggeredItemDecoration extends RecyclerView.ItemDecoration {

    private Context context;
    private int leftPadding = 0;
    private int rightPadding = 0;
    private int bottomPadding = 0;

    private StaggeredItemDecoration(Context context, int leftPadding, int rightPadding, int bottomPadding) {
        this.context = context;
        this.leftPadding = leftPadding;
        this.rightPadding = rightPadding;
        this.bottomPadding = bottomPadding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        // 获取item在span中的下标
        int spanIndex = params.getSpanIndex();
        int leftPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                this.leftPadding, context.getResources().getDisplayMetrics());
        int rightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                this.rightPadding, context.getResources().getDisplayMetrics());
        int bottomPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                this.bottomPadding, context.getResources().getDisplayMetrics());
        outRect.left = leftPadding;
        outRect.right = rightPadding;
        outRect.bottom = bottomPadding;
    }

    public static class Builder{
        private Context context;
        private int leftPadding;
        private int rightPadding;
        private int bottomPadding;

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Builder setLeftPadding(int leftPadding) {
            this.leftPadding = leftPadding;
            return this;
        }

        public Builder setRightPadding(int rightPadding) {
            this.rightPadding = rightPadding;
            return this;
        }

        public Builder setBottomPadding(int bottomPadding) {
            this.bottomPadding = bottomPadding;
            return this;
        }

        public StaggeredItemDecoration build() {
            if (null == context) {
                throw new IllegalArgumentException("context must be set.");
            }
            StaggeredItemDecoration decoration = new StaggeredItemDecoration(context, leftPadding, rightPadding, bottomPadding);
            return decoration;
        }
    }

}
