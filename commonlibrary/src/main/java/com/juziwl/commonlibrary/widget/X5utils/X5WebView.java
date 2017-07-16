package com.juziwl.commonlibrary.widget.X5utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import com.facebook.stetho.common.LogUtil;
import com.juziwl.commonlibrary.config.Global;
import com.tencent.smtt.export.external.interfaces.ConsoleMessage;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


public class X5WebView extends WebView {
    private int flag;
    private OnPageFinishendLisenter mOnpageFinishendListenter;
    private OnProgressChangeListener onProgressChangeListener;

    private WebViewClient client = new WebViewClient() {
        /**
         * 防止加载网页时调起系统浏览器
         */
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if(url.startsWith("tel:")){
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(url));
                view.getContext().startActivity(intent);
            }else{
                view.loadUrl(url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView webView, String s) {
            super.onPageFinished(webView, s);
            if(mOnpageFinishendListenter != null){
                mOnpageFinishendListenter.onPageFinish(s);
            }
        }

        @Override
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if(sslError.getCertificate().getIssuedTo().getCName().contains("imexue.com")
                    ||sslError.getCertificate().getIssuedTo().getCName().contains("juziwl.com")){
                sslErrorHandler.proceed();
            }else{
                sslErrorHandler.cancel();
            }
        }
    };

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context context, AttributeSet arg1) {
        super(context, arg1);
        setWebViewClient(client);
        setWebChromeClient(chromeClient);
        initWebViewSettings(context);
        getView().setClickable(true);
        getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);
    }

    private void initWebViewSettings(final Context context) {
        WebSettings webSetting = this.getSettings();
        webSetting.setDefaultTextEncodingName(Global.encoding);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSetting.setAppCachePath(context.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(context.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(context.getDir("geolocation", 0).getPath());
        // 关于是否缩放
        webSetting.setDisplayZoomControls(false);
        webSetting.setLoadsImagesAutomatically(true);  //支持自动加载图片
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        if (Build.VERSION.SDK_INT >= 21) {
            webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                // 监听下载功能，当用户点击下载链接的时候，直接调用系统的浏览器来下载
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

        if (getX5WebViewExtension() != null) {
            Bundle data = new Bundle();
            data.putBoolean("standardFullScreen", false);// true表示标准全屏，false表示X5全屏；不设置默认false，
            data.putBoolean("supportLiteWnd", false);// false：关闭小窗；true：开启小窗；不设置默认true，
            data.putInt("DefaultVideoScreen", 2);// 1：以页面内开始播放，2：以全屏开始播放；不设置默认：1
            getX5WebViewExtension().invokeMiscMethod("setVideoParams", data);
        }
        CookieSyncManager.createInstance(context);
        CookieSyncManager.getInstance().sync();
    }

    public void setOnPageFinishListenter(final OnPageFinishendLisenter onPageFinishendLisenter) {
        mOnpageFinishendListenter = onPageFinishendLisenter;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }

    @SuppressLint("SetJavaScriptEnabled")
    public X5WebView(Context arg0) {
        this(arg0, null);
    }


    public void setFlag(int i) {
        flag = i;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (flag == 1) {
            return super.onKeyDown(keyCode, event);
        } else {
//			if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK && canGoBack()) {  //表示按返回键时的操作
                goBack();
                return true;    //已处理
            }
//			}
            return super.onKeyDown(keyCode, event);
        }
    }

    private WebChromeClient chromeClient = new WebChromeClient() {
        @Override
        public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }

        @Override
        public void onHideCustomView() {
            super.onHideCustomView();
        }

        @Override
        public void onProgressChanged(WebView webView, int i) {
            if(onProgressChangeListener != null){
                onProgressChangeListener.onProgressChanged(i);
            }
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            LogUtil.d("message = " + consoleMessage.message() + "\tlineNumber = "
                    + consoleMessage.lineNumber() + "\tsourceId = " + consoleMessage.sourceId(), false);
            return super.onConsoleMessage(consoleMessage);
        }
    };

}
