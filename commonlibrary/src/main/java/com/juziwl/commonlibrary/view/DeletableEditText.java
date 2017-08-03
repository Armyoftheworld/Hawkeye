package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

/**
 * author   YeMing(yeming_1001@163.com)
 * Date:    2015-01-20 22:32
 * version: V1.0
 * Description: 带删除图标的编辑框
 */
public class DeletableEditText extends EditText {

    private Drawable mRightDrawable;

    public DeletableEditText(Context context) {
        this(context, null);
        initView();
    }

    public DeletableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        //上下左右图片的位置  0 :left, 1: top,2 :right, and 3 :bottom
        Drawable[] drawables = this.getCompoundDrawables();
        //取得right位置的Drawable
        //即布局文件中设置的android:drawableRight
        mRightDrawable = drawables[2];
        //设置EditText文字变化的监听
        addTextChangedListener(new TextWatcherImpl());
        //初始化时让右边clean图标不可见
        setClearDrawableVisible(false);
    }

    //隐藏或者显示右边clean的图标
    protected void setClearDrawableVisible(boolean isVisible) {
        Drawable rightDrawable;
        if (isVisible) {
            rightDrawable = mRightDrawable;
        } else {
            rightDrawable = null;
        }
        //设置该控件left, top, right, and bottom处的图标
        setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1],
                rightDrawable, getCompoundDrawables()[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (getCompoundDrawables()[2] != null) {
                boolean touchable = event.getX() > (getWidth() - getTotalPaddingRight())
                        && (event.getX() < ((getWidth() - getPaddingRight())));

                if (touchable) {
                    this.setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    //当输入结束后判断是否显示右边clean的图标
    private class TextWatcherImpl implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            boolean isVisible = getText().toString().length() >= 1;
            setClearDrawableVisible(isVisible);
        }
    }
}
