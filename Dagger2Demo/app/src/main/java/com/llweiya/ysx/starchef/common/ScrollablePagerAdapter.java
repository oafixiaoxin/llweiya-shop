package com.llweiya.ysx.starchef.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

public class ScrollablePagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    public interface Provider {
        Fragment provide();
    }

    static public class Item {

        final String name;
        final Provider provider;

        public String getName() {
            return name;
        }

        public Item(String name, Provider provider) {
            this.name = name;
            this.provider = provider;
        }
    }

    private final List<Item> mItems;

    public ScrollablePagerAdapter(FragmentManager fm, List<Item> items) {
        super(fm);
        mItems = items;
    }

    @Override
    public Fragment getItem(int i) {
        return mItems.get(i).provider.provide();
    }

    @Override
    public int getCount() {
        return mItems != null
                ? mItems.size()
                : 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mItems.get(position).name;
    }
}
