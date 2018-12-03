package com.joymates.common.http;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.fastjson.JSONObject;
import com.blankj.utilcode.util.LogUtils;
import com.joymates.flowers.R;
import com.joymates.flowers.util.Utils;
import com.joymates.flowers.xml.TokenXML;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.io.File;
import java.util.List;
import java.util.Map;

//import org.json.JSONObject;


/**
 * ProjectName：TuanLeApp
 * PackageName：com.joymates.tuanle.http
 * ClassDescribe：
 * CreaterBy：SongGang
 * CreateDate：2017/12/18 9:29
 * Remark：
 */
public class HttpRequest {

    public static int REQUEST_TYPE_CLASS = 0;
    public static int REQUEST_TYPE_LIST = 1;
    public static int REQUEST_TYPE_JSON = 2;

    // 加载动画
    private static ZLoadingDialog loadingDialog;

    private static MaterialDialog materialDialog;

    public static void getByOkGo(final Context context, final Handler handler, final String url, Map<String, String>
            map, final Class<?> clazz, final int type, final int successState, final int errorState) {
        getByOkGo(context, handler, url, map, clazz, type, successState, errorState, false);
    }

    public static void getByOkGo(final Context context, final Handler handler, final String url, Map<String, String>
            map, final Class<?> clazz, final int type, final int successState, final int errorState, boolean
                                         isShowLoading) {
        getByOkGo(context, handler, url, map, clazz, type, successState, errorState, isShowLoading, false);
    }

    /**
     * @param context
     * @param handler
     * @param url
     * @param map
     * @param clazz         实体类
     * @param type          类型 0-实体  1-列表
     * @param successState
     * @param errorState
     * @param isShowLoading 是否显示加载圈
     * @param isCache       是否缓存
     */
    public static void getByOkGo(final Context context, final Handler handler, final String url, Map<String, String>
            map, final Class<?> clazz, final int type, final int successState, final int errorState, boolean
                                         isShowLoading, boolean isCache) {

        if (!Utils.isInternetAvailable(context) && !isCache) {
            Message message = new Message();
            message.what = errorState;
            message.obj = "网络不给力";
            if (null != handler)
                handler.sendMessage(message);
            return;
        }
        if (isShowLoading)
            showLoading(context);

        LogUtils.e(url + (map == null ? "" : map.toString()));

        LogUtils.e("token=" + TokenXML.getToken());
        Request request = OkGo.<String>get(url)
                .headers("token", TokenXML.getToken())
                .tag(context) // 请求的 tag, 主要用于取消对应的请求
                .params(map);

        if (isCache) {
            request = request.cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST);//缓存模式
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (null != loadingDialog) {
                        loadingDialog.dismiss();
                        loadingDialog = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(activityIsFinish(context)){
                    return;
                }

                LogUtils.e(response.body());
                Message message = new Message();
                message.what = successState;
                if (REQUEST_TYPE_CLASS == type) {
                    message.obj = JSONObject.parseObject(response.body(), clazz);
                } else if (REQUEST_TYPE_LIST == type) {
                    message.obj = JSONObject.parseArray(response.body(), clazz);
                }

                //判断令牌是否有效
                checkTokenSuccess(context, response.body());

                if (null != handler)
                    handler.sendMessage(message);

            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);
                if(activityIsFinish(context)){
                    return;
                }
                Message message = new Message();
                message.what = successState;
                if (REQUEST_TYPE_CLASS == type) {
                    message.obj = JSONObject.parseObject(response.body(), clazz);
                } else if (REQUEST_TYPE_LIST == type) {
                    message.obj = JSONObject.parseArray(response.body(), clazz);
                }
                if (null != handler)
                    handler.sendMessage(message);
            }

