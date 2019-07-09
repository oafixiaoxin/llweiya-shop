package com.llweiya.ysx.starchef.business.community.view;

import com.llweiya.ysx.starchef.business.community.model.FavoriteItemModel;
import com.llweiya.ysx.starchef.common.view.BaseView;

import java.util.List;

public interface FavoriteView extends BaseView {
    void getDataSuccess(List<FavoriteItemModel> list);
    void getDataFailed(String errorString);
}
