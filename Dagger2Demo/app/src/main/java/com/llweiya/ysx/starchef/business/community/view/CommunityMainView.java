package com.llweiya.ysx.starchef.business.community.view;

import com.llweiya.ysx.starchef.business.community.model.ICommunitySectionType;
import com.llweiya.ysx.starchef.common.view.BaseView;

import java.util.List;

public interface CommunityMainView extends BaseView {
    void receiveData(List<ICommunitySectionType> list);
}
