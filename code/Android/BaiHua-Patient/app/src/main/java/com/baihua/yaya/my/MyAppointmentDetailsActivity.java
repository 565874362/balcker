package com.baihua.yaya.my;

import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.entity.EmptyEntity;
import com.baihua.yaya.entity.RegisteredListEntity;
import com.baihua.yaya.entity.form.PatientListForm;
import com.baihua.yaya.entity.form.PublishCommentForm;
import com.baihua.yaya.util.CommonUtils;
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
    @BindView(R.id.visit_et_publish_comment)
    EditText visitEtPublishComment;
    @BindView(R.id.visit_tv_publish)
    TextView visitTvPublish;


    private RegisteredListEntity.PageBean.RecordsBean mRecordBean;

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
        if (getIntent().hasExtra("appointment")) { // 来自列表
            mRecordBean = (RegisteredListEntity.PageBean.RecordsBean) getIntent().getSerializableExtra("appointment");
            setContentData();
        }
    }

    private void setContentData() {
        // 医生信息
        RegisteredListEntity.PageBean.RecordsBean.DoctorBean doctorBean = mRecordBean.getDoctor();
        Utils.showUserHead(this, layoutVisitIvAvatar, doctorBean.getPhoto());
        layoutVisitTvDoctor.setText(doctorBean.getName());
        layoutVisitTvDepartment.setText(doctorBean.getOffName());
        layoutVisitTvPrice.setText(String.format("%s元", Utils.keep2DecimalDigits(doctorBean.getRegistrationFee())));

        // 患者信息
        visitingTvName.setText(mRecordBean.getName());
        visitingTvAge.setText(String.format("%s岁", mRecordBean.getAge()));
        visitingTvGender.setText(CommonUtils.getGender(mRecordBean.getGender()));
        visitingTvMobile.setText(mRecordBean.getPhone());
        visitingTvTime.setText(String.format("%s %s", mRecordBean.getVisitTime(), CommonUtils.getTimePartString(mRecordBean.getTimePart())));

        // 支付信息 // TODO: 19/12/2018
        visitingTvPayMethod.setText("微信支付");
        visitingTvPayTime.setText(DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()));
    }

    @Override
    public void setListener() {

        visitTvPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publishComment();
            }
        });

    }

    /**
     * 发布评论
     */
    private void publishComment() {
        String content = visitEtPublishComment.getText().toString();
        if (TextUtils.isEmpty(content)) {
            toast("请输入评论内容");
            return;
        }
        // TODO: 19/12/2018 挂号编号
        RxHttp.getInstance().getSyncServer()
                .publishComment(CommonUtils.getToken(), new PublishCommentForm(content, mRecordBean.getDoctorId(), String.valueOf(mRecordBean.getPatientId())))
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<EmptyEntity>(this, true, "正在发布，请稍后···") {
                    @Override
                    public void onSuccess(EmptyEntity result) {
                        toast("发布成功");
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

}
