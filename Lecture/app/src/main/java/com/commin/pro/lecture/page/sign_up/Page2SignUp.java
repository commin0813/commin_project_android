package com.commin.pro.lecture.page.sign_up;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.commin.pro.lecture.R;
import com.commin.pro.lecture.model.Model2User;
import com.commin.pro.lecture.page.ApplicationProperty;
import com.commin.pro.lecture.util.UtilCustomDialog;
import com.commin.pro.lecture.util.UtilDialog;

import java.util.ArrayList;

public class Page2SignUp extends AppCompatActivity {

    Page2SignUpAdvisor advisor = null;
    EditText ed_user_id, ed_user_password, ed_user_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_layout);
        init();


    }

    private void init() {
        advisor = new Page2SignUpAdvisor();
        ed_user_id = (EditText) findViewById(R.id.ed_user_id);
        ed_user_password = (EditText) findViewById(R.id.ed_user_password);
        ed_user_password_confirm = (EditText) findViewById(R.id.ed_user_password_confirm);

    }

    public void sign_up(View view) {
        String err_msg = checkInvalid();
        if (err_msg.equals("OK")) {
            try {
                advisor.cretaeUser(ed_user_id.getText().toString(), ed_user_password.getText().toString());
                setResult(ApplicationProperty.RESULT_CODE_SIGN_UP_OK);
                finish();

            } catch (Exception e) {
                UtilDialog.openError(Page2SignUp.this, e.getMessage(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
            }

        } else {
            UtilDialog.openError(Page2SignUp.this, err_msg, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });
        }

    }

    private String checkInvalid() {
        if (ed_user_id.getText().toString().equals("")) {
            return "User ID를 입력해주세요";
        }

        if (ed_user_password.getText().toString().equals("")) {
            return "Password를 입력해주세요";
        }

        if (ed_user_password_confirm.getText().toString().equals("")) {
            return "Password Confirm을 입력해주세요";
        }

        if (!ed_user_password.getText().toString().equals(ed_user_password_confirm.getText().toString())) {
            return "Password가 서로 다릅니다.";
        }

        return "OK";
    }
}
