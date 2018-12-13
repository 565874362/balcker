package com.baihua.yaya.doctor;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;

/**
 * Author:byd
 * Time:7/12/2018 10:29
 * Description: 预约就诊
 */
public class AppointmentActivity extends BaseActivity {
    @BindView(R.id.appointment_et_patient_name)
    EditText appointmentEtPatientName;
    @BindView(R.id.appointment_et_patient_age)
    EditText appointmentEtPatientAge;
    @BindView(R.id.appointment_rb_female)
    RadioButton appointmentRbFemale;
    @BindView(R.id.home_rb_male)
    RadioButton homeRbMale;
    @BindView(R.id.appointment_radio_group)
    RadioGroup appointmentRadioGroup;
    @BindView(R.id.appointment_et_patient_mobile)
    EditText appointmentEtPatientMobile;
    @BindView(R.id.appointment_tv_select_time)
    TextView appointmentTvSelectTime;
    @BindView(R.id.appointment_tv_price)
    TextView appointmentTvPrice;
    @BindView(R.id.appointment_tv_submit)
    TextView appointmentTvSubmit;

    @Override
    public void setLayout() {
        setTitle("预约就诊");
        setContentView(R.layout.activity_appointment);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {

    }

    @Override
    public void setListener() {
        appointmentTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(AppointmentConfirmActivity.class);
            }
        });
    }

}
