package com.education.cateye.injector.component

import com.education.cateye.ui.camera.activity.PlayCameraActivity
import com.education.cateye.ui.login.activity.LoginActivity
import com.education.cateye.ui.main.activity.MainActivity
import com.juziwl.commonlibrary.injector.component.ApplicationComponent
import com.juziwl.commonlibrary.injector.module.ActivityModule
import com.juziwl.commonlibrary.injector.scope.ActivityScope
import dagger.Component

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/1
 * @description
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(loginActivity: LoginActivity)
    fun inject(playCameraActivity: PlayCameraActivity)
}