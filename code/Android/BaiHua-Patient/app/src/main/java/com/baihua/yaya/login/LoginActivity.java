package com.baihua.yaya.login;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.MainActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.widget.ValidateCodeView;
import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:4/12/2018 9:11
 * Description: 登录页面
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.register_et_mobile_no)
    EditText registerEtMobileNo;
    @BindView(R.id.login_tv_get_code)
    ValidateCodeView loginTvGetCode;
    @BindView(R.id.login_et_code)
    EditText loginEtCode;
    @BindView(R.id.login_tv_login)
    TextView loginTvLogin;

    @Override
    public void setLayout() {
        mIbLeft.setVisibility(View.GONE);
        setTitle(getString(R.string.login_login));
        setContentView(R.layout.activity_login);
        loginTvGetCode.onCreate();
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {

    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.login_tv_get_code, R.id.login_tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_get_code:
                loginTvGetCode.start();
                break;
            case R.id.login_tv_login:
                ActivityUtils.startActivity(MainActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginTvGetCode.onDestroy();
    }
}
