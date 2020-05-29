package com.example.zhangzhanghengye.servicexz;

public interface DownloadListener {
    //当前下载进度
    void onProgress(int progress);
    //下载成功
    void onSuccess();
    //下载失败
    void onFailed();
    //下载暂停
    void onPaused();
    //取消下载
    void onCanceled();
}
