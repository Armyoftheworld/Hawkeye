package com.juziwl.commonlibrary.injector.provide;

import com.juziwl.commonlibrary.api.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/04/27
 * @description
 */
@Singleton
public class DataManager {
    private final ApiService apiService;

    @Inject
    public DataManager(ApiService apiService) {
        this.apiService = apiService;
    }

    /**
     *     //左侧标题栏
     public Subscription getLeftContent(Subscriber<TitileBean> subscriber) {
     return apiService.getLeftContent()
     .subscribeOn(Schedulers.io())
     .observeOn(AndroidSchedulers.mainThread())
     .subscribe(subscriber);
     }
     */
}
