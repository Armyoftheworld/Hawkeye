package com.juziwl.commonlibrary.config;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.facebook.stetho.common.LogUtil;
import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.model.Event;
import com.juziwl.commonlibrary.utils.AppManager;
import com.juziwl.commonlibrary.utils.RxBus;
import com.juziwl.commonlibrary.utils.RxUtils;
import com.juziwl.commonlibrary.utils.StatusBarUtil;
import com.juziwl.commonlibrary.utils.TopBarBuilder;
import com.juziwl.commonlibrary.utils.UserPreference;
import com.juziwl.commonlibrary.view.PageStateView;
import com.kymjs.themvp.presenter.ActivityPresenter;
import com.orhanobut.logger.Logger;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;


/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月18日
 * @description 应用程序Activity的继承类，每个activity都必须继承此activity
 */
public abstract class BaseActivity<T extends BaseAppDelegate> extends ActivityPresenter<T> implements LifecycleProvider<ActivityEvent>,
        OnDelegateActivityInteractiveListener {
    protected RelativeLayout rootView;

    public String TAG = getClass().getSimpleName();
    protected Handler mHandler = null;
    protected String uid = "", token = "";
    protected boolean canOpen = true;//用来防止多次点击打开多个页面
    public List<String> functionNames = new ArrayList<>();
    public String startTime = "";
    public String endTime = "";
    public int requestCode;
    public Intent intentData;
    // 处理系统发出的广播
    private BroadcastReceiver broadcastReceiver = null;
    private BroadcastReceiver localBroadcastReceiver = null;
    protected TopBarBuilder topBarBuilder;
    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Inject
    UserPreference userPreference;
    protected RxPermissions rxPermissions;
    //状态页面
    private PageStateView pageStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setBackgroundDrawable(null);
        viewDelegate.setInteractiveListener(this);
        rootView = new RelativeLayout(this);
        super.onCreate(savedInstanceState);  //父类都加载布局
        super.setContentView(rootView);
        if (isSetStatusBar()) {
            setStatusBar();
        }
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        AppManager.getInstance().addActivity(this);
        rxPermissions = new RxPermissions(this);
        injectActivity();
        if (userPreference != null) {
            uid = userPreference.getUid();
            token = userPreference.getToken();
        }
        //注册广播
        initBroadcastAndLocalBroadcastAction();
        initEventAndData();
        // 注册rxbus
        initRxBus();

    }

    protected boolean isSetStatusBar() {
        return true;
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.common_new_main_color), 0);
    }

    private void initRxBus() {
        RxUtils.getActivityObservable(this, RxBus.getDefault().take(), false)
                .subscribe(event -> {
                    dealWithRxEvent(event.action, event);
                });
    }

    public void dealWithRxEvent(String action, Event event) {
    }

    @Override
    public void onInteractive(String action, Bundle data) {
    }

    @Override
    protected void initToolbar() {

        if (!isShowToolBar()) {

            return;
        }

        //有toolbar先放置toolbar
        Toolbar toolbar = viewDelegate.getToolbar();
        if (toolbar == null) {
            toolbar = (Toolbar) View.inflate(this, R.layout.common_base_toolbar, null);
            topBarBuilder = new TopBarBuilder(toolbar.findViewById(R.id.top_title_headerbar));
            initCustomTopbar();
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            toolbar.setLayoutParams(lp);
            toolbar.setId(R.id.toolbarid);
            rootView.addView(toolbar, lp); //添加toolbar
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
    }

    protected abstract void initCustomTopbar();

    protected abstract void injectActivity();

    protected abstract void initEventAndData();


    //zijide
    @Override
    public void setContentView(int layoutId) {
        setContentView(View.inflate(this, layoutId, null));
    }

    @Override
    public void setContentView(View view) {  //设置内容布冯
        if (isShowToolBar()) {
            // 有toolbar  添加activity中的布局在他的下面
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            lp.addRule(RelativeLayout.BELOW, R.id.toolbarid);//与父容器的左侧对齐
            view.setLayoutParams(lp);//设置布局参数
            rootView.addView(view);//RelativeLayout添加子View
            pageStateView = new PageStateView(this);
            pageStateView.setLayoutParams(lp);
            rootView.addView(pageStateView);
        } else {
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            view.setLayoutParams(lp);//设置布局参数
            rootView.addView(view);//RelativeLayout添加子View
            pageStateView = new PageStateView(this);
            pageStateView.setLayoutParams(lp);
            rootView.addView(pageStateView);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);//友盟统计监测
        canOpen = true;
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);    //友盟统计监测
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
        if (localBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(localBroadcastReceiver);
        }
        AppManager.getInstance().killActivity(this);
    }

    protected boolean isShowToolBar() {
        return true;
    }

    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
    }

    public void showLog(String content) {
        showLog(content, null);
    }

    public void showLog(String content, Throwable ex) {
        if (TextUtils.isEmpty(content))
            return;
        if (ex == null) {
            Logger.e(content, false);
        } else {
            LogUtil.e(content, ex, false);
        }
    }

    public void reverseInfo() {
        startTime = "";
        endTime = "";
        if (functionNames !=
                null) {
            functionNames.clear();
        }
    }

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    /**
     * 显示提示对话框
     */
    private void showTipsDialog() {
        new AlertDialog.Builder(this)
                .setTitle("提示信息")
                .setMessage("当前应用缺少必要权限，该功能暂时无法使用。如若需要，请单击【确定】按钮前往设置中心进行权限授权。")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startAppSettings();
                    }
                }).show();
    }

    /**
     * 启动当前应用设置页面
     */
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivity(intent);
    }

    //本地广播和系统广播初始化
    private void initBroadcastAndLocalBroadcastAction() {
        ArrayList<String> broadcastAction = getBroadcastAction();
        if (broadcastAction != null && broadcastAction.size() > 0) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    dealWithBroadcastAction(context, intent);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            for (String action : broadcastAction) {
                intentFilter.addAction(action);
            }
            registerReceiver(broadcastReceiver, intentFilter);
        }

        ArrayList<String> localBroadcastAction = getLocalBroadcastAction();
        if (localBroadcastAction != null && !localBroadcastAction.isEmpty()) {
            localBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    dealWithBroadcastAction(context, intent);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            for (String action : localBroadcastAction) {
                intentFilter.addAction(action);
            }
            LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(localBroadcastReceiver, intentFilter);
        }
    }

    // 子类添加的action
    public ArrayList<String> getBroadcastAction() {  //钩子函数
        return null; //默认返回空之类可以添加
    }

    // 子类添加的action
    public ArrayList<String> getLocalBroadcastAction() {  //钩子函数
        return null; //默认返回空之类可以添加
    }

    public void dealWithBroadcastAction(Context context, Intent intent) {
    }


    public static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;
        public Activity activity;

        public MyHandler(Activity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            activity = mActivityReference.get();
        }
    }

    public static class MyThread extends Thread {
        WeakReference<Activity> mWeakReference;
        public Activity activity;

        public MyThread(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            activity = mWeakReference.get();
        }
    }

    public static class MyRunable implements Runnable {

        WeakReference<Activity> mWeakReference;
        public Activity activity;

        public MyRunable(Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            activity = mWeakReference.get();

        }
    }

    //状态页面显示
    public void showNoNetWork(){
        if (pageStateView!=null) {
            pageStateView.showStatePage(PageStateView.NoNETWORK);
        }

    }







}
