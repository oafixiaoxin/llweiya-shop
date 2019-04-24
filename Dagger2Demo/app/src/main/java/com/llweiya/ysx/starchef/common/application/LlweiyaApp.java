package com.llweiya.ysx.starchef.common.application;

import android.app.Application;

import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.AppModule;
import com.llweiya.ysx.starchef.common.injection.DaggerAppComponent;

/**
 * Created by ysx on 2018/1/16.
 */

public class LlweiyaApp {

    private static AppComponent appComponent;

    public static void setup(Application application) {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
        appComponent.inject(application);
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
