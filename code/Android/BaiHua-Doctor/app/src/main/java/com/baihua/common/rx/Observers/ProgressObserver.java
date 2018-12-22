package com.baihua.common.rx.Observers;

import android.content.Context;
import android.text.TextUtils;

import com.afollestad.materialdialogs.MaterialDialog;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by JohnsonFan on 2017/9/29.
 */

public abstract class ProgressObserver<T> extends BaseObserver<T> {
    private MaterialDialog mMaterialDialog;
    private Context mContext;
    private String mLoadingText;
    private boolean mIsShowLoading;

    public ProgressObserver(Context context) {
        this(context, false, null);
    }

    public ProgressObserver(Context context, boolean isShow) {
        this(context, isShow, null);
    }

    public ProgressObserver(Context context, boolean isShowLoading, String loadingText) {
        mContext = context;
        mLoadingText = loadingText;
        mIsShowLoading = isShowLoading;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!d.isDisposed()&& mIsShowLoading) {
            mMaterialDialog = new MaterialDialog.Builder(mContext).content(TextUtils.isEmpty(mLoadingText) ? "正在加载中..."
                    : mLoadingText).progress(true, 0).build();
            mMaterialDialog.show();
        }
    }

    @Override
    public void onComplete() {
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        if (mMaterialDialog != null) {
            mMaterialDialog.dismiss();
        }
    }

}
