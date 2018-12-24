package com.baihua.common.rx;


import com.baihua.common.rx.ApiService.SyncServerService;
import com.baihua.common.rx.Converter.FastJsonConverterFactory;
import com.baihua.yaya.BuildConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by JohnsonFan on 2017/7/22.
 */

public class RxHttp {
//    private final String BASE_URL = "http://192.168.2.96:8080/";
    private final String BASE_URL = "http://47.99.243.241/";
    private Map<String, Retrofit> mRetrofitMap = new HashMap<>();

    private RxHttp() {

    }

    /**
     * 单例模式
     *
     * @return
     */
    public static RxHttp getInstance() {
        return RxHttpHolder.sInstance;
    }

    private static class RxHttpHolder {
        private final static RxHttp sInstance = new RxHttp();
    }

    public Retrofit getRetrofit(String serverUrl) {
        Retrofit retrofit;
        if (mRetrofitMap.containsKey(serverUrl)) {
            retrofit = mRetrofitMap.get(serverUrl);
        } else {
            retrofit = createRetrofit(serverUrl);
            mRetrofitMap.put(serverUrl, retrofit);
        }
        return retrofit;
    }

    public SyncServerService getSyncServer() {
        return getRetrofit(BASE_URL).create(SyncServerService.class);
    }


    /**
     * @param baseUrl baseUrl要以/作为结尾  eg：https://github.com/
     * @return
     */
    private Retrofit createRetrofit(String baseUrl) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(BuildConfig.DEBUG
                        ? new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                        : new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE))
                .build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

}
