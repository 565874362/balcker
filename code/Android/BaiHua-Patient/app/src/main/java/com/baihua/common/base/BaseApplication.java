package com.baihua.common.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.multidex.MultiDex;
import android.support.v4.content.ContextCompat;

import com.baihua.yaya.R;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;
import com.mob.MobSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.zlw.main.recorderlib.RecordManager;

import io.rong.imkit.RongIM;


/**
 *
 */

public class BaseApplication extends Application {
    public static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        theThirdInit();

        // 工具类 初始化
        Utils.init(this);
        LogUtils.getConfig().setLogSwitch(true);
        setToastProperty();
    }


    private void theThirdInit() {
        MultiDex.install(this);
        MobSDK.init(this);//MobSDK
        OkGo.getInstance().init(this);// 网络请求库okgo初始化
        RongIM.init(this);// 融云IM
    }


    /**
     * 设置Toast的属性
     */
    private void setToastProperty() {
        ToastUtils.setBgColor(ContextCompat.getColor(this, R.color.colorPrimary));
        ToastUtils.setMsgColor(Color.WHITE);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);//让应用支持多DEX文件
    }


    //SmartRefreshLayout 刷新的头布局和尾布局
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//                layout.setPrimaryColorsId(R.color.white, R.color.color_font_black);//全局设置主题颜色
                return new ClassicsHeader(context)
                        .setEnableLastTime(false);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//                return new BezierRadarHeader(context);
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }
}