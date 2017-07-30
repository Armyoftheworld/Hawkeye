package com.juziwl.commonlibrary.utils;

import android.content.res.Resources;

import com.juziwl.commonlibrary.config.Global;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/7/30
 * @description 跟屏幕有关的工具类
 */
public class DisplayUtils {

    public static int dip2px(float dpValue) {
        final float scale = Global.application.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(float spValue) {
        float scale = Global.application.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        // 获得状态栏高度
        Resources resources = Global.application.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    /**
     * 获得手机屏幕高度
     */
    public static int getScreenHeight() {
        return Global.application.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return Global.application.getResources().getDisplayMetrics().widthPixels;
    }
}
