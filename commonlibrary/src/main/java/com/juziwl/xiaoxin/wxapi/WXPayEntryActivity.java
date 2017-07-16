package com.juziwl.xiaoxin.wxapi;


import android.content.Intent;
import android.os.Bundle;

import com.juziwl.commonlibrary.config.BaseActivity;
import com.juziwl.commonlibrary.mvp.view.WXDelegate;
import com.juziwl.commonlibrary.utils.AppManager;
import com.juziwl.commonlibrary.utils.CommonTools;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import net.sourceforge.simcpux.Constants;


public class WXPayEntryActivity extends BaseActivity<WXDelegate> implements IWXAPIEventHandler {

    // --Commented out by Inspection (2017/2/10 0010 上午 9:46):private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void initCustomTopbar() {

    }

    @Override
    protected void injectActivity() {

    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected Class<WXDelegate> getDelegateClass() {
        return WXDelegate.class;
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                CommonTools.showToast("支付成功");
                sendBroadcast(new Intent("com.Pay"));
                finish();
            } else if (resp.errCode == -1) {
                CommonTools.showToast("支付失败");
                sendBroadcast(new Intent("com.Pay.error"));
                finish();
            } else if (resp.errCode == -2) {
                CommonTools.showToast("支付取消");
                sendBroadcast(new Intent("com.Pay.cancle"));
                finish();
            }
        }
    }
}