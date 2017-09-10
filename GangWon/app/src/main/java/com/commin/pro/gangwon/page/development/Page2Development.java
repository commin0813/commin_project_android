package com.commin.pro.gangwon.page.development;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.page.ApplicationProperty;

import java.util.ArrayList;

public class Page2Development extends AppCompatActivity {
    private static final String LOG_TAG = "Page2Development";

    private LinearLayout ll_tab_sun_light, ll_tab_sun_fire, ll_tab_wind_force,ll_tab_water_fire;
    private LinearLayout ll_sun_light, ll_sun_fire, ll_wind_force,ll_water_fire;
    private ArrayList<LinearLayout> tab_menu_linear;
    private ArrayList<LinearLayout> tab_linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_development_layout);

        init();
        Intent intent = getIntent();
        int tab = intent.getIntExtra("tab",-1);
        setInitTab(tab);
    }

    private void setInitTab(int tab){
        View view = ll_tab_sun_light;
        switch (tab){
            case ApplicationProperty.CODE_SUN_LIGHT :
                view = ll_tab_sun_light;
                break;
            case ApplicationProperty.CODE_SUN_FIRE :
                view = ll_tab_sun_fire;
                break;

            case ApplicationProperty.CODE_WIND_FORCE :
                view = ll_tab_wind_force;
                break;
            case ApplicationProperty.CODE_WATER_FIRE :
                view = ll_tab_water_fire;
                break;
        }

        setVisible(view);
        setTabColor(view);
    }

    private void init(){
        ll_tab_sun_light = (LinearLayout)findViewById(R.id.ll_tab_sun_light);
        ll_tab_sun_fire = (LinearLayout)findViewById(R.id.ll_tab_sun_fire);
        ll_tab_wind_force = (LinearLayout)findViewById(R.id.ll_tab_wind_force);
        ll_tab_water_fire = (LinearLayout)findViewById(R.id.ll_tab_water_fire);
        tab_menu_linear = new ArrayList<>();
        tab_menu_linear.add(ll_tab_sun_light);
        tab_menu_linear.add(ll_tab_sun_fire);
        tab_menu_linear.add(ll_tab_wind_force);
        tab_menu_linear.add(ll_tab_water_fire);


        ll_sun_light = (LinearLayout)findViewById(R.id.ll_sun_light);
        ll_sun_fire = (LinearLayout)findViewById(R.id.ll_sun_fire);
        ll_wind_force = (LinearLayout)findViewById(R.id.ll_wind_force);
        ll_water_fire = (LinearLayout)findViewById(R.id.ll_water_fire);
        tab_linear = new ArrayList<>();
        tab_linear.add(ll_sun_light);
        tab_linear.add(ll_sun_fire);
        tab_linear.add(ll_wind_force);
        tab_linear.add(ll_water_fire);
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
            if (view.getId() == R.id.ll_tab_sun_light) {
                if (view1.getId() == R.id.ll_sun_light){
                    view1.setVisibility(View.VISIBLE);
                }
                else view1.setVisibility(View.INVISIBLE);
            } else if (view.getId() == R.id.ll_tab_sun_fire) {
                if (view1.getId() == R.id.ll_sun_fire){
                    view1.setVisibility(View.VISIBLE);
                }
                else view1.setVisibility(View.INVISIBLE);
            } else if (view.getId() == R.id.ll_tab_wind_force) {
                if (view1.getId() == R.id.ll_wind_force){
                    view1.setVisibility(View.VISIBLE);
                }
                else view1.setVisibility(View.INVISIBLE);
            } else if (view.getId() == R.id.ll_tab_water_fire) {
                if (view1.getId() == R.id.ll_water_fire){
                    view1.setVisibility(View.VISIBLE);
                }
                else view1.setVisibility(View.INVISIBLE);
            }
        }
    }

}
