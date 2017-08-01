package com.commin.pro.comminproject.page.login;

import android.util.Log;

import com.commin.pro.comminproject.driver.Driver4Http;
import com.commin.pro.comminproject.model.Model2Login;
import com.commin.pro.comminproject.page.ApplicationProperty;
import com.commin.pro.comminproject.util.UtilParam;

import java.util.Map;

/**
 * Created by user on 2017-07-31.
 */
public class Advisor2Login {

    public Model2Login loginCustomer(String user_id, String user_password) throws Exception {
        try {
            Map<String, String> request_params = UtilParam.createParamMap();
            request_params.put("action", "loginCustomer");
            request_params.put("user_id", user_id);
            request_params.put("user_password", user_password);

            Map<String, Object> response_params = Driver4Http.doPost(ApplicationProperty.SERVER_URL, request_params);
            return Model2Login.convertModel2LoginByJson(response_params);
        } catch (Exception e) {
            throw e;
        }
    }
}
