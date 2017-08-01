package com.commin.pro.comminproject.driver;

import com.commin.pro.comminproject.util.ServerException;
import com.commin.pro.comminproject.util.UtilJson;

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
 * Created by user on 2016-01-04.
 */
public class Driver4Http {

    public static String doGet(String server_url) throws Exception {
        URL url = new URL(server_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//        }};
//
//        SSLContext sc = SSLContext.getInstance("TLS");
//        sc.init(null, trustAllCerts, new SecureRandom());
//        connection.setSSLSocketFactory(sc.getSocketFactory());
//
//        connection.setHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });

        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        connection.connect();

        StringBuilder response = new StringBuilder();
        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line + "\n");
            }
            connection.disconnect();
        }

        return response.toString();
    }

    public static Map<String, Object> doPost(String server_url, Map<String, String> request_params) throws Exception {
        String request_string = UtilJson.toString(request_params);
        String response_string = doPost(server_url, request_string);
        Map<String, Object> response_params = UtilJson.toMap(response_string);
        if (!"true".equalsIgnoreCase(String.valueOf(response_params.get("result")))) {
            throw new ServerException(String.valueOf(response_params.get("error")));
        }
        return response_params;
    }

    public static String doPost(String server_url, String request,String str) throws Exception {
        URL url = new URL(server_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(request);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();

        connection.connect();

        StringBuilder response = new StringBuilder();
        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line + "\n");
            }
            connection.disconnect();
        }



        return response.toString();
    }

    private static String doPost(String server_url, String request) throws Exception {
        URL url = new URL(server_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

//        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
//
//            }
//
//            @Override
//            public X509Certificate[] getAcceptedIssuers() {
//                return new X509Certificate[0];
//            }
//        }};
//
//        SSLContext sc = SSLContext.getInstance("TLS");
//        sc.init(null, trustAllCerts, new SecureRandom());
//        connection.setSSLSocketFactory(sc.getSocketFactory());
//
//        connection.setHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                return true;
//            }
//        });

        connection.setRequestMethod("POST");
        connection.setDoInput(true);
        connection.setDoOutput(true);

        OutputStream outputStream = connection.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
        bufferedWriter.write(request);
        bufferedWriter.flush();
        bufferedWriter.close();
        outputStream.close();

        connection.connect();

        StringBuilder response = new StringBuilder();
        if (connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line + "\n");
            }
            connection.disconnect();
        }

        return response.toString();
    }

}
