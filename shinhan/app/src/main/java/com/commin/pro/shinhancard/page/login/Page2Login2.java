package com.commin.pro.shinhancard.page.login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.commin.pro.shinhancard.R;
import com.commin.pro.shinhancard.page.ApplicationProperty;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class Page2Login2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_login2);

    }

    public void back(View view) {
        setResult(RESULT_CANCELED);
        onBackPressed();
    }

    public void onClick(View view) {
        startActivityForResult(new Intent(Page2Login2.this, Page2Login3.class), ApplicationProperty.REQUEST_CODE_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            setResult(ApplicationProperty.REQUEST_CODE_LOGIN);
            finish();
        }

    }

}
