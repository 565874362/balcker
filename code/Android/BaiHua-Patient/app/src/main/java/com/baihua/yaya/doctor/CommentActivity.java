package com.baihua.yaya.doctor;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.DividerDecoration;
import com.baihua.yaya.entity.CommentEntity;
import com.baihua.yaya.entity.form.CommentForm;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ConvertUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Author:byd
 * Time:7/12/2018 10:09
 * Description: 评论列表
 */
public class CommentActivity extends BaseActivity {
    @BindView(R.id.comment_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smartRefreshLayout;

    private List<String> mList; // 评论数据
    private CommentAdapter mAdapter; // 评论适配器

    private TextView mComment; // 评论个数

    private int mCurrentPage = 1;
    private String mDoctorId;

    @Override
    public void setLayout() {
        setTitle("评价");
        setContentView(R.layout.activity_comment);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        initRecycler();
        if (getIntent().hasExtra("doctorId")) {
            mDoctorId = (String) getIntent().getSerializableExtra("doctorId");
            if (TextUtils.isEmpty(mDoctorId))
                return;
            smartRefreshLayout.autoRefresh();
        }
    }

    /**
     * 获取医生评论列表
     */
    private void getDoctorComment() {
        RxHttp.getInstance().getSyncServer()
                .getDoctorCommentList(new CommentForm(mCurrentPage, String.valueOf(mDoctorId), 10))
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<CommentEntity>(this) {
                    @Override
                    public void onSuccess(CommentEntity result) {
                        Utils.finishRefreshAndLoadMore(smartRefreshLayout);
                        Utils.cancelLoadMore(smartRefreshLayout, result.getPage().getCurrent(), result.getPage().getPages());
                        mComment.setText(String.format("评价（%s）", String.valueOf(result.getPage().getTotal())));
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
        DividerDecoration dividerDecoration = new DividerDecoration(Color.parseColor("#f3f3f3"), ConvertUtils.dp2px(1), ConvertUtils.dp2px(12), 0);
        dividerDecoration.setDrawHeaderFooter(true);
        mRecyclerView.addItemDecoration(dividerDecoration);
        mAdapter = new CommentAdapter(new ArrayList<>());
        mAdapter.addHeaderView(getCommentHeaderView());
        Utils.showNoData(mAdapter, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }

    private View getCommentHeaderView() {
        View header = mInflater.inflate(R.layout.header_of_comment, mRecyclerView, false);
        mComment = (TextView) header.findViewById(R.id.header_comment_text);
        return header;
    }

    @Override
    public void setListener() {

        smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mCurrentPage += 1;
                getDoctorComment();
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mCurrentPage = 1;
                getDoctorComment();
            }
        });

    }

}
