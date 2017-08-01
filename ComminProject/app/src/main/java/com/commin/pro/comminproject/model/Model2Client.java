package com.commin.pro.comminproject.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * Created by user on 2017-07-27.
 */
public class Model2Client implements Serializable {

    public static final String SERVER_IP = "172.168.1.91";
    public static final int SERVER_PORT = 9999;

    private String uuid;
    private String client_msg;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getClient_msg() {
        return client_msg;
    }

    public void setClient_msg(String client_msg) {
        this.client_msg = client_msg;
    }

    public static Map<String,Object> convertToModel2Client(){
        return null;
    }
}
