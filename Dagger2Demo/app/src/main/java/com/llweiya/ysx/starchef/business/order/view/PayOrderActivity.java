package com.llweiya.ysx.starchef.business.order.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.annotation.apt.Extra;
import com.example.annotation.apt.LlweiyaRouter;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.order.injection.DaggerOrderComponent;
import com.llweiya.ysx.starchef.business.order.model.OrderItemViewModel;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityPayOrderBinding;

@LlweiyaRouter(RouterConfig.PAYORDER)
public class PayOrderActivity extends BaseActivity<ActivityPayOrderBinding> {

    @Extra(RouterConfig.EXTRA_DATA)
    public OrderItemViewModel order;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        setTitle(getString(R.string.text_pay_order));
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
