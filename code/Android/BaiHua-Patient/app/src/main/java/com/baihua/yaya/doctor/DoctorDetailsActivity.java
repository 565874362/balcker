package com.baihua.yaya.doctor;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.DividerDecoration;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.rcloud.RCUtils;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.SpanUtils;

import java.util.ArrayList;
import java.util.List;

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

    private List<String> mList; // 前三条评论数据
    private CommentAdapter mAdapter; // 评论适配器

    @Override
    public void setLayout() {
        setTitle("医生详情");
        mTvTitle.setTextColor(Color.WHITE);
        mVgTitleBar.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setContentView(R.layout.activity_doctor_details);
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerDecoration dividerDecoration = new DividerDecoration(Color.parseColor("#f3f3f3"), ConvertUtils.dp2px(1), ConvertUtils.dp2px(12), 0);
        dividerDecoration.setDrawHeaderFooter(true);
        mRecyclerView.addItemDecoration(dividerDecoration);
        mAdapter = new CommentAdapter(new ArrayList<>());
        mAdapter.addFooterView(getCommentFooterView());
        mRecyclerView.setAdapter(mAdapter);

        mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mList.add(String.valueOf(i));
        }

        mAdapter.setNewData(mList);
    }

    private View getCommentFooterView() {
        View footer = mInflater.inflate(R.layout.footer_of_comment, mRecyclerView, false);
        TextView moreComment = (TextView) footer.findViewById(R.id.footer_comment_more);
        moreComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(CommentActivity.class);
            }
        });
        return footer;
    }

    private void setContentText() {
        Utils.showImg(this, "http://img.hb.aicdn.com/7ba7b99a9cbdbb3b965e6b24036e89fee5dfc88530ffd-hlULA7_fw658", doctorDetailsImage);
        doctorDetailsTvName.setText("令狐小影");
        doctorDetailsTvJob.setText("江湖百晓生");
        doctorDetailsTvDepartment.setText("剑客");
        doctorDetailsTvHospital.setText("青衣楼");
        SpannableStringBuilder one = new SpanUtils().append("独孤九剑：").setBold().append("归妹趋无妄，无妄趋同人，同人趋大有。甲转丙，丙转庚，庚转癸。子丑之交，辰巳之交，午未之 交。风雷是一变，山泽是一变，水火是一变。乾坤相激，震兑相激，离巽相激。三增而成五，五增而成九···").create();
        SpannableStringBuilder two = new SpanUtils().append("总决式：").setBold().append("有种种变化，用以体演总诀，共有三百六十种变化。").create();
        SpannableStringBuilder three = new SpanUtils().append("破剑式：").setBold().append("破解普天下各门各派的剑法。").create();
        doctorDetailsTvGoodAtOne.setText(one);
        doctorDetailsTvGoodAtTwo.setText(two);
        doctorDetailsTvGoodAtThree.setText(three);
        doctorDetailsTvComment.setText(String.format("评价（%s）", "99"));
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
                ActivityUtils.startActivity(AppointmentActivity.class);
                break;
        }
    }
}
