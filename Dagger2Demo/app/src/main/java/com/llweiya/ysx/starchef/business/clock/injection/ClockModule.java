package com.llweiya.ysx.starchef.business.clock.injection;

import android.content.Context;

import com.llweiya.ysx.starchef.business.clock.interactor.ClockInteractor;
import com.llweiya.ysx.starchef.business.clock.interactor.ClockInteractorImpl;
import com.llweiya.ysx.starchef.business.clock.presenter.ClockPresenter;
import com.llweiya.ysx.starchef.business.clock.view.ClockView;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.presenter.BasePresenter;
import com.llweiya.ysx.starchef.common.view.BaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ysx on 2018/7/23.
 */

@Module
public class ClockModule {

    public ClockModule() {}

    @Provides
    @ActivityScope
    ClockInteractor provideClockInteractor () {
        return new ClockInteractorImpl();
    }

    @Provides
    ClockPresenter provideClockPresenter(Context context, ClockInteractor clockInteractor, BaseView baseView) {
        return new BasePresenter.Builder<ClockInteractor, ClockView>()
                .setCurrentContext(context)
                .setInteractor(clockInteractor)
                .attachView(baseView)
                .build(ClockPresenter.class);
    }

}
