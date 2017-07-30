package com.juziwl.commonlibrary.mvp.view;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.chrisbanes.photoview.PhotoView;
import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.R2;
import com.juziwl.commonlibrary.config.BaseAppDelegate;
import com.juziwl.commonlibrary.model.ImageSize;
import com.juziwl.commonlibrary.utils.BrowseImagesActivity;
import com.juziwl.commonlibrary.utils.LoadingImgUtil;
import com.juziwl.commonlibrary.utils.StringUtils;
import com.juziwl.commonlibrary.widget.HackyViewPager;

import java.lang.ref.SoftReference;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/4/30
 * @description 图片浏览的view
 */
public class BrowseImagesDelegate extends BaseAppDelegate {

    @BindView(R2.id.hacky_pager)
    HackyViewPager pager;
    @BindView(R2.id.page_number)
    TextView page_number;

    @Override
    public int getRootLayoutId() {
        return R.layout.common_layout_browse_photo;
    }

    @Override
    public void initWidget() {
        ButterKnife.bind(this, getRootView());
    }

    public void initViewPagerData(String pic[], int currentPosition, boolean isShowHeadIcon, boolean canImageSave) {
        ImagePagerAdapter adapter = new ImagePagerAdapter(pic, isShowHeadIcon, canImageSave);
        pager.setAdapter(adapter);
        pager.setCurrentItem(currentPosition, false);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                page_number.setText(String.format(Locale.CHINA, "%d/%d", position + 1, adapter.getCount()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        page_number.setText(String.format(Locale.CHINA, "%d/%d", currentPosition + 1, pic.length));
    }

    public int getCurrentItem() {
        return pager.getCurrentItem();
    }

    class ImagePagerAdapter extends PagerAdapter {
        private String[] pic = null;
        private boolean isShowHeadIcon = false;
        private boolean canImageSave = true;

        public ImagePagerAdapter(String[] pic, boolean isShowHeadIcon, boolean canImageSave) {
            this.pic = pic;
            this.isShowHeadIcon = isShowHeadIcon;
            this.canImageSave = canImageSave;
        }

        @Override
        public int getCount() {
            return pic.length;
        }


        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            SoftReference<PhotoView> photoViewSoftReference = new SoftReference<>(photoView);
            photoViewSoftReference.get().setBackgroundColor(0xff000000);
            if (isShowHeadIcon) {
                if (StringUtils.isEmpty(pic[position])) {
                    photoViewSoftReference.get().setImageResource(R.mipmap.common_default_head);
                } else {
                    LoadingImgUtil.displayImageWithoutPlaceholder(pic[position], photoViewSoftReference.get(), new ImageSize(480, 800), true);
                }
            } else {
                LoadingImgUtil.displayImageWithoutPlaceholder(pic[position], photoViewSoftReference.get(), new ImageSize(480, 800), false);
            }
            photoViewSoftReference.get().setOnLongClickListener(view -> {
                if (canImageSave) {
                    interactiveListener.onInteractive(BrowseImagesActivity.IMAGELONGCLICK, null);
                }
                return true;
            });
            photoViewSoftReference.get().setOnClickListener(view ->
                    interactiveListener.onInteractive(BrowseImagesActivity.ACTIVITYFINISH, null));
            container.addView(photoViewSoftReference.get(), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return photoViewSoftReference.get();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
