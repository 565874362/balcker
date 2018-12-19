package com.baihua.yayayisheng;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;

import com.baihua.yayayisheng.login.LoginActivity;
import com.baihua.yayayisheng.util.CommonUtils;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.SPUtils;

/**
 *
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        setContentView(R.layout.activity_splash);


        CommonUtils.transparentStatusBar(this);

        /* Can do something init */
        int SPLASH_DISPLAY_LENGTH = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getWindow().setFlags(
                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                if (TextUtils.isEmpty(SPUtils.getInstance("token").getString("token", "")))
                    Utils.gotoActivity(SplashActivity.this, LoginActivity.class, true, null, null);
                else
                    Utils.gotoActivity(SplashActivity.this, MainActivity.class, true, null, null);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
