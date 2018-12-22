package com.baihua.common.rx.ApiService;


import com.baihua.common.rx.Response;
import com.baihua.yayayisheng.entity.AccountEntity;
import com.baihua.yayayisheng.entity.AvatarEntity;
import com.baihua.yayayisheng.entity.DiagnoseDateEntity;
import com.baihua.yayayisheng.entity.DiagnoseDateListEntity;
import com.baihua.yayayisheng.entity.DicEntity;
import com.baihua.yayayisheng.entity.DoctorInfoEntity;
import com.baihua.yayayisheng.entity.DoctorRegistrationListEntity;
import com.baihua.yayayisheng.entity.EmptyEntity;
import com.baihua.yayayisheng.entity.ExaminationEntity;
import com.baihua.yayayisheng.entity.FileEntity;
import com.baihua.yayayisheng.entity.HospitalEntity;
import com.baihua.yayayisheng.entity.OfficeEntity;
import com.baihua.yayayisheng.entity.PatientListEntity;
import com.baihua.yayayisheng.entity.RegisteredListEntity;
import com.baihua.yayayisheng.entity.RongCloudToken;
import com.baihua.yayayisheng.entity.TokenEntity;
import com.baihua.yayayisheng.entity.VerificationEntity;
import com.baihua.yayayisheng.entity.VisitDetailsEntity;
import com.baihua.yayayisheng.entity.form.AvatarForm;
import com.baihua.yayayisheng.entity.form.DiagnoseForm;
import com.baihua.yayayisheng.entity.form.ListForm;
import com.baihua.yayayisheng.entity.form.LoginForm;
import com.baihua.yayayisheng.entity.form.PatientListForm;
import com.baihua.yayayisheng.entity.form.RegisterForm;
import com.baihua.yayayisheng.entity.form.ResponseForm;

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
    @POST("/rest/serinquiry/doctorIndex")
    Observable<Response<PatientListEntity>> getPatientList(@Header("token") String token, @Body PatientListForm patientListForm);

    /**
     * 用户问诊详细
     *
     * @return 结果
     */
    @GET("/rest/serinquiry/info/{id}")
    Observable<Response<VisitDetailsEntity>> getVisitDetails(@Header("token") String token, @Path("id") String id);

    /**
     * 接诊
     *
     * @return 结果
     */
    @GET("/rest/serinquiry/accept/{inquiryId}")
    Observable<Response<String>> inquiry(@Header("token") String token, @Path("inquiryId") String inquiryId);

    /**
     * 医生回复
     *
     * @param responseForm 所需参数
     * @return 结果
     */
    @POST("/rest/serinquiry/response")
    Observable<Response<String>> response(@Header("token") String token, @Body ResponseForm responseForm);

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

    /**
     * 医生信息更新
     *
     * @return 结果
     */
    @POST("/rest/usdoctor/update")
    Observable<Response<EmptyEntity>> updateDoctorInfo(@Header("token") String token, @Body RegisterForm registerForm);

    /**
     * 获取接诊周期
     *
     * @return 结果
     */
    @GET("/rest/serschedule/diagnoseDates")
    Observable<Response<DiagnoseDateEntity>> getDiagnoseDates(@Header("token") String token);

    /**
     * 健康检查项目
     *
     * @return 结果
     */
    @GET("/rest/bashealthexamination/list")
    Observable<Response<ExaminationEntity>> getExaminations(@Header("token") String token);

    /**
     * 获取接诊时间
     *
     * @return 结果
     */
    @GET("/rest/serschedule/diagnoseList")
    Observable<Response<DiagnoseDateListEntity>> getDiagnoseList(@Header("token") String token);

    /**
     * 增加接诊
     *
     * @return 结果
     */
    @POST("/rest/serschedule/addDiagnose")
    Observable<Response<EmptyEntity>> addDiagnose(@Header("token") String token, @Body DiagnoseForm diagnoseForm);

    /**
     * 医生接诊列表
     *
     * @return 结果
     */
    @POST("/rest/serregistration/doctorListRegistration")
    Observable<Response<DoctorRegistrationListEntity>> getReceptionList(@Header("token") String token, @Body ListForm listForm);

    /**
     * Token获取
     *
     * @return 结果
     */
    @GET("/rest/chat/token")
    Observable<Response<RongCloudToken>> getToken(@Header("token") String token);

    /**
     * 获取账户信息
     *
     * @return 结果
     */
    @GET("/rest/chat/info/{accountId}")
    Observable<Response<AccountEntity>> getAccount(@Header("token") String token, @Path("accountId") String accountId);

}
