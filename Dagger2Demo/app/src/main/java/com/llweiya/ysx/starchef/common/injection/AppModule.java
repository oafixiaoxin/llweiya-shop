package com.llweiya.ysx.starchef.common.injection;

import android.app.Application;

import com.llweiya.ysx.starchef.business.user.model.UserInfoModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ysx on 2018/1/15.
 */

@Module
public class AppModule {
    private Application application;
    private UserInfoModel userInfo;

    public AppModule(Application application, UserInfoModel userInfoModel) {
        this.application = application;
        this.userInfo = userInfoModel;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    UserInfoModel provideUserInfoModel() {
        return userInfo;
    }
}
