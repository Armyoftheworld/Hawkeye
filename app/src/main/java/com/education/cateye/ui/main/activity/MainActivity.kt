package com.education.cateye.ui.main.activity

import com.education.cateye.config.MainBaseActivity
import com.education.cateye.ui.main.delegate.MainDelegate

class MainActivity : MainBaseActivity<MainDelegate>() {

    override fun initEventAndData() {
    }

    override fun getDelegateClass(): Class<MainDelegate> {
        return MainDelegate::class.java
    }

    override fun initCustomTopbar() {
    }

    override fun injectActivity() {
        activityComponent.inject(this)
    }

}
