package com.baihua.yayayisheng.home;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
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
 * Description: 患者信息详情
 */
public class PatientInfoAnsweredDetailsActivity extends BaseActivity {

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
    TextView layoutReplyInitialDiagnosis;
    @BindView(R.id.layout_reply_need_to_check)
    LinearLayout layoutReplyNeedToCheck;
    @BindView(R.id.layout_reply_recycler)
    RecyclerView layoutReplyRecycler;
    @BindView(R.id.layout_reply_warm_doctor)
    TextView layoutReplyWarmDoctor;

    private List<String> mList;
    private PhotoAdapter mAdapter;
    private NeedToCheckAdapter mCheckAdapter;

    @Override
    public void setLayout() {
        setTitle("患者信息");
        setContentView(R.layout.activity_patient_info_answered_details);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        setContentText();
        initRecycler();
        initCheckRecycler();
    }

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
        TextView tvPrice = (TextView) footer.findViewById(R.id.footer_check_tv_price);
        tvPrice.setText(String.format("%s元", "25.00"));
        return footer;
    }

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

    private void setContentText() {
        layoutPatientTvName.setText("令狐小影");
        layoutPatientTvGender.setText("男");
        layoutPatientTvAge.setText(String.format("%s岁", "5"));
        layoutPatientTvDescription.setText(String.format("%s", "这几天我家宝宝嘴里起来好多小泡泡，哭的也特别列害也不敢吃东西，后来去诊所，医生说这是小儿疱疹性口炎，我下问下这中情况要不要输液？"));
        layoutPatientTvStatus.setVisibility(View.GONE);
        layoutPatientTvDate.setText(DateFormat.format("yyyy-MM-dd HH:mm", System.currentTimeMillis()));
        SpannableStringBuilder spannable = new SpanUtils().append("初步诊断：").setBold().setFontSize(16, true).append("小儿疱疹性口炎，其实就是因为感染后形成的").setFontSize(16, true).create();
        layoutReplyInitialDiagnosis.setText(spannable);
        SpannableStringBuilder spannableWarm = new SpanUtils().append("温馨医嘱：").setBold().setFontSize(16, true).append("可以给小孩买点好吃的，多喝开水；可以给小孩买点好吃的，多喝开水").setFontSize(16, true).create();
        layoutReplyWarmDoctor.setText(spannableWarm);
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

    @OnClick({R.id.play_voice_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_voice_layout:
                toast("TODO");
                break;
        }
    }

}
