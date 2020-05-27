package com.llweiya.ysx.starchef.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatUtil {
    private static final String FMT_GMT_TIME = "yyyy-MM-dd HH:mm:ss.SSS Z";
    private static final String FMT_YYYY_MM_DD = "yyyy-MM-dd";
    private static final String FMT_YYYY_MM_DD_DOT = "yyyy.MM.dd";
    private static final String FMT_YYYY_MM_DD_HH_M_DOT = "yyyy.MM.dd HH:mm";
    private static final String FMT_MM_DD = "MM-dd";
    private static final String FMT_HH_MM = "HH:mm";
    private static final String FMT_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    private static final String FMT_MM_DD_HH_MM = "MM-dd HH:mm";
    private static final String FMT_MM_DD_ZH = "M月d日";
    private static final String FMT_MM_DD_E = "MM月dd日 E";
    private static final String FMT_YYYY_MM_DD_ZH = "yyyy年MM月dd日";
    private static final String FMT_YYYY_MM_DD_E = "yyyy年MM月dd日 E";
    private static final String FMT_YYYY_MM_DD_HH_MM_SS = "yyyy/MM/dd HH:mm:ss";

    private static final SimpleDateFormat GMT_DATE_FMT = new SimpleDateFormat(FMT_GMT_TIME);
    private static final SimpleDateFormat YYYY_MM_DD_FMT = new SimpleDateFormat(FMT_YYYY_MM_DD);
    private static final SimpleDateFormat YYYY_MM_DD_DOT_FMT = new SimpleDateFormat(FMT_YYYY_MM_DD_DOT);
    private static final SimpleDateFormat YYYY_MM_DD_HH_M_DOT_FMT = new SimpleDateFormat(FMT_YYYY_MM_DD_HH_M_DOT);
    private static final SimpleDateFormat MM_DD_FMT = new SimpleDateFormat(FMT_MM_DD);
    private static final SimpleDateFormat KK_MM_FMT = new SimpleDateFormat(FMT_HH_MM);
    private static final SimpleDateFormat YYYY_MM_DD_KK_MM_FMT = new SimpleDateFormat(FMT_YYYY_MM_DD_HH_MM);
    private static final SimpleDateFormat MM_DD_KK_MM_FMT = new SimpleDateFormat(FMT_MM_DD_HH_MM);
    private static final SimpleDateFormat MM_DD_ZH = new SimpleDateFormat(FMT_MM_DD_ZH);
    private static final SimpleDateFormat MM_DD_E = new SimpleDateFormat(FMT_MM_DD_E);
    private static final SimpleDateFormat YYYY_MM_DD_ZH_FMT = new SimpleDateFormat(FMT_YYYY_MM_DD_ZH);
    private static final SimpleDateFormat YYYY_MM_DD_E = new SimpleDateFormat(FMT_YYYY_MM_DD_E);
    private static final SimpleDateFormat YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat(FMT_YYYY_MM_DD_HH_MM_SS);
    private static final ThreadLocal<SimpleDateFormat> T_GMT_DATE_FMT = new ThreadLocal<SimpleDateFormat>();
    private static final long CONST_WEEK = 3600 * 1000 * 24 * 7;

    public static String formatToMD(Date date) {
        return MM_DD_FMT.format(date);
    }

    public static String formatToMDText(Date date) {
        return MM_DD_ZH.format(date);
    }
    public static String formatToYMDText(Date date) {
        return YYYY_MM_DD_ZH_FMT.format(date);
    }

    public static String formatToMS(Date date) {
        return KK_MM_FMT.format(date);
    }

    public static String formatToMDMS(Date date) {
        return MM_DD_KK_MM_FMT.format(date);
    }

    public static String formatToHM(Date date) {
        return KK_MM_FMT.format(date);
    }

    public static String formatToYMDHSS(Date date) {
        return YYYY_MM_DD_KK_MM_FMT.format(date);
    }

    public static String formatToYYYYHMDD(Date date) {
        return YYYY_MM_DD_FMT.format(date);
    }

    public static String formatToYYYYHMDDDot(Date date) {
        return YYYY_MM_DD_DOT_FMT.format(date);
    }

    public static String formatToYYYYHMDDHHMDot(Date date) {
        return YYYY_MM_DD_HH_M_DOT_FMT.format(date);
    }

    public static String formatToYMDHMS(Date date){ return YYYY_MM_DD_HH_MM_SS.format(date); }

    public static String formatAll(Date date) { return GMT_DATE_FMT.format(date); }
}
