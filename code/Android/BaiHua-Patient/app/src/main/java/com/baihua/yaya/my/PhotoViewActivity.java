package com.baihua.yaya.my;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.widget.HackyViewPager;
import com.baihua.yaya.widget.PhotoViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

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
        CommonUtils.transparentStatusBar(this);
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
        viewPager.setCurrentItem(1);
    }

    @Override
    public void setListener() {

    }

}
