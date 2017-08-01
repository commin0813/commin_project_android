package com.commin.pro.comminproject.page.signup;

import android.util.Log;

import com.commin.pro.comminproject.driver.Driver4Http;
import com.commin.pro.comminproject.page.ApplicationProperty;
import com.commin.pro.comminproject.util.UtilJson;
import com.commin.pro.comminproject.util.UtilParam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by user on 2017-07-26.
 */
public class Page2SignupAdvisor {
    public String createCustomer(String user_id, String user_password) throws Exception {
        try {
            Map<String, String> request_params = UtilParam.createParamMap();
            request_params.put("action", "createCustomer");
            request_params.put("user_id", user_id);
            request_params.put("user_password", user_password);
//            String url_string = ApplicationProperty.SERVER_URL;
//
//            String message = UtilJson.toString(request_params);
//
//            URL url = new URL(url_string);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            connection.setRequestMethod("POST");
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//
//
//            OutputStream outputStream = connection.getOutputStream();
//            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
//            bufferedWriter.write(message);
//            bufferedWriter.flush();
//            bufferedWriter.close();
//            outputStream.close();
//
//            connection.connect();
//
//            StringBuilder response = new StringBuilder();
//            if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String line = null;
//                while ((line = bufferedReader.readLine()) != null) {
//                    response.append(line + "\n");
//                }
//                connection.disconnect();
//            }
//
//            Log.d("aaa",UtilJson.toMap(response.toString()).get("result").toString());

            Map<String, Object> response_params = Driver4Http.doPost(ApplicationProperty.SERVER_URL, request_params);
            Log.d("aaa",response_params.get("result").toString());
            return null;
        } catch (Exception e) {
            throw e;
        }
    }
}
