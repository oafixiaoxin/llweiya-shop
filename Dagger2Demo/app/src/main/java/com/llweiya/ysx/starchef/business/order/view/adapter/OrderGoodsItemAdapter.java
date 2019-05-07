package com.llweiya.ysx.starchef.business.order.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.order.model.FoodItemModel;

public class OrderGoodsItemAdapter extends BaseQuickAdapter<FoodItemModel, BaseViewHolder> {
    public OrderGoodsItemAdapter() {
        super(R.layout.item_order_goods);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodItemModel item) {
        helper.setText(R.id.txt_goods_name, item.goodsName);
        helper.setText(R.id.txt_goods_num, "x" + item.goodsNum);
    }
}
