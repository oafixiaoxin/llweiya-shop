package com.llweiya.ysx.starchef.business.user.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityUserInfoBinding;

public class UserInfoActivity extends BaseActivity<ActivityUserInfoBinding> implements UserInfoView {

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        setTitle("个人信息");

        viewBinding.ctlTest.setTitle("我们都一样");
        viewBinding.ctlTest.setCollapsedTitleGravity(Gravity.LEFT);
        viewBinding.ctlTest.setCollapsedTitleTextColor(Color.WHITE);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void injectComponent() {
        DaggerUserComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }
}
