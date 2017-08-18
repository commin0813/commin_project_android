package com.commin.pro.shinhancard.page.menu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.commin.pro.shinhancard.Application;
import com.commin.pro.shinhancard.R;
import com.commin.pro.shinhancard.page.ApplicationProperty;
import com.commin.pro.shinhancard.page.login.Page2Login;
import com.commin.pro.shinhancard.page.use.Page2Use;

public class Page2Menu extends AppCompatActivity {
    TextView tv_my, tv_a1, tv_a2, tv_a3, tv_a4, tv_a5, tv_a6,tv_card_use_list;
    LinearLayout ll_login_top,ll_logout_top;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_menu);

        this.tv_my = (TextView) findViewById(R.id.tv_my);
        this.tv_a1 = (TextView) findViewById(R.id.tv_a1);
        this.tv_a2 = (TextView) findViewById(R.id.tv_a2);
        this.tv_a3 = (TextView) findViewById(R.id.tv_a3);
        this.tv_a4 = (TextView) findViewById(R.id.tv_a4);
        this.tv_a5 = (TextView) findViewById(R.id.tv_a5);
        this.tv_a6 = (TextView) findViewById(R.id.tv_a6);

        this.ll_login_top = (LinearLayout)findViewById(R.id.ll_login_top);
        this.ll_logout_top = (LinearLayout)findViewById(R.id.ll_logout_top);


        change_login_button();


        this.btn_login = (Button)findViewById(R.id.btn_login);
        tv_card_use_list = (TextView)findViewById(R.id.tv_card_use_list);

        createGUI();


    }
    public void logout(View view){
        ApplicationProperty.isLogin = false;
        change_login_button();
    }
    private void change_login_button(){
        if(ApplicationProperty.isLogin){
            ll_logout_top.setVisibility(View.VISIBLE);
            ll_login_top.setVisibility(View.INVISIBLE);
        }else {
            ll_logout_top.setVisibility(View.INVISIBLE);
            ll_login_top.setVisibility(View.VISIBLE);
        }
    }

    public void back(View view){
        onBackPressed();
    }

    private void createGUI() {

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Page2Menu.this, Page2Login.class), ApplicationProperty.REQUEST_CODE_LOGIN);
            }
        });

        tv_card_use_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ApplicationProperty.isLogin){
                    startActivity(new Intent(Page2Menu.this, Page2Use.class));
                }else{
                    startActivityForResult(new Intent(Page2Menu.this, Page2Login.class), ApplicationProperty.REQUEST_CODE_LOGIN);
                }
            }
        });


        tv_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor(view);
            }
        });
        tv_a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor(view);
            }
        });
        tv_a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor(view);
            }
        });
        tv_a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor(view);
            }
        });
        tv_a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor(view);
            }
        });
        tv_a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor(view);
            }
        });
        tv_a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeColor(view);
            }
        });
    }

    private void changeColor(View view) {
        TextView textView = (TextView) view;
        textView.setTextColor(this.getResources().getColor(R.color.comminWhiteFA));
        initTextColor(textView);
        if (textView.equals(tv_my)) {

        } else if (textView.equals(tv_a1)) {

        }

    }

    private void initTextColor(TextView view) {
        if (!tv_my.equals(view)) {
            tv_my.setTextColor(this.getResources().getColor(R.color.shinhantextDisalble));
        }
        if (!tv_a1.equals(view)) {
            tv_a1.setTextColor(this.getResources().getColor(R.color.shinhantextDisalble));
        }
        if (!tv_a2.equals(view)) {
            tv_a2.setTextColor(this.getResources().getColor(R.color.shinhantextDisalble));
        }
        if (!tv_a3.equals(view)) {
            tv_a3.setTextColor(this.getResources().getColor(R.color.shinhantextDisalble));
        }
        if (!tv_a4.equals(view)) {
            tv_a4.setTextColor(this.getResources().getColor(R.color.shinhantextDisalble));
        }
        if (!tv_a5.equals(view)) {
            tv_a5.setTextColor(this.getResources().getColor(R.color.shinhantextDisalble));
        }
        if (!tv_a6.equals(view)) {
            tv_a6.setTextColor(this.getResources().getColor(R.color.shinhantextDisalble));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == ApplicationProperty.REQUEST_CODE_LOGIN){
            change_login_button();
        }
    }



}
