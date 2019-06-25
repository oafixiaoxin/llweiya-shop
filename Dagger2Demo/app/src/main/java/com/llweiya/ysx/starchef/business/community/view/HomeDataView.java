package com.llweiya.ysx.starchef.business.community.view;

import com.llweiya.ysx.starchef.business.community.model.IHomeDataType;
import com.llweiya.ysx.starchef.common.view.BaseView;

import java.util.List;

public interface HomeDataView extends BaseView {
    void getDataSuccess(List<IHomeDataType> list);
    void getDataFailed(String errorString);
}
