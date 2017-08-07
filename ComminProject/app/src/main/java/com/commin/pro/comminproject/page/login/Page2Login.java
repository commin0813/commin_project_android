package com.commin.pro.comminproject.page.login;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.commin.pro.comminproject.R;
import com.commin.pro.comminproject.model.Model2Login;
import com.commin.pro.comminproject.page.ApplicationProperty;
import com.commin.pro.comminproject.page.signup.Page2Signup;
import com.commin.pro.comminproject.util.UtilDialog;
import com.commin.pro.comminproject.widget.DialogProgress;

public class Page2Login extends AppCompatActivity {
    private EditText ed_login_id;
    private EditText ed_login_psw;
    private Button btn_login,btn_logout;
    private TextView tv_button_sign_up;
    private TextView tv_logout_id,tv_logout_password;
    public static final String LOG_TAG = "Page2Login";
    private Advisor2Login advisor = new Advisor2Login();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            if(!ApplicationProperty.model2Login.isLogined()){
                setContentView(R.layout.page_login_layout);
                init();
            }else {
                setContentView(R.layout.page_logout_layout);
                init2();
            }
        }catch (Exception e){
            setContentView(R.layout.page_login_layout);
            init();
        }
    }
    private void init2(){
        Model2Login model2Login = ApplicationProperty.model2Login;
        tv_logout_id = (TextView)findViewById(R.id.tv_logout_id);
        tv_logout_password = (TextView)findViewById(R.id.tv_logout_password);
        btn_logout = (Button)findViewById(R.id.btn_logout);

        tv_logout_id.setText(model2Login.getUser_id());
        tv_logout_password.setText(model2Login.getUser_password());

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            UtilDialog.confirmDialog(Page2Login.this, "로그아웃", "로그아웃하시겠습니까?", new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    ApplicationProperty.model2Login.setLogined(false);
                    finish();
                }
            }, new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                }
            }).show();
            }
        });
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
                final String user_id = ed_login_id.getText().toString();
                final String user_password = ed_login_psw.getText().toString();
                try {
                    String err_message = confirm();

                    if (!(err_message == null)) {
                        UtilDialog.basicDialog(Page2Login.this, "로그인실패", err_message, new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            }
                        }).show();
                        return;
                    } else {
                        Model2Login model2Login = (Model2Login) DialogProgress.run(Page2Login.this, new DialogProgress.ProgressTaskIf() {
                            @Override
                            public Object run() throws Exception {
                                return advisor.loginCustomer(user_id, user_password);
                            }
                        });

                        if (model2Login != null) {
                            if (model2Login.isLogined()) {
                                ApplicationProperty.model2Login = model2Login;
                                UtilDialog.basicDialog(Page2Login.this, "로그인성공", "환영합니다.", new MaterialDialog.SingleButtonCallback() {
                                    @Override
                                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                        finish();
                                    }
                                }).show();
                            } else {
                                if(!model2Login.isId()){
                                    throw new Exception("There is no ID");
                                }
                                if(!model2Login.isPassword()){
                                    throw new Exception("The password is incorrect.");
                                }
                                return;
                            }
                        }
                    }

                } catch (Exception e) {
                    Log.w(LOG_TAG, e);
                    UtilDialog.basicDialog(Page2Login.this, "로그인실패", e.getMessage(), new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            ed_login_psw.setText("");
                        }
                    }).show();
                }

            }
        });
    }

    private String confirm() {
        String err_message = null;
        if (ed_login_id.getText().toString().equals("")) {
            err_message = "아이디를 입력해주세요.";
            return err_message;
        }
        if (ed_login_psw.getText().toString().equals("")) {
            err_message = "패스워드를 입력해주세요.";
            return err_message;
        }

        return err_message;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
    }
}
