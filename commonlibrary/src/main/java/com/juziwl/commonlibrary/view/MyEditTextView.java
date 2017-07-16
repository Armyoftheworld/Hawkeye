package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;


public class MyEditTextView extends EditText {
	  private boolean scrollble=true;

	  public MyEditTextView(Context context) {
	    super(context);
	  }

	  public MyEditTextView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	  }


	  @Override
	  public boolean onTouchEvent(MotionEvent ev) {
//	    if (!scrollble) {
//	      return true;
//	    }

	    return super.onTouchEvent(ev);
	  }


	  public boolean isScrollble() {
	    return scrollble;
	  }

	  public void setScrollble(boolean scrollble) {
	    this.scrollble = scrollble;
	  }

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
         //请求父控件不拦截事件
		 getParent().requestDisallowInterceptTouchEvent(true);

		return super.dispatchTouchEvent(ev);
	}
}
