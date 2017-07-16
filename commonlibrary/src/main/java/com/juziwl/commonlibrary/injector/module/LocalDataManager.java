package com.juziwl.commonlibrary.injector.module;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.juziwl.commonlibrary.dao.DaoMaster;
import com.juziwl.commonlibrary.dao.DaoSession;
import com.juziwl.commonlibrary.utils.UserPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/04/25
 * @description
 */
@Module
public class LocalDataManager {
    private final String DATA_BASE_NAME = "exue.db";

    @Provides
    @Singleton
    public DaoSession provideDaoSession(Application context) {
        MyOpenHelper devOpenHelper = new MyOpenHelper(context, DATA_BASE_NAME);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        return daoMaster.newSession();
    }

    class MyOpenHelper extends DaoMaster.OpenHelper {

        public MyOpenHelper(Context context, String name) {
            super(context, name);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            MigrationHelper.migrate(db, <dao>);
        }
    }

    @Provides
    @Singleton
    public UserPreference provideUserPreference(Application application){
        return new UserPreference(application);
    }
}
