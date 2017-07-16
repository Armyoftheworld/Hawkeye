package com.juziwl.commonlibrary.config;

import android.view.View;

import com.juziwl.commonlibrary.utils.CommonTools;
import com.kymjs.themvp.view.AppDelegate;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.EIconProgressLayout;

import io.reactivex.functions.Consumer;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/06/20
 * @description
 */
public abstract class BaseAppDelegate extends AppDelegate {

    protected OnDelegateActivityInteractiveListener interactiveListener;

    public void setInteractiveListener(OnDelegateActivityInteractiveListener interactiveListener) {
        this.interactiveListener = interactiveListener;
    }

    protected void initPullToRefresh(TwinklingRefreshLayout refreshLayout) {
        initPullToRefresh(refreshLayout, EIconProgressLayout.GREEN);
    }

    protected void initPullToRefresh(TwinklingRefreshLayout refreshLayout, @EIconProgressLayout.AnimType int type) {
        EIconProgressLayout eIconProgressLayout = new EIconProgressLayout(getActivity(), type);
        refreshLayout.setHeaderView(eIconProgressLayout);
        refreshLayout.setOverScrollRefreshShow(false);
        refreshLayout.setEnableOverScroll(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                onRefreshing(refreshLayout);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                onLoading(refreshLayout);
            }
        });
    }

    protected void onRefreshing(final TwinklingRefreshLayout refreshLayout) {
    }

    protected void onLoading(final TwinklingRefreshLayout refreshLayout) {
    }

    protected void click(View v, Consumer<Object> onNext) {
        CommonTools.click(v, onNext);
    }

}
