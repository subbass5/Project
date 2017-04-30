package com.example.windowsnt.project;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

/**
 * Created by kritsana on 4/27/17.
 */

public class WebviewActivity extends AppCompatActivity {
    String url = "https://www.fireboard.xyz/show--KijIAho9eisNoTNDPrd.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        getSupportActionBar().hide();
        WebView  webView = (WebView) findViewById(R.id.webView);
        Button  gomainBtn = (Button) findViewById(R.id.btnGomainWeb);

        webView.setWebViewClient(new MyWebViewCilent());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        gomainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private class MyWebViewCilent extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }

}


