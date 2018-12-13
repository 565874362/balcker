package com.baihua.yayayisheng.home;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.DividerDecoration;
import com.baihua.yayayisheng.decoration.SpaceDecoration;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SpanUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        if (getIntent().hasExtra("status")) {
            String status = (String) getIntent().getSerializableExtra("status");
            switch (status) {
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


                    SpannableStringBuilder spannable = new SpanUtils().append("初步诊断：").setBold().setFontSize(16, true).append("小儿疱疹性口炎，其实就是因为感染后形成的").setFontSize(16, true).create();
                    layoutReplyInitialDiagnosis.setText(spannable);
                    SpannableStringBuilder spannableWarm = new SpanUtils().append("温馨医嘱：").setBold().setFontSize(16, true).append("可以给小孩买点好吃的，多喝开水；可以给小孩买点好吃的，多喝开水").setFontSize(16, true).create();
                    layoutReplyWarmDoctor.setText(spannableWarm);
                    break;
            }
        }
        setContentText();
        initRecycler();
        initCheckRecycler();
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
        mList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            mList.add(i + "");
        }
        mCheckAdapter.setNewData(mList);
    }

    private View getCheckHeaderView() {
        return mInflater.inflate(R.layout.header_need_to_check, layoutPatientRecycler, false);
    }

    private View getCheckFooterView() {
        View footer = mInflater.inflate(R.layout.footer_need_to_check, layoutPatientRecycler, false);
        TextView tvPrice = (TextView) footer.findViewById(R.id.footer_check_tv_price);
        tvPrice.setText(String.format("%s元", "25.00"));
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

        mList = new ArrayList<>();
        mList.add("http://img.hb.aicdn.com/a74dba1791530dcb8dee2a63e539d7920343dd5db9db9-lKWKQn_fw658");
        mList.add("http://img.hb.aicdn.com/89b64a4fe336383edb977c0bb16ef40c5619bba6e3207-4TUP5A_fw658");
        mList.add("http://img.hb.aicdn.com/607f122005dc6ac326a4d4613242d783b09dc0676f2af-VLzfQk_fw658");
        mAdapter.setNewData(mList);
    }

    /**
     * 填充内容
     */
    private void setContentText() {
        // 共有的
        layoutPatientTvName.setText("令狐小影");
        layoutPatientTvGender.setText("男");
        layoutPatientTvAge.setText(String.format("%s岁", "5"));
        layoutPatientTvDescription.setText(String.format("%s", "这几天我家宝宝嘴里起来好多小泡泡，哭的也特别列害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我下问下这中情况要不要输液？"));
        layoutPatientTvStatus.setVisibility(View.GONE);
        layoutPatientTvDate.setText(DateFormat.format("yyyy-MM-dd HH:mm", System.currentTimeMillis()));
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.startActivity(PhotoViewActivity.class);
            }
        });
    }

    @OnClick({R.id.play_voice_layout, R.id.layout_reply_need_to_check, R.id.patient_details_visiting, R.id.patient_details_tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_voice_layout: // 语音播放
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
