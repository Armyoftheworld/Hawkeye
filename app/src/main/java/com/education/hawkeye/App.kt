package com.education.hawkeye

import android.app.Application
import com.juziwl.commonlibrary.config.Global
import com.lzy.okgo.OkGo
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger
import com.taobao.sophix.SophixManager
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/7/16
 * @description
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Global.application = this

        initSophix()
        initOkgo()
        initLogger()

    }

    private fun initSophix() {
        SophixManager.INSTANCE.setAesKey(null)
                .setAppVersion(packageManager.getPackageInfo(packageName, 0).versionName)
                .setContext(this)
                .setEnableDebug(BuildConfig.DEBUG)
                .setPatchLoadStatusStub { mode, code, info, handlePatchVersion ->
                    Logger.e("mode = $mode,code = $code,info = $info, handlePatchVersion = $handlePatchVersion")
                }.initialize()
        SophixManager.INSTANCE.queryAndLoadNewPatch()
    }

    private fun initOkgo() {
        val builder = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor("OkGo")
        loggingInterceptor.setColorLevel(Level.INFO)
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        builder.addInterceptor(loggingInterceptor)

        //全局的读取超时时间
        builder.readTimeout(20_000, TimeUnit.MILLISECONDS)
        //全局的写入超时时间
        builder.writeTimeout(15_000, TimeUnit.MILLISECONDS)
        //全局的连接超时时间
        builder.connectTimeout(10_000, TimeUnit.MILLISECONDS)

        val okGo = OkGo.getInstance().init(this)
        okGo.okHttpClient = builder.build()
        okGo.retryCount = 0
    }


    private fun initLogger() {
        Logger.init("catEye")
                .logLevel(if (BuildConfig.DEBUG) LogLevel.FULL else LogLevel.NONE)
                .hideThreadInfo()
                .methodCount(0)
    }
}