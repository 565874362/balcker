package com.baihua.yaya.my;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.Constants;
import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.entity.AvatarEntity;
import com.baihua.yaya.entity.PatientListEntity;
import com.baihua.yaya.entity.form.PatientListForm;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.http.Body;

/**
 * Author:byd
 * Time:7/12/2018 14:46
 * Description: 我的问诊
 */
public class MyVisitActivity extends BaseActivity {
    @BindView(R.id.visit_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<String> mList;
    private VisitAdapter mAdapter;

    private int mCurrentPage = 1;

    @Override
    public void setLayout() {
        setTitle("我的问诊");
        setContentView(R.layout.activity_my_visit);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        initRecycler();
        smartRefreshLayout.autoRefresh();
    }

    private void getVisitList() {
        RxHttp.getInstance().getSyncServer()
                .getPatientList(CommonUtils.getToken(), new PatientListForm(mCurrentPage, 10))
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<PatientListEntity>(this) {
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingEdgeSide(false);
        mRecyclerView.addItemDecoration(spaceDecoration);
        mAdapter = new VisitAdapter(new ArrayList<>());
        Utils.showNoData(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void setListener() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage += 1;
                getVisitList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getVisitList();
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                PatientListEntity.PageBean.RecordsBean recordsBean = (PatientListEntity.PageBean.RecordsBean) adapter.getData().get(position);
                Utils.gotoActivity(MyVisitActivity.this, MyVisitDetailsActivity.class, false, "visit", recordsBean);
            }
        });
    }

}
