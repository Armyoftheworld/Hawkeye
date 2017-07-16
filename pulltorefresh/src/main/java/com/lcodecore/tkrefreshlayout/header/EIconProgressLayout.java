package com.lcodecore.tkrefreshlayout.header;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v4.view.ViewCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.lcodecore.tkrefreshlayout.R;
import com.lcodecore.tkrefreshlayout.utils.DensityUtil;
import com.lcodecore.tkrefreshlayout.utils.LogUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Locale;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/06/15
 * @description
 */
public class EIconProgressLayout extends FrameLayout implements IHeaderView {

    private ImageView image;
    private int[] whitePullingDownResId = {
            R.mipmap.refresh_white_pullingdown_00001, R.mipmap.refresh_white_pullingdown_00002, R.mipmap.refresh_white_pullingdown_00003,
            R.mipmap.refresh_white_pullingdown_00004, R.mipmap.refresh_white_pullingdown_00005, R.mipmap.refresh_white_pullingdown_00006,
            R.mipmap.refresh_white_pullingdown_00007, R.mipmap.refresh_white_pullingdown_00008, R.mipmap.refresh_white_pullingdown_00009,
            R.mipmap.refresh_white_pullingdown_00010, R.mipmap.refresh_white_pullingdown_00011, R.mipmap.refresh_white_pullingdown_00012,
            R.mipmap.refresh_white_pullingdown_00013, R.mipmap.refresh_white_pullingdown_00014, R.mipmap.refresh_white_pullingdown_00015,
            R.mipmap.refresh_white_pullingdown_00016, R.mipmap.refresh_white_pullingdown_00017, R.mipmap.refresh_white_pullingdown_00018,
            R.mipmap.refresh_white_pullingdown_00019, R.mipmap.refresh_white_pullingdown_00020, R.mipmap.refresh_white_pullingdown_00021,
            R.mipmap.refresh_white_pullingdown_00022, R.mipmap.refresh_white_pullingdown_00023, R.mipmap.refresh_white_pullingdown_00024,
            R.mipmap.refresh_white_pullingdown_00025, R.mipmap.refresh_white_pullingdown_00025, R.mipmap.refresh_white_pullingdown_00027,
            R.mipmap.refresh_white_pullingdown_00028, R.mipmap.refresh_white_pullingdown_00029
    };
    private int[] greenPullingDownResId = {
            R.mipmap.refresh_green_pullingdown_00001, R.mipmap.refresh_green_pullingdown_00002, R.mipmap.refresh_green_pullingdown_00003,
            R.mipmap.refresh_green_pullingdown_00004, R.mipmap.refresh_green_pullingdown_00005, R.mipmap.refresh_green_pullingdown_00006,
            R.mipmap.refresh_green_pullingdown_00007, R.mipmap.refresh_green_pullingdown_00008, R.mipmap.refresh_green_pullingdown_00009,
            R.mipmap.refresh_green_pullingdown_00010, R.mipmap.refresh_green_pullingdown_00011, R.mipmap.refresh_green_pullingdown_00012,
            R.mipmap.refresh_green_pullingdown_00013, R.mipmap.refresh_green_pullingdown_00014, R.mipmap.refresh_green_pullingdown_00015,
            R.mipmap.refresh_green_pullingdown_00016, R.mipmap.refresh_green_pullingdown_00017, R.mipmap.refresh_green_pullingdown_00018,
            R.mipmap.refresh_green_pullingdown_00019, R.mipmap.refresh_green_pullingdown_00020, R.mipmap.refresh_green_pullingdown_00021,
            R.mipmap.refresh_green_pullingdown_00022, R.mipmap.refresh_green_pullingdown_00023, R.mipmap.refresh_green_pullingdown_00024,
            R.mipmap.refresh_green_pullingdown_00025, R.mipmap.refresh_green_pullingdown_00025, R.mipmap.refresh_green_pullingdown_00027,
            R.mipmap.refresh_green_pullingdown_00028, R.mipmap.refresh_green_pullingdown_00029
    };
    private int[] pullingDownResId;
    @DrawableRes
    private int pullingReleasingResId, pullingFinishResId;

    public EIconProgressLayout(Context context, @AnimType int type) {
        super(context);
        setBackgroundColor(Color.TRANSPARENT);
        createImageView(context);
        ViewCompat.setChildrenDrawingOrderEnabled(this, true);
        if (type == GREEN) {
            pullingDownResId = greenPullingDownResId;
            pullingReleasingResId = R.drawable.anim_green_pullingreleasing;
            pullingFinishResId = R.drawable.anim_green_pullingfinish;
        } else {
            pullingDownResId = whitePullingDownResId;
            pullingReleasingResId = R.drawable.anim_white_pullingreleasing;
            pullingFinishResId = R.drawable.anim_white_pullingfinish;
        }
    }

    private void createImageView(Context context) {
        image = new ImageView(context);
        int width = DensityUtil.dp2px(context, 35);
        LayoutParams params = new LayoutParams(width, width, Gravity.TOP | Gravity.CENTER_HORIZONTAL);
        params.topMargin = DensityUtil.dp2px(context, 5);
        image.setScaleType(ImageView.ScaleType.FIT_XY);
        image.setLayoutParams(params);
        addView(image);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        image.setBackgroundResource(0);
        LogUtil.i(String.format(Locale.CHINA, "onPullingDown fraction = %f\tmaxHeadHeight = %f\theadHeight = %f", fraction, maxHeadHeight, headHeight));
        if (fraction >= 1) {
            image.setImageResource(pullingDownResId[pullingDownResId.length - 1]);
        } else {
            image.setImageResource(pullingDownResId[(int) (pullingDownResId.length * fraction)]);
        }
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        LogUtil.i("onPullReleasing");
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        LogUtil.i("startAnim");
        image.setImageResource(0);
        image.setBackgroundResource(pullingReleasingResId);
        AnimationDrawable animationDrawable = (AnimationDrawable) image.getBackground();
        animationDrawable.stop();
        animationDrawable.start();
    }

    @Override
    public void onFinish(final OnAnimEndListener animEndListener) {
        LogUtil.i("onFinish");
        image.setImageResource(0);
        AnimationDrawable animationDrawable = (AnimationDrawable) image.getBackground();
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        image.setBackgroundResource(pullingFinishResId);
        animationDrawable = (AnimationDrawable) image.getBackground();
        animationDrawable.stop();
        animationDrawable.start();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                animEndListener.onAnimEnd();
            }
        }, 1000);
    }

    @Override
    public void reset() {
        Drawable background = image.getBackground();
        if (background != null && background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).stop();
            image.setBackgroundResource(0);
        }
        image.setImageResource(0);
    }

    public static final int GREEN = 0, WHITE = 1;

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.PARAMETER)
    @IntDef({GREEN, WHITE})
    public @interface AnimType {
    }
}
