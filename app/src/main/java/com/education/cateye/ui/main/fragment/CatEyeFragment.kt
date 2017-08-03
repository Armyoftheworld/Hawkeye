package com.education.cateye.ui.main.fragment

import com.education.cateye.config.MainBaseFragment
import com.education.cateye.ui.main.delegate.CatEyeFragmentDelegate

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/3
 * @description
 */
class CatEyeFragment : MainBaseFragment<CatEyeFragmentDelegate>() {
    override fun getDelegateClass(): Class<CatEyeFragmentDelegate> {
        return CatEyeFragmentDelegate::class.java
    }

    override fun injectFragment() {
        fragmentComponent.inject(this)
    }
}