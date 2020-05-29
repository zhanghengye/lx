package com.example.zhangzhanghengye.servicexz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.zhangzhanghengye.lx.R;

import java.io.File;

public class DownloadService extends Service {
    private DownloadTask downloadTask;

    private String downloadUrl;
    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1, getNotification("Download...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            //下载成功之后将前台通知关闭 创建一个完成的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Success", -1));
            Toast.makeText(DownloadService.this, "下载完成了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            //朱门酒肉臭 路有冻死骨
            downloadTask = null;
            //下载成功之后将前台通知关闭 创建一个失败的通知
            stopForeground(true);
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "下载失败了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "下载暂停", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            //where are you form 你从哪里来的    nice to meet you 见到你很高兴
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(DownloadService.this, "下载取消了", Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder binder = new DownloadBinder();

    public DownloadService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(downloadListener);
                downloadTask.execute(downloadUrl);
                startForeground(1, getNotification("DownloadDing...", 0));
                Toast.makeText(DownloadService.this, "DownloadDing...", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pausrDownload();
            }
        }

        public void canceDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
            } else {

                if (downloadUrl != null) {
                    //取消下载需要删除已下载的文件并把前台通知关闭
                    String substring = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
                    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(path + substring);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(DownloadService.this, "Download取消", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, XiaZaiActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.back9));
        builder.setContentIntent(pi);
        builder.setContentText(title);
        if (progress > 0) {
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

}
