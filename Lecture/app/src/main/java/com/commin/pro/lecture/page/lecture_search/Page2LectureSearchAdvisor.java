package com.commin.pro.lecture.page.lecture_search;


import com.commin.pro.lecture.Driver.Driver2SQL;
import com.commin.pro.lecture.model.Model2Course;
import com.commin.pro.lecture.model.Model2User;

public class Page2LectureSearchAdvisor {
    public void insertCourse(Model2Course item) throws Exception{
        try{
            Model2Course model = item;
            Driver2SQL driver = new Driver2SQL();
            driver.insertCourse(model);
            return;
        }catch (Exception e){
            throw e;
        }
    }
}
