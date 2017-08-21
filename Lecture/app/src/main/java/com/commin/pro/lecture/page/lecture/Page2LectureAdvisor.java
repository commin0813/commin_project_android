package com.commin.pro.lecture.page.lecture;

import com.commin.pro.lecture.Driver.Driver2SQL;

/**
 * Created by user on 2017-08-21.
 */
public class Page2LectureAdvisor {
    public void getLectureData(String user_id) throws Exception{
        try{
            Driver2SQL driver = new Driver2SQL();
            driver.setLectureData(user_id);
            return;
        }catch (Exception e){
            throw e;
        }
    }

}
