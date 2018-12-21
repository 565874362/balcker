package com.baihua.yayayisheng.my;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.DoctorInfoEntity;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SpanUtils;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Author:byd
 * Time:6/12/2018 15:43
 * Description: 我的信息
 */
public class MyInformationActivity extends BaseActivity {
    @BindView(R.id.my_info_image)
    ImageView myInfoImage;
    @BindView(R.id.my_info_tv_name)
    TextView myInfoTvName;
    @BindView(R.id.my_info_tv_job)
    TextView myInfoTvJob;
    @BindView(R.id.my_info_tv_department)
    TextView myInfoTvDepartment;
    @BindView(R.id.my_info_tv_hospital)
    TextView myInfoTvHospital;
    @BindView(R.id.my_info_tv_good_at_one)
    TextView myInfoTvGoodAtOne;
    @BindView(R.id.my_info_tv_good_at_two)
    TextView myInfoTvGoodAtTwo;
    @BindView(R.id.my_info_tv_good_at_three)
    TextView myInfoTvGoodAtThree;
    @BindView(R.id.my_info_iv_doctor_certificate)
    ImageView myInfoIvDoctorCertificate;
    @BindView(R.id.my_info_iv_front_photo)
    ImageView myInfoIvFrontPhoto;
    @BindView(R.id.my_info_iv_reverse_photo)
    ImageView myInfoIvReversePhoto;

    private DoctorInfoEntity.InfoBean mDoctorInfo;

    @Override
    public void setLayout() {
        setTitle("我的信息");
        mTvRight.setText("编辑");
        mTvRight.setVisibility(View.VISIBLE);
        setContentView(R.layout.activity_my_information);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        if (getIntent().hasExtra("doctorInfo")) {
            mDoctorInfo = (DoctorInfoEntity.InfoBean) getIntent().getSerializableExtra("doctorInfo");
        }
        setContentText();
    }

    private void setContentText() {
        Utils.showImg(this, mDoctorInfo.getPhoto(), myInfoImage);
        myInfoTvName.setText(mDoctorInfo.getName());
        myInfoTvJob.setText(mDoctorInfo.getPositionName());
        myInfoTvDepartment.setText(mDoctorInfo.getOffName());
        myInfoTvHospital.setText(mDoctorInfo.getHosName());
        List<DoctorInfoEntity.InfoBean.AdeptEntitiesBean> adeptEntities = mDoctorInfo.getAdeptEntities();
        for (int i = 0; i < adeptEntities.size(); i++) { // 擅长
            DoctorInfoEntity.InfoBean.AdeptEntitiesBean adeptEntitiesBean = adeptEntities.get(i);
            if (i == 0) {
                SpannableStringBuilder one = new SpanUtils().append(String.format("%s：", adeptEntitiesBean.getName())).setBold().append(adeptEntitiesBean.getDescribe()).create();
                myInfoTvGoodAtOne.setText(one);
            } else if (i == 1) {
                SpannableStringBuilder two = new SpanUtils().append(String.format("%s：", adeptEntitiesBean.getName())).setBold().append(adeptEntitiesBean.getDescribe()).create();
                myInfoTvGoodAtTwo.setText(two);
            } else if (i == 2) {
                SpannableStringBuilder three = new SpanUtils().append(String.format("%s：", adeptEntitiesBean.getName())).setBold().append(adeptEntitiesBean.getDescribe()).create();
                myInfoTvGoodAtThree.setText(three);
            }
        }
        Utils.showImg(this, mDoctorInfo.getPhysicianLicence(), myInfoIvDoctorCertificate);
        List<String> idCards = Arrays.asList(mDoctorInfo.getIdentityCard().split(","));
        Utils.showImg(this, idCards.get(0), myInfoIvFrontPhoto);
        Utils.showImg(this, idCards.get(1), myInfoIvReversePhoto);
    }

    @Override
    public void setListener() {
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.gotoActivity(MyInformationActivity.this, MyInfoEditActivity.class, false, "doctor", mDoctorInfo);
            }
        });
    }

}
