package com.baihua.yaya.my;

import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.util.Utils;

import butterknife.BindView;

/**
 * Author:byd
 * Time:7/12/2018 17:14
 * Description: MyAppointmentDetailsActivity
 */
public class MyAppointmentDetailsActivity extends BaseActivity {

    @BindView(R.id.layout_visit_iv_avatar)
    ImageView layoutVisitIvAvatar;
    @BindView(R.id.layout_visit_tv_department)
    TextView layoutVisitTvDepartment;
    @BindView(R.id.layout_visit_tv_doctor)
    TextView layoutVisitTvDoctor;
    @BindView(R.id.layout_visit_tv_price)
    TextView layoutVisitTvPrice;
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
    @BindView(R.id.visiting_tv_pay_time)
    TextView visitingTvPayTime;

    @Override
    public void setLayout() {
        setTitle("就诊详情");
        setContentView(R.layout.activity_my_appointment_details);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        setContentData();
    }

    private void setContentData() {
        // 医生信息
        Utils.showCircleImg(this, "http://img.hb.aicdn.com/7c0490fab6cee1d24cb46b372b2fe19b60949a08b383-5X4OeT_sq75sf", layoutVisitIvAvatar);
        layoutVisitTvDoctor.setText("令狐小影");
        layoutVisitTvDepartment.setText("儿科");
        layoutVisitTvPrice.setText(String.format("%s元", "20.00"));

        // 患者信息
        visitingTvName.setText("小明");
        visitingTvAge.setText(String.format("%s岁", "5"));
        visitingTvGender.setText("男");
        visitingTvMobile.setText("13434345454");
        visitingTvTime.setText("2018-12-10 周一 上午");

        // 支付信息
        visitingTvPayMethod.setText("微信支付");
        visitingTvPayTime.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()));
    }

    @Override
    public void setListener() {

    }

}
