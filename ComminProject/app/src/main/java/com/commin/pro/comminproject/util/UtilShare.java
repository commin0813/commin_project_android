package com.commin.pro.comminproject.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by user on 2016-08-02.
 */
public class UtilShare {
    public final static String LOG_TAG = "UtilShare";


    public final static int INT_TYPE = 0;
    public final static int STRING_TYPE = 1;
    public final static int FLOAT_TYPE = 2;
    public final static int LONG_TYPE = 3;
    public final static int BOOL_TYPE = 4;


    //file_name
    public final static String SAHRE_STATUS = "lecture_settings";


    //file_data_key_name
    public final static String KEY_VALUE_TIME_RESOURCE = "time_resource";
    public final static String KEY_VALUE_DAY_RESOURCE = "day_resource";


    //file_data_defalt_value
    public final static int TIME_RESOURCE_DEFALT_VALUE = 0;
    public final static int DAY_RESOURCE_DEFALT_VALUE = 0;


    public static void savePreferences(SharedPreferences.Editor editor, String key, String value, int type) {


        switch (type) {
            case INT_TYPE:
                editor.putInt(key, Integer.parseInt(value));
                break;
            case STRING_TYPE:
                editor.putString(key, value);
                break;
            case FLOAT_TYPE:
                break;
            case LONG_TYPE:
                editor.putLong(key, Long.valueOf(value));
                break;
            case BOOL_TYPE:
                if (value.equals("true")) {
                    editor.putBoolean(key, true);
                    Log.w("abc", "true");
                } else {
                    editor.putBoolean(key, false);
                    Log.w("abc", "false");
                }
                break;
        }
        Log.w(LOG_TAG, "key : " + key + "\nvalue : " + value);
        editor.commit();


    }

    public static void removePreferences(SharedPreferences.Editor editor, String key) {
        editor.remove(key);
    }


    public static SharedPreferences getSharedPreferences(String file_name, Context context) {
        if (file_name.equals(SAHRE_STATUS)) {
            return context.getSharedPreferences(file_name, Context.MODE_MULTI_PROCESS);
        }

        return context.getSharedPreferences(file_name, Context.MODE_MULTI_PROCESS);
    }

    public static SharedPreferences.Editor getEditor(SharedPreferences sharedPreferences) {

        return sharedPreferences.edit();
    }


}
