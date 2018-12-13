package com.baihua.yaya.my;

import android.support.annotation.Nullable;
import android.text.format.DateFormat;

import com.baihua.yaya.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

/**
 * Author:byd
 * Time:7/12/2018 15:11
 * Description: VisitAdapter
 */
public class VisitAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public VisitAdapter(@Nullable List<String> data) {
        super(R.layout.item_visit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.visit_tv_name, "唐伯虎")
                .setText(R.id.visit_tv_gender, "男")
                .setText(R.id.visit_tv_age, "98")
                .setText(R.id.visit_tv_description, "头痛（headache） 是临床常见的症状，通常将局限于头颅上半部，包括眉弓、耳轮上缘和枕外隆突连线以上部位的疼痛统称头痛。" +
                        "头痛病因繁多，神经痛、颅内感染、颅内占位病变、脑血管疾病、颅外头面部疾病、以及全身疾病如急性感染、中毒等均可导致头痛。发病年龄常见于青年、中年和老年。")
                .setText(R.id.visit_tv_date, DateFormat.format("yyyy-MM-dd hh:mm", new Date()));
        if (helper.getAdapterPosition() % 2 == 0) {
            helper.setText(R.id.visit_tv_status, "已解答")
                    .setBackgroundRes(R.id.visit_tv_status, R.drawable.shape_for_visit_status_answered);
        } else {
            helper.setText(R.id.visit_tv_status, "未解答")
                    .setBackgroundRes(R.id.visit_tv_status, R.drawable.shape_for_visit_status_question);
        }
    }
}
