package com.llweiya.ysx.starchef.business.order.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.view.CommunityMainFragment;
import com.llweiya.ysx.starchef.business.community.view.FavoriteFragment;
import com.llweiya.ysx.starchef.business.community.view.HomeFragment;
import com.llweiya.ysx.starchef.common.ScrollablePagerAdapter;
import com.llweiya.ysx.starchef.databinding.FragmentOrderBinding;

import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding orderBinding;

    private List<String> indicatorTitles;

    public OrderFragment() {

    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        orderBinding = DataBindingUtil.bind(view);

        buildIndicatorTitles();
        initScrollViewPager();

        return view;
    }

    private void buildIndicatorTitles () {
        indicatorTitles = new ArrayList<>();
        indicatorTitles.add("订单");
        indicatorTitles.add("购物车");
        indicatorTitles.add("收藏");
    }

    private void initScrollViewPager() {
        List<ScrollablePagerAdapter.Item> items = items();
        final ScrollablePagerAdapter adapter = new ScrollablePagerAdapter(getActivity().getSupportFragmentManager(), items);
        orderBinding.viewPager.setAdapter(adapter);

        initIndicator();
    }

    private List<ScrollablePagerAdapter.Item> items() {
        List<ScrollablePagerAdapter.Item> list = new ArrayList<>(3);
        list.add(new ScrollablePagerAdapter.Item(indicatorTitles.get(0), HomeFragment::newInstance));
        list.add(new ScrollablePagerAdapter.Item(indicatorTitles.get(1), FavoriteFragment::newInstance));
        list.add(new ScrollablePagerAdapter.Item(indicatorTitles.get(2), CommunityMainFragment::newInstance));
        return list;
    }

    private void initIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return indicatorTitles == null ? 0 : indicatorTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(getContext());
                colorTransitionPagerTitleView.setNormalColor(getContext().getResources().getColor(R.color.llweiya_text_color_gray));
                colorTransitionPagerTitleView.setSelectedColor(getContext().getResources().getColor(R.color.llweiya_text_color_black));
                colorTransitionPagerTitleView.setText(indicatorTitles.get(index));
                colorTransitionPagerTitleView.setTextSize(22);
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        orderBinding.viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(getContext());
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                indicator.setLineHeight(2);
                indicator.setColors(getContext().getResources().getColor(R.color.llweiya_main_color));
                return indicator;
            }
        });
        orderBinding.indicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(orderBinding.indicator, orderBinding.viewPager);
    }

}
