package com.example.zhangzhanghengye.lx.interfaces;

import com.example.zhangzhanghengye.lx.bean.Bean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ReturnfitService {
    @FormUrlEncoded
    @POST("front/app/shopping/user/usercomment/getUserComment")
    Observable<Bean> logein(@Field("system_data") String system_data);
}
