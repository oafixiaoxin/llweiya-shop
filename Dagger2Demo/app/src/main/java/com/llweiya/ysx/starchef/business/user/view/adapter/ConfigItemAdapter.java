package com.llweiya.ysx.starchef.business.user.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.model.CommonItemModel;

public class ConfigItemAdapter extends BaseQuickAdapter<CommonItemModel, BaseViewHolder> {
    public ConfigItemAdapter() {
        super(R.layout.item_grid_config);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommonItemModel item) {
        helper.setImageDrawable(R.id.image, item.icon);
        helper.setText(R.id.text, item.title);
    }
}
