package com.baihua.yayayisheng.my;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.Tag;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.blankj.utilcode.util.LogUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:5/12/2018 11:58
 * Description: 接诊周期选择
 */
public class MyVisitingScheduleSelectActivity extends BaseActivity {
    @BindView(R.id.header_tv_content)
    TextView headerTvContent;
    @BindView(R.id.tag_flow_layout)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.schedule_select_morning_time)
    TextView scheduleSelectMorningTime;
    @BindView(R.id.schedule_select_morning_people_count)
    EditText scheduleSelectMorningPeopleCount;
    @BindView(R.id.schedule_select_afternoon_time)
    TextView scheduleSelectAfternoonTime;
    @BindView(R.id.schedule_select_afternoon_people_count)
    EditText scheduleSelectAfternoonPeopleCount;
    @BindView(R.id.schedule_select_tv_submit)
    TextView scheduleSelectTvSubmit;
    @BindView(R.id.schedule_select_morning_end_time)
    TextView scheduleSelectMorningEndTime;
    @BindView(R.id.schedule_select_afternoon_end_time)
    TextView scheduleSelectAfternoonEndTime;

    private List<Tag> mValues;
    private TagAdapter<Tag> mAdapter;

    @Override
    public void setLayout() {
        setTitle("接诊时间表");
        setContentView(R.layout.activity_my_visiting_schedule_select);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        setContentText();
        initFlowLayout();
    }

    private void initFlowLayout() {

        mValues = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Tag tag = new Tag();
            tag.setTagDate(String.format("%s1-1%s", i, i));
            tag.setTagWeek(String.format("周%s", i));
            mValues.add(tag);
        }

        mAdapter = new TagAdapter<Tag>(mValues) {
            @Override
            public View getView(FlowLayout parent, int position, Tag tag) {
                TextView tvTag = (TextView) mInflater.inflate(R.layout.item_visiting_schedule_select, tagFlowLayout, false);
                tvTag.setText(String.format("%s\n%s", tag.getTagDate(), tag.getTagWeek()));
                return tvTag;
            }
        };

        tagFlowLayout.setAdapter(mAdapter);

        mAdapter.setSelectedList(2, 4);
    }

    /**
     * 设置数据
     */
    private void setContentText() {
        headerTvContent.setText(String.format("%s", "接诊周期"));
    }

    @Override
    public void setListener() {
        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                LogUtils.e("choose: " + selectPosSet.toString());
            }
        });
    }

    @OnClick({R.id.schedule_select_morning_time, R.id.schedule_select_afternoon_time, R.id.schedule_select_morning_end_time, R.id.schedule_select_afternoon_end_time, R.id.schedule_select_tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.schedule_select_morning_time:
                selectTime(scheduleSelectMorningTime);
                break;
            case R.id.schedule_select_afternoon_time:
                selectTime(scheduleSelectAfternoonTime);
                break;
            case R.id.schedule_select_morning_end_time:
                selectTime(scheduleSelectMorningEndTime);
                break;
            case R.id.schedule_select_afternoon_end_time:
                selectTime(scheduleSelectAfternoonEndTime);
                break;
            case R.id.schedule_select_tv_submit:
                break;
        }
    }

    private void selectTime(TextView textView) {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(2013, 1, 1);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2020, 1, 1);

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                textView.setText(String.format("%s", DateFormat.format("HH:mm", date)));
            }
        })
                .setType(new boolean[]{false, false, false, true, true, false})//默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("接诊时间")//标题文字
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(ContextCompat.getColor(MyVisitingScheduleSelectActivity.this, R.color.colorPrimary))//标题背景颜色 Night mode
                .setBgColor(ContextCompat.getColor(MyVisitingScheduleSelectActivity.this, R.color.colorPrimary))//滚轮背景颜色 Night mode
                .setRangDate(startDate, endDate)//默认是1900-2100年
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isDialog(false)//是否显示为对话框样式
                .build();

        pvTime.show();

    }

}
