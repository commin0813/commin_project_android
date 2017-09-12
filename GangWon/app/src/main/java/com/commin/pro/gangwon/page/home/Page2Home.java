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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.commin.pro.gangwon.R;
import com.commin.pro.gangwon.model.Model2News;
import com.commin.pro.gangwon.model.Model2SMP;
import com.commin.pro.gangwon.page.ApplicationProperty;
import com.commin.pro.gangwon.page.development.Page2Development;
import com.commin.pro.gangwon.page.energy.Page2Energy;
import com.commin.pro.gangwon.page.map.Page2Map;
import com.commin.pro.gangwon.page.menu.CustomMenu;
import com.commin.pro.gangwon.page.util.Util2Menu;
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
        init_menu();
        createGUI();
    }


    private void init() {
        ApplicationProperty.HOME_CONTEXT = Page2Home.this;

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
                Document document = Jsoup.connect(ApplicationProperty.ADDR_ARTICLE).get(); // Jsoup이라는 라이브러리를 이용해서 Doc 형식의 html 파싱데이터를 받아옵니다.
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
//            setListViewHeightBasedOnChildren(lst_home_news);
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
                Document document = Jsoup.connect(ApplicationProperty.ADDR_SMP).get(); // Jsoup이라는 라이브러리를 이용해서 Doc 형식의 html 파싱데이터를 받아옵니다.
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

    @Override
    protected void onStop() {
        super.onStop();
        if (ll_nav_menu != null)
            ll_nav_menu.setVisibility(View.INVISIBLE);
    }

    public void startMap(View view) {
        startActivity(new Intent(Page2Home.this, Page2Map.class));

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


    /***************************************
     * Footer
     */
    private LinearLayout ll_nav_menu = null;
    private CustomMenu customMenu = null;

    private void init_menu() {
        customMenu = new CustomMenu(Page2Home.this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        customMenu.setLayoutParams(lp);

        ImageView imageView = (ImageView) customMenu.findViewById(R.id.iv_item_cancel_menu);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Util2Menu.isNavMenuShowing(Page2Home.this, ll_nav_menu);
            }
        });
    }

    public void call_menu(View view) {
        if (ll_nav_menu == null)
            ll_nav_menu = (LinearLayout) findViewById(R.id.ll_nav_menu);
        else
            ll_nav_menu.removeAllViews();

        ll_nav_menu.addView(customMenu);
        Util2Menu.isNavMenuShowing(Page2Home.this, ll_nav_menu);
    }

    public void call_home(View view) {

    }

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        if (ll_nav_menu != null && ll_nav_menu.isShown()) {
            Util2Menu.isNavMenuShowing(Page2Home.this, ll_nav_menu);
            return;
        }
        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로 버튼을 한번더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }
}
