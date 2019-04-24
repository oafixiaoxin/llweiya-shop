package com.llweiya.ysx.starchef.common.injection;

import android.app.Application;

import com.llweiya.ysx.starchef.common.api.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by ysx on 2018/1/15.
 */

@Singleton
@Component(
        modules = {
                AppModule.class,
                RetrofitModule.class
        }
)

public interface AppComponent {
    Application getApplication();
    Retrofit getRetrofit();
    OkHttpClient getOkHttpClient();

    void inject(Application ysxApplication);
}
