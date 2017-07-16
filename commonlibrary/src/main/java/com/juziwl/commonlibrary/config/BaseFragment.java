package com.juziwl.commonlibrary.config;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;

import com.juziwl.commonlibrary.model.Event;
import com.juziwl.commonlibrary.utils.RxBus;
import com.juziwl.commonlibrary.utils.RxUtils;
import com.juziwl.commonlibrary.utils.UserPreference;
import com.kymjs.themvp.presenter.FragmentPresenter;
import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.umeng.analytics.MobclickAgent;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @author wy
 * @version V_5.0.0
 * @date 2016/3/1
 * @description 基类fragment
 */
public abstract class BaseFragment<T extends BaseAppDelegate> extends FragmentPresenter<T> implements LifecycleProvider<FragmentEvent>,
        OnDelegateActivityInteractiveListener {
    public String TAG = this.getClass().getSimpleName();
    public String startTime = "";
    public String endTime = "";
    public List<String> functionName = new ArrayList<>();
    public String uid = "";
    public String token = "";
    public boolean hideflag = false;
    private SparseArray<View> mViewMap = new SparseArray<View>();
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"; //防止fragment重叠
    public String fragmentTitle;
    private boolean isVisible;
    private boolean isPrepared;
    private boolean isFirstLoad = true;
    private View view;
    public Context mcontent;
    // 圈子而外加的
    protected Handler mHandler = null;
    @Inject
    UserPreference userPreference;
    private final BehaviorSubject<FragmentEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<FragmentEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull FragmentEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject);
    }

    @Override
    public void onAttach(android.app.Activity activity) {
        super.onAttach(activity);
        lifecycleSubject.onNext(FragmentEvent.ATTACH);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE);
        //注入对象
        injectFragment();
        uid = userPreference.getUid();
        token = userPreference.getToken();
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }

        initBroadcastAction();//初始化广播
        RxUtils.getFragmentObservable(this, RxBus.getDefault().take(), false).subscribe(event -> {
            dealEvent(event);
        });
    }

    protected abstract void injectFragment();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW);
        isFirstLoad = true;
        isPrepared = true;
        viewDelegate.setInteractiveListener(this);
        commonLoad(viewDelegate.getRootView());  //比initwidget后执行 initwidget初始化控件 实现类用butterknife实现
        lazyLoad(viewDelegate.getRootView());
    }

    @Override
    public void onStart() {
        super.onStart();
        mcontent = getActivity();

        lifecycleSubject.onNext(FragmentEvent.START);
    }

    public void dealEvent(Event event) {

    }

    @Override
    public void onInteractive(String action, Bundle bundle) {

    }

    @Override
    public void onResume() {
        super.onResume();
        lifecycleSubject.onNext(FragmentEvent.RESUME);
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE);
        super.onPause();
        if (!hideflag) {
        }
        MobclickAgent.onPageEnd(TAG);
    }

    @Override
    public void onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW);
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY);
        if (broadcastReceiver != null) {
            getActivity().unregisterReceiver(broadcastReceiver);  //取消注册广播
        }
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH);
        super.onDetach();
    }


    // 普通加载  获取参数argument 获取网络数据
    public void commonLoad(View view) {
    }


    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     *
     * @param isVisibleToUser 是否显示出来了
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     *
     * @param hidden hidden True if the fragment is now hidden, false if it is not
     *               visible.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    public void onVisible() {
        lazyLoad(viewDelegate.getRootView());
    }

    public void onInvisible() {
    }

    /**
     * 要实现延迟加载Fragment内容,需要在 onCreateView
     * isPrepared = true;
     */
    public void lazyLoad(View view) {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            //if (!isAdded() || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        lazyLoadData(view);
    }

    public void lazyLoadData(View view) {
    }

    public void stateUpdate() {

    }

    public void showLog(String content) {
        showLog(content, null);
    }

    public void showLog(String content, Throwable ex) {
        if (TextUtils.isEmpty(content))
            return;
        if (ex == null) {
            Logger.e(content);
        } else {
            Logger.e(content, ex);
        }
    }

    public static class MyHandler extends Handler {
        WeakReference<Activity> mActivityReference;
        public Activity activity;

        public MyHandler(Activity activity) {
            mActivityReference = new WeakReference<Activity>(activity);
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

    // 处理系统发出的广播
    public BroadcastReceiver broadcastReceiver;

    //注册广播
    public void initBroadcastAction() {
        if (setBroadcastAction() != null && setBroadcastAction().size() > 0) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    dealWithBroadcastAction(intent.getAction(), intent);//之类可以覆盖
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            for (String action : setBroadcastAction()
                    ) {
                intentFilter.addAction(action);
            }
            getActivity().registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    // 之类添加的action
    public ArrayList<String> setBroadcastAction() {
        return null; //默认返回空之类可以添加
    }

    public void dealWithBroadcastAction(String action, Intent intent) {
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
//        只要上面的9行代码！ FragmentState没帮我们保存Hidden状态，那就我们自己来保存，在页面重启后，我们自己来决定Fragment是否显示！
//        解决思路转变了，由Activity/父Fragment来管理子Fragment的Hidden状态转变为 由Fragment自己来管理自己的Hidden状态！
    }
}
