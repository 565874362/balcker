package com.baihua.yaya.my;

import android.support.annotation.Nullable;
import android.text.format.DateFormat;

import com.baihua.common.Constants;
import com.baihua.yaya.R;
import com.baihua.yaya.entity.PatientListEntity;
import com.baihua.yaya.util.CommonUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Author:byd
 * Time:7/12/2018 15:11
 * Description: VisitAdapter
 */
public class VisitAdapter extends BaseQuickAdapter<PatientListEntity.PageBean.RecordsBean, BaseViewHolder> {

    public VisitAdapter(@Nullable List<PatientListEntity.PageBean.RecordsBean> data) {
        super(R.layout.item_visit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientListEntity.PageBean.RecordsBean item) {
        helper.setText(R.id.visit_tv_name, item.getName())
                .setText(R.id.visit_tv_gender, CommonUtils.getGender(item.getGender()))
                .setText(R.id.visit_tv_age, String.format("%s岁", item.getAge()))
                .setText(R.id.visit_tv_description, item.getCharacterDescribe())
                .setText(R.id.visit_tv_date, DateFormat.format("yyyy-MM-dd hh:mm", TimeUtils.string2Date(item.getGmtModified(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))));
        // '问诊状态 1 等待抢单 2 已抢单 3 已回复'
        if (Constants.VISIT_STATUS_REPLIED.equals(item.getStatus())) {
            helper.setText(R.id.visit_tv_status, "已解答")
                    .setBackgroundRes(R.id.visit_tv_status, R.drawable.shape_for_visit_status_answered);
        } else {
            helper.setText(R.id.visit_tv_status, "未解答")
                    .setBackgroundRes(R.id.visit_tv_status, R.drawable.shape_for_visit_status_question);
        }
    }
}
