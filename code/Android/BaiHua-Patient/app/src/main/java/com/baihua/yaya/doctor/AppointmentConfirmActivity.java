package com.baihua.yaya.doctor;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baihua.common.Constants;
import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.entity.RegistrationDetailsEntity;
import com.baihua.yaya.entity.VisitDetailsEntity;
import com.baihua.yaya.util.CommonUtils;

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
    @BindView(R.id.iv_wechat)
    ImageView ivWechat;
    @BindView(R.id.iv_alipay)
    ImageView ivAlipay;

    private boolean isWechat; // 是否微信支付

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

        if (getIntent().hasExtra("registrationId")) {
            String registrationId = (String) getIntent().getSerializableExtra("registrationId");
            getRegisteredDetails(registrationId);
        }

    }

    /**
     * 获取问诊详情
     *
     * @param id 问诊ID
     */
    private void getRegisteredDetails(String id) {
        RxHttp.getInstance().getSyncServer()
                .getRegisteredDetails(CommonUtils.getToken(), id)
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<RegistrationDetailsEntity>(this) {
                    @Override
                    public void onSuccess(RegistrationDetailsEntity result) {
                        setContentData(result.getInfo());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }

    private void setContentData(RegistrationDetailsEntity.InfoBean infoBean) {
        RegistrationDetailsEntity.InfoBean.DoctorBean doctorBean = infoBean.getDoctor();
        confirmTvHospital.setText(doctorBean.getHosName());
        visitingTvDepartment.setText(doctorBean.getOffName());
        visitingTvDoctor.setText(doctorBean.getName());
        visitingTvPrice.setText(String.format("%s元", doctorBean.getRegistrationFee()));
        visitingTvName.setText(infoBean.getName());
        visitingTvAge.setText(infoBean.getAge());
        visitingTvGender.setText(CommonUtils.getGender(infoBean.getGender()));
        visitingTvMobile.setText(infoBean.getPhone());
        visitingTvTime.setText(infoBean.getVisitTime());
    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.visiting_ll_wechat, R.id.visiting_ll_alipay, R.id.tv_submit_registration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.visiting_ll_wechat: // 微信支付
                isWechat = true;
                setSelectedBg();
                break;
            case R.id.visiting_ll_alipay: // 支付宝支付
                isWechat = false;
                setSelectedBg();
                break;
            case R.id.tv_submit_registration: // 提交预约
                break;
        }
    }

    private void setSelectedBg() {
        if (isWechat) {
            ivWechat.setImageResource(R.drawable.radio_fille);
            ivAlipay.setImageResource(R.drawable.radio);
        } else {
            ivWechat.setImageResource(R.drawable.radio);
            ivAlipay.setImageResource(R.drawable.radio_fille);
        }
    }
}
