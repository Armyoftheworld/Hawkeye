package com.juziwl.commonlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.juziwl.commonlibrary.BuildConfig;
import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.model.ImageSize;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.WeakHashMap;

/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月19日
 * @description 加载图片工具类
 */
public class LoadingImgUtil {

    public static void loadimg(String url, ImageView imgview,
                               final ProgressBar progressBar, boolean flag) {
        if (imgview == null || imgview.getContext() == null)
            return;
        int resId = flag ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        Glide.with(Global.application).load(replaceImageUrlHost(url))
                .placeholder(resId)
                .error(resId)
                .fallback(resId)
//                .thumbnail(0.1f)
                .dontAnimate()
                .dontTransform()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<>())
                .into(new GlideDrawableImageViewTarget(imgview) {

                    private void setVisibility(boolean visible) {
                        if (progressBar != null) {
                            progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        setVisibility(true);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        setVisibility(false);
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        super.onLoadCleared(placeholder);
                        setVisibility(false);
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        setVisibility(false);
                    }
                });

    }


    public static void loadImgAds(String url, ImageView imgview, final ProgressBar progressBar) {
        if (imgview == null || imgview.getContext() == null)
            return;
        int resId = R.mipmap.common_falseimg;
        Glide.with(Global.application).load(replaceImageUrlHost(url))
//                .placeholder(resId)
//                .error(resId)
//                .fallback(resId)
//                .thumbnail(0.1f)
                .dontAnimate()
                .dontTransform()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<>())
                .into(new GlideDrawableImageViewTarget(imgview) {

                    private void setVisibility(boolean visible) {
                        if (progressBar != null) {
                            progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        setVisibility(true);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        setVisibility(false);
                    }

                    @Override
                    public void onLoadCleared(Drawable placeholder) {
                        super.onLoadCleared(placeholder);
                        setVisibility(false);
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        setVisibility(false);
                    }
                });
    }

    public static void loadingLocalImage(String url, ImageSize imageSize, ImageView imageView) {
        if (imageView == null || imageView.getContext() == null)
            return;
        DrawableRequestBuilder<String> builder = Glide.with(Global.application)
                .load(url)
                .dontAnimate()
                .dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.mipmap.common_falseimg)
                .fallback(R.mipmap.common_falseimg)
                .error(R.mipmap.common_falseimg);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void displayImageWithImageSize(String url, ImageView imageView, ImageSize imageSize,
                                                 onLoadingImageListener loadListener, boolean flag) {
        if (imageView == null || imageView.getContext() == null)
            return;
        int resId = flag ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        DrawableRequestBuilder<String> builder = Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .placeholder(resId)
                .dontAnimate()
                .dontTransform()
                .fallback(resId)
                .error(resId);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void displayImageWithImageSizeProgressBar(String url, ImageView imageView,
                                                            ImageSize imageSize, final ProgressBar progressBar, boolean flag) {
        if (imageView == null || imageView.getContext() == null)
            return;
        int resId = flag ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        DrawableRequestBuilder<String> builder = Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .placeholder(resId)
                .error(resId)
                .fallback(resId)
                .dontAnimate()
                .dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }

        builder.listener(new LoggingListener<>())
                .into(new GlideDrawableImageViewTarget(imageView) {

                    private void setVisibility(boolean visible) {
                        if (progressBar != null) {
                            progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        setVisibility(false);
                    }

                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        setVisibility(true);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        setVisibility(false);
                    }
                });
    }

