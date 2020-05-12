package com.llweiya.ysx.starchef.common.application;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.multidex.MultiDex;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.baidu.android.pushservice.PushSettings;
import com.llweiya.ysx.starchef.common.utils.LanguageUtil;
import com.llweiya.ysx.starchef.common.utils.SpUtil;

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

        String language = SpUtil.getInstance(this).getString(SpUtil.LANGUAGE);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N) {
            LanguageUtil.changeAppLanguage(this, language);
        }

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
