package com.llweiya.ysx.starchef.business.user.view;

import android.os.Bundle;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.business.user.model.CardStackItemViewModel;
import com.llweiya.ysx.starchef.business.user.tools.CardConfig;
import com.llweiya.ysx.starchef.business.user.view.adapter.CardStackAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityCardStackBinding;

import java.util.ArrayList;
import java.util.List;

public class CardStackActivity extends BaseActivity<ActivityCardStackBinding> {

    private CardStackAdapter adapter;
    private List<CardStackItemViewModel> mList;

    private int[] colors = {
            R.color.card_orange,
            R.color.card_light_green,
            R.color.card_dark_green,
            R.color.card_blue,
            R.color.card_red,
            R.color.card_light_blue,
            R.color.card_yellow
    };

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        setTitle("卡片层叠");

        initData();

        CardStackLayoutManager cardStackLayoutManager = new CardStackLayoutManager(this);
        viewBinding.recyclerCardStack.setLayoutManager(cardStackLayoutManager);
        CardConfig.initConfig(this);

        adapter = new CardStackAdapter();
        adapter.setNewData(mList);
        viewBinding.recyclerCardStack.setAdapter(adapter);

        recreate();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i++) {
            CardStackItemViewModel model = new CardStackItemViewModel();
            model.name = "卡片" + i;
            model.description = "卡片描述" + i;
            model.subtitle = "卡片副标题" + i;
            model.picture = getResources().getDrawable(R.drawable.naturo);
            model.backgroundColor = getResources().getColor(colors[i]);
            mList.add(model);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_stack;
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
