package com.education.cateye.utils

import android.os.Bundle
import android.view.View

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/6
 * @description 摄像头播放类的接口
 */
interface CameraPlayer {
    fun initWeight(rootView: View)

    fun startPlayer(bundle: Bundle)

    fun stopPlayer()

    fun release()
}