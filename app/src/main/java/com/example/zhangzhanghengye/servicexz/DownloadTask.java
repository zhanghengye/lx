package com.example.zhangzhanghengye.servicexz;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 范型的三个值分别代表
 * 1、代表传一个字符串来给后台
 * 2、代表用int值来显示进度
 * 3、代表返回一个int值
 */
public class DownloadTask extends AsyncTask<String, Integer, Integer> {

    //开始
    private static final int TYPE_SUCCESS = 0;
    //失败
    private static final int TYPE_FAILED = 1;
    //暂停
    private static final int TYPE_PAUSED = 2;
    //取消
    private static final int TYPE_CANCELED = 3;
    private DownloadListener downloadListener;
    private boolean isCanceled = false;
    private boolean isPaused = false;
    private int lastProgress;

    public DownloadTask(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    @Override
    protected Integer doInBackground(String... strings) {
        InputStream is = null;
        RandomAccessFile sa = null;
        File file = null;
        try {
            //记录已下载文件长度
            long downloadedLength = 0;
            //下载地址
            String downloadUrl = strings[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String diretory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(fileName + diretory);
            //如果已经存在这个文件 将文件字节数值取出来
            if (file.exists()) {
                downloadedLength = file.length();
            }
            //获取待下载的文件总长度
            long contentLength = getContentLength(downloadUrl);
            //如果是0代表文件错误或者有问题
            if (contentLength == 0) {
                return TYPE_FAILED;
                //如果文件长度==下载长度代表已经下载完了
            } else if (contentLength == downloadedLength) {
                //已下载文件和文件总字节相等说明下载完成
                return TYPE_CANCELED;
            }
            //从下载点继续下载
            OkHttpClient okHttpClient = new OkHttpClient();
            Request range = new Request.Builder()
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();

            Response execute = okHttpClient.newCall(range).execute();
            if (execute != null) {
                is = execute.body().byteStream();
                sa = new RandomAccessFile(file, "rw");
                sa.seek(downloadedLength);
                byte[] b = new byte[1024];
                int tobla = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        tobla += len;
                        sa.write(b, 0, len);
                        //计算已下载的百分比
                        int progress = (int) ((tobla + downloadedLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                execute.body().close();
                return TYPE_SUCCESS;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {

                    is.close();
                }
                if (sa != null) {
                    sa.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            downloadListener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer integer) {
        switch (integer){
            case TYPE_SUCCESS:
                downloadListener.onSuccess();
                break;
            case TYPE_FAILED:
                downloadListener.onFailed();
                break;
            case TYPE_CANCELED:
                downloadListener.onCanceled();
                break;
            case TYPE_PAUSED:
                downloadListener.onPaused();
                break;

        }
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response execute = okHttpClient.newCall(build).execute();
        if(execute !=null && execute.isSuccessful()){
            long contentLength=execute.body().contentLength();
            execute.close();
            return contentLength;
        }
        return 0;
    }


    public void pausrDownload(){
        isPaused=true;
    }

    public void cancelDownload(){
        isCanceled=true;
    }


}
