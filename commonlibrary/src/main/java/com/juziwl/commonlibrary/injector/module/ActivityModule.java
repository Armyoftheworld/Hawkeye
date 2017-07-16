package com.juziwl.commonlibrary.injector.module;

import android.content.Context;

import com.juziwl.commonlibrary.config.BaseActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/04/27
 * @description
 */
@Module
public class ActivityModule {
    private BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return baseActivity;
    }



}
