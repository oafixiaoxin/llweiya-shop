package com.llweiya.ysx.starchef.business.community.injection;

import android.content.Context;

import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractor;
import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractorImpl;
import com.llweiya.ysx.starchef.business.community.presenter.CommunityMainPresenter;
import com.llweiya.ysx.starchef.business.community.presenter.FavoritePresenter;
import com.llweiya.ysx.starchef.business.community.presenter.HomeDataPresenter;
import com.llweiya.ysx.starchef.business.community.presenter.UserDefinedCoursePresenter;
import com.llweiya.ysx.starchef.business.community.view.CommunityMainView;
import com.llweiya.ysx.starchef.business.community.view.FavoriteView;
import com.llweiya.ysx.starchef.business.community.view.HomeDataView;
import com.llweiya.ysx.starchef.business.community.view.UserDefinedCourseView;
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

    @Provides
    FavoritePresenter provideFavoritePresenter(Context context, BaseView baseView, CommunityInteractor interactor) {
        return new BasePresenter.Builder<CommunityInteractor, FavoriteView>()
                .attachView(baseView)
                .setCurrentContext(context)
                .setInteractor(interactor)
                .build(FavoritePresenter.class);
    }

    @Provides
    CommunityMainPresenter proviceCommunityMainPresenter(Context context, BaseView baseView, CommunityInteractor interactor) {
        return new BasePresenter.Builder<CommunityInteractor, CommunityMainView>()
                .attachView(baseView)
                .setCurrentContext(context)
                .setInteractor(interactor)
                .build(CommunityMainPresenter.class);
    }

    @Provides
    UserDefinedCoursePresenter proviceUserDefinedCoursePresenter(Context context, BaseView baseView, CommunityInteractor interactor) {
        return new BasePresenter.Builder<CommunityInteractor, UserDefinedCourseView>()
                .attachView(baseView)
                .setCurrentContext(context)
                .setInteractor(interactor)
                .build(UserDefinedCoursePresenter.class);
    }
}
