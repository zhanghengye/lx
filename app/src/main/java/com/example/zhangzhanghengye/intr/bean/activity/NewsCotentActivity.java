package com.example.zhangzhanghengye.intr.bean.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangzhanghengye.intr.bean.fragment.NewsContentFragment;
import com.example.zhangzhanghengye.lx.R;

public class NewsCotentActivity extends AppCompatActivity {
    public static  void actionStart(Context context, String title, String content){
        Intent intent = new Intent(context, NewsCotentActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_cotent);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        NewsContentFragment fragmentById = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        fragmentById.refresh(title,content);
    }
}
