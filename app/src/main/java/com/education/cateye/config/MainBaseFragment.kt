package com.education.cateye.config

import com.education.cateye.injector.component.DaggerFragmentComponent
import com.juziwl.commonlibrary.config.BaseAppDelegate
import com.juziwl.commonlibrary.config.BaseFragment

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/1
 * @description 不要在此类做其他操作，此类的作用只是获取fragmentComponent
 */
abstract class MainBaseFragment<T : BaseAppDelegate> : BaseFragment<T>() {
    val fragmentComponent = DaggerFragmentComponent.builder().applicationComponent(App.instance.applicationComponent).build()
}