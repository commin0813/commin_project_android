package com.pro.commin.cpdemogwa;

/**
 * Created by K on 2017-11-02.
 */

//BEAN or Model or POJO
public class User {
    int id;
    String name;
    String email;
    String password;
    String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User(){

    }

    public User(int id, String name, String email, String password, String city) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.city = city;
    }
}
