package com.llweiya.ysx.starchef.common.injection;

import android.content.Context;

import com.llweiya.ysx.starchef.common.view.BaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ysx on 2018/1/15.
 */

@Module
public class BaseModule {
    private Context context;
    private BaseView baseView;

    public BaseModule(Context context, BaseView baseView) {
        this.context = context;
        this.baseView = baseView;
    }

    @Provides
    @ActivityScope
    Context provideCurrentContext() {
        return this.context;
    }

    @Provides
    @ActivityScope
    BaseView provideBaseView() {
        return this.baseView;
    }
}
