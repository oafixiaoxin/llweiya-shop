package com.llweiya.ysx.starchef.common.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LlweiyaGridItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int specialSpace;
    private int spanCount = 3; // 列

    public LlweiyaGridItemDecoration(int spanCount, int space, int specialSpace) {
        this.space = space;
        this.specialSpace = specialSpace;
        this.spanCount = spanCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int mEachSpace = (specialSpace * 2 + (spanCount - 1) * space) / spanCount;//每个item的左右间距
        int diff = ((mEachSpace - specialSpace) - specialSpace) / (spanCount - 1);
        int column = position % spanCount;  // 列数 从0开始计数
        int left = (column + 1 - 1) * diff + specialSpace; // left = (column-1)*diff+A1
        int right = mEachSpace - left;

        outRect.left = left;
        outRect.right = right;
    }
}
