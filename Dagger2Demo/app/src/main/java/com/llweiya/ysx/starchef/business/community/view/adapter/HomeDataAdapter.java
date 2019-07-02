package com.llweiya.ysx.starchef.business.community.view.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.model.HomeAddsAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.HomeCategoryAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.HomeDiscountAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.HomeNearbyShopAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.IHomeDataType;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.llweiya.ysx.starchef.common.utils.Utility;
import com.llweiya.ysx.starchef.common.view.adapter.SimplePictureContentVerticalAdapter;

import java.util.ArrayList;

public class HomeDataAdapter extends BaseMultiItemQuickAdapter<IHomeDataType, BaseViewHolder> {
    public void globalClickAction(View view, int position, IHomeDataType item) {
        if (view.getId() == R.id.llayout_check_more) {

        }
    }

    public HomeDataAdapter() {
        super(new ArrayList<>());
        addItemType(IHomeDataType.HOMT_DATA_TYPE_DISCOUNT_AREA, R.layout.item_discount_area);
        addItemType(IHomeDataType.HOMT_DATA_TYPE_CATEGORY, R.layout.item_category_area);
        addItemType(IHomeDataType.HOMT_DATA_TYPE_ADS, R.layout.item_ads_area);
        addItemType(IHomeDataType.HOMT_DATA_TYPE_NEARBY_SHOP, R.layout.item_nearby_shop);
    }

    @Override
    protected void convert(BaseViewHolder helper, IHomeDataType item) {
        switch (helper.getItemViewType()) {
            case IHomeDataType.HOMT_DATA_TYPE_DISCOUNT_AREA:
                setItemDiscountArea(helper, item);
                break;
            case IHomeDataType.HOMT_DATA_TYPE_CATEGORY:
                setItemCategoryArea(helper, item);
                break;
            case IHomeDataType.HOMT_DATA_TYPE_ADS:
                setItemAdds(helper, item);
                break;
            case IHomeDataType.HOMT_DATA_TYPE_NEARBY_SHOP:
                setItemNearbyShop(helper, item);
                break;
        }
    }

    private void setItemDiscountArea(BaseViewHolder helper, IHomeDataType item) {
        HomeDiscountAreaViewModel areaViewModel = (HomeDiscountAreaViewModel)item;
        if (Utility.listHasElement(areaViewModel.getDiscountAreaList())) {
            ImageUtil.showImage(mContext, helper.getView(R.id.image_discount_area_1), areaViewModel.getDiscountAreaList().get(0).pictureUrl);
            if (areaViewModel.getDiscountAreaList().size() > 1) {
                ImageUtil.showImage(mContext, helper.getView(R.id.image_discount_area_2), areaViewModel.getDiscountAreaList().get(1).pictureUrl);
            }
            if (areaViewModel.getDiscountAreaList().size() > 2) {
                ImageUtil.showImage(mContext, helper.getView(R.id.image_discount_area_3), areaViewModel.getDiscountAreaList().get(2).pictureUrl);
            }
        }
        helper.addOnClickListener(R.id.llayout_check_more);
    }

    private void setItemCategoryArea(BaseViewHolder helper, IHomeDataType item) {
        HomeCategoryAreaViewModel categoryAreaViewModel = (HomeCategoryAreaViewModel)item;
        if (Utility.listHasElement(categoryAreaViewModel.getCatagoryList())) {
            RecyclerView recyclerView = helper.getView(R.id.recycler_category);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            SimplePictureContentVerticalAdapter verticalAdapter = new SimplePictureContentVerticalAdapter();
            verticalAdapter.bindToRecyclerView(recyclerView);
            verticalAdapter.setNewData(categoryAreaViewModel.getCatagoryList());
            recyclerView.setAdapter(verticalAdapter);
            verticalAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                }
            });
        }
    }

    private void setItemAdds(BaseViewHolder helper, IHomeDataType item) {
        HomeAddsAreaViewModel homeAddsAreaViewModel = (HomeAddsAreaViewModel)item;
        ImageUtil.showImage(mContext, helper.getView(R.id.image_ads), homeAddsAreaViewModel.addsUrl);
    }

    private void setItemNearbyShop(BaseViewHolder helper, IHomeDataType item) {
        HomeNearbyShopAreaViewModel nearbyShopAreaViewModel = (HomeNearbyShopAreaViewModel)item;
        RecyclerView recyclerView = (RecyclerView)helper.getView(R.id.recycler_nearby_shop);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        HomeNearbyShopAdapter shopAdapter = new HomeNearbyShopAdapter();
        recyclerView.setAdapter(shopAdapter);
        shopAdapter.setNewData(nearbyShopAreaViewModel.shopList);
    }
}
