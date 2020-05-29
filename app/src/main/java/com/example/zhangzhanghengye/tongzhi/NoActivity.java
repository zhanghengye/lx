package com.example.zhangzhanghengye.tongzhi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.zhangzhanghengye.lx.R;

public class NoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no);
        Intent intent = getIntent();
        int a = intent.getIntExtra("a", 0);
        Log.e("TAG", "onCreate: "+a);
    }
}
