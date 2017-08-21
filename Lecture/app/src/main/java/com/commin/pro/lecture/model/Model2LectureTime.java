package com.commin.pro.lecture.model;

import java.util.ArrayList;

/**
 * Created by user on 2017-08-21.
 */
public class Model2LectureTime {
    private String day;
    private ArrayList<String> lecture_times = new ArrayList<String>();


    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<String> getLecture_times() {
        return lecture_times;
    }

    public void setLecture_times(ArrayList<String> lecture_times) {
        this.lecture_times = lecture_times;
    }

    public void addLecture_times(String lecture_time){
        this.lecture_times.add(lecture_time);
    }
}
