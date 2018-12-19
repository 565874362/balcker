package com.baihua.yayayisheng.home;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
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

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.DividerDecoration;
import com.baihua.yayayisheng.decoration.SpaceDecoration;
import com.baihua.yayayisheng.entity.PatientListEntity;
import com.baihua.yayayisheng.entity.VisitDetailsEntity;
import com.baihua.yayayisheng.util.CommonUtils;
import com.baihua.yayayisheng.view.recorder.MediaManager;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
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
import butterknife.OnClick;

/**
 * Author:byd
 * Time:6/12/2018 9:05
 * Description: 患者信息详情（三种状态：待回复，接诊，已解决，判断显示）
 */
public class PatientInfoDetailsActivity extends BaseActivity {


    @BindView(R.id.layout_patient_tv_name)
    TextView layoutPatientTvName;
    @BindView(R.id.layout_patient_tv_gender)
    TextView layoutPatientTvGender;
    @BindView(R.id.layout_patient_tv_age)
    TextView layoutPatientTvAge;
    @BindView(R.id.layout_patient_tv_status)
    TextView layoutPatientTvStatus;
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
    @BindView(R.id.layout_reply_initial_diagnosis)
    EditText layoutReplyInitialDiagnosis;
    @BindView(R.id.layout_reply_need_to_check)
    LinearLayout layoutReplyNeedToCheck;
    @BindView(R.id.layout_reply_right_arrow)
    ImageView layoutReplyRightArrow;
    @BindView(R.id.layout_reply_recycler)
    RecyclerView layoutReplyRecycler;
    @BindView(R.id.layout_reply_warm_doctor)
    EditText layoutReplyWarmDoctor;
    @BindView(R.id.patient_details_visiting)
    TextView patientDetailsVisiting;
    @BindView(R.id.patient_details_tv_submit)
    TextView patientDetailsTvSubmit;
    private View mAnswerOrNeedAnswerLayout;

    private List<String> mList;
    private PhotoAdapter mAdapter;
    private NeedToCheckAdapter mCheckAdapter;

    private PatientListEntity.PageBean.RecordsBean mVisitDetails;// 详情

    private int mMinItemWidth; //最小的item宽度
    private int mMaxItemWidth; //最大的item宽度

    private AnimationDrawable mAnimDrawable;
    private File mVoice; // 语音文件

    private VisitDetailsEntity mVisitDetailsEntity;
    private TextView mTvPrice; // 检查总价

    @Override
    public void setLayout() {
        setTitle("患者信息");
        setContentView(R.layout.activity_patient_info_details);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        mAnswerOrNeedAnswerLayout = findViewById(R.id.answer_or_need_answer_layout);
        if (getIntent().hasExtra("visit")) {
            mVisitDetails = (PatientListEntity.PageBean.RecordsBean) getIntent().getSerializableExtra("visit");
            setContentText();
            if (!TextUtils.isEmpty(mVisitDetails.getImage())) // 如果图片不为空则显示图片
                initRecycler();
            if (!TextUtils.isEmpty(mVisitDetails.getVoiceDescribe())) // 如果语音描述不为空
                initVoiceLayout();
            switch (mVisitDetails.getStatus()) {
                case "1": // 接诊

                    break;
                case "2": // 待回复
                    pendingReply();
                    break;
                case "3": // 已解决
                    // 已解决
                    layoutReplyNeedToCheck.setEnabled(false);

                    layoutReplyInitialDiagnosis.setFocusable(false);
                    layoutReplyInitialDiagnosis.setFocusableInTouchMode(false);
                    layoutReplyWarmDoctor.setFocusable(false);
                    layoutReplyWarmDoctor.setFocusableInTouchMode(false);
                    mAnswerOrNeedAnswerLayout.setVisibility(View.VISIBLE);
                    patientDetailsVisiting.setVisibility(View.GONE);

                    initCheckRecycler();
                    getVisitDetails(String.valueOf(mVisitDetails.getId()));

//                    SpannableStringBuilder spannable = new SpanUtils().append("初步诊断：").setBold().setFontSize(16, true).append("小儿疱疹性口炎，其实就是因为感染后形成的").setFontSize(16, true).create();
//                    layoutReplyInitialDiagnosis.setText(spannable);
//                    SpannableStringBuilder spannableWarm = new SpanUtils().append("温馨医嘱：").setBold().setFontSize(16, true).append("可以给小孩买点好吃的，多喝开水；可以给小孩买点好吃的，多喝开水").setFontSize(16, true).create();
//                    layoutReplyWarmDoctor.setText(spannableWarm);
                    break;
            }
        }

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
        VisitDetailsEntity.InfoBean infoBean = visitDetailsEntity.getInfo();

        SpannableStringBuilder spannable = new SpanUtils().append("初步诊断：").setBold().setFontSize(16, true).append(infoBean.getResponse()).setFontSize(16, true).create();
        layoutReplyInitialDiagnosis.setText(spannable);
        SpannableStringBuilder spannableWarm = new SpanUtils().append("温馨医嘱：").setBold().setFontSize(16, true).append(infoBean.getAdvice()).setFontSize(16, true).create();
        layoutReplyWarmDoctor.setText(spannableWarm);

//        layoutReplyAdvisory.setText("咨询");
//        layoutReplyVisit.setText(String.format("就诊（%s）", "20.00"));

    }

