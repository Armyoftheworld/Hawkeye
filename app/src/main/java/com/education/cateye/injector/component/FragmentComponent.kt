package com.education.cateye.injector.component

import com.education.cateye.ui.main.fragment.CatEyeFragment
import com.education.cateye.ui.main.fragment.MainFragment
import com.education.cateye.ui.main.fragment.MyFragment
import com.juziwl.commonlibrary.injector.component.ApplicationComponent
import com.juziwl.commonlibrary.injector.module.FragmentModule
import com.juziwl.commonlibrary.injector.scope.ActivityScope
import dagger.Component

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/3
 * @description
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {
    fun inject(mainFragment: MainFragment)

    fun inject(catEyeFragment: CatEyeFragment)

    fun inject(myFragment: MyFragment)
}