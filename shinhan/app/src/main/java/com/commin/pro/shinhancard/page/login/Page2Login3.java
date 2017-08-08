package com.commin.pro.shinhancard.page.login;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.commin.pro.shinhancard.R;

public class Page2Login3 extends AppCompatActivity {
    EditText ed_password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_login3);
        ed_password = (EditText) findViewById(R.id.ed_password);
    }

    public void back(View view) {
        setResult(RESULT_CANCELED);
        onBackPressed();
    }

    public void press_keyboard(View view) {
        ed_password.append("●");
    }

    public void delete(View view) {
        String str = ed_password.getText().toString();
        if(str.length()>0) {
            str = str.substring(0, str.length() - 1);
            ed_password.setText(str);
        }
    }

    public void confirm(View view) {
        if (ed_password.getText().toString().length() == 7) {
            setResult(RESULT_OK);
            finish();
        } else {
            //Dialog !!
        }
    }

}
