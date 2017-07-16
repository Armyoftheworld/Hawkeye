package com.juziwl.commonlibrary.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月18日
 * @description 应用程序Activity的管理类
 */
public class AppManager {
    private static Stack<Activity> mActivityStack = new Stack<>();
    private static AppManager mAppManager;
    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        if (mAppManager == null) {
            mAppManager = new AppManager();
        }
        return mAppManager;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (mActivityStack == null) {
            mActivityStack = new Stack<>();
        }
        mActivityStack.add(activity);
    }

    /**
     * 获取栈顶Activity（堆栈中最后一个压入的）
     */
    public Activity getTopActivity() {
        if (mActivityStack != null) {
            Activity activity = mActivityStack.lastElement();
            return activity;
        }
        return null;
    }

    /**
     * 结束栈顶Activity（堆栈中最后一个压入的）
     */
    public void killTopActivity() {
        if (mActivityStack != null) {
            Activity activity = mActivityStack.lastElement();
            killActivity(activity);
        }
    }

    /**
     * 结束指定的Activity
     */
    public void killActivity(Activity activity) {
        if (activity != null && mActivityStack != null) {
            if (mActivityStack.contains(activity)) {
                mActivityStack.remove(activity);
                activity.finish();
                activity = null;
            }


        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void killActivity(Class<?> cls) {
        if (cls != null && mActivityStack != null) {
            for (int i = mActivityStack.size() - 1; i >= 0; i--) {
                if (mActivityStack.get(i).getClass().equals(cls)) {
                    killActivity(mActivityStack.get(i));
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void killAllActivity() {
        if (mActivityStack != null) {
            for (int i = 0, size = mActivityStack.size(); i < size; i++) {
                if (null != mActivityStack.get(i)) {
                    mActivityStack.get(i).finish();
                }
            }
            mActivityStack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            killAllActivity();
            ActivityManager activityMgr = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
//			activityMgr.restartPackage(context.getPackageName());
//			System.exit(0);
        } catch (Exception e) {
        }
    }
}
