package com.llweiya.ysx.starchef.business.community.model;

import java.util.List;

public class HomeDiscountAreaViewModel implements IHomeDataType {
    private List<DiscountAreaModel> discountAreaList;

    public List<DiscountAreaModel> getDiscountAreaList() {
        return discountAreaList;
    }

    public void setDiscountAreaList(List<DiscountAreaModel> discountAreaList) {
        this.discountAreaList = discountAreaList;
    }

    @Override
    public int getItemType() {
        return IHomeDataType.HOMT_DATA_TYPE_DISCOUNT_AREA;
    }
}
