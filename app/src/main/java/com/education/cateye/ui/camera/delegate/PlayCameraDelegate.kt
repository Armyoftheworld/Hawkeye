package com.education.cateye.ui.camera.delegate

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.TextUtils
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.View
import android.widget.RelativeLayout
import com.education.cateye.R
import com.juziwl.commonlibrary.config.BaseAppDelegate
import com.juziwl.commonlibrary.utils.DialogManager
import com.juziwl.commonlibrary.utils.ToastUtils
import com.orhanobut.logger.Logger
import com.videogo.constant.Constant
import com.videogo.errorlayer.ErrorInfo
import com.videogo.exception.BaseException
import com.videogo.exception.ErrorCode
import com.videogo.openapi.EZConstants
import com.videogo.openapi.EZOpenSDK
import com.videogo.openapi.EZPlayer
import com.videogo.openapi.bean.EZCameraInfo
import com.videogo.openapi.bean.EZDeviceInfo
import com.videogo.realplay.RealPlayStatus
import com.videogo.util.ConnectionDetector
import com.videogo.util.LocalInfo
import com.videogo.util.Utils
import com.videogo.widget.CustomRect
import com.videogo.widget.CustomTouchListener
import kotlinx.android.synthetic.main.activity_playcamera.view.*
import java.util.*

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/8/5
 * @description 摄像头播放页面的delegate
 */
class PlayCameraDelegate : BaseAppDelegate(), SurfaceHolder.Callback {

    private var mRealPlaySh: SurfaceHolder? = null
    private var mEZPlayer: EZPlayer? = null
    private var mStatus = RealPlayStatus.STATUS_INIT
    private var mCurrentQulityMode = EZConstants.EZVideoLevel.VIDEO_LEVEL_SUPERCLEAR
    private var mDeviceInfo: EZDeviceInfo? = null
    private var mCameraInfo: EZCameraInfo? = null
    private var mControlDisplaySec = 0
    private var mOrientation = Configuration.ORIENTATION_LANDSCAPE
    private var mZoomScale = 0f
    // 播放比例
    private var mPlayScale = 1f
    private lateinit var mLocalInfo: LocalInfo
    private var mRealRatio = Constant.LIVE_VIEW_RATIO
    /**
     * 定时器
     */
    private var mUpdateTimer: Timer? = null
    /**
     * 定时器执行的任务
     */
    private var mUpdateTimerTask: TimerTask? = null

    val MSG_PLAY_UI_UPDATE = 200
    val MSG_PLAY_UI_REFRESH = 206
    val MSG_PREVIEW_START_PLAY = 207

