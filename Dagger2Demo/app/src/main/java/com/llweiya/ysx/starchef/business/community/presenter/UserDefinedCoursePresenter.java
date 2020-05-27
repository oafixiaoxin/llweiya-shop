package com.llweiya.ysx.starchef.business.community.presenter;

import com.llweiya.ysx.starchef.business.community.interactor.CommunityInteractor;
import com.llweiya.ysx.starchef.business.community.model.CommentItemViewModel;
import com.llweiya.ysx.starchef.business.community.model.CommunityUserInfoModel;
import com.llweiya.ysx.starchef.business.community.model.MenuItemViewModel;
import com.llweiya.ysx.starchef.business.community.model.UserCourseViewModel;
import com.llweiya.ysx.starchef.business.community.model.UserDefinedCourseViewModel;
import com.llweiya.ysx.starchef.business.community.view.UserDefinedCourseView;
import com.llweiya.ysx.starchef.common.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDefinedCoursePresenter extends BasePresenter<CommunityInteractor, UserDefinedCourseView> {

    public void loadData(int targetId) {
        UserDefinedCourseViewModel viewModel = new UserDefinedCourseViewModel();
        viewModel.courseImage = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590054046194&di=dc41ff489d4691473ec0f51d8bab8081&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20170822%2Fa8c529ac9d4544b4937cf0e7ae0ccc94.jpeg";
        viewModel.content = "尹普美（윤보미，Yoon Bo Mi），1993年8月13日出生于韩国京畿道水原市，韩国女歌手、主持人，女子演唱组合Apink成员。";
        viewModel.lastUpdateTime = 1590489122000L;
        viewModel.commentCount = 88;

        HashMap<String, String> menuMap = new HashMap<>();
        menuMap.put("抹茶粉", "适量");
        menuMap.put("开水", "48g");
        menuMap.put("蛋黄", "3个");
        menuMap.put("植物油", "21g");
        menuMap.put("低筋面粉", "48g");
        menuMap.put("泡打粉", "适量");
        menuMap.put("蛋清", "3个");
        menuMap.put("柠檬汁", "少许");
        menuMap.put("糖", "90g");
        menuMap.put("蜜红豆", "适量");
        menuMap.put("牛奶", "50g");
        menuMap.put("淡奶油", "250g");
        List<MenuItemViewModel> list = new ArrayList<>();
        for (String key : menuMap.keySet()) {
            MenuItemViewModel menu = new MenuItemViewModel();
            menu.name = key;
            menu.property = menuMap.get(key);
            list.add(menu);
        }
        viewModel.menuList = list;

        String[] tags = {"甜品", "抹茶", "奶油"};
        for (int i = 0 ; i < 3 ; i++) {
            viewModel.tagList.add(tags[i]);
        }

        for (int i = 0 ; i < 4 ; i++) {
            CommentItemViewModel comment = new CommentItemViewModel();
            comment.commentId = i + "";
            comment.content = "哎哟，不错哦~";
            comment.createTime = 1590489122000L;
            comment.likeCount = i+1;
            CommunityUserInfoModel userInfo = new CommunityUserInfoModel();
            userInfo.avatarUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590054166891&di=c992547430760b9e5e3996dedec60b26&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201712%2F03%2F20171203122814_LaYrc.jpeg";
            userInfo.userNickname = "金南珠";
            userInfo.userId = i + 100;
            comment.userInfoModel = userInfo;
            viewModel.commentList.add(comment);
        }

        for (int i = 0 ; i < 5 ; i++) {
            UserCourseViewModel userCourse = new UserCourseViewModel();
            userCourse.itemId = (i+1)+"";
            userCourse.title = "舒芙蕾松饼";
            userCourse.likeCount = 22;
            userCourse.imageUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590054046194&di=dc41ff489d4691473ec0f51d8bab8081&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20170822%2Fa8c529ac9d4544b4937cf0e7ae0ccc94.jpeg";
            CommunityUserInfoModel userInfoModel = new CommunityUserInfoModel();
            userInfoModel.userId = 100+i;
            userInfoModel.userNickname = "尹普美的小迷弟";
            userInfoModel.userGender = 1;
            userInfoModel.avatarUrl = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590054166891&di=c992547430760b9e5e3996dedec60b26&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201712%2F03%2F20171203122814_LaYrc.jpeg";
            userCourse.userInfo = userInfoModel;
            viewModel.userRelevant.add(userCourse);
        }

        view.loadDataSuccess(viewModel);
    }

}
