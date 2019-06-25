package com.llweiya.ysx.starchef.business.community.model;

import com.llweiya.ysx.starchef.common.model.SimpleObjectViewModel;

import java.util.List;

public class HomeCategoryAreaViewModel implements IHomeDataType {
    private List<SimpleObjectViewModel> catagoryList;

    public List<SimpleObjectViewModel> getCatagoryList() {
        return catagoryList;
    }

    public void setCatagoryList(List<SimpleObjectViewModel> catagoryList) {
        this.catagoryList = catagoryList;
    }

    @Override
    public int getItemType() {
        return IHomeDataType.HOMT_DATA_TYPE_CATEGORY;
    }
}
