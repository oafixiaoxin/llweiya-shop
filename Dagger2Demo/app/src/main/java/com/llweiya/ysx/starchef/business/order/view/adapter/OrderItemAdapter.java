package com.llweiya.ysx.starchef.business.order.view.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.order.model.FoodItemModel;
import com.llweiya.ysx.starchef.business.order.model.OrderEnum;
import com.llweiya.ysx.starchef.business.order.model.OrderItemViewModel;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class OrderItemAdapter extends BaseQuickAdapter<OrderItemViewModel, BaseViewHolder> {
    private OrderGoodsItemAdapter goodsItemAdapter;

    public OrderItemAdapter() {
        super(R.layout.item_order);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderItemViewModel item) {
        helper.setText(R.id.txt_item_name, item.itemName);
        helper.setText(R.id.txt_item_price, item.totalPrice);

        if (item.itemStatus.equals(OrderEnum.ORDER_STATUS_FINISH)) {
            helper.setText(R.id.txt_item_status, "订单完成");
        } else if (item.itemStatus.equals(OrderEnum.ORDER_STATUS_WAIT_COMMENT)) {
            helper.setText(R.id.txt_item_status, "待评价");
        } else if (item.itemStatus.equals(OrderEnum.ORDER_STATUS_WAIT_PAY)) {
            helper.setText(R.id.txt_item_status, "待支付");
        } else if (item.itemStatus.equals(OrderEnum.ORDER_STATUS_PREPARE)) {
            helper.setText(R.id.txt_item_status, "订单准备中");
        }

        List<FoodItemModel> tmpList = new ArrayList<>();
        if (item.goodsList.size() > 0) {
            helper.setGone(R.id.recycler_view, true);

            if (item.goodsList.size() > 2) {
                helper.setGone(R.id.txt_dot, true);
                helper.setGone(R.id.txt_check, true);
                helper.setText(R.id.txt_check, "查看剩余" + (item.goodsList.size() - 2) + "件商品");
                tmpList = item.goodsList.subList(0, 2);

                helper.getView(R.id.txt_check).setOnClickListener(v -> {

                });
            } else {
                helper.setGone(R.id.txt_dot, false);
                helper.setGone(R.id.txt_check, false);
                tmpList = item.goodsList;
            }

            initGoodsItemAdapter(helper, tmpList);
        } else {
            helper.setGone(R.id.recycler_view, false);
        }
    }

    private void initGoodsItemAdapter(BaseViewHolder helper, List<FoodItemModel> list) {
        RecyclerView recyclerView = helper.getView(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        goodsItemAdapter = new OrderGoodsItemAdapter();
        recyclerView.setAdapter(goodsItemAdapter);
        goodsItemAdapter.setNewData(list);
    }
}