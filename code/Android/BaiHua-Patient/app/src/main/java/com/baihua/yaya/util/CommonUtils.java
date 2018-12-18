package com.baihua.yaya.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baihua.yaya.R;
import com.baihua.yaya.widget.HackyViewPager;
import com.baihua.yaya.widget.PhotoViewPagerAdapter;
import com.blankj.utilcode.util.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;

/**
 * Author:byd
 * Time:14/12/2018 9:50
 * Description: CommonUtils
 */
public class CommonUtils {

    /**
     * 使状态栏透明
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void transparentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 展示大图
     *
     * @param selectPosition 选中的图片位置
     */
    public static void showPicDialog(Context context, List<String> imageSource, int selectPosition) {
        MaterialDialog show = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_show_photo_view, false)
                .build();
        View view = show.getCustomView();
        if (null == view)
            return;
        HackyViewPager hackyViewPager = view.findViewById(R.id.view_pager);
        PhotoViewPagerAdapter mAdapter = new PhotoViewPagerAdapter(imageSource, context);
        hackyViewPager.setAdapter(mAdapter);
        hackyViewPager.setCurrentItem(selectPosition);
        show.show();
    }

    /**
     * 获取TOKEN
     *
     * @return Token
     */
    public static String getToken() {
        return SPUtils.getInstance("token").getString("token", "");
    }

    /**
     * 清除TOKEN
     */
    public static void clearToken() {
        SPUtils.getInstance("token").remove("token");
    }

    /**
     * 获取性别 1 男 0 女
     *
     * @param gender 性别标识
     * @return 性别字符串
     */
    public static String getGender(String gender) {
        return gender.equals("1") ? "男" : "女";
    }

    /**
     * 文件转base64字符串
     *
     * @param file 目标文件
     * @return
     */
    public static String fileToBase64(File file) {
        String base64 = null;
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            int length = in.read(bytes);
            base64 = Base64.encodeToString(bytes, 0, length, Base64.DEFAULT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return base64;
    }

    /**
     * base64字符串转文件
     *
     * @param base64 字符串
     * @return 结果
     */
    public static File base64ToFile(String base64) {
        File file = null;
        String fileName = String.format(Locale.getDefault(),
                "%s/Record/com.baihua.yaya/%s.mp3",
                Environment.getExternalStorageDirectory().getAbsolutePath(), System.currentTimeMillis());
        FileOutputStream out = null;
        try {
            // 解码，然后将字节转换为文件
            file = new File(Environment.getExternalStorageDirectory(), fileName);
            if (!file.exists())
                file.createNewFile();
            byte[] bytes = Base64.decode(base64, Base64.DEFAULT);// 将字符串转换为byte数组
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            out = new FileOutputStream(file);
            int bytesum = 0;
            int byteread = 0;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); // 文件写操作
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return file;
    }
}
