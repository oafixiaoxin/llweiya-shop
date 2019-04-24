package com.llweiya.ysx.starchef.business.maintab.view;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.githang.statusbar.StatusBarCompat;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityMainTabBinding;
import com.roughike.bottombar.OnTabSelectListener;

import timber.log.Timber;

public class MainTabActivity extends BaseActivity<ActivityMainTabBinding> {

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        addListener();
    }

    private void addListener() {
        viewBinding.bottomBar.setOnTabSelectListener(tabId -> {
            Timber.e("select >>> " + tabId);
        });

        viewBinding.bottomBar.setOnTabReselectListener(tabId -> {
            Timber.e("reSelect >>> " + tabId);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_tab;
    }

    @Override
    public void injectComponent() {

    }
}
