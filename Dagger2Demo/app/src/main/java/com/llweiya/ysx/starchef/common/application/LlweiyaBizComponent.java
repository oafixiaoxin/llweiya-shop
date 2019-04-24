package com.llweiya.ysx.starchef.common.application;

import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.injection.AppComponent;

import dagger.Component;

/**
 * Created by ysx on 2018/1/16.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class
)

public interface LlweiyaBizComponent {
    void inject(LlweiyaBiz bizClass);
}
