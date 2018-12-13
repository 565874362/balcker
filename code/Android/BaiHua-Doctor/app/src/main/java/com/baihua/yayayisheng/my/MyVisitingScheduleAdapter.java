package com.baihua.yayayisheng.my;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.baihua.yayayisheng.R;

import java.util.List;

/**
 * Author:byd
 * Time:5/12/2018 10:17
 * Description: 接诊时间表适配器
 */
public class MyVisitingScheduleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyVisitingScheduleAdapter(@Nullable List<String> data) {
        super(R.layout.item_visiting_schedule, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.visiting_schedule_tv_week, "周一")
                .setText(R.id.visiting_schedule_tv_morning_or_afternoon, "上午")
                .setText(R.id.visiting_schedule_tv_time, "08:30-11:30")
                .setText(R.id.visiting_schedule_tv_number, String.format("接诊人数：%s人", 80));
    }
}
