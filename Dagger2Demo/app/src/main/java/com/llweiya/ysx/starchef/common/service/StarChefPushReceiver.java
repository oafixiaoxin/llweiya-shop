package com.llweiya.ysx.starchef.common.service;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.google.gson.Gson;
import com.llweiya.ysx.starchef.business.dependency.view.AnnouncementActivity;
import com.llweiya.ysx.starchef.business.user.view.LoginActivity;
import com.llweiya.ysx.starchef.business.user.view.RegisterActivity;
import com.llweiya.ysx.starchef.common.model.TransmittedMessage;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ysx on 2018/1/18.
 */

public class StarChefPushReceiver extends PushMessageReceiver {
    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {
        Log.e("StarChefPushReceiver", "onBind,code=" + i + ", s=" + s + ", s1=" + s1 + ", s2=" + s2 + ", s3=" + s3);
    }

    @Override
    public void onUnbind(Context context, int i, String s) {
        Log.e("StarChefPushReceiver", "onUnbind");
    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Log.e("StarChefPushReceiver", "onSetTags");
    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Log.e("StarChefPushReceiver", "onDelTags");
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
        Log.e("StarChefPushReceiver", "onListTags");
    }

    @Override
    public void onMessage(Context context, String s, String s1) {
        Log.e("StarChefPushReceiver", "接收到的透传信息为：" + s + ", " + s1);

        Intent intent = new Intent();
        intent.putExtra("message", s);
        intent.setClass(context, AnnouncementActivity.class);
        context.startActivity(intent);

    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Log.e("StarChefPushReceiver", "onNotificationClicked: s=" + s + ", s1=" + s1 + ", s2=" +
                s2);

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);

        boolean isRunning = false;

        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(context.getPackageName()) || info
                    .baseActivity.getPackageName().equals(context.getPackageName())) {
                isRunning = true;
                break;
            }
        }

        Intent mainIntent = new Intent(context, LoginActivity.class);
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (isRunning) {
            Toast.makeText(context, "app is running", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, RegisterActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            Intent[] intents = {mainIntent, intent};
            context.startActivities(intents);
        } else {
            Toast.makeText(context, "app is die", Toast.LENGTH_SHORT).show();
            Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
            context.startActivity(launchIntent);
        }
    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
        Log.e("StarChefPushReceiver", "onNotificationArrived" + ", " + s + "," + s1 + "," + s2);
    }
}
