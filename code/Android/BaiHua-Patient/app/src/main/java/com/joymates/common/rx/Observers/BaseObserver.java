package com.joymates.common.rx.Observers;


import com.joymates.common.rx.Response;
import com.joymates.common.rx.RxExceptionUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by JohnsonFan on 2017/9/29.
 */

public abstract class BaseObserver<T> implements Observer<Response<T>> {

    @Override
    public final void onNext(@NonNull Response<T> result) {
        if (result.getRet() == -1) {
            onFailure(new Exception(result.getMsg()), result.getMsg());
        } else {
            onSuccess(result.getData());
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
