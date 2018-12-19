package com.baihua.yayayisheng.login;

import android.content.Context;

import com.baihua.common.adapter.CommonAdapter;
import com.baihua.common.adapter.CommonViewHolder;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.HospitalEntity;
import com.baihua.yayayisheng.entity.OfficeEntity;

import java.util.List;

/**
 * Author:byd
 * Time:19/12/2018 12:07
 * Description: 单位
 */
public class SpinnerHospitalAdapter extends CommonAdapter<HospitalEntity.ListBean> {

    public SpinnerHospitalAdapter(Context context, List<HospitalEntity.ListBean> list) {
        super(context, list, R.layout.item_spinner);
    }

    @Override
    public void setViewContent(CommonViewHolder holder, HospitalEntity.ListBean bean) {
        holder.setText(R.id.item_spinner_name, bean.getName());
    }
}
