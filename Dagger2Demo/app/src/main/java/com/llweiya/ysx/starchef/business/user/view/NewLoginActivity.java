package com.llweiya.ysx.starchef.business.user.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.utils.UIUtil;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityNewLoginBinding;

public class NewLoginActivity extends BaseActivity<ActivityNewLoginBinding> {

    private boolean rememberPwd = false;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        setSupportActionBar(viewBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setHomeAsUpIndicator(UIUtil.actionBarBackArrow(this));
        getSupportActionBar().setTitle("");
        viewBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addListener();

        viewBinding.txtSayHello.setText(getResources().getString(R.string.text_say_hello));
    }

    private void addListener() {
        viewBinding.txtRegister.setOnClickListener(v -> {
            Intent intent = new Intent(NewLoginActivity.this, NewRegisterActivity.class);
            this.startActivity(intent);
        });
        viewBinding.llayoutRememberPassword.setOnClickListener(v -> {
            rememberPwd = !rememberPwd;
            if (rememberPwd) {
                viewBinding.imageRememberPassword.setImageDrawable(getResources().getDrawable(R.drawable.icon_tick_circle_choosed));
            } else {
                viewBinding.imageRememberPassword.setImageDrawable(getResources().getDrawable(R.drawable.icon_tick_circle_normal));
            }
        });
        viewBinding.btnLogin.setOnClickListener(v -> showProgressView("llweiya", true));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_login;
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
