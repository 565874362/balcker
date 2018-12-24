package com.baihua.yaya.wxapi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

//    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (WeiXinPay.getInstance(this) != null) {
            WeiXinPay.getInstance(this).getWXApi().handleIntent(getIntent(), this);
        } else {
            finish();
        }
//        api = WXAPIFactory.createWXAPI(this, "");
//        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (WeiXinPay.getInstance(this) != null) {
            WeiXinPay.getInstance(this).getWXApi().handleIntent(intent, this);
        }
//        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        //4、支付结果回调 https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=8_5
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (WeiXinPay.getInstance(this) != null) {
                if (resp.errStr != null) {
                    Log.e("weiXinPay", "errStr=" + resp.errStr);
                }
                WeiXinPay.getInstance(this).onResp(resp.errCode, resp.errStr);
                finish();
            }
        }
    }
}