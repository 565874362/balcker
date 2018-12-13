package com.baihua.yayayisheng.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.util.Utils;
import com.baihua.yayayisheng.widget.ValidateCodeView;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:3/12/2018 15:22
 * Description: 注册
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.register_et_name)
    EditText registerEtName;
    @BindView(R.id.register_rb_female)
    RadioButton registerRbFemale;
    @BindView(R.id.register_rb_male)
    RadioButton registerRbMale;
    @BindView(R.id.register_rb_group)
    RadioGroup registerRbGroup;
    @BindView(R.id.register_et_company)
    EditText registerEtCompany;
    @BindView(R.id.register_et_job)
    EditText registerEtJob;
    @BindView(R.id.register_sp_department)
    AppCompatSpinner registerSpDepartment;
    @BindView(R.id.register_et_registration_fee)
    EditText registerEtRegistrationFee;
    @BindView(R.id.register_et_good_at_one)
    EditText registerEtGoodAtOne;
    @BindView(R.id.register_et_good_at_one_desc)
    EditText registerEtGoodAtOneDesc;
    @BindView(R.id.register_et_good_at_two)
    EditText registerEtGoodAtTwo;
    @BindView(R.id.register_et_good_at_two_desc)
    EditText registerEtGoodAtTwoDesc;
    @BindView(R.id.register_tv_good_at_three)
    EditText registerTvGoodAtThree;
    @BindView(R.id.register_tv_good_at_three_desc)
    EditText registerTvGoodAtThreeDesc;
    @BindView(R.id.register_tv_upload_certificate)
    TextView registerTvUploadCertificate;
    @BindView(R.id.register_tv_upload_front_id_card)
    TextView registerTvUploadFrontIdCard;
    @BindView(R.id.register_tv_upload_back_id_card)
    TextView registerTvUploadBackIdCard;
    @BindView(R.id.register_et_mobile_no)
    EditText registerEtMobileNo;
    @BindView(R.id.register_tv_code)
    EditText registerTvCode;
    @BindView(R.id.register_tv_register)
    TextView registerTvRegister;
    @BindView(R.id.register_tv_login)
    TextView registerTvLogin;
    @BindView(R.id.register_iv_certificate)
    ImageView registerIvCertificate;
    @BindView(R.id.register_iv_front_id_card)
    ImageView registerIvFrontIdCard;
    @BindView(R.id.register_iv_back_id_card)
    ImageView registerIvBackIdCard;
    @BindView(R.id.register_tv_get_code)
    ValidateCodeView registerTvGetCode;
    @BindView(R.id.register_iv_yourself)
    ImageView registerIvYourself;
    @BindView(R.id.register_tv_upload_yourself)
    TextView registerTvUploadYourself;

    private List<LocalMedia> mReturnList = new ArrayList<>();
    private String uploadType;

    @Override
    public void setLayout() {
        mIbLeft.setVisibility(View.GONE);
        setTitle(getString(R.string.login_register));
        setContentView(R.layout.activity_register);
        registerTvGetCode.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerTvGetCode.onDestroy();
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
    }

    @Override
    public void setListener() {
        registerTvGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTvGetCode.start();
            }
        });
    }

    private void showSingleCamera(Activity activity, List<LocalMedia> mediaList) {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
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
//                                .circleDimmedLayer()// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(true)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false)// 是否开启点击声音 true or false
                .selectionMedia(mediaList)// 是否传入已选图片 List<LocalMedia> list
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


    @OnClick({R.id.register_tv_upload_yourself, R.id.register_tv_upload_certificate, R.id.register_tv_upload_front_id_card, R.id.register_tv_upload_back_id_card,
            R.id.register_tv_code, R.id.register_tv_register, R.id.register_tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_tv_upload_yourself:
                uploadType = "yourself";
                showSingleCamera(RegisterActivity.this, new ArrayList<>());
                break;
            case R.id.register_tv_upload_certificate:
                uploadType = "certificate";
                showSingleCamera(RegisterActivity.this, new ArrayList<>());
                break;
            case R.id.register_tv_upload_front_id_card:
                uploadType = "front_id_card";
                showSingleCamera(RegisterActivity.this, new ArrayList<>());
                break;
            case R.id.register_tv_upload_back_id_card:
                uploadType = "back_id_card";
                showSingleCamera(RegisterActivity.this, new ArrayList<>());
                break;
            case R.id.register_tv_code:
                break;
            case R.id.register_tv_register:
                toast("register");
                ActivityUtils.startActivity(LoginActivity.class);
                finish();
                break;
            case R.id.register_tv_login:
                ActivityUtils.startActivity(LoginActivity.class);
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.SINGLE && resultCode == RESULT_OK) {
            List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
            switch (uploadType) {
                case "yourself":
                    Utils.showImg(RegisterActivity.this, localMediaList.get(0).getCompressPath(), registerIvYourself);
                    break;
                case "certificate":
                    Utils.showImg(RegisterActivity.this, localMediaList.get(0).getCompressPath(), registerIvCertificate);
                    break;
                case "front_id_card":
                    Utils.showImg(RegisterActivity.this, localMediaList.get(0).getCompressPath(), registerIvFrontIdCard);
                    break;
                case "back_id_card":
                    Utils.showImg(RegisterActivity.this, localMediaList.get(0).getCompressPath(), registerIvBackIdCard);
                    break;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                InputMethodManager imm =
                        (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS
                );
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // Return whether touch the view.
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            return !(event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom);
        }
        return false;
    }

}
