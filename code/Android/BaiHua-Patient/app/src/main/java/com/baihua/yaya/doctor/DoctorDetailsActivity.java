package com.baihua.yaya.doctor;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.DividerDecoration;
import com.baihua.yaya.entity.CommentEntity;
import com.baihua.yaya.entity.form.CommentForm;
import com.baihua.yaya.entity.DoctorInfoEntity;
import com.baihua.yaya.rcloud.RCUtils;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:6/12/2018 17:37
 * Description: DoctorDetailsActivity
 */
public class DoctorDetailsActivity extends BaseActivity {

    @BindView(R.id.doctor_details_image)
    ImageView doctorDetailsImage;
    @BindView(R.id.doctor_details_tv_name)
    TextView doctorDetailsTvName;
    @BindView(R.id.doctor_details_tv_job)
    TextView doctorDetailsTvJob;
    @BindView(R.id.doctor_details_tv_department)
    TextView doctorDetailsTvDepartment;
    @BindView(R.id.doctor_details_tv_hospital)
    TextView doctorDetailsTvHospital;
    @BindView(R.id.doctor_details_tv_good_at_one)
    TextView doctorDetailsTvGoodAtOne;
    @BindView(R.id.doctor_details_tv_good_at_two)
    TextView doctorDetailsTvGoodAtTwo;
    @BindView(R.id.doctor_details_tv_good_at_three)
    TextView doctorDetailsTvGoodAtThree;
    @BindView(R.id.doctor_details_tv_advisory)
    TextView doctorDetailsTvAdvisory;
    @BindView(R.id.doctor_details_tv_visiting)
    TextView doctorDetailsTvVisiting;
    @BindView(R.id.doctor_details_comment)
    TextView doctorDetailsTvComment;
    @BindView(R.id.doctor_details_recycler)
    RecyclerView mRecyclerView;

    private CommentAdapter mAdapter; // 评论适配器

    private String mDoctorId;
    private String mRegisteredFee; // 就诊费用

    @Override
    public void setLayout() {
        setTitle("医生详情");
        setContentView(R.layout.activity_doctor_details);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        if (getIntent().hasExtra("doctorId")) {
            mDoctorId = (String) getIntent().getSerializableExtra("doctorId");
            if (TextUtils.isEmpty(mDoctorId))
                return;
            getDoctorInfo(mDoctorId);
            getDoctorComment(mDoctorId);
        }
//        setContentText();
        initRecycler();
    }

    private void getDoctorComment(String doctorId) {
        RxHttp.getInstance().getSyncServer()
                .getDoctorCommentList(new CommentForm(1, String.valueOf(doctorId), 3))
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<CommentEntity>(this) {
                    @Override
                    public void onSuccess(CommentEntity result) {
                        if (!Utils.isListEmpty(result.getPage().getRecords())) {
                            doctorDetailsTvComment.setText(String.format("评价（%s）", String.valueOf(result.getPage().getTotal())));
                            mAdapter.setNewData(result.getPage().getRecords());
                            mAdapter.addFooterView(getCommentFooterView());
                        } else {
                            doctorDetailsTvComment.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    private void getDoctorInfo(String doctorId) {
        RxHttp.getInstance().getSyncServer()
                .getDoctorInfo(doctorId)
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<DoctorInfoEntity>(this) {
                    @Override
                    public void onSuccess(DoctorInfoEntity result) {
                        setContentText(result);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerDecoration dividerDecoration = new DividerDecoration(Color.parseColor("#f3f3f3"), ConvertUtils.dp2px(1), ConvertUtils.dp2px(12), 0);
        dividerDecoration.setDrawHeaderFooter(true);
        mRecyclerView.addItemDecoration(dividerDecoration);
        mAdapter = new CommentAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }

    private View getCommentFooterView() {
        View footer = mInflater.inflate(R.layout.footer_of_comment, mRecyclerView, false);
        TextView moreComment = (TextView) footer.findViewById(R.id.footer_comment_more);
        moreComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.gotoActivity(DoctorDetailsActivity.this, CommentActivity.class, false, "doctorId", mDoctorId);
            }
        });
        return footer;
    }

    /**
     * 设置数据
     *
     * @param doctorInfoEntity 数据源
     */
    private void setContentText(DoctorInfoEntity doctorInfoEntity) {
        DoctorInfoEntity.InfoBean doctor = doctorInfoEntity.getInfo();
        mRegisteredFee = doctor.getRegistrationFee();
        Utils.showImg(this, doctor.getPhoto(), doctorDetailsImage);
        doctorDetailsTvName.setText(doctor.getName());
        doctorDetailsTvJob.setText(doctor.getPositionName());
        doctorDetailsTvDepartment.setText(doctor.getOffName());
        doctorDetailsTvHospital.setText(doctor.getHosName());
        doctorDetailsTvVisiting.setText(String.format("就诊（%s）元", Utils.keep2DecimalDigits(doctor.getRegistrationFee()))); // 就诊（20.00元）
        List<DoctorInfoEntity.InfoBean.AdeptEntitiesBean> adeptEntities = doctor.getAdeptEntities();
        for (int i = 0; i < adeptEntities.size(); i++) {
            DoctorInfoEntity.InfoBean.AdeptEntitiesBean adeptEntitiesBean = adeptEntities.get(i);
            if (i == 0) {
                SpannableStringBuilder one = new SpanUtils().append(String.format("%s：", adeptEntitiesBean.getName())).setBold().append(adeptEntitiesBean.getDescribe()).create();
                doctorDetailsTvGoodAtOne.setText(one);
            } else if (i == 1) {
                SpannableStringBuilder two = new SpanUtils().append(String.format("%s：", adeptEntitiesBean.getName())).setBold().append(adeptEntitiesBean.getDescribe()).create();
                doctorDetailsTvGoodAtTwo.setText(two);
            } else if (i == 2) {
                SpannableStringBuilder three = new SpanUtils().append(String.format("%s：", adeptEntitiesBean.getName())).setBold().append(adeptEntitiesBean.getDescribe()).create();
                doctorDetailsTvGoodAtThree.setText(three);
            }
        }
    }

    @Override
    public void setListener() {

    }


    @OnClick({R.id.doctor_details_tv_advisory, R.id.doctor_details_tv_visiting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.doctor_details_tv_advisory:
                RCUtils.startConversation(this, "targetId", "title");
                break;
            case R.id.doctor_details_tv_visiting:
                Map<String, String> data = new HashMap<>();
                data.put("id", mDoctorId);
                data.put("fee", mRegisteredFee);
                Utils.gotoActivity(DoctorDetailsActivity.this, AppointmentActivity.class, false, "data", data);
                break;
        }
    }
}
