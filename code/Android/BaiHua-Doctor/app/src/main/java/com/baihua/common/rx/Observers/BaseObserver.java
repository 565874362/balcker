package com.baihua.common.rx.Observers;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baihua.common.rx.Response;
import com.baihua.common.rx.RxExceptionUtil;
import com.baihua.yayayisheng.util.CommonUtils;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by JohnsonFan on 2017/9/29.
 */

public abstract class BaseObserver<T> implements Observer<Response<T>> {

    private MaterialDialog mDialog;

    @Override
    public final void onNext(@NonNull Response<T> result) {
        if (result.getCode() == 0) {
            onSuccess(result.getData());
        } else if (result.getCode() == 401) {
            if (null != mDialog && mDialog.isShowing()) {
                return;
            }
            mDialog = new MaterialDialog.Builder(ActivityUtils.getTopActivity())
                    .content("令牌已失效，请重新登陆")
                    .positiveText("重新登录")
                    .cancelable(false)
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@android.support.annotation.NonNull MaterialDialog dialog, @android.support.annotation.NonNull DialogAction which) {
                            CommonUtils.clearToken();
                            Utils.goLogin(ActivityUtils.getTopActivity());
                        }
                    }).build();
            mDialog.show();
        } else {
            onFailure(new Exception(result.getMsg()), result.getMsg());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }


    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e, String errorMsg);
}
