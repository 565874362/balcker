package com.baihua.yaya.my;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:byd
 * Time:7/12/2018 16:47
 * Description: 我的预约
 */
public class MyAppointmentActivity extends BaseActivity {
    @BindView(R.id.my_appointment_recycler)
    RecyclerView mRecyclerView;

    private List<String> mList;
    private MyAppointmentAdapter mAdapter;

    @Override
    public void setLayout() {
        setTitle("就诊记录");
        setContentView(R.layout.activity_my_appointment);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        initRecycler();
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingEdgeSide(false);
        mRecyclerView.addItemDecoration(spaceDecoration);
        mAdapter = new MyAppointmentAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

        mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mList.add(String.valueOf(i));
        }

        mAdapter.setNewData(mList);
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.startActivity(MyAppointmentDetailsActivity.class);
            }
        });
    }

}
