package com.juziwl.commonlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class BottomLoadingScrollView extends ScrollView {

	private ScrollBottomListener scrollBottomListener;  
	  
    public BottomLoadingScrollView(Context context) {
        this(context,null,0);
    }  
  
    public BottomLoadingScrollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }  
  
    public BottomLoadingScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
  
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt){  
        if(t + getHeight() >=  computeVerticalScrollRange()-75){  
            //ScrollView滑动到底部了
            if(scrollBottomListener!=null){
                scrollBottomListener.scrollBottom();
            }
        }
    }  
  
    public void setScrollBottomListener(ScrollBottomListener scrollBottomListener){  
        this.scrollBottomListener = scrollBottomListener;  
    }  
  
    public interface ScrollBottomListener{  
        public void scrollBottom();  
    }

}
