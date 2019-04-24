package com.llweiya.ysx.starchef.business.maintab.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.view.CommunityMainFragment;
import com.llweiya.ysx.starchef.business.community.view.FavoriteFragment;
import com.llweiya.ysx.starchef.business.community.view.HomeFragment;
import com.llweiya.ysx.starchef.business.order.view.OrderFragment;
import com.llweiya.ysx.starchef.business.user.view.UserInfoFragment;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityMainTabBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainTabActivity extends BaseActivity<ActivityMainTabBinding> {

    private FragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        initTabItems();
        addListener();
    }

    private void initTabItems() {
        List<Fragment> items = new ArrayList<>();
        items.add(HomeFragment.newInstance());
        items.add(FavoriteFragment.newInstance());
        items.add(CommunityMainFragment.newInstance());
        items.add(OrderFragment.newInstance());
        items.add(UserInfoFragment.newInstance());

        pagerAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return items.get(position);
            }

            @Override
            public int getCount() {
                return items.size();
            }

            @Override
            public int getItemPosition(@NonNull Object object) {
                return POSITION_NONE;
            }
        };
        viewBinding.viewPager.setAdapter(pagerAdapter);
        viewBinding.viewPager.setOffscreenPageLimit(items.size());
    }

    private void addListener() {
        viewBinding.bottomBar.setOnTabSelectListener(tabId -> {
            Timber.e("select >>> " + tabId);
            switch (tabId) {
                case R.id.tab_home:
                    changeCurrentTab(0);
                    break;
                case R.id.tab_favorite:
                    changeCurrentTab(1);
                    break;
                case R.id.tab_community:
                    changeCurrentTab(2);
                    break;
                case R.id.tab_order:
                    changeCurrentTab(3);
                    break;
                case R.id.tab_mine:
                    changeCurrentTab(4);
                    break;
            }
        });

        viewBinding.bottomBar.setOnTabReselectListener(tabId -> {

        });
    }

    private void changeCurrentTab(int index) {
        viewBinding.viewPager.setCurrentItem(index, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_tab;
    }

    @Override
    public void injectComponent() {

    }
}
