package com.llweiya.ysx.starchef.business.user.view;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.annotation.aspect.CheckLogin;
import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.aop.RouterConfig;
import com.llweiya.ysx.starchef.business.user.model.CommonItemModel;
import com.llweiya.ysx.starchef.business.user.model.UserInfoModel;
import com.llweiya.ysx.starchef.business.user.view.adapter.CommonItemAdapter;
import com.llweiya.ysx.starchef.business.user.view.adapter.ConfigItemAdapter;
import com.llweiya.ysx.starchef.databinding.FragmentUserInfoBinding;
import com.lpmas.apt.LWRouter;

import java.util.ArrayList;
import java.util.List;

public class UserInfoFragment extends Fragment {

//    @Inject
//    UserInfoModel userInfoModel;

    private FragmentUserInfoBinding binding;

    private CommonItemAdapter itemAdapter1;
    private CommonItemAdapter itemAdapter2;
    private CommonItemAdapter itemAdapter3;
    private ConfigItemAdapter configAdapter;

    private List<CommonItemModel> items1;
    private List<CommonItemModel> items2;
    private List<CommonItemModel> items3;
    private List<CommonItemModel> gridItems;

    private int[] icons1 = {
            R.drawable.icon_item_vip,
            R.drawable.icon_item_address,
            R.drawable.icon_item_history,
            R.drawable.icon_item_comment
    };
    private int[] icons2 = {
            R.drawable.icon_item_point,
            R.drawable.icon_item_help,
            R.drawable.icon_item_feedback
    };
    private int[] icons3 = {
            R.drawable.icon_item_cooperation,
            R.drawable.icon_item_more
    };
    private int[] configIcons = {
            R.drawable.icon_config_cupon,
            R.drawable.icon_config_collection,
            R.drawable.icon_config_money,
            R.drawable.icon_config_shop
    };

    private String[] configTitles = {"卡券", "收藏", "钱包", "商城"};
    private String[] titles1 = {"会员", "地址", "足迹", "评价"};
    private String[] titles2 = {"积分", "帮助", "反馈"};
    private String[] titles3 = {"合作", "更多"};

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

        UserInfoModel user = UserInfoModel.getInstance();
        if (!user.isGuest()) {
            binding.llayoutHasLogin.setVisibility(View.VISIBLE);
            binding.txtNotLogin.setVisibility(View.GONE);

            binding.txtUsername.setText(user.getNickname());
            binding.txtUserDescription.setText(user.getUserDescription());
        } else {
            binding.llayoutHasLogin.setVisibility(View.GONE);
            binding.txtNotLogin.setVisibility(View.VISIBLE);
        }

        addListener();
        buildItemData();
        initAdapter();

        return rootView;
    }

    private void addListener() {
        binding.txtNotLogin.setOnClickListener(v -> LWRouter.go(getActivity(), RouterConfig.NEWLOGIN));
        binding.imageChatRoom.setOnClickListener(v -> toChatRoomPage());
        binding.imageSetting.setOnClickListener(v -> LWRouter.go(getActivity(), RouterConfig.CHANGELANGUAGE));
    }

    private void initAdapter() {
        itemAdapter1 = new CommonItemAdapter();
        itemAdapter2 = new CommonItemAdapter();
        itemAdapter3 = new CommonItemAdapter();
        configAdapter = new ConfigItemAdapter();
        itemAdapter1.setNewData(items1);
        itemAdapter2.setNewData(items2);
        itemAdapter3.setNewData(items3);
        configAdapter.setNewData(gridItems);

        binding.recyclerItem1.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerItem2.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerItem3.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerConfig.setLayoutManager(new GridLayoutManager(getActivity(), 4));

        binding.recyclerItem1.setAdapter(itemAdapter1);
        binding.recyclerItem2.setAdapter(itemAdapter2);
        binding.recyclerItem3.setAdapter(itemAdapter3);
        binding.recyclerConfig.setAdapter(configAdapter);
    }

    private void buildItemData() {
        items1 = new ArrayList<>();
        for (int i = 0 ; i < titles1.length ; i++) {
            CommonItemModel model = new CommonItemModel();
            model.title = titles1[i];
            model.icon = getContext().getResources().getDrawable(icons1[i]);
            items1.add(model);
        }

        items2 = new ArrayList<>();
        for (int i = 0 ; i < titles2.length ; i++) {
            CommonItemModel model = new CommonItemModel();
            model.title = titles2[i];
            model.icon = getContext().getResources().getDrawable(icons2[i]);
            items2.add(model);
        }

        items3 = new ArrayList<>();
        for (int i = 0 ; i < titles3.length ; i++) {
            CommonItemModel model = new CommonItemModel();
            model.title = titles3[i];
            model.icon = getContext().getResources().getDrawable(icons3[i]);
            items3.add(model);
        }

        gridItems = new ArrayList<>();
        for (int i = 0 ; i < configTitles.length ; i++) {
            CommonItemModel model = new CommonItemModel();
            model.title = configTitles[i];
            model.icon = getContext().getResources().getDrawable(configIcons[i]);
            gridItems.add(model);
        }
    }

//    @CheckLogin
    private void toChatRoomPage() {
        LWRouter.go(getActivity(), RouterConfig.NEWLOGIN);
    }
}
