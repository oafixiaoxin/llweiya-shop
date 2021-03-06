package com.llweiya.ysx.starchef.business.user.model;

import com.llweiya.ysx.starchef.common.utils.StringUtils;

public class UserInfoModel {
    private int userId;
    private String userName;
    private String nickname;
    private String userDescription;
    private int sex;
    private String birthday;

    private static UserInfoModel userInfo = null;

    public static UserInfoModel getInstance(){
        if (null == userInfo) {
            synchronized (UserInfoModel.class) {
                if (null == userInfo) {
                    userInfo = new UserInfoModel();
                }
            }
        }
        return userInfo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex == 1 ? "男" : "女";
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

    public void clearUserInfo() {
        this.userId = 0;
        this.userName = "";
        this.nickname = "";
        this.sex = 0;
        this.birthday = "";
        this.userDescription = "";
    }

    public boolean isGuest() {
        return userId <= 0;
    }
}
