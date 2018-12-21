package com.baihua.yayayisheng.home;

import android.support.annotation.Nullable;

import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.ExaminationEntity;
import com.baihua.yayayisheng.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:6/12/2018 14:16
 * Description: NeedToCheckAdapter
 */
public class NeedToCheckAdapter extends BaseQuickAdapter<ExaminationEntity.ListBean, BaseViewHolder> {

    public NeedToCheckAdapter(@Nullable List<ExaminationEntity.ListBean> data) {
        super(R.layout.item_need_to_check, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ExaminationEntity.ListBean item) {
        helper.setText(R.id.item_check_tv_name, item.getName())
                .setText(R.id.item_check_tv_price, String.format("%s元", Utils.keep2DecimalDigits(item.getPrice())));
    }
}
