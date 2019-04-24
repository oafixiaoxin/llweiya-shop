package com.llweiya.ysx.starchef.common.application;

import android.app.AppOpsManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.support.multidex.MultiDex;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushSettings;
import com.llweiya.ysx.starchef.common.application.ApplicationContract;
import com.llweiya.ysx.starchef.common.application.LlweiyaBiz;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 * Created by ysx on 2018/1/16.
 */

public class LlweiyaApplication extends Application implements ApplicationContract {

    private static final String TAG = "LlweiyaApplication";
    private static LlweiyaApplication llweiyaApplication;
    private static Context mcontext;

    public LlweiyaApplication () {
        llweiyaApplication = this;
    }

    public static LlweiyaApplication getInstance() {
        return llweiyaApplication;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);

        MultiDex.install(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = getApplicationContext();
        llweiyaApplication = this;

        initLlweiyaBiz();
        initPushSDK();

        BGASwipeBackHelper.init(this, null);

//        RxTool.init(this);

        Log.e(TAG, getPackageName());

    }

    private void initPushSDK() {
        PushSettings.enableDebugMode(getApplicationContext(), true);
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "elvvNrbdq9HI9cTCgLZlAyKx");
    }

    private LlweiyaBiz llweiyaBiz;

    public LlweiyaBiz getLlweiyaBiz() {
        return llweiyaBiz;
    }

    private void initLlweiyaBiz() {
        llweiyaBiz = LlweiyaBiz.init(this);
    }

}
