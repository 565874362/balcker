package com.baihua.yayayisheng.my;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:byd
 * Time:5/12/2018 9:52
 * Description: 我的出诊时间表
 */
public class MyVisitingScheduleActivity extends BaseActivity {

    @BindView(R.id.visiting_schedule_recycler)
    RecyclerView mRecyclerView;
    private MyVisitingScheduleAdapter mAdapter;
    private List<String> mList;

    @Override
    public void setLayout() {
        setTitle("接诊时间表");
        mIbRight.setBackgroundResource(R.drawable.schedule_white);
        mIbRight.setVisibility(View.VISIBLE);
        setContentView(R.layout.activity_my_visiting_schedule);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {

        initRecycler();
        initData();

    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            mList.add("i = " + i);
        }
        mAdapter.setNewData(mList);
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingEdgeSide(false);
        mRecyclerView.addItemDecoration(spaceDecoration);
        mAdapter = new MyVisitingScheduleAdapter(new ArrayList<>());
        mAdapter.addHeaderView(getHeaderView());
        mRecyclerView.setAdapter(mAdapter);
    }

    private View getHeaderView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.header_for_visiting_schedule, null);
        TextView tvContent = headerView.findViewById(R.id.header_tv_content);
        tvContent.setText("接诊时间");
        return headerView;
    }


    @Override
    public void setListener() {
        mIbRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(MyVisitingScheduleSelectActivity.class);
            }
        });
    }

}
