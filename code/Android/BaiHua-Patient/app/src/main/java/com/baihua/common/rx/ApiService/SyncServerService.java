package com.baihua.common.rx.ApiService;


import com.baihua.common.rx.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SyncServerService {

    @GET("service/mobile/IsLatestVersion.ashx")
    Observable<Response<String>> getLatestVersion(@Query("SoftwareID") String SoftwareID,
                                                  @Query("ClientVersion") String ClientVersion);
}
