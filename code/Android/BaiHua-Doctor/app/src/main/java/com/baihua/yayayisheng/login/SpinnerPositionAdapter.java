package com.baihua.yayayisheng.login;

import android.content.Context;

import com.baihua.common.adapter.CommonAdapter;
import com.baihua.common.adapter.CommonViewHolder;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.DicEntity;
import com.baihua.yayayisheng.entity.HospitalEntity;

import java.util.List;

/**
 * Author:byd
 * Time:19/12/2018 12:07
 * Description: 职位
 */
public class SpinnerPositionAdapter extends CommonAdapter<DicEntity.DictionariesBean> {

    public SpinnerPositionAdapter(Context context, List<DicEntity.DictionariesBean> list) {
        super(context, list, R.layout.item_spinner);
    }

    @Override
    public void setViewContent(CommonViewHolder holder, DicEntity.DictionariesBean bean) {
        holder.setText(R.id.item_spinner_name, bean.getName());
    }
}
