package com.baihua.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.baihua.common.ibase.ICreateTemplate;
import com.baihua.yaya.R;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements ICreateTemplate {
    public View mRootView;
    protected boolean isPrepared = false;
    Unbinder unbinder;

    public abstract int setRootView();

    public BaseFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getActivity() != null) {
            int resourseId = setRootView();
            mRootView = LayoutInflater.from(getActivity()).inflate(
                    resourseId, null);

            unbinder = ButterKnife.bind(this, mRootView);
            dealSequence();
            isPrepared = true;
        }
        return mRootView;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            if (isVisibleToUser && isPrepared) {
                onVisibleDo();
            }
        } else {
            onInVisibleDo();
        }
    }

    protected void onInVisibleDo() {

    }

    protected void onVisibleDo() {

    }

    @Override
    public void dealSequence() {
        setLayout();
        setHandler();
        initMember();
        setListener();
    }

    protected void toast(int psTip) {
        if (getActivity() != null) {
            ToastUtils.showShort(psTip);
        }
    }

    protected void toast(String psTip) {
        if (getActivity() != null) {
            ToastUtils.showShort(psTip);
        }
    }

    // 加载动画
    private ZLoadingDialog loadingDialog;

    public void showLoading() {
        /*显示加载动画*/
        if (loadingDialog == null) {
            if (null != getContext()) {
                loadingDialog = new ZLoadingDialog(getContext());
                loadingDialog.setLoadingBuilder(Z_TYPE.STAR_LOADING)
                        .setLoadingColor(this.getResources().getColor(R.color.color_font_app))
                        .setHintText("Loading...")
                        .setHintTextSize(16)
                        .setHintTextColor(this.getResources().getColor(R.color.color_font_app))
                        .setCanceledOnTouchOutside(false)
                        .setCancelable(true);
            }
        }
        loadingDialog.show();
    }

    public void finishLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        finishLoading();
        unbinder.unbind();
    }
}
