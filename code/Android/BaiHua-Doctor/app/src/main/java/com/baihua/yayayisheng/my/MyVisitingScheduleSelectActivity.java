package com.baihua.yayayisheng.my;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.DiagnoseDateEntity;
import com.baihua.yayayisheng.entity.EmptyEntity;
import com.baihua.yayayisheng.entity.form.DiagnoseForm;
import com.baihua.yayayisheng.util.CommonUtils;
import com.bigkoo.pickerview.adapter.ArrayWheelAdapter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.luoshihai.xxdialog.DialogViewHolder;
import com.luoshihai.xxdialog.XXDialog;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import butterknife.BindView;
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

    private List<DiagnoseDateEntity.DiagnoseDatesBean> mDatesBeanList;

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
        getDiagnoseDates();
        setContentText();
    }

    /**
     * 获取接诊周期
     */
    private void getDiagnoseDates() {
        RxHttp.getInstance().getSyncServer()
                .getDiagnoseDates(CommonUtils.getToken())
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<DiagnoseDateEntity>(this) {
                    @Override
                    public void onSuccess(DiagnoseDateEntity result) {
                        mDatesBeanList = result.getDiagnoseDates();
                        initFlowLayout(result.getDiagnoseDates());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    private void initFlowLayout(List<DiagnoseDateEntity.DiagnoseDatesBean> datesBeanList) {

        TagAdapter<DiagnoseDateEntity.DiagnoseDatesBean> mAdapter = new TagAdapter<DiagnoseDateEntity.DiagnoseDatesBean>(datesBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, DiagnoseDateEntity.DiagnoseDatesBean tag) {
                TextView tvTag = (TextView) mInflater.inflate(R.layout.item_visiting_schedule_select, tagFlowLayout, false);
                tvTag.setText(String.format("%s\n%s", tag.getMonthDay(), tag.getWeek()));
                return tvTag;
            }
        };

        tagFlowLayout.setAdapter(mAdapter);
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
                selectCustomTime(scheduleSelectMorningTime, true, true);
                break;
            case R.id.schedule_select_afternoon_time:
                selectCustomTime(scheduleSelectAfternoonTime, false, true);
                break;
            case R.id.schedule_select_morning_end_time:
                selectCustomTime(scheduleSelectMorningEndTime, true, false);
                break;
            case R.id.schedule_select_afternoon_end_time:
                selectCustomTime(scheduleSelectAfternoonEndTime, false, false);
                break;
            case R.id.schedule_select_tv_submit:
                submitSelectedTime();
                break;
        }
    }

    /**
     * 新增接诊时间提交
     */
    private void submitSelectedTime() {
        List<String> dates = new ArrayList<>();
        Set<Integer> selectedList = tagFlowLayout.getSelectedList();
        if (selectedList.isEmpty()) {
            toast("请选择接诊周期");
            return;
        }
        for (Integer aSelectedList : selectedList) {
            dates.add(mDatesBeanList.get(aSelectedList).getDate());
        }

        String morBegin = CommonUtils.getTextString(scheduleSelectMorningTime);
        String morEnd = CommonUtils.getTextString(scheduleSelectMorningEndTime);
        String aftBegin = CommonUtils.getTextString(scheduleSelectAfternoonTime);
        String aftEnd = CommonUtils.getTextString(scheduleSelectAfternoonEndTime);
        String morNum = CommonUtils.getTextString(scheduleSelectMorningPeopleCount);
        String aftNum = CommonUtils.getTextString(scheduleSelectAfternoonPeopleCount);

        if (TextUtils.isEmpty(morBegin)) {
            toast("请选择上午开始时间");
            return;
        }
        if (TextUtils.isEmpty(morEnd)) {
            toast("请选择上午结束时间");
            return;
        }
        if (TimeUtils.string2Date(morBegin, new SimpleDateFormat("HH:mm", Locale.getDefault()))
                .after(TimeUtils.string2Date(morEnd, new SimpleDateFormat("HH:mm", Locale.getDefault())))) {
            toast("开始时间应该在结束时间之前");
            return;
        }

        if (TextUtils.isEmpty(morNum)) {
            toast("请输入上午接诊人数");
            return;
        }

        if (TextUtils.isEmpty(aftBegin)) {
            toast("请选择下午开始时间");
            return;
        }
        if (TextUtils.isEmpty(aftEnd)) {
            toast("请选择下午结束时间");
            return;
        }
        if (TimeUtils.string2Date(aftBegin, new SimpleDateFormat("HH:mm", Locale.getDefault()))
                .after(TimeUtils.string2Date(aftEnd, new SimpleDateFormat("HH:mm", Locale.getDefault())))) {
            toast("开始时间应该在结束时间之前");
            return;
        }

        if (TextUtils.isEmpty(aftNum)) {
            toast("请输入下午接诊人数");
            return;
        }

        DiagnoseForm diagnoseForm = new DiagnoseForm();
        diagnoseForm.setMoringBegin(morBegin);
        diagnoseForm.setMoringEnd(morEnd);
        diagnoseForm.setMorningNum(morNum);
        diagnoseForm.setAfternoonBegin(aftBegin);
        diagnoseForm.setAfternoonEnd(aftEnd);
        diagnoseForm.setAfternoonNum(aftNum);
        diagnoseForm.setDates(dates);

        RxHttp.getInstance().getSyncServer()
                .addDiagnose(CommonUtils.getToken(), diagnoseForm)
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<EmptyEntity>(this) {
                    @Override
                    public void onSuccess(EmptyEntity result) {
                        toast("提交成功");
                        finish();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }


    /**
     * 自定义时间选中
     *
     * @param textView 目标Text
     * @param isAM     是否为上午
     */
    String mHour = "", mMin = "00";

    private void selectCustomTime(TextView textView, boolean isAM, boolean isStart) {
        List<String> hour = new ArrayList<>();
        List<String> minute = new ArrayList<>();
        new XXDialog(this, R.layout.dialog_select_custom_time) {
            @Override
            public void convert(DialogViewHolder holder) {
                if (isAM) {
                    if (isStart) {
                        holder.setText(R.id.tv_title, "上午开始接诊时间选择");
                    } else {
                        holder.setText(R.id.tv_title, "上午结束接诊时间选择");
                    }
                } else {
                    if (isStart) {
                        holder.setText(R.id.tv_title, "下午开始接诊时间选择");
                    } else {
                        holder.setText(R.id.tv_title, "下午结束接诊时间选择");
                    }
                }

                WheelView hourWheel = holder.getView(R.id.wheel_hour);
                hourWheel.setCyclic(false);
                WheelView minuteWheel = holder.getView(R.id.wheel_minute);
                minuteWheel.setCyclic(false);

                minute.add("00");
                minute.add("30");

                if (isAM) { // 上午
                    mHour = "09";
                    for (int i = 0; i < 13; i++) {
                        if (i < 10) {
                            hour.add("0" + String.valueOf(i));
                        } else {
                            hour.add(String.valueOf(i));
                        }
                    }
                } else { // 下午
                    mHour = "14";
                    for (int i = 13; i < 24; i++) {
                        hour.add(String.valueOf(i));
                    }
                }


                hourWheel.setAdapter(new ArrayWheelAdapter(hour));
                if (isAM)
                    hourWheel.setCurrentItem(9);
                else
                    hourWheel.setCurrentItem(1);
                hourWheel.setTextColorCenter(Color.WHITE);
                hourWheel.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {
                        mHour = hour.get(index);
                    }
                });

                minuteWheel.setAdapter(new ArrayWheelAdapter(minute));
                minuteWheel.setTextColorCenter(Color.WHITE);
                minuteWheel.setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int index) {
                        mMin = minute.get(index);
                    }
                });

                holder.setOnClick(R.id.tv_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                holder.setOnClick(R.id.tv_ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                        textView.setText(String.format("%s:%s", mHour, mMin));
                    }
                });

            }
        }.fromBottom().backgroundLight(0.2).fullWidth().showDialog();
    }

}
