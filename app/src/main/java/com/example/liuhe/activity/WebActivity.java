package com.example.liuhe.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.liuhe.App;
import com.example.liuhe.R;

public class WebActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
        //获取意图传入的数据
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        setVebUrl(url);
    }

    private void setVebUrl(String url) {
        WebSettings setting = webview.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setLoadsImagesAutomatically(true);
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webview.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                if(pd==null){
//                    pd = ProgressDialog.show(WebActivity.this,"网页加载","Loing...");
//                }else {
//                    pd.show();
//                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                pd.cancel();
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                WebActivity.this.setProgress(newProgress*1000);
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    private void initView() {
        webview = (WebView) findViewById(R.id.webview);
    }

    @Override
    protected void onResume() {
        webview.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        webview.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        webview.destroy();
        super.onDestroy();
    }
}
