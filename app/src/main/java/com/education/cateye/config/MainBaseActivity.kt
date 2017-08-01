package com.education.cateye.config

import com.education.cateye.injector.component.DaggerActivityComponent
import com.juziwl.commonlibrary.config.BaseActivity
import com.juziwl.commonlibrary.config.BaseAppDelegate

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/1
 * @description
 */
abstract class MainBaseActivity<T : BaseAppDelegate> : BaseActivity<T>() {
    var activityComponent = DaggerActivityComponent.builder().applicationComponent(App.instance.applicationComponent).build()
}