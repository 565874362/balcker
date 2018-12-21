package com.baihua.yayayisheng.my;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.entity.DicEntity;
import com.baihua.yayayisheng.entity.DoctorInfoEntity;
import com.baihua.yayayisheng.entity.EmptyEntity;
import com.baihua.yayayisheng.entity.FileEntity;
import com.baihua.yayayisheng.entity.HospitalEntity;
import com.baihua.yayayisheng.entity.OfficeEntity;
import com.baihua.yayayisheng.entity.form.RegisterForm;
import com.baihua.yayayisheng.login.SpinnerHospitalAdapter;
import com.baihua.yayayisheng.login.SpinnerOfficeAdapter;
import com.baihua.yayayisheng.login.SpinnerPositionAdapter;
import com.baihua.yayayisheng.util.CommonUtils;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.LogUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Author:byd
 * Time:6/12/2018 16:28
 * Description: 我的信息编辑页面
 */
public class MyInfoEditActivity extends BaseActivity {

    @BindView(R.id.register_et_name)
    EditText registerEtName;
    @BindView(R.id.register_rb_female)
    RadioButton registerRbFemale;
    @BindView(R.id.register_rb_male)
    RadioButton registerRbMale;
    @BindView(R.id.register_rb_group)
    RadioGroup registerRbGroup;
    @BindView(R.id.register_sp_hospital)
    AppCompatSpinner registerSpHospital;
    @BindView(R.id.register_sp_position)
    AppCompatSpinner registerSpPosition;
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
    @BindView(R.id.register_iv_certificate)
    ImageView registerIvCertificate;
    @BindView(R.id.register_iv_front_id_card)
    ImageView registerIvFrontIdCard;
    @BindView(R.id.register_iv_back_id_card)
    ImageView registerIvBackIdCard;
    @BindView(R.id.register_iv_yourself)
    ImageView registerIvYourself;
    @BindView(R.id.register_tv_upload_yourself)
    TextView registerTvUploadYourself;
    @BindView(R.id.tv_submit)
    TextView mTvSubmit; // 提交

    private String uploadType;

    private int mGender = 0; // 性别 1 男 0 女
    private HospitalEntity.ListBean selectHosBean; // 被选中的单位
    private DicEntity.DictionariesBean selectPosBean; // 被选中的职位
    private OfficeEntity.ListBean selectOfficeBean; // 被选中的科室
    private String mYourSelfImagePath = ""; // 本人照片地址
    private String mCertificateImagePath = ""; // 医师资格证照片地址
    private String mFrontCardImagePath = ""; // 正面身份证照片地址
    private String mBackCardImagePath = ""; // 反面身份证照片地址

    private DoctorInfoEntity.InfoBean mDoctorInfo; // 医生信息

    @Override
    public void setLayout() {
        setTitle("编辑个人信息");
        setContentView(R.layout.activity_my_info_edit);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
//        mTvSubmit.setVisibility(View.VISIBLE);// TODO: 20/12/2018 编辑功能
        if (getIntent().hasExtra("doctor")) {
            mDoctorInfo = (DoctorInfoEntity.InfoBean) getIntent().getSerializableExtra("doctor");
            getHospital();
            getPositionList();
            getOfficeList();
            setContentData();
        }

    }

    /**
     * 数据回填
     */
    private void setContentData() {
        registerEtName.setText(mDoctorInfo.getName());
        // 1 男 0 女
        if (mDoctorInfo.getGender().equals("0"))
            registerRbFemale.setSelected(true);
        else if (mDoctorInfo.getGender().equals("1"))
            registerRbMale.setSelected(true);
        registerEtRegistrationFee.setText(Utils.keep2DecimalDigits(mDoctorInfo.getRegistrationFee()));
        List<DoctorInfoEntity.InfoBean.AdeptEntitiesBean> adeptEntities = mDoctorInfo.getAdeptEntities();
        for (int i = 0; i < adeptEntities.size(); i++) { // 擅长
            DoctorInfoEntity.InfoBean.AdeptEntitiesBean adeptEntitiesBean = adeptEntities.get(i);
            if (i == 0) {
                registerEtGoodAtOne.setText(adeptEntitiesBean.getName());
                registerEtGoodAtOneDesc.setText(adeptEntitiesBean.getDescribe());
            } else if (i == 1) {
                registerEtGoodAtTwo.setText(adeptEntitiesBean.getName());
                registerEtGoodAtTwoDesc.setText(adeptEntitiesBean.getDescribe());
            } else if (i == 2) {
                registerTvGoodAtThree.setText(adeptEntitiesBean.getName());
                registerTvGoodAtThreeDesc.setText(adeptEntitiesBean.getDescribe());
            }
        }
        Utils.showImg(this, mDoctorInfo.getPhoto(), registerIvYourself);
        mYourSelfImagePath = mDoctorInfo.getPhoto();
        Utils.showImg(this, mDoctorInfo.getPhysicianLicence(), registerIvCertificate);
        mCertificateImagePath = mDoctorInfo.getPhysicianLicence();
        List<String> idCards = Arrays.asList(mDoctorInfo.getIdentityCard().split(","));
        Utils.showImg(this, idCards.get(0), registerIvFrontIdCard);
        mFrontCardImagePath = idCards.get(0);
        Utils.showImg(this, idCards.get(1), registerIvBackIdCard);
        mBackCardImagePath = idCards.get(1);
    }

