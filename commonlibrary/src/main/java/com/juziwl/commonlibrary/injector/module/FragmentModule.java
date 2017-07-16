package com.juziwl.commonlibrary.injector.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/4/30
 * @description
 */
@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    Activity provideActivity(){
        return fragment.getActivity();
    }
}
