package com.baihua.yaya.my;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.entity.RegisteredListEntity;
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

/**
 * Author:byd
 * Time:7/12/2018 16:47
 * Description: 我的预约
 */
public class MyAppointmentActivity extends BaseActivity {
    @BindView(R.id.my_appointment_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<String> mList;
    private MyAppointmentAdapter mAdapter;

    private int mCurrentPage = 1;


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
        smartRefreshLayout.autoRefresh();
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
    }

    @Override
    public void setListener() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage += 1;
                getRegisteredList();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getRegisteredList();
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RegisteredListEntity.PageBean.RecordsBean recordsBean = (RegisteredListEntity.PageBean.RecordsBean) adapter.getData().get(position);
                Utils.gotoActivity(MyAppointmentActivity.this, MyAppointmentDetailsActivity.class, false, "appointment", recordsBean);
            }
        });
    }

    private void getRegisteredList() {
        RxHttp.getInstance().getSyncServer()
                .getRegisteredList(CommonUtils.getToken(), new PatientListForm(mCurrentPage, 10))
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<RegisteredListEntity>(this) {
                    @Override
                    public void onSuccess(RegisteredListEntity result) {
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

}
