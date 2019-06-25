package com.llweiya.ysx.starchef.business.community.model;

public class HomeAddsAreaViewModel implements IHomeDataType {
    public String addsUrl = "";
    @Override
    public int getItemType() {
        return IHomeDataType.HOMT_DATA_TYPE_ADS;
    }
}
