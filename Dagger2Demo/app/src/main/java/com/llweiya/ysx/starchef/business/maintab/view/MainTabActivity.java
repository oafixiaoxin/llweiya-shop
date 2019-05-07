package com.llweiya.ysx.starchef.business.maintab.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.KeyEvent;

import com.example.annotation.aspect.CheckLogin;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.view.CommunityMainFragment;
import com.llweiya.ysx.starchef.business.community.view.FavoriteFragment;
import com.llweiya.ysx.starchef.business.community.view.HomeFragment;
import com.llweiya.ysx.starchef.business.order.view.OrderMainFragment;
import com.llweiya.ysx.starchef.business.user.model.UserInfoManager;
import com.llweiya.ysx.starchef.business.user.model.UserInfoModel;
import com.llweiya.ysx.starchef.business.user.view.UserInfoFragment;
import com.llweiya.ysx.starchef.common.view.BaseActivity;
import com.llweiya.ysx.starchef.databinding.ActivityMainTabBinding;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class MainTabActivity extends BaseActivity<ActivityMainTabBinding> {
    private double exitTime = 0;

    private FragmentStatePagerAdapter pagerAdapter;

    @Override
    protected void onCreateSubView(Bundle savedInstanceState) {
        initTabItems();
        addListener();

        UserInfoModel userInfoModel = UserInfoModel.getInstance();
        if (userInfoModel.getUserId() == 0) {
            userInfoModel.setUserId(10086);
            userInfoModel.setUserName("25岁的土味大叔");
            userInfoModel.setNickname("My Heart Will Go ON");
            userInfoModel.setSex(1);
            userInfoModel.setBirthday("1994-01-09");
            userInfoModel.setUserDescription("我一生之中最幸运的两件事，一件是时间终于将我对你的爱消耗殆尽，另一件事，很久很久前有一天，我遇见了你。");
            UserInfoManager.cacheUserInfo(this, userInfoModel);
        }
    }

    private void initTabItems() {
        List<Fragment> items = new ArrayList<>();
        items.add(HomeFragment.newInstance());
        items.add(FavoriteFragment.newInstance());
        items.add(CommunityMainFragment.newInstance());
        items.add(OrderMainFragment.newInstance());
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
    @CheckLogin
    private void changeCurrentTabNeedLogin(int index) {
        viewBinding.viewPager.setCurrentItem(index, false);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast(getString(R.string.toast_exit_app));
                // 将系统当前的时间赋值给exitTime
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main_tab;
    }

    @Override
    public void injectComponent() {

    }
}
