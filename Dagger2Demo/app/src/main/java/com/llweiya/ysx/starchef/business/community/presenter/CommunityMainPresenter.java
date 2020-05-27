package com.llweiya.ysx.starchef.business.community.presenter;

import com.llweiya.ysx.starchef.R;
import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractor;
import com.llweiya.ysx.starchef.business.community.model.CommunityMainCourseModel;
import com.llweiya.ysx.starchef.business.community.model.CommunityMainSectionModel;
import com.llweiya.ysx.starchef.business.community.model.CommunityUserInfoModel;
import com.llweiya.ysx.starchef.business.community.model.ICommunitySectionType;
import com.llweiya.ysx.starchef.business.community.model.UserCourseViewModel;
import com.llweiya.ysx.starchef.business.community.view.CommunityMainView;
import com.llweiya.ysx.starchef.common.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class CommunityMainPresenter extends BasePresenter<CommunityInteractor, CommunityMainView> {

    public void loadData() {
        List<ICommunitySectionType> retList = new ArrayList<>();
        retList.add(new CommunityMainSectionModel());

        CommunityMainCourseModel mainCourseModel = new CommunityMainCourseModel();
        mainCourseModel.title = currentContext().getResources().getString(R.string.text_recommend_course);
        mainCourseModel.totalCount = 7;
        List<UserCourseViewModel> userCourseList = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++) {
            UserCourseViewModel viewModel = new UserCourseViewModel();
            viewModel.itemId = (i+1)+"";
            viewModel.title = "舒芙蕾松饼";
            viewModel.likeCount = 22;
            viewModel.imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590054046194&di=dc41ff489d4691473ec0f51d8bab8081&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20170822%2Fa8c529ac9d4544b4937cf0e7ae0ccc94.jpeg";
            CommunityUserInfoModel userInfoModel = new CommunityUserInfoModel();
            userInfoModel.userId = 100+i;
            userInfoModel.userNickname = "尹普美的小迷弟";
            userInfoModel.userGender = 1;
            userInfoModel.avatarUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590054166891&di=c992547430760b9e5e3996dedec60b26&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201712%2F03%2F20171203122814_LaYrc.jpeg";
            viewModel.userInfo = userInfoModel;
            userCourseList.add(viewModel);
        }
        mainCourseModel.userCourseList = userCourseList;
        retList.add(mainCourseModel);

        view.receiveData(retList);
    }

}
