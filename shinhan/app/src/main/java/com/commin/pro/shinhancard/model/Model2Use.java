package com.commin.pro.shinhancard.model;

import java.io.Serializable;

/**
 * Created by user on 2017-08-08.
 */
public class Model2Use implements Serializable{

    private String use_date; // or 이용일시
    private String use_confirm_date; // or 승인번호
    private String shop_name;
    private String brand;
    private String price;
    private String how_use;
    private String suc_number;
    private String check_date;



    public String getUse_date() {
        return use_date;
    }

    public void setUse_date(String use_date) {
        use_date = use_date.replace("/","-");
        this.use_date = use_date;
    }

    public String getUse_confirm_date() {


        return use_confirm_date;
    }

    public void setUse_confirm_date(String use_confirm_date) {
        use_confirm_date = use_confirm_date.replace("/",".");
        this.use_confirm_date = use_confirm_date;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHow_use() {
        return how_use;
    }

    public void setHow_use(String how_use) {
        this.how_use = how_use;
    }

    public String getSuc_number() {
        return suc_number;
    }

    public void setSuc_number(String suc_number) {
        this.suc_number = suc_number;
    }

    public String getCheck_date() {
        return check_date;
    }

    public void setCheck_date(String check_date) {
        this.check_date = check_date;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }
}
