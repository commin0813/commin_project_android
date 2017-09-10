package com.commin.pro.gangwon.page.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.page.ApplicationProperty;
import com.commin.pro.gangwon.page.development.Page2Development;
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

    public void startDevelopment(View view){
        Intent intent =  new Intent(Page2Home.this, Page2Development.class);

        switch (view.getId()){
            case R.id.ll_sun_light :{
                intent.putExtra("tab", ApplicationProperty.CODE_SUN_LIGHT);
            } break;

            case R.id.ll_sun_fire :{
                intent.putExtra("tab", ApplicationProperty.CODE_SUN_FIRE);

            } break;

            case R.id.ll_wind_force :{
                intent.putExtra("tab", ApplicationProperty.CODE_WIND_FORCE);
            } break;

            case R.id.ll_water_fire :{
                intent.putExtra("tab", ApplicationProperty.CODE_WATER_FIRE);
            } break;

        }

        startActivity(intent);
    }
}
