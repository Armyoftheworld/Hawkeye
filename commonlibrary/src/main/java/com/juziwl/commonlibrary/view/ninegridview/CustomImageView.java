package com.juziwl.commonlibrary.view.ninegridview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.model.ImageSize;
import com.juziwl.commonlibrary.utils.LoadingImgUtil;


/**
 * Created by Pan_ on 2015/2/2.
 */
public class CustomImageView extends ImageView {
    private String url;
    private boolean isAttachedToWindow;
    private int width = 0, height = 0;

    public CustomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context) {
        super(context);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Drawable drawable = getDrawable();
                if (drawable != null) {
                    drawable.mutate().setColorFilter(Color.GRAY,
                            PorterDuff.Mode.MULTIPLY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Drawable drawableUp = getDrawable();
                if (drawableUp != null) {
                    drawableUp.mutate().clearColorFilter();
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public void onAttachedToWindow() {
        isAttachedToWindow = true;
//        if (width == 0) {
//            width = 50;
//        }
//        if (height == 0) {
//            height = 50;
//        }
        setImageUrl(url, width, height);
        super.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
//        Picasso.with(getContext()).cancelRequest(this);
        isAttachedToWindow = false;
        setImageBitmap(null);
        super.onDetachedFromWindow();
    }

    public void setImageUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
            if (isAttachedToWindow) {
                LoadingImgUtil.loadimg(url, this, null,false);
//                Picasso.with(getContext()).load(url).placeholder(new ColorDrawable(Color.parseColor("#f5f5f5"))).error(R.drawable.falseimg).into(this);
            }
        }
    }
    public void setImageUrl(String url, int width, int height) {
        if (!TextUtils.isEmpty(url)) {
            this.url = url;
            this.width = width;
            this.height = width;
            if (isAttachedToWindow) {
                if (width == 0 || height == 0) {
                    if (url.equals(Global.baseURL)) {
                        setImageResource(R.mipmap.common_falseimg);
                    } else {
                        LoadingImgUtil.loadimg(url, this, null, false);
                    }
                } else {
                    if (url.equals(Global.baseURL)) {
                        setImageResource(R.mipmap.common_falseimg);
                    } else {
                        LoadingImgUtil.displayImageWithImageSize(url, this, new ImageSize(width, height), null, false);
                    }
                }
            }
        }
    }
}
