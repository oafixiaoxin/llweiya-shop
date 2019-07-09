package com.llweiya.ysx.starchef.business.community.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.maintab.view.ScaleTransitionPagerTitleView;
import com.llweiya.ysx.starchef.business.order.view.OrderFragment;
import com.llweiya.ysx.starchef.common.ScrollablePagerAdapter;
import com.llweiya.ysx.starchef.common.utils.UIUtil;
import com.llweiya.ysx.starchef.databinding.FragmentFavoriteMainBinding;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteMainFragment extends Fragment {

    private FragmentFavoriteMainBinding favoriteBinding;

    private List<String> indicatorTitles;

    public FavoriteMainFragment() {

    }

    public static FavoriteMainFragment newInstance() {
        return new FavoriteMainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_main, container, false);

        favoriteBinding = DataBindingUtil.bind(view);

        buildIndicatorTitles();
        initScrollViewPager();

        return view;
    }

    private void buildIndicatorTitles () {
        indicatorTitles = new ArrayList<>();
        indicatorTitles.add("关注");
        indicatorTitles.add("推荐");
        indicatorTitles.add("附近");
        indicatorTitles.add("榜单");
    }

    private void initScrollViewPager() {
        List<ScrollablePagerAdapter.Item> items = items();
        favoriteBinding.viewPager.setOffscreenPageLimit(items.size());
        final ScrollablePagerAdapter adapter = new ScrollablePagerAdapter(getChildFragmentManager(), items);
        favoriteBinding.viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        initIndicator();
    }

    private List<ScrollablePagerAdapter.Item> items() {
        List<ScrollablePagerAdapter.Item> list = new ArrayList<>(indicatorTitles.size());
        list.add(new ScrollablePagerAdapter.Item(indicatorTitles.get(0), FavoriteFragment::newInstance));
        list.add(new ScrollablePagerAdapter.Item(indicatorTitles.get(1), CommunityMainFragment::newInstance));
        list.add(new ScrollablePagerAdapter.Item(indicatorTitles.get(2), CommunityMainFragment::newInstance));
        list.add(new ScrollablePagerAdapter.Item(indicatorTitles.get(3), OrderFragment::newInstance));
        return list;
    }

    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setScrollPivotX(0.8f);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return indicatorTitles == null ? 0 : indicatorTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(indicatorTitles.get(index));
                simplePagerTitleView.setTextSize(22);
                simplePagerTitleView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                simplePagerTitleView.setPadding(UIUtil.dip2pixel(getContext(), index == 0 ? 10 : 5),
                        0, UIUtil.dip2pixel(getContext(), index == getCount()-1 ? 10 : 5), 0);
                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.llweiya_text_color_gray));
                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.llweiya_text_color_black));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        favoriteBinding.viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(getContext());
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(UIUtil.dip2pixel(getContext(), 0));
                indicator.setColors(getContext().getResources().getColor(R.color.llweiya_main_color));
                return indicator;
            }
        });
        favoriteBinding.indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(favoriteBinding.indicator, favoriteBinding.viewPager);
    }

}
