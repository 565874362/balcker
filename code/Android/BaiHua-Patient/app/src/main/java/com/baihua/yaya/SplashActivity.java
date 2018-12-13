package com.baihua.yaya;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.baihua.yaya.login.LoginActivity;
import com.baihua.yaya.util.Utils;


/**
 *
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* Can do something init */
        int SPLASH_DISPLAY_LENGTH = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.gotoActivity(SplashActivity.this, LoginActivity.class, true, null, null);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
