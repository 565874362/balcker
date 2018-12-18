package com.baihua.yaya.my;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baihua.common.Constants;
import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.DividerDecoration;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.doctor.AppointmentActivity;
import com.baihua.yaya.entity.PatientListEntity;
import com.baihua.yaya.entity.VisitDetailsEntity;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.util.Utils;
import com.baihua.yaya.view.recorder.MediaManager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Author:byd
 * Time:7/12/2018 15:17
 * Description: 问诊详情
 */
public class MyVisitDetailsActivity extends BaseActivity {
    @BindView(R.id.layout_patient_tv_name)
    TextView layoutPatientTvName;
    @BindView(R.id.layout_patient_tv_gender)
    TextView layoutPatientTvGender;
    @BindView(R.id.layout_patient_tv_age)
    TextView layoutPatientTvAge;
    @BindView(R.id.layout_patient_tv_description)
    TextView layoutPatientTvDescription;
    @BindView(R.id.iv_play_voice_anim)
    ImageView ivPlayVoiceAnim;
    @BindView(R.id.play_voice_layout)
    RelativeLayout playVoiceLayout;
    @BindView(R.id.layout_patient_recycler)
    RecyclerView layoutPatientRecycler;
    @BindView(R.id.layout_patient_tv_date)
    TextView layoutPatientTvDate;
    @BindView(R.id.item_doctor_iv_avatar)
    ImageView itemDoctorIvAvatar;
    @BindView(R.id.item_doctor_tv_name)
    TextView itemDoctorTvName;
    @BindView(R.id.item_doctor_tv_job)
    TextView itemDoctorTvJob;
    @BindView(R.id.item_doctor_tv_hospital)
    TextView itemDoctorTvHospital;
    @BindView(R.id.item_doctor_tv_department)
    TextView itemDoctorTvDepartment;
    @BindView(R.id.item_doctor_tv_good)
    TextView itemDoctorTvGood;
    @BindView(R.id.layout_reply_initial_diagnosis)
    EditText layoutReplyInitialDiagnosis;
    @BindView(R.id.layout_reply_need_to_check)
    LinearLayout layoutReplyNeedToCheck;
    @BindView(R.id.layout_reply_recycler)
    RecyclerView layoutReplyRecycler;
    @BindView(R.id.layout_reply_warm_doctor)
    EditText layoutReplyWarmDoctor;
    @BindView(R.id.layout_reply_time)
    TextView layoutReplyTime;
    @BindView(R.id.layout_reply_advisory)
    TextView layoutReplyAdvisory;
    @BindView(R.id.layout_reply_visit)
    TextView layoutReplyVisit;

    private View mDoctorInfo;
    private View mAnswerContent;

    private List<String> mList;
    private PhotoAdapter mAdapter;
    private NeedToCheckAdapter mCheckAdapter;

    private PatientListEntity.PageBean.RecordsBean mVisitDetails;// 问诊详情

    private int mMinItemWidth; //最小的item宽度
    private int mMaxItemWidth; //最大的item宽度

    private AnimationDrawable mAnimDrawable;
    private File mVoice; // 语音文件

    private VisitDetailsEntity mVisitDetailsEntity;
    private TextView mTvPrice; // 检查总价

    @Override
    public void setLayout() {
        setTitle("问诊详情");
        setContentView(R.layout.activity_visit_details);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        mDoctorInfo = findViewById(R.id.visit_details_doctor);
        mAnswerContent = findViewById(R.id.visit_details_answer_content);
        if (getIntent().hasExtra("visit")) {
            mVisitDetails = (PatientListEntity.PageBean.RecordsBean) getIntent().getSerializableExtra("visit");
            setContentText();
            if (!TextUtils.isEmpty(mVisitDetails.getImage())) // 如果图片不为空则显示图片
                initRecycler();
            if (!TextUtils.isEmpty(mVisitDetails.getVoiceDescribe())) // 如果语音描述不为空
                initVoiceLayout();
            if (Constants.VISIT_STATUS_REPLIED.equals(mVisitDetails.getStatus())) { // 已回复的
                mDoctorInfo.setVisibility(View.VISIBLE);
                mAnswerContent.setVisibility(View.VISIBLE);
                initCheckRecycler();
                getVisitDetails(String.valueOf(mVisitDetails.getId()));
            }
        }

//        getVisitDetails("");
    }

    /**
     * 设置语音信息
     */
    private void initVoiceLayout() {
        playVoiceLayout.setVisibility(View.VISIBLE);
        mMaxItemWidth = (int) (ScreenUtils.getScreenWidth() * 0.7f);
        mMinItemWidth = (int) (ScreenUtils.getScreenWidth() * 0.15f);
        mAnimDrawable = (AnimationDrawable) ivPlayVoiceAnim.getDrawable();

        mVoice = CommonUtils.base64ToFile(mVisitDetails.getVoiceDescribe());
        long voiceLength = MediaManager.getSoundDuration(mVoice.getAbsolutePath());
        if (3000 <= voiceLength) {
            ViewGroup.LayoutParams layoutParams = playVoiceLayout.getLayoutParams();
            layoutParams.width = (int) (mMinItemWidth + (mMaxItemWidth / 60f) * voiceLength / 1000);
            playVoiceLayout.setLayoutParams(layoutParams);
        }
    }

