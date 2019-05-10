package com.llweiya.ysx.starchef.business.order.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.order.model.FoodItemModel;

/**
 * 支付页面，item
 */
public class PayOrderItemAdapter extends BaseQuickAdapter<FoodItemModel, BaseViewHolder> {
    public PayOrderItemAdapter() {
        super(R.layout.item_pay_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, FoodItemModel item) {
        helper.setText(R.id.txt_item_name, item.goodsName);
        helper.setText(R.id.txt_item_price, String.valueOf(item.goodsPrice));
        helper.setText(R.id.txt_item_number, "x" + item.goodsNum);
    }
}
