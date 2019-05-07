package com.llweiya.ysx.starchef.business.order.view;

import android.os.Bundle;

import com.example.annotation.apt.LlweiyaRouter;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.order.injection.DaggerOrderComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;

@LlweiyaRouter(RouterConfig.ORDERDETAIL)
public class OrderDetailActivity extends BaseActivity {

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void injectComponent() {
        DaggerOrderComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }
}
