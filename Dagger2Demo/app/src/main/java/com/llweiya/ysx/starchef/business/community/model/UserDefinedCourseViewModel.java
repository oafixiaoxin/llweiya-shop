package com.llweiya.ysx.starchef.business.community.model;

import java.util.ArrayList;
import java.util.List;

public class UserDefinedCourseViewModel {
    public CommunityUserInfoModel userInfoModel;
    public String courseImage = "";
    public String content = "";
    public long lastUpdateTime = 0;
    public int commentCount = 0;
    public List<String> tagList = new ArrayList<>();
    public List<MenuItemViewModel> menuList = new ArrayList<>();
    public List<CommentItemViewModel> commentList = new ArrayList<>();
    public List<UserCourseViewModel> userRelevant = new ArrayList<>();
}
