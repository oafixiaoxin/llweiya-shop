package com.llweiya.ysx.starchef.business.dependency.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.dependency.model.VideoSelectItemViewModel;

public class LocalVideoAdapter extends BaseQuickAdapter<VideoSelectItemViewModel, BaseViewHolder> {
    public LocalVideoAdapter() {
        super(R.layout.item_video);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoSelectItemViewModel item) {

    }
}
