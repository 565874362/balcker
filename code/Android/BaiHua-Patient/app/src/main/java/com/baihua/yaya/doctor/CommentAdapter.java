package com.baihua.yaya.doctor;

import android.support.annotation.Nullable;
import android.text.format.DateFormat;

import com.baihua.yaya.R;
import com.baihua.yaya.entity.CommentEntity;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Author:byd
 * Time:7/12/2018 9:10
 * Description: 评论适配器
 */
public class CommentAdapter extends BaseQuickAdapter<CommentEntity.PageBean.RecordsBean, BaseViewHolder> {

    public CommentAdapter(@Nullable List<CommentEntity.PageBean.RecordsBean> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentEntity.PageBean.RecordsBean item) {
        Utils.showCircleImg(mContext, item.getPatientPhoto(), helper.getView(R.id.item_comment_avatar));
        helper.setText(R.id.item_comment_mobile, Utils.hidePhoneMiddleNumber(item.getPatientAccount()))
//                .setText(R.id.item_comment_date, DateFormat.format("yyyy-MM-dd", TimeUtils.string2Date(item.getGmtCreate(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()))))
                .setText(R.id.item_comment_date, item.getGmtCreate().split(" ")[0])
                .setText(R.id.item_comment_content, item.getContent());
    }
}
