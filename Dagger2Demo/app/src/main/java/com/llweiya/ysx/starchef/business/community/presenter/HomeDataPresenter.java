package com.llweiya.ysx.starchef.business.community.presenter;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractor;
import com.llweiya.ysx.starchef.business.community.model.DiscountAreaModel;
import com.llweiya.ysx.starchef.business.community.model.HomeAddsAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.HomeCategoryAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.HomeDiscountAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.HomeNearbyShopAreaViewModel;
import com.llweiya.ysx.starchef.business.community.model.IHomeDataType;
import com.llweiya.ysx.starchef.business.community.model.IShopType;
import com.llweiya.ysx.starchef.business.community.model.NearbyShopModel;
import com.llweiya.ysx.starchef.business.community.view.HomeDataView;
import com.llweiya.ysx.starchef.common.model.SimpleObjectViewModel;
import com.llweiya.ysx.starchef.common.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomeDataPresenter extends BasePresenter<CommunityInteractor, HomeDataView> {
    private String[] discountImages = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560232981773&di=207bee4697dd3f0a77b7b1e0c2318ec9&imgtype=0&src=http%3A%2F%2Fdingyue.nosdn.127.net%2FWtkteTwypXWcUcVgNNbPflfSjKWQHNk%3DyYtKJo7yYOIqx1536055370416.jpg",
            "http://img1.imgtn.bdimg.com/it/u=560442242,37597139&fm=26&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=248395548,980320538&fm=26&gp=0.jpg"
    };
    private String[] categories = {
            "中餐", "饮品", "快餐", "西餐", "甜品", "超市", "生鲜", "更多"
    };
    private int[] categoryIcons = {
            R.drawable.icon_chinese_food,
            R.drawable.icon_drink,
            R.drawable.icon_west_style_food,
            R.drawable.icon_fast_food,
            R.drawable.icon_desert,
            R.drawable.icon_super_market,
            R.drawable.icon_fresh_food,
            R.drawable.icon_more
    };
    private int[] shopTypes = {0, 2, 3, 1, 5, 4};
    private String[] shopTags = {"环境很好", "服务态度很不错", "会再来吃", "周边环境很好，氛围很好"};

    public void getHomeData() {
        List<IHomeDataType> retList = new ArrayList<>();

        //分类
        HomeCategoryAreaViewModel homeCategoryAreaViewModel = new HomeCategoryAreaViewModel();
        List<SimpleObjectViewModel> categoryList = new ArrayList<>();
        for (int i = 0 ; i < categories.length ; i++) {
            SimpleObjectViewModel objectViewModel = new SimpleObjectViewModel();
            objectViewModel.targetId = String.valueOf(i);
            objectViewModel.targetName = categories[i];
            objectViewModel.targetPictureResourceId = categoryIcons[i];
            categoryList.add(objectViewModel);
        }
        homeCategoryAreaViewModel.setCatagoryList(categoryList);
        retList.add(homeCategoryAreaViewModel);

        //优惠专区
        HomeDiscountAreaViewModel homeDiscountAreaViewModel = new HomeDiscountAreaViewModel();
        List<DiscountAreaModel> discountAreaList = new ArrayList<>();
        for (int i = 0 ; i < 3 ; i++) {
            DiscountAreaModel discountAreaModel = new DiscountAreaModel();
            discountAreaModel.targetId = String.valueOf(i);
            discountAreaModel.pictureUrl = discountImages[0];
            discountAreaList.add(discountAreaModel);
        }
        homeDiscountAreaViewModel.setDiscountAreaList(discountAreaList);
        retList.add(homeDiscountAreaViewModel);

        //广告
        HomeAddsAreaViewModel homeAddsAreaViewModel = new HomeAddsAreaViewModel();
        homeAddsAreaViewModel.addsUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560406965202&di=ccd92eeb21943474cae09a92b7d2f298&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fd4b999cf78e6e75e358842b2a9de14f069779b8d7f36-URP56P_fw658";
        retList.add(homeAddsAreaViewModel);

        //附近店家
        HomeNearbyShopAreaViewModel homeNearbyShopAreaViewModel = new HomeNearbyShopAreaViewModel();
        List<NearbyShopModel> shopList = new ArrayList<>();
        for (int i = 0 ; i < 6 ; i++) {
            NearbyShopModel shopModel = new NearbyShopModel();
            shopModel.shopId = String.valueOf(i);
            shopModel.shopName = "附近店家" + (i+1);
            shopModel.shopScroe = 4;
            shopModel.shopUrl = "";
            shopModel.location = "天河区";
            shopModel.shopType = transformShopType(shopTypes[i]);
            shopModel.shopTags.addAll(Arrays.asList(shopTags));
            shopList.add(shopModel);
        }
        homeNearbyShopAreaViewModel.shopList = shopList;
        retList.add(homeNearbyShopAreaViewModel);

        view.getDataSuccess(retList);
    }

    private String transformShopType(int shopType) {
        String st = "";
        switch (shopType) {
            case 0 :
                st = IShopType.SHOP_TYPE_CANTOON;
                break;
            case 1 :
                st = IShopType.SHOP_TYPE_FAST_FOOD;
                break;
            case 2 :
                st = IShopType.SHOP_TYPE_JANPANSE;
                break;
            case 3 :
                st = IShopType.SHOP_TYPE_WESTERN;
                break;
            case 4 :
                st = IShopType.SHOT_TYPE_SICUAN;
                break;
            case 5 :
                st = IShopType.SHOP_TYPE_WESTERN;
                break;
        }
        return st;
    }
}
