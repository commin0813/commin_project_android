package com.commin.pro.lecture.page.lecture;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.commin.pro.lecture.R;
import com.commin.pro.lecture.model.Model2Course;
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
    private GridView gv_content;

    private Adapter2GridDay adapter2GridDay;
    private Adapter2GridContent adapter2GridContent;

    private ArrayList<String> day_item;
    private ArrayList<Model2Course> content_item;

    private int device_width;
    private int device_height;
    private int NumColum;
    private int NumRow;

    public SharedPreferences.Editor editor;
    public SharedPreferences sharedPreferences;
    public boolean isLongClick = false;

    private ImageView iv_button_add_lecture;
    private ImageView iv_button_edit_lecture;

    private Page2LectureAdvisor advisor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ApplicationProperty.isLogined) {
            setContentView(R.layout.activity_lecture_layout);
            setDeviceWidthHeight();//핸드폰의 가로 세로 길이를 구합니다.
            loadPreferences();
            advisor = new Page2LectureAdvisor();
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
        }else {


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

    private void createGUI() {
        iv_button_add_lecture = (ImageView) findViewById(R.id.iv_button_add_lecture);
        iv_button_edit_lecture = (ImageView) findViewById(R.id.iv_button_edit_lecture);
        day_item = new ArrayList<String>();
        content_item = new ArrayList<Model2Course>();
        gv_day = (GridView) findViewById(R.id.gv_day);
        gv_content = (GridView)findViewById(R.id.gv_content);

        queryDataGrid();
    }

    private void queryDataGrid() {
        day_item.clear();
        content_item.clear();


        adapter2GridDay = new Adapter2GridDay(Page2Lecture.this, R.layout.item_gird_day, day_item);
        gv_day.invalidateViews();//혹시나하는마음에 GridView 청소
        gv_day.setAdapter(adapter2GridDay);

        //위와 동일
        adapter2GridContent = new Adapter2GridContent(Page2Lecture.this, R.layout.item_grid_content, gv_content, content_item);
        gv_content.invalidateViews();
        gv_content.setAdapter(adapter2GridContent);

        ArrayList<Model2Course> items = null;
        try{
            advisor.getLectureData(ApplicationProperty.model2User.getUser_id());
        }catch (Exception e){
            UtilDialog.openError(Page2Lecture.this, e.getMessage(), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });
        }

        items = ApplicationProperty.getRegisteredList();
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


        String[] arr_string_time = getResources().getStringArray(R.array.time);
        NumRow = arr_string_time.length;
        gv_content.setNumColumns(NumColum);
        gv_content.setColumnWidth(device_width / NumColum);

        int cnt = 0;
        int row_index = 0;
        int colum_index = 0;
        Model2Course model;
        for (int i = 0; i < NumColum * NumRow; i++) {
            model = new Model2Course();
            model.setRow_index(row_index);
            if (i % NumColum == 0) {
                model.setData(false);
                model.setName_value(arr_string_time[cnt]);
                if (cnt >= 1) {
                    row_index++;
                }
                cnt++;
            } else {
                model.setData(true);
                model.setName_value("");
            }
            if (colum_index >= NumColum) {
                colum_index = 0;
            }
            model.setColumn_index(colum_index);
            model.setId(row_index + "+" + colum_index);

            if (items != null) {
                for (Model2Course mo : items) {
                    if (model.getId().equals(mo.getId())) {
                        model = mo;
                    }
                }
            }
            colum_index++;
            content_item.add(model);
        }



        adapter2GridContent.notifyDataSetChanged();
        adapter2GridContent.notifyDataSetInvalidated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryDataGrid();
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
