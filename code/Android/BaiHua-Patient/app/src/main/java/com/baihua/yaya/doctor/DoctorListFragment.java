package com.baihua.yaya.doctor;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.baihua.common.base.BaseFragment;
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
 * Time:6/12/2018 16:48
 * Description: DoctorListFragment
 */
public class DoctorListFragment extends BaseFragment {

    @BindView(R.id.doctor_ll_search)
    LinearLayout doctorLlSearch;
    @BindView(R.id.doctor_recycler)
    RecyclerView mRecyclerView;

    private List<String> mList;
    private DoctorListAdapter mAdapter;

    @Override
    public int setRootView() {
        return R.layout.fragment_doctor;
    }

    @Override
    public void setLayout() {

    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        initRecycler();
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingEdgeSide(false);
        mRecyclerView.addItemDecoration(spaceDecoration);
        mAdapter = new DoctorListAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);

        mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mList.add(String.valueOf(i));
        }

        mAdapter.setNewData(mList);
    }

    @Override
    public void setListener() {
        doctorLlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ActivityUtils.startActivity(DoctorDetailsActivity.class);
            }
        });
    }

}
