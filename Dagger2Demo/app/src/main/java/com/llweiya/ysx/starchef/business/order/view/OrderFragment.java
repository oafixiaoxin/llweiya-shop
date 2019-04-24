package com.llweiya.ysx.starchef.business.order.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.databinding.FragmentOrderBinding;

public class OrderFragment extends Fragment {

    private FragmentOrderBinding orderBinding;

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

        return view;
    }

}
