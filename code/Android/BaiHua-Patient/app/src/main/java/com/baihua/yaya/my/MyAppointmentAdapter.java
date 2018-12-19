package com.baihua.yaya.my;

import android.support.annotation.Nullable;

import com.baihua.yaya.R;
import com.baihua.yaya.entity.RegisteredListEntity;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:7/12/2018 16:49
 * Description: MyAppointmentAdapter
 */
public class MyAppointmentAdapter extends BaseQuickAdapter<RegisteredListEntity.PageBean.RecordsBean, BaseViewHolder> {

    public MyAppointmentAdapter(@Nullable List<RegisteredListEntity.PageBean.RecordsBean> data) {
        super(R.layout.item_my_appointment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RegisteredListEntity.PageBean.RecordsBean item) {
        RegisteredListEntity.PageBean.RecordsBean.DoctorBean doctorBean = item.getDoctor();
        helper.setText(R.id.item_my_appointment_tv_doctor_name, String.format("%s（%s）", doctorBean.getName(), doctorBean.getOffName()))
                .setText(R.id.item_my_appointment_tv_doctor_price, String.format("%s元", Utils.keep2DecimalDigits(doctorBean.getRegistrationFee())))
                .setText(R.id.item_my_appointment_visiting_tv_time, String.format("%s %s", item.getVisitTime(), CommonUtils.getTimePartString(item.getTimePart()))) // 时间区间   0 上午 1 下午
                .setText(R.id.item_my_appointment_visiting_tv_name, item.getName())
                .setText(R.id.item_my_appointment_visiting_tv_age, String.format("%s岁", item.getAge()));
    }
}
