package com.example.zhangzhanghengye.contentp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    private static final int A = 0;

    private static final int B = 1;
    private static UriMatcher uriMatcher;
    private static final String AUTHORITY = "com.example.zhangzhanghengye.lx";

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "book", A);
        uriMatcher.addURI(AUTHORITY, "book/#", B);
    }


    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        switch (uriMatcher.match(uri)) {
            case A:
                //表的全部
                break;
            case B:
                //表的某一行
                break;


        }
        return -1;
    }

    @Override
    public String getType(Uri uri) {
    // at the given URI.
        switch (uriMatcher.match(uri)) {
            case A:
                return "vnd.android.cursor.dir/vnd.com.example.zhangzhanghengye.lx.book";
            case B:
                return "vnd.android,cursor.item/vnd.com.example.zhangzhanghengye.lx.book";
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
