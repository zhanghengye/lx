package com.example.zhangzhanghengye.tongzhi;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zhangzhanghengye.lx.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main3Activity extends AppCompatActivity {

    private File file;
    private final int REQU = 1;
    private final int XC = 2;
    Uri imgUri;
    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mImg = findViewById(R.id.img);
    }

    public void a(View v) {
        Intent intent = new Intent(Main3Activity.this, NoActivity.class);
        intent.putExtra("a", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationManager systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("aa")
                .setContentText("bb")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)//点击后消失
                .setVibrate(new long[]{0, 1000, 1000, 1000})
                .setPriority(NotificationManager.IMPORTANCE_MAX)
                .build();
        systemService.notify(1, notification);
    }

    public void b(View v) {
        file = new File(getExternalCacheDir(), "img.jpg");
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //判断sdk
        if (Build.VERSION.SDK_INT >= 24) {
            imgUri = FileProvider.getUriForFile(Main3Activity.this,
                    "com.example.cameraalbumtest.fileprovider", file);
        } else {
            imgUri = Uri.fromFile(file);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);
        startActivityForResult(intent, REQU);
    }

    public void xc(View v) {
        if (ContextCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Main3Activity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            openAlbum();
        }
    }

    //通过内容提供者获取所有的图片地址
    public void getImage(View v){
        Cursor query = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        while (query.moveToNext()){
            byte[] blob = query.getBlob(query.getColumnIndex(MediaStore.Images.Media.DATA));
            String s = new String(blob, 0, blob.length - 1);
            Log.e("TAG", "getImage: "+s);
        }
        Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/inews/image/DC99F0BFD609BA1661CE83A0628892EE.jpg");
        mImg.setImageBitmap(bitmap);
    }

    private void openAlbum() {
        //跳转到相册
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, XC);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQU:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imgUri));
                        mImg.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case XC:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        //当安卓版本大于4.4
                        handleImageOnKitKat(data);

                    } else {
                        //当安卓版本小于4.4
                        handleImageBeforeKitKat(data);
                    }

                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //小于4.4的版本 wang ya fei wang li wei
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri=data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    //大于4。4的版本
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imgPath = null;
        Uri uri = data.getData();
        //判断是Document类型的uri
        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是 则通过Document id来处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = documentId.split(":")[1];
                String sele = MediaStore.Images.Media._ID + "=" + id;
                imgPath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, sele);
            } else if ("com.android.providers.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_dowloads"),
                        Long.valueOf(documentId));
                imgPath = getImagePath(contentUri, null);

            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri
            imgPath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的直接获取路径就可以了
            imgPath = uri.getPath();
        }
        //根据路径加载图片
        displayImage(imgPath);
    }

    private void displayImage(String imgPath) {
        if (imgPath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
            mImg.setImageBitmap(bitmap);
        }else{
            Toast.makeText(this,"无法获取图片",Toast.LENGTH_SHORT).show();
        }
    }

    private String getImagePath(Uri externalContentUri, String sele) {
        String path =null;
        //通过参数来获取图片的真实路径
        Cursor query = getContentResolver().query(externalContentUri, null, sele, null, null);
        if(query!=null){
            //将光标移植第一行
            if(query.moveToFirst()){
               path=query.getString(query.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            query.close();
        }
        return path;
    }

    //动态权限回掉
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
                }
                break;

        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
