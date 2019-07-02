package com.llweiya.ysx.starchef.business.community.model;

import java.util.ArrayList;
import java.util.List;

public class HomeNearbyShopAreaViewModel implements IHomeDataType {
    public List<NearbyShopModel> shopList = new ArrayList<>();

    @Override
    public int getItemType() {
        return IHomeDataType.HOMT_DATA_TYPE_NEARBY_SHOP;
    }
}
