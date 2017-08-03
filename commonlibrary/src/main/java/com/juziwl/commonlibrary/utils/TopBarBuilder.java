package com.juziwl.commonlibrary.utils;

import android.graphics.Typeface;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.R2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.functions.Consumer;


/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月22日
 * @description 设置并生成topbar
 */
public class TopBarBuilder {
    private View topbar;

    @BindView(R2.id.back_btn)
    TextView back_btn;
    @BindView(R2.id.complete)
    TextView complete;
    @BindView(R2.id.left_complete)
    TextView left_complete;

    @BindView(R2.id.iamge_btn)
    ImageView image_btn;
    @BindView(R2.id.back_img)
    ImageView back_img;
    @BindView(R2.id.left_iamge_btn)
    ImageView left_image_btn;

    @BindView(R2.id.set_setting_black)
    RelativeLayout set_setting_black;
    @BindView(R2.id.title_layout)
    RelativeLayout title_layout;
    @BindView(R2.id.centre_layout)
    RelativeLayout centre_layout;
    @BindView(R2.id.title_right_layout)
    RelativeLayout title_right_layout;
    @BindView(R2.id.right_layout)
    RelativeLayout right_layout;
    @BindView(R2.id.set_delete)
    RelativeLayout set_delete;
    @BindView(R2.id.left_title_right_layout)
    RelativeLayout left_title_right_layout;

    @BindView(R2.id.title_string)
    TextView title_string;
    @BindView(R2.id.contre_textview)
    TextView contre_textview;
    @BindView(R2.id.right_textview)
    TextView right_textview;

    @BindView(R2.id.center_top_title)
    TextView center_top_title;

    @BindView(R2.id.center_down_title)
    TextView center_down_title;

    private int topBarLayoutId = R.id.top_title_headerbar;

    public TopBarBuilder setCenter_top_title(String text) {
        center_top_title.setVisibility(!TextUtils.isEmpty(text) ? View.VISIBLE : View.GONE);
        center_top_title.setText(text);
        return this;
    }

    public TopBarBuilder setCenter_down_title(String text) {
        center_down_title.setVisibility(!TextUtils.isEmpty(text) ? View.VISIBLE : View.GONE);
        center_down_title.setText(text);
        return this;
    }

    // 2.0之后新添加的

//    android:id="@+id/center_top_title"
//
//    android:id="@+id/center_top_title"


    public TopBarBuilder(View topbar) {
        this.topbar = topbar;
        ButterKnife.bind(this, topbar);
    }

    public TopBarBuilder setLeftImageRes(int resId) {
        set_setting_black.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        back_img.setImageResource(resId);
        return this;
    }

    public TopBarBuilder setLeftClickListener(Consumer<Object> onNext) {
        set_setting_black.setVisibility(onNext != null ? View.VISIBLE : View.GONE);
        click(set_setting_black, onNext);
        return this;
    }

    public TopBarBuilder setLeftDeleteClickListener(Consumer<Object> onNext) {
        set_delete.setVisibility(onNext != null ? View.VISIBLE : View.GONE);
        click(set_delete, onNext);
        return this;
    }

    public TopBarBuilder setLeftTitle(String title) {
        set_setting_black.setVisibility(!TextUtils.isEmpty(title) ? View.VISIBLE : View.GONE);
        back_btn.setBackgroundResource(android.R.color.transparent);
        back_btn.setTextSize(16);
        back_btn.setText(title);
        back_img.setVisibility(View.GONE);
        return this;
    }

    public TopBarBuilder setTitleBlackBoldStyle(String title, int color) {
        title_layout.setVisibility(!TextUtils.isEmpty(title) ? View.VISIBLE : View.GONE);
        title_layout.setBackgroundResource(android.R.color.transparent);
        title_string.setText(title);
        title_string.setTextColor(color);
        title_string.setTypeface(Typeface.DEFAULT_BOLD);
        return this;
    }

