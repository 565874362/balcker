package com.baihua.yayayisheng.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.afollestad.materialdialogs.MaterialDialog;
import com.baihua.yayayisheng.R;


/**
 * Project name golf
 * Created by byd on 2017/7/26 14:48
 * Package name cn.joymates.golf.util
 * Class Description
 */

public class MaterialDialogUtils {

    /**
     * @param context
     * @param content    内容ResID
     * @param cancel     取消ResID
     * @param ok         确认ResID
     * @param cancelable 是否可以取消
     * @param okCallback 确认回调
     */
    public static void showDialog(Context context, int content, int cancel, int ok, boolean cancelable,
                                  MaterialDialog.SingleButtonCallback okCallback) {

        new MaterialDialog.Builder(context)
                .content(content)
                .negativeText(cancel)
                .negativeColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .positiveText(ok)
                .positiveColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .cancelable(cancelable)
                .onPositive(okCallback)
                .show();
    }

    /**
     * 通用Dialog展示 按钮为“取消”、“确定” 点击返回键可取消弹框
     *
     * @param context
     * @param content    弹框内容
     * @param okCallback 确定回调
     */
    public static void showCommonDialog(Context context, int content, MaterialDialog.SingleButtonCallback okCallback) {
        showDialog(context, content, R.string.common_cancel, R.string.common_sure, true, okCallback);
    }

    /**
     * 通用Dialog展示 按钮为“取消”、“确定” 点击返回键可取消弹框
     *
     * @param context
     * @param content    弹框内容
     * @param okCallback 确定回调
     */
    public static void showCommonDialog(Context context, int content, MaterialDialog.SingleButtonCallback okCallback, MaterialDialog.SingleButtonCallback cancelCallback) {
        showDialog(context, content, R.string.common_cancel, R.string.common_sure, true, okCallback, cancelCallback);
    }

    /**
     * 通用Dialog展示 按钮为“取消”、“确定” 点击返回键不可取消弹框
     *
     * @param context
     * @param content    弹框内容
     * @param okCallback 确定回调
     */
    public static void showNCommonDialog(Context context, int content, MaterialDialog.SingleButtonCallback okCallback) {
        showDialog(context, content, R.string.common_cancel, R.string.common_sure, false, okCallback);
    }

    /**
     * 通用一个按钮Dialog展示 按钮为“确定” 点击返回键可取消弹框
     *
     * @param context
     * @param content    弹框内容
     * @param okCallback 确定回调
     */
    public static void showOneBtnDialog(Context context, int content, MaterialDialog.SingleButtonCallback okCallback) {
        showDialog(context, content, 0, R.string.common_sure, true, okCallback);

    }

    /**
     * 通用一个按钮Dialog展示 按钮为“确定” 点击返回键不可取消弹框
     *
     * @param context
     * @param content    弹框内容
     * @param okCallback 确定回调
     */
    public static void showNOneBtnDialog(Context context, int content, MaterialDialog.SingleButtonCallback okCallback) {
        showDialog(context, content, 0, R.string.common_sure, false, okCallback);
    }


    public static void showDialog(Context context, String content, String cancel, String ok, MaterialDialog
            .SingleButtonCallback okCallback) {

        new MaterialDialog.Builder(context)
                .content(content)
                .negativeText(cancel)
                .negativeColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .positiveText(ok)
                .positiveColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .onPositive(okCallback)
                .show();
    }

    /**
     * 通用一个按钮Dialog展示 按钮为“确定” 点击返回键不可取消弹框
     *
     * @param context
     * @param content        弹框内容
     * @param okCallback     确定回调
     * @param cancelCallback 取消回调
     */
    public static void showDialog(Context context, String content, String cancel, String ok, boolean cancelable, MaterialDialog
            .SingleButtonCallback okCallback, MaterialDialog.SingleButtonCallback cancelCallback) {

        new MaterialDialog.Builder(context)
                .content(content)
                .negativeText(cancel)
                .negativeColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .positiveText(ok)
                .positiveColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .onPositive(okCallback)
                .onNegative(cancelCallback)
                .cancelable(cancelable)
                .show();
    }

    /**
     * 通用一个按钮Dialog展示 按钮为“确定” 点击返回键不可取消弹框
     *
     * @param context
     * @param content        弹框内容
     * @param okCallback     确定回调
     * @param cancelCallback 取消回调
     */
    public static void showDialog(Context context, int content, int cancel, int ok, boolean cancelable, MaterialDialog
            .SingleButtonCallback okCallback, MaterialDialog.SingleButtonCallback cancelCallback) {

        new MaterialDialog.Builder(context)
                .content(content)
                .negativeText(cancel)
                .negativeColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .positiveText(ok)
                .positiveColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .onPositive(okCallback)
                .onNegative(cancelCallback)
                .cancelable(cancelable)
                .show();
    }


    public static MaterialDialog showNOneBtnDialogOnlyOne(Context context, int content, MaterialDialog.SingleButtonCallback okCallback) {
        return showDialogOnlyOne(context, content, 0, R.string.common_sure, false, okCallback);
    }

    public static MaterialDialog showDialogOnlyOne(Context context, int content, int cancel, int ok, boolean cancelable, MaterialDialog
            .SingleButtonCallback okCallback) {

        return new MaterialDialog.Builder(context)
                .content(content)
                .negativeText(cancel)
                .negativeColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .positiveText(ok)
                .positiveColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .onPositive(okCallback)
                .cancelable(cancelable)
                .show();
    }



}
