package com.commin.pro.shinhancard.page.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.commin.pro.shinhancard.R;
import com.commin.pro.shinhancard.page.menu.Page2Menu;

import java.util.Date;

public class Page2Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_home);

    }

    public void menu(View view){
       startActivity(new Intent(Page2Home.this, Page2Menu.class));
    }
}
