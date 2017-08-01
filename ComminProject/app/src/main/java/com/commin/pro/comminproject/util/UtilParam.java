package com.commin.pro.comminproject.util;

import com.commin.pro.comminproject.page.ApplicationProperty;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by user on 2016-01-11.
 */
public class UtilParam {

    public static Map<String, String> createParamMap() {
        Map<String, String> params = new LinkedHashMap<String, String>();
        params.put("version", "0.1.0");
        params.put("uuid", ApplicationProperty.getUUID());
        return params;
    }

    public static Double convertDouble(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return Double.parseDouble(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer convertInteger(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long convertLong(Object value) {
        if (value == null) {
            return null;
        }
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String convertString(Object value) {
        if (value != null) {
            return value.toString();
        }
        return null;
    }

    public static Date convertDate(Object value) {
        Long date = convertLong(value);
        if (date == null) {
            return null;
        }
        return new Date(date);
    }

    public static Boolean convertBoolean(Object value) {
        if (value == null || !("true".equalsIgnoreCase(value.toString())) || "false".equalsIgnoreCase(value.toString())) {
            return false;
        }
        return Boolean.parseBoolean(value.toString());
    }

    public static String toNumFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }

    public static String toTimeFormat(int num) {
        String result_num = String.format("%04d", num);
        StringBuffer stringBuffer = new StringBuffer(result_num);
        stringBuffer.insert(2, ":");
        return stringBuffer.toString();
    }
}
