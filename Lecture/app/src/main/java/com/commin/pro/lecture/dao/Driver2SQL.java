package com.commin.pro.lecture.dao;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.commin.pro.lecture.model.Model2User;
import com.commin.pro.lecture.util.DBException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by user on 2017-08-16.
 */
public class Driver2SQL {


    public static final String TABLA_NAME = "cm_user_table";

    class MyQuery extends AsyncTask<String, Void, ResultSet> {

        @Override
        protected ResultSet doInBackground(String... strings) {
            ResultSet resultSet = null;
           try{
               Driver2SQL driver2SQL = new Driver2SQL();
               Connection conn = driver2SQL.getConnection();
               Statement stmt = conn.createStatement();
               resultSet = stmt.executeQuery(strings[0]);

           }catch (Exception e){
                e.printStackTrace();
           }
            return resultSet;
        }

        @Override
        protected void onPostExecute(ResultSet result) {

        }

    }

    class MyInsert extends AsyncTask<String, Void, String>  {

        @Override
        protected String doInBackground(String... strings) {
            try{
                Driver2SQL driver2SQL = new Driver2SQL();
                Connection conn = driver2SQL.getConnection();
                Statement stmt = conn.createStatement();

                stmt.executeUpdate(strings[0]);

            }catch (Exception e){
                return e.getMessage();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            return;
        }
    }

    public boolean login(Model2User model) throws  Exception{
        String TABLE_NAME = "User";
        String user_id = model.getUser_id();
        String user_password = model.getUser_password();
        try{
            String query = "SELECT user_id,user_password from `knustudy`.`User` where user_id = '"+user_id+"';";
            MyQuery myQuery = new MyQuery();
            ResultSet resultSet = myQuery.execute(query).get();

            if(resultSet.next()){
                if(user_password.equals(resultSet.getString("user_password"))){
                    return true;
                }else{
                    throw new DBException("비밀번호가 잘 못 되었습니다.");
                }
            }else{
                throw new DBException("아이디가 잘 못 되었습니다.");
            }
        }catch (Exception e){
            throw e;
        }

    }

    public void createUser(Model2User model) throws Exception{
        String TABLE_NAME = "User";
        String user_id = model.getUser_id();
        String user_password = model.getUser_password();
        try{
            String query = "INSERT INTO `knustudy`.`User` (`user_id`, `user_password`, `create_date`) VALUES ('"+user_id+"', '"+user_password+"', now());";
            MyInsert insert = new MyInsert();
            String str = insert.execute(query).get();
            if(str != null){
                throw new DBException(str);
            }
        }catch (Exception e){
            throw e;
        }
    }



    public Connection getConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://my5008.gabiadb.com/knustudy", "hopekkt", "hopekkt123");
            Log.d("Driver2SQL", conn + "");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


//    public static boolean confirmDuplication(String column, String target) throws Exception {
//        String result = "";
//        String error = "";
//        boolean isDuplication = false;
//        Map<String, String> response_params = new LinkedHashMap<String, String>();
//        try {
//
//            String query = "SELECT " + column + " FROM " + TABLA_NAME + " WHERE " + column + " = '" + target + "';";
//            Driver2SQL db = new Driver2SQL();
//            db.getConnection();
//            Statement stmt = db.myConn.createStatement();
//            ResultSet resultSet = stmt.executeQuery(query);
//
//            if (resultSet.next()) {
//                isDuplication = true;
//            } else {
//                isDuplication = false;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//
//        return isDuplication;
//    }
//
//
//    public static ResultSet query(String sql) throws Exception {
//        try {
//            Driver2SQL db = new Driver2SQL();
//            db.getConnection();
//            Statement stmt = db.myConn.createStatement();
//
//            return stmt.executeQuery(sql);
//
//        } catch (Exception e) {
//            throw e;
//        }
//    }
//
//    public static String insert(LinkedHashMap<String, String> sql_map) throws Exception {
//        String result = "";
//        String error = "";
//        boolean isSuccess = false;
//        Map<String, String> response_params = new LinkedHashMap<String, String>();
//        try {
//
//            String sql_header = "insert into " + TABLA_NAME + " (create_time,";
//            String sql_footer = "values (now(),";
//
//            Iterator<String> keys = sql_map.keySet().iterator();
//
//            while (keys.hasNext()) {
//                String key = keys.next();
//                if (key.startsWith("att")) {
//                    sql_header += sql_map.get(key);
//                } else {
//                    sql_footer += sql_map.get(key);
//                }
//            }
//            sql_header += ") ";
//
//            sql_footer += ");";
//            String sql = sql_header + sql_footer;
//
//            System.out.println(sql);
//
//            Driver2SQL db = new Driver2SQL();
//            db.getConnection();
//            Statement stmt = db.myConn.createStatement();
//            try {
//                stmt.executeUpdate(sql);
//                isSuccess = true;
//            } catch (Exception e) {
//                isSuccess = false;
//                error = e.getMessage();
//            } finally {
//                response_params.put("action", "createCustomerResult");
//                response_params.put("result", String.valueOf(isSuccess));
//                response_params.put("error", String.valueOf(error));
//
//                System.out.println(result);
//            }
//        } catch (Exception e) {
//            throw e;
//        }
//
//        return result;
//    }


}
