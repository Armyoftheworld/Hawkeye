package com.education.cateye.ui.main.fragment

import com.education.cateye.config.MainBaseFragment
import com.education.cateye.ui.main.delegate.MainFragmentDelegate
import com.education.cateye.ui.main.delegate.MyFragmentDelegate

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/3
 * @description
 */
class MyFragment : MainBaseFragment<MyFragmentDelegate>() {
    override fun getDelegateClass(): Class<MyFragmentDelegate> {
        return MyFragmentDelegate::class.java
    }

    override fun injectFragment() {
        fragmentComponent.inject(this)
    }
}