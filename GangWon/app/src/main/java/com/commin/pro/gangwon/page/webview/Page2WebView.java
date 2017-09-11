package com.commin.pro.gangwon.page.webview;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.commin.pro.gangwon.R;

public class Page2WebView extends AppCompatActivity {

    private static final String LOG_TAG = "Page2WebView";
    private WebView wb_content = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_web_view_layout);
        init();
    }

    private void init() {
        wb_content = (WebView) findViewById(R.id.wb_content);
        wb_content.setWebViewClient(new WebClientHelper());

        wb_content.getSettings().setJavaScriptEnabled(true);
        wb_content.getSettings().setLoadWithOverviewMode(true);
        wb_content.getSettings().setUseWideViewPort(true);
        wb_content.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wb_content.setVerticalScrollBarEnabled(false);
        wb_content.setHorizontalScrollBarEnabled(false);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            wb_content.getSettings().setTextZoom(90);
        }
        wb_content.loadUrl(getIntent().getStringExtra("url").toString());
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wb_content.canGoBack()) {
            wb_content.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    class WebClientHelper extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
