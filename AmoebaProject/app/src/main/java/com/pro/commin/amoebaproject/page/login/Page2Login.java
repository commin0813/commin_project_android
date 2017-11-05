package com.pro.commin.amoebaproject.page.login;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pro.commin.amoebaproject.R;
import com.pro.commin.amoebaproject.util.Util;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Page2Login extends AppCompatActivity {

    @InjectView(R.id.buttonQuery)
    Button buttonQuery;
    @InjectView(R.id.textViewResultLogin)
    TextView textViewResultLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_login_layout);
        start();
    }
    private void start(){
        ButterKnife.inject(this);

        buttonQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver cr = getContentResolver();
                String[] projection = new String[]{
                        Util.COL_ID,
                        Util.COL_NAME,
                        Util.COL_EMAIL,
                        Util.COL_PASSWORD,
                        Util.COL_CITY
                };
                Cursor cursor = cr.query(Util.USER_URI,projection,null,null,Util.COL_NAME + " ASC" );
                cursor.moveToFirst();
                String str = "";
                do{
                    int _id = cursor.getInt(cursor.getColumnIndex(Util.COL_ID));
                    String name =cursor.getString(cursor.getColumnIndex(Util.COL_NAME));
                    String email  = cursor.getString(cursor.getColumnIndex(Util.COL_EMAIL));
                    String password = cursor.getString(cursor.getColumnIndex(Util.COL_PASSWORD));
                    String city = cursor.getString(cursor.getColumnIndex(Util.COL_CITY));

                    str += _id+"--" +name+"--"+email+"--"+password+"--"+city+"\n";
                }while(cursor.moveToNext());
                textViewResultLogin.setText(str);
            }
        });

    }
}
