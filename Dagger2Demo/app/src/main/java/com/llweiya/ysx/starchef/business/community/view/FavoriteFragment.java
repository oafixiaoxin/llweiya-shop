package com.llweiya.ysx.starchef.business.community.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.databinding.FragmentFavoriteBinding;

public class FavoriteFragment extends Fragment {

    private FragmentFavoriteBinding favoriteBinding;

    public FavoriteFragment() {

    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        favoriteBinding = DataBindingUtil.bind(view);

        return view;
    }

}
