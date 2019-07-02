package com.llweiya.ysx.starchef.business.community.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;

public class NearbyShopTagAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public NearbyShopTagAdapter() {
        super(R.layout.item_shop_tag);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.txt_tag_name, item);
    }
}
