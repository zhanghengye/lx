package com.example.zhangzhanghengye.lx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String timeStamp2Date(long time, String format) {
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(time));
    }
}
