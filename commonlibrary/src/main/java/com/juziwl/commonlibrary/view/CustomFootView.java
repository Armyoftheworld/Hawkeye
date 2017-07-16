//package com.juziwl.commonlibrary.view;
//
//import android.content.Context;
//import android.util.AttributeSet;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import com.aspsine.swipetoloadlayout.SwipeLoadMoreTrigger;
//import com.aspsine.swipetoloadlayout.SwipeTrigger;
//import com.juziwl.commonlibrary.R;
//
//
///**
// * @author lijia
// * @version V_5.0.0
// * @date 2017/2/17 0017
// * @description
// */
//public class CustomFootView extends LinearLayout implements SwipeLoadMoreTrigger, SwipeTrigger {
//    public CustomFootView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        View view = View.inflate(getContext(), R.layout.common_footer, null);
//        addView(view, lp);
//
//    }
//
//    public CustomFootView(Context context) {
//        this(context, null, 0);
//    }
//
//    public CustomFootView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//
//    }
//
//
//    @Override
//    public void onLoadMore() {
//
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
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
//
//    @Override
//    public void onReset() {
//
//    }
//}
