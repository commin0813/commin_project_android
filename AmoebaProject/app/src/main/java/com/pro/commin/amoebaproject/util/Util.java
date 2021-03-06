package com.pro.commin.amoebaproject.util;

import android.net.Uri;

/**
 * Created by K on 2017-11-02.
 */

public class Util {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "cpdemo.db";

    public static final String TAB_NAME = "Users";
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "NAME";
    public static final String COL_EMAIL = "EMAIL";
    public static final String COL_PASSWORD = "PASSWORD";
    public static final String COL_CITY = "CITY";

    public static final Uri USER_URI = Uri.parse("content://com.pro.commin.cpdemogwa.usercontentprovider/"+TAB_NAME);
}
