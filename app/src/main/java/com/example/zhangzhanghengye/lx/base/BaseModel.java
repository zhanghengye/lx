package com.example.zhangzhanghengye.lx.base;

import com.example.zhangzhanghengye.lx.interfaces.ReturnfitService;
import com.example.zhangzhanghengye.lx.utils.RetrofitUtils;

public interface BaseModel {
    //初始化retrofit对象
    ReturnfitService service =new RetrofitUtils().build().create(ReturnfitService.class);
}
