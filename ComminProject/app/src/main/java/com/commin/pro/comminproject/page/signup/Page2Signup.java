package com.commin.pro.comminproject.page.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.commin.pro.comminproject.R;
import com.commin.pro.comminproject.widget.DialogProgress;
import com.victor.loading.rotate.RotateLoading;



public class Page2Signup extends AppCompatActivity {
    private EditText ed_sign_id, ed_sign_psw, ed_sign_psw_confirm;
    private Button btn_signup;

    private RotateLoading rotateLoading2;

    private final String LOG_TAG = "Page2Signup";

    private Page2SignupAdvisor advisor = new Page2SignupAdvisor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_signup_layout);

        init();
    }

    private void init() {
        ed_sign_id = (EditText) findViewById(R.id.ed_sign_id);
        ed_sign_psw = (EditText) findViewById(R.id.ed_sign_psw);
        ed_sign_psw_confirm = (EditText) findViewById(R.id.ed_sign_psw_confirm);
        btn_signup = (Button) findViewById(R.id.btn_signup);
        rotateLoading2 = (RotateLoading) findViewById(R.id.rotateloading2);


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    final String user_id = ed_sign_id.getText().toString();
                    final String password = ed_sign_psw.getText().toString();
                    final String password_confirm = ed_sign_psw_confirm.getText().toString();
                    String result = (String) DialogProgress.run(Page2Signup.this, new DialogProgress.ProgressTaskIf() {
                        @Override
                        public Object run() throws Exception {
                            return advisor.createCustomer(user_id, password);
                        }
                    });


                } catch (Exception e) {
                    Log.w(LOG_TAG, e);

                }

            }
        });


    }
}
