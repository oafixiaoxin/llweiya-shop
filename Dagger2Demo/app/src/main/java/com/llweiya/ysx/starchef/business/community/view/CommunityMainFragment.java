package com.llweiya.ysx.starchef.business.community.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.community.injection.DaggerCommunityComponent;
import com.llweiya.ysx.starchef.business.community.model.CommunityMainCourseModel;
import com.llweiya.ysx.starchef.business.community.model.CommunityMainSectionModel;
import com.llweiya.ysx.starchef.business.community.model.ICommunitySectionType;
import com.llweiya.ysx.starchef.business.community.presenter.CommunityMainPresenter;
import com.llweiya.ysx.starchef.business.community.view.adapter.CommunityMainSectionAdapter;
import com.llweiya.ysx.starchef.business.community.view.adapter.RecommendedCourseItemAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;
import com.llweiya.ysx.starchef.common.utils.ImageUtil;
import com.llweiya.ysx.starchef.common.utils.UIUtil;
import com.llweiya.ysx.starchef.common.utils.Utility;
import com.llweiya.ysx.starchef.common.view.LlweiyaGridItemDecoration;
import com.llweiya.ysx.starchef.databinding.FragmentCommunityMainBinding;
import com.lpmas.apt.LWRouter;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CommunityMainFragment extends Fragment implements CommunityMainView,
        SwipeRefreshLayout.OnRefreshListener {

    private FragmentCommunityMainBinding mainBinding;
    private BaseModule baseModule;
    private CommunityMainSectionAdapter communityMainAdapter;
    private RecommendedCourseItemAdapter mainCourseAdapter;

    @Inject
    CommunityMainPresenter presenter;

    public CommunityMainFragment() {

    }

    public static CommunityMainFragment newInstance() {
        return new CommunityMainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_main, container, false);

        DaggerCommunityComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);

        mainBinding = DataBindingUtil.bind(view);

        initAdapter();
        initToolBar();
        initBannerView();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRefresh();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        setupComponent(LlweiyaApp.getAppComponent(), new BaseModule(context, this));
    }

    @Override
    public void onPause() {
        super.onPause();
        mainBinding.banner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mainBinding.banner.start();
    }

    private void initAdapter() {
        communityMainAdapter = new CommunityMainSectionAdapter();
        mainBinding.recyclerSectionView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mainBinding.swipeRefreshLayout.setOnRefreshListener(this);
        mainBinding.recyclerSectionView.setAdapter(communityMainAdapter);

        mainCourseAdapter = new RecommendedCourseItemAdapter();
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mainBinding.recyclerCourseView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        mainBinding.recyclerCourseView.addItemDecoration(new LlweiyaGridItemDecoration(2, UIUtil.dip2pixel(getActivity(), 10), 0));
        mainBinding.recyclerCourseView.setAdapter(mainCourseAdapter);
        mainBinding.recyclerCourseView.setItemAnimator(null);
        mainCourseAdapter.setOnItemClickListener((adapter, view, position) -> {
            LWRouter.go(getActivity(), RouterConfig.USERDEFINEDCOURSE);
        });
    }

    private void initToolBar() {
        mainBinding.toolbar.removeAllViews();
        ((AppCompatActivity)getActivity()).setSupportActionBar(mainBinding.toolbar);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.llweiya_home_search_bar, null);

        ActionBar.LayoutParams searchBarLayoutParams = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        searchBarLayoutParams.rightMargin = UIUtil.dip2pixel(getActivity(), 16);

        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        actionBar.setCustomView(view, searchBarLayoutParams);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayShowTitleEnabled(false);

        view.findViewById(R.id.llayout_location).setVisibility(View.GONE);
    }

    private void initBannerView() {
        // 设置数据
        String[] images = {
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560171159736&di=1ec49888dd49a363c4cee8637092d2af&imgtype=0&src=http%3A%2F%2Fpics.ettoday.net%2Fimages%2F1523%2Fd1523633.jpg",
                "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3796177013,1776574858&fm=26&gp=0.jpg"
        };
        List<String> list = new ArrayList<>();
        for (int i = 0 ; i < images.length ; i++) {
            list.add(images[i]);
        }
        mainBinding.banner.setPages(list, new MZHolderCreator<CommunityMainFragment.BannerViewHolder>() {
            @Override
            public CommunityMainFragment.BannerViewHolder createViewHolder() {
                return new CommunityMainFragment.BannerViewHolder();
            }
        });
        mainBinding.banner.setIndicatorRes(R.drawable.icon_exam_result_indicator_normal, R.drawable.icon_exam_result_indicator_selected);
    }

    @Override
    public void viewFinish() {

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
    }

    @Override
    public void onRefresh() {
        mainBinding.swipeRefreshLayout.setRefreshing(true);
        presenter.loadData();
    }

    @Override
    public void receiveData(List<ICommunitySectionType> list) {
        mainBinding.swipeRefreshLayout.setRefreshing(false);

        CommunityMainCourseModel courseModel = null;
        List<ICommunitySectionType> sectionList = new ArrayList<>();
        for (ICommunitySectionType type : list) {
            if (type instanceof CommunityMainSectionModel) {
                sectionList.add(type);
            }
            if (type instanceof CommunityMainCourseModel) {
                courseModel = (CommunityMainCourseModel)type;
            }
        }
        if (Utility.listHasElement(sectionList)) {
            communityMainAdapter.setNewData(sectionList);
        }
        if (null != courseModel) {
            mainBinding.txtTitle.setText(courseModel.title);
            mainCourseAdapter.setNewData(courseModel.totalCount > 4 ? courseModel.userCourseList.subList(0, 4) : courseModel.userCourseList);
            mainBinding.llayoutCheckMore.setVisibility(courseModel.totalCount > 4 ? View.VISIBLE : View.GONE);
        }
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
