package com.baihua.yaya.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;


import com.baihua.yaya.R;

import java.util.HashMap;

/**
 * Author:byd
 * Time:11/12/2018 16:28
 * Description: ValidateCodeView
 */
public class ValidateCodeView extends android.support.v7.widget.AppCompatTextView {

    private static final String TAG = "ValidateCodeView";

    /**
     * 退出界面后，在onDestroy方法中保存当前剩余时间
     */
    private static final HashMap<Integer, Long> mStopTimes = new HashMap<>();
    /**
     * 前缀文本
     */
    private String mPreStr;
    /**
     * 后缀文本
     */
    private String mSuffixStr;
    /**
     * 原始显示字符串
     */
    private String mSrcStr;

    /**
     * Millis since epoch when alarm should stop.
     * 总时间
     */
    private long mMillisInFuture = 3000;

    /**
     * 间隔时间毫秒 固定一秒
     * The interval in millis that the user receives callbacks
     */
    private final long mCountdownInterval = 1000;

    /**
     * 最终的完整时间  例如：当前时间mCurTime为2017年08月04日18点22分20秒  30秒后结束  那么mStopTimeInFuture是mCurTime加上30*1000毫秒
     */
    private long mStopTimeInFuture;

    /**
     * boolean representing if the timer was cancelled
     */
    private boolean mCancelled = false;

    /**
     * Hander发送信息的what标识，保存结束时间的key
     */
    private int mHandlerSendCode;


    // handles counting down
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            synchronized (ValidateCodeView.this) {
                if (mCancelled) {
                    return;
                }
                //不同界面使用该空间时，发送的标识不同
                if (msg.what != mHandlerSendCode) {
                    return;
                }

                final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();
                //SystemClock.elapsedRealtime()，开机持续时间，刚开机得到的时间为1970/01/01 00:00:00
                //它包括了系统深度睡眠的时间。这个时钟是单调的，它保证一直计时，即使CPU处于省电模式，所以它是推荐使用的时间计时器。
                if (millisLeft <= 0) {
                    onFinish();
                } else {
                    long lastTickStart = SystemClock.elapsedRealtime();
                    setText(mPreStr + (millisLeft / 1000) + mSuffixStr);//前缀+剩余秒数+后缀
                    // take into account user's onTick taking time to execute
                    long delay = lastTickStart + mCountdownInterval - SystemClock.elapsedRealtime();

                    // special case: user's onTick took more than interval to
                    // complete, skip to next interval
                    while (delay < 0) delay += mCountdownInterval;
                    sendMessageDelayed(obtainMessage(mHandlerSendCode), delay);
                }
            }
        }
    };

    public ValidateCodeView(Context context) {
        super(context);
        initView(context, null, 0);
    }


    public ValidateCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs, 0);
    }

    public ValidateCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }


    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ValidateCodeView, defStyleAttr, 0);
        long mTotalSeconds = a.getInteger(R.styleable.ValidateCodeView_mTotalSeconds, 30);
        mMillisInFuture = mTotalSeconds * 1000l + 1000;//总毫秒数
        mPreStr = a.getString(R.styleable.ValidateCodeView_mPreStr);//前缀文本
        mSuffixStr = a.getString(R.styleable.ValidateCodeView_mSuffixStr);//后缀文本
        mHandlerSendCode = a.getInteger(R.styleable.ValidateCodeView_mHandlerSendCode, 1);//布局文件中不写统一认为同一个验证码
        a.recycle();
        mSrcStr = getText().toString().trim();//获取原始文字，例如：发送验证码
        setClickable(true);//设置可点击
    }


    /**
     * Cancel the countdown.
     */
    public synchronized final void cancel() {
        mCancelled = true;
        mHandler.removeMessages(mHandlerSendCode);
    }

    /**
     * Start the countdown.
     */
    public synchronized final void start() {
        setEnabled(false);//设置控件不可用(不可点击)
        mCancelled = false;
        //间隔时间
        if (mMillisInFuture <= 0) {
            onFinish();
            return;
        }
        //结束时间
        mStopTimeInFuture = SystemClock.elapsedRealtime() + mMillisInFuture;
        //发送信息
        mHandler.sendMessage(mHandler.obtainMessage(mHandlerSendCode));
    }


    /**
     * Callback fired when the time is up.
     */
    public void onFinish() {
        setEnabled(true);
        setText(mSrcStr);
    }


    /**
     * 加入生命周期的方法，在Activity的onCreate方法中调用即可
     */
    public void onCreate() {
        //如果保存了当前页面的剩余时间则自动开始
        if (mStopTimes.containsKey(mHandlerSendCode)) {
            mStopTimeInFuture = mStopTimes.get(mHandlerSendCode);
            mCancelled = false;
            final long millisLeft = mStopTimeInFuture - SystemClock.elapsedRealtime();//剩余毫秒数
            //剩余毫秒数小于或等于零，结束，否则设置按钮不可用
            if (millisLeft <= 0) {
                onFinish();
                return;
            }
            setEnabled(false);
            mHandler.sendMessage(mHandler.obtainMessage(mHandlerSendCode));
        }

    }


    /**
     * 加入生命周期的方法，在Activity的onCreate方法中调用即可
     */
    public void onDestroy() {
        //保存剩余时间
        mStopTimes.put(mHandlerSendCode, mStopTimeInFuture);
    }

}
