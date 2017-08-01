package com.commin.pro.comminproject.page.main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.commin.pro.comminproject.R;
import com.commin.pro.comminproject.page.ApplicationProperty;
import com.commin.pro.comminproject.page.login.Page2Login;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.List;

public class Page2Main extends AppCompatActivity{
    private String LOG_TAG = "Page2Main";
    public ViewPager vp_contents_layer = null;
    public Button btn_fir_content_page, btn_sec_content_page =null;
    public ImageView iv_button_login, iv_button_no_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main_layout);
        init();
    }

    private void init(){
        vp_contents_layer = (ViewPager)findViewById(R.id.vp_contents_layer);
        btn_fir_content_page = (Button)findViewById(R.id.btn_fir_content_page);
        btn_sec_content_page = (Button)findViewById(R.id.btn_sec_content_page);
        iv_button_login = (ImageView)findViewById(R.id.iv_button_login);
        iv_button_no_login = (ImageView)findViewById(R.id.iv_button_no_login);
        setListener();

    }

    View.OnClickListener changePageListener = new View.OnClickListener(){
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            vp_contents_layer.setCurrentItem(tag);
        }
    };

    private void setListener() {
        vp_contents_layer.setAdapter(new Adapter2Pager(getSupportFragmentManager()));
        vp_contents_layer.setCurrentItem(0);

        btn_fir_content_page.setOnClickListener(changePageListener);
        btn_fir_content_page.setTag(0);
        btn_sec_content_page.setOnClickListener(changePageListener);
        btn_sec_content_page.setTag(1);

        iv_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        iv_button_no_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });


    }

    public void Login(){
        if(iv_button_login.isShown()){//login -- logout
            Log.d(LOG_TAG,"iv_button_login");
        }else if(iv_button_no_login.isShown()){// no login
            Log.d(LOG_TAG,"iv_button_no_login");
            Intent intent =new Intent(Page2Main.this, Page2Login.class);
            startActivityForResult(intent,ApplicationProperty.REQUEST_CODE_LOGIN);
        }
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if(fragmentList!=null){
            Toast.makeText(this,"Hello E",Toast.LENGTH_SHORT);
            for(Fragment fragment : fragmentList){
                if(fragment instanceof Frag2Content.OnBackPressedListener){
                    ((Frag2Content.OnBackPressedListener)fragment).onBackPressed();
                    Log.d(LOG_TAG,"onBackPressd");
                }
            }
        }
    }
}
