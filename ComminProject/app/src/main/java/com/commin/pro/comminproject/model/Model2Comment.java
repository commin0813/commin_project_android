package com.commin.pro.comminproject.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by user on 2017-07-26.
 */
public class Model2Comment implements Serializable {

    private static final String LOG_TAG = "Model2Comment";

    private String user_name;
    private String comment_contents;
    private int comment_private_num;
    private int device_id;
    private Date create_time;

    private Map<Long, Model2Comment> comment_map = new TreeMap<Long, Model2Comment>();


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_contents() {
        return comment_contents;
    }

    public void setComment_contents(String comment_contents) {
        this.comment_contents = comment_contents;
    }

    public int getComment_private_num() {
        return comment_private_num;
    }

    public void setComment_private_num(int comment_private_num) {
        this.comment_private_num = comment_private_num;
    }

    public int getDevice_id() {
        return device_id;
    }

    public void setDevice_id(int device_id) {
        this.device_id = device_id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
