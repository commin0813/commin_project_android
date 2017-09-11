package com.commin.pro.gangwon.page.home;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.model.Model2News;
import com.commin.pro.gangwon.model.Model2SMP;
import com.commin.pro.gangwon.page.ApplicationProperty;
import com.commin.pro.gangwon.page.development.Page2Development;
import com.commin.pro.gangwon.page.energy.Page2Energy;
import com.commin.pro.gangwon.page.webview.Page2WebView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Page2Home extends AppCompatActivity {

    private Model2SMP smps = null;
    private ArrayList<Model2News> news_items = null;
    private TextView tv_home_smp_date, tv_home_smp_max, tv_home_smp_avg;

    private ListView lst_home_news = null;
    private ArrayAdapter2News adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_home_layout);
        init();
        createGUI();
    }

    private void init() {
        tv_home_smp_date = (TextView) findViewById(R.id.tv_home_smp_date);
        tv_home_smp_max = (TextView) findViewById(R.id.tv_home_smp_max);
        tv_home_smp_avg = (TextView) findViewById(R.id.tv_home_smp_avg);

        lst_home_news = (ListView) findViewById(R.id.lst_home_news);
        lst_home_news.setDivider(null);

        lst_home_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String url = news_items.get(position).getTarget_url();
                Intent intent = new Intent(Page2Home.this, Page2WebView.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });

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

    //    private static String url2 = "http://gangwon.news1.kr/news/articleList.html?sc_word=%EC%8B%A0%EC%9E%AC%EC%83%9D";
//    private static String url2 = "http://www.kado.net/?mod=search&act=engine&sc_code=&sc_area=A&sc_article_type=&sc_view_level=&sc_sdate=2016-09-11&sc_edate=2017-09-11&searchWord=%EC%8B%A0%EC%9E%AC%EC%83%9D";
    private static String url2 = "http://www.kado.net/?mod=search&act=engine&cust_div_code=&searchContType=article&searchWord=%EC%8B%A0%EC%9E%AC%EC%83%9D&fromDate=&toDate=&sfield=&article_type=&sort=date";

    class GetNewsInfo extends AsyncTask<Integer, Integer, ArrayList<Model2News>> {
        private Context context;

        public GetNewsInfo(Context context) {
            this.context = context;
        }

        @Override
        protected ArrayList<Model2News> doInBackground(Integer... integers) {
            Elements options = null;
            ArrayList<Model2News> items = new ArrayList<Model2News>();
            try {
                Document document = Jsoup.connect(url2).get(); // Jsoup이라는 라이브러리를 이용해서 Doc 형식의 html 파싱데이터를 받아옵니다.
                options = document.select("div > .container > #content > .syw_result_box > .syw_result > .sywr_summary > .box > ul");
                for (Element element : options) {
                    Element title_ele = element.child(0).child(0);
                    if (title_ele == null) {
                        continue;
                    }
                    Model2News model = new Model2News();
                    model.setTitle(title_ele.text());
                    model.setTarget_url(title_ele.attr("href"));
                    items.add(model);

                    if (items.size() >= ApplicationProperty.SIZE_NEWS) {
                        break;
                    }

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
            adapter = new ArrayAdapter2News(Page2Home.this, R.layout.item_list_news, result);
            lst_home_news.setAdapter(adapter);
            setListViewHeightBasedOnChildren(lst_home_news);
            adapter.notifyDataSetChanged();
        }
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
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

    public void startWebView(View view) {
        Intent intent = new Intent(Page2Home.this, Page2WebView.class);
        String url = "";
        switch (view.getId()) {
            case R.id.ll_py:
                url = ApplicationProperty.ADDR_PYEONGCHANG;
                break;
            case R.id.ll_siber:
                url = ApplicationProperty.ADDR_SIBER;
                break;
        }
        intent.putExtra("url", url);
        startActivity(intent);
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
