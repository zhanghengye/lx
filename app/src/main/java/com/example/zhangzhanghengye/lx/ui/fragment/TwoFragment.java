package com.example.zhangzhanghengye.lx.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.zhangzhanghengye.lx.R;
import com.example.zhangzhanghengye.lx.adapter.TwoFragmentRecyclerAdapter;
import com.example.zhangzhanghengye.lx.base.BaseFragment;
import com.example.zhangzhanghengye.lx.bean.Bean;
import com.example.zhangzhanghengye.lx.model.LoginModel;
import com.example.zhangzhanghengye.lx.presenter.LoginPresenter;
import com.example.zhangzhanghengye.lx.view.MyView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseFragment<LoginPresenter, LoginModel> implements MyView {

    private RecyclerView mTwoRecy;
    private TwoFragmentRecyclerAdapter twoFragmentRecyclerAdapter;

    @Override
    protected void initView(View inflate) {
        mTwoRecy = inflate.findViewById(R.id.two_recy);
    }

    @Override
    protected void initData() {
        mPresenter.viewStart("{\"imei\":\"aimei867247024497603\",\"location_address\":\"朝阳区\",\"phone_brand\":\"Meizu\",\"phone_os\":\"5.1\",\"phone_system\":\"Android\",\"uid\":\"672\",\"version_number\":\"3\",\"versions\":\"2.0.5\"}");
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_two;
    }

    @Override
    public void succ(String str) {
        Log.e("TAG", "error "+str );
    }

    @Override
    public void fail(Object string) {
        Bean bean= (Bean) string;
        Log.e("TAG", bean.getCode()+"----"+bean.getData().size());
        twoFragmentRecyclerAdapter = new TwoFragmentRecyclerAdapter(context, bean.getData());
        mTwoRecy.setLayoutManager(new LinearLayoutManager(context));
        mTwoRecy.setAdapter(twoFragmentRecyclerAdapter);
        twoFragmentRecyclerAdapter.setMyOnCilk(new TwoFragmentRecyclerAdapter.MyOnCilk() {
            @Override
            public void onClik(int i) {
                Log.e("TAG", "onClik: "+i );
            }
        });

    }
}
