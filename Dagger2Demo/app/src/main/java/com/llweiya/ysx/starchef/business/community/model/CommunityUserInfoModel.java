package com.llweiya.ysx.starchef.business.community.model;

public class CommunityUserInfoModel {
    public int userId = 0;
    public String userNickname = "";
    public String avatarUrl = "";
    public int userGender = 1;

    public String getUserGender() {
        return this.userGender == 1 ? "男" : "女";
    }
}
