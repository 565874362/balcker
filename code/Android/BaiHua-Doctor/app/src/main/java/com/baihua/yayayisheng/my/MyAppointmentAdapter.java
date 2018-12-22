package com.baihua.yayayisheng.my;

import android.support.annotation.Nullable;

import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.DoctorRegistrationListEntity;
import com.baihua.yayayisheng.util.CommonUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:7/12/2018 16:49
 * Description: MyAppointmentAdapter
 */
public class MyAppointmentAdapter extends BaseQuickAdapter<DoctorRegistrationListEntity.PageBean.RecordsBean, BaseViewHolder> {

    public MyAppointmentAdapter(@Nullable List<DoctorRegistrationListEntity.PageBean.RecordsBean> data) {
        super(R.layout.item_my_appointment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorRegistrationListEntity.PageBean.RecordsBean item) {
        helper.setText(R.id.item_my_appointment_visiting_tv_time, String.format("%s %s", item.getVisitTime(), CommonUtils.getTimePartString(item.getTimePart()))) // 时间区间   0 上午 1 下午
                .setText(R.id.item_my_appointment_visiting_tv_name, item.getName())
                .setText(R.id.item_my_appointment_visiting_tv_age, String.format("%s岁", item.getAge()));
    }
}
