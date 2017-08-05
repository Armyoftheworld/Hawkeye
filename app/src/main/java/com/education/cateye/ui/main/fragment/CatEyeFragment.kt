package com.education.cateye.ui.main.fragment

import android.os.Bundle
import android.view.View
import com.education.cateye.config.MainBaseFragment
import com.education.cateye.ui.camera.activity.PlayCameraActivity
import com.education.cateye.ui.main.delegate.CatEyeFragmentDelegate
import com.videogo.constant.IntentConsts
import com.videogo.openapi.EZOpenSDK
import com.videogo.openapi.bean.EZCameraInfo
import com.videogo.openapi.bean.EZDeviceInfo
import org.jetbrains.anko.support.v4.startActivity

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/3
 * @description
 */
class CatEyeFragment : MainBaseFragment<CatEyeFragmentDelegate>() {

    companion object {
        val WATCHCAMERA = "CatEyeFragment_watchCamera"
    }

    override fun commonLoad(view: View?) {
        viewDelegate.setBanner(arrayListOf("http://api.imexue.com/data/news/image/normal/2017/07/28/2017072814571457142404.jpg",
                "http://api.imexue.com/data/news/image/normal/2017/08/02/2017080211554355431782.jpg",
                "http://admin.imexue.com/data/news/image/normal/2017/08/05/201708051008398391285.png",
                "http://admin.imexue.com/data/news/image/normal/2017/08/04/2017080410271327138991.jpg",
                "http://admin.imexue.com/data/news/image/normal/2017/07/27/2017072717174017409564.jpg"))
        viewDelegate.setSchoolCamera()
    }

    override fun onInteractive(action: String?, bundle: Bundle?) {
        when (action) {
            WATCHCAMERA -> {
                EZOpenSDK.getInstance().setAccessToken("at.4soz5aqd6ra1bawv9w9pl7wj3tgm86wx-4g1lev2z4i-1y2tqve-598fnkkla")
                val mDeviceInfo = EZDeviceInfo()
                mDeviceInfo.deviceSerial = "781813414"
                mDeviceInfo.deviceName = "C2C(781813414)"
                mDeviceInfo.status = 1
                val mCameraInfo = EZCameraInfo() // 重新配置数据
                mCameraInfo.deviceSerial = "781813414"
                mCameraInfo.cameraName = "C2C(781813414)"
                mCameraInfo.setVideoLevel(3)
                mCameraInfo.cameraCover = "https://i.ys7.com/assets/imgs/public/homeDevice.jpeg";
                mCameraInfo.cameraNo = 1
                mCameraInfo.isShared = 0
                startActivity<PlayCameraActivity>(IntentConsts.EXTRA_CAMERA_INFO to mCameraInfo, IntentConsts.EXTRA_DEVICE_INFO to mDeviceInfo)
            }
        }
    }

    override fun getDelegateClass(): Class<CatEyeFragmentDelegate> {
        return CatEyeFragmentDelegate::class.java
    }

    override fun injectFragment() {
        fragmentComponent.inject(this)
    }
}