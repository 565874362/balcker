package com.baihua.yaya.my;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baihua.common.base.BaseFragment;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.MainActivity;
import com.baihua.yaya.R;
import com.baihua.yaya.entity.AccountEntity;
import com.baihua.yaya.entity.AvatarEntity;
import com.baihua.yaya.entity.FileEntity;
import com.baihua.yaya.entity.form.AvatarForm;
import com.baihua.yaya.rcloud.RCUtils;
import com.baihua.yaya.util.CommonUtils;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

/**
 * Author:byd
 * Time:4/12/2018 14:39
 * Description: 我的
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.my_iv_avatar)
    CircleImageView myIvAvatar;
    @BindView(R.id.my_ll_visiting)
    LinearLayout myLlVisting;
    @BindView(R.id.my_ll_my_registered)
    LinearLayout myLlMyTreatment;
    @BindView(R.id.my_ll_ask_and_answer)
    LinearLayout myLlTreatmentTime;
    @BindView(R.id.my_tv_exit_account)
    TextView myTvExitAccount;

    private List<LocalMedia> mLocalMedia = new ArrayList<>();

    @Override
    public int setRootView() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && null != getActivity()) {
//            // 设置状态栏透明
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //  设置根布局的参数
//            //将根rootView直接顶上去，和状态栏的顶部对齐。
//            ViewGroup rootView = (ViewGroup) ((ViewGroup) getActivity().findViewById(android.R.id.content)).getChildAt(0);
//            ViewCompat.setFitsSystemWindows(rootView, false);
//            rootView.setClipToPadding(true);
//        }

        return R.layout.fragment_my;
    }

    //得到系统statusbar的高度
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void setLayout() {

    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        getAvatar();
        if (Utils.isLogin(getActivity())) {
            myTvExitAccount.setVisibility(View.VISIBLE);
        }

    }

    /**
     * 获取头像
     */
    private void getAvatar() {
        RxHttp.getInstance().getSyncServer()
                .getAvatar(CommonUtils.getToken())
                .compose(RxSchedulers.observableIO2Main(getActivity()))
                .subscribe(new ProgressObserver<AvatarEntity>(getActivity()) {
                    @Override
                    public void onSuccess(AvatarEntity result) {
                        Utils.showUserHead(getActivity(), myIvAvatar, result.getPhoto());
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.my_iv_avatar, R.id.my_ll_visiting, R.id.my_ll_my_registered, R.id.my_ll_ask_and_answer, R.id.my_tv_exit_account})
    public void onViewClicked(View view) {
        if (!Utils.isLogin(getActivity())) {
            Utils.goLogin(getActivity());
            return;
        }
        switch (view.getId()) {
            case R.id.my_iv_avatar:
                showSingleCamera(getActivity());
                break;
            case R.id.my_ll_visiting:
                ActivityUtils.startActivity(MyVisitActivity.class);
                break;
            case R.id.my_ll_my_registered:
                ActivityUtils.startActivity(MyAppointmentActivity.class);
                break;
            case R.id.my_ll_ask_and_answer:
                RCUtils.startConversationList(getActivity());
                break;
            case R.id.my_tv_exit_account:
                exitYYAPP();
                break;
        }
    }

    /**
     * 退出患者端
     */
    private void exitYYAPP() {
        CommonUtils.clearToken();
        myTvExitAccount.setVisibility(View.GONE);
        Utils.showUserHead(getActivity(), myIvAvatar, "");
        if (null != getActivity())
            ((MainActivity) getActivity()).setCurrentPage(0);
    }

    private void showSingleCamera(Activity activity) {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_QQ_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
//                                .minSelectNum()// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
//                                .previewVideo()// 是否可预览视频 true or false
//                                .enablePreviewAudio() // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
//                                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(3, 2)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
//                                .hideBottomControls()// 是否显示uCrop工具栏，默认不显示 true or false
//                                .isGif()// 是否显示gif图片 true or false
//                                .compressSavePath("/compress")//压缩图片保存地址
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(true)// 是否圆形裁剪 true or false
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia(mediaList)// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                                .cropCompressQuality()// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(false)//同步true或异步false 压缩 默认同步
//                                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                                .videoQuality()// 视频录制质量 0 or 1 int
//                                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
//                                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                                .recordVideoSecond()//视频秒数录制 默认60s int
//                                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.SINGLE);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.SINGLE && resultCode == RESULT_OK) {
            mLocalMedia = PictureSelector.obtainMultipleResult(data);
            if (mLocalMedia.size() > 0) {
                /* 多图片处理*/
                Map<String, RequestBody> partMap = new HashMap<>();
                for (LocalMedia localMedia :
                        mLocalMedia) {
                    File imageFile = new File(localMedia.getCompressPath());
                    if (ObjectUtils.isNotEmpty(localMedia) && !TextUtils.isEmpty(localMedia.getCompressPath())) {
                        RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
                        partMap.put("files\";filename=\"" + imageFile.getName() + "", imageBody);
                    }
                }
                // 上传头像
                RxHttp.getInstance().getSyncServer()
                        .upLoadFile(CommonUtils.getToken(), partMap)
                        .compose(RxSchedulers.observableIO2Main(getActivity()))
                        .subscribe(new ProgressObserver<FileEntity>(getActivity(), true, "") {
                            @Override
                            public void onSuccess(FileEntity result) {
                                LogUtils.e(result.getUrls());
                                //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                                if (null != getActivity()) {
                                    PictureFileUtils.deleteCacheDirFile(getActivity());
                                }
                                uploadAvatar(result.getUrls().get(0));
                            }

                            @Override
                            public void onFailure(Throwable e, String errorMsg) {
                                toast(errorMsg);
                            }
                        });
            }
        }
    }

    /**
     * 设置头像
     *
     * @param s 头像地址
     */
    private void uploadAvatar(String s) {
        Utils.showUserHead(getActivity(), myIvAvatar, s);
        String id = SPUtils.getInstance("account").getString("id", "");
        RCUtils.refreshUserInfo(new UserInfo(id, "患者", Uri.parse(s)));

        RxHttp.getInstance().getSyncServer()
                .setAvatar(CommonUtils.getToken(), new AvatarForm(s))
                .compose(RxSchedulers.observableIO2Main(getActivity()))
                .subscribe(new ProgressObserver<AvatarEntity>(getActivity()) {
                    @Override
                    public void onSuccess(AvatarEntity result) {
                        LogUtils.e("设置头像成功");
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

}
