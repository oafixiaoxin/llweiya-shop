package com.llweiya.ysx.starchef.business.order.view;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.annotation.apt.Extra;
import com.example.annotation.apt.LlweiyaRouter;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.order.injection.DaggerOrderComponent;
import com.llweiya.ysx.starchef.business.order.model.OrderItemViewModel;
import com.llweiya.ysx.starchef.business.order.view.adapter.PayOrderItemAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityPayOrderBinding;
import com.lpmas.apt.LWRouter;

@LlweiyaRouter(RouterConfig.PAYORDER)
public class PayOrderActivity extends BaseActivity<ActivityPayOrderBinding> {

    @Extra(RouterConfig.EXTRA_DATA)
    public OrderItemViewModel order;

    private PayOrderItemAdapter adapter;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        LWRouter.bind(this);
        setTitle(getString(R.string.text_pay_order));

        initAdapter();

        initListener();
    }

    private void initListener() {
        viewBinding.btnConfirmOrder.setOnClickListener(v -> {
            viewBinding.confirmView.showLayout();
        });
    }

    private void initAdapter() {
        adapter = new PayOrderItemAdapter();
        adapter.setNewData(order.goodsList);

        viewBinding.recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        viewBinding.recyclerViewItems.setAdapter(adapter);
        viewBinding.recyclerViewItems.setNestedScrollingEnabled(false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay_order;
    }

    @Override
    public void injectComponent() {
        DaggerOrderComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    @Override
    public void setNeedElevation() {
        needElevation = false;
    }
}
