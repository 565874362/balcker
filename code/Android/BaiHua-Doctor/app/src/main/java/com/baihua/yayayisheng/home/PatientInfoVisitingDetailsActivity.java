package com.baihua.yayayisheng.home;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.SpaceDecoration;
import com.baihua.yayayisheng.widget.HackyViewPager;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:6/12/2018 10:26
 * Description: 接诊详情
 */
public class PatientInfoVisitingDetailsActivity extends BaseActivity {

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
    RecyclerView mRecyclerView;
    @BindView(R.id.layout_patient_tv_date)
    TextView layoutPatientTvDate;
    @BindView(R.id.patient_details_visiting)
    TextView patientDetailsVisiting;

    private List<String> mList;
    private PhotoAdapter mAdapter;

    @Override
    public void setLayout() {
        setTitle("患者信息");
        setContentView(R.layout.activity_patient_info_visiting_details);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        setContentText();
        initRecycler();
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        mRecyclerView.addItemDecoration(spaceDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new PhotoAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

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
                showPicDialog(view.getContext(), mList, position);
            }
        });
    }

    /**
     * 展示大图
     *
     * @param selectPosition 选中的图片位置
     */
    private void showPicDialog(Context context, List<String> imageSource, int selectPosition) {
        MaterialDialog show = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_show_photo_view, false)
                .build();
        View view = show.getCustomView();
        if (null == view)
            return;
        HackyViewPager hackyViewPager = view.findViewById(R.id.view_pager);
        PhotoViewPagerAdapter mAdapter = new PhotoViewPagerAdapter(imageSource, context);
        hackyViewPager.setAdapter(mAdapter);
        hackyViewPager.setCurrentItem(selectPosition);
        show.show();
    }

    @OnClick({R.id.play_voice_layout, R.id.patient_details_visiting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_voice_layout:
                toast("TODO");
                break;
            case R.id.patient_details_visiting:
                toast("TODO");
                break;
        }
    }
}
