package com.baihua.yaya.doctor;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:7/12/2018 14:02
 * Description: 预约确认
 */
public class AppointmentConfirmActivity extends BaseActivity {
    @BindView(R.id.confirm_tv_hospital)
    TextView confirmTvHospital;
    @BindView(R.id.visiting_tv_department)
    TextView visitingTvDepartment;
    @BindView(R.id.visiting_tv_doctor)
    TextView visitingTvDoctor;
    @BindView(R.id.visiting_tv_price)
    TextView visitingTvPrice;
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
    @BindView(R.id.visiting_ll_wechat)
    LinearLayout visitingLlWechat;
    @BindView(R.id.visiting_ll_alipay)
    LinearLayout visitingLlAlipay;

    @Override
    public void setLayout() {
        setTitle("就诊确认");
        setContentView(R.layout.activity_appointment_confirm);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        setContentData();
    }

    private void setContentData() {

    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.visiting_ll_wechat, R.id.visiting_ll_alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.visiting_ll_wechat:
                break;
            case R.id.visiting_ll_alipay:
                break;
        }
    }
}
