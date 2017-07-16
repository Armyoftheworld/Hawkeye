package com.juziwl.xiaoxin.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.BaseActivity;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.mvp.view.WXDelegate;
import com.juziwl.commonlibrary.utils.AppManager;
import com.juziwl.commonlibrary.utils.CommonTools;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXEntryActivity extends BaseActivity<WXDelegate> implements IWXAPIEventHandler {

    private IWXAPI api;
   // static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
		api = WXAPIFactory.createWXAPI(this, Global.APP_ID, false);
		api.registerApp(Global.APP_ID);
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
    protected Class getDelegateClass() {
        return WXDelegate.class;
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        int result = 0;

        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                Intent mIntent = new Intent("WxShare_result");
                mIntent.putExtra("result", "true");

                //发送广播
                sendBroadcast(mIntent);

                result = R.string.common_errcode_success;
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = R.string.common_errcode_cancel;
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = R.string.common_errcode_deny;
                break;
            case BaseResp.ErrCode.ERR_SENT_FAILED:
                Intent Intent = new Intent("WxShare_result");
                Intent.putExtra("result", "false");
                //发送广播
                sendBroadcast(Intent);
                break;
            default:
                result = R.string.common_errcode_unknown;
                break;
        }

        CommonTools.showToast(result);
        finish();
    }
}