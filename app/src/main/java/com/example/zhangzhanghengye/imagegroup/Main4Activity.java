package com.example.zhangzhanghengye.imagegroup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.zhangzhanghengye.lx.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main4Activity extends AppCompatActivity {

    private List<String> mList = new ArrayList<>();
    private LvAdapter lvAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ListView mLv = findViewById(R.id.lv);
        lvAdapter = new LvAdapter(mList, this);
        mLv.setAdapter(lvAdapter);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(Main4Activity.this,ImageContentActivity.class);
                intent.putExtra("id",position);
                startActivityForResult(intent,1);

            }
        });
    }
    public void moren(View view){
        Intent intent =new Intent(Main4Activity.this,ImageContentActivity.class);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){

            case 1:
                if(resultCode==2){
                    String[] data1 = data.getStringArrayExtra("data");
                    Collections.addAll(mList,data1);
                    lvAdapter.notifyDataSetChanged();
                }
                break;
        }

    }
}
