package com.commin.pro.gangwon.page.energy;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.page.ApplicationProperty;
import com.commin.pro.gangwon.page.home.Page2Home;
import com.commin.pro.gangwon.page.menu.CustomMenu;
import com.commin.pro.gangwon.page.util.Util2Menu;

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
        init_menu();
        Intent intent = getIntent();
        int tab = intent.getIntExtra("tab",-1);
        setInitTab(tab);
    }

    private void setInitTab(int tab){
        View view = ll_tab_summary;
        switch (tab){
            case ApplicationProperty.CODE_SUMMARY :
                view = ll_tab_summary;
                break;
            case ApplicationProperty.CODE_CONTENT :
                view = ll_tab_content;
                break;

            case ApplicationProperty.CODE_DETAIL_SUMMARY :
                view = ll_tab_point;
                break;
        }

        setVisible(view);
        setTabColor(view);
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


    /***************************************
     * Footer
     */
    private LinearLayout ll_nav_menu = null;
    private CustomMenu customMenu = null;

    private void init_menu() {
        customMenu = new CustomMenu(Page2Energy.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        customMenu.setLayoutParams(lp);

        ImageView imageView = (ImageView) customMenu.findViewById(R.id.iv_item_cancel_menu);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util2Menu.isNavMenuShowing(Page2Energy.this, ll_nav_menu);
            }
        });
    }

    public void call_menu(View view) {
        if (ll_nav_menu == null)
            ll_nav_menu = (LinearLayout) findViewById(R.id.ll_nav_menu);
        else
            ll_nav_menu.removeAllViews();

        ll_nav_menu.addView(customMenu);
        Util2Menu.isNavMenuShowing(Page2Energy.this, ll_nav_menu);
    }

    public void call_home(View view) {
        finish();
    }


    @Override
    public void onBackPressed() {
        if (ll_nav_menu != null && ll_nav_menu.isShown()) {
            Util2Menu.isNavMenuShowing(Page2Energy.this, ll_nav_menu);
            return;
        }
        super.onBackPressed();
    }

}
