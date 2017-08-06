package com.education.cateye.ui.camera.delegate

import android.os.Bundle
import com.education.cateye.R
import com.education.cateye.utils.CameraPlayer
import com.education.cateye.utils.EzvizCameraPlayer
import com.juziwl.commonlibrary.config.BaseAppDelegate

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/5
 * @description 摄像头播放页面的delegate
 */
class PlayCameraDelegate : BaseAppDelegate() {

    private val cameraPlayer: CameraPlayer = EzvizCameraPlayer()

    override fun initWidget() {
        cameraPlayer.initWeight(rootView)
    }

    fun startRealPlay(bundle: Bundle) {
        cameraPlayer.startPlayer(bundle)
    }

    fun onActivityStop() {
        cameraPlayer.stopPlayer()
    }

    fun onActivityDestory() {
        cameraPlayer.release()
    }

    override fun getRootLayoutId(): Int = R.layout.activity_playcamera
}