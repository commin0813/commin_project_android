package com.commin.pro.comminproject.model;

import android.util.Log;

import com.commin.pro.comminproject.util.UtilParam;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by user on 2017-07-27.
 */
public class Model2Contents implements Serializable{

    private String title;
    private Date create_time;
    private String user_id;
    private int grp;
    private int seq;
    private int lvl;
    public static Model2Contents convertModel2ContentsByJson(Map<String, Object> response_param) {
        try {
            Model2Contents model2Contents = new Model2Contents();

            Map model2Login_map = (Map) response_param.get("Model2Contents");
            model2Contents.title = UtilParam.convertString(model2Login_map.get("title"));
            model2Contents.create_time = UtilParam.convertDate(model2Login_map.get("create_time"));
            model2Contents.user_id =  UtilParam.convertString(model2Login_map.get("user_id"));
            model2Contents.grp = UtilParam.convertInteger(model2Login_map.get("grp"));
            model2Contents.seq = UtilParam.convertInteger(model2Login_map.get("seq"));
            model2Contents.lvl = UtilParam.convertInteger(model2Login_map.get("lvl"));

            return model2Contents;
        } catch (Exception e) {
            Log.w("Model2Contents",e);
            return null;
        }
    }

    public static ArrayList<Model2Contents> convertArray2ContentsByJson(Map<String, Object> response_param) {
        try {

            Set<String> keys = response_param.keySet();
            for(String key : keys){

            }
            Model2Contents model2Contents = new Model2Contents();

            Map model2Login_map = (Map) response_param.get("Model2Contents");
            model2Contents.title = UtilParam.convertString(model2Login_map.get("title"));
            model2Contents.create_time = UtilParam.convertDate(model2Login_map.get("create_time"));
            model2Contents.user_id =  UtilParam.convertString(model2Login_map.get("user_id"));
            model2Contents.grp = UtilParam.convertInteger(model2Login_map.get("grp"));
            model2Contents.seq = UtilParam.convertInteger(model2Login_map.get("seq"));
            model2Contents.lvl = UtilParam.convertInteger(model2Login_map.get("lvl"));

            return null;
        } catch (Exception e) {
            Log.w("Model2Contents",e);
            return null;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getGrp() {
        return grp;
    }

    public void setGrp(int grp) {
        this.grp = grp;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }
}
