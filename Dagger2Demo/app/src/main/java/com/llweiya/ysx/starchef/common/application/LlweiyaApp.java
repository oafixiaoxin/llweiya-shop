package com.llweiya.ysx.starchef.common.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.llweiya.ysx.starchef.business.user.model.UserInfoManager;
import com.llweiya.ysx.starchef.business.user.model.UserInfoModel;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.AppModule;
import com.llweiya.ysx.starchef.common.injection.DaggerAppComponent;
import com.llweiya.ysx.starchef.common.view.NineGridViewImageLoader;
import com.lzy.ninegrid.NineGridView;

import java.util.Stack;

/**
 * Created by ysx on 2018/1/16.
 */

public class LlweiyaApp {
    private static Stack<Activity> activityStack;

    private static AppComponent appComponent;

    public static void setup(Application application) {
        UserInfoModel userInfoModel = UserInfoManager.getUserInfoFromCache(application);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application, userInfoModel))
                .build();
        appComponent.inject(application);

        activityStack = new Stack<>();
        registerActivityLifecycle(application);

        //九宫格图片显示
        NineGridView.setImageLoader(new NineGridViewImageLoader());

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Activity getCurrentActivity() {
        try {
            return activityStack.lastElement();
        } catch (Exception ex) {
            return null;
        }
    }

    private static void registerActivityLifecycle(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                activityStack.add(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                activityStack.remove(activity);
            }
        });
    }

}
