package com.baihua.yayayisheng.my;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.baihua.yayayisheng.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

/**
 * Author:byd
 * Time:4/12/2018 11:38
 * Description: 患者信息ITEM
 */
public class MyVisitingAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyVisitingAdapter(@Nullable List<String> data) {
        super(R.layout.item_patient_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setGone(R.id.patient_tv_status, false);

        helper.setText(R.id.patient_tv_name, "唐伯虎")
                .setText(R.id.patient_tv_gender, "男")
                .setText(R.id.patient_tv_age, "98")
                .setText(R.id.patient_tv_description, "头痛（headache） 是临床常见的症状，通常将局限于头颅上半部，包括眉弓、耳轮上缘和枕外隆突连线以上部位的疼痛统称头痛。" +
                        "头痛病因繁多，神经痛、颅内感染、颅内占位病变、脑血管疾病、颅外头面部疾病、以及全身疾病如急性感染、中毒等均可导致头痛。发病年龄常见于青年、中年和老年。")
                .setText(R.id.patient_tv_date, DateFormat.format("yyyy-MM-dd hh:mm", new Date()));

    }
}
