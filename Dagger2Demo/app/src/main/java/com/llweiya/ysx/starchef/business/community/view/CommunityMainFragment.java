package com.llweiya.ysx.starchef.business.community.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.databinding.FragmentCommunityMainBinding;

public class CommunityMainFragment extends Fragment {

    private FragmentCommunityMainBinding mainBinding;

    public CommunityMainFragment() {

    }

    public static CommunityMainFragment newInstance() {
        return new CommunityMainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_main, container, false);

        mainBinding = DataBindingUtil.bind(view);

        return view;
    }

}
