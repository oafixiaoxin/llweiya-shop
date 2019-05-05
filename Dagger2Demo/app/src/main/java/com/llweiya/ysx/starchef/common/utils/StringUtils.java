package com.llweiya.ysx.starchef.common.utils;

import com.google.gson.Gson;

public final class StringUtils {
    private static final Gson GSON = new Gson();

    public static String toString(Object obj) {
        return GSON.toJson(obj);
    }
}
