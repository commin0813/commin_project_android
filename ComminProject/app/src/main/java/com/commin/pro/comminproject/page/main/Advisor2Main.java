package com.commin.pro.comminproject.page.main;

import com.commin.pro.comminproject.driver.Driver4Http;
import com.commin.pro.comminproject.model.Model2Contents;
import com.commin.pro.comminproject.page.ApplicationProperty;
import com.commin.pro.comminproject.util.UtilParam;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by user on 2017-08-01.
 */
public class Advisor2Main{
    public Model2Contents queryContent() throws Exception{
        try{
            Map<String,String> request_params = UtilParam.createParamMap();
            request_params.put("action","queryContent");
            Map<String, Object> response_params = Driver4Http.doPost(ApplicationProperty.SERVER_URL, request_params);
            Model2Contents model = Model2Contents.convertModel2ContentsByJson(response_params);
            return model;
        }catch (Exception e){
            throw e;
        }
    }

    public ArrayList<Model2Contents> queryContents_comment() throws  Exception {
        try{
            Map<String,String> request_params = UtilParam.createParamMap();
            request_params.put("action","queryContents_comment");
            Map<String, Object> response_params = Driver4Http.doPost(ApplicationProperty.SERVER_URL, request_params);
            ArrayList<Model2Contents> array = Model2Contents.convertArray2ContentsByJson(response_params);

        }catch(Exception e){
            throw e;
        }
    }
}

