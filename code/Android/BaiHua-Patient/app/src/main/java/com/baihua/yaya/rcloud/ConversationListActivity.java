package com.baihua.yaya.rcloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.baihua.yaya.R;

public class ConversationListActivity extends AppCompatActivity {

    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.toolbar_tv_title);

        setSupportActionBar(toolbar);
        mTvTitle.setText("我的咨询");
        if (null != getSupportActionBar()) {
            //隐藏Toolbar的标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

    }

}
