package com.baihua.yaya;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.doctor.DoctorListFragment;
import com.baihua.yaya.entity.AccountEntity;
import com.baihua.yaya.entity.RongCloudToken;
import com.baihua.yaya.helper.BottomNavigationViewHelper;
import com.baihua.yaya.home.HomepageFragment;
import com.baihua.yaya.my.MyFragment;
import com.baihua.yaya.rcloud.RCUtils;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.widget.MyPagerAdapter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private View mCommonToolbar;

    private TextView mTvTitle;
    //    private final String mToken = "m6NyhEntPhr+8SLY4HKyOCWFNPp3rcEzhezI19eSKNO3gowaBR4T+GbdIi8hNJvFOTl1j0TMcuRIh/n5qpk2FzWkCtUUZ9Yz";
    private final String mToken = "AqCvJEZPW1ExNlBCH9po8ooO90bGELzks3KYn7rZSAOHpTHzGTJM7fhq3e4Bg0VN7WnnKzVIE1m0KyIWSFBPyWb2IY9643Nz";

    private BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.navigation_notifications && !com.baihua.yaya.util.Utils.isLogin(MainActivity.this)) {
                com.baihua.yaya.util.Utils.goLogin(MainActivity.this);
                return false; // false 表示点击事件被取消
            }
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTvTitle.setText(R.string.title_homepage);
                    mCommonToolbar.setVisibility(View.VISIBLE);
                    mViewPager.setCurrentItem(0, false);
                    return true;
                case R.id.navigation_dashboard:
                    mCommonToolbar.setVisibility(View.VISIBLE);
                    mTvTitle.setText(R.string.title_doctor);
                    mViewPager.setCurrentItem(1, false);
                    return true;
                case R.id.navigation_notifications:
                    mCommonToolbar.setVisibility(View.GONE);
                    mTvTitle.setText(R.string.title_mine);
                    mViewPager.setCurrentItem(2, false);
                    return true;
            }
            return false;
        }
    };


    @Override
    public void setLayout() {
        hideTitleBar();
        setContentView(R.layout.activity_main);
    }


    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {

        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);

        mCommonToolbar = findViewById(R.id.common_toolbar);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.toolbar_tv_title);

        setSupportActionBar(mToolbar);

        mTvTitle.setText(R.string.title_homepage);

        if (null != getSupportActionBar()) {
            //隐藏Toolbar的标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }


        navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mViewPager = findViewById(R.id.view_pager);

        Fragment conversationList = initConversationList();

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomepageFragment());
        fragmentList.add(new DoctorListFragment());
        fragmentList.add(new MyFragment());
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragmentList));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 2) { // 因为个人页面图片延伸到状态栏 所以自定义了
                    mCommonToolbar.setVisibility(View.GONE);
                } else {
                    mCommonToolbar.setVisibility(View.VISIBLE);
                }
                navigation.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        if (com.baihua.yaya.util.Utils.isLogin(this)) {
            getChatToken();
            String id = SPUtils.getInstance("account").getString("id", "");
            RCUtils.refreshUserInfo(new UserInfo(id, "患者", Uri.parse(""))); // 主要是更新患者的名字
            setUserInfo(this);
        }

        checkAppVersion();

    }

    /**
     * app 版本檢測
     */
    private void checkAppVersion() {
        // TODO: 25/12/2018  MarketUtils.launchAppDetail(BuildConfig.APPLICATION_ID, "");
    }

    UserInfo userInfo;

    private void setUserInfo(Context context) {
        /**
         * 设置用户信息的提供者，供 RongIM 调用获取用户名称和头像信息。
         *
         * @param userInfoProvider 用户信息提供者。
         * @param isCacheUserInfo  设置是否由 IMKit 来缓存用户信息。<br>
         *                         如果 App 提供的 UserInfoProvider
         *                         每次都需要通过网络请求用户数据，而不是将用户数据缓存到本地内存，会影响用户信息的加载速度；<br>
         *                         此时最好将本参数设置为 true，由 IMKit 将用户信息缓存到本地内存中。
         * @see UserInfoProvider
         */
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {

                if (TextUtils.isEmpty(userId))
                    return userInfo;

                RxHttp.getInstance().getSyncServer()
                        .getAccount(CommonUtils.getToken(), userId)
                        .compose(RxSchedulers.observableIO2Main(context))
                        .subscribe(new ProgressObserver<AccountEntity>(context) {
                            @Override
                            public void onSuccess(AccountEntity result) {
                                userInfo = new UserInfo(userId, result.getInfo().getName(), Uri.parse(result.getInfo().getPhoto()));
                            }

                            @Override
                            public void onFailure(Throwable e, String errorMsg) {
                                LogUtils.e(errorMsg);
                            }
                        });
                return userInfo;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
    }

    /**
     * 获取融云TOKEN
     */
    private void getChatToken() {
        RxHttp.getInstance().getSyncServer()
                .getToken(CommonUtils.getToken())
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<RongCloudToken>(this) {
                    @Override
                    public void onSuccess(RongCloudToken result) {
                        RCUtils.connect(MainActivity.this, result.getToken());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    public void setCurrentPage(int page) {
        mTvTitle.setText(R.string.title_homepage);
        navigation.getMenu().getItem(page).setChecked(true);
        mViewPager.setCurrentItem(page);
    }

    @Override
    public void setListener() {

    }

    private boolean mCanExit;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!mCanExit) {
                toast("再按一次退出" + getString(R.string.app_name));
                mCanExit = true;
                new Handler().postDelayed(() -> mCanExit = false, 2000);
                return true;
            }
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Fragment initConversationList() {
        ConversationListFragment fragment = new ConversationListFragment();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话，该会话聚合显示
//                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//设置群组会话，该会话非聚合显示
                .build();
        fragment.setUri(uri);  //设置 ConverssationListFragment 的显示属性

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.rong_content, fragment);
//        transaction.commit();
        return fragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
