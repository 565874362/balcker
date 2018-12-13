package com.baihua.yaya.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author:byd
 * Time:3/12/2018 10:24
 * Description: MySelectImageAdapter
 */
public class MySelectImageAdapter extends RecyclerView.Adapter {

    @Override
    public PicChoiceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class PicChoiceHolder extends RecyclerView.ViewHolder{

        public PicChoiceHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
