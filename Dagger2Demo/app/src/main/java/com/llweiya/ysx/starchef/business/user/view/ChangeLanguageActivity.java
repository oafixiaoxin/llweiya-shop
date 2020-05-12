package com.llweiya.ysx.starchef.business.user.view;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.annotation.apt.LlweiyaRouter;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.user.injection.DaggerUserComponent;
import com.llweiya.ysx.starchef.business.user.model.NinePictureItemViewModel;
import com.llweiya.ysx.starchef.business.user.view.adapter.NinePictureTestAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityChangeLanguageBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@LlweiyaRouter(RouterConfig.CHANGELANGUAGE)
public class ChangeLanguageActivity extends BaseActivity<ActivityChangeLanguageBinding> {

    private static final String PICTURE_URL = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574310700237&di=177cad4113f4d385a12099ee08f47175&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201509%2F30%2F20150930234745_kdwJ2.jpeg";

    private NinePictureTestAdapter adapter;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        setTitle(getString(R.string.text_language));

        viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NinePictureTestAdapter();
        viewBinding.recyclerView.setAdapter(adapter);
        adapter.setNewData(buildData());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_language;
    }

    @Override
    public void injectComponent() {
        DaggerUserComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);
    }

    private List<NinePictureItemViewModel> buildData() {
        List<NinePictureItemViewModel> list = new ArrayList<>();
        for (int i = 0 ; i < 33 ; i++) {
            NinePictureItemViewModel viewModel = new NinePictureItemViewModel();
            viewModel.avatarUrl = "http://b-ssl.duitang.com/uploads/item/201807/19/20180719134938_okilr.jpg";
            viewModel.content = "apink 南珠";
            viewModel.userName = "25岁的大叔";
            Random random = new Random();
            int index = random.nextInt(10);
            for (int j = 0 ; j < index ; j ++) {
                viewModel.pictureUrls.add(PICTURE_URL);
            }
            list.add(viewModel);
        }
        return list;
    }
}
