package com.baihua.common.rx.ApiService;


import com.baihua.common.rx.Response;
import com.baihua.yayayisheng.entity.AvatarEntity;
import com.baihua.yayayisheng.entity.DicEntity;
import com.baihua.yayayisheng.entity.DoctorInfoEntity;
import com.baihua.yayayisheng.entity.EmptyEntity;
import com.baihua.yayayisheng.entity.FileEntity;
import com.baihua.yayayisheng.entity.HospitalEntity;
import com.baihua.yayayisheng.entity.OfficeEntity;
import com.baihua.yayayisheng.entity.PatientListEntity;
import com.baihua.yayayisheng.entity.VisitDetailsEntity;
import com.baihua.yayayisheng.entity.form.AvatarForm;
import com.baihua.yayayisheng.entity.form.LoginForm;
import com.baihua.yayayisheng.entity.TokenEntity;
import com.baihua.yayayisheng.entity.VerificationEntity;
import com.baihua.yayayisheng.entity.form.PatientListForm;
import com.baihua.yayayisheng.entity.form.RegisterForm;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SyncServerService {

    /**
     * 样例
     *
     * @param SoftwareID
     * @param ClientVersion
     * @return
     */
    @GET("service/mobile/IsLatestVersion.ashx")
    Observable<Response<String>> getLatestVersion(@Query("SoftwareID") String SoftwareID,
                                                  @Query("ClientVersion") String ClientVersion);

    /**
     * 多图/单图/音频上传
     *
     * @param params 参数
     * @return 结果
     */
    @Multipart
    @POST("/rest/upload/android")
    Observable<Response<FileEntity>> uploadFile(@PartMap Map<String, RequestBody> params);

    /**
     * 登录
     *
     * @param loginForm 登录所需参数
     * @return 结果
     */
    @POST("/rest/login/doctor")
    Observable<Response<TokenEntity>> login(@Body LoginForm loginForm);

    /**
     * 注册
     *
     * @param registerForm 所需参数
     * @return 结果
     */
    @POST("/rest/login/doctor/register")
    Observable<Response<EmptyEntity>> register(@Body RegisterForm registerForm);

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return 结果
     */
    @GET("/rest/login/verification/{phone}")
    Observable<Response<VerificationEntity>> getVerificationCode(@Path("phone") String phone);

    /**
     * 获取医院
     *
     * @return 结果
     */
    @GET("/rest/bashospital/list")
    Observable<Response<HospitalEntity>> getHospitalList();

    /**
     * 获取科室
     *
     * @return 结果
     */
    @GET("/rest/basoffice/list")
    Observable<Response<OfficeEntity>> getOfficeList();

    /**
     * 获取字典
     *
     * @param groupId 获取字典 1 睡眠 2 食欲 3 职称
     * @return 结果
     */
    @GET("/rest/basdictionary/list/{groupId}")
    Observable<Response<DicEntity>> getDictionary(@Path("groupId") String groupId);

    /**
     * 问诊列表
     *
     * @return 结果
     */
    @POST("/rest/serinquiry/patientList")
    Observable<Response<PatientListEntity>> getPatientList(@Header("token") String token, @Body PatientListForm patientListForm);

    /**
     * 用户问诊详细
     *
     * @return 结果
     */
    @GET("/rest/serinquiry/info/{id}")
    Observable<Response<VisitDetailsEntity>> getVisitDetails(@Header("token") String token, @Path("id") String id);

    /**
     * 设置头像
     *
     * @param avatarForm 照片路径
     * @return 结果
     */
    @POST("/rest/patient/phone")
    Observable<Response<AvatarEntity>> setAvatar(@Header("token") String token, @Body AvatarForm avatarForm);

    /**
     * 获取头像
     *
     * @return 结果
     */
    @GET("/rest/patient/photo")
    Observable<Response<AvatarEntity>> getAvatar(@Header("token") String token);

    /**
     * 获取医生信息
     *
     * @return 结果
     */
    @GET("/rest/usdoctor/info")
    Observable<Response<DoctorInfoEntity>> getDoctorInfo(@Header("token") String token);
}
