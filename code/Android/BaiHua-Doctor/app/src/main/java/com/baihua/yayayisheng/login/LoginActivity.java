package com.baihua.yayayisheng.login;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.MainActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.widget.ValidateCodeView;
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
    @BindView(R.id.login_tv_register)
    TextView loginTvRegister;

    @Override
    public void setLayout() {
        mIbLeft.setVisibility(View.GONE);
        setTitle(getString(R.string.login_login));
        setContentView(R.layout.activity_login);
        loginTvGetCode.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginTvGetCode.onDestroy();
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

    @OnClick({R.id.login_tv_get_code, R.id.login_tv_login, R.id.login_tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tv_get_code:
                loginTvGetCode.start();
                break;
            case R.id.login_tv_login:
                ActivityUtils.startActivity(MainActivity.class);
                break;
            case R.id.login_tv_register:
                ActivityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }
}
