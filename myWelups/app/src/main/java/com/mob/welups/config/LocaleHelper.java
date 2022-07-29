package com.mob.welups.config;

public class LocaleHelper {
    public final static String LOCALE_VI_VN = "vi";
    public final static String LOCALE_EN = "en";
    public static String language = LOCALE_VI_VN;
    private static LocaleHelper instance = new LocaleHelper();

    public static LocaleHelper getInstance() {
        return instance;
    }
}
