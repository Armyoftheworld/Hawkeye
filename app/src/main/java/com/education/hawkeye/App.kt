package com.education.hawkeye

import android.app.Application
import com.orhanobut.logger.Logger
import com.taobao.sophix.SophixManager

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/7/16
 * @description
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        SophixManager.INSTANCE.setAesKey(null)
                .setAppVersion(packageManager.getPackageInfo(packageName, 0).versionName)
                .setContext(this)
                .setEnableDebug(BuildConfig.DEBUG)
                .setPatchLoadStatusStub { mode, code, info, handlePatchVersion ->
                    Logger.e("mode = $mode,code = $code,info = $info, handlePatchVersion = $handlePatchVersion")
                }.initialize()
        SophixManager.INSTANCE.queryAndLoadNewPatch()

    }
}