package com.juziwl.commonlibrary.utils;

import android.app.Activity;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.BaseActivity;
import com.juziwl.commonlibrary.config.BaseFragment;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/4/29
 * @description
 */
public class RxUtils {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper(Activity activity) {    //compose简化线程
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(subscription -> {
                    if (activity != null) {
                        if (!DialogManager.getInstance().isShow()) {
                            DialogManager.getInstance().createLoadingDialog(activity,
                                    activity.getString(R.string.common_onloading)).show();
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Activity里面统一Observable处理
     *
     * @param activity     BaseActivity对象
     * @param observable   需要耗时操作的Observable
     * @param isNeedDialog 是否需要显示加载框
     * @return 处理后的Observable
     */
    public static <T> Flowable<T> getActivityObservable(BaseActivity activity, Flowable<T> observable,
                                                        boolean isNeedDialog) {
        return observable.compose(rxSchedulerHelper(isNeedDialog ? activity : null))
                .compose(activity.bindToLifecycle());
    }

    /**
     * fragment里面统一Observable处理
     *
     * @param fragment     BaseFragment对象
     * @param observable   需要耗时操作的Observable
     * @param isNeedDialog 是否需要显示加载框
     * @return 处理后的Observable
     */
    public static <T> Flowable<T> getFragmentObservable(BaseFragment fragment, Flowable<T> observable,
                                                        boolean isNeedDialog) {
        return observable.compose(rxSchedulerHelper(isNeedDialog ? fragment.getActivity() : null))
                .compose(fragment.bindToLifecycle());
    }
}