            @Override
            public void onError(Response<String> response) {
                try {
                    if (null != loadingDialog) {
                        loadingDialog.dismiss();
                        loadingDialog = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(activityIsFinish(context)){
                    return;
                }

                LogUtils.e(response.body());
                Message message = new Message();
                message.what = errorState;
                message.obj = "网络不给力";
                if (null != handler)
                    handler.sendMessage(message);
            }
        });

    }

    private static void showLoading(Context context) {
        if(context instanceof Activity){
            if(((Activity)context).isFinishing()||((Activity)context).isDestroyed()){
                return;
            }
        }
        /*显示加载动画*/
        loadingDialog = new ZLoadingDialog(context);
        loadingDialog.setLoadingBuilder(Z_TYPE.STAR_LOADING)
                .setLoadingColor(context.getResources().getColor(R.color.color_font_app))
                .setHintText("Loading...")
                .setHintTextSize(16)
                .setHintTextColor(context.getResources().getColor(R.color.color_font_app))
                .setCanceledOnTouchOutside(false)
                .setCancelable(true)
                .show();
    }

    public static void post(final Context context, final Handler handler, final String url, final org.json.JSONObject
            jb, final Class<?> clazz, final int type, final int successState, final int errorState) {
        post(context, handler, url, jb.toString(), clazz, type, successState, errorState, false);
    }

    public static void post(final Context context, final Handler handler, final String url, final org.json.JSONObject
            jb, final Class<?> clazz, final int type, final int successState, final int errorState, boolean
                                    isShowLoading) {
        post(context, handler, url, jb.toString(), clazz, type, successState, errorState, isShowLoading);
    }

    public static void post(final Context context, final Handler handler, final String url, final String
            jb, final Class<?> clazz, final int type, final int successState, final int errorState) {
        post(context, handler, url, jb, clazz, type, successState, errorState, false);
    }

    public static void post(final Context context, final Handler handler, final String url, final String param,
                            final Class<?> clazz, final int type, final int successState, final int errorState,
                            boolean isShowLoading) {
        post(context, handler, url, param, clazz, type, successState, errorState, isShowLoading, false);
    }

    public static void post(final Context context, final Handler handler, final String url, final String param,
                            final Class<?> clazz, final int type, final int successState, final int errorState,
                            boolean isShowLoading, boolean isCache) {
        if (!Utils.isInternetAvailable(context) && !isCache) {
            Message message = new Message();
            message.what = errorState;
            message.obj = "网络不给力";
            if (null != handler)
                handler.sendMessage(message);
            return;
        }
        if (isShowLoading)
            showLoading(context);

        LogUtils.e(url + "参数：" + param);
        LogUtils.e("token=" + TokenXML.getToken());
        Request request = OkGo.<String>post(url)//
                .tag(context)//
                .headers("token", TokenXML.getToken())
                .upJson(param)//这里不要使用params，upJson 与 params 是互斥的，只有 upJson 的数据会被上传
                .isMultipart(true);         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
        if (isCache) {
            request = request.cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST);//缓存模式
        }
        request.execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (null != loadingDialog) {
                        loadingDialog.dismiss();
                        loadingDialog = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(activityIsFinish(context)){
                    return;
                }

                LogUtils.e(response.body());
                Message message = new Message();
                message.what = successState;
                if (REQUEST_TYPE_CLASS == type) {
                    message.obj = JSONObject.parseObject(response.body(), clazz);
                } else if (REQUEST_TYPE_LIST == type) {
                    message.obj = JSONObject.parseArray(response.body(), clazz);
                } else {
                    message.obj = response.body();
                }

                //判断令牌是否有效
                checkTokenSuccess(context, response.body());


                if (handler != null) {
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                super.onCacheSuccess(response);

                if(activityIsFinish(context)){
                    return;
                }
                Message message = new Message();
                message.what = successState;
                if (REQUEST_TYPE_CLASS == type) {
                    message.obj = JSONObject.parseObject(response.body(), clazz);
                } else if (REQUEST_TYPE_LIST == type) {
                    message.obj = JSONObject.parseArray(response.body(), clazz);
                }
                if (handler != null) {
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onError(Response<String> response) {
                try {
                    if (null != loadingDialog) {
                        loadingDialog.dismiss();
                        loadingDialog = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(activityIsFinish(context)){
                    return;
                }

//                        response.getException().getMessage();
                LogUtils.e(response.getException().getMessage());
                Message message = new Message();
                message.what = errorState;
                message.obj = "网络不给力";
                if (null != handler)
                    handler.sendMessage(message);
            }
        });
    }

    public static boolean activityIsFinish(Context context){
        if(context==null){
            return true;
        }
        if(context instanceof Activity){
            if(((Activity) context).isFinishing()||((Activity) context).isDestroyed()){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断令牌是否有效
     *
     * @param context
     * @param data
     */
    public static void checkTokenSuccess(final Context context, String data) {
        //判断令牌是否有效
//        BaseVO vo = JSONObject.parseObject(data, BaseVO.class);
//        if (vo.getCode() == ResultCode.CODE_405 || vo.getCode() == ResultCode.CODE_403) {
//            //令牌无效，去重新登录
//
//            if (materialDialog != null && materialDialog.isShowing()) {
//                return;
//            }
//            TuanleUtils.jPushDeleteAlias(context);
//            materialDialog = MaterialDialogUtils.showNOneBtnDialogOnlyOne(context, R.string.token_failure, new
//                    MaterialDialog.SingleButtonCallback() {
//                        @Override
//                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                            Delete.tables(MemberVO.class);
//                            CacheUtils.getInstance().clear();
//                            SPUtils.getInstance().clear();
//                            ActivityUtils.finishAllActivities();
//                            Utils.gotoActivity((Activity) context, MainFragmentActivity.class, false, null, null);
//                            Utils.goLogin(context);
//                        }
//                    });
//        }
    }

    public static void delete(Context context, String url, final org.json.JSONObject jb) {
        OkGo.<String>delete(url)//
                .tag(context)//
                .headers("header1", "headerValue1")//
                .upString(jb.toString())//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    }

                    @Override
                    public void onError(Response<String> response) {
                    }
                });
    }

    public static void put(Context context, String url, final org.json.JSONObject jb) {
        OkGo.<String>put(url)//
                .tag(context)//
                .headers("header1", "headerValue1")//
//                .params("param1", "paramValue1")//
                .upString(jb.toString())//
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    }

                    @Override
                    public void onError(Response<String> response) {
                    }
                });
    }


    /**
     * 文件上传
     *
     * @param context
     * @param handler
     * @param url           上传地址
     * @param param         type
     * @param file          文件
     * @param clazz         返回结果
     * @param type          返回类型
     * @param successState  成功码
     * @param errorState    失败码
     * @param isShowLoading 是否loading
     */
    public static void upFile(final Context context, final Handler handler, final String url, final Map<String,
            String> param, final File file,
                              final Class<?> clazz, final int type, final int successState, final int errorState,
                              boolean isShowLoading) {

        if (!Utils.isInternetAvailable(context)) {
            Message message = new Message();
            message.what = errorState;
            message.obj = "网络不给力";
            if (null != handler)
                handler.sendMessage(message);
            return;
        }

        if (isShowLoading)
            showLoading(context);

        LogUtils.e(url + "参数：" + param);
        OkGo.<String>post(url)//
                .tag(context)//
                .params(param)
                .params("file", file)
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null != loadingDialog) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        LogUtils.e(response.body());
                        Message message = new Message();
                        message.what = successState;
                        if (REQUEST_TYPE_CLASS == type) {
                            message.obj = JSONObject.parseObject(response.body(), clazz);
                        } else if (REQUEST_TYPE_LIST == type) {
                            message.obj = JSONObject.parseArray(response.body(), clazz);
                        }

                        handler.sendMessage(message);
                    }


                    @Override
                    public void onError(Response<String> response) {
                        if (null != loadingDialog) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
//                        response.getException().getMessage();
                        LogUtils.e(response.getException().getMessage());
                        Message message = new Message();
                        message.what = errorState;
                        message.obj = "网络不给力";
                        if (null != handler)
                            handler.sendMessage(message);
                    }
                });
    }


    /**
     * 文件批量上传
     *
     * @param context
     * @param handler
     * @param url           上传地址
     * @param param         type
     * @param file          文件集合
     * @param clazz         返回结果
     * @param type          返回类型
     * @param successState  成功码
     * @param errorState    失败码
     * @param isShowLoading 是否loading
     */
    public static void batchUpFile(final Context context, final Handler handler, final String url, final Map<String,
            String> param, final List<File> file,
                                   final Class<?> clazz, final int type, final int successState, final int
                                           errorState, boolean isShowLoading) {

        if (!Utils.isInternetAvailable(context)) {
            Message message = new Message();
            message.what = errorState;
            message.obj = "网络不给力";
            if (null != handler)
                handler.sendMessage(message);
            return;
        }

        if (isShowLoading)
            showLoading(context);

        LogUtils.e(url + "参数：" + param);
        OkGo.<String>post(url)//
                .tag(context)//
                .params(param)
                .addFileParams("file", file)
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (null != loadingDialog) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        LogUtils.e(response.body());
                        Message message = new Message();
                        message.what = successState;
                        if (REQUEST_TYPE_CLASS == type) {
                            message.obj = JSONObject.parseObject(response.body(), clazz);
                        } else if (REQUEST_TYPE_LIST == type) {
                            message.obj = JSONObject.parseArray(response.body(), clazz);
                        }

                        handler.sendMessage(message);
                    }


                    @Override
                    public void onError(Response<String> response) {
                        if (null != loadingDialog) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
//                        response.getException().getMessage();
                        LogUtils.e(response.getException().getMessage());
                        Message message = new Message();
                        message.what = errorState;
                        message.obj = "网络不给力";
                        if (null != handler)
                            handler.sendMessage(message);
                    }
                });
    }


}
