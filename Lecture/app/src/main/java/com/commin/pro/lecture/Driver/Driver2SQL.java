package com.commin.pro.lecture.Driver;


import android.os.AsyncTask;
import android.util.Log;

import com.commin.pro.lecture.model.Model2Course;
import com.commin.pro.lecture.model.Model2LectureTime;
import com.commin.pro.lecture.model.Model2User;

import com.commin.pro.lecture.page.ApplicationProperty;
import com.commin.pro.lecture.util.DBException;
import com.commin.pro.lecture.util.UtilTime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Driver2SQL {
    class MyQuery extends AsyncTask<String, Void, ResultSet> {

        @Override
        protected ResultSet doInBackground(String... strings) {
            ResultSet resultSet = null;
            try {
                Driver2SQL driver2SQL = new Driver2SQL();
                Connection conn = driver2SQL.getConnection();
                if(conn ==null){
                    Log.d("","");
                    throw new DBException("DB서버와의 연결이 좋지않습니다. 다시 시도해주세요");
                }
                Statement stmt = conn.createStatement();
                resultSet = stmt.executeQuery(strings[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultSet;
        }

        @Override
        protected void onPostExecute(ResultSet result) {

        }

    }

    class MyUpdate extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                Driver2SQL driver2SQL = new Driver2SQL();
                Connection conn = driver2SQL.getConnection();
                Statement stmt = conn.createStatement();

                stmt.executeUpdate(strings[0]);

            } catch (Exception e) {
                return e.getMessage();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            return;
        }
    }

    public void deleteLectureData(String course_id,String user_id) throws Exception{
        try{
            String query = "DELETE FROM `knustudy`.`User_Course`  WHERE courseID = '" + course_id + "' AND user_id = '" + user_id + "';";
            MyUpdate delete = new MyUpdate();
            String str = delete.execute(query).get();
            if (str != null) {
                throw new DBException(str);
            }

        }catch (Exception e){
            throw e;
        }
    }

    public void setLectureData(String user_id) throws Exception {
        try {
            String query = "SELECT * FROM `knustudy`.`User_Course` WHERE user_id = '" + user_id + "'";
            MyQuery myQuery = new MyQuery();
            ResultSet set = myQuery.execute(query).get();

            ArrayList<Model2Course> registered_list = ApplicationProperty.getRegisteredList();
            registered_list.clear();
            String[] time_title = ApplicationProperty.time_title;
            while (set.next()) {
                ArrayList<Model2LectureTime> time_items = UtilTime.getTimeValue(set.getString("courseTime"));
                if (time_items.size() > 0) {
                    for (Model2LectureTime time_model : time_items) {
                        for(String time_name : time_model.getLecture_times()){
                            Model2Course model = new Model2Course();
                            model.setCourseCampus(set.getString("courseCampus"));
                            model.setCourseGrade(set.getString("courseGrade"));
                            model.setCourseID(set.getString("courseID"));
                            model.setCourseName(set.getString("courseName"));
                            model.setCourseProfessor(set.getString("courseProfessor"));
                            model.setCourseRoom(set.getString("courseRoom"));
                            model.setCourseTime(set.getString("courseTime"));
                            model.setData(true);
                            model.setLecture(true);

                            model.setDay_name(time_model.getDay());
                            model.setTime_name(time_name);

                            int colum_index = 0;
                            switch (time_model.getDay()) {
                                case "월":
                                    colum_index = 1;
                                    break;
                                case "화":
                                    colum_index = 2;
                                    break;
                                case "수":
                                    colum_index = 3;
                                    break;
                                case "목":
                                    colum_index = 4;
                                    break;
                                case "금":
                                    colum_index = 5;
                                    break;
                                case "토":
                                    colum_index = 6;
                                    break;
                                case "일":
                                    colum_index = 7;
                                    break;
                            }

                            int start_row_index = 0;
                            for(int i = 0 ; i <time_title.length;i++){
                                if(time_title[i].equals(time_name)){
                                    start_row_index = i;
                                    break;
                                }
                            }
                            String id = start_row_index + "+" + colum_index;
                            model.setId(id);

                            registered_list.add(model);
                        }
                    }

                } else {
                    continue;
                }
            }
            ApplicationProperty.setRegisteredList(registered_list);
        } catch (Exception e) {
            throw e;
        }
    }

    public void checkDuplicateCourse(Model2Course model) throws Exception {
        String courseID = model.getCourseID();
        String user_id = ApplicationProperty.model2User.getUser_id();
        try {
            String query = "SELECT * FROM `knustudy`.`User_Course` WHERE courseID = '" + courseID + "' AND user_id = '" + user_id + "'";

            Log.d("Driver2SQL", query);
            MyQuery myQuery = new MyQuery();
            ResultSet set = myQuery.execute(query).get();
            if (set.next()) {
                throw new DBException("이미 등록한 과목입니다.");
            }

        } catch (Exception e) {
            throw e;
        }
    }


    public void insertCourse(Model2Course model) throws Exception {
        try {
            checkDuplicateCourse(model);
        } catch (Exception e) {
            throw e;
        }


        String TABLE_NAME = "User_Course";
        String courseCampus = model.getCourseCampus();
        String courseGrade = model.getCourseGrade();
        String courseID = model.getCourseID();
        String courseName = model.getCourseName();
        String courseProfessor = model.getCourseProfessor();
        String courseRoom = model.getCourseRoom();
        String courseTime = model.getCourseTime();
        String sectionID = model.sectionID;
        String user_id = ApplicationProperty.model2User.getUser_id();

        try {
            String query = "INSERT INTO `knustudy`.`User_Course` " +
                    "(courseCampus, courseGrade, courseID, courseName, courseProfessor, courseRoom, courseTime, sectionID, user_id,create_date) " +
                    "VALUES ('" + courseCampus + "'," +
                    "'" + courseGrade + "'," +
                    "'" + courseID + "'," +
                    "'" + courseName + "'," +
                    "'" + courseProfessor + "'," +
                    "'" + courseRoom + "'," +
                    "'" + courseTime + "'," +
                    "'" + sectionID + "'," +
                    "'" + user_id + "'," +
                    " now());";

            Log.d("Driver2SQL", query);
            MyUpdate insert = new MyUpdate();
            String str = insert.execute(query).get();
            if (str != null) {
                throw new DBException(str);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean login(Model2User model) throws Exception {
        String TABLE_NAME = "User";
        String user_id = model.getUser_id();
        String user_password = model.getUser_password();
        try {
            String query = "SELECT user_id,user_password from `knustudy`.`User` where user_id = '" + user_id + "';";
            MyQuery myQuery = new MyQuery();
            ResultSet resultSet = myQuery.execute(query).get();

            if (resultSet.next()) {
                if (user_password.equals(resultSet.getString("user_password"))) {
                    return true;
                } else {
                    throw new DBException("비밀번호가 잘 못 되었습니다.");
                }
            } else {
                throw new DBException("아이디가 잘 못 되었습니다.");
            }
        } catch (Exception e) {
            throw e;
        }

    }

    public void createUser(Model2User model) throws Exception {
        String TABLE_NAME = "User";
        String user_id = model.getUser_id();
        String user_password = model.getUser_password();
        try {
            String query = "INSERT INTO `knustudy`.`User` (`user_id`, `user_password`, `create_date`) VALUES ('" + user_id + "', '" + user_password + "', now());";
            MyUpdate insert = new MyUpdate();
            String str = insert.execute(query).get();
            if (str != null) {
                throw new DBException(str);
            }
        } catch (Exception e) {
            throw e;
        }
    }


    private Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://my5008.gabiadb.com/knustudy", "hopekkt", "hopekkt123");
            Log.d("Driver2SQL", conn + "");
            try{
                new Thread().sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



}