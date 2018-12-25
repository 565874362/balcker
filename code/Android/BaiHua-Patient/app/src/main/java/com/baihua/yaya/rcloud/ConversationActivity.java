package com.baihua.yaya.rcloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.widget.TextView;

import com.baihua.yaya.R;


public class ConversationActivity extends AppCompatActivity {

    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.toolbar_tv_title);

        String title = getIntent().getData().getQueryParameter("title");
        if (TextUtils.isEmpty(title))
            mTvTitle.setText("吖吖医疗");
        else
            mTvTitle.setText(title);

        setSupportActionBar(toolbar);

//        mTvTitle.setText("咨询");

        if (null != getSupportActionBar()) {
            //隐藏Toolbar的标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

    }

}
