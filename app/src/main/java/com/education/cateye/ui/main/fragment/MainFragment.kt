package com.education.cateye.ui.main.fragment

import com.education.cateye.config.MainBaseFragment
import com.education.cateye.ui.main.delegate.MainFragmentDelegate

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/3
 * @description
 */
class MainFragment : MainBaseFragment<MainFragmentDelegate>() {
    override fun getDelegateClass(): Class<MainFragmentDelegate> {
        return MainFragmentDelegate::class.java
    }

    override fun injectFragment() {
        fragmentComponent.inject(this)
    }
}