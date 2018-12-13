package com.baihua.yaya.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baihua.common.base.BaseFragment;
import com.baihua.yaya.R;
import com.baihua.yaya.decoration.SpaceDecoration;
import com.baihua.yaya.my.PhotoViewActivity;
import com.baihua.yaya.view.recorder.IRecordButton;
import com.baihua.yaya.view.recorder.MediaManager;
import com.baihua.yaya.view.recorder.RecordButton;
import com.baihua.yaya.widget.GlideImageLoader;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class HomepageFragment extends BaseFragment {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.play_voice_layout)
    RelativeLayout mPlayVoiceView;
    @BindView(R.id.record_voice)
    RecordButton mRecordVoice;
    @BindView(R.id.home_photo_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.home_tv_submit)
    TextView mTvSubmit;
    @BindView(R.id.iv_play_voice_anim)
    ImageView mIvPlayVoiceAnim;
    @BindView(R.id.home_et_name)
    EditText homeEtName;
    @BindView(R.id.home_et_age)
    EditText homeEtAge;
    @BindView(R.id.home_rb_female)
    RadioButton homeRbFemale;
    @BindView(R.id.home_rb_male)
    RadioButton homeRbMale;
    @BindView(R.id.home_radio_group)
    RadioGroup homeRadioGroup;
    @BindView(R.id.home_et_mobile)
    EditText homeEtMobile;
    @BindView(R.id.home_tv_select_diet)
    TextView homeTvSelectDiet;
    @BindView(R.id.home_tv_select_sleep)
    TextView homeTvSelectSleep;
    @BindView(R.id.home_et_description)
    EditText homeEtDescription;

    private boolean isStart = false;
    private boolean isPause = false;

    private String soundPath = String.format(Locale.getDefault(),
            "%s/Record/com.zlw.main/record_20181128_10_18_04.mp3",
            Environment.getExternalStorageDirectory().getAbsolutePath());

    private int mAmplitude;

    private int mMinItemWidth; //最小的item宽度
    private int mMaxItemWidth; //最大的item宽度

    private AnimationDrawable mAnimDrawable;
    private List<LocalMedia> mReturnList = new ArrayList<>();

    @Override
    public int setRootView() {
        return R.layout.fragment_homepage;
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

        initRecycler();

    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        mRecyclerView.addItemDecoration(spaceDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
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
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
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
                mPlayVoiceView.setVisibility(View.VISIBLE);
            }
        });

        // 录音状态监听
        RecordManager.getInstance().setRecordStateListener(new RecordStateListener() {
            @Override
            public void onStateChange(RecordHelper.RecordState recordState) {
                LogUtils.e("onStateChange: " + recordState);
                switch (recordState) {
                    case PAUSE:
                        break;
                    case IDLE:
                        break;
                    case RECORDING:
                        break;
                    case STOP:
                        break;
                    case FINISH:
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
                        MediaManager.playSound(soundPath, new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                MediaManager.release();
                                mAnimDrawable.stop();
                            }
                        });
                    }
                }
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
        MediaManager.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
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
        MediaManager.release();
    }

    @OnClick({R.id.home_tv_select_diet, R.id.home_tv_select_sleep, R.id.home_tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_tv_select_diet:
                showDietDialog();
                break;
            case R.id.home_tv_select_sleep:
                showSleepDialog();
                break;
            case R.id.home_tv_submit:
                ActivityUtils.startActivity(TipsActivity.class);
                //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
//                PictureFileUtils.deleteCacheDirFile(getActivity());
                break;
        }
    }

    private void showSleepDialog() {
        if (null == getActivity())
            return;

        new MaterialDialog.Builder(getActivity())
                .title("睡眠")
                .items(new ArrayList<String>(Arrays.asList("睡眠很好", "睡眠一般", "睡眠下降", "失眠", "其它")))
                .positiveText("确定")
                .negativeText("取消")
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        homeTvSelectSleep.setText(text);
                        return false;
                    }
                })
                .show();
    }

    private void showDietDialog() {
        if (null == getActivity())
            return;

        new MaterialDialog.Builder(getActivity())
                .title("饮食")
                .items(new ArrayList<String>(Arrays.asList("食欲很好", "食欲一般", "食欲下降", "没有食欲", "其它")))
                .positiveText("确定")
                .negativeText("取消")
                .itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                        homeTvSelectDiet.setText(text);
                        return false;
                    }
                })
                .show();
    }

    /**
     * 图片上传Adapter
     */
    RecyclerView.Adapter mAdapter = new RecyclerView.Adapter<PicHolder>() {
        @Override
        public PicHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View picView = View.inflate(parent.getContext(), R.layout.item_upload_image, null);
            return new PicHolder(picView);
        }

        @Override
        public void onBindViewHolder(final PicHolder holder, final int position) {
            holder.ivDel.setVisibility(View.GONE);
            //在list.size<9时，最后一个默认显示“加号”图片
            if (position == mReturnList.size()) {
                holder.ivPic.setImageResource(R.drawable.upload);
            } else {
                //glide 加载图片
                com.baihua.yaya.util.Utils.showImg(getActivity(), mReturnList.get(position).getCompressPath(), holder.ivPic);
                //非最后一张显示删除角标
                if (position < mReturnList.size()) {
                    //图片删除角标
                    holder.ivDel.setVisibility(View.VISIBLE);
                    holder.ivDel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //从数据源list中移除
                            mReturnList.remove(position);
                            notifyDataSetChanged();
                        }
                    });
                }
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == mReturnList.size()) {
                        // 进入相册 以下是例子：用不到的api可以不写
                        PictureSelector.create(getActivity())
                                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                                .maxSelectNum(9)// 最大图片选择数量 int
//                                .minSelectNum()// 最小选择数量 int
                                .imageSpanCount(4)// 每行显示个数 int
                                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                                .previewImage(true)// 是否可预览图片 true or false
//                                .previewVideo()// 是否可预览视频 true or false
//                                .enablePreviewAudio() // 是否可播放音频 true or false
                                .isCamera(true)// 是否显示拍照按钮 true or false
                                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                                .enableCrop(false)// 是否裁剪 true or false
                                .compress(true)// 是否压缩 true or false
//                                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                                .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
//                                .isGif()// 是否显示gif图片 true or false
//                                .compressSavePath("/compress")//压缩图片保存地址
//                                .freeStyleCropEnabled()// 裁剪框是否可拖拽 true or false
//                                .circleDimmedLayer()// 是否圆形裁剪 true or false
//                                .showCropFrame()// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
//                                .showCropGrid()// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                                .openClickSound(false)// 是否开启点击声音 true or false
                                .selectionMedia(mReturnList)// 是否传入已选图片 List<LocalMedia> list
                                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                                .cropCompressQuality()// 裁剪压缩质量 默认90 int
                                .minimumCompressSize(100)// 小于100kb的图片不压缩
                                .synOrAsy(false)//同步true或异步false 压缩 默认同步
//                                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
//                                .rotateEnabled() // 裁剪是否可旋转图片 true or false
//                                .scaleEnabled()// 裁剪是否可放大缩小图片 true or false
//                                .videoQuality()// 视频录制质量 0 or 1 int
//                                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
//                                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                                .recordVideoSecond()//视频秒数录制 默认60s int
//                                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code

                    } else {
                        ActivityUtils.startActivity(PhotoViewActivity.class);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            //含有9张图片，直接展示，不需要“加号”图片
            if (mReturnList.size() == 9) {
                return 9;
            }
            //小于9张需要“加号”图片
            return mReturnList.size() + 1;
        }
    };

    class PicHolder extends RecyclerView.ViewHolder {
        /**
         * 图片控件
         */
        ImageView ivPic;
        /**
         * 图片删除控件
         */
        ImageView ivDel;

        public PicHolder(View itemView) {
            super(itemView);
            ivPic = itemView.findViewById(R.id.item_upload_image);
            ivDel = itemView.findViewById(R.id.item_upload_cancel);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK) {
            // 图片、视频、音频选择结果回调
            mReturnList = PictureSelector.obtainMultipleResult(data);
            // 例如 LocalMedia 里面返回三种path
            // 1.media.getPath(); 为原图path
            // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
            // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
            // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//            adapter.setList(selectList);
            mAdapter.notifyDataSetChanged();
        }
    }

}
