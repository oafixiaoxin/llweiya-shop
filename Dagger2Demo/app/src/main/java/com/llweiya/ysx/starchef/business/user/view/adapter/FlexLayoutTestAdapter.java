package com.llweiya.ysx.starchef.business.user.view.adapter;

import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.AlignSelf;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.model.CardStackItemViewModel;

/**
 * Created by ysx on 2018/10/31.
 */

public class FlexLayoutTestAdapter extends BaseQuickAdapter<CardStackItemViewModel, BaseViewHolder> {
    public FlexLayoutTestAdapter() {
        super(R.layout.item_flex_layout_test);
    }

    @Override
    protected void convert(BaseViewHolder helper, CardStackItemViewModel item) {
        helper.setText(R.id.text, item.name);
        TextView text = (TextView)helper.getView(R.id.text);
        ViewGroup.LayoutParams lp = text.getLayoutParams();
        if (lp instanceof FlexboxLayoutManager.LayoutParams) {
            FlexboxLayoutManager.LayoutParams flexboxLp = (FlexboxLayoutManager.LayoutParams)lp;
            flexboxLp.setFlexGrow(1.0f);
            flexboxLp.setAlignSelf(AlignSelf.FLEX_END);
        }
    }
}
