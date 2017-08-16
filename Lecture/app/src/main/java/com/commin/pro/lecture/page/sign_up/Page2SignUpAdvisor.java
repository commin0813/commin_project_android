package com.commin.pro.lecture.page.sign_up;

import com.commin.pro.lecture.dao.Driver2SQL;
import com.commin.pro.lecture.model.Model2User;
import com.mysql.jdbc.Driver;

/**
 * Created by user on 2017-08-16.
 */
public class Page2SignUpAdvisor {
    public String cretaeUser(String user_id,String user_password) throws Exception{
        try{
            Model2User model = new Model2User();
            model.setUser_id(user_id);
            model.setUser_password(user_password);
            Driver2SQL driver = new Driver2SQL();
            driver.createUser(model);
            return null;
        }catch (Exception e){
            throw e;
        }
    }
}
