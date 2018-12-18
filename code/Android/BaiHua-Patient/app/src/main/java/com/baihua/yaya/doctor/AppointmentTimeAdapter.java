package com.baihua.yaya.doctor;

import android.support.annotation.Nullable;

import com.baihua.yaya.R;
import com.baihua.yaya.entity.DiagnoseEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:18/12/2018 16:28
 * Description: AppointmentTimeAdapter
 */
public class AppointmentTimeAdapter extends BaseQuickAdapter<DiagnoseEntity.DiagnoseListBean, BaseViewHolder> {

    private int mPosition;

    public AppointmentTimeAdapter(@Nullable List<DiagnoseEntity.DiagnoseListBean> data) {
        super(R.layout.item_appointment_time, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiagnoseEntity.DiagnoseListBean item) {
        helper.setText(R.id.item_appointment_tv_time, item.getDate());
        if (helper.getAdapterPosition() == mPosition) {
            helper.itemView.setSelected(true);
        } else {
            helper.itemView.setSelected(false);
        }
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
