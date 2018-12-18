package com.baihua.yaya.doctor;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.baihua.common.base.BaseFragment;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.entity.DoctorEntity;
import com.baihua.yaya.entity.form.DoctorForm;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

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
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private DoctorListAdapter mAdapter;

    private int mCurrentPage = 1;

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
        smartRefreshLayout.autoRefresh();
    }

    /**
     * 获取医生列表
     */
    private void getDoctorList(int currentPage) {
        RxHttp.getInstance().getSyncServer().getDoctorList(new DoctorForm(currentPage, "", 10))
                .compose(RxSchedulers.observableIO2Main(getActivity()))
                .subscribe(new ProgressObserver<DoctorEntity>(getActivity()) {
                    @Override
                    public void onSuccess(DoctorEntity result) {
                        Utils.finishRefreshAndLoadMore(smartRefreshLayout);
                        Utils.cancelLoadMore(smartRefreshLayout, result.getPage().getCurrent(), result.getPage().getPages());
                        if (1 < result.getPage().getCurrent()) {
                            mAdapter.addData(result.getPage().getRecords());
                        } else {
                            mAdapter.setNewData(result.getPage().getRecords());
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        Utils.finishRefreshAndLoadMore(smartRefreshLayout);
                        toast(errorMsg);
                    }
                });
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingEdgeSide(false);
        mRecyclerView.addItemDecoration(spaceDecoration);
        mAdapter = new DoctorListAdapter(new ArrayList<>());
        Utils.showNoData(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage += 1;
                getDoctorList(mCurrentPage);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getDoctorList(mCurrentPage);
            }
        });

        doctorLlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(DoctorSearchActivity.class);
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DoctorEntity.PageBean.RecordsBean recordsBean = (DoctorEntity.PageBean.RecordsBean) adapter.getData().get(position);
                Utils.gotoActivity(getActivity(), DoctorDetailsActivity.class, false, "doctorId", recordsBean.getId());
            }
        });
    }

}
