package com.baihua.yayayisheng.home;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.baihua.yayayisheng.util.Utils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

/**
 * Author:byd
 * Time:6/12/2018 11:23
 * Description: PhotoViewPagerAdapter
 */
public class PhotoViewPagerAdapter extends PagerAdapter {

    private List<String> sDrawables;
    private Context mContext;

    public PhotoViewPagerAdapter(List<String> sDrawables, Context context) {
        this.sDrawables = sDrawables;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sDrawables.size();
    }

    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
//        photoView.setImageResource(sDrawables.get(position));
        Utils.showImg(mContext, sDrawables.get(position), photoView);
        // Now just add PhotoView to ViewPager and return it
        container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return photoView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
