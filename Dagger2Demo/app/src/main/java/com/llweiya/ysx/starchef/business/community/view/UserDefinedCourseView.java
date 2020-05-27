package com.llweiya.ysx.starchef.business.community.view;

import com.llweiya.ysx.starchef.business.community.model.UserDefinedCourseViewModel;
import com.llweiya.ysx.starchef.common.view.BaseView;

public interface UserDefinedCourseView extends BaseView {
    void loadDataSuccess(UserDefinedCourseViewModel viewModel);
    void loadFailed(String errorString);
}
