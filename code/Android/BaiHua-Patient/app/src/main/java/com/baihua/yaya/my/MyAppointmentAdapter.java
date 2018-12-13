package com.baihua.yaya.my;

import android.support.annotation.Nullable;

import com.baihua.yaya.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:7/12/2018 16:49
 * Description: MyAppointmentAdapter
 */
public class MyAppointmentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAppointmentAdapter(@Nullable List<String> data) {
        super(R.layout.item_my_appointment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_my_appointment_tv_doctor_name, String.format("%s（%s）", "令狐小影", "儿科"))
                .setText(R.id.item_my_appointment_tv_doctor_price, String.format("%s元", "20.00"))
                .setText(R.id.item_my_appointment_visiting_tv_time, String.format("%s", "2018-12-08 周五 上午"))
                .setText(R.id.item_my_appointment_visiting_tv_name, "小小")
                .setText(R.id.item_my_appointment_visiting_tv_age, String.format("%s岁", 5));
    }
}
