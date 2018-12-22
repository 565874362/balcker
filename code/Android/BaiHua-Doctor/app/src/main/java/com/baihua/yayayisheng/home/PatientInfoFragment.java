package com.baihua.yayayisheng.home;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.base.BaseFragment;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.SpaceDecoration;
import com.baihua.yayayisheng.entity.PatientListEntity;
import com.baihua.yayayisheng.entity.form.PatientListForm;
import com.baihua.yayayisheng.util.CommonUtils;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PatientInfoFragment extends BaseFragment {


    @BindView(R.id.patient_info_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<String> mList;
    private PatientInfoAdapter mAdapter;

    private boolean mIsNeedRefresh;

    private int mCurrentPage = 1;

    @Override
    public int setRootView() {
        return R.layout.fragment_patient_info;
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

    @Override
    public void onResume() {
        super.onResume();
        if (mIsNeedRefresh)
            smartRefreshLayout.autoRefresh();
    }

    /**
     * 获取患者信息列表
     */
    private void getPatientInfoList() {
        RxHttp.getInstance().getSyncServer()
                .getPatientList(CommonUtils.getToken(), new PatientListForm(mCurrentPage, 10))
                .compose(RxSchedulers.observableIO2Main(getActivity()))
                .subscribe(new ProgressObserver<PatientListEntity>(getActivity()) {
                    @Override
                    public void onSuccess(PatientListEntity result) {
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
        mAdapter = new PatientInfoAdapter(new ArrayList<>());
        Utils.showNoData(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void setListener() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage += 1;
                getPatientInfoList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getPatientInfoList();
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mIsNeedRefresh = true;
                PatientListEntity.PageBean.RecordsBean recordsBean = (PatientListEntity.PageBean.RecordsBean) adapter.getData().get(position);
                Utils.gotoActivity(getActivity(), PatientInfoDetailsActivity.class, false, "visit", recordsBean);
            }
        });

    }

}
