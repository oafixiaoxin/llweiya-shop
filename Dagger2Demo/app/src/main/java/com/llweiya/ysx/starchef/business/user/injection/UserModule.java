package com.llweiya.ysx.starchef.business.user.injection;

import android.content.Context;

import com.llweiya.ysx.starchef.business.user.Interactor.UserInteractor;
import com.llweiya.ysx.starchef.business.user.Interactor.UserInteractorImpl;
import com.llweiya.ysx.starchef.business.user.presenter.LoginPresenter;
import com.llweiya.ysx.starchef.business.user.presenter.RegisterPresenter;
import com.llweiya.ysx.starchef.business.user.presenter.UserInfoPresenter;
import com.llweiya.ysx.starchef.business.user.view.LoginView;
import com.llweiya.ysx.starchef.business.user.view.RegisterView;
import com.llweiya.ysx.starchef.business.user.view.UserInfoView;
import com.llweiya.ysx.starchef.common.injection.ActivityScope;
import com.llweiya.ysx.starchef.common.presenter.BasePresenter;
import com.llweiya.ysx.starchef.common.view.BaseView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ysx on 2018/1/16.
 */

@Module
public class UserModule {

    public UserModule() {

    }

    @Provides
    @ActivityScope
    UserInteractor provideUserInteractor() {
        return new UserInteractorImpl();
    }

    @Provides
    LoginPresenter provideLoginPresenter(Context context, BaseView baseView, UserInteractor userInteractor) {
        return new BasePresenter.Builder<UserInteractor, LoginView>()
                .attachView(baseView)
                .setCurrentContext(context)
                .setInteractor(userInteractor)
                .build(LoginPresenter.class);
    }

    @Provides
    RegisterPresenter provideRegisterPresenter(Context context, BaseView baseView, UserInteractor userInteractor) {
        return new BasePresenter.Builder<UserInteractor, RegisterView>()
                .attachView(baseView)
                .setCurrentContext(context)
                .setInteractor(userInteractor)
                .build(RegisterPresenter.class);
    }

    @Provides
    UserInfoPresenter provideUserInfoPresenter(Context context, BaseView baseView, UserInteractor userInteractor) {
        return new BasePresenter.Builder<UserInteractor, UserInfoView>()
                .attachView(baseView)
                .setCurrentContext(context)
                .setInteractor(userInteractor)
                .build(UserInfoPresenter.class);
    }

}
