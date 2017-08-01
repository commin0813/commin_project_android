package com.commin.pro.comminproject.page.main;

import com.commin.pro.comminproject.model.Model2Contents;
import com.commin.pro.comminproject.util.UtilParam;

import java.util.Map;

/**
 * Created by user on 2017-07-27.
 */
public class Page2MainAdvisor {
    public Model2Contents queryContents() throws Exception{
        try{
            Map<String, String> request_params = UtilParam.createParamMap();
            request_params.put("action","queryContents");
//            Map<String,Object> response_params = Driver2TCP.doPost(request_params);
            return null;
        }catch(Exception e){
            throw e;
        }

    }
}
