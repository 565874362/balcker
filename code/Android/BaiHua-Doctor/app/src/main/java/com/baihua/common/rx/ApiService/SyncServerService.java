package com.baihua.common.rx.ApiService;


import com.baihua.common.rx.Response;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
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
    @POST("url")
    Observable<Response<String>> upLoadImages(@PartMap Map<String, RequestBody> params);
}
