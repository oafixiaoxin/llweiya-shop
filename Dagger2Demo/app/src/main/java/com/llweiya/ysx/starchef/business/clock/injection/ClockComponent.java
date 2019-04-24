package com.llweiya.ysx.starchef.business.clock.injection;

import com.llweiya.ysx.starchef.business.clock.view.ClockActivity;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;

import dagger.Component;

/**
 * Created by ysx on 2018/7/23.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = {
                BaseModule.class,
                ClockModule.class
        }
)

public interface ClockComponent {
        void inject(ClockActivity activity);
}
