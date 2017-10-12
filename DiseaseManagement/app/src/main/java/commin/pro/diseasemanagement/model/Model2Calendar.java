package commin.pro.diseasemanagement.model;

import android.app.Activity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import commin.pro.diseasemanagement.dao.DBHelper;

/**
 * Created by user on 2017-10-12.
 */

public class Model2Calendar implements Serializable {
    private Date date;
    private int shrink; // 수축
    private int relaxation; // 이완
    private String text; // 정상 or 위험

    private int db_id;

    public int getShrink() {
        return shrink;
    }

    public void setShrink(int shrink) {
        this.shrink = shrink;
    }

    public int getRelaxation() {
        return relaxation;
    }

    public void setRelaxation(int relaxation) {
        this.relaxation = relaxation;
    }

    public String getText() {
        return text;
    }

    public void setText() {
        if (this.shrink < 120 && this.relaxation < 90) {
            this.text = "정상";
        } else {
            this.text = "비정상";
        }
    }


    public int getDb_id() {
        return db_id;
    }

    public void setDb_id(int db_id) {
        this.db_id = db_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static HashMap<Date, Model2Calendar> getHashMap(Activity activity) throws Exception {
        final DBHelper dbHelper = new DBHelper(activity, "disease_management2.db", null, 1);
        return dbHelper.query_all();
    }
}
