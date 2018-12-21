package com.baihua.yaya.doctor;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.baihua.yaya.R;
import com.baihua.yaya.entity.MatchDoctorsEntity;
import com.baihua.yaya.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:byd
 * Time:6/12/2018 17:17
 * Description: DoctorListAdapter
 */
public class MatchDoctorListAdapter extends BaseQuickAdapter<MatchDoctorsEntity.MatchDoctorsBean, BaseViewHolder> {

    public MatchDoctorListAdapter(@Nullable List<MatchDoctorsEntity.MatchDoctorsBean> data) {
        super(R.layout.item_doctor_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MatchDoctorsEntity.MatchDoctorsBean item) {
        List<String> good = new ArrayList<>();
        for (int i = 0; i < item.getAdeptEntities().size(); i++) {
            good.add(item.getAdeptEntities().get(i).getName());
        }
        Utils.showCircleImg(mContext, item.getPhoto(), helper.getView(R.id.item_doctor_iv_avatar));
        helper.setText(R.id.item_doctor_tv_name, item.getName())
                .setText(R.id.item_doctor_tv_job, item.getPositionName())
                .setText(R.id.item_doctor_tv_hospital, item.getHosName())
                .setText(R.id.item_doctor_tv_department, item.getOffName())
                .setText(R.id.item_doctor_tv_good, String.format("擅长：%s", TextUtils.join("、", good)));

    }
}
