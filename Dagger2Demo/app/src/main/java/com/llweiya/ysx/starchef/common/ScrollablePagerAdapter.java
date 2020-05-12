package com.llweiya.ysx.starchef.common;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ScrollablePagerAdapter extends FragmentPagerAdapter {
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
