package commin.pro.diseasemanagement.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;
import java.util.HashMap;

import commin.pro.diseasemanagement.model.Model2Calendar;

/**
 * Created by user on 2017-10-12.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final String TABLE_NAME = "Calendar";

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " date Date,"+
                " shrink INTEGER," +
                " relaxation INTEGER," +
                " text TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(Model2Calendar model) throws Exception{
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES(null, '" + model.getDate() + "'," + model.getShrink() + ", " + model.getRelaxation() + ", '" + model.getText() + "');");
        db.close();
    }

    public HashMap<Date,Model2Calendar> query_all() throws Exception{
        SQLiteDatabase db = getReadableDatabase();
        HashMap<Date,Model2Calendar> result_map = new HashMap<Date,Model2Calendar>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+this.TABLE_NAME, null);
        while(cursor.moveToNext()){
            Model2Calendar model = new Model2Calendar();
            model.setDb_id(cursor.getInt(0));
            model.setDate(new Date(cursor.getString(1)));
            model.setShrink(cursor.getInt(2));
            model.setRelaxation(cursor.getInt(3));
            model.setText();
            result_map.put(model.getDate(),model);
        }
        return result_map;
    }

//    public void update(Model2Calendar model) {
//
//        SQLiteDatabase db = getWritableDatabase();
//        // 입력한 항목과 일치하는 행의 가격 정보 수정
//        db.execSQL("UPDATE " + TABLE_NAME + " SET shrink=" + model.getShrink() + " WHERE _id='" + model.getDb_id() + "';");
//        db.close();
//    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE _id='" + id + "';");
        db.close();
    }


}
