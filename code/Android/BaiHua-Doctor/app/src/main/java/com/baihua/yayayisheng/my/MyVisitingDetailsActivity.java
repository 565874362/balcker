package com.baihua.yayayisheng.my;

import android.text.format.DateFormat;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.DoctorRegistrationListEntity;
import com.baihua.yayayisheng.util.CommonUtils;
import com.blankj.utilcode.util.TimeUtils;

import butterknife.BindView;

/**
 * Author:byd
 * Time:6/12/2018 15:11
 * Description: 我的接诊详情
 */
public class MyVisitingDetailsActivity extends BaseActivity {
    @BindView(R.id.visiting_tv_name)
    TextView visitingTvName;
    @BindView(R.id.visiting_tv_age)
    TextView visitingTvAge;
    @BindView(R.id.visiting_tv_gender)
    TextView visitingTvGender;
    @BindView(R.id.visiting_tv_mobile)
    TextView visitingTvMobile;
    @BindView(R.id.visiting_tv_time)
    TextView visitingTvTime;
    @BindView(R.id.visiting_tv_pay_method)
    TextView visitingTvPayMethod;
    @BindView(R.id.visiting_tv_pay_total)
    TextView visitingTvPayTotal;
    @BindView(R.id.visiting_tv_pay_time)
    TextView visitingTvPayTime;

    private DoctorRegistrationListEntity.PageBean.RecordsBean recordsBean;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_my_visiting_details);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        if (getIntent().hasExtra("registration")) {
            recordsBean = (DoctorRegistrationListEntity.PageBean.RecordsBean) getIntent().getSerializableExtra("registration");
            setContentText();
        }
    }

    private void setContentText() {
        visitingTvName.setText(recordsBean.getName());
        visitingTvAge.setText(String.format("%s岁", recordsBean.getAge()));
        visitingTvGender.setText(CommonUtils.getGender(recordsBean.getGender()));
        visitingTvMobile.setText(recordsBean.getPhone());
        visitingTvTime.setText(recordsBean.getVisitTime());
        // TODO: 22/12/2018 欠缺支付信息 
        visitingTvPayMethod.setText("支付宝支付");
        visitingTvPayTotal.setText(String.format("%s元", "100.00"));
        visitingTvPayTime.setText(DateFormat.format("yyyy-MM-dd", System.currentTimeMillis()));
    }

    @Override
    public void setListener() {

    }

}
