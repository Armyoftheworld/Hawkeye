package com.education.cateye.config

import android.app.Application
import com.education.cateye.BuildConfig
import com.juziwl.commonlibrary.config.Global
import com.juziwl.commonlibrary.injector.component.ApplicationComponent
import com.juziwl.commonlibrary.injector.component.DaggerApplicationComponent
import com.juziwl.commonlibrary.injector.module.ApplicationModule
import com.lzy.okgo.OkGo
import com.lzy.okgo.interceptor.HttpLoggingInterceptor
import com.orhanobut.logger.LogLevel
import com.orhanobut.logger.Logger
import com.taobao.sophix.SophixManager
import com.videogo.openapi.EZOpenSDK
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import java.util.logging.Level


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/7/16
 * @description 自定义Application
 */
class App : Application() {

    lateinit var applicationComponent: ApplicationComponent

    companion object {
        lateinit var instance: App

        val Ezviz_APPkey = "727fcd76895e49e18f3a895dbba29843"
        val Ezviz_APPSecret = "56d171fb892daaf0e89c90325a4748bb"
    }

    override fun onCreate() {
        super.onCreate()

        Global.application = this

        instance = this
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()

        initLogger()
        initSophix()
        initOkgo()
        initEzviz()

    }

    private fun initEzviz() {
        /**
         * sdk日志开关，正式发布需要去掉
         */
        EZOpenSDK.showSDKLog(BuildConfig.DEBUG)
        /**
         * 设置是否支持P2P取流,详见api
         */
        EZOpenSDK.enableP2P(false)

        /**
         * APP_KEY请替换成自己申请的
         */
        EZOpenSDK.initLib(this, Ezviz_APPkey, "")
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