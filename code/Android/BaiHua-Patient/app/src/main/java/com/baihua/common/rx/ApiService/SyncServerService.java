package com.baihua.common.rx.ApiService;


import com.baihua.common.rx.Response;
import com.baihua.yaya.entity.AdEntity;
import com.baihua.yaya.entity.AvatarEntity;
import com.baihua.yaya.entity.CommentEntity;
import com.baihua.yaya.entity.DiagnoseEntity;
import com.baihua.yaya.entity.DicEntity;
import com.baihua.yaya.entity.DoctorEntity;
import com.baihua.yaya.entity.DoctorInfoEntity;
import com.baihua.yaya.entity.EmptyEntity;
import com.baihua.yaya.entity.FileEntity;
import com.baihua.yaya.entity.MatchDoctorsEntity;
import com.baihua.yaya.entity.PatientListEntity;
import com.baihua.yaya.entity.RegisteredEntity;
import com.baihua.yaya.entity.RegisteredListEntity;
import com.baihua.yaya.entity.Token;
import com.baihua.yaya.entity.Verification;
import com.baihua.yaya.entity.VisitDetailsEntity;
import com.baihua.yaya.entity.form.AdForm;
import com.baihua.yaya.entity.form.AvatarForm;
import com.baihua.yaya.entity.form.CommentForm;
import com.baihua.yaya.entity.form.DoctorForm;
import com.baihua.yaya.entity.form.LoginForm;
import com.baihua.yaya.entity.form.PatientListForm;
import com.baihua.yaya.entity.form.PublishCommentForm;
import com.baihua.yaya.entity.form.RegisteredForm;
import com.baihua.yaya.entity.form.VisitForm;

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
    Observable<Response<FileEntity>> upLoadFile(@Header("token") String token, @PartMap Map<String, RequestBody> params);

    /**
     * 登录
     *
     * @param loginForm 登录所需参数
     * @return 结果
     */
    @POST("rest/login/patient")
    Observable<Response<Token>> login(@Body LoginForm loginForm);

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @return 结果
     */
    @GET("/rest/login/verification/{phone}")
    Observable<Response<Verification>> getVerificationCode(@Path("phone") String phone);

    /**
     * 广告
     *
     * @param adForm 所需参数
     * @return 结果
     */
    @POST("/rest/basad/list")
    Observable<Response<AdEntity>> getAdList(@Body AdForm adForm);

    /**
     * 医生列表
     *
     * @param doctorForm 所需参数
     * @return 结果
     */
    @POST("/rest/usdoctor/list")
    Observable<Response<DoctorEntity>> getDoctorList(@Body DoctorForm doctorForm);

    /**
     * 获取医生信息
     *
     * @param doctorId 医生ID
     * @return 结果
     */
    @GET("/rest/usdoctor/info/{doctorId}")
    Observable<Response<DoctorInfoEntity>> getDoctorInfo(@Path("doctorId") String doctorId);

    /**
     * 医生评论列表
     *
     * @param commentForm 所需参数
     * @return 结果
     */
    @POST("/rest/sercomment/doctorList")
    Observable<Response<CommentEntity>> getDoctorCommentList(@Body CommentForm commentForm);

    /**
     * 发表评论
     *
     * @param publishCommentForm 所需参数
     * @return 结果
     */
    @POST("/rest/sercomment/patientCommit")
    Observable<Response<EmptyEntity>> publishComment(@Header("token") String token, @Body PublishCommentForm publishCommentForm);

    // ************************************** 问诊 *****************************************

    /**
     * 问诊提交
     *
     * @param visitForm 所需参数
     * @return 结果
     */
    @POST("/rest/serinquiry/save")
    Observable<Response<MatchDoctorsEntity>> submitVisit(@Header("token") String token, @Body VisitForm visitForm);

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
     * 获取字典
     *
     * @param groupId 获取字典 1 睡眠 2 食欲 3 职称
     * @return 结果
     */
    @GET("/rest/basdictionary/list/{groupId}")
    Observable<Response<DicEntity>> getDictionary(@Path("groupId") String groupId);

    // ************************************** 患者头像 *****************************************

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

    // ************************************** 挂号 *****************************************

    /**
     * 挂号列表
     *
     * @return 结果
     */
    @POST("/rest/serregistration/patientList")
    Observable<Response<RegisteredListEntity>> getRegisteredList(@Header("token") String token, @Body PatientListForm patientListForm);

    /**
     * 挂号详情
     *
     * @return 结果
     */
    @GET("/rest/serregistration/info/{id}")
    Observable<Response<RegisteredListEntity>> getRegisteredDetails(@Header("token") String token, @Path("id") String id);

    /**
     * 挂号
     *
     * @return 结果
     */
    @POST("/rest/serregistration/registration")
    Observable<Response<RegisteredEntity>> registered(@Header("token") String token, @Body RegisteredForm registeredForm);

    /**
     * 出诊时间
     *
     * @return 结果
     */
    @GET("/rest/serschedule/diagnoseList/{doctorId}")
    Observable<Response<DiagnoseEntity>> getDiagnoseList(@Header("token") String token, @Path("doctorId") String doctorId);
}
