package com.juziwl.commonlibrary.utils;

import android.widget.Toast;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.orhanobut.logger.Logger;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/4/30
 * @description 网络请求基本订阅者
 */
public abstract class NetworkSubscriber<T> implements Subscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onComplete() {
        DialogManager.getInstance().cancelDialog();
    }

    @Override
    public void onError(Throwable e) {
        DialogManager.getInstance().cancelDialog();
        Logger.e(e, "");
        if (!NetworkUtils.isNetworkAvailable(Global.application)) {
            ToastUtils.showToast(R.string.common_useless_network);
        } else {
            if(!dealHttpException(e)){
                ToastUtils.showToast(R.string.common_fail_to_request);
            }
        }
    }

    @Override
    public void onNext(T t) {
        Logger.json(t.toString());
    }

    protected boolean dealHttpException(Throwable e) {
        return false;
    }
}
