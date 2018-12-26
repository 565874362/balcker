package com.baihua.yaya.doctor;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
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
 * Time:17/12/2018 11:38
 * Description: 医生搜索
 */
public class DoctorSearchActivity extends BaseActivity {
    @BindView(R.id.doctor_search_iv_back)
    ImageView doctorSearchIvBack;
    @BindView(R.id.doctor_et_search_content)
    EditText doctorEtSearchContent;
    @BindView(R.id.doctor_tv_search)
    TextView doctorTvSearch;
    @BindView(R.id.doctor_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private DoctorListAdapter mAdapter;

    private int mCurrentPage = 1; // 当前页

    private String mKeyWord = ""; // 搜索关键字

    @Override
    public void setLayout() {
        hideTitleBar();
        setContentView(R.layout.activity_doctor_search);
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
        mAdapter = new DoctorListAdapter(new ArrayList<>());
        Utils.showNoData(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

        // smartRefreshLayout
        smartRefreshLayout.setEnableRefresh(false);
    }

    /**
     * 获取医生列表
     */
    private void getDoctorList(int currentPage, String keyWord) {
        RxHttp.getInstance().getSyncServer().getDoctorList(new DoctorForm(currentPage, keyWord, 10))
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<DoctorEntity>(this) {
                    @Override
                    public void onSuccess(DoctorEntity result) {
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


    @Override
    public void setListener() {

        doctorSearchIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        doctorTvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });

        doctorEtSearchContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    search();
                    return true;
                }
                return false;
            }
        });

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage += 1;
                getDoctorList(mCurrentPage, mKeyWord);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getDoctorList(mCurrentPage, mKeyWord);
            }
        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DoctorEntity.PageBean.RecordsBean recordsBean = (DoctorEntity.PageBean.RecordsBean) adapter.getData().get(position);
                Utils.gotoActivity(DoctorSearchActivity.this, DoctorDetailsActivity.class, false, "doctorId", recordsBean.getId());
            }
        });
    }

    /**
     * 搜索
     */
    private void search() {
        mKeyWord = doctorEtSearchContent.getText().toString().trim();
        if (TextUtils.isEmpty(mKeyWord))
            return;
        getDoctorList(mCurrentPage, mKeyWord);
    }

}
