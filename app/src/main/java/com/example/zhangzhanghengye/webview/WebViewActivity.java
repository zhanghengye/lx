package com.example.zhangzhanghengye.webview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.zhangzhanghengye.lx.R;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWeb;
    private HttpURLConnection urlConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mWeb = findViewById(R.id.web);
        mWeb.getSettings().setJavaScriptEnabled(true);
        mWeb.setWebViewClient(new WebViewClient());
        mWeb.loadUrl("http://www.baidu.com");
        httpBaiDu();
    }

    public void httpBaiDu(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   Log.e("TAG", "run: ");
                   URL url = new URL("http://www.baidu.com");
                   urlConnection = (HttpURLConnection) url.openConnection();
                   urlConnection.setRequestMethod("GET");
                   urlConnection.setConnectTimeout(8000);
                   urlConnection.setReadTimeout(8000);
                   urlConnection.setDoInput(true);
                   urlConnection.setDoOutput(true);
                   InputStream inputStream = urlConnection.getInputStream();
                   BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
                   StringBuffer sb = new StringBuffer();
                   String fi;
                   while((fi=bf.readLine())!=null){
                       sb.append(fi);
                   }
                   Log.e("TAG", sb.toString());
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }

    @Override
    protected void onDestroy() {
        urlConnection.disconnect();
        super.onDestroy();
    }
}
