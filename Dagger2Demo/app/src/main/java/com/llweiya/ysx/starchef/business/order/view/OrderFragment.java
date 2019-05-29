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
import com.llweiya.ysx.starchef.business.order.model.OrderEnum;
import com.llweiya.ysx.starchef.business.order.model.OrderItemViewModel;
import com.llweiya.ysx.starchef.business.order.view.adapter.OrderItemAdapter;
import com.llweiya.ysx.starchef.databinding.FragmentOrderBinding;
import com.lpmas.apt.LWRouter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;

    private OrderItemAdapter orderItemAdapter;

    private List<OrderItemViewModel> orderList;
    String[] images = {
            "http://test-img001.lpfile.com/private/18930318/20190527190500560kwms4.jpg",
            "http://test-img001.lpfile.com/private/18930318/20190527190501789uug7q.jpg",
            "http://test-img001.lpfile.com/private/18930318/20190527190502476q3qaz.jpg",
            "http://test-img001.lpfile.com/private/18930318/20190527190503069jaddn.jpg",
            "http://test-img001.lpfile.com/private/18930318/20190527190504149a1mgz.jpg",
            "http://test-img001.lpfile.com/private/18930318/20190527190504822fkzah.png",
            "http://test-img001.lpfile.com/private/18930318/20190527190505343i039v.jpg",
            "http://test-img001.lpfile.com/private/18930318/201905271905056834fzzr.jpg",
            "http://test-img001.lpfile.com/private/18930318/20190527190506179tvmi0.jpg"
    };

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
            OrderItemViewModel viewModel = (OrderItemViewModel)adapter.getData().get(position);
            if (viewModel.itemStatus.equals(OrderEnum.ORDER_STATUS_WAIT_PAY)
                || viewModel.itemStatus.equals(OrderEnum.ORDER_STATUS_PREPARE)) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put(RouterConfig.EXTRA_DATA, viewModel);
                LWRouter.go(getActivity(), RouterConfig.PAYORDER, hashMap);
            } else {
                LWRouter.go(getActivity(), RouterConfig.ORDERDETAIL);
            }
        }));
    }

    private void buildOrderItemList() {
        orderList = new ArrayList<>();
        for (int i = 0 ; i < 8 ; i++) {
            OrderItemViewModel viewModel = new OrderItemViewModel();
            viewModel.itemId = String.valueOf(i+1);
            viewModel.itemName = "Llweiya_item_" + (i+1);
            viewModel.totalPrice = "￥100.00";
            if (i == 2 || i == 5) {
                viewModel.goodsList = buildFoodItemList(5);
                viewModel.imageList = buildImageList(3);
                viewModel.itemStatus = OrderEnum.ORDER_STATUS_FINISH;
            } else if (i == 7) {
                viewModel.goodsList = buildFoodItemList(8);
                viewModel.imageList = buildImageList(4);
                viewModel.itemStatus = OrderEnum.ORDER_STATUS_PREPARE;
            } else if (i == 6) {
                viewModel.goodsList = buildFoodItemList(1);
                viewModel.imageList = buildImageList(9);
                viewModel.itemStatus = OrderEnum.ORDER_STATUS_WAIT_COMMENT;
            } else {
                viewModel.itemStatus = OrderEnum.ORDER_STATUS_WAIT_PAY;
                viewModel.imageList = buildImageList(1);
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

    private List<String> buildImageList(int size) {
        List<String> imageList = new ArrayList<>();
        for (int i = 0 ; i < size ; i++) {
            imageList.add(images[i]);
        }
        return imageList;
    }

}
