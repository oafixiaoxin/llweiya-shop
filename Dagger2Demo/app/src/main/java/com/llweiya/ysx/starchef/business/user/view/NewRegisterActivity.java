package com.llweiya.ysx.starchef.business.user.view;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityNewRegisterBinding;

public class NewRegisterActivity extends BaseActivity<ActivityNewRegisterBinding> {

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

        String userProtocal = "点击注册即表示接受 <font color='#F9B032'>《LlweiyaFood用户协议》</font>";
        viewBinding.txtUserProtocal.setText(Html.fromHtml(userProtocal));
    }

    private void addListener() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_register;
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
