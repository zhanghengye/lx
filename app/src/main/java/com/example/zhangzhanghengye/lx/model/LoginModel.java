package com.example.zhangzhanghengye.lx.model;

import com.example.zhangzhanghengye.lx.bean.Bean;
import com.example.zhangzhanghengye.lx.interfaces.DataCallback;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class LoginModel implements MyModel {
    //具体步骤实现
    @Override
    public void login(String phone, final DataCallback dataCallback) {
        //这里面是网络请求
        service.logein(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bean bean) {
                        dataCallback.okData(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        dataCallback.errorData(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
