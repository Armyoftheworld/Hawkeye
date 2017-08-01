package com.education.cateye.ui.main.delegate

import com.education.cateye.R
import com.juziwl.commonlibrary.config.BaseAppDelegate
import kotlinx.android.synthetic.main.activity_main.view.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/1
 * @description
 */
class MainDelegate : BaseAppDelegate() {

    override fun initWidget() {
        rootView.tv.text = "Hello, catEye"
    }

    override fun getRootLayoutId(): Int {
        return R.layout.activity_main
    }
}