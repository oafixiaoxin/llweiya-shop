package com.llweiya.ysx.starchef.business.dependency.view;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.dependency.injection.DaggerDependencyComponent;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.model.TransmittedMessage;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityAnnouncementBinding;

public class AnnouncementActivity extends BaseActivity<ActivityAnnouncementBinding> {

    @Override
    public void onCreateSubView(Bundle savedInstanceState) {

        setTitle("");
        initUI();

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_announcement;
    }

    @Override
    public void injectComponent() {
        DaggerDependencyComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    private void initUI() {
        String message = getIntent().getStringExtra("message");
        if (!TextUtils.isEmpty(message)) {
            final TransmittedMessage transmittedMessage = new Gson().fromJson(message, TransmittedMessage.class);
            viewBinding.txtMessage.setText(transmittedMessage.getMessage());
        }
    }
}
