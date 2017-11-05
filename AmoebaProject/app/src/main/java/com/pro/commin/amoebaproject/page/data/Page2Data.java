package com.pro.commin.amoebaproject.page.data;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pro.commin.amoebaproject.R;
import com.pro.commin.amoebaproject.util.UtilJSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Blob;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Page2Data extends AppCompatActivity {
    @InjectView(R.id.textViewData)
    TextView textViewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_data_layout);
        start();
    }
    void start(){
        ButterKnife.inject(this);
        JSONArray jarray = null;
        String str = "";
        try {
             jarray = new JSONArray(UtilJSON.APP_DATA);
            for (int i = 0 ; i < jarray.length() ;i++){
                JSONObject jsonObject = jarray.getJSONObject(i);
                String address = jsonObject.getString("address");
                String name = jsonObject.getString("name");
                int age = jsonObject.getInt("age");
                str += "Name : " + name + " Address : " + address + " Age : " + age + "\n";
            }
            Intent intent = getIntent();
            if(intent !=null && intent.getData() != null)
                str += intent.getData().getPath();
            textViewData.setText(str);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
