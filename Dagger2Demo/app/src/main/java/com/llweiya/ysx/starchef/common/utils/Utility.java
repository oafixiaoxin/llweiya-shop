package com.llweiya.ysx.starchef.common.utils;

import java.util.List;

public class Utility {

    public static boolean listHasElement(List list) {
        if (null == list || list.size() == 0) {
            return false;
        }
        return true;
    }

}