    private var mHandler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            if (getActivity<Activity>().isFinishing) {
                return
            }
            when (msg?.what) {
                EZConstants.EZRealPlayConstants.MSG_GET_CAMERA_INFO_SUCCESS -> {
                    Logger.d("MSG_GET_CAMERA_INFO_SUCCESS")
                    mCameraInfo?.setVideoLevel(mCurrentQulityMode.videoLevel)
                }
                EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_START -> Logger.d("MSG_REALPLAY_PLAY_START")
                EZConstants.EZRealPlayConstants.MSG_REALPLAY_CONNECTION_START -> Logger.d("MSG_REALPLAY_CONNECTION_START")
                EZConstants.EZRealPlayConstants.MSG_REALPLAY_CONNECTION_SUCCESS -> Logger.d("MSG_REALPLAY_CONNECTION_SUCCESS")
                EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_SUCCESS -> handlePlaySuccess(msg)
                EZConstants.EZRealPlayConstants.MSG_REALPLAY_PLAY_FAIL -> handlePlayFail(msg.obj)
//                EZConstants.EZRealPlayConstants.MSG_SET_VEDIOMODE_SUCCESS -> handleSetVedioModeSuccess()
//                EZConstants.EZRealPlayConstants.MSG_SET_VEDIOMODE_FAIL -> handleSetVedioModeFail(msg.arg1)
                MSG_PLAY_UI_UPDATE -> updateRealPlayUI()
                MSG_PLAY_UI_REFRESH -> initUI()
                else -> {
                }
            }
        }
    }


    private fun updateRealPlayUI() {
        if (mControlDisplaySec == 5) {
            mControlDisplaySec = 0
            hideControlRlAndFullOperateBar(false)
        }
    }

    /**
     * 处理播放成功的情况

     * @see
     * @since V1.0
     */
    private fun handlePlaySuccess(msg: Message) {
        Logger.d("handlePlaySuccess")
        mStatus = RealPlayStatus.STATUS_PLAY

        // 声音处理
        setRealPlaySound()

        mRealRatio = Constant.LIVE_VIEW_RATIO

        initUI()
        setRealPlaySvLayout()
        startUpdateTimer()
        DialogManager.getInstance().cancelDialog()
    }

    /**
     * 处理播放失败的情况

     * @param obj - 错误码
     * *
     * @see
     * @since V1.0
     */
    private fun handlePlayFail(obj: Any?) {
        var errorCode = 0
        if (obj != null) {
            val errorInfo = obj as ErrorInfo?
            errorCode = errorInfo!!.errorCode
            Logger.e("handlePlayFail:" + errorInfo.errorCode)
        }

        stopRealPlay()

        updateRealPlayFailUI(errorCode)

        getActivity<Activity>().finish()
    }

    private fun updateRealPlayFailUI(errorCode: Int) {
        var txt: String? = null
        // 判断返回的错误码
        when (errorCode) {
            ErrorCode.ERROR_TRANSF_ACCESSTOKEN_ERROR -> {
                txt = "token过期，请重新登录"
            }
            ErrorCode.ERROR_CAS_MSG_PU_NO_RESOURCE -> txt = "设备连接数过大，停止其他连接后再试试吧"
            ErrorCode.ERROR_TRANSF_DEVICE_OFFLINE -> {
                mCameraInfo?.isShared = 0
                txt = "设备不在线"
            }
            ErrorCode.ERROR_INNER_STREAM_TIMEOUT -> txt = "播放失败，连接设备异常"
            ErrorCode.ERROR_WEB_CODE_ERROR -> {
            }
            ErrorCode.ERROR_WEB_HARDWARE_SIGNATURE_OP_ERROR -> {
            }
            ErrorCode.ERROR_TRANSF_TERMINAL_BINDING -> txt = "请在萤石客户端关闭终端绑定"
        // 收到这两个错误码，可以弹出对话框，让用户输入密码后，重新取流预览
            ErrorCode.ERROR_INNER_VERIFYCODE_NEED, ErrorCode.ERROR_INNER_VERIFYCODE_ERROR -> {
                txt = "设备信息验证失败，请重新登录"
            }
            else -> txt = "视频播放失败"
        }

        if (!TextUtils.isEmpty(txt)) {
            ToastUtils.showToast(txt)
        }
    }

    private fun initUI() {

        if (mCameraInfo != null) {
            rootView.title_bar_landscape.setTitle(mCameraInfo?.cameraName)
        }

        if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (mStatus == RealPlayStatus.STATUS_START) {
                showControlRlAndFullOperateBar()
            }
        }
    }

    private fun setRealPlaySound() {
        if (mEZPlayer != null) {
            if (mLocalInfo.isSoundOpen) {
                mEZPlayer?.openSound()
            } else {
                mEZPlayer?.closeSound()
            }
        }
    }

    override fun initWidget() {
        mRealPlaySh = rootView.realplay_sv.holder
        mRealPlaySh?.addCallback(this)
        rootView.title_bar_landscape.setStyle(Color.rgb(0xff, 0xff, 0xff), rootView.resources.getDrawable(R.color.dark_bg_70p),
                rootView.resources.getDrawable(R.drawable.message_back_selector))
        rootView.title_bar_landscape.addLeftButton(R.drawable.message_back_selector, {
            getActivity<Activity>().onBackPressed()
        })
        rootView.realplay_sv.setOnTouchListener(mRealPlayTouchListener)

        // 获取配置信息操作对象
        mLocalInfo = LocalInfo.getInstance()
        // 获取屏幕参数, 由于我给Activity设置的landscape，所以获取的是横屏状态下的宽高，一次这里宽高要颠倒
        mLocalInfo.setScreenWidthHeight(rootView.resources.displayMetrics.heightPixels, rootView.resources.displayMetrics.widthPixels)
        mLocalInfo.navigationBarHeight = Math.ceil((25 * rootView.resources.displayMetrics.density).toDouble()).toInt()

        setRealPlaySvLayout()
    }

    fun startRealPlay(mDeviceInfo: EZDeviceInfo, mCameraInfo: EZCameraInfo) {
        this.mDeviceInfo = mDeviceInfo
        this.mCameraInfo = mCameraInfo
        if (mStatus == RealPlayStatus.STATUS_START || mStatus == RealPlayStatus.STATUS_PLAY) {
            return
        }

        // 检查网络是否可用
        if (!ConnectionDetector.isNetworkAvailable(getActivity())) {
            // 提示没有连接网络
            ToastUtils.showToast(com.juziwl.commonlibrary.R.string.common_useless_network)
            getActivity<Activity>().finish()
            return
        }

        mStatus = RealPlayStatus.STATUS_START
        setRealPlayLoadingUI()

        if (mEZPlayer == null) {
            mEZPlayer = EZOpenSDK.getInstance().createPlayer(mCameraInfo.deviceSerial, mCameraInfo.cameraNo)
        }

        if (mEZPlayer == null)
            return
        mEZPlayer!!.setHandler(mHandler)
        mEZPlayer!!.setSurfaceHold(mRealPlaySh)
        mEZPlayer!!.startRealPlay()
    }

    /**
     * 停止播放

     * @see
     * @since V1.0
     */
    fun stopRealPlay() {
        Logger.d("stopRealPlay")
        mStatus = RealPlayStatus.STATUS_STOP

        stopUpdateTimer()
        if (mEZPlayer != null) {
            mEZPlayer?.stopRealPlay()
        }
    }

    /**
     * 启动定时器

     * @see
     * @since V1.0
     */
    private fun startUpdateTimer() {
        stopUpdateTimer()
        // 开始录像计时
        mUpdateTimer = Timer()
        mUpdateTimerTask = object : TimerTask() {
            override fun run() {
                if (rootView.title_bar_landscape != null && rootView.title_bar_landscape.visibility == View.VISIBLE
                        && mControlDisplaySec < 5) {
                    mControlDisplaySec++
                }
                mHandler.sendEmptyMessage(MSG_PLAY_UI_UPDATE)
            }
        }
        // 延时1000ms后执行，1000ms执行一次
        mUpdateTimer?.schedule(mUpdateTimerTask, 0, 1000)
    }

    /**
     * 停止定时器

     * @see
     * @since V1.0
     */
    private fun stopUpdateTimer() {
        mHandler.removeMessages(MSG_PLAY_UI_UPDATE)
        // 停止录像计时
        mUpdateTimer?.cancel()
        mUpdateTimer = null

        mUpdateTimerTask?.cancel()
        mUpdateTimerTask = null
    }

    private fun setRealPlayLoadingUI() {
        rootView.realplay_sv.visibility = View.INVISIBLE
        rootView.realplay_sv.visibility = View.VISIBLE
        DialogManager.getInstance().createLoadingDialog(getActivity(), getActivity<Activity>()
                .getString(com.juziwl.commonlibrary.R.string.common_onloading)).show()
        showControlRlAndFullOperateBar()
    }

    private fun showControlRlAndFullOperateBar() {
        rootView.title_bar_landscape.visibility = View.VISIBLE
        mControlDisplaySec = 0
    }

    private fun setRealPlaySvLayout() {
        // 设置播放窗口位置
        val screenWidth = mLocalInfo.screenWidth
        val screenHeight = if (mOrientation == Configuration.ORIENTATION_PORTRAIT)
            mLocalInfo.screenHeight - mLocalInfo.navigationBarHeight
        else
            mLocalInfo.screenHeight
        val realPlaySvlp = Utils.getPlayViewLp(mRealRatio.toDouble(), mOrientation,
                mLocalInfo.screenWidth, (mLocalInfo.screenWidth * Constant.LIVE_VIEW_RATIO).toInt(),
                screenWidth, screenHeight)

        val svLp = RelativeLayout.LayoutParams(realPlaySvlp.width, realPlaySvlp.height)
        rootView.realplay_sv.layoutParams = svLp
        mRealPlayTouchListener.setSacaleRect(Constant.MAX_SCALE, 0, 0, realPlaySvlp.width, realPlaySvlp.height)
        setPlayScaleUI(1f, null, null)
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        if (mEZPlayer != null) {
            mEZPlayer?.setSurfaceHold(null)
        }
        mRealPlaySh = null
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        if (mEZPlayer != null) {
            mEZPlayer?.setSurfaceHold(holder)
        }
        mRealPlaySh = holder
    }

    private var mRealPlayTouchListener: CustomTouchListener = object : CustomTouchListener() {

        override fun canZoom(scale: Float): Boolean = mStatus == RealPlayStatus.STATUS_PLAY

        override fun canDrag(direction: Int): Boolean {
            if (mStatus != RealPlayStatus.STATUS_PLAY) {
                return false
            }
            if (mEZPlayer != null && mDeviceInfo != null) {
                // 出界判断
                if (CustomTouchListener.DRAG_LEFT == direction || CustomTouchListener.DRAG_RIGHT == direction) {
                    // 左移/右移出界判断
                    return mDeviceInfo!!.isSupportPTZ
                } else if (CustomTouchListener.DRAG_UP == direction || CustomTouchListener.DRAG_DOWN == direction) {
                    // 上移/下移出界判断
                    return mDeviceInfo!!.isSupportPTZ
                }
            }
            return false
        }

        override fun onSingleClick() {
            onRealPlaySvClick()
        }

        override fun onDoubleClick(e: MotionEvent) {}

        override fun onZoom(scale: Float) {
            if (mEZPlayer != null && mDeviceInfo != null && mDeviceInfo!!.isSupportZoom) {
                startZoom(scale)
            }
        }

        override fun onDrag(direction: Int, distance: Float, rate: Float) {
        }

        override fun onEnd(mode: Int) {
            if (mEZPlayer != null && mDeviceInfo != null && mDeviceInfo!!.isSupportZoom) {
                stopZoom()
            }
        }

        override fun onZoomChange(scale: Float, oRect: CustomRect, curRect: CustomRect) {
            var scale = scale
            if (mEZPlayer != null && mDeviceInfo != null && mDeviceInfo!!.isSupportZoom) {
                //采用云台调焦
                return
            }
            if (mStatus == RealPlayStatus.STATUS_PLAY) {
                if (scale > 1.0f && scale < 1.1f) {
                    scale = 1.1f
                }
                setPlayScaleUI(scale, oRect, curRect)
            }
        }
    }

    private fun startZoom(scale: Float) {
        if (mEZPlayer == null) {
            return
        }

        hideControlRlAndFullOperateBar(false)
        val preZoomIn = mZoomScale > 1.01
        val zoomIn = scale > 1.01
        if (mZoomScale != 0f && preZoomIn != zoomIn) {
            mZoomScale = 0f
        }
        if (scale != 0f && (mZoomScale == 0f || preZoomIn != zoomIn)) {
            mZoomScale = scale
        }
    }


    private fun stopZoom() {
        if (mEZPlayer == null) {
            return
        }
        if (mZoomScale != 0f) {
            mZoomScale = 0f
        }
    }

    private fun hideControlRlAndFullOperateBar(excludeLandscapeTitle: Boolean) {
        if (excludeLandscapeTitle && mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            rootView.title_bar_landscape.visibility = View.VISIBLE
        } else {
            rootView.title_bar_landscape.visibility = View.GONE
        }
    }

    private fun setPlayScaleUI(scale: Float, oRect: CustomRect?, curRect: CustomRect?) {
        if (scale == 1f) {
            if (mPlayScale == scale) {
                return
            }
            try {
                if (mEZPlayer != null) {
                    mEZPlayer?.setDisplayRegion(false, null, null)
                }
            } catch (e: BaseException) {
                e.printStackTrace()
            }

        } else {
            if (mPlayScale == scale) {
                try {
                    if (mEZPlayer != null) {
                        mEZPlayer?.setDisplayRegion(true, oRect, curRect)
                    }
                } catch (e: BaseException) {
                    e.printStackTrace()
                }
                return
            }
            hideControlRlAndFullOperateBar(false)
            try {
                if (mEZPlayer != null) {
                    mEZPlayer?.setDisplayRegion(true, oRect, curRect)
                }
            } catch (e: BaseException) {
                e.printStackTrace()
            }
        }
        mPlayScale = scale
    }

    private fun onRealPlaySvClick() {
        if (mCameraInfo != null && mEZPlayer != null && mDeviceInfo != null) {
            if (mDeviceInfo?.status != 1) {
                return
            }
            setRealPlayFullOperateBarVisibility()
        }
    }

    private fun setRealPlayFullOperateBarVisibility() {
        if (rootView.title_bar_landscape.visibility == View.VISIBLE) {
            rootView.title_bar_landscape.visibility = View.GONE
        } else {
            rootView.title_bar_landscape.visibility = View.VISIBLE
            mControlDisplaySec = 0
        }
    }

    fun onScreenOff() {
        if (mStatus != RealPlayStatus.STATUS_STOP) {
            stopRealPlay()
            mStatus = RealPlayStatus.STATUS_PAUSE
        }
    }

    fun onActivityStop() {

        if (mCameraInfo == null) {
            return
        }
        if (mStatus != RealPlayStatus.STATUS_STOP) {
            stopRealPlay()
            mStatus = RealPlayStatus.STATUS_PAUSE
        }
        rootView.realplay_sv.visibility = View.INVISIBLE
    }

    fun onActivityDestory() {
        mEZPlayer?.release()
    }

    override fun getRootLayoutId(): Int = R.layout.activity_playcamera
}