    /**
     * 获取医院(单位)
     */
    private void getHospital() {
        RxHttp.getInstance().getSyncServer()
                .getHospitalList()
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<HospitalEntity>(this) {
                    @Override
                    public void onSuccess(HospitalEntity result) {
                        SpinnerHospitalAdapter spinnerHospitalAdapter = new SpinnerHospitalAdapter(MyInfoEditActivity.this, result.getList());
                        registerSpHospital.setAdapter(spinnerHospitalAdapter);
                        for (int i = 0; i < result.getList().size(); i++) {
                            if (mDoctorInfo.getHosId().equals(result.getList().get(i).getId())) {
                                registerSpHospital.setSelection(i);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    /**
     * 获取职位
     */
    private void getPositionList() {
        RxHttp.getInstance().getSyncServer()
                .getDictionary("3")
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<DicEntity>(this) {

                    @Override
                    public void onSuccess(DicEntity result) {
                        SpinnerPositionAdapter spinnerPositionAdapter = new SpinnerPositionAdapter(MyInfoEditActivity.this, result.getDictionaries());
                        registerSpPosition.setAdapter(spinnerPositionAdapter);
                        for (int i = 0; i < result.getDictionaries().size(); i++) {
                            if (mDoctorInfo.getPositionId().equals(result.getDictionaries().get(i).getId())) {
                                registerSpPosition.setSelection(i);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    /**
     * 获取科室
     */
    private void getOfficeList() {
        RxHttp.getInstance().getSyncServer()
                .getOfficeList()
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<OfficeEntity>(this) {
                    @Override
                    public void onSuccess(OfficeEntity result) {
                        SpinnerOfficeAdapter spinnerOfficeAdapter = new SpinnerOfficeAdapter(MyInfoEditActivity.this, result.getList());
                        registerSpDepartment.setAdapter(spinnerOfficeAdapter);
                        for (int i = 0; i < result.getList().size(); i++) {
                            if (mDoctorInfo.getOffId().equals(result.getList().get(i).getId())) {
                                registerSpDepartment.setSelection(i);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    @Override
    public void setListener() {

        registerSpHospital.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter().getItem(position) instanceof String)
                    return;
                selectHosBean = (HospitalEntity.ListBean) parent.getAdapter().getItem(position);
                LogUtils.e(selectHosBean.getName() + " : " + selectHosBean.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        registerSpPosition.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter().getItem(position) instanceof String)
                    return;
                selectPosBean = (DicEntity.DictionariesBean) parent.getAdapter().getItem(position);
                LogUtils.e(selectPosBean.getName() + " : " + selectPosBean.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerSpDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getAdapter().getItem(position) instanceof String)
                    return;
                selectOfficeBean = (OfficeEntity.ListBean) parent.getAdapter().getItem(position);
                LogUtils.e(selectOfficeBean.getName() + " : " + selectOfficeBean.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        registerRbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.register_rb_female: // 女
                        mGender = 0;
                        break;
                    case R.id.register_rb_male: // 男
                        mGender = 1;
                        break;
                }
            }
        });

    }

    @OnClick({R.id.register_tv_upload_yourself, R.id.register_tv_upload_certificate, R.id.register_tv_upload_front_id_card, R.id.register_tv_upload_back_id_card, R.id.tv_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_tv_upload_yourself:
                uploadType = "yourself";
                showSingleCamera(MyInfoEditActivity.this, new ArrayList<>());
                break;
            case R.id.register_tv_upload_certificate:
                uploadType = "certificate";
                showSingleCamera(MyInfoEditActivity.this, new ArrayList<>());
                break;
            case R.id.register_tv_upload_front_id_card:
                uploadType = "front_id_card";
                showSingleCamera(MyInfoEditActivity.this, new ArrayList<>());
                break;
            case R.id.register_tv_upload_back_id_card:
                uploadType = "back_id_card";
                showSingleCamera(MyInfoEditActivity.this, new ArrayList<>());
                break;
            case R.id.tv_submit:
                judgeEmpty();
                break;
        }
    }

    /**
     * 判空
     */
    boolean y, c, f, b;

    private void judgeEmpty() {
        if (CommonUtils.isTextEmpty(registerEtName)) {
            toast("请输入姓名");
            return;
        }
        if (CommonUtils.isTextEmpty(registerEtRegistrationFee)) {
            toast("请输入挂号费金额");
            return;
        }
        if (CommonUtils.isTextEmpty(registerEtGoodAtOne)) {
            toast("请输入擅长名称");
            return;
        }
        if (CommonUtils.isTextEmpty(registerEtGoodAtOneDesc)) {
            toast("请输入擅长描述");
            return;
        }
        if (CommonUtils.isTextEmpty(registerEtGoodAtTwo)) {
            toast("请输入擅长名称");
            return;
        }
        if (CommonUtils.isTextEmpty(registerEtGoodAtTwoDesc)) {
            toast("请输入擅长描述");
            return;
        }
        if (CommonUtils.isTextEmpty(registerTvGoodAtThree)) {
            toast("请输入擅长名称");
            return;
        }
        if (CommonUtils.isTextEmpty(registerTvGoodAtThreeDesc)) {
            toast("请输入擅长描述");
            return;
        }

        if (TextUtils.isEmpty(mYourSelfImagePath)) {
            toast("请上传本人照片");
            return;
        }
        if (TextUtils.isEmpty(mCertificateImagePath)) {
            toast("请上传医师资格证");
            return;
        }
        if (TextUtils.isEmpty(mFrontCardImagePath)) {
            toast("请上传身份证正面照");
            return;
        }
        if (TextUtils.isEmpty(mYourSelfImagePath)) {
            toast("请上传身份证反面照");
            return;
        }

        List<String> images = new ArrayList<>();

        if (!mYourSelfImagePath.contains("http")) {
            y = true;
            images.add(mYourSelfImagePath);
        }
        if (!mCertificateImagePath.contains("http")) {
            c = true;
            images.add(mCertificateImagePath);
        }
        if (!mFrontCardImagePath.contains("http")) {
            f = true;
            images.add(mFrontCardImagePath);
        }
        if (!mBackCardImagePath.contains("http")) {
            b = true;
            images.add(mBackCardImagePath);
        }
        if (Utils.isListEmpty(images)) {
            updateInfo(new ArrayList<>());
        } else {
            uploadImages(images);
        }
    }

    /**
     * 图片上传
     *
     * @param images 图片地址集合
     */
    private void uploadImages(List<String> images) {
        if (!Utils.isListEmpty(images)) {
            /* 多图片处理*/
            Map<String, RequestBody> partMap = new LinkedHashMap<>();
            for (String image :
                    images) {
                LogUtils.e(image);
                File imageFile = new File(image);
                RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
                partMap.put("files\";filename=\"" + imageFile.getName() + "", imageBody);
            }
            // 上传图片
            RxHttp.getInstance().getSyncServer()
                    .uploadFile(partMap)
                    .compose(RxSchedulers.observableIO2Main(this))
                    .subscribe(new ProgressObserver<FileEntity>(this, true, "") {
                        @Override
                        public void onSuccess(FileEntity result) {
                            LogUtils.e(result.getUrls());
                            // 清除包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
                            PictureFileUtils.deleteCacheDirFile(MyInfoEditActivity.this);
                            updateInfo(result.getUrls());
                        }

                        @Override
                        public void onFailure(Throwable e, String errorMsg) {
                            toast(errorMsg);
                        }
                    });
        }
    }

    /**
     * 提交修改信息
     *
     * @param urls 需要上传的图片
     */
    private void updateInfo(List<String> urls) {
        String photo;
        String idCard;
        String certification;
        if (Utils.isListEmpty(urls)) {
            photo = mYourSelfImagePath;
            certification = mCertificateImagePath;
            idCard = mFrontCardImagePath + "," + mBackCardImagePath;
        } else {
            for (int i = 0; i < urls.size(); i++) {
                switch (i) {
                    case 0:
                        if (y) {
                            mYourSelfImagePath = urls.get(i);
                        } else if (c) {
                            mCertificateImagePath = urls.get(i);
                        } else if (f) {
                            mFrontCardImagePath = urls.get(i);
                        } else if (b) {
                            mFrontCardImagePath = urls.get(i);
                        }
                        break;
                    case 1:
                        if (c) {
                            mCertificateImagePath = urls.get(i);
                        } else if (f) {
                            mFrontCardImagePath = urls.get(i);
                        } else if (b) {
                            mFrontCardImagePath = urls.get(i);
                        }
                        break;
                    case 2:
                        if (f) {
                            mFrontCardImagePath = urls.get(i);
                        } else if (b) {
                            mFrontCardImagePath = urls.get(i);
                        }
                        break;
                    case 3:
                        mFrontCardImagePath = urls.get(i);
                        break;
                }
            }
            photo = mYourSelfImagePath;
            certification = mCertificateImagePath;
            idCard = mFrontCardImagePath + "," + mBackCardImagePath;
        }
        // 擅长
        List<RegisterForm.AdeptsBean> adeptsBeans = new ArrayList<>();
        RegisterForm.AdeptsBean one = new RegisterForm.AdeptsBean();
        RegisterForm.AdeptsBean two = new RegisterForm.AdeptsBean();
        RegisterForm.AdeptsBean three = new RegisterForm.AdeptsBean();
        one.setName(CommonUtils.getTextString(registerEtGoodAtOne));
        one.setDescribe(CommonUtils.getTextString(registerEtGoodAtOneDesc));
        two.setName(CommonUtils.getTextString(registerEtGoodAtTwo));
        two.setDescribe(CommonUtils.getTextString(registerEtGoodAtTwoDesc));
        three.setName(CommonUtils.getTextString(registerTvGoodAtThree));
        three.setDescribe(CommonUtils.getTextString(registerTvGoodAtThreeDesc));
        adeptsBeans.add(one);
        adeptsBeans.add(two);
        adeptsBeans.add(three);
        RegisterForm registerForm = new RegisterForm();
        registerForm.setAccount("");
        registerForm.setCaptchaCode("");
        registerForm.setCaptchaId("");
        registerForm.setGender(String.valueOf(mGender));
        registerForm.setHosId(selectHosBean.getId());
        registerForm.setHosName(selectHosBean.getName());
        registerForm.setIdentityCard(idCard);
        registerForm.setName(CommonUtils.getTextString(registerEtName));
        registerForm.setOffId(selectOfficeBean.getId());
        registerForm.setOffName(selectOfficeBean.getName());
        registerForm.setPhoto(photo);
        registerForm.setPhysicianLicence(certification);
        registerForm.setPositionId(selectPosBean.getId());
        registerForm.setPositionName(selectPosBean.getName());
        registerForm.setRegistrationFee(CommonUtils.getTextString(registerEtRegistrationFee));
        registerForm.setAdepts(adeptsBeans);

        // 更新信息
        RxHttp.getInstance().getSyncServer()
                .updateDoctorInfo(CommonUtils.getToken(), registerForm)
                .compose(RxSchedulers.observableIO2Main(this))
                .subscribe(new ProgressObserver<EmptyEntity>(this, true, "") {
                    @Override
                    public void onSuccess(EmptyEntity result) {
                        finish();
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        toast(errorMsg);
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureConfig.SINGLE && resultCode == RESULT_OK) {
            List<LocalMedia> localMediaList = PictureSelector.obtainMultipleResult(data);
            switch (uploadType) {
                case "yourself":
                    mYourSelfImagePath = localMediaList.get(0).getCompressPath();
                    Utils.showImg(MyInfoEditActivity.this, localMediaList.get(0).getCompressPath(), registerIvYourself);
                    break;
                case "certificate":
                    mCertificateImagePath = localMediaList.get(0).getCompressPath();
                    Utils.showImg(MyInfoEditActivity.this, localMediaList.get(0).getCompressPath(), registerIvCertificate);
                    break;
                case "front_id_card":
                    mFrontCardImagePath = localMediaList.get(0).getCompressPath();
                    Utils.showImg(MyInfoEditActivity.this, localMediaList.get(0).getCompressPath(), registerIvFrontIdCard);
                    break;
                case "back_id_card":
                    mBackCardImagePath = localMediaList.get(0).getCompressPath();
                    Utils.showImg(MyInfoEditActivity.this, localMediaList.get(0).getCompressPath(), registerIvBackIdCard);
                    break;
            }
        }
    }

    private void showSingleCamera(Activity activity, List<LocalMedia> mediaList) {
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_QQ_style)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
//                .maxSelectNum(1)// 最大图片选择数量 int
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

}
