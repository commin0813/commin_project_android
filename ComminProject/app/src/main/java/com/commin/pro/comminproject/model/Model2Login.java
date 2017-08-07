package com.commin.pro.comminproject.model;

import com.commin.pro.comminproject.util.UtilParam;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Created by user on 2017-08-01.
 */
public class Model2Login implements Serializable {

    private String user_id;
    private String user_password;
    private boolean isLogined;
    private String user_uuid;

    private boolean id;
    private boolean password;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public boolean isLogined() {
        return isLogined;
    }

    public void setLogined(boolean logined) {
        isLogined = logined;
    }

    public String getUser_uuid() {
        return user_uuid;
    }

    public void setUser_uuid(String user_uuid) {
        this.user_uuid = user_uuid;
    }


    public static Model2Login convertModel2LoginByJson(Map<String, Object> response_param) {
        try {
            Model2Login model2Login = new Model2Login();

            Map model2Login_map = (Map) response_param.get("Model2Login");
            model2Login.user_uuid = UtilParam.convertString(model2Login_map.get("uuid"));
            model2Login.user_id = UtilParam.convertString(model2Login_map.get("user_id"));
            model2Login.isLogined =  UtilParam.convertBoolean(model2Login_map.get("logined"));
            model2Login.user_password = UtilParam.convertString(model2Login_map.get("user_password"));
            model2Login.id = UtilParam.convertBoolean(model2Login_map.get("id"));
            model2Login.password = UtilParam.convertBoolean(model2Login_map.get("password"));

            return model2Login;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

    public boolean isPassword() {
        return password;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }
}
