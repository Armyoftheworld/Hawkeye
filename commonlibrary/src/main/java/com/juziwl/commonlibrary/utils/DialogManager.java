package com.juziwl.commonlibrary.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.juziwl.commonlibrary.R;


public class DialogManager {
    private static DialogManager dialogManager = null;
    private Dialog loadingDialog = null;

    public static DialogManager getInstance() {
        if (dialogManager == null) {
            dialogManager = new DialogManager();
        }
        return dialogManager;
    }

    public Dialog createLoadingDialog(Context context, String msg) {
        try {
            cancelDialog();
            LayoutInflater inflater = LayoutInflater.from(context);
            View v = inflater.inflate(R.layout.common_progressdialog_no_deal, null);// 得到加载view
            LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
            TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
            tipTextView.setText(msg);// 设置加载信息
            loadingDialog = new Dialog(context, R.style.common_loading_dialog);// 创建自定义样式dialog
            loadingDialog.setCancelable(true);// 可以用“返回键”取消
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setOnCancelListener(dialog -> {
                loadingDialog = null;
                if (onCancelListener != null) {
                    onCancelListener.onCancel(dialog);
                    onCancelListener = null;//防止其他页面的dialog消失后也会调用该回调
                }
            });
        } catch (Exception e) {
            CommonTools.outputError(e);
            loadingDialog = new ProgressDialog(context);
        }
        return loadingDialog;

    }

    public void cancelDialog() {
        if (loadingDialog != null) {
            loadingDialog.cancel();
            loadingDialog = null;
        }
    }

    public boolean isShow() {
        return loadingDialog != null && loadingDialog.isShowing();
    }

    private OnCancelListener onCancelListener;

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public interface OnCancelListener {
        void onCancel(DialogInterface dialog);
    }
}
