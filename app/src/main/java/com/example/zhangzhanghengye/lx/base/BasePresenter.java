package com.example.zhangzhanghengye.lx.base;

public class BasePresenter<M, V> {
    public M mModel;
    public V mView;

    public void setMV(M m, V v) {
        this.mModel = m;
        this.mView = v;
    }
}
