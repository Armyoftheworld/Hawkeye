package com.juziwl.commonlibrary.injector.module;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.juziwl.commonlibrary.api.ApiService;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.utils.Retrofit2ConverterFactory;
import com.juziwl.commonlibrary.utils.UserPreference;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/04/25
 * @description
 */
@Module
public class ApiModule {
    private static final long OKCLIENT_DISK_CACHE_SIZE = 20 * 1024 * 1024;
    private static final String OKCLIENT_DISK_CACHE_NAME = "http-cache";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(UserPreference userPreference) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10 * 1000L, TimeUnit.MILLISECONDS)//10
                .readTimeout(20 * 1000L, TimeUnit.MILLISECONDS)//20
                .writeTimeout(30 * 1000L, TimeUnit.MILLISECONDS)//15
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request newRequest = request.newBuilder()
                            //对所有请求添加请求头
                            .addHeader("uid", userPreference.getUid())
                            .addHeader("accessToken", userPreference.getToken())
                            .method(request.method(), request.body())
                            .build();
                    return  chain.proceed(newRequest);
                })
                .addNetworkInterceptor(new StethoInterceptor())
                .cache(new Cache(new File(Global.filePath, OKCLIENT_DISK_CACHE_NAME),
                        OKCLIENT_DISK_CACHE_SIZE)); //设置缓存目录和20M缓存
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Global.UrlApi)
                .client(okHttpClient)
                .addConverterFactory(new Retrofit2ConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
