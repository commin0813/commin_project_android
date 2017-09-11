package com.commin.pro.gangwon.page.energy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.commin.pro.gangwon.R;
import java.util.ArrayList;


public class Page2Energy extends AppCompatActivity {
    private LinearLayout ll_tab_summary, ll_tab_content, ll_tab_point;
    private LinearLayout ll_summary, ll_content, ll_point;
    private ArrayList<LinearLayout> tab_menu_linear;
    private ArrayList<LinearLayout> tab_linear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_energy_layout);
        init();
    }




    private void init() {
        ll_tab_summary = (LinearLayout) findViewById(R.id.ll_tab_summary);
        ll_tab_content = (LinearLayout) findViewById(R.id.ll_tab_content);
        ll_tab_point = (LinearLayout) findViewById(R.id.ll_tab_point);
        tab_menu_linear = new ArrayList<>();
        tab_menu_linear.add(ll_tab_summary);
        tab_menu_linear.add(ll_tab_content);
        tab_menu_linear.add(ll_tab_point);

        ll_summary = (LinearLayout) findViewById(R.id.ll_summary);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        ll_point = (LinearLayout) findViewById(R.id.ll_point);
        tab_linear = new ArrayList<>();
        tab_linear.add(ll_summary);
        tab_linear.add(ll_content);
        tab_linear.add(ll_point);
    }

    public void tab(View view) {
        setVisible(view);
        setTabColor(view);
    }

    private void setTabColor(View view){
        for(View view1 : tab_menu_linear){
            if(view ==  view1){
                view1.setBackgroundColor(getResources().getColor(R.color.colorBrown));
            }else {
                view1.setBackgroundColor(getResources().getColor(R.color.colorGrayCD));
            }
        }
    }

    private void setVisible(View view) {
        for (View view1 : tab_linear) {
            if (view.getId() == R.id.ll_tab_summary) {
                if (view1.getId() == R.id.ll_summary){
                    view1.setVisibility(View.VISIBLE);
                }
                else view1.setVisibility(View.INVISIBLE);
            } else if (view.getId() == R.id.ll_tab_content) {
                if (view1.getId() == R.id.ll_content){
                    view1.setVisibility(View.VISIBLE);
                }
                else view1.setVisibility(View.INVISIBLE);
            } else if (view.getId() == R.id.ll_tab_point) {
                if (view1.getId() == R.id.ll_point){
                    view1.setVisibility(View.VISIBLE);
                }
                else view1.setVisibility(View.INVISIBLE);
            }
        }
    }





}
