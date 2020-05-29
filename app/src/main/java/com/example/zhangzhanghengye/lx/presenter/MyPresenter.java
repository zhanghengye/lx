package com.example.zhangzhanghengye.lx.presenter;

import com.example.zhangzhanghengye.lx.base.BasePresenter;
import com.example.zhangzhanghengye.lx.model.MyModel;
import com.example.zhangzhanghengye.lx.view.MyView;

public abstract class MyPresenter extends BasePresenter<MyModel, MyView> {

    public abstract void viewStart(String phone);
}
