package com.baihua.yayayisheng.view.recorder;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.helper.DialogHelper;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;


/**
 * 录音按钮
 *
 * @author GMY
 * @mail 2275964276@qq.com
 * @date 2015年6月2日
 */
public class RecordButton extends android.support.v7.widget.AppCompatButton {

    private static final int MIN_RECORD_TIME = 3; // 最短录音时间，单位秒
    private static final int MAX_RECORD_TIME = 6; // 最长录音时间，单位秒
    private static final int RECORD_OFF = 0; // 不在录音
    private static final int RECORD_ON = 1; // 正在录音

    private Dialog mRecordDialog;
    private IRecordButton mAudioRecorder;
    private Thread mRecordThread;
    private RecordListener listener;

    private int recordState = 0; // 录音状态
    private float recodeTime = 0.0f; // 录音时长，如果录音时间太短则录音失败
    private double voiceValue = 0.0; // 录音的音量值
    private boolean isCanceled = false; // 是否取消录音
    private float downY;

    private TextView dialogTextView;
    private ImageView dialogImg;
    private TextView record_time_txt;
    private Context mContext;
    // 终止线程的标志位
    private boolean isStop = false;

    public RecordButton(Context context) {
        super(context);
        init(context);
    }

    public RecordButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化相关组件
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        this.setText("按住说话");
        if (mRecordDialog == null) {
            mRecordDialog = new Dialog(mContext, R.style.ViewDialog);
            mRecordDialog.setContentView(R.layout.dialog_record);
            dialogImg = (ImageView) mRecordDialog
                    .findViewById(R.id.record_dialog_img);
            record_time_txt = (TextView) mRecordDialog
                    .findViewById(R.id.record_time_txt);
            dialogTextView = (TextView) mRecordDialog
                    .findViewById(R.id.record_dialog_txt);
        }
    }

    public void setAudioRecord(IRecordButton record) {
        this.mAudioRecorder = record;
    }

    public void setRecordListener(RecordListener listener) {
        this.listener = listener;
    }

    // 录音时显示Dialog
    private void showVoiceDialog(int flag) {

        switch (flag) {
            case 1:
                dialogImg.setImageResource(R.drawable.record_cancel);
                dialogTextView.setText("松开手指可取消录音");
                this.setText("松开手指 取消录音");
                break;

            default:
                dialogImg.setImageResource(R.drawable.record_animate_01);
                dialogTextView.setText("向上滑动可取消录音");
                this.setText("松开手指 完成录音");
                break;
        }
        dialogTextView.setTextSize(14);
        mRecordDialog.show();
    }

    // 录音时间太短时Toast显示
    private void showWarnToast(String toastText) {
        Toast toast = new Toast(mContext);
        View warnView = LayoutInflater.from(mContext).inflate(
                R.layout.toast_warn, null);
        toast.setView(warnView);
        toast.setGravity(Gravity.CENTER, 0, 0);// 起点位置为中间
        toast.show();
    }

    // 开启录音计时线程
    private void callRecordTimeThread() {
        mRecordThread = new Thread(recordThread);
        mRecordThread.start();
    }

    // 录音Dialog图片随录音音量大小切换 (为了省事，就随机动了）
    private void setDialogImage() {
        if (voiceValue < 10.0) {
            dialogImg.setImageResource(R.drawable.record_animate_01);
        } else if (voiceValue > 10.0 && voiceValue < 20.0) {
            dialogImg.setImageResource(R.drawable.record_animate_02);
        } else if (voiceValue > 20.0 && voiceValue < 30.0) {
            dialogImg.setImageResource(R.drawable.record_animate_03);
        } else if (voiceValue > 30.0 && voiceValue < 40.0) {
            dialogImg.setImageResource(R.drawable.record_animate_04);
        } else if (voiceValue > 40.0 && voiceValue < 50.0) {
            dialogImg.setImageResource(R.drawable.record_animate_05);
        } else if (voiceValue > 50.0 && voiceValue < 60.0) {
            dialogImg.setImageResource(R.drawable.record_animate_06);
        } else if (voiceValue > 60.0 && voiceValue < 70.0) {
            dialogImg.setImageResource(R.drawable.record_animate_07);
        } else if (voiceValue > 70.0 && voiceValue < 80.0) {
            dialogImg.setImageResource(R.drawable.record_animate_08);
        } else if (voiceValue > 80.0 && voiceValue < 90.0) {
            dialogImg.setImageResource(R.drawable.record_animate_09);
        } else if (voiceValue > 90.0 && voiceValue < 100.0) {
            dialogImg.setImageResource(R.drawable.record_animate_10);
        } else if (voiceValue > 100.0 && voiceValue < 110.0) {
            dialogImg.setImageResource(R.drawable.record_animate_11);
        } else if (voiceValue > 110.0 && voiceValue < 120.0) {
            dialogImg.setImageResource(R.drawable.record_animate_12);
        } else if (voiceValue > 120.0 && voiceValue < 130.0) {
            dialogImg.setImageResource(R.drawable.record_animate_13);
        } else if (voiceValue > 130.0) {
            dialogImg.setImageResource(R.drawable.record_animate_14);
        }
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis((long) (recodeTime * 1000));
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
                "mm:ss", Locale.getDefault());
        // 超时停止线程
        if (recodeTime > MAX_RECORD_TIME) {
            isCanceled = true;
            this.setText("按住说话");
        }
        record_time_txt.setText(format.format(gc.getTime()));
    }

    // 录音线程
    private Runnable recordThread = new Runnable() {

        @Override
        public void run() {
            recodeTime = 0.0f;
            while (recordState == RECORD_ON) {
                {
                    try {
                        Thread.sleep(100);
                        recodeTime += 0.1;
                        // 获取音量，更新dialog
                        if (!isCanceled) {
                            voiceValue = mAudioRecorder.getAmplitude();
                            recordHandler.sendEmptyMessage(1);
                        }
                        if (recodeTime >= MAX_RECORD_TIME) {
                            finishHandler.sendEmptyMessage(1);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    private Handler finishHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (recordState == RECORD_ON) {
                recordState = RECORD_OFF;
                if (mRecordDialog.isShowing()) {
                    mRecordDialog.dismiss();
                }
                mAudioRecorder.stop();
                mRecordThread.interrupt();
                voiceValue = 0.0;
                if (listener != null) {
                    listener.recordEnd(mAudioRecorder.getFilePath());
                }
                mAudioRecorder.complete(recodeTime);
                isCanceled = false;
            }
            return false;
        }
    });

    private Handler recordHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            setDialogImage();
            return false;
        }
    });

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!PermissionUtils.isGranted(PermissionConstants.STORAGE, PermissionConstants.MICROPHONE)) {
            PermissionUtils.permission(PermissionConstants.STORAGE, PermissionConstants.MICROPHONE)
                    .rationale(new PermissionUtils.OnRationaleListener() {
                        @Override
                        public void rationale(ShouldRequest shouldRequest) {
                            DialogHelper.showRationaleDialog(shouldRequest);
                        }
                    }).callback(new PermissionUtils.FullCallback() {
                @Override
                public void onGranted(List<String> permissionsGranted) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: // 按下按钮
                            try {
                                if (recordState != RECORD_ON) {
                                    showVoiceDialog(0);
                                    downY = event.getY();
                                    if (mAudioRecorder != null) {
                                        mAudioRecorder.ready();
                                        recordState = RECORD_ON;
                                        mAudioRecorder.start();
                                        callRecordTimeThread();
                                    }
                                }
                            } catch (Exception e) {
                                Toast.makeText(mContext, "请检查录音权限是否开启", Toast.LENGTH_SHORT)
                                        .show();
                                mRecordDialog.dismiss();
                                e.printStackTrace();
                            }

                            break;
                        case MotionEvent.ACTION_MOVE: // 滑动手指
                            float moveY = event.getY();
                            if (downY - moveY > 50) {
                                isCanceled = true;
                                showVoiceDialog(1);
                                mAudioRecorder.pause();
                            }
                            if (downY - moveY < 20) {
                                isCanceled = false;
                                showVoiceDialog(0);
                                mAudioRecorder.resume();
                            }
                            break;
                        case MotionEvent.ACTION_UP: // 松开手指
                            if (recordState == RECORD_ON) {
                                stopRecord();
                            }
                            break;
                        //避免第一次录音询问权限时，影响触摸效果
                        case MotionEvent.ACTION_CANCEL:
                            if (recordState == RECORD_ON) {
                                stopRecord();
                            }
                            break;
                    }
                }

                @Override
                public void onDenied(List<String> permissionsDeniedForever, List<String> permissionsDenied) {
                    if (!permissionsDeniedForever.isEmpty()) {
                        DialogHelper.showOpenAppSettingDialog();
                    }
                }
            }).request();
        }


        return true;
    }

    private void stopRecord() {
        recordState = RECORD_OFF;
        if (mRecordDialog.isShowing()) {
            mRecordDialog.dismiss();
        }
        mAudioRecorder.stop();
        mRecordThread.interrupt();
        voiceValue = 0.0;
        if (isCanceled) {// 取消录音
            mAudioRecorder.deleteOldFile();
        } else {
            if (recodeTime < MIN_RECORD_TIME) {
                showWarnToast("时间太短  录音失败");
                mAudioRecorder.deleteOldFile();
            } else if (recodeTime > MAX_RECORD_TIME) {
                showWarnToast("录音时间不能超过 60s");
                mAudioRecorder.deleteOldFile();
            } else {
                if (listener != null) {
                    listener.recordEnd(mAudioRecorder.getFilePath());
                }
                mAudioRecorder.complete(recodeTime);
            }
        }
        isCanceled = false;
        this.setText("按住说话");
    }

    public interface RecordListener {
        public void recordEnd(String filePath);
    }
}