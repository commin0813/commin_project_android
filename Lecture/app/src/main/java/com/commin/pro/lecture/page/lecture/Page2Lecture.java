package com.commin.pro.lecture.page.lecture;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.commin.pro.lecture.R;
import com.commin.pro.lecture.page.ApplicationProperty;
import com.commin.pro.lecture.page.lecture_edit.Page2LectureEdit;
import com.commin.pro.lecture.page.lecture_search.Page2LectureSearch;
import com.commin.pro.lecture.page.login.Page2Login;
import com.commin.pro.lecture.util.UtilDialog;
import com.commin.pro.lecture.util.UtilShare;

import java.util.ArrayList;

public class Page2Lecture extends AppCompatActivity {
    private static final String LOG_TAG = "Page2Lecture";

    private GridView gv_day;
    private Adapter2GridDay adapter2GridDay;
    private ArrayList<String> day_item;

    private int device_width;
    private int device_height;
    private int NumColum;
    private int NumRow;

    public SharedPreferences.Editor editor;
    public SharedPreferences sharedPreferences;
    public boolean isLongClick = false;

    private ImageView iv_button_add_lecture;
    private ImageView iv_button_edit_lecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ApplicationProperty.isLogined) {
            setContentView(R.layout.activity_lecture_layout);
            setDeviceWidthHeight();//핸드폰의 가로 세로 길이를 구합니다.
            loadPreferences();
            createGUI();
            init_listener();
        } else {
            startActivity(new Intent(Page2Lecture.this, Page2Login.class));
            finish();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!ApplicationProperty.isLogined) {
            startActivity(new Intent(Page2Lecture.this, Page2Login.class));
            finish();
        }
    }

    private void init_listener() {
        iv_button_add_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Page2Lecture.this, Page2LectureSearch.class), ApplicationProperty.REQUEST_CODE_FOR_LECTURE_SEARCH);
            }
        });

        iv_button_edit_lecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Page2Lecture.this, Page2LectureEdit.class), ApplicationProperty.REQUEST_CODE_FOR_LECTURE_EDIT);
            }
        });

    }

    private void queryDataGrid() {
        day_item.clear();

        adapter2GridDay = new Adapter2GridDay(Page2Lecture.this, R.layout.item_gird_day, day_item);
        gv_day.invalidateViews();//혹시나하는마음에 GridView 청소
        gv_day.setAdapter(adapter2GridDay);

        //day setting - 마지막 요일 구하기
        int day_resource = sharedPreferences.getInt(UtilShare.KEY_VALUE_DAY_RESOURCE, R.array.days_7);
        String[] arr_string_day = getResources().getStringArray(day_resource);

        //Day 그리드뷰영역을 Setting 합니다.
        day_item.add("");
        for (int i = 0; i < arr_string_day.length; i++) {
            day_item.add(arr_string_day[i]);
        }
        NumColum = day_item.size();
        gv_day.setColumnWidth(device_width / NumColum);
        adapter2GridDay.notifyDataSetChanged();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            queryDataGrid();
        } else if (resultCode == ApplicationProperty.RESULT_LOGOUT) {



            startActivity(new Intent(Page2Lecture.this, Page2Login.class));
            finish();
        } else {
            UtilDialog.showToast(Page2Lecture.this, "취소 되었습니다.");
        }
    }

    private void loadPreferences() {
        sharedPreferences = UtilShare.getSharedPreferences(UtilShare.SAHRE_STATUS, Page2Lecture.this);
        editor = UtilShare.getEditor(sharedPreferences);
    }


    private void createGUI() {
        iv_button_add_lecture = (ImageView) findViewById(R.id.iv_button_add_lecture);
        iv_button_edit_lecture = (ImageView) findViewById(R.id.iv_button_edit_lecture);
        day_item = new ArrayList<String>();
        gv_day = (GridView) findViewById(R.id.gv_day);

        queryDataGrid();
    }

    private void setDeviceWidthHeight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        device_width = displayMetrics.widthPixels;// 가로
        device_height = displayMetrics.heightPixels;// 세로
    }


    private long backKeyPressedTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            UtilDialog.showToast(Page2Lecture.this, "한번 더 클릭하시면 종료됩니다.");
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
        }
    }
}
