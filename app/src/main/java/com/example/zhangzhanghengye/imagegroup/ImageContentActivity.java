package com.example.zhangzhanghengye.imagegroup;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zhangzhanghengye.lx.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class ImageContentActivity extends AppCompatActivity {

    private List<Bean> mList = new ArrayList<>();
    private RecyclerView mRecycler;
    private RecyclerAdapter recyclerAdapter;
    private List<String> imgList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_content);
        mRecycler = findViewById(R.id.recycler);
        Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (query.moveToNext()) {
            byte[] blob = query.getBlob(query.getColumnIndex(MediaStore.Images.Media.DATA));
            String s = new String(blob, 0, blob.length - 1);
            Log.e("TAG", "getImage: " + s);
            Bean bean = new Bean();
            bean.setPath(s);
            mList.add(bean);
        }
        recyclerAdapter = new RecyclerAdapter(this, mList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        mRecycler.setLayoutManager(gridLayoutManager);
        mRecycler.setAdapter(recyclerAdapter);
    }

    public void wancheng(View view){
        for (int i = 0; i < mList.size(); i++) {
            Bean bean = mList.get(i);
            if(bean.isA()){
                Log.e("TAG", "wancheng: "+bean.getPath());
                imgList.add(bean.getPath());
            }
        }
        String[] objects =imgList.toArray(new String[imgList.size()]);
        Intent intent = getIntent();
        intent.putExtra("data",objects);
        setResult(2,intent);
        finish();
    }
}
