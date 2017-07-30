package com.juziwl.commonlibrary.config;

import android.app.Application;
import android.os.Environment;

/**
 * Created by ztn on
 * 全局数据存放处 广播action 消息 url地址等等
 */
public class Global {
    public static Application application;//在application里面赋值

    public static final String ENCODING = "UTF-8";
    public static final String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/catEye/";

    //统一图片压缩宽高
    public static final int imgWidth = 960;
    public static final int imgHeight = 1280;
    //语音存放路径
    public static final String VOICEPATH = filePath + "voice/";

    //视频存放路径
    public static final String VIDEOPATH = filePath + "video/";

    //图片保存
    public static final String SAVEPICTURE = filePath + "savepictures/";

    //选图片缓存的图片
    public static final String imgPath = Global.filePath + "pickImgCache/";

    //微信的appid
    public static String APP_ID = "";

    public static String baseURL = "";
}
