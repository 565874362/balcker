package com.baihua.yaya.home;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.baihua.common.base.BaseFragment;
import com.baihua.yaya.R;
import com.baihua.yaya.view.recorder.IRecordButton;
import com.baihua.yaya.view.recorder.MediaManager;
import com.baihua.yaya.view.recorder.RecordButton;
import com.baihua.yaya.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.zlw.main.recorderlib.RecordManager;
import com.zlw.main.recorderlib.recorder.RecordConfig;
import com.zlw.main.recorderlib.recorder.RecordHelper;
import com.zlw.main.recorderlib.recorder.listener.RecordResultListener;
import com.zlw.main.recorderlib.recorder.listener.RecordSoundSizeListener;
import com.zlw.main.recorderlib.recorder.listener.RecordStateListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import jaygoo.widget.wlv.WaveLineView;

public class HomeFragment extends BaseFragment {


    @BindView(R.id.btn_recorder)
    Button mBtnRecorder;
    @BindView(R.id.btn_stop_recorder)
    Button mBtnStopRecorder;
    @BindView(R.id.tv_state)
    TextView mTvState;
    @BindView(R.id.tv_sound_size)
    TextView mTvSoundSize;
    @BindView(R.id.wave_view)
    WaveLineView mWaveView;
    @BindView(R.id.btn_play_sound)
    Button mPlaySound;
    @BindView(R.id.btn_press_record)
    RecordButton mPressRecord;
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.play_voice_layout)
    View mPlayVoiceView;
    @BindView(R.id.record_voice)
    RecordButton mRecordVoice;
    @BindView(R.id.home_photo_recycler)
    RecyclerView mPreResultRecycler;
    @BindView(R.id.home_tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.iv_play_voice_anim)
    ImageView mIvPlayVoiceAnim;

    private boolean isStart = false;
    private boolean isPause = false;

    private String soundPath = String.format(Locale.getDefault(),
            "%s/Record/com.zlw.main/record_20181128_10_18_04.mp3",
            Environment.getExternalStorageDirectory().getAbsolutePath());

    private int mAmplitude;

    private int mMinItemWidth; //最小的item宽度
    private int mMaxItemWidth; //最大的item宽度

    private AnimationDrawable mAnimDrawable;

    @Override
    public int setRootView() {
        return R.layout.fragment_home;
    }

    @Override
    public void setLayout() {

    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {

        initBanner();

        RecordManager.getInstance().changeFormat(RecordConfig.RecordFormat.MP3);
        String recordDir = String.format(Locale.getDefault(),
                "%s/Record/com.zlw.main/",
                Environment.getExternalStorageDirectory().getAbsolutePath());
        RecordManager.getInstance().changeRecordDir(recordDir);

//        RxHttp.getInstance().getSyncServer().getLatestVersion("1", "1.0.0")
//                .compose(RxSchedulers.observableIO2Main(getActivity()))
//                .subscribe(new ProgressObserver<String>(getActivity()) {
//
//                    @Override
//                    public void onSuccess(String result) {
//                        toast(result);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable e, String errorMsg) {
//
//                    }
//                });

        mMaxItemWidth = (int) (ScreenUtils.getScreenWidth() * 0.7f);
        mMinItemWidth = (int) (ScreenUtils.getScreenWidth() * 0.15f);

        mAnimDrawable = (AnimationDrawable) mIvPlayVoiceAnim.getDrawable();

    }

    private void initBanner() {

        List<String> images = new ArrayList<>();
        images.add("http://h.hiphotos.baidu.com/image/h%3D300/sign=22d266a5d839b60052ce09b7d9513526/f2deb48f8c5494eec7036a5f20f5e0fe99257e56.jpg");
        images.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2593321770,2679209096&fm=26&gp=0.jpg");
        images.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1209904593,2579101878&fm=26&gp=0.jpg");
        List<String> titles = new ArrayList<>();
        titles.add("紫霞仙子");
        titles.add("阳光海滩");
        titles.add("诗和远方");

        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(images);
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    @Override
    public void setListener() {


        // 录音结果监听
        RecordManager.getInstance().setRecordResultListener(new RecordResultListener() {
            @Override
            public void onResult(File file) {
                LogUtils.e("onResult: " + file.getAbsolutePath());
                soundPath = file.getAbsolutePath();
                LogUtils.e("sound length : " + MediaManager.getSoundDuration(soundPath));
                long voiceLength = MediaManager.getSoundDuration(soundPath);
                ViewGroup.LayoutParams layoutParams = mPlayVoiceView.getLayoutParams();
                layoutParams.width = (int) (mMinItemWidth + (mMaxItemWidth / 60f) * voiceLength / 1000);
                mPlayVoiceView.setLayoutParams(layoutParams);
            }
        });

        // 录音状态监听
        RecordManager.getInstance().setRecordStateListener(new RecordStateListener() {
            @Override
            public void onStateChange(RecordHelper.RecordState recordState) {
                LogUtils.e("onStateChange: " + recordState);
                switch (recordState) {
                    case PAUSE:
                        mTvState.setText("暂停中");
                        break;
                    case IDLE:
                        mTvState.setText("空闲中");
                        break;
                    case RECORDING:
                        mTvState.setText("录音中");
                        break;
                    case STOP:
                        mTvState.setText("停止");
                        break;
                    case FINISH:
                        mTvState.setText("录音结束");
                        mTvSoundSize.setText("---");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onError(String s) {
                LogUtils.e("onError: " + s);
            }
        });

        // 声音大小监听
        RecordManager.getInstance().

                setRecordSoundSizeListener(new RecordSoundSizeListener() {
                    @Override
                    public void onSoundSize(int i) {
                        mWaveView.setVolume(i);
                        mAmplitude = i;
                    }
                });

        mRecordVoice.setAudioRecord(new IRecordButton() {
            @Override
            public void ready() {

            }

            @Override
            public void start() {
                RecordManager.getInstance().start();
            }

            @Override
            public void pause() {
                RecordManager.getInstance().pause();
            }

            @Override
            public void resume() {
                RecordManager.getInstance().resume();
            }

            @Override
            public void complete(float time) {
                LogUtils.e("complete: " + time);
            }

            @Override
            public void stop() {
                RecordManager.getInstance().stop();
            }

            @Override
            public void deleteOldFile() {
                if (FileUtils.isFileExists(soundPath)) {
                    LogUtils.e(soundPath);
                    FileUtils.delete(soundPath);
                }
            }

            @Override
            public double getAmplitude() {
                return mAmplitude;
            }

            @Override
            public String getFilePath() {
                return null;
            }
        });

        mPlayVoiceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mAnimDrawable) {
                    if (mAnimDrawable.isRunning()) {
                        mAnimDrawable.selectDrawable(0);
                        mAnimDrawable.stop();
                    } else {
                        mAnimDrawable.start();
                    }
                }
            }
        });

        mBtnRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStart) {
                    RecordManager.getInstance().pause();
                    mWaveView.onPause();
                    mBtnRecorder.setText("开始");
                    isPause = true;
                    isStart = false;
                } else {
                    if (isPause) {
                        RecordManager.getInstance().resume();
                        mWaveView.onResume();
                    } else {
                        RecordManager.getInstance().start();
                        mWaveView.startAnim();
                    }
                    mBtnRecorder.setText("暂停");
                    isStart = true;
                }

            }

        });

        mBtnStopRecorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordManager.getInstance().stop();
                mWaveView.stopAnim();
                mBtnRecorder.setText("开始");
                isPause = false;
                isStart = false;
            }
        });

        mPlaySound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.playSound(soundPath, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        MediaManager.release();
                    }
                });
            }
        });

        mPressRecord.setRecordListener(new RecordButton.RecordListener() {
            @Override
            public void recordEnd(String filePath) {
                LogUtils.e("-------------" + filePath + "------------");
            }
        });

        mPressRecord.setAudioRecord(new IRecordButton() {
            @Override
            public void ready() {

            }

            @Override
            public void start() {
                RecordManager.getInstance().start();
                mWaveView.startAnim();
            }

            @Override
            public void pause() {
                RecordManager.getInstance().pause();
                mWaveView.onPause();
            }

            @Override
            public void resume() {
                RecordManager.getInstance().resume();
                mWaveView.onResume();
            }

            @Override
            public void complete(float time) {
                LogUtils.e("complete: " + time);
            }

            @Override
            public void stop() {
                RecordManager.getInstance().stop();
                mWaveView.stopAnim();
            }

            @Override
            public void deleteOldFile() {
                if (FileUtils.delete(soundPath)) {
                    toast("It's ok, deleted !!");
                }
            }

            @Override
            public double getAmplitude() {
                LogUtils.e("getAmplitude: " + mAmplitude);
                return mAmplitude;
            }

            @Override
            public String getFilePath() {
                LogUtils.e(soundPath);
                return soundPath;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        mBanner.startAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        mWaveView.onPause();
        MediaManager.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mWaveView.onPause();
        MediaManager.pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mBanner.stopAutoPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWaveView.release();
        MediaManager.release();
    }
}
