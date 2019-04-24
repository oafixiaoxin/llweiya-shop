package com.llweiya.ysx.starchef.business.user.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.databinding.FragmentUserInfoBinding;

public class UserInfoFragment extends Fragment implements View.OnClickListener {

    private FragmentUserInfoBinding binding;

    public UserInfoFragment() {

    }

    public static UserInfoFragment newInstance() {
        return new UserInfoFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_info, container, false);
        binding = DataBindingUtil.bind(rootView);

        addListener();

        return rootView;
    }

    private void addListener() {
        binding.btnClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        if (viewId == R.id.btn_click) {
            Intent intent = new Intent(getActivity(), NewLoginActivity.class);
            getActivity().startActivity(intent);
        }
    }
}
