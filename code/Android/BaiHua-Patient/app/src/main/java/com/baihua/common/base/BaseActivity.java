package com.baihua.common.base;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.baihua.common.ibase.ICreateTemplate;
import com.baihua.yaya.R;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.ButterKnife;

/**
 * 项目中的所有activity都需要继承该activity
 *
 * @author gang
 */
public abstract class BaseActivity extends AppCompatActivity implements ICreateTemplate {

    protected LayoutInflater mInflater;
    protected View mRoot;
    protected ViewGroup mVgContent;
    protected ViewGroup mVgTitleBar;
    protected ImageButton mIbLeft;
    protected TextView mIbRight, mIbRightLeft;
    protected TextView mTvTitle, mTvRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setScreenOrientation();
        setWinFeature();
        super.onCreate(savedInstanceState);
        mInflater = LayoutInflater.from(this);
        super.setContentView(R.layout.activity_base);
        mRoot = this.findViewById(R.id.root_view);
        initTitleBar();
        initContent();


        mTvTitle.setTextColor(Color.WHITE);
        mVgTitleBar.setBackgroundResource(R.drawable.base_shape_list_frame);
        mIbLeft.setImageResource(R.drawable.arrow_white);

        dealSequence();
    }

    // 子类实现该方法把activity加入到baseActivity里
    public void setContentView(int resource) {
        mVgContent.addView(mInflater.inflate(resource, null), new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        ButterKnife.bind(this);
    }

    private void initTitleBar() {
        this.mVgTitleBar = this.findViewById(R.id.title_bar);
        this.mIbLeft = this.findViewById(R.id.left);
        this.mIbRightLeft = this.findViewById(R.id.right_ib_left);
        this.mIbRight = this.findViewById(R.id.right);
        this.mTvTitle = this.findViewById(R.id.title);
        this.mTvRight = this.findViewById(R.id.right_tv);
        clickLeft();
        clickRight();
    }

    @Override
    protected void onResume() {
        bizLogic();
        fixFontSize();
        super.onResume();
    }

    /**
     * 固定字体大小，防止更改系统字体，影响APP字体
     */
    private void fixFontSize() {
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = 1;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finishLoading();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public void setScreenOrientation() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void setWinFeature() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public void dealSequence() {
        try {
            setLayout();
            setHandler();
            initMember();
            setListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * onResume中的业务逻辑处理
     */
    protected void bizLogic() {

    }

    /***
     * 设置标题栏上的按钮是否可见 true--可见 false -- 不可见
     *
     * @param mIbLeft
     *            --左侧返回按钮
     * @param mIbRight
     *            --右侧图片按钮
     * @param mTvRight
     *            --右侧文字按钮
     * @param mTvTitle
     *            --中间标题
     */
    public void setTitleButtonVisible(boolean mIbLeft, boolean mIbRight,
                                      boolean mTvRight, boolean mTvTitle) {
        if (mIbLeft)
            this.mIbLeft.setVisibility(View.VISIBLE);
        else
            this.mIbLeft.setVisibility(View.INVISIBLE);

        if (mIbRight)
            this.mIbRight.setVisibility(View.VISIBLE);
        else
            this.mIbRight.setVisibility(View.INVISIBLE);

        if (mTvRight)
            this.mTvRight.setVisibility(View.VISIBLE);
        else
            this.mTvRight.setVisibility(View.INVISIBLE);

        if (mTvTitle)
            this.mTvTitle.setVisibility(View.VISIBLE);
        else
            this.mTvTitle.setVisibility(View.INVISIBLE);

    }

    // 左上角按钮监听事件
    protected void clickLeft() {
        mIbLeft.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                KeyboardUtils.hideSoftInput(BaseActivity.this);
                BaseActivity.this.finish();
            }
        });
    }

    // 右侧按钮监听事件
    protected void clickRight() {
        mIbRight.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

            }
        });
    }

    // 设置标题文字 默认显示
    protected void setTitle(String psTitle) {
        mTvTitle.setText(psTitle);
    }

    // 设置标题文字 默认显示
    public void setTitle(int psTitle) {
        mTvTitle.setText(psTitle);
    }

    // 重新设置标题栏样式
    public void setTitleBar(int resource) {
        mVgTitleBar.addView(mInflater.inflate(resource, null));
    }

    // 隐藏titleBar
    protected void hideTitleBar() {
        this.mVgTitleBar.setVisibility(View.GONE);

    }

    // 初始化content
    protected void initContent() {
        this.mVgContent = this.findViewById(R.id.content);
    }

    private android.widget.Toast mToast;

    public void toast(String psTip) {
        ToastUtils.showShort(psTip);
    }

    public void toast(int psTip) {
        ToastUtils.showShort(psTip);
    }

    // 加载动画
    private ZLoadingDialog loadingDialog;

    public void showLoading() {
        /*显示加载动画*/
        if (loadingDialog == null) {
            loadingDialog = new ZLoadingDialog(this);
            loadingDialog.setLoadingBuilder(Z_TYPE.STAR_LOADING)
                    .setLoadingColor(this.getResources().getColor(R.color.color_font_app))
                    .setHintText("Loading...")
                    .setHintTextSize(16)
                    .setHintTextColor(this.getResources().getColor(R.color.color_font_app))
                    .setCanceledOnTouchOutside(false)
                    .setCancelable(true);
        }
        loadingDialog.show();
    }

    public void finishLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

}
