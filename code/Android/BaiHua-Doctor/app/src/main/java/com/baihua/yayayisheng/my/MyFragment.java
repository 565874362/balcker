package com.baihua.yayayisheng.my;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.baihua.common.base.BaseFragment;
import com.baihua.yayayisheng.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:4/12/2018 14:39
 * Description: 我的
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.my_iv_avatar)
    ImageView myIvAvatar;
    @BindView(R.id.my_tv_doctor_name)
    TextView myTvDoctorName;
    @BindView(R.id.my_tv_doctor_job)
    TextView myTvDoctorJob;
    @BindView(R.id.my_tv_doctor_level)
    TextView myTvDoctorLevel;
    @BindView(R.id.my_ll_patient_information)
    LinearLayout myLlPatientInformation;
    @BindView(R.id.my_ll_my_visiting)
    LinearLayout myLlMyTreatment;
    @BindView(R.id.my_ll_visiting_time)
    LinearLayout myLlTreatmentTime;
    @BindView(R.id.my_ll_my_information)
    LinearLayout myLlMyInformation;
    @BindView(R.id.my_tv_exit_account)
    TextView myTvExitAccount;

    @Override
    public int setRootView() {
        return R.layout.fragment_my;
    }

    @Override
    public void setLayout() {

    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        initContent();
    }

    private void initContent() {
        Utils.showCircleImg(getActivity(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=593762100,3871765340&fm=26&gp=0.jpg", myIvAvatar);
        myTvDoctorName.setText("令狐小影");
        myTvDoctorJob.setText(String.format("%s | %s", "主任医生", "儿科"));
        myTvDoctorLevel.setText(String.format("%s", "等级"));
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.my_iv_avatar, R.id.my_ll_patient_info, R.id.my_ll_my_visiting, R.id.my_ll_visiting_time, R.id.my_ll_my_information, R.id.my_tv_exit_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_iv_avatar:
                break;
            case R.id.my_ll_patient_info:
                ActivityUtils.startActivity(MyPatientInfoActivity.class);
                break;
            case R.id.my_ll_my_visiting:
                ActivityUtils.startActivity(MyVisitingActivity.class);
                break;
            case R.id.my_ll_visiting_time:
                ActivityUtils.startActivity(MyVisitingScheduleActivity.class);
                break;
            case R.id.my_ll_my_information:
                ActivityUtils.startActivity(MyInformationActivity.class);
                break;
            case R.id.my_tv_exit_account:
                toast("TODO");
                break;
        }
    }
}
