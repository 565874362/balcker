package com.baihua.yayayisheng.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.text.Html;

import com.baihua.yayayisheng.R;

/**
 * ProjectName：TuanLeApp
 * PackageName：com.joymates.tuanle.util
 * ClassDescribe：
 * CreaterBy：SongGang
 * CreateDate：2018/1/16 17:45
 * Remark：
 */
public class NotificationUtils {

    public static void addNotificaction(Context context, Intent intent, int id,
                                        String tip, String title, String content) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = null;
        contentIntent = PendingIntent.getActivity(context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder1 = new Notification.Builder(context);
        builder1.setSmallIcon(R.mipmap.ic_launcher); //设置图标
        builder1.setTicker(tip);
        builder1.setContentTitle(title); //设置标题
        builder1.setContentText(Html.fromHtml(content)); //消息内容
        builder1.setWhen(System.currentTimeMillis()); //发送时间
//        if (Constants.SilentModeSwitch.OPEN.equals(SettingsXML
//                .getSlientMode(context))) {
//            builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
//        } else {
        builder1.setDefaults(Notification.DEFAULT_ALL); //设置默认的提示音，振动方式，灯光
//
//        }
        builder1.setAutoCancel(true);//打开程序后图标消失
//        Intent intent =new Intent (MainActivity.this,Center.class);
//        PendingIntent pendingIntent =PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        builder1.setContentIntent(contentIntent);
        Notification notification = builder1.build();


        if (manager != null) {
            manager.notify(id, notification);
        }
    }

    /**
     * 清除关于消息的通知
     */
    public static void cancleAll(Context context) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.cancelAll();
        }
    }


}
