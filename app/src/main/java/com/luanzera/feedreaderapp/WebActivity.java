package com.luanzera.feedreaderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.luanzera.feedreaderapp.Common.Common;

public class WebActivity extends AppCompatActivity {

    Toolbar toolbar_web;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        toolbar_web = findViewById(R.id.toolbar_web);
        setSupportActionBar(toolbar_web);
        getSupportActionBar().setTitle(Common.currentItem.getTitle());
        getSupportActionBar().setSubtitle(Common.currentItem.getLink());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar_web.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
            }
        });


        if (Common.currentItem.getLink() != null) {
            webView.loadUrl(Common.currentItem.getLink());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.openWebNavigator:
                openInWebNavigator(Common.currentItem.getLink());
                break;
            default:
                break;
        }
        return true;
    }

    private void openInWebNavigator(String url) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }
}
