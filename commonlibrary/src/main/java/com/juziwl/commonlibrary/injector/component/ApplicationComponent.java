package com.juziwl.commonlibrary.injector.component;

import android.app.Application;

import com.juziwl.commonlibrary.api.ApiService;
import com.juziwl.commonlibrary.dao.DaoSession;
import com.juziwl.commonlibrary.injector.module.ApiModule;
import com.juziwl.commonlibrary.injector.module.ApplicationModule;
import com.juziwl.commonlibrary.injector.module.LocalDataManager;
import com.juziwl.commonlibrary.injector.provide.DataManager;
import com.juziwl.commonlibrary.utils.UserPreference;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/04/25
 * @description
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class, LocalDataManager.class})
public interface ApplicationComponent {
    Application application();

    ApiService apiservice();

    DaoSession daosession();

    DataManager datamanager();

    UserPreference userpreference();
}
