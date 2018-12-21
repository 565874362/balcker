package com.baihua.yayayisheng.my;

import android.support.annotation.Nullable;

import com.baihua.yayayisheng.entity.DiagnoseDateListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.baihua.yayayisheng.R;

import java.util.List;

/**
 * Author:byd
 * Time:5/12/2018 10:17
 * Description: 接诊时间表适配器
 */
public class MyVisitingScheduleAdapter extends BaseQuickAdapter<DiagnoseDateListEntity.DiagnoseListBean, BaseViewHolder> {

    public MyVisitingScheduleAdapter(@Nullable List<DiagnoseDateListEntity.DiagnoseListBean> data) {
        super(R.layout.item_visiting_schedule, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DiagnoseDateListEntity.DiagnoseListBean item) {
        helper.setText(R.id.visiting_schedule_tv_week, item.getDate())
                .setText(R.id.visiting_schedule_tv_morning_or_afternoon, item.getTimepart())
                .setText(R.id.visiting_schedule_tv_time, String.format("%s-%s", item.getBeginTime(), item.getEndTime()))
                .setText(R.id.visiting_schedule_tv_number, String.format("接诊人数：%s人", item.getTotalNumber()));
    }
}
