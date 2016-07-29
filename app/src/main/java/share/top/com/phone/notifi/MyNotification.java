package share.top.com.phone.notifi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import share.top.com.phone.R;

/**
 * Created by ZHOU on 2016/3/9.
 */
public class MyNotification {

    Notification notifications;
    private static MyNotification notification;
    private Context mContext;
    public static final int NOTIFICATION_ID = 1;
    NotificationManager manager;

    private MyNotification(Context mContext) {
        this.mContext = mContext;
        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static MyNotification getInstance(Context mContext) {
        if (notification == null) {
            notification = new MyNotification(mContext);
        }
        return notification;
    }

    //发送
    public void MyF(String text) {
        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifications = new Notification();
        notifications.flags = Notification.FLAG_NO_CLEAR;
        notifications.icon = R.mipmap.ic_launcher;
        notifications.tickerText = "你妈叫你回家吃饭!" + text;
        notifications.when = System.currentTimeMillis();
        //布局文件不能为空, RemoteViews 可以代表layout下所有的布局文件
        RemoteViews views = new RemoteViews("share.top.com.phone", R.layout.notification_layout);
        notifications.contentView = views;
        Intent intent = new Intent("share.top.com.phone");//拉下通知栏 进行跳转到该程序中去
        //PendingIntent.FLAG_CANCEL_CURRENT 点击了之后会消失
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        notifications.contentIntent = pendingIntent;
        //第一个参数 代表 编号，用来清除的  参数二  通知对象
        manager.notify(NOTIFICATION_ID, notifications);
    }

    //清除
    public void MyClean() {
        manager.cancel(NOTIFICATION_ID);
    }

}
