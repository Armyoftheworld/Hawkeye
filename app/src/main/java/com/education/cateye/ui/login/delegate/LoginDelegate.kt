package com.education.cateye.ui.login.delegate

import com.education.cateye.R
import com.education.cateye.ui.login.activity.LoginActivity
import com.juziwl.commonlibrary.config.BaseAppDelegate
import kotlinx.android.synthetic.main.activity_login.view.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/5
 * @description 登录界面的delegate
 */
class LoginDelegate : BaseAppDelegate() {

    override fun initWidget() {
        click(rootView.btn_login, {
            interactiveListener.onInteractive(LoginActivity.LOGIN, null)
        })
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_login
    }
}