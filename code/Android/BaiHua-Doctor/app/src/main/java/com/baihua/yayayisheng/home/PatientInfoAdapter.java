package com.baihua.yayayisheng.home;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.baihua.yayayisheng.R;

import java.util.Date;
import java.util.List;

/**
 * Author:byd
 * Time:4/12/2018 11:38
 * Description: 患者信息ITEM
 */
public class PatientInfoAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PatientInfoAdapter(@Nullable List<String> data) {
        super(R.layout.item_patient_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        helper.setText(R.id.patient_tv_name, "唐伯虎")
                .setText(R.id.patient_tv_gender, "男")
                .setText(R.id.patient_tv_age, "98")
                .setText(R.id.patient_tv_description, "头痛（headache） 是临床常见的症状，通常将局限于头颅上半部，包括眉弓、耳轮上缘和枕外隆突连线以上部位的疼痛统称头痛。" +
                        "头痛病因繁多，神经痛、颅内感染、颅内占位病变、脑血管疾病、颅外头面部疾病、以及全身疾病如急性感染、中毒等均可导致头痛。发病年龄常见于青年、中年和老年。")
                .setText(R.id.patient_tv_date, DateFormat.format("yyyy-MM-dd hh:mm", new Date()));

        TextView tvStatus = helper.getView(R.id.patient_tv_status);
        if (helper.getAdapterPosition() % 2 == 0 && helper.getAdapterPosition() != 10) {
            tvStatus.setText("待回复");
            tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.color_red_light_f8f8f9));
            tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_red_circle_dot, 0, 0, 0);
        } else if (helper.getAdapterPosition() % 2 == 1) {
            tvStatus.setText("接诊");
            tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.color_red_light_f8f8f9));
            tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_red_circle_dot, 0, 0, 0);
        } else if (helper.getAdapterPosition() == 10) {
            tvStatus.setText("已解答");
            tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_blue_circle_dot, 0, 0, 0);
        }
    }
}
