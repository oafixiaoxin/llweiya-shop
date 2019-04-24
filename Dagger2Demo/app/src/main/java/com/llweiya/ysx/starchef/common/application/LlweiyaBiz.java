package com.llweiya.ysx.starchef.common.application;

import android.app.Application;

/**
 * Created by ysx on 2018/1/16.
 */

public class LlweiyaBiz {

    private Application application;

    private LlweiyaBiz(Application application) {
        this.application = application;

        DaggerLlweiyaBizComponent.builder()
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    public static LlweiyaBiz init(Application application) {
        LlweiyaApp.setup(application);
        return new LlweiyaBiz(application);
    }

}
