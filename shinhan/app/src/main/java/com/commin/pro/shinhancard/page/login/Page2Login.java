package com.commin.pro.shinhancard.page.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.commin.pro.shinhancard.Application;
import com.commin.pro.shinhancard.R;
import com.commin.pro.shinhancard.page.ApplicationProperty;

public class Page2Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_login);
    }

    public void back(View view){
        setResult(RESULT_CANCELED);
        onBackPressed();
    }

    public void onClick(View view){
        startActivityForResult(new Intent(Page2Login.this, Page2Login2.class), ApplicationProperty.REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == ApplicationProperty.REQUEST_CODE_LOGIN){
            ApplicationProperty.isLogin =true;
            setResult(ApplicationProperty.REQUEST_CODE_LOGIN);
            finish();
        }
    }
}
