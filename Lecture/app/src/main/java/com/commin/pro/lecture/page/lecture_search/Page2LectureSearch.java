package com.commin.pro.lecture.page.lecture_search;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.commin.pro.lecture.R;
import com.commin.pro.lecture.model.Model2Course;
import com.commin.pro.lecture.util.ProgressGenerator;
import com.commin.pro.lecture.util.UtilCustomDialog;
import com.commin.pro.lecture.util.UtilDialog;
import com.commin.pro.lecture.widget.DialogProgress;
import com.dd.processbutton.iml.ActionProcessButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Page2LectureSearch extends AppCompatActivity {
    public static String LOG_TAG = "Page2LectureSearch";
    private static String url = "http://my.knu.ac.kr/stpo/stpo/cour/listLectPln/list.action?search_open_yr_trm=20172";
    private String section_id = "search_open_yr_trm=20172";

    private RadioGroup rdg_search, rdg_campus;
    private EditText ed_search_word;
    private Button btn_query_data;
    private ArrayList<Model2Course> courses = null;
    private ListView lst_search_result = null;
    private ArrayAdapter2LectureSearch adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_search_layout);

        init();
    }

    private void init() {
        btn_query_data = (Button) findViewById(R.id.btn_query_data);

        btn_query_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    btn_query_data.setEnabled(false);
                    if (courses == null) {
                        courses = new GetSiteInfo(Page2LectureSearch.this).execute().get();
                    }

                    if (courses != null) {
                        createData(courses);
                        btn_query_data.setEnabled(true);
                    }

                } catch (Exception e) {
                    UtilDialog.openError(Page2LectureSearch.this, e.getMessage(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            return;
                        }
                    });
                }
            }
        });

        ed_search_word = (EditText) findViewById(R.id.ed_search_word);
        rdg_search = (RadioGroup) findViewById(R.id.rdg_search);
        rdg_campus = (RadioGroup) findViewById(R.id.rdg_campus);


        lst_search_result = (ListView) findViewById(R.id.lst_search_result);
        lst_search_result.setDivider(null);


    }

    private ArrayList<Model2Course> getContainItem(ArrayList<Model2Course> courses, String search_text) {
        int setting_search_id = rdg_search.getCheckedRadioButtonId();
        ArrayList<Model2Course> imsi_list = new ArrayList<Model2Course>();

        switch (setting_search_id) {
            case R.id.rd_search_pro: { // 교수이름
                for (Model2Course model : courses) {
                    String name = model.getCourseProfessor();
                    if (name == null) {
                        continue;
                    }
                    if (name.equals(search_text) || name.contains(search_text)) {
                        imsi_list.add(model);
                    }
                }
                break;
            }
            case R.id.rd_search_department: { //학과이름
                for (Model2Course model : courses) {
                    String name = model.getCourseMajor();
                    if (name == null) {
                        continue;
                    }
                    if (name.equals(search_text) || name.contains(search_text)) {
                        imsi_list.add(model);
                    }
                }
                break;
            }
            case R.id.rd_search_subject: { // 과목이름
                for (Model2Course model : courses) {
                    String name = model.getCourseName();
                    if (name == null) {
                        continue;
                    }
                    if (name.equals(search_text) || name.contains(search_text)) {
                        imsi_list.add(model);
                    }
                }
                break;
            }

        }

        return imsi_list;
    }

    private void createData(ArrayList<Model2Course> courses) throws Exception {
        int setting_campus_id = rdg_campus.getCheckedRadioButtonId();
        ArrayList<Model2Course> imsi_list = new ArrayList<Model2Course>();

        String search_text = "";
        search_text = ed_search_word.getText().toString();
        switch (setting_campus_id) {
            case R.id.rd_campus_total: { //전체
                if (search_text.equals("")) {
                    imsi_list = courses;
                } else {
                    imsi_list = getContainItem(courses, search_text);
                }

                break;
            }
            case R.id.rd_campus_D: { // 대구캠퍼스
                for (Model2Course model : courses) {
                    String name = model.getCourseCampus();
                    if (name == null) {
                        continue;
                    }
                    if (name.equals("대구캠퍼스") || name.contains("대구")) {
                        imsi_list.add(model);
                    }
                }

                if (search_text.equals("")) {

                } else {
                    imsi_list = getContainItem(imsi_list, search_text);
                }

                break;
            }
            case R.id.rd_campus_S: { // 상주캠퍼스
                for (Model2Course model : courses) {
                    String name = model.getCourseCampus();
                    if (name == null) {
                        continue;
                    }
                    if (name.equals("상주캠퍼스") || name.contains("상주")) {
                        imsi_list.add(model);
                    }
                }

                if (search_text.equals("")) {

                } else {
                    imsi_list = getContainItem(imsi_list, search_text);
                }
                break;
            }
        }
        courses = imsi_list;
        setData(courses);
    }

    private void setData(ArrayList<Model2Course> dataList) throws Exception {
        UtilDialog.showToast(Page2LectureSearch.this, dataList.size() + "개의 데이터를 가져왔습니다.");
        adapter = new ArrayAdapter2LectureSearch(Page2LectureSearch.this, R.layout.item_list_search_result, dataList);
        lst_search_result.setAdapter(adapter);
        setListViewHeightBasedOnChildren(lst_search_result);
        adapter.notifyDataSetChanged();
    }


    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    class GetSiteInfo extends AsyncTask<Integer, String, ArrayList<Model2Course>> {
        private Context context;


        public GetSiteInfo(Context context) {
            this.context = context;

        }

        @Override
        protected ArrayList<Model2Course> doInBackground(Integer... integers) {
            Elements tableRows = null;
            ArrayList<Model2Course> course_list = new ArrayList<Model2Course>();// 테이블 Row데이터를 Model에담아 리스트에 넣는다.


            try {
                Document document = Jsoup.connect(url).get();
                tableRows = document.select("#viewPlans > table > tbody > tr");

                int i = 0;// 테이블의 첫번째 Row는 받지않기위해서 쓰는 변수


                for (Element element : tableRows) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    Elements tableColumn = element.children();
                    int column_count = 1;//테이블 각 항목에따라 모델에 set해야하는 부분이 다르므로 구분하기위해 쓰는 변수
                    Model2Course model = new Model2Course();


                    for (Element element1 : tableColumn) {
                        if (i == 0) {
                            i = 1;
                            break;
                        }

                        String text = element1.text();
                        if (text.equals("")) {
                            Log.d(LOG_TAG, "No Data ::: " + column_count);
                            column_count++;
                            continue;
                        }
                        switch (column_count) {
                            case 1: {//학년
                                model.setCourseGrade(text);
                                break;
                            }
                            case 2: {//구분 **
                                model.setCourseDivide(text);
                                break;
                            }
                            case 3: {//과목아이디 **
                                model.setCourseID(text);
                                break;
                            }
                            case 4: {//과목이름 **
                                model.setCourseName(text);
                                break;
                            }
                            case 5: {//학점
                                model.setCourseCredit((text));
                                break;
                            }
                            case 6: {//이론
                                model.setCourseTheory((text));
                                break;
                            }
                            case 7: {//실습
                                model.setCoursePractice(text);
                                break;
                            }
                            case 8: {//교수이름 **
                                model.setCourseProfessor(text);
                                break;
                            }
                            case 9: {//강의시간 **
                                model.setCourseTime(text);
                                break;
                            }
                            case 10: {//강의실 **
                                model.setCourseRoom(text);
                                break;
                            }
                            case 11: {//정원
                                model.setCourseLimit((text));
                                break;
                            }
                            case 12: {//수강신청한인원
                                model.setCoursePeople((text));
                                break;
                            }
                            case 13: {//패케지 인원
                                model.setCoursePackage((text));
                                break;
                            }
                            case 14: {//패케지 가능여부
                                model.setCoursePackageEnabled(text);
                                break;
                            }
                            case 15: {//비고 **
                                model.setCourseNote(text);
                                break;
                            }
                        }
                        column_count++;
                    }
                    if (i == 1) {
                        i = 2;
                    } else {
                        String str = model.getCourseNote();
                        if (str != null && str.contains("상주")) {
                            model.setCourseCampus("상주캠퍼스");
                        } else if (str != null) {
                            model.setCourseCampus("대구캠퍼스");
                        }
                        i++;
                        course_list.add(model);
                    }

                }

                Log.d(LOG_TAG, "Complete Data List ::: " + course_list.size());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return course_list;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<Model2Course> result) {

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
