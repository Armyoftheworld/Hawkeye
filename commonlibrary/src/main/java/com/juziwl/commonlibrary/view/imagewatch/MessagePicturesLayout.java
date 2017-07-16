package com.juziwl.commonlibrary.view.imagewatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.juziwl.commonlibrary.utils.CommonTools;
import com.juziwl.commonlibrary.utils.LoadingImgUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * QQ 517309507
 * 至尊流畅;daLao专用;/斜眼笑
 */
public class MessagePicturesLayout extends FrameLayout implements View.OnClickListener {

    public static final int MAX_DISPLAY_COUNT = 9;//ijuigongge
    private final LayoutParams lpChildImage = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    private final int mSingleMaxSize;
    private final int mSpace;
    private final List<ImageView> iPictureList = new ArrayList<>();  //9ge
    private final List<ImageView> mVisiblePictureList = new ArrayList<>();
    private final TextView tOverflowCount;
    private Callback mCallback;
    private boolean isInit;
    private float width = CommonTools.getScreenWidth(getContext()) * 1f;
    private List<String> mDataList = new ArrayList<>();
    private List<String> mThumbDataList = new ArrayList<>();
    Context mcontext;

    public MessagePicturesLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mcontext = context;
        DisplayMetrics mDisplayMetrics = context.getResources().getDisplayMetrics();
        mSingleMaxSize = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, mDisplayMetrics) + 0.5f); //单张图片
        mSpace = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, mDisplayMetrics) + 0.5f); //分割线
        for (int i = 0; i < MAX_DISPLAY_COUNT; i++) {
            ImageView squareImageView = new SquareImageView(context);
            squareImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            squareImageView.setVisibility(View.GONE);
            squareImageView.setOnClickListener(this);
            addView(squareImageView);
            iPictureList.add(squareImageView);
        }
        tOverflowCount = new TextView(context);
        tOverflowCount.setTextColor(0xFFFFFFFF);
        tOverflowCount.setTextSize(24);
        tOverflowCount.setGravity(Gravity.CENTER);
        tOverflowCount.setBackgroundColor(0x66000000);
        tOverflowCount.setVisibility(View.GONE);
        addView(tOverflowCount);
    }

    public void clearData() {
        mDataList.clear();
        mThumbDataList.clear();
    }
    public void set(List<String> urlThumbList, List<String> urlList) {
        mThumbDataList = urlThumbList;
        mDataList = urlList;
        notifyDataChanged();
    }

    public void setWidth(float width) {
        this.width = width;
    }

    private void notifyDataChanged() {
        final List<String> dataList = mThumbDataList;
        final int urlListSize = dataList != null ? mThumbDataList.size() : 0;//  mThumbDataList.size()
        if (mDataList == null || mDataList.size() != urlListSize) {
            setVisibility(GONE);
            //    throw new IllegalArgumentException("dataList.size != thumbDataList.size");
        }
        int column = 3;
        if (urlListSize == 1) {
            column = 1;//lieshu
        } else if (urlListSize == 4) {
            column = 2;
        } else if (urlListSize == 2) {
            column = 2;
        }
        int row = 0;
        if (urlListSize > 6) {
            row = 3;
        } else if (urlListSize > 3) {
            row = 2;
        } else if (urlListSize > 0) {
            row = 1;
        }
        final int imageSize = urlListSize == 1 ? (int)width :
                (int) ((width - mSpace * (column - 1)) / column); // 每张图片大小
        lpChildImage.width = imageSize;
        if (urlListSize == 1) {
            DisplayMetrics mDisplayMetrics = mcontext.getResources().getDisplayMetrics();
            lpChildImage.height = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, mDisplayMetrics) + 0.5f);
        } else {
            lpChildImage.height = lpChildImage.width;
        }
        mVisiblePictureList.clear();  //可见的
        for (int i = 0; i < iPictureList.size(); i++) {
            final ImageView iPicture = iPictureList.get(i);
            if (i < urlListSize) {
                iPicture.setVisibility(View.VISIBLE);
                mVisiblePictureList.add(iPicture);
                iPicture.setLayoutParams(lpChildImage);
                LoadingImgUtil.loadimg(dataList.get(i), iPicture, null, false);
                iPicture.setTranslationX((i % column) * (imageSize + mSpace));  // 回到当初位子？
                iPicture.setTranslationY((i / column) * (imageSize + mSpace));
            } else {
                iPicture.setVisibility(View.GONE);
            }

        }


        if (urlListSize == 1) {
            DisplayMetrics mDisplayMetrics = mcontext.getResources().getDisplayMetrics();
            getLayoutParams().height = (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160, mDisplayMetrics) + 0.5f);
            //单张图片; //单张图片
        } else {
            getLayoutParams().height = imageSize * row + mSpace * (row - 1);  // 代码设置真布局高度
        }

    }

    @Override
    public void onClick(View v) {
        if (mCallback != null) {
            mCallback.onThumbPictureClick((ImageView) v, mVisiblePictureList, mDataList);
        }
    }

    public interface Callback {
        void onThumbPictureClick(ImageView i, List<ImageView> imageGroupList, List<String> urlList);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

}
