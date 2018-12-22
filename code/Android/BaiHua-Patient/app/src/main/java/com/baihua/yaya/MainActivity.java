package com.baihua.yaya;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.doctor.DoctorListFragment;
import com.baihua.yaya.entity.AccountEntity;
import com.baihua.yaya.entity.RongCloudToken;
import com.baihua.yaya.home.HomepageFragment;
import com.baihua.yaya.my.MyFragment;
import com.baihua.yaya.rcloud.RCUtils;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.widget.MyPagerAdapter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

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
                    mViewPager.setCurrentItem(0, false);
                    return true;
                case R.id.navigation_dashboard:
                    mTvTitle.setText(R.string.title_doctor);
                    mViewPager.setCurrentItem(1, false);
                    return true;
                case R.id.navigation_notifications:
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

        if (com.baihua.yaya.util.Utils.isLogin(this)) {
            getChatToken();
        }

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
                navigation.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
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
