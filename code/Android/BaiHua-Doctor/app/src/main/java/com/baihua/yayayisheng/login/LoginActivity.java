package com.baihua.yayayisheng.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yayayisheng.MainActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.form.LoginForm;
import com.baihua.yayayisheng.entity.TokenEntity;
import com.baihua.yayayisheng.entity.VerificationEntity;
import com.baihua.yayayisheng.util.CommonUtils;
import com.baihua.yayayisheng.util.Utils;
import com.baihua.yayayisheng.widget.ValidateCodeView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;

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

    private LoginForm mLogin = new LoginForm();
    private String mCodeId = ""; // 验证码ID
    private String mCode = ""; // 验证码

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
                getLoginCode();
                break;
            case R.id.login_tv_login:
                login();
                break;
            case R.id.login_tv_register:
                ActivityUtils.startActivity(RegisterActivity.class);
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {

        if (TextUtils.isEmpty(registerEtMobileNo.getText().toString().trim())) {
            toast("请输入手机号");
            return;
        }

        if (TextUtils.isEmpty(loginEtCode.getText().toString().trim())) {
            toast("请输入短信验证码");
            return;
        }

        if (!CommonUtils.getTextString(loginEtCode).equals(mCode)) {
            toast("请输入正确的验证码");
            return;
        }

        LoginForm loginForm = new LoginForm();
        loginForm.setAccount(registerEtMobileNo.getText().toString().trim());
        loginForm.setCaptchaCode(mCode);
        loginForm.setCaptchaId(mCodeId);
        RxHttp.getInstance().getSyncServer().login(loginForm)
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<TokenEntity>(this) {
                    @Override
                    public void onSuccess(TokenEntity result) {
                        LogUtils.e(result.getToken());
                        SPUtils.getInstance("token").put("token", result.getToken());
                        SPUtils.getInstance("doctor").put("id", result.getInfo().getId());
                        SPUtils.getInstance("doctor").put("photo", result.getInfo().getPhoto());
                        SPUtils.getInstance("doctor").put("name", result.getInfo().getName());
                        Utils.gotoActivity(LoginActivity.this, MainActivity.class, true, null, null);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    /**
     * 获取验证码
     */
    private void getLoginCode() {

        if (TextUtils.isEmpty(registerEtMobileNo.getText().toString().trim())) {
            toast("请输入手机号");
            return;
        }

        loginTvGetCode.start();

        RxHttp.getInstance().getSyncServer().getVerificationCode(registerEtMobileNo.getText().toString().trim())
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<VerificationEntity>(this) {
                    @Override
                    public void onSuccess(VerificationEntity result) {
                        LogUtils.e(result.getCaptchaId() + "\n");
                        LogUtils.e(result.getVerificationCode());
                        mCode = result.getVerificationCode();
                        mCodeId = result.getCaptchaId();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

}
