package com.baihua.yaya.doctor;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.entity.DiagnoseEntity;
import com.baihua.yaya.entity.RegisteredEntity;
import com.baihua.yaya.entity.form.RegisteredForm;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.luoshihai.xxdialog.DialogViewHolder;
import com.luoshihai.xxdialog.XXDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.appointment_rb_male)
    RadioButton appointmentRbMale;
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

    private String mDoctorId;
    private String mFee;
    private List<DiagnoseEntity.DiagnoseListBean> mDiagnoseList; // 出诊时间

    private TextView mTvAMStatus; // 上午有无就诊
    private TextView mTvAMRegistered; // 上午有无就诊
    private TextView mTvPMStatus; // 下午有无就诊
    private TextView mTvPMRegistered; // 下午有无就诊

    private int mSelectedPosition = 0; // 选中的位置
    private int mGender = 0; // 女
    private String mTimePart = ""; // 时间区间 0 上午 1 下午 ,

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
        if (getIntent().hasExtra("data")) {
            Map<String, String> data = new HashMap<>();
            data = (Map<String, String>) getIntent().getSerializableExtra("data");
            mDoctorId = data.get("id");
            mFee = data.get("fee");
            appointmentTvPrice.setText(Utils.keep2DecimalDigits(mFee));
            getDiagnoseList();
        }
    }

    /**
     * 获取出诊时间
     */
    private void getDiagnoseList() {
        RxHttp.getInstance().getSyncServer()
                .getDiagnoseList(CommonUtils.getToken(), mDoctorId)
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<DiagnoseEntity>(this) {
                    @Override
                    public void onSuccess(DiagnoseEntity result) {
                        mDiagnoseList = result.getDiagnoseList();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    @Override
    public void setListener() {
        appointmentTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registered(v.getContext());
                ActivityUtils.startActivity(AppointmentConfirmActivity.class);
            }
        });

        appointmentTvSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimeSelectDialog();
            }
        });

        appointmentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.appointment_rb_female:
                        mGender = 0;
                        break;
                    case R.id.appointment_rb_male:
                        mGender = 1;
                        break;
                }
            }
        });
    }

    /**
     * 挂号
     */
    private void registered(Context context) {

        String age = appointmentEtPatientAge.getText().toString().trim();
        String name = appointmentEtPatientName.getText().toString().trim();
        String phone = appointmentEtPatientMobile.getText().toString().trim();
        String scheduleId = String.valueOf(mDiagnoseList.get(mSelectedPosition).getId());
        String registeredTime = DateFormat.format("yyyy-MM-dd", System.currentTimeMillis()).toString();

        RxHttp.getInstance().getSyncServer()
                .registered(CommonUtils.getToken(), new RegisteredForm(age, mDoctorId, String.valueOf(mGender), name, phone, scheduleId, mTimePart, registeredTime))
                .compose(RxSchedulers.observableIO2Main(context))
                .subscribe(new ProgressObserver<RegisteredEntity>(context) {
                    @Override
                    public void onSuccess(RegisteredEntity result) {
                        LogUtils.e(result.getRegistrationId());

                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    XXDialog timeSelectDialog;

    private void showTimeSelectDialog() {
        timeSelectDialog = new XXDialog(this, R.layout.dialog_select_appoint_time) {
            @Override
            public void convert(DialogViewHolder holder) {
                mTvAMStatus = holder.getView(R.id.dialog_tv_m_status);
                mTvAMRegistered = holder.getView(R.id.dialog_tv_m_registered);
                mTvPMStatus = holder.getView(R.id.dialog_tv_a_status);
                mTvPMRegistered = holder.getView(R.id.dialog_tv_a_registered);

                RecyclerView recyclerView = holder.getView(R.id.dialog_recycler_view);
                recyclerView.setLayoutManager(new LinearLayoutManager(AppointmentActivity.this, LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(16));
                spaceDecoration.setPaddingStart(false);
                spaceDecoration.setPaddingEdgeSide(false);
                recyclerView.addItemDecoration(spaceDecoration);
                AppointmentTimeAdapter timeAdapter = new AppointmentTimeAdapter(new ArrayList<>());
                recyclerView.setAdapter(timeAdapter);
                timeAdapter.setmPosition(mSelectedPosition); // 默认选中项
                timeAdapter.setNewData(mDiagnoseList);

                holder.setOnClick(R.id.dialog_iv_close, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });

                if (Utils.isListEmpty(mDiagnoseList)) {
                    LogUtils.e("出诊时间没有拿到···");
                    return;
                }
                DiagnoseEntity.DiagnoseListBean firstItem = mDiagnoseList.get(0);

                setSelectStatus(firstItem);

                timeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        mSelectedPosition = position;
                        timeAdapter.setmPosition(mSelectedPosition);
                        timeAdapter.notifyDataSetChanged();
                        DiagnoseEntity.DiagnoseListBean firstItem = mDiagnoseList.get(mSelectedPosition);
                        setSelectStatus(firstItem);
                    }
                });

                holder.setOnClick(R.id.dialog_tv_m_registered, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTimePart = "0";
                        appointmentTvSelectTime.setText(String.format("%s上午", mDiagnoseList.get(mSelectedPosition).getFullDate()));
                        dismiss();
                    }
                });

                holder.setOnClick(R.id.dialog_tv_a_registered, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTimePart = "1";
                        appointmentTvSelectTime.setText(String.format("%s下午", mDiagnoseList.get(mSelectedPosition).getFullDate()));
                        dismiss();
                    }
                });

            }


            private void setSelectStatus(DiagnoseEntity.DiagnoseListBean firstItem) {
                if (0 < firstItem.getMorningRemainNum()) { // 上午有预诊
                    mTvAMStatus.setText("有");
                    mTvAMStatus.setTextColor(ContextCompat.getColor(AppointmentActivity.this, R.color.colorPrimary));
                    mTvAMRegistered.setVisibility(View.VISIBLE);
                } else {
                    mTvAMStatus.setText("无");
                    mTvAMStatus.setTextColor(Color.RED);
                    mTvAMRegistered.setVisibility(View.GONE);
                }

                if (0 < firstItem.getAfternoonRemainNum()) { // 下午有预诊
                    mTvPMStatus.setText("有");
                    mTvPMStatus.setTextColor(ContextCompat.getColor(AppointmentActivity.this, R.color.colorPrimary));
                    mTvPMRegistered.setVisibility(View.VISIBLE);
                } else {
                    mTvPMStatus.setText("无");
                    mTvPMStatus.setTextColor(Color.RED);
                    mTvPMRegistered.setVisibility(View.GONE);
                }
            }
        };
        timeSelectDialog.fromBottom().backgroundLight(0.2).fullWidth().showDialog();
    }

}
