package com.baihua.yaya.home;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.DividerDecoration;
import com.baihua.yaya.doctor.DoctorDetailsActivity;
import com.baihua.yaya.doctor.DoctorListAdapter;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:byd
 * Time:7/12/2018 11:40
 * Description: TipsActivity
 */
public class TipsActivity extends BaseActivity {
    @BindView(R.id.tips_recycler)
    RecyclerView mRecyclerView;

    private List<String> mList; // 推荐医生信息集合
    private DoctorListAdapter mAdapter;

    @Override
    public void setLayout() {
        setTitle("提示");
        setContentView(R.layout.activity_tips);
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
        DividerDecoration dividerDecoration = new DividerDecoration(Color.parseColor("#f3f3f3"), ConvertUtils.dp2px(1), ConvertUtils.dp2px(12), 0);
        dividerDecoration.setDrawHeaderFooter(true);
        mRecyclerView.addItemDecoration(dividerDecoration);
        mAdapter = new DoctorListAdapter(new ArrayList<>());
        mAdapter.addHeaderView(getTipsHeader());
        mAdapter.addFooterView(getTipsFooter());
        mRecyclerView.setAdapter(mAdapter);

        mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mList.add(String.valueOf(i));
        }

        mAdapter.setNewData(mList);
    }

    private View getTipsFooter() {
        View footer = mInflater.inflate(R.layout.footer_of_tips, mRecyclerView, false);
        TextView tvBack = (TextView) footer.findViewById(R.id.tips_tv_back);
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return footer;
    }

    private View getTipsHeader() {
        return mInflater.inflate(R.layout.header_of_tips, mRecyclerView, false);
    }

    @Override
    public void setListener() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.startActivity(DoctorDetailsActivity.class);
            }
        });
    }

}
