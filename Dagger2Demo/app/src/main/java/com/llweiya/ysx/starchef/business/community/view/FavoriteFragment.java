package com.llweiya.ysx.starchef.business.community.view;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.injection.DaggerCommunityComponent;
import com.llweiya.ysx.starchef.business.community.model.FavoriteItemModel;
import com.llweiya.ysx.starchef.business.community.presenter.FavoritePresenter;
import com.llweiya.ysx.starchef.business.community.view.adapter.FavoriteItemAdapter;
import com.llweiya.ysx.starchef.common.application.LlweiyaApp;
import com.llweiya.ysx.starchef.common.injection.AppComponent;
import com.llweiya.ysx.starchef.common.injection.BaseModule;
import com.llweiya.ysx.starchef.databinding.FragmentFavoriteBinding;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class FavoriteFragment extends Fragment implements FavoriteView, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    FavoritePresenter presenter;

    private FragmentFavoriteBinding favoriteBinding;
    private FavoriteItemAdapter adapter;
    private int currentPage = 1;

    private BaseModule baseModule;

    public FavoriteFragment() {

    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        return favoriteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);

        DaggerCommunityComponent.builder()
                .baseModule(getBaseModule())
                .appComponent(LlweiyaApp.getAppComponent())
                .build()
                .inject(this);

        favoriteBinding = DataBindingUtil.bind(rootView);

        initAdapter();

        favoriteBinding.swipeRefreshLayout.setRefreshing(true);
        onRefresh();

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        setupComponent(LlweiyaApp.getAppComponent(), new BaseModule(context, this));
    }

    private void initAdapter() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        favoriteBinding.recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new FavoriteItemAdapter();
        favoriteBinding.recyclerView.setAdapter(adapter);
        favoriteBinding.recyclerView.setItemAnimator(null);
        favoriteBinding.swipeRefreshLayout.setOnRefreshListener(this);
//        favoriteBinding.recyclerView.addItemDecoration(new StaggeredItemDecoration.Builder()
//                .setContext(getActivity())
//                .setLeftPadding(10)
//                .setBottomPadding(10)
//                .setRightPadding(5)
//                .build());
        favoriteBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE:
                        favoriteBinding.fab.extend();
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                    case RecyclerView.SCROLL_STATE_SETTLING:
                        favoriteBinding.fab.shrink();
                        break;
                    default:
                        break;
                }
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        presenter.getUserFavoriteItem(currentPage);
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
        Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLongToast(CharSequence text) {
        Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
    }

    @Override
    public BaseModule getBaseModule() {
        return this.baseModule;
    }

    private void setupComponent(AppComponent appComponent, BaseModule baseModule) {
        this.baseModule = baseModule;
    }

    @Override
    public void getDataSuccess(List<FavoriteItemModel> list) {
        if (currentPage == 1) {
            adapter.setNewData(list);
        } else {
            adapter.addData(list);
        }
        favoriteBinding.swipeRefreshLayout.setRefreshing(false);
        favoriteBinding.swipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void getDataFailed(String errorString) {
        showToast(errorString);
    }
}
