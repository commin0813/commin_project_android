package com.commin.pro.lecture.model;

import java.util.ArrayList;

/*********************
 * 조금더 쉽게 비교를 하기위해서 만든 모델입니다.
 * 월1A,1B화1A,1B 식으로 되어있는 데이터를 요일과 시간리스트로 만들어서 관리합니다.
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
