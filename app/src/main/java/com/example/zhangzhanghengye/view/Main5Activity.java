package com.example.zhangzhanghengye.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhangzhanghengye.lx.R;

public class Main5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new SplashView(this));
    }
}
