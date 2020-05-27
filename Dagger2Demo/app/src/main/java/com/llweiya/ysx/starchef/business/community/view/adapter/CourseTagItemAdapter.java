package com.llweiya.ysx.starchef.business.community.view.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.common.utils.UIUtil;

public class CourseTagItemAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public CourseTagItemAdapter() {
        super(R.layout.item_course_tag);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        TextView text = helper.getView(R.id.text);
        helper.setText(R.id.text, item);
        ViewGroup.LayoutParams lp = text.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams)lp;
            flexboxLp.setFlexGrow(1.0f);
            flexboxLp.setAlignSelf(AlignSelf.FLEX_END);
        }
        RecyclerView.LayoutParams rootLp = (RecyclerView.LayoutParams)helper.getView(R.id.root).getLayoutParams();
        rootLp.leftMargin = UIUtil.dip2pixel(mContext, helper.getAdapterPosition() > 0 ? 10 : 0);
        helper.getView(R.id.root).setLayoutParams(rootLp);
    }
}
