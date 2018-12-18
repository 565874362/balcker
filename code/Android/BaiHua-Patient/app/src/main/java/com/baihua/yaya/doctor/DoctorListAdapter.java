package com.baihua.yaya.doctor;

import android.support.annotation.Nullable;

import com.baihua.yaya.R;
import com.baihua.yaya.entity.DoctorEntity;
import com.baihua.yaya.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:6/12/2018 17:17
 * Description: DoctorListAdapter
 */
public class DoctorListAdapter extends BaseQuickAdapter<DoctorEntity.PageBean.RecordsBean, BaseViewHolder> {

    public DoctorListAdapter(@Nullable List<DoctorEntity.PageBean.RecordsBean> data) {
        super(R.layout.item_doctor_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorEntity.PageBean.RecordsBean item) {
        Utils.showCircleImg(mContext, item.getPhoto(), helper.getView(R.id.item_doctor_iv_avatar));
        helper.setText(R.id.item_doctor_tv_name, item.getName())
                .setText(R.id.item_doctor_tv_job, item.getPositionName())
                .setText(R.id.item_doctor_tv_hospital, item.getHosName())
                .setText(R.id.item_doctor_tv_department, item.getOffName())
                .setText(R.id.item_doctor_tv_good, String.format("擅长：%s", item.getMajor()));
    }
}
