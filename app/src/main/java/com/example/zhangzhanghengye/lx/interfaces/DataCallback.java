package com.example.zhangzhanghengye.lx.interfaces;

public interface DataCallback<T> {

    void okData(T t);

    void errorData(String s);
}
