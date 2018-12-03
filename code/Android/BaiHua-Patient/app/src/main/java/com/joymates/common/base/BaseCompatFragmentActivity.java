package com.joymates.common.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.joymates.common.ibase.ICreateTemplate;


public abstract class BaseCompatFragmentActivity extends AppCompatActivity implements
        ICreateTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setScreenOrientation();
        setWinFeature();
        super.onCreate(savedInstanceState);
        dealSequence();
    }

    @Override
    protected void onResume() {
        bizLogic();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void dealSequence() {
        setLayout();
        setHandler();
        initMember();
        setListener();

    }

    /**
     * 在onResume中的业务逻辑
     */
    protected void bizLogic() {

    }

    private void setWinFeature() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected void setScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void toast(String psTip) {
        ToastUtils.showShort(psTip);
    }

    public void toast(int psTip) {
        ToastUtils.showShort(psTip);
    }

}
