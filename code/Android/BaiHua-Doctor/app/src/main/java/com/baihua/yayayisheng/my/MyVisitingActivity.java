package com.baihua.yayayisheng.my;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.SpaceDecoration;
import com.baihua.yayayisheng.entity.DoctorRegistrationListEntity;
import com.baihua.yayayisheng.entity.RegisteredListEntity;
import com.baihua.yayayisheng.entity.form.ListForm;
import com.baihua.yayayisheng.util.CommonUtils;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyVisitingActivity extends BaseActivity {


    @BindView(R.id.my_patient_info_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<String> mList;
    //    private MyVisitingAdapter mAdapter;
    private MyAppointmentAdapter mAdapter;

    private int mCurrentPage = 1;

    @Override
    public void setLayout() {
        setTitle("我的接诊");
        setContentView(R.layout.activity_my_patient_info);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {

        initRecycler();
        getReceptionList();
    }

    /**
     * 获取接诊列表
     */
    private void getReceptionList() {
        RxHttp.getInstance().getSyncServer()
                .getReceptionList(CommonUtils.getToken(), new ListForm(mCurrentPage, 10))
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<DoctorRegistrationListEntity>(this) {

                    @Override
                    public void onSuccess(DoctorRegistrationListEntity result) {
                        // TODO: 21/12/2018
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingEdgeSide(false);
        mRecyclerView.addItemDecoration(spaceDecoration);
//        mAdapter = new MyVisitingAdapter(new ArrayList<>());
        mAdapter = new MyAppointmentAdapter(new ArrayList<>());
        Utils.showNoData(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void setListener() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage += 1;
                getReceptionList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getReceptionList();
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DoctorRegistrationListEntity.PageBean.RecordsBean recordsBean = (DoctorRegistrationListEntity.PageBean.RecordsBean) adapter.getData().get(position);
                Utils.gotoActivity(MyVisitingActivity.this, MyVisitingDetailsActivity.class, false, "registration", recordsBean);
            }
        });

    }

}
