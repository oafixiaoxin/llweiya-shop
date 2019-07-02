package com.llweiya.ysx.starchef.business.community.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.injection.DaggerCommunityComponent;
import com.llweiya.ysx.starchef.business.community.model.IHomeDataType;
import com.llweiya.ysx.starchef.business.community.presenter.HomeDataPresenter;
import com.llweiya.ysx.starchef.business.community.view.adapter.HomeDataAdapter;
import com.llweiya.ysx.starchef.common.ComponentReflectionInjector;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;
import com.llweiya.ysx.starchef.common.injection.DaggerBaseComponent;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.llweiya.ysx.starchef.common.utils.UIUtil;
import com.llweiya.ysx.starchef.databinding.FragmentHomeBinding;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends Fragment implements HomeDataView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    HomeDataPresenter presenter;

    private FragmentHomeBinding homeBinding;
    private Toolbar toolbar;

    private HomeDataAdapter homeDataAdapter;

    private BaseModule baseModule;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(Toolbar toolbar) {
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.toolbar = toolbar;
        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DaggerCommunityComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);

        homeBinding = DataBindingUtil.bind(view);

        initToolBar();
        initBannerView();
        initAdapter();

        homeBinding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        setupComponent(LlweiyaApp.getAppComponent(), new BaseModule(context, this));
    }

    @Override
    public void onPause() {
        super.onPause();
        homeBinding.banner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        homeBinding.banner.start();
    }

    private void initAdapter() {
        homeDataAdapter = new HomeDataAdapter();
        homeDataAdapter.openLoadAnimation();
        homeDataAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        homeBinding.recyclerHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeBinding.recyclerHome.setNestedScrollingEnabled(false);
        homeBinding.recyclerHome.setAdapter(homeDataAdapter);
        homeDataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                homeDataAdapter.globalClickAction(view, position, homeDataAdapter.getData().get(position));
            }
        });
        homeBinding.swipeRefreshLayout.setColorSchemeResources(R.color.llweiya_main_color);
        homeBinding.swipeRefreshLayout.setOnRefreshListener(this);
    }

    private void initToolBar() {
        toolbar.removeAllViews();
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.llweiya_home_search_bar, null);

        ActionBar.LayoutParams searchBarLayoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchBarLayoutParams.rightMargin = UIUtil.dip2pixel(getActivity(), 16);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setCustomView(view, searchBarLayoutParams);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(false);
    }

    private void initBannerView() {
        // 设置数据
        String[] images = {
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560171159736&di=1ec49888dd49a363c4cee8637092d2af&imgtype=0&src=http%3A%2F%2Fpics.ettoday.net%2Fimages%2F1523%2Fd1523633.jpg",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3796177013,1776574858&fm=26&gp=0.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560171257772&di=281735c5f44a3e7818028f94f13b0004&imgtype=0&src=http%3A%2F%2Fimg4.duitang.com%2Fuploads%2Fitem%2F201203%2F11%2F20120311103320_ekXTi.thumb.700_0.jpeg"
        };
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            list.add(images[i]);
        }
        homeBinding.banner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    @Override
    public void getDataSuccess(List<IHomeDataType> list) {
        homeBinding.swipeRefreshLayout.setRefreshing(false);
        homeDataAdapter.setNewData(list);
        homeBinding.swipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void getDataFailed(String errorString) {
        homeBinding.swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void viewFinish() {
        getActivity().finish();
    }

    @Override
    public void showProgressView(CharSequence text, boolean cancelable) {

    }

    @Override
    public void dismissProgressView() {

    }

    @Override
    public void showToast(CharSequence text) {

    }

    @Override
    public void showLongToast(CharSequence text) {

    }

    @Override
    public BaseModule getBaseModule() {
        return this.baseModule;
    }

    private void setupComponent(AppComponent appComponent, BaseModule baseModule) {
        this.baseModule = baseModule;

//        ComponentReflectionInjector<BaseComponent> componentInjector
//                = new ComponentReflectionInjector<>(BaseComponent.class, DaggerBaseComponent
//                .builder()
//                .baseModule(baseModule)
//                .appComponent(appComponent)
//                .build());
//        componentInjector.inject(this);
    }

    @Override
    public void onRefresh() {
        presenter.getHomeData();
    }

    private class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.item_home_banner,null);
            mImageView = (ImageView) view.findViewById(R.id.image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, String data) {
            // 数据绑定
            ImageUtil.showImage(getActivity(), mImageView, data);
        }
    }

}
