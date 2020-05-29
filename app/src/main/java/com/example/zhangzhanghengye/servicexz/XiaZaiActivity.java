package com.example.zhangzhanghengye.servicexz;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.os.IBinder;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import com.example.zhangzhanghengye.lx.R;

public class XiaZaiActivity extends AppCompatActivity {
    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xia_zai);
        Intent intent = new Intent(this, DownloadService.class);
        startService(intent);
        bindService(intent, connection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

    public void start(View view) {
        if (downloadBinder == null) {
            return;
        }
        //开始下载
        String url = "http://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=tup&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=1615699460,4215749679&os=1116638949,1286890202&simid=3428868985,271092641&pn=0&rn=1&di=195140&ln=1591&fr=&fmq=1574926755683_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=http%3A%2F%2Fwww.krabiandamantour.com%2Fimages%2Fkrabitour%2F4islands-koh-hong-by-speedboat%2Fkoh-tup-%2528tup-Island%2529-talay-waek-krabi-Islands-1.jpg&rpstart=0&rpnum=0&adpicid=0&force=undefined";

        downloadBinder.startDownload(url);

    }

    public void pause(View view) {
        //暂停下载
        if (downloadBinder == null) {
            return;
        }
        downloadBinder.pauseDownload();
    }

    public void cancel(View view) {
        //取消下载
        if (downloadBinder == null) {
            return;
        }
        downloadBinder.canceDownload();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);


    }
}
