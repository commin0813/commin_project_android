package com.commin.pro.gangwon.page.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.page.energy.Page2Energy;

public class Page2Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home_layout);

    }

    public void startEnergyHistory(View view){
        startActivity(new Intent(Page2Home.this, Page2Energy.class));
    }
}
