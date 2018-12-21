package com.baihua.yayayisheng.home;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.baihua.common.Constants;
import com.baihua.yayayisheng.entity.PatientListEntity;
import com.baihua.yayayisheng.util.CommonUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.baihua.yayayisheng.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Author:byd
 * Time:4/12/2018 11:38
 * Description: 患者信息ITEM
 */
public class PatientInfoAdapter extends BaseQuickAdapter<PatientListEntity.PageBean.RecordsBean, BaseViewHolder> {

    public PatientInfoAdapter(@Nullable List<PatientListEntity.PageBean.RecordsBean> data) {
        super(R.layout.item_patient_info, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PatientListEntity.PageBean.RecordsBean item) {

        helper.setText(R.id.patient_tv_name, item.getName())
                .setText(R.id.patient_tv_gender, CommonUtils.getGender(item.getGender()))
                .setText(R.id.patient_tv_age, String.format("%s岁", item.getAge()))
                .setText(R.id.patient_tv_description, item.getCharacterDescribe())
                .setText(R.id.patient_tv_date, DateFormat.format("yyyy-MM-dd hh:mm", TimeUtils.string2Date(item.getGmtModified(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))));

        TextView tvStatus = helper.getView(R.id.patient_tv_status);
        // '问诊状态 1 等待抢单 2 已抢单 3 已回复'
        if (Constants.VISIT_STATUS_CHECKED.equals(item.getStatus())) {
            tvStatus.setText("待回复");
            tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.color_red_light_f8f8f9));
            tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_red_circle_dot, 0, 0, 0);
        } else if (Constants.VISIT_STATUS_WAIT.equals(item.getStatus())) {
            tvStatus.setText("接诊");
            tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.color_red_light_f8f8f9));
            tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_red_circle_dot, 0, 0, 0);
        } else if (Constants.VISIT_STATUS_REPLIED.equals(item.getStatus())) {
            tvStatus.setText("已解答");
            tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
            tvStatus.setCompoundDrawablesWithIntrinsicBounds(R.drawable.shape_blue_circle_dot, 0, 0, 0);
        }
    }
}
