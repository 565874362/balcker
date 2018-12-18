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
import com.baihua.yaya.doctor.DoctorListFragment;
import com.baihua.yaya.home.HomepageFragment;
import com.baihua.yaya.my.MyFragment;
import com.baihua.yaya.widget.MyPagerAdapter;
import com.blankj.utilcode.util.LogUtils;

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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        connect(mToken);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.toolbar_tv_title);

        setSupportActionBar(mToolbar);

        mTvTitle.setText(R.string.title_homepage);

        if (null != getSupportActionBar()) {
            //隐藏Toolbar的标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
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

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 @init(Context) 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token 从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtils.e("----====onTokenIncorrect====----");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);

                    /**
                     * 设置当前用户信息，
                     *
                     * @param userInfo 当前用户信息
                     */
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo("luckerbai", "令狐小影", Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541485393251&di=0bee903f56c4375f4174fcc2509669b7&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F908fa0ec08fa513dde0e76f8376d55fbb2fbd974.jpg")));
                    /**
                     * 设置消息体内是否携带用户信息。
                     *
                     * @param state 是否携带用户信息，true 携带，false 不携带。
                     */
                    RongIM.getInstance().setMessageAttachedUserInfo(true);

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.e("onError----==== " + errorCode + " ====----onError");
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

}
