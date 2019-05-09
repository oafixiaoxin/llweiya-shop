package com.llweiya.ysx.starchef.business.order.injection;

import com.llweiya.ysx.starchef.business.order.view.OrderDetailActivity;
import com.llweiya.ysx.starchef.business.order.view.PayOrderActivity;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                BaseModule.class,
                OrderModule.class
        }
)
public interface OrderComponent {
    void inject(OrderDetailActivity activity);
    void inject(PayOrderActivity activity);
}
