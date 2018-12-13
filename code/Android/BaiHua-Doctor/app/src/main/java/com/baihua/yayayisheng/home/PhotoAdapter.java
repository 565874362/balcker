package com.baihua.yayayisheng.home;

import android.support.annotation.Nullable;

import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:6/12/2018 10:44
 * Description: 照片展示
 */
public class PhotoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PhotoAdapter(@Nullable List<String> data) {
        super(R.layout.item_photo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Utils.showImg(mContext, item, helper.getView(R.id.item_iv_photo));
    }
}
