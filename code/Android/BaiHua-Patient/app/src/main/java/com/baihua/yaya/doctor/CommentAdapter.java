package com.baihua.yaya.doctor;

import android.support.annotation.Nullable;
import android.text.format.DateFormat;

import com.baihua.yaya.R;
import com.baihua.yaya.util.Utils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Author:byd
 * Time:7/12/2018 9:10
 * Description: 评论适配器
 */
public class CommentAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public CommentAdapter(@Nullable List<String> data) {
        super(R.layout.item_comment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        Utils.showCircleImg(mContext, "http://img.hb.aicdn.com/6ca9e64fff19a3cb7b00bde80da9ad2083484ee67595-ZwmOZe_fw658", helper.getView(R.id.item_comment_avatar));
        String mobile = "13634567890";
        helper.setText(R.id.item_comment_mobile, String.format("%s%s%s", mobile.substring(0, 3), "****", mobile.substring(7, mobile.length())))
                .setText(R.id.item_comment_date, DateFormat.format("yyyy-MM-dd", System.currentTimeMillis()))
                .setText(R.id.item_comment_content, "很感谢令狐医生的细心解读，真的是吃了一颗定心丸，自己也不用苏斯乱想了。");
    }
}
