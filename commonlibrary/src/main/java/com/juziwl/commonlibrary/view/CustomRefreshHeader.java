//package com.juziwl.commonlibrary.view;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ProgressBar;
//
//import com.aspsine.swipetoloadlayout.SwipeRefreshTrigger;
//import com.aspsine.swipetoloadlayout.SwipeTrigger;
//import com.juziwl.commonlibrary.R;
//
//
//
///**
// * @author lijia
// * @version V_5.0.0
// * @date 2017/2/16 0016
// * @description
// */
//public class CustomRefreshHeader extends LinearLayout implements SwipeTrigger, SwipeRefreshTrigger {
//    private ProgressBar progressBar;
//    private ImageView headTitle;
//    private ImageView headLastUpdate;
//
//    public CustomRefreshHeader(Context context) {
//
//        this(context, null, 0);
//    }
//
//    public CustomRefreshHeader(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//
//    }
//
//    private void init() {
//
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        View view = View.inflate(getContext(), R.layout.common_header, null);
//        addView(view, lp);
//        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
//        headTitle = (ImageView) view.findViewById(R.id.head_title);
//        headLastUpdate = (ImageView) view
//                .findViewById(R.id.head_last_update);
//
//    }
//
//    public CustomRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    @Override
//    public void onRefresh() {
//        headLastUpdate.setVisibility(GONE);
//        progressBar.setVisibility(VISIBLE);
//    }
//
//    @Override
//    public void onPrepare() {
//
//    }
//
//    @Override
//    public void onMove(int i, boolean b, boolean b1) {
//
//    }
//
//    @Override
//    public void onRelease() {
//
//
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
//
//    @Override
//    public void onReset() {
//        headLastUpdate.setVisibility(VISIBLE);
//        progressBar.setVisibility(GONE);
//    }
//
//
//}
