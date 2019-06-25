package com.llweiya.ysx.starchef.common.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.common.model.SimpleObjectViewModel;

public class SimplePictureContentVerticalAdapter extends BaseQuickAdapter<SimpleObjectViewModel, BaseViewHolder> {
    public SimplePictureContentVerticalAdapter() {
        super(R.layout.item_simple_picture_content_vertical);
    }
    @Override
    protected void convert(BaseViewHolder helper, SimpleObjectViewModel item) {
        helper.setText(R.id.text, item.targetName);
        helper.setImageResource(R.id.image, item.targetPictureResourceId);
    }
}
