package com.care.smsform;

import androidx.appcompat.app.AppCompatActivity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        if (checkSelfPermission("android.permission.RECEIVE_SMS") != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{"android.permission.RECEIVE_SMS"}, 1);
        }

        // Initialize WebView
        myWebView = findViewById(R.id.webView);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient());
        String baseUrl = "file:///android_asset/";
        String htmlFile = "index.html";
        String fileUrl = baseUrl + htmlFile;
        myWebView.loadUrl(fileUrl);
        Log.d("MYAPP", "App Started.." );
    }

    // Handle back button press in the WebView
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}
