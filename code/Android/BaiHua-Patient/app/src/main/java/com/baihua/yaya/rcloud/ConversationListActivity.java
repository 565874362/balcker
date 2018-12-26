package com.baihua.yaya.rcloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.baihua.yaya.R;
import com.jaeger.library.StatusBarUtil;

public class ConversationListActivity extends AppCompatActivity {

    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist);

        StatusBarUtil.setTranslucentForImageViewInFragment(this, 0, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvTitle = findViewById(R.id.toolbar_tv_title);
        ImageButton ibLeft = findViewById(R.id.toolbar_ib_start);
        ibLeft.setVisibility(View.VISIBLE);

        setSupportActionBar(toolbar);
        mTvTitle.setText("我的咨询");
        if (null != getSupportActionBar()) {
            //隐藏Toolbar的标题
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }

        ibLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
