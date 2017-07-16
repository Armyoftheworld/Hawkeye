//package com.juziwl.commonlibrary.utils;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.text.TextPaint;
//import android.text.method.ScrollingMovementMethod;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.juziwl.commonlibrary.R;
//import com.juziwl.commonlibrary.config.Global;
//import com.juziwl.commonlibrary.model.ImageSize;
//
//
///**
// * 选择更新用的dialog
// */
//public class CustomAlertDialog {
//    private static CustomAlertDialog alertDialogManager = null;
//    private EditText tv_title;
//    private Button btnSure;
//    private TextView tv_content;
//    private TextView tv_otherscore;
//    private TextView tv_score;
//
//    public TextView getTv_score() {
//        return tv_score;
//    }
//
//    public TextView getTv_otherscore() {
//        return tv_otherscore;
//    }
//
//    public Button getBtnSure() {
//        return btnSure;
//    }
//
//    public EditText getTv_title() {
//        return tv_title;
//    }
//    public TextView getTv_content() {
//        return tv_content;
//    }
//    public static CustomAlertDialog getInstance() {
//        if (alertDialogManager == null) {
//            alertDialogManager = new CustomAlertDialog();
//        }
//        return alertDialogManager;
//    }
//
//    private Dialog alertDialog;
//
//    public Dialog createAlertDialog(Context context, String msg, String cancleBtnMsg, String sureBtnMsg, OnClickListener canclelistener, OnClickListener surelistener) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.common_layout_common_dialog, null);// 得到加载view
//        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.alertdialog);
////		LinearLayout layoutDialog=(LinearLayout) v.findViewById(R.id.llSuccessBoard);
//        TextView tv_title = (TextView) v.findViewById(R.id.tvPromptMsg);
//
//        Button btnCancle = (Button) v.findViewById(R.id.btnCancle);
//        Button btnSure = (Button) v.findViewById(R.id.btnSure);
//        btnCancle.setText(cancleBtnMsg);
//        btnSure.setText(sureBtnMsg);
//        tv_title.setText(msg);
//        tv_title.setMovementMethod(ScrollingMovementMethod.getInstance());
//        btnCancle.setOnClickListener(canclelistener);
//        btnSure.setOnClickListener(surelistener);
////		layout.setOnClickListener(new OnClickListener() {
////
////			@Override
////			public void onClick(View v) {
////				cancelAlertDialog();
////			}
////		});
//        alertDialog = new Dialog(context, R.style.common_textDialogStyle);// 创建自定义样式dialog
//        alertDialog.setCancelable(true);// 可以用“返回键”取消
//        alertDialog.setContentView(layout);// 设置布局
//        return alertDialog;
//
//    }
//
//
//    public Dialog createAlertDialogUpdate(Context context, String msg, String content, String cancleBtnMsg, String sureBtnMsg, OnClickListener canclelistener, OnClickListener surelistener) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.common_layout_common_dialog, null);// 得到加载view
//        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.alertdialog);
////		LinearLayout layoutDialog=(LinearLayout) v.findViewById(R.id.llSuccessBoard);
//        TextView tv_title = (TextView) v.findViewById(R.id.tvPromptMsg);
//        TextView tv_content = (TextView) v.findViewById(R.id.tv_content);
//        tv_content.setVisibility(View.VISIBLE);
//        Button btnCancle = (Button) v.findViewById(R.id.btnCancle);
//        Button btnSure = (Button) v.findViewById(R.id.btnSure);
//        btnCancle.setText(cancleBtnMsg);
//        btnSure.setText(sureBtnMsg);
//        tv_title.setText(msg);
//
//        TextPaint tpaint = tv_title.getPaint();
//        tpaint .setFakeBoldText(true);
//        tv_content.setMaxLines(10);
//        tv_content.setMovementMethod(ScrollingMovementMethod.getInstance());
//        tv_content.setVerticalScrollBarEnabled(true);
//        tv_content.setText(content);
//        btnCancle.setOnClickListener(canclelistener);
//        btnSure.setOnClickListener(surelistener);
////		layout.setOnClickListener(new OnClickListener() {
////
////			@Override
////			public void onClick(View v) {
////				cancelAlertDialog();
////			}
////		});
//      alertDialog = new Dialog(context, R.style.common_textDialogStyle);// 创建自定义样式dialog
//        alertDialog.setCancelable(true);// 可以用“返回键”取消
//        alertDialog.setContentView(layout);// 设置布局
//        return alertDialog;
//
//    }
//
//    public void cancelAlertDialog() {
//        try {
//            if (alertDialog != null) {
//                alertDialog.dismiss();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public boolean isShowAlertDialog() {
//        return alertDialog != null && alertDialog.isShowing();
//    }
//
////    public Dialog setSingleChoiceItems(Context context, String[] targetName, String title, int whichtype,
////                                       AdapterView.OnItemClickListener clicklistener) {
////
////        LayoutInflater inflater = LayoutInflater.from(context);
////        View v = inflater.inflate(R.layout.dialog_litem_layout, null);// 得到加载view
////        LinearLayout layout = (LinearLayout) v.findViewById(R.id.rootView);
////        TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
////
////        ListView listView = (ListView) v.findViewById(R.id.listView);
////        adapter = new DialogItemAdapter(context, targetName, whichtype);
//////		adapter.notifyDataSetChanged();
////        listView.setAdapter(adapter);
////        tvTitle.setText(title);
////        CommonTools.setListViewHeightBasedOnChildren(listView);
////        listView.setOnItemClickListener(clicklistener);
////        alertDialog = new Dialog(context, R.style.commonDialog);// 创建自定义样式dialog
////        alertDialog.setContentView(layout, new ViewGroup.LayoutParams(
////                LinearLayout.LayoutParams.MATCH_PARENT,
////                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
////
////        alertDialog.setCanceledOnTouchOutside(true);
////        alertDialog.setCancelable(true);
////
////
////        return alertDialog;
////    }
//
//
//    public Dialog createtryWatchAlertDialog(Context context, String msg, String cancleBtnMsg, String sureBtnMsg, OnClickListener canclelistener, OnClickListener surelistener) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.common_layout_common_dialog, null);// 得到加载view
//        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.alertdialog);
////		LinearLayout layoutDialog=(LinearLayout) v.findViewById(R.id.llSuccessBoard);
//        TextView tv_title = (TextView) v.findViewById(R.id.tvPromptMsg);
//        TextView tv_tip=(TextView)v.findViewById(R.id.tv_tip);
//        tv_tip.setLineSpacing(CommonTools.dip2px(context,10),1.2f);
//        Button btnCancle = (Button) v.findViewById(R.id.btnCancle);
//        Button btnSure = (Button) v.findViewById(R.id.btnSure);
//        btnCancle.setText(cancleBtnMsg);
//        btnSure.setText(sureBtnMsg);
//        tv_tip.setText(msg);
//        tv_title.setVisibility(View.INVISIBLE);
//        btnCancle.setOnClickListener(canclelistener);
//        btnSure.setOnClickListener(surelistener);
//        alertDialog = new Dialog(context, R.style.common_textDialogStyle);// 创建自定义样式dialog
//        alertDialog.setCancelable(true);// 可以用“返回键”取消
//        alertDialog.setContentView(layout);// 设置布局
//        return alertDialog;
//
//    }
//    public Dialog createInputAlertDialog(Context context, String msg, String cancleBtnMsg, String sureBtnMsg, OnClickListener canclelistener, OnClickListener surelistener) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.common_layout_inputcard_dialog, null);// 得到加载view
//        RelativeLayout layout = (RelativeLayout) v.findViewById(R.id.alertdialog);
////		LinearLayout layoutDialog=(LinearLayout) v.findViewById(R.id.llSuccessBoard);
//        tv_title = (EditText) v.findViewById(R.id.tvPromptMsg);
//        //tv_title.setFilters(new InputFilter[]{new EmojiFilter(context), new InputFilter.LengthFilter(15)});
//        TextView tv_tip=(TextView)v.findViewById(R.id.tv_tip);
//        tv_tip.setLineSpacing(CommonTools.dip2px(context,10),1.2f);
//        tv_content = (TextView) v.findViewById(R.id.tv_content);
//        tv_content.setVisibility(View.VISIBLE);
//        Button btnCancle = (Button) v.findViewById(R.id.btnCancle);
//        btnSure = (Button) v.findViewById(R.id.btnSure);
//        btnCancle.setText(cancleBtnMsg);
//        btnSure.setText(sureBtnMsg);
//        tv_tip.setText(msg);
//        btnCancle.setOnClickListener(canclelistener);
//        btnSure.setOnClickListener(surelistener);
//        alertDialog = new Dialog(context, R.style.common_textDialogStyle);// 创建自定义样式dialog
//        alertDialog.setCancelable(true);// 可以用“返回键”取消
//        alertDialog.setContentView(layout);// 设置布局
//        return alertDialog;
//    }
//    public Dialog createRedDialog(Context context, String teachername, String score, String sureBtnMsg, OnClickListener surelistener, OnClickListener otherScorelistener, String headerurl) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View v = inflater.inflate(R.layout.common_layout_reddialog, null);// 得到加载view
//        RelativeLayout ll_redpackage=(RelativeLayout)v.findViewById(R.id.ll_redpackage);
//        ImageView ivheader=(ImageView)v.findViewById(R.id.ivheader);
//        TextView tv_teachername = (TextView)v.findViewById(R.id.tv_teacher_name);
//        tv_score = (TextView)v.findViewById(R.id.tv_score);
//        tv_otherscore = (TextView)v.findViewById(R.id.tv_otherscore);
//        tv_score.setText(score);
//        btnSure = (Button) v.findViewById(R.id.btnSure);
//        btnSure.setText(sureBtnMsg);
//        tv_teachername.setText(teachername);
//        LoadingImgUtil.displayImageWithImageSize(Global.baseURL + headerurl,ivheader, new ImageSize(120, 120), null, true);
//        btnSure.setOnClickListener(surelistener);
//        tv_otherscore.setOnClickListener(otherScorelistener);
//        alertDialog = new Dialog(context, R.style.common_textDialogStyle);// 创建自定义样式dialog
//        alertDialog.setCancelable(true);// 可以用“返回键”取消
//        alertDialog.setContentView(ll_redpackage);// 设置布局
//        return alertDialog;
//    }
//
//}
