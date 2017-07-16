package com.juziwl.commonlibrary.utils;

public class NoFastClickUtils {

    private static long lastClickTime = 0;//上次点击的时间

    private static int spaceTime = 500;//时间间隔 0.5 秒

    public static boolean isFastClick() {

        long currentTime = System.currentTimeMillis();//当前系统时间

        boolean isAllowClick;//是否允许点击

        if (currentTime - lastClickTime > spaceTime) {

            isAllowClick = false;

        } else {

            isAllowClick = true;

        }

        lastClickTime = currentTime;

        return isAllowClick;

    }

}
//
//if(NoFastClickUtils.isDoubleClick()) {
//
////快速点击后的逻辑，可以提示用户点击太快，休息一会
//
//        }else{
//
////正常点击的逻辑
//
//        }