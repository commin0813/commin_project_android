package com.commin.pro.lecture.page.sign_up;

import com.commin.pro.lecture.Driver.Driver2SQL;
import com.commin.pro.lecture.model.Model2User;

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