package com.education.cateye.ui.login.activity

import android.os.Bundle
import com.education.cateye.config.MainBaseActivity
import com.education.cateye.ui.login.delegate.LoginDelegate
import com.education.cateye.ui.main.activity.MainActivity

class LoginActivity : MainBaseActivity<LoginDelegate>() {

    companion object {
        val LOGIN = "LoginActivity_Login"
    }

    override fun initEventAndData() {
    }

    override fun onInteractive(action: String?, data: Bundle?) {
        when (action) {
            LOGIN ->
                openActivity(MainActivity::class.java)
        }
    }

    override fun getDelegateClass(): Class<LoginDelegate> = LoginDelegate::class.java

    override fun isShowToolBar(): Boolean = false
    override fun initCustomTopbar() {}

    override fun injectActivity() {
        activityComponent.inject(this)
    }
}
