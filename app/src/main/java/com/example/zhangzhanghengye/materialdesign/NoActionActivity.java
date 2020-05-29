package com.example.zhangzhanghengye.materialdesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.zhangzhanghengye.lx.R;

public class NoActionActivity extends AppCompatActivity {

    private Toolbar mTobar;
    private DrawerLayout mDraw;
    private FloatingActionButton mFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_action);
        mTobar = findViewById(R.id.tobar);
        setSupportActionBar(mTobar);
        mDraw = findViewById(R.id.draw);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeAsUpIndicator(R.drawable.shoucang);
        }

        mFab = findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"aa",Snackbar.LENGTH_SHORT)
                        .setAction("uodo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(NoActionActivity.this, "uodo", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.a:
                Toast.makeText(this, "a", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b:
                Toast.makeText(this, "b", Toast.LENGTH_SHORT).show();
                break;
            case R.id.c:
                Toast.makeText(this, "c", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDraw.openDrawer(GravityCompat.START);
                break;
        }


        return true;
    }
}
