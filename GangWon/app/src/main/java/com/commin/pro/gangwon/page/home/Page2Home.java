package com.commin.pro.gangwon.page.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.model.Model2News;
import com.commin.pro.gangwon.model.Model2SMP;
import com.commin.pro.gangwon.page.ApplicationProperty;
import com.commin.pro.gangwon.page.development.Page2Development;
import com.commin.pro.gangwon.page.energy.Page2Energy;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Page2Home extends AppCompatActivity {

    private Model2SMP smps = null;
    private ArrayList<Model2News> news_items = null;
    private TextView tv_home_smp_date,tv_home_smp_max,tv_home_smp_avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home_layout);
        init();
        createGUI();
    }

    private void init(){
        tv_home_smp_date = (TextView)findViewById(R.id.tv_home_smp_date);
        tv_home_smp_max = (TextView)findViewById(R.id.tv_home_smp_max);
        tv_home_smp_avg = (TextView)findViewById(R.id.tv_home_smp_avg);
    }


    private void createGUI() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    smps = new GetSMPInfo(Page2Home.this).execute().get();
                    news_items = new GetNewsInfo(Page2Home.this).execute().get();
                } catch (Exception e) {
                    Log.w("LOG_TAG", e);
                }
            }
        };
        Handler handler = new Handler();
        handler.postDelayed(runnable, 100);
    }

//    private static String url2 = "http://m.gangwon.news1.kr/news/articleList.html?sc_area=A&sc_word=%EC%8B%A0%EC%9E%AC%EC%83%9D";
//    private static String url2 = "http://gangwon.news1.kr/news/articleList.html?sc_word=%EC%8B%A0%EC%9E%AC%EC%83%9D";
    private static String url2 = "http://www.kado.net/?mod=search&act=engine&sc_code=&sc_area=A&sc_article_type=&sc_view_level=&sc_sdate=2016-09-11&sc_edate=2017-09-11&searchWord=%EC%8B%A0%EC%9E%AC%EC%83%9D";
    class GetNewsInfo extends AsyncTask<Integer, Integer, ArrayList<Model2News>> {
        private Context context;

        public GetNewsInfo(Context context) {
            this.context = context;
        }

        @Override
        protected ArrayList<Model2News> doInBackground(Integer... integers) {
            Elements options = null;
            ArrayList<Model2News> items = new ArrayList<Model2News>();
            final StringBuffer sb = new StringBuffer();
            try {
//                URL url = new URL(url2);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                if(conn !=null){
//                    conn.setConnectTimeout(2000);
//                    conn.setUseCaches(false);
//                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
//                        BufferedReader br
//                                = new BufferedReader(new InputStreamReader
//                                (conn.getInputStream(),"utf-8"));//"utf-8"
//                        while(true) {
//                            String line = br.readLine();
//                            if (line == null) break;
//                            sb.append(line+"\n");
//                        }
//                        br.close(); // 스트림 해제
//                    }
//                }
//                String str = sb.toString();
//                Log.d("LOG_TAG",str);



//                Connection.Response response = Jsoup.connect(url2).execute();
//                Document document = Jsoup.parse("<li>"+response.body()+"</li");

                Document document = Jsoup.connect(url2).get(); // Jsoup이라는 라이브러리를 이용해서 Doc 형식의 html 파싱데이터를 받아옵니다.

                options = document.select("div > article > ul");
                ArrayList<String> options_value = new ArrayList<>();
                for (Element element : options) {
                    String value = element.text();
                    if (value.equals("")) {
                        continue;
                    }
                    options_value.add(value);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }
            return items;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onPostExecute(ArrayList<Model2News> result) {

        }
    }

    private static String url = "http://www.kpx.or.kr/";
    class GetSMPInfo extends AsyncTask<Integer, Integer, Model2SMP> {
        private Context context;

        public GetSMPInfo(Context context) {
            this.context = context;
        }

        @Override
        protected Model2SMP doInBackground(Integer... integers) {
            Elements options = null;
            Model2SMP model = new Model2SMP();
            try {
                Document document = Jsoup.connect(url).get(); // Jsoup이라는 라이브러리를 이용해서 Doc 형식의 html 파싱데이터를 받아옵니다.
                options = document.select("div > #smp_01 > table > tbody > tr > td");
                ArrayList<String> options_value = new ArrayList<>();
                for (Element element : options) {
                    String value = element.text();
                    if (value.equals("")) {
                        continue;
                    }
                    options_value.add(value);
                }
                model.setDate(options_value.get(0));
                model.setMax_value(options_value.get(1));
                model.setAvg_value(options_value.get(2));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return model;
        }

        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onPostExecute(Model2SMP result) {
            tv_home_smp_date.setText(result.getDate());
            tv_home_smp_max.setText(result.getMax_value());
            tv_home_smp_avg.setText(result.getAvg_value());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void startEnergyHistory(View view) {
        startActivity(new Intent(Page2Home.this, Page2Energy.class));
    }

    public void startDevelopment(View view) {
        Intent intent = new Intent(Page2Home.this, Page2Development.class);

        switch (view.getId()) {
            case R.id.ll_sun_light: {
                intent.putExtra("tab", ApplicationProperty.CODE_SUN_LIGHT);
            }
            break;

            case R.id.ll_sun_fire: {
                intent.putExtra("tab", ApplicationProperty.CODE_SUN_FIRE);

            }
            break;

            case R.id.ll_wind_force: {
                intent.putExtra("tab", ApplicationProperty.CODE_WIND_FORCE);
            }
            break;

            case R.id.ll_water_fire: {
                intent.putExtra("tab", ApplicationProperty.CODE_WATER_FIRE);
            }
            break;

        }

        startActivity(intent);
    }
}
