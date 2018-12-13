package com.baihua.yayayisheng.home;

import android.os.Build;
import android.util.Log;
import android.view.View;

import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.widget.HackyViewPager;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.uiOptions;

import butterknife.BindView;

/**
 * Author:byd
 * Time:6/12/2018 11:21
 * Description: PhotoViewActivity
 */
public class PhotoViewActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    HackyViewPager viewPager;

    @Override
    public void setLayout() {
        hideTitleBar();
        setContentView(R.layout.activity_photo_view);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        // 图片地址合集
        List<String> mList = new ArrayList<>();
        mList.add("http://img.hb.aicdn.com/a74dba1791530dcb8dee2a63e539d7920343dd5db9db9-lKWKQn_fw658");
        mList.add("http://img.hb.aicdn.com/89b64a4fe336383edb977c0bb16ef40c5619bba6e3207-4TUP5A_fw658");
        mList.add("http://img.hb.aicdn.com/607f122005dc6ac326a4d4613242d783b09dc0676f2af-VLzfQk_fw658");
        PhotoViewPagerAdapter mAdapter = new PhotoViewPagerAdapter(mList, this);
        viewPager.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {

    }

    public void fullScreen() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        boolean isImmersiveModeEnabled = isImmersiveModeEnabled();
        if (isImmersiveModeEnabled) {
            Log.i("TEST", "Turning immersive mode mode off. ");
        } else {
            Log.i("TEST", "Turning immersive mode mode on.");
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }

    private boolean isImmersiveModeEnabled() {
        return ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
    }

}
