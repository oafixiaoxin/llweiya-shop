package com.llweiya.ysx.starchef.aop;

import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.injection.AppComponent;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = AppComponent.class
)
public interface CheckLoginComponent {
    void inject(CheckLoginAspect aspect);
}
