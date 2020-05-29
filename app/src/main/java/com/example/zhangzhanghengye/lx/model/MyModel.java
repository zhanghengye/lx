package com.example.zhangzhanghengye.lx.model;

import com.example.zhangzhanghengye.lx.base.BaseModel;
import com.example.zhangzhanghengye.lx.interfaces.DataCallback;

//某一功能的m层接口类
public interface MyModel extends BaseModel {

    //一个网络请求
    void login(String phone,  DataCallback dataCallback);

}
