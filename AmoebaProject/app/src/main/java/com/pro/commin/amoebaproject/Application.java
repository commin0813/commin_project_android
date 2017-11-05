package com.pro.commin.amoebaproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pro.commin.amoebaproject.page.data.Page2Data;
import com.pro.commin.amoebaproject.page.login.Page2Login;
import com.pro.commin.amoebaproject.page.phone.Page2Phone;

public class Application extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_layout);
//        startActivity(new Intent(Application.this, Page2Login.class));


        startActivity(new Intent(Application.this, Page2Data.class));

    }

}
