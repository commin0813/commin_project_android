package com.commin.pro.comminproject;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.commin.pro.comminproject.driver.Driver4Http;
import com.commin.pro.comminproject.model.Model2Client;
import com.commin.pro.comminproject.page.ApplicationProperty;
import com.commin.pro.comminproject.page.main.Page2Main;
import com.commin.pro.comminproject.util.UtilJson;
import com.commin.pro.comminproject.util.UtilParam;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = "MainActivity";
    TextView tv_outPut;
    EditText ed_example;
    Button btn_send;
    String message = "";
    InputStream is;
    String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_outPut = (TextView) findViewById(R.id.tv_example);
        ed_example = (EditText) findViewById(R.id.ed_example);
        btn_send = (Button) findViewById(R.id.btn_send);

        createGUI();

//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String text = tv_outPut.getText().toString();
//                final String text2 = ed_example.getText().toString();
//
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String url_string = ApplicationProperty.SERVER_URL;
//                        try {
//                            Map<String,String>request_params =UtilParam.createParamMap();
//                            request_params.put("action","example");
//                            request_params.put("phoneNum","0102222");
//                            request_params.put("name","kim");
//                            request_params.put("address","bupyeng");
//
//                            String message = UtilJson.toString(request_params);
//
//                            Log.d(LOG_TAG,"client :: "+ message);
//
//
//
//                            URL url = new URL(url_string);
//                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//                            connection.setRequestMethod("POST");
//                            connection.setDoInput(true);
//                            connection.setDoOutput(true);
//
//
//
//
//                            OutputStream outputStream = connection.getOutputStream();
//                            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//                            bufferedWriter.write(message);
//                            bufferedWriter.flush();
//                            bufferedWriter.close();
//                            outputStream.close();
//
//                            connection.connect();
//
//                            StringBuilder response = new StringBuilder();
//                            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
//                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                                String line = null;
//                                while ((line = bufferedReader.readLine()) != null) {
//                                    response.append(line + "\n");
//
//                                    Log.d(LOG_TAG, line);
//                                }
//                                connection.disconnect();
//                            }
//
//                            tv_outPut.setText(response.toString());
//
//                        } catch (Exception e) {
//                            Log.d(LOG_TAG, "error");
//                        }
//                    }
//                }).start();
//
//
//            }
//        });



    }


    private void createGUI() {

        try {
            final String uuid = ApplicationProperty.createUUID(MainActivity.this);
            Log.d(LOG_TAG, "uuid created!!!" + uuid);
            final Model2Client model = new Model2Client();
            model.setUuid(uuid);
            model.setClient_msg(ApplicationProperty.CLIENT_SERVER_CONNECTION);

        } catch (Exception e) {

        }

        Intent intent = new Intent(MainActivity.this, Page2Main.class);
        startActivity(intent);
        finish();
    }
}
