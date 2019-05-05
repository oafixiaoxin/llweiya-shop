package com.llweiya.ysx.starchef.business.user.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.llweiya.ysx.starchef.common.utils.StringUtils;

public class UserInfoManager {
    private static final String CACHE_USER_INFO = "cache_user_info";
    private static final String SHAREDPREFERENCES_CACHE = "shared_preferences";

    //缓存用户信息
    public static void cacheUserInfo(Context context, UserInfoModel userInfoModel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCES_CACHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CACHE_USER_INFO, StringUtils.toString(userInfoModel));
        editor.apply();
    }

    public static UserInfoModel getUserInfoFromCache(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCES_CACHE, Context.MODE_PRIVATE);
        String userInfoStr = sharedPreferences.getString(CACHE_USER_INFO, "");
        if (!TextUtils.isEmpty(userInfoStr)) {
            Gson gson = new Gson();
            return gson.fromJson(userInfoStr, UserInfoModel.class);
        } else {
            return new UserInfoModel();
        }
    }
}