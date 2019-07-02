package com.llweiya.ysx.starchef.business.community.view.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.model.NearbyShopModel;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;

public class HomeNearbyShopAdapter extends BaseQuickAdapter<NearbyShopModel, BaseViewHolder> {
    private NearbyShopTagAdapter shopTagAdapter;

    public HomeNearbyShopAdapter() {
        super(R.layout.item_shop_1);
    }

    @Override
    protected void convert(BaseViewHolder helper, NearbyShopModel item) {
        ImageUtil.showImage(mContext, helper.getView(R.id.image_shop), item.shopUrl);
        helper.setText(R.id.txt_shop_name, item.shopName);
        helper.setText(R.id.txt_shop_location, item.location);
        helper.setText(R.id.txt_shop_type, item.shopType);

        RecyclerView recyclerView =  (RecyclerView)helper.getView(R.id.recycler_view_tag);
        shopTagAdapter = new NearbyShopTagAdapter();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(mContext);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(shopTagAdapter);
        shopTagAdapter.setNewData(item.shopTags);
    }
}