    /**
     * 选择的检查项目列表 （待回复）
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

    /**
     * 咨询上传的图片列表 （共有的）
     */
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

    /**
     * 填充内容
     */
    private void setContentText() {
        // 共有的
        layoutPatientTvName.setText(mVisitDetails.getName());
        layoutPatientTvGender.setText(CommonUtils.getGender(mVisitDetails.getGender()));
        layoutPatientTvAge.setText(String.format("%s岁", mVisitDetails.getAge()));
        layoutPatientTvDescription.setText(String.format("%s", mVisitDetails.getCharacterDescribe()));
        layoutPatientTvStatus.setVisibility(View.GONE);
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
    }

    @OnClick({R.id.play_voice_layout, R.id.layout_reply_need_to_check, R.id.patient_details_visiting, R.id.patient_details_tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_voice_layout: // 语音播放
                playVoice();
                break;
            case R.id.layout_reply_need_to_check: // 选择检查项 （待回复）
                showCheckedDialog();
                break;
            case R.id.patient_details_visiting: // 接诊（待接诊）(调用接诊接口，成功后 展示待回复页面）
                pendingReply();
                break;
            case R.id.patient_details_tv_submit: // 提交 （待回复）
                break;
        }
    }

    /**
     * 播放语音
     */
    private void playVoice() {
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

    private void pendingReply() {
        mAnswerOrNeedAnswerLayout.setVisibility(View.VISIBLE);
        patientDetailsTvSubmit.setVisibility(View.VISIBLE);
        layoutReplyRightArrow.setVisibility(View.VISIBLE);
        patientDetailsVisiting.setVisibility(View.GONE);
        layoutReplyNeedToCheck.setEnabled(true);
    }

    /**
     * 展示检查项
     */
    MaterialDialog checkboxDialog = null;

    private void showCheckedDialog() {

        List<String> items = new ArrayList<String>(Arrays.asList("口腔检查 25.00元", "血常规检查 30.00元",
                "病毒检查 40.00元", "微量元素检查 35.50元", "心电图检查 105.00元"));

        checkboxDialog = new MaterialDialog.Builder(this)
                .title("需做检查")
                .content("共计0.00元")
                .items(items)
                .positiveText("确定")
                .negativeText("取消")
                .alwaysCallMultiChoiceCallback()
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {

                    @Override
                    public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                        if (0 >= text.length) {
                            checkboxDialog.setContent(String.format("共计%s元", "0.00"));
                            return true;
                        }
                        double total = 0;
                        for (CharSequence charSequence :
                                text) {
                            String ends = charSequence.toString().split(" ")[1];
                            String price = ends.replaceAll("元", "");
                            total += Double.valueOf(price);
                        }
                        checkboxDialog.setContent(String.format("共计%s元", total));
                        return true;
                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        if (null == dialog.getSelectedIndices())
                            return;
                        LogUtils.e(dialog.getSelectedIndices().length);
                    }
                })
                .show();
    }

}
