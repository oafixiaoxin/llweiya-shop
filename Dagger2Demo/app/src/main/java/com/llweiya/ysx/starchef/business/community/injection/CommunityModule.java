package com.llweiya.ysx.starchef.business.community.injection;

import android.content.Context;

import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractor;
import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractorImpl;
import com.llweiya.ysx.starchef.business.community.presenter.HomeDataPresenter;
import com.llweiya.ysx.starchef.business.community.view.HomeDataView;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.presenter.BasePresenter;
import com.llweiya.ysx.starchef.common.view.BaseView;

import dagger.Module;
import dagger.Provides;

@Module
public class CommunityModule {
    public CommunityModule( ){}

    @Provides
    @ActivityScope
    CommunityInteractor provideUserInteractor() {
        return new CommunityInteractorImpl();
    }

    @Provides
    HomeDataPresenter provideHomeDataPresenter(Context context, BaseView baseView, CommunityInteractor interactor) {
        return new BasePresenter.Builder<CommunityInteractor, HomeDataView>()
                .attachView(baseView)
                .setCurrentContext(context)
                .setInteractor(interactor)
                .build(HomeDataPresenter.class);
    }
}
