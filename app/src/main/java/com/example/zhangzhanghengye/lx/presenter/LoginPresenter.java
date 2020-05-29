package com.example.zhangzhanghengye.lx.presenter;

import com.example.zhangzhanghengye.lx.bean.Bean;
import com.example.zhangzhanghengye.lx.interfaces.DataCallback;

public class LoginPresenter extends MyPresenter {


    @Override
    public void viewStart(String phone) {
        //
        mModel.login(phone,  new DataCallback<Bean>() {
            @Override
            public void okData(Bean s) {
                mView.fail(s);
            }
            @Override
            public void errorData(String s) {
                mView.succ(s);
            }
        });
    }
}
