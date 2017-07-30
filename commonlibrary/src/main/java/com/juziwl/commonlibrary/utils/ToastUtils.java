package com.juziwl.commonlibrary.utils;

import android.content.Context;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/7/30
 * @description 显示Toast
 */
public class ToastUtils {


    private static Toast toast;

    private static void createToast(Context context, String content, int resId) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = new Toast(context);
            toast.setView(View.inflate(context, R.layout.common_layout_toast, null));
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        if (resId > 0) {
            toast.setText(resId);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    //由于实现沉浸式状态栏，导致系统自带的Toast出现问题，所以自定义Toast的View
    public static void showToast(final String content) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            createToast(Global.application, content, 0);
        } else {
            UIHandler.getInstance().post(() -> createToast(Global.application, content, 0));
        }
    }

    public static void showToast(final int resId) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            createToast(Global.application, "", resId);
        } else {
            UIHandler.getInstance().post(() -> createToast(Global.application, "", resId));
        }
    }
}
