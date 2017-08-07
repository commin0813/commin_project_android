package com.commin.pro.comminproject.page;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.commin.pro.comminproject.model.Model2Login;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by user on 2017-07-24.
 */
public class ApplicationProperty {
    public static int PAGER_NUMBER_CONTENT_PAGE = 213213;
    public static int PAGER_NUMBER_MANAGE_PAGE = 346345;

    public static int REQUEST_CODE_LOGIN = 0x0000123;
    public static int REQUEST_CODE_SIGNUP = 0x0000124;

    public static Model2Login model2Login = new Model2Login();

    //////////////////////////////////////////PROTOCOL/////////////////////////////////
    public static final String CLIENT_SERVER_CONNECTION = "CONNECTION!@#%%$";


//    public static final String SERVER_URL = "http://172.168.1.91:8080/test2/commin_mobile.jsp";
    public static final String SERVER_URL = "http://172.168.1.91:8080/commin_project/";

    private static String uuid = "";

    public static String getUUID() {
        return uuid;
    }

    public static String createUUID(Context context) {
        if ("".equals(uuid)) {
            SharedPreferences preferences = context.getSharedPreferences("UUID", Context.MODE_PRIVATE);
            String old_uuid = preferences.getString("UUID", "");
            if ("".equals(old_uuid)) {
                final String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                try {
                    UUID new_uuid = null;
                    if (!"9774d56d682e549c".equals(android_id)) {
                        new_uuid = UUID.nameUUIDFromBytes(android_id.getBytes("utf-8"));
                    } else {
                        final String device_id = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                        new_uuid = device_id != null ? UUID.nameUUIDFromBytes(device_id.getBytes("utf-8")) : UUID.randomUUID();
                    }
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("UUID", new_uuid.toString());
                    editor.commit();
                    uuid = new_uuid.toString();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } else {
                uuid = old_uuid;
            }
        }
        return uuid;
    }
}
