package com.llweiya.ysx.starchef.business.dependency.view;

import android.app.Activity;
import android.os.Bundle;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.dependency.injection.DaggerDependencyComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityAllLocalVideosBinding;

public class AllLocalVideosActivity extends BaseActivity<ActivityAllLocalVideosBinding> {

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        setTitle("视频");

        
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_all_local_videos;
    }

    @Override
    public void injectComponent() {
        DaggerDependencyComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }
}
