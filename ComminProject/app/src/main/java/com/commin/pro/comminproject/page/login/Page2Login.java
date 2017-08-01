package com.commin.pro.comminproject.page.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.commin.pro.comminproject.R;
import com.commin.pro.comminproject.model.Model2Login;
import com.commin.pro.comminproject.page.ApplicationProperty;
import com.commin.pro.comminproject.page.signup.Page2Signup;
import com.commin.pro.comminproject.widget.DialogProgress;

public class Page2Login extends AppCompatActivity {
    private EditText ed_login_id;
    private EditText ed_login_psw;
    private Button btn_login;
    private TextView tv_button_sign_up;
    public static final String LOG_TAG = "Page2Login";
    private Advisor2Login advisor = new Advisor2Login();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login_layout);
        init();
    }

    private void init() {
        ed_login_id = (EditText) findViewById(R.id.ed_login_id);
        ed_login_psw = (EditText) findViewById(R.id.ed_login_psw);
        btn_login = (Button) findViewById(R.id.btn_login);
        tv_button_sign_up = (TextView) findViewById(R.id.tv_button_sign_up);

        
        setListener();
    }

    private void setListener() {
        tv_button_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Page2Login.this, Page2Signup.class);
                startActivityForResult(intent, ApplicationProperty.REQUEST_CODE_SIGNUP);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ed_login_id.getText().toString().equals("")){
                    ed_login_id.setHint("아이디를 적어주세요");
                    return;
                }
                if(ed_login_psw.getText().toString().equals("")){
                    return;
                }

                final String user_id = ed_login_id.getText().toString();
                final String user_password = ed_login_psw.getText().toString();
                try{
                    Model2Login model2Login = (Model2Login) DialogProgress.run(Page2Login.this, new DialogProgress.ProgressTaskIf() {
                        @Override
                        public Object run() throws Exception {
                            return  advisor.loginCustomer(user_id,user_password);
                        }
                    });

                    if(model2Login ==null){
                        return ;
                    }else{
                        if(model2Login.isLogined()){

                        }else {
                            ed_login_id.setText("Login 실패");
                        }
                    }
                }catch(Exception e){
                    ed_login_id.setText("Login 실패");
                    Log.w(LOG_TAG , e);
                }

            }
        });
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
