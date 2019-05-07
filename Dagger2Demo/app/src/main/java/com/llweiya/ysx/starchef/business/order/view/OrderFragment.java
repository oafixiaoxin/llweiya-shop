package com.llweiya.ysx.starchef.business.order.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.order.model.FoodItemModel;
import com.llweiya.ysx.starchef.business.order.model.OrderItemViewModel;
import com.llweiya.ysx.starchef.business.order.view.adapter.OrderItemAdapter;
import com.llweiya.ysx.starchef.databinding.FragmentOrderBinding;
import com.lpmas.apt.LWRouter;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    private OrderItemAdapter orderItemAdapter;

    private List<OrderItemViewModel> orderList;

    public OrderFragment() {

    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);
        binding = DataBindingUtil.bind(rootView);

        buildOrderItemList();
        initAdapter();

        return rootView;
    }

    private void initAdapter() {
        orderItemAdapter = new OrderItemAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(orderItemAdapter);
        orderItemAdapter.setNewData(orderList);

        orderItemAdapter.setOnItemClickListener(((adapter, view, position) -> {
            LWRouter.go(getActivity(), RouterConfig.ORDERDETAIL);
        }));
    }

    private void buildOrderItemList() {
        orderList = new ArrayList<>();
        for (int i = 0 ; i < 8 ; i++) {
            OrderItemViewModel viewModel = new OrderItemViewModel();
            viewModel.itemId = String.valueOf(i+1);
            viewModel.itemName = "Llweiya_item_" + (i+1);
            viewModel.itemStatus = "订单准备中";
            viewModel.totalPrice = "￥100.00";
            if (i == 2 || i == 5) {
                viewModel.goodsList = buildFoodItemList(5);
            } else if (i == 7) {
                viewModel.goodsList = buildFoodItemList(1);
            } else {
                viewModel.goodsList = buildFoodItemList(2);
            }
            orderList.add(viewModel);
        }
    }

    private List<FoodItemModel> buildFoodItemList(int size) {
        List<FoodItemModel> list = new ArrayList<>();
        for (int i = 0 ; i < size ; i++) {
            FoodItemModel itemModel = new FoodItemModel();
            itemModel.goodsId = String.valueOf(i+1);
            itemModel.goodsName = "Llweiya_food_item_" + (i+1);
            itemModel.goodsNum = i+1;
            itemModel.goodsPrice = 20;
            list.add(itemModel);
        }
        return list;
    }

}
