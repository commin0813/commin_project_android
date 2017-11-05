package com.pro.commin.amoebaproject.page.phone;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pro.commin.amoebaproject.R;

import butterknife.InjectView;

public class Page2Phone extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_phone_layout);
        start();
    }

    private void start(){
        TextView tv = (TextView)findViewById(R.id.tv_phone_label);
        Cursor c = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        String str = "";
        c.moveToFirst();
        do{
            String name = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            str +="Name : " + name +" Number : " + phoneNumber +"\n";
        }while(c.moveToNext());
        tv.setText(str);
    }

}