    public TopBarBuilder setRightHigh(int width, int height) {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, height);
//        params.addRule(width, height);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.setMargins(0, 0, DisplayUtils.dip2px(10), 0);
        title_right_layout.setLayoutParams(params);
        return this;
    }

    public TopBarBuilder setTitle(String title) {
        title_layout.setVisibility(!TextUtils.isEmpty(title) ? View.VISIBLE : View.GONE);
        title_string.setText(title);
        return this;
    }

    public TopBarBuilder setTitleColor(int color) {
        title_string.setTextColor(color);
        return this;
    }


    public TopBarBuilder setTitleAndColor(String title, int color) {
        title_layout.setVisibility(!TextUtils.isEmpty(title) ? View.VISIBLE : View.GONE);
        title_string.setText(title);
        title_string.setTextColor(color);
        return this;
    }

    public TopBarBuilder setTitleLength(int length) {

        title_string.setMaxEms(length);
        return this;
    }

    public TopBarBuilder setRedPointText(String text) {
        centre_layout.setVisibility(!TextUtils.isEmpty(text) ? View.VISIBLE : View.GONE);
        contre_textview.setText(text);
        return this;
    }

    public TopBarBuilder setRightButtonBackgroundRes(int resId) {
        title_right_layout.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        complete.setBackgroundResource(resId);
        image_btn.setVisibility(View.GONE);
        return this;
    }

    public TopBarBuilder setRightImageRes(int resId) {
        title_right_layout.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        complete.setVisibility(View.GONE);
//        image_btn.setBackgroundResource(resId);
        image_btn.setImageResource(resId);
        return this;
    }

    // 新添最右边最左边
    public TopBarBuilder setLeftRightImageBackgroundRes(int resId) {
        left_title_right_layout.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
        left_complete.setVisibility(View.GONE);
//        image_btn.setBackgroundResource(resId);
        left_image_btn.setImageResource(resId);
        return this;
    }

    public TopBarBuilder setLeftRightImageClickListener(Consumer<Object> onNext) {
//        title_right_layout.setVisibility(listener != null ? View.VISIBLE : View.GONE);
//        image_btn.setOnClickListener(listener);
        click(left_title_right_layout, onNext);
        return this;
    }


    public TopBarBuilder setTitleRightDrawable(int resId, int drawablepadding) {
        title_string.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
        title_string.setCompoundDrawablePadding(drawablepadding);
        return this;
    }

    public TopBarBuilder setTitlePaddingLeftRight(int padding) {
        title_string.setPadding(padding, 0, padding, 0);
        return this;
    }

    public TopBarBuilder setTitleClickListener(Consumer<Object> onNext) {
        click(title_layout, onNext);
        return this;
    }

    public TopBarBuilder setRightButtonClickListener(Consumer<Object> onNext) {
        title_right_layout.setVisibility(onNext != null ? View.VISIBLE : View.GONE);
        click(title_right_layout, onNext);
        return this;
    }

    public TopBarBuilder setRightImageClickListener(Consumer<Object> onNext) {
//        title_right_layout.setVisibility(listener != null ? View.VISIBLE : View.GONE);
//        image_btn.setOnClickListener(listener);
        click(title_right_layout, onNext);
        return this;
    }

    public TopBarBuilder setRightText(String text) {
        return setRightText(text, 18);
    }

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    @Retention(RetentionPolicy.RUNTIME)
    @IntDef({LEFT, RIGHT})
    public @interface Direction {
    }


    public TopBarBuilder setRightTextComponentImage(@DrawableRes int resId, @Direction int direction, int padding) {
        title_right_layout.setVisibility(resId <= 0 ? View.VISIBLE : View.GONE);
        if (direction == LEFT) {
            complete.setCompoundDrawablesWithIntrinsicBounds(resId, 0, 0, 0);
        } else {
            complete.setCompoundDrawablesWithIntrinsicBounds(0, 0, resId, 0);
        }
        complete.setCompoundDrawablePadding(padding);
        return this;
    }

    public TopBarBuilder setRightText(String text, float textSize) {
        title_right_layout.setVisibility(!TextUtils.isEmpty(text) ? View.VISIBLE : View.GONE);
        complete.setBackgroundResource(android.R.color.transparent);
        complete.setText(text);
        complete.setTextSize(textSize);
        return this;
    }

    public TopBarBuilder setRightTextColor(@ColorRes int colorId) {
        title_right_layout.setVisibility(!(colorId > 0) ? View.VISIBLE : View.GONE);
        complete.setTextColor(complete.getResources().getColor(colorId));
        return this;
    }

    public TopBarBuilder setRightTextAndColor(String text, int color) {
        title_right_layout.setVisibility(!TextUtils.isEmpty(text) ? View.VISIBLE : View.GONE);
        //  complete.setBackgroundResource(R.color.transparent);
        complete.setText(text);
        complete.setTextColor(color);
        return this;
    }

    public TopBarBuilder setRightRedPointText(String text) {
        right_layout.setVisibility(!TextUtils.isEmpty(text) ? View.VISIBLE : View.GONE);
        right_textview.setText(text);
        return this;
    }

    public TopBarBuilder setRightTextSize(float size) {
        complete.setTextSize(size);
        return this;
    }

    public View build() {
        return topbar;
    }

    public TextView getTitle_string() {
        return title_string;
    }

    public TextView getRightButton() {
        return complete;
    }

    public ImageView getRightImage() {
        return image_btn;
    }

    public TextView getBack_btn() {
        return back_btn;
    }

    public void setRightLayoutVisible(int visible) {
        title_right_layout.setVisibility(visible);
    }

    public TopBarBuilder isSetMargin(boolean isset) {
        if (!isset) {
            ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) topbar.getLayoutParams();
            lp.topMargin = 0;
            topbar.setLayoutParams(lp);
        }
        return this;
    }

    public TopBarBuilder setTopBarBackGround(int color) {
        topbar.setBackgroundResource(color);
        return this;
    }


    public TopBarBuilder setTitleMargin(int left, int right) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) title_layout.getLayoutParams();
        lp.leftMargin = left;
        lp.rightMargin = right;
        title_layout.setLayoutParams(lp);
        return this;
    }

    private void click(View v, Consumer<Object> onNext) {
        CommonTools.click(v, onNext);
    }

//    public TopBarBuilder setRightTextSize(int size) {
//        complete.setTextSize(CommonTools.sp2px(complete.getContext(),size));
//        return this;
//    }

}
