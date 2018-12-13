package com.baihua.yaya.doctor;

import android.graphics.Color;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.DividerDecoration;
import com.blankj.utilcode.util.ConvertUtils;

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

    private List<String> mList; // 评论数据
    private CommentAdapter mAdapter; // 评论适配器

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
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerDecoration dividerDecoration = new DividerDecoration(Color.parseColor("#f3f3f3"), ConvertUtils.dp2px(1), ConvertUtils.dp2px(12), 0);
        dividerDecoration.setDrawHeaderFooter(true);
        mRecyclerView.addItemDecoration(dividerDecoration);
        mAdapter = new CommentAdapter(new ArrayList<>());
        mAdapter.addHeaderView(getCommentHeaderView());
        mRecyclerView.setAdapter(mAdapter);

        mList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mList.add(String.valueOf(i));
        }

        mAdapter.setNewData(mList);
    }

    private View getCommentHeaderView() {
        View header = mInflater.inflate(R.layout.header_of_comment, mRecyclerView, false);
        TextView comment = (TextView) header.findViewById(R.id.header_comment_text);
        comment.setText(String.format("评价（%s）", "99"));
        return header;
    }

    @Override
    public void setListener() {

    }

}