    /**
     * 获取问诊详情
     *
     * @param id 问诊ID
     */
    private void getVisitDetails(String id) {
        RxHttp.getInstance().getSyncServer()
                .getVisitDetails(CommonUtils.getToken(), id)
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<VisitDetailsEntity>(this) {
                    @Override
                    public void onSuccess(VisitDetailsEntity result) {
                        VisitDetailsEntity.DoctorBean doctorBean = result.getDoctor();
                        VisitDetailsEntity.InfoBean infoBean = result.getInfo();
                        initDoctorInfo(result);
                        // TODO: 18/12/2018 检查项数据设置
//                        List<String> checks = Arrays.asList(infoBean.getExaContent())
//                        mCheckAdapter.setNewData();
                        mTvPrice.setText(String.format("%s元", infoBean.getExaFee()));
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    /**
     * 设置医生信息
     */
    private void initDoctorInfo(VisitDetailsEntity visitDetailsEntity) {
        VisitDetailsEntity.DoctorBean doctorBean = visitDetailsEntity.getDoctor();
        VisitDetailsEntity.InfoBean infoBean = visitDetailsEntity.getInfo();
        Utils.showUserHead(this, itemDoctorIvAvatar, doctorBean.getPhoto());
        itemDoctorTvName.setText(doctorBean.getName());
        itemDoctorTvJob.setText(doctorBean.getPositionName());
        itemDoctorTvHospital.setText(doctorBean.getHosName());
        itemDoctorTvDepartment.setText(doctorBean.getOffName());

        SpannableStringBuilder spannable = new SpanUtils().append("初步诊断：").setBold().setFontSize(16, true).append(infoBean.getResponse()).setFontSize(16, true).create();
        layoutReplyInitialDiagnosis.setText(spannable);
        SpannableStringBuilder spannableWarm = new SpanUtils().append("温馨医嘱：").setBold().setFontSize(16, true).append(infoBean.getAdvice()).setFontSize(16, true).create();
        layoutReplyWarmDoctor.setText(spannableWarm);

        layoutReplyAdvisory.setText("咨询");
        layoutReplyVisit.setText(String.format("就诊（%s）", "20.00"));

    }

    /**
     * 检查项
     */
    private void initCheckRecycler() {
        layoutReplyRecycler.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration dividerDecoration =
                new DividerDecoration(ContextCompat.getColor(this, R.color.windowBackground),
                        ConvertUtils.dp2px(1), ConvertUtils.dp2px(12), ConvertUtils.dp2px(12));
        dividerDecoration.setDrawHeaderFooter(true);
        layoutReplyRecycler.addItemDecoration(dividerDecoration);
        layoutReplyRecycler.setItemAnimator(new DefaultItemAnimator());
        mCheckAdapter = new NeedToCheckAdapter(new ArrayList<>());
        mCheckAdapter.addHeaderView(getCheckHeaderView());
        mCheckAdapter.addFooterView(getCheckFooterView());
        layoutReplyRecycler.setAdapter(mCheckAdapter);
    }

    private View getCheckHeaderView() {
        return mInflater.inflate(R.layout.header_need_to_check, layoutPatientRecycler, false);
    }

    private View getCheckFooterView() {
        View footer = mInflater.inflate(R.layout.footer_need_to_check, layoutPatientRecycler, false);
        mTvPrice = (TextView) footer.findViewById(R.id.footer_check_tv_price);
        mTvPrice.setText(String.format("%s元", "00.00"));
        return footer;
    }

    private void initRecycler() {
        layoutPatientRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        layoutPatientRecycler.addItemDecoration(spaceDecoration);
        layoutPatientRecycler.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new PhotoAdapter(new ArrayList<>());
        layoutPatientRecycler.setAdapter(mAdapter);

        List<String> images = Arrays.asList(mVisitDetails.getImage().split(","));
        mAdapter.setNewData(images);
    }

    private void setContentText() {
        layoutPatientTvName.setText(mVisitDetails.getName());
        layoutPatientTvGender.setText(CommonUtils.getGender(mVisitDetails.getGender()));
        layoutPatientTvAge.setText(String.format("%s岁", mVisitDetails.getAge()));
        layoutPatientTvDescription.setText(String.format("%s", mVisitDetails.getCharacterDescribe()));
        layoutPatientTvDate.setText(DateFormat.format("yyyy-MM-dd hh:mm", TimeUtils.string2Date(mVisitDetails.getGmtCreate(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))));
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ActivityUtils.startActivity(PhotoViewActivity.class);
                // 图片地址合集
                List<String> mList = new ArrayList<>();
                mList.add("http://img.hb.aicdn.com/a74dba1791530dcb8dee2a63e539d7920343dd5db9db9-lKWKQn_fw658");
                mList.add("http://img.hb.aicdn.com/89b64a4fe336383edb977c0bb16ef40c5619bba6e3207-4TUP5A_fw658");
                mList.add("http://img.hb.aicdn.com/607f122005dc6ac326a4d4613242d783b09dc0676f2af-VLzfQk_fw658");
                CommonUtils.showPicDialog(view.getContext(), mList, position);
            }
        });

        layoutReplyAdvisory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("咨询");
            }
        });

        layoutReplyVisit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(AppointmentActivity.class);
            }
        });

        playVoiceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mAnimDrawable) {
                    if (mAnimDrawable.isRunning()) {
                        mAnimDrawable.selectDrawable(0);
                        mAnimDrawable.stop();
                    } else {
                        mAnimDrawable.start();
                        MediaManager.playSound(mVoice.getAbsolutePath(), new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                MediaManager.release();
                                mAnimDrawable.selectDrawable(0);
                                mAnimDrawable.stop();
                            }
                        });
                    }
                }
            }
        });
    }

}
