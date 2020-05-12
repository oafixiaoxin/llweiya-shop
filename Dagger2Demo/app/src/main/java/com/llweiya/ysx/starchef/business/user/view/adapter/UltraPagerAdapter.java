package com.llweiya.ysx.starchef.business.user.view.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by ysx on 2018/10/31.
 */

public class UltraPagerAdapter extends PagerAdapter {
    private boolean isMultiScr;
    private List<String> data;

    public UltraPagerAdapter(boolean isMultiScr, List<String> list) {
        this.isMultiScr = isMultiScr;
        this.data = list;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        LinearLayout view = (LinearLayout) object;
//        container.removeView(view);
//    }
}
