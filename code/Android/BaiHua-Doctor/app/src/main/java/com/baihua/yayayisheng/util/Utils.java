package com.baihua.yayayisheng.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jaeger.library.StatusBarUtil;
import com.baihua.yayayisheng.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * ProjectName：TuanLeApp
 * PackageName：com.joymates.tuanle.Util
 * ClassDescribe：
 * CreaterBy：SongGang
 * CreateDate：2017/12/11 15:29
 * Remark：
 */
public class Utils {

    /**
     * 加载图片
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void showImg(Context context, String path, ImageView imageView) {
        if (ObjectUtils.isEmpty(context))
            return;
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.img_default)
                .error(R.mipmap.img_default);
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void showCircleImg(Context context, String path, ImageView imageView) {
        if (ObjectUtils.isEmpty(context))
            return;
        if (context instanceof Activity && ((Activity) context).isDestroyed()) {
            return;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new CropCircleTransformation()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(transformDrawable(context, R.mipmap.img_default, new CircleCrop()))
                .error(transformDrawable(context, R.mipmap.img_default, new CircleCrop()));
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    /**
     * Glide加载圆形图片时 设置placeholder、error图片也为圆形
     *
     * @param context
     * @param resourceId
     * @param transformation
     * @return
     */
    private static BitmapDrawable transformDrawable(Context context,
                                                    int resourceId,
                                                    Transformation<Bitmap> transformation) {
        //
        Drawable drawable = ContextCompat.getDrawable(context, resourceId);
        Bitmap bitmap = null;
        bitmap = Bitmap.createBitmap(drawable.getMinimumWidth(), drawable.getMinimumHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        drawable.draw(canvas);
        //make
        Resource<Bitmap> original = BitmapResource.obtain(bitmap, Glide.get(context).getBitmapPool());
        Resource<Bitmap> rounded = transformation.transform(context, original, bitmap.getWidth(), bitmap.getHeight());

        if (!original.equals(rounded)) {
            original.recycle();
        }
        return new BitmapDrawable(context.getResources(), rounded.get());
    }

    /**
     * 用于主题图片展示
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void showImgHeight(final Context context, String path, final ImageView imageView) {
        if (ObjectUtils.isEmpty(context))
            return;

        RequestOptions options = new RequestOptions().centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.img_default)
                .error(R.mipmap.img_default);
        //获取图片显示在ImageView后的宽高

        Glide.with(context).load(path).apply(options).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {

                imageView.setImageDrawable(resource);

                int height = (int) ((float) imageView.getWidth() / resource.getMinimumWidth() * resource
                        .getMinimumHeight());

                ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                layoutParams.height = height;
                imageView.setLayoutParams(layoutParams);

                LogUtils.e("MinimumWidth=" + resource.getMinimumWidth() + "MinimumHeight=" + resource
                        .getMinimumHeight() + "IntrinsicWidth=" + resource.getIntrinsicWidth() + "IntrinsicHeight=" +
                        resource.getIntrinsicHeight());

            }
        });
    }

    /**
     * 加载用户头像（修改了默认图片）
     *
     * @param context
     * @param imageView
     * @param path
     */
    public static void showUserHead(Context context, ImageView imageView, String path) {
        if (ObjectUtils.isEmpty(context))
            return;
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new CropCircleTransformation()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(transformDrawable(context, R.drawable.no_image, new CircleCrop()))
                .error(transformDrawable(context, R.drawable.no_image, new CircleCrop()));
        Glide.with(context).load(path).apply(options).into(imageView);
    }

    /**
     * 获取当前登陆状态
     *
     * @param ctx
     * @return
     */
    public static boolean isLogin(Context ctx) {// 本地loginState中是激活则为登陆状态，或者本地存有角色id为非注册用户
        // TODO 判断登录状态
//返回单个查询结果
//        MemberVO user = getMember();
//        try {
//            return !StringUtils.isTrimEmpty(user.getAccount());
//        } catch (Exception e) {
//            return false;
//        }
        return false;
    }


    /**
     * 到登陆页面
     *
     * @param ctx
     */
    public static void goLogin(Context ctx) {
//        gotoActivity((Activity) ctx, LoginActivity.class, false, null, null);
    }

    /**
     * 设置阿里的矢量图标
     *
     * @param context
     * @param tv
     * @param iconString
     * @param color
     * @param size
     */
    public static void setALiIcon(Context context, TextView tv, int iconString, String color, int size) {
        Typeface CashAndVoucherType = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        tv.setTypeface(CashAndVoucherType);
        tv.setText(iconString);
        tv.setTextColor(Color.parseColor(color));
        tv.setTextSize(size);
    }

    /**
     * Activity之间跳转
     *
     * @param poFrom   所在Activity
     * @param poTo     跳转至的Activity
     * @param pbFinish 是否Finish当前Activity
     * @param lsKey
     * @param pmExtra
     */
    public static void gotoActivity(Activity poFrom, Class<?> poTo,
                                    boolean pbFinish, String lsKey, Object pmExtra) {
        Intent loIntent = new Intent(poFrom, poTo);
        if (pmExtra != null) {
            loIntent.putExtra(lsKey, (Serializable) pmExtra);
        }
        if (pbFinish)
            poFrom.finish();
        poFrom.startActivity(loIntent);
    }

    public static void gotoActivity(Activity poFrom, Class<?> poTo,
                                    boolean pbFinish, String lsKey, Object pmExtra, int flag) {
        Intent loIntent = new Intent(poFrom, poTo);
        loIntent.setFlags(flag);
        if (pmExtra != null) {
            loIntent.putExtra(lsKey, (Serializable) pmExtra);
        }
        if (pbFinish)
            poFrom.finish();
        poFrom.startActivity(loIntent);
    }

    public static void gotoActivityForResult(Activity poFrom, Class<?> poTo,
                                             String lsKey, Object pmExtra, int piRequestCode) {
        Intent loIntent = new Intent(poFrom, poTo);
        if (pmExtra != null) {
            loIntent.putExtra(lsKey, (Serializable) pmExtra);
        }
        poFrom.startActivityForResult(loIntent, piRequestCode);
    }

    /**
     * 设置状态栏为橘色
     *
     * @param activity
     */
    public static void setStatusBarColor(Activity activity) {
        StatusBarUtil.setColor(activity, Color.parseColor("#f36718"), 0);
    }


    public static boolean isHavePermission(Context context, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || ContextCompat.checkSelfPermission(context,
                permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static void AskForPermission(final Activity activity) {
        MaterialDialogUtils.showDialog(activity, activity.getString(R.string.permission_need),
                activity.getString(R.string.common_cancel),
                activity.getString(R.string.permission_settings), new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + AppUtils.getAppPackageName())); // 根据包名打开对应的设置界面
                        activity.startActivity(intent);
                    }
                });
    }

    // 乘法
    public static double mul(double d1, int d2) { // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return to2Decimal(b1.multiply(b2).doubleValue());
    }

    // 乘法
    public static double mul(double d1, double d2) { // 进行乘法运算
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return to2Decimal(b1.multiply(b2).doubleValue());
    }

    // 返回四舍五入后的结果
    public static double to2Decimal(double pfValue) {
        BigDecimal loDecimal = new BigDecimal(pfValue).setScale(2,
                BigDecimal.ROUND_HALF_DOWN);
        return Double.parseDouble(String.valueOf(loDecimal));
    }

    /**
     * 保留一位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String double2String(Double pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(1, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.0";
        }
    }

    public static String double6String(Double pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(6, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.000000";
        }
    }

    public static Double string6Double(String pfValue) {
        try {
            return Double.valueOf(new BigDecimal(pfValue).setScale(6, RoundingMode.DOWN).toString());
        } catch (Exception e) {
            return 0.000000;
        }
    }

    /**
     * 保留一位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String double2String(BigDecimal pfValue) {
        try {
            return String.valueOf(pfValue.setScale(1, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.0";
        }
    }

    /**
     * 保留一位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String double2String(int pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(1, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.0";
        }
    }

    /**
     * 保留一位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String double2String(Float pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(1, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.0";
        }
    }

    /**
     * 保留一位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String double2String(String pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(1, RoundingMode.DOWN).doubleValue());
        } catch (Exception e) {
            return "0.0";
        }
    }

    /**
     * 保留两位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String keep2DecimalDigits(String pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(2, RoundingMode.DOWN));
        } catch (Exception e) {
            return pfValue;
        }
    }

    public static String keep2DecimalDigits(BigDecimal pfValue) {
        try {
            return String.valueOf(pfValue.setScale(2, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * 保留两位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String keep2DecimalDigits(Double pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(2, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * 保留两位小数（未四舍五入）
     *
     * @param pfValue 待转换的值
     * @return 转换后的值
     */
    public static String keep2DecimalDigits(Float pfValue) {
        try {
            return String.valueOf(new BigDecimal(pfValue).setScale(2, RoundingMode.DOWN));
        } catch (Exception e) {
            return "0.00";
        }
    }

    /**
     * 计算总价 乘
     *
     * @param price
     * @param number
     * @return String
     */
    public static String getTotalPrice(String price, Integer number) {
        double dd = Double.valueOf(price);
        BigDecimal decimal1 = BigDecimal.valueOf(dd);
        BigDecimal decimal2 = new BigDecimal(number);
        return double2String(decimal1.multiply(decimal2).doubleValue());
    }

    /**
     * 计算两个价格的和
     *
     * @param price1
     * @param price2
     * @return String
     */
    public static String getOrderPrice(String price1, String price2) {
        double dd1 = Double.valueOf(price1);
        BigDecimal decimal1 = BigDecimal.valueOf(dd1);
        double dd2 = Double.valueOf(price2);
        BigDecimal decimal2 = new BigDecimal(dd2);
        return double2String(decimal1.add(decimal2).doubleValue());
    }

    /**
     * 计算两个BigDecimal的和
     *
     * @param b1 加数
     * @param b2 被加数
     * @return String
     */
    public static String bigDecAdd(BigDecimal b1, BigDecimal b2) {
        if (ObjectUtils.isEmpty(b1) && ObjectUtils.isEmpty(b2))
            return "0.0";
        else if (ObjectUtils.isEmpty(b1))
            return double2String(b2);
        else if (ObjectUtils.isEmpty(b2))
            return double2String(b1);
        else
            return double2String(b1.add(b2));
    }

    /**
     * 计算两个价格的差
     *
     * @param price1
     * @param price2
     * @return String
     */
    public static String getSubtractPrice(String price1, String price2) {
        double dd1 = Double.valueOf(price1);
        BigDecimal decimal1 = BigDecimal.valueOf(dd1);
        double dd2 = Double.valueOf(price2);
        BigDecimal decimal2 = BigDecimal.valueOf(dd2);
        return double2String(decimal1.subtract(decimal2));
    }


    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 设置RecyclerView 不可滑动，并保证NestedScrollView滑动流畅
     *
     * @param recyclerView
     */
    public static void setRecyclerViewScrollingFalse(RecyclerView recyclerView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            recyclerView.setNestedScrollingEnabled(false);
        }
    }

    /**
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @params 要转换的毫秒数
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return days + " days " + hours + " hours " + minutes + " minutes "
                + seconds + " seconds ";
    }

    /**
     * @return 该毫秒数转换为 hours 后的格式
     * @params 要转换的毫秒数
     * @author fy.zhang
     */
    public static int millis2Hour(long millis) {
        return (int) (millis % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
    }

    /**
     * @return 该毫秒数转换为 seconds 后的格式
     * @params 要转换的毫秒数
     * @author fy.zhang
     */
    public static int millis2Minute(long millis) {
        return (int) (millis % (1000 * 60 * 60)) / (1000 * 60);
    }

    /**
     * @return 该毫秒数转换为 minutes 后的格式
     * @params 要转换的毫秒数
     * @author fy.zhang
     */
    public static int millis2Second(long millis) {
        return (int) (millis % (1000 * 60)) / 1000;
    }

    /**
     * @param begin 时间段的开始
     * @param end   时间段的结束
     * @return 输入的两个Date类型数据之间的时间间格用* days * hours * minutes * seconds的格式展示
     * @author fy.zhang
     */
    public static String formatDuring(Date begin, Date end) {
        return formatDuring(end.getTime() - begin.getTime());
    }


    /**
     * 为一组edittext和textview绑定一个字数改变监听，字数超过限制进行提示
     *
     * @param ctx
     * @param et
     * @param tv
     * @param max
     */
    public static void setTextWatcher(final Context ctx, EditText et,
                                      TextView tv, int max) {
        class FuncInnerTextWitcher implements TextWatcher {
            private EditText et;
            private int maxNum;
            private TextView tvIndictor;

            public FuncInnerTextWitcher(EditText et, TextView tvIndictor,
                                        int maxNum) {
                this.maxNum = maxNum;
                this.et = et;
                this.tvIndictor = tvIndictor;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                tvIndictor.setText(s.length() + "/" + maxNum);
                if (s.length() > maxNum) {
                    // dialog_oneBtn((Activity) ctx, null,
                    // "超过" + maxNum + "个字数限制", 0, "确定");
                    s = s.subSequence(0, maxNum);
                    et.setText(s);
                    et.setSelection(maxNum);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        }
        et.addTextChangedListener(new FuncInnerTextWitcher(et, tv, max));
    }


    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bm     需要旋转的图片
     * @param degree 旋转角度
     * @return 旋转后的图片
     */
    public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
        Bitmap returnBm = null;

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bm;
        }

        if (bm != null && bm != returnBm) {
            bm.recycle();
        }

        return returnBm;
    }


    /**
     * 读取图片的旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片的旋转角度
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 刷新和加载结束动画
     *
     * @param smartRefreshLayout
     */
    public static void finishRefreshAndLoadMore(SmartRefreshLayout smartRefreshLayout) {
        if (smartRefreshLayout != null) {
            smartRefreshLayout.finishRefresh();
            smartRefreshLayout.finishLoadmore();
        }
    }

    /**
     * 取消加载更多
     *
     * @param currentPage 当前页
     * @param totalPage   总页数
     */
    public static void cancelLoadMore(SmartRefreshLayout smartRefreshLayout, int currentPage, int totalPage) {
        if (null == smartRefreshLayout)
            return;
        if (currentPage == totalPage) {
            smartRefreshLayout.setEnableLoadMore(false);
        }
    }

    /**
     * 取消timer
     *
     * @param countTimer
     */
    public static void cancelTimer(CountDownTimer countTimer) {
        if (null != countTimer) {
            countTimer.cancel();
            countTimer = null;
        }
    }

    /**
     * 列表无数据（recyclerView BaseQuickAdapter）
     *
     * @param adapter
     */
    public static void showNoData(BaseQuickAdapter adapter, RecyclerView recyclerView) {
        if (null == adapter || null == recyclerView)
            return;
        if (!isListEmpty(adapter.getData())) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
        }
        adapter.setEmptyView(R.layout.layout_empty_no_data, (ViewGroup) recyclerView.getParent());
    }

    /**
     * 搜索列表无数据（recyclerView BaseQuickAdapter）
     *
     * @param adapter
     */
    public static void showEmptySearch(final Activity activity, BaseQuickAdapter adapter, RecyclerView recyclerView) {
        if (null == adapter || null == recyclerView || null == activity)
            return;
        //TODO
//        if (isListEmpty(adapter.getData())) {
//            adapter.setEmptyView(R.layout.layout_empty_search, recyclerView);
//            /*试一试点击事件*/
//            TextView button = adapter.getEmptyView().findViewById(R.id.btn_go_to_try);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Utils.gotoActivity(activity, IPurchaseActivity.class, false, null, null);
//                }
//            });
//        }
    }

    /**
     * 列表是否为空
     *
     * @param list
     * @return
     */
    public static boolean isListEmpty(List list) {
        return ObjectUtils.isEmpty(list) || list.size() < 1;
    }

    /**
     * 隐藏手机号中间四位
     *
     * @param phoneNum 手机号
     * @return 隐藏了中间四位的手机号
     */
    public static String hidePhoneMiddleNumber(String phoneNum) {
        try {
            return phoneNum.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        } catch (Exception e) {
            return "";
        }
    }


    /**
     * 判断是否有网
     *
     * @param poContext
     * @return
     */
    public static boolean isInternetAvailable(Context poContext) {
        if (poContext == null) {
            return true;
        }
        ConnectivityManager loConnMgr = (ConnectivityManager) poContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (loConnMgr != null) {
            NetworkInfo loNetInfo = loConnMgr.getActiveNetworkInfo();
            return loNetInfo != null && loNetInfo.isAvailable();
        } else {
            return false;
        }
    }


    public static String getFormateDate(String time) {
        if (StringUtils.isTrimEmpty(time)) {
            return null;
        }
        Date date = TimeUtils.string2Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return simpleDateFormat.format(date);
//        return time.substring(0, 10);
    }

    /**
     * 根据经纬度获取两点之间的距离
     *
     * @param coordinate1
     * @param coordinate2
     * @return
     */
    public static double getDistance(String coordinate1, String coordinate2) {
        try {
            String[] coordinateOne = coordinate1.split(",");
            String[] coordinateTwo = coordinate2.split(",");
            double longitude1 = Double.parseDouble(coordinateOne[0]);
            double latitude1 = Double.parseDouble(coordinateOne[1]);
            double longitude2 = Double.parseDouble(coordinateTwo[0]);
            double latitude2 = Double.parseDouble(coordinateTwo[1]);
            return getDistance(longitude1, latitude1, longitude2, latitude2);

        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 根据经纬度获取两点之间的距离// 返回单位是米
     *
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double EARTH_RADIUS = 6378137.0;
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