    public static void displayLongImageSize(String url, ImageView imageView, ImageSize imageSize) {
        if (imageView == null || imageView.getContext() == null)
            return;
        int resId = R.mipmap.common_banner_onloading;
        DrawableRequestBuilder<String> builder = Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .placeholder(resId)
                .fallback(resId)
                .dontAnimate()
                .dontTransform()
                .error(resId);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void displayImageWithSizeResIdListener(String url, ImageView imageView, ImageSize imageSize, int resId, final onLoadingImageListener listener) {
        if (imageView == null || imageView.getContext() == null)
            return;
        BitmapRequestBuilder<String, Bitmap> builder = Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .asBitmap()
                .dontAnimate()
                .dontTransform()
                .placeholder(resId)
                .fallback(resId)
                .error(resId);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<>())
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        super.onResourceReady(resource, glideAnimation);
                        if (listener != null) {
                            listener.onLoadingComplete(resource);
                        }
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        if (listener != null) {
                            listener.onLoadingFailed();
                        }
                    }
                });
    }

    public static void displayImageWithoutPlaceholder(String url, ImageView imageView, ImageSize imageSize, boolean isHeader) {
        if (imageView == null || imageView.getContext() == null)
            return;
        int resId = isHeader ? R.mipmap.common_default_head : R.mipmap.common_falseimg;
        DrawableRequestBuilder<String> builder = Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .dontAnimate()
                .dontTransform()
                .fallback(resId)
                .error(resId);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void displayLocalImage(String url, ImageView imageView, ImageSize imageSize) {
        if (imageView == null || imageView.getContext() == null)
            return;
        int resId = R.mipmap.common_broken_image_black;
        DrawableRequestBuilder<String> builder = Glide.with(Global.application)
                .load(replaceImageUrlHost(url))
                .dontAnimate()
                .dontTransform()
                .placeholder(resId)
                .fallback(resId)
                .error(resId);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.listener(new LoggingListener<>())
                .into(imageView);
    }

    public static void clearMemory() {
        final Glide glide = Glide.get(Global.application);
        glide.clearMemory();
        ThreadExecutorManager.getInstance().runInThreadPool(glide::clearDiskCache);
    }

    public static void getCacheImage(String path, final Handler handler, final onLoadingImageListener listener) {
        Glide.with(Global.application).load(replaceImageUrlHost(path)).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                Message msg = handler.obtainMessage(100, resource.getAbsolutePath());
                handler.sendMessage(msg);
                if (listener != null) {
                    listener.onLoadingComplete(null);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (listener != null) {
                    listener.onLoadingFailed();
                }
            }
        });
    }

    public static void getImageFile(String path, final OnFileImageLoadingListener listener) {
        Glide.with(Global.application).load(replaceImageUrlHost(path)).downloadOnly(new SimpleTarget<File>() {
            @Override
            public void onResourceReady(File resource, GlideAnimation<? super File> glideAnimation) {
                if (listener != null) {
                    listener.onFileLoadingComplete(resource);
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                super.onLoadFailed(e, errorDrawable);
                if (listener != null) {
                    listener.onLoadingFailed();
                }
            }
        });
    }

    public static void getCacheImageBitmap(final String path, final Handler handler, ImageSize imageSize, final onLoadingImageListener loadingImageListener) {
        BitmapRequestBuilder<String, Bitmap> builder = Glide.with(Global.application).load(replaceImageUrlHost(path)).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE);
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.dontAnimate().dontTransform();
        builder.listener(new LoggingListener<>()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (loadingImageListener != null) {
                    loadingImageListener.onLoadingComplete(resource);
                }
                if (handler != null) {
                    WeakHashMap<String, Bitmap> map = new WeakHashMap<>();
                    map.put(path, resource);
                    handler.sendMessage(handler.obtainMessage(0, map));
                }
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                if (loadingImageListener != null) {
                    loadingImageListener.onLoadingFailed();
                }
            }
        });
    }

    public static void displayLoopImageView(String path, ImageView imageView, ImageSize imageSize, int which) {
        DrawableRequestBuilder<String> builder = Glide.with(Global.application).load(replaceImageUrlHost(path));
        if (which == 1) {
            builder.placeholder(R.mipmap.common_banner_onloading)
                    .error(R.mipmap.common_fw_banner)
                    .fallback(R.mipmap.common_fw_banner);
        } else if (which == 2) {
            builder.placeholder(R.mipmap.common_long_default_logo)
                    .error(R.mipmap.common_long_default_logo)
                    .fallback(R.mipmap.common_long_default_logo);
        }
        if (imageSize != null) {
            builder.override(imageSize.width, imageSize.height);
        }
        builder.skipMemoryCache(false)
                .dontAnimate()
                .dontTransform()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new LoggingListener<>())
                .into(imageView);
    }

    private static String replaceImageUrlHost(String url){
        String httpUrl = url;
        if(!(url.startsWith("http://") || url.startsWith("https://"))){
            httpUrl = Global.baseURL + url;
        }
        if(httpUrl.contains("exiaoxin.com") || httpUrl.contains("juziwl.com") || httpUrl.contains("imexue.com")){
            httpUrl = httpUrl.replace("http://", "https://").replace("/psmg/", "/pimgs/");
            String replaceHost = Global.UrlApi.contains("test.juziwl.com") ? "//test.juziwl.com/" : "//m.imexue.com/";
            if(httpUrl.contains("//exiaoxin.com/")){
                return httpUrl.replace("//exiaoxin.com/", replaceHost);
            } else if(httpUrl.contains("//mp.imexue.com/")){
                return httpUrl.replace("//mp.imexue.com/", replaceHost);
            } else if(httpUrl.contains("//platform.exiaoxin.com/")){
                return httpUrl.replace("//platform.exiaoxin.com/", replaceHost);
            } else if(httpUrl.contains("//platform.imexue.com/")){
                return httpUrl.replace("//platform.imexue.com/", replaceHost);
            }
            return httpUrl;
        }else {
            return httpUrl;
        }
    }

    public static void resumeLoading() {
        Glide.with(Global.application).resumeRequests();
    }

    public static void pauseLoading() {
        Glide.with(Global.application).pauseRequests();
    }

    public interface onLoadingImageListener {
        void onLoadingComplete(Bitmap bitmap);

        void onLoadingFailed();
    }

    public interface OnFileImageLoadingListener extends onLoadingImageListener {
        void onFileLoadingComplete(File file);
    }

    public static class LoggingListener<T, R> implements RequestListener<T, R> {

        @Override
        public boolean onException(Exception e, T model, com.bumptech.glide.request.target.Target<R> target, boolean isFirstResource) {
            if (BuildConfig.DEBUG) {
                Logger.e(CommonTools.outputError(e));
            }
            return false;
        }

        @Override
        public boolean onResourceReady(R resource, T model, com.bumptech.glide.request.target.Target<R> target, boolean isFromMemoryCache, boolean isFirstResource) {
            if (BuildConfig.DEBUG) {
                Logger.e("onResourceReady=" + model.toString());
            }
            return false;
        }
    }
}
