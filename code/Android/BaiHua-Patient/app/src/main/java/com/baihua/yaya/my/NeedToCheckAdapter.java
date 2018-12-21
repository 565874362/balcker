package com.baihua.yaya.my;

import android.support.annotation.Nullable;

import com.baihua.yaya.R;
import com.baihua.yaya.entity.HealthExaminationsEntity;
import com.baihua.yaya.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:6/12/2018 14:16
 * Description: NeedToCheckAdapter
 */
public class NeedToCheckAdapter extends BaseQuickAdapter<HealthExaminationsEntity, BaseViewHolder> {

    public NeedToCheckAdapter(@Nullable List<HealthExaminationsEntity> data) {
        super(R.layout.item_need_to_check, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthExaminationsEntity item) {
        helper.setText(R.id.item_check_tv_name, item.getName())
                .setText(R.id.item_check_tv_price, String.format("%s元", Utils.keep2DecimalDigits(item.getPrice())));
    }
}
