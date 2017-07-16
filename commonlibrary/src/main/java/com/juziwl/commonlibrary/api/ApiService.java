package com.juziwl.commonlibrary.api;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/04/25
 * @description
 */
public interface ApiService {

    @GET("api/v2/sysParam")
    Flowable<String> getSysParam();

    @GET("api/v2/startinfo")
    Flowable<String> getStartInfo();

    @POST("api/v2/users/tokens")
    Flowable<String> login(@Body String json);




}
