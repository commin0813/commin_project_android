package com.commin.pro.lecture.page.login;

import com.commin.pro.lecture.Driver.Driver2SQL;
import com.commin.pro.lecture.model.Model2User;

public class Page2LoginAdvisor {
    public boolean login(String user_id,String user_password) throws Exception{
        try{
            Model2User model = new Model2User();
            model.setUser_id(user_id);
            model.setUser_password(user_password);
            Driver2SQL driver = new Driver2SQL();
            return driver.login(model);
        }catch (Exception e){
            throw e;
        }
    }
}
