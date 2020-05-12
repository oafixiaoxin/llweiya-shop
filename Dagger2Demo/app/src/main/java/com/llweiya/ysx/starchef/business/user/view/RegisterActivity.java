package com.llweiya.ysx.starchef.business.user.view;

import android.content.Intent;
import androidx.core.app.NotificationManagerCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidadvance.topsnackbar.TSnackbar;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.business.user.model.CardStackItemViewModel;
import com.llweiya.ysx.starchef.business.user.view.adapter.FlexLayoutTestAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityRegisterBinding;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding> implements RegisterView {

    @Override
    public void onCreateSubView(Bundle savedInstanceState) {
        setTitle("注册");

        NotificationManagerCompat compat = NotificationManagerCompat.from(getApplicationContext());
        boolean isOpened = compat.areNotificationsEnabled();

        if (isOpened) {
            Log.e("LlweiyaApplication", "open");
        } else {
            Log.e("LlweiyaApplication", "close");
        }

        viewBinding.btnJump.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setClass(this, UserInfoActivity.class);
            startActivity(intent);
        });

//        showSnackbar();

        String[] keys = {"水稻种植", "湘阴县线上培育航航上积累了", "大棚的种植技术", "职业农民", "补贴"};
        List<CardStackItemViewModel> list = new ArrayList<>();
        for (int i = 0 ; i < keys.length ; i++) {
            CardStackItemViewModel model = new CardStackItemViewModel();
            model.name = keys[i];
            list.add(model);
        }

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        viewBinding.recyclerView.setLayoutManager(layoutManager);

        FlexLayoutTestAdapter adapter = new FlexLayoutTestAdapter();
        adapter.setNewData(list);
        viewBinding.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void injectComponent() {
        DaggerUserComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    private void showSnackbar() {
        TSnackbar tSnackbar = TSnackbar.make(findViewById(R.id.llayout_root), "test top snackbar", TSnackbar.LENGTH_LONG);
        View view = tSnackbar.getView();
        TSnackbar.SnackbarLayout snackbarLayout = (TSnackbar.SnackbarLayout)view;

        View defineView = LayoutInflater.from(view.getContext()).inflate(R.layout.snackbar,
                null);

        TextView textView = defineView.findViewById(R.id.txt_snackbar_text);
        textView.setText("你有n条未读的消息，请点击查看");

        defineView.findViewById(R.id.btn_check).setOnClickListener(v -> {
//            LPRouter.go(getContext(), RouterConfig.COMMUNITYMAILBOX);
        });

        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                .MATCH_PARENT, 64);//设置新建布局参数

        p.gravity = Gravity.CENTER_VERTICAL;//设置新建布局在Snackbar内垂直居中显示

        snackbarLayout.addView(defineView, 0, p);//将新建布局添加进snackbarLayout相应位置

        tSnackbar.show();

    }

}
