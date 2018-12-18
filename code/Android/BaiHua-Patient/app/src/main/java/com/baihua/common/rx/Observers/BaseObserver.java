package com.baihua.common.rx.Observers;


import com.baihua.common.rx.Response;
import com.baihua.common.rx.RxExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by JohnsonFan on 2017/9/29.
 */

public abstract class BaseObserver<T> implements Observer<Response<T>> {

    @Override
    public final void onNext(@NonNull Response<T> result) {
        if (result.getCode() == 0) {
            onSuccess(result.getData());
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
