package com.education.cateye.ui.camera.activity

import android.view.WindowManager
import com.education.cateye.config.MainBaseActivity
import com.education.cateye.ui.camera.delegate.PlayCameraDelegate
import com.videogo.constant.IntentConsts
import com.videogo.openapi.bean.EZCameraInfo
import com.videogo.openapi.bean.EZDeviceInfo

class PlayCameraActivity : MainBaseActivity<PlayCameraDelegate>() {

    override fun initEventAndData() {
        // 保持屏幕常亮
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        fullScreen(true)
    }

    private fun fullScreen(enable: Boolean) {
        if (enable) {
            val lp = window.attributes
            lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
            window.attributes = lp
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        } else {
            val attr = window.attributes
            attr.flags = attr.flags and WindowManager.LayoutParams.FLAG_FULLSCREEN.inv()
            window.attributes = attr
            window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    override fun onResume() {
        super.onResume()
        viewDelegate.startRealPlay(intent.extras)
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
        viewDelegate.onActivityStop()
    }

    override fun onDestroy() {
        viewDelegate.onActivityDestory()
        super.onDestroy()
    }

    override fun isShowToolBar(): Boolean = false

    override fun getDelegateClass(): Class<PlayCameraDelegate> = PlayCameraDelegate::class.java

    override fun initCustomTopbar() {}

    override fun injectActivity() {
        activityComponent.inject(this)
    }
}
