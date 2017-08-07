package com.commin.pro.shinhancard.page.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.commin.pro.shinhancard.R;

public class Page2Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_login);
    }

    public void back(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

}
