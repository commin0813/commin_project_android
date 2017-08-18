package com.commin.pro.shinhancard.page;

import java.text.DecimalFormat;

/**
 * Created by user on 2017-08-07.
 */
public class ApplicationProperty {

    public static final int REQUEST_CODE_LOGIN = 12321;

    public static boolean isLogin = false;

    public static String toNumFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }
}
