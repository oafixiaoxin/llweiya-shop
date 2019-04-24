package com.llweiya.ysx.starchef.common.injection;

import android.app.Application;
import android.content.Context;
import android.databinding.ViewDataBinding;

import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.common.view.BaseView;

import dagger.Component;

/**
 * Created by ysx on 2018/1/15.
 */

@ActivityScope
@Component(
        dependencies = AppComponent.class,
        modules = BaseModule.class
)

public interface BaseComponent {
    void inject(BaseActivity<ViewDataBinding> baseActivity);

    Application getApplication();
    Context getContext();
    BaseView getBaseView();
}
