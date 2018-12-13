package com.baihua.yaya.my;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:byd
 * Time:7/12/2018 14:46
 * Description: 我的问诊
 */
public class MyVisitActivity extends BaseActivity {
    @BindView(R.id.visit_recycler)
    RecyclerView mRecyclerView;

    private List<String> mList;
    private VisitAdapter mAdapter;

    @Override
    public void setLayout() {
        setTitle("问诊");
        setContentView(R.layout.activity_my_visit);
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
        mAdapter = new VisitAdapter(new ArrayList<>());
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
                if (position % 2 == 0) {
                    Utils.gotoActivity(MyVisitActivity.this, MyVisitDetailsActivity.class, false, "answer", "answer");
                } else {
                    Utils.gotoActivity(MyVisitActivity.this, MyVisitDetailsActivity.class, false, null, null);
                }
            }
        });
    }

}
