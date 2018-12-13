package com.baihua.yaya.doctor;

import android.support.annotation.Nullable;

import com.baihua.yaya.R;
import com.baihua.yaya.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:6/12/2018 17:17
 * Description: DoctorListAdapter
 */
public class DoctorListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public DoctorListAdapter(@Nullable List<String> data) {
        super(R.layout.item_doctor_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Utils.showCircleImg(mContext, "http://img.hb.aicdn.com/7c0490fab6cee1d24cb46b372b2fe19b60949a08b383-5X4OeT_sq75sf", helper.getView(R.id.item_doctor_iv_avatar));
        helper.setText(R.id.item_doctor_tv_name, "令狐小影")
                .setText(R.id.item_doctor_tv_job, "主任医生")
                .setText(R.id.item_doctor_tv_hospital, "西安市儿童医院")
                .setText(R.id.item_doctor_tv_department, "儿科")
                .setText(R.id.item_doctor_tv_good, "擅长：咳嗽、小船、呼吸道感染");
    }
}
