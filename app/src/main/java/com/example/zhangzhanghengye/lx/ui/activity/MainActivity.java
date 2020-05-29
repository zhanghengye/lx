package com.example.zhangzhanghengye.lx.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;

import com.example.zhangzhanghengye.lx.R;
import com.example.zhangzhanghengye.lx.adapter.ViewPagerAdapter;
import com.example.zhangzhanghengye.lx.base.BaseActivity;
import com.example.zhangzhanghengye.lx.bean.Bean;
import com.example.zhangzhanghengye.lx.interfaces.MenuData;
import com.example.zhangzhanghengye.lx.model.LoginModel;
import com.example.zhangzhanghengye.lx.presenter.LoginPresenter;
import com.example.zhangzhanghengye.lx.ui.fragment.OneFragment;
import com.example.zhangzhanghengye.lx.ui.fragment.TwoFragment;
import com.example.zhangzhanghengye.lx.view.MyView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<LoginPresenter, LoginModel> implements MyView {
    private ViewPager mFragmentPager;
    private TabLayout mTablayout;
    private String[] titles={"未评论","已评论"};
    private List<Fragment> fragments=new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void initData() {
        //{"imei":"aimei867247024497603","location_address":"朝阳区","phone_brand":"Meizu","phone_os":"5.1","phone_system":"Android","uid":"670","version_number":"3","versions":"2.0.5"}
        //mPresenter.viewStart("北京");
       // mPresenter.viewStart("{\"imei\":\"aimei867247024497603\",\"location_address\":\"朝阳区\",\"phone_brand\":\"Meizu\",\"phone_os\":\"5.1\",\"phone_system\":\"Android\",\"uid\":\"670\",\"version_number\":\"3\",\"versions\":\"2.0.5\"}");
        mFragmentPager = findViewById(R.id.fragment_pager);
        mTablayout = findViewById(R.id.tablayout);
        fragments.add(new OneFragment());
        fragments.add(new TwoFragment());
        viewPagerAdapter = new ViewPagerAdapter(titles, fragments, getSupportFragmentManager());
        mFragmentPager.setAdapter(viewPagerAdapter);
        mTablayout.setupWithViewPager(mFragmentPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void succ(String str) {
        Log.e("TAG", "succ: " + str);
    }

    @Override
    public void fail(Object string) {
        Bean bean = (Bean) string;
        Log.e("TAG", "fail: " + bean.getCode());
    }
}
