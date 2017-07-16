package com.juziwl.commonlibrary.utils;

import android.app.Activity;
import android.app.Notification;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.jakewharton.rxbinding2.view.RxView;
import com.juziwl.commonlibrary.R;
import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.model.Clazz;
import com.orhanobut.logger.Logger;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.functions.Consumer;

/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月18日
 * @description 应用程序的公共工具类
 */
public class CommonTools {

    public static String userId = "", accessToken = "";

    private static Toast toast;

    private static void createToast(Context context, String content, int resId) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            Context applicationContext = context.getApplicationContext(); //防止内存泄漏
            toast = new Toast(applicationContext);
            toast.setView(View.inflate(context, R.layout.common_layout_toast, null));
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        if (resId > 0) {
            toast.setText(resId);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    //由于实现沉浸式状态栏，导致系统自带的Toast出现问题，所以自定义Toast的View
    public static void showToast(final String content) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            createToast(Global.application, content, 0);
        } else {
            UIHandler.getInstance().post(() -> createToast(Global.application, content, 0));
        }
    }

    public static void showToast(final int resId) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            createToast(Global.application, "", resId);
        } else {
            UIHandler.getInstance().post(() -> createToast(Global.application, "", resId));
        }
    }

    /**
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 获取状态栏高度
     *
     * @param context context
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    public static String getCurrentTime() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 可以方便地修改日期格式
        return dateFormat.format(nowTime);
    }

    public static String getMsgCurrentTime() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    public static String currentDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    public static Date currentDate2() {
        Date date = new Date();
        return date;
    }


    /**
     * 格式化时间
     *
     * @param time
     * @return
     */
    public static String formatDateTime(String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = "";
        try {
            Date d1 = sdf.parse(time);
            currentDate = sdf.format(d1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        if (currentDate == null || "".equals(currentDate)) {
            return "";
        }
        Date date = null;
        try {
            date = format.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar current = Calendar.getInstance();
        Calendar today = Calendar.getInstance();    //今天
        int year = current.get(Calendar.YEAR);
        int month = current.get(Calendar.MONTH);
        int day = current.get(Calendar.DAY_OF_MONTH);
        today.set(Calendar.YEAR, year);
        today.set(Calendar.MONTH, month);
        today.set(Calendar.DAY_OF_MONTH, day);
        //  Calendar.HOUR——12小时制的小时数 Calendar.HOUR_OF_DAY——24小时制的小时数
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);

        Calendar yesterday = Calendar.getInstance();    //昨天

        yesterday.set(Calendar.YEAR, year);
        yesterday.set(Calendar.MONTH, month);
        yesterday.set(Calendar.DAY_OF_MONTH, day - 1);
        yesterday.set(Calendar.HOUR_OF_DAY, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);

        current.setTime(date);
        int year1 = current.get(Calendar.YEAR);
        int month1 = current.get(Calendar.MONTH) + 1;
        int day1 = current.get(Calendar.DAY_OF_MONTH);
        int hour1 = current.get(Calendar.HOUR_OF_DAY);
        int minute1 = current.get(Calendar.MINUTE);
        String syear = String.valueOf(year1);
        String smonth = String.valueOf(month1);
        String sday = String.valueOf(day1);
        String shour = String.valueOf(hour1);
        String sminute = String.valueOf(minute1);

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentflag = simpleDateFormat.format(current.getTime());
//        String todayflag = simpleDateFormat.format(today.getTime());
//        String yesterdayflag = simpleDateFormat.format(yesterday.getTime());
        if (current.after(today)) {
            if (hour1 < 10) {
                shour = "0" + hour1;
            }
            if (minute1 < 10) {
                sminute = "0" + minute1;
            }
            return shour + ":" + sminute;
        } else if (current.before(today) && current.after(yesterday)) {
            if (hour1 < 10) {
                shour = "0" + hour1;
            }
            if (minute1 < 10) {
                sminute = "0" + minute1;
            }
            return "昨天 " + shour + ":" + sminute;
        } else if (current.get(Calendar.YEAR) < (today.get(Calendar.YEAR))) {
            return currentDate.replaceFirst("-", "年").replace("-", "月").replace(" ", "日  ");
        } else {
            if (month1 < 10) {
                smonth = "0" + month1;
            }
            if (day1 < 10) {
                sday = "0" + day1;
            }
            if (hour1 < 10) {
                shour = "0" + hour1;
            }
            if (minute1 < 10) {
                sminute = "0" + minute1;
            }
            return smonth + "月" + sday + "日" + " " + shour + ":" + sminute;
        }
    }

    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    public static String formatDateTime2(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date d1 = sdf.parse(time);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = format.format(d1);
            return dateString;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    public static String formatDateTime3(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            Date d1 = sdf.parse(time);
            SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
            String dateString = format.format(d1);
            return dateString;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }


    public static String currentDateAndTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String currentDate = sdf.format(date);
        return currentDate;
    }

    public static String currentTimeSong() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
        String mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
        return mMonth + "月" + mDay + "日";
    }

    public static String secToTime(int time) {
        String timeStr;
        int hour;
        int minute;
        int second;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    public static long compare_int(String a, String b) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Long c;
        long d = 0;
        try {
            c = sf.parse(b).getTime() - sf.parse(a).getTime();
            d = c / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 检测是否有emoji字符
     *
     * @param source
     * @return 一旦含有就抛出
     */
    public static boolean containsEmoji(String source) {

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                // do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA)
                || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
                || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static String filterEmoji(String source) {

        source += " "; // 在传入的source后面加上一个空字符。返回的时候trim掉就OK了
        if (!containsEmoji(source)) {
            return source;// 如果不包含，直接返回
        }
        // 到这里铁定包含
        StringBuilder buf = null;

        int len = source.length();

        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }

                buf.append(codePoint);
            } else {
            }
        }

        if (buf == null) {
            return source;// 如果没有找到 emoji表情，则返回源字符串
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    public static long dateToLong(String in) {//2016-05-19 13:31:21
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(in);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getTimeInMillis();
    }

    public static String format(long time) {
        long[] time_differ = getZeroData();
        String date = "";
        if (time >= time_differ[0]) {
            if (time - time_differ[0] <= 24 * 60 * 60 * 1000) {
                date = secondToDate(time, 0);
            } else {
                date = secondToDate(time, 4);
            }
        } else if (time < time_differ[0] && time >= time_differ[1]) {
            date = secondToDate(time, 1);
        } else if (time < time_differ[1] && time >= time_differ[2]) {
            date = secondToDate(time, 2);
        } else if (time < time_differ[2]) {
            date = secondToDate(time, 3);
        }
        return date;
    }


    public static String format(long time, boolean flag) {

        long[] time_differ = getZeroData();
        String date = "";
        if (time >= time_differ[0]) {
            if (time - time_differ[0] <= 24 * 60 * 60 * 1000) {
                date = secondToDate(time, 0, flag);
            } else {
                date = secondToDate(time, 4, flag);
            }
        } else if (time < time_differ[0] && time >= time_differ[1]) {
            date = secondToDate(time, 1, flag);
        } else if (time < time_differ[1] && time >= time_differ[2]) {
            date = secondToDate(time, 2, flag);
        } else if (time < time_differ[2]) {
            date = secondToDate(time, 3, flag);
        }
        return date;

    }


    private static long[] getZeroData() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time_today = cal.getTimeInMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MONTH, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        long year = cal1.getTimeInMillis();
        long time_yesterday = time_today - 60 * 60 * 24 * 1000;
        long[] time_group = {time_today, time_yesterday, year};
        return time_group;
    }

    private static String secondToDate(long second, int type, boolean flag) {
        Date dat = new Date(second);
        SimpleDateFormat format;
        String data = null;
        switch (type) {
            case 0:
                format = new SimpleDateFormat("HH:mm");
                data = format.format(dat);
                break;
            case 1:
                format = new SimpleDateFormat("MM月dd日");
                data = format.format(dat);
                break;
            case 2:
                format = new SimpleDateFormat("MM月dd日");
                data = format.format(dat);
                break;
            case 3:
                Calendar cal = Calendar.getInstance();
                String s = String.valueOf(cal.get(Calendar.YEAR));
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                data = format.format(dat);
                if (data.contains(s)) {
                    format = new SimpleDateFormat("MM月-dd日");
                    data = format.format(dat);
                }
                break;
            case 4:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                data = format.format(dat);
                break;
        }
        return data;
    }

    private static String secondToDate(long second, int type) {
        Date dat = new Date(second);
        SimpleDateFormat format;
        String data = null;
        switch (type) {
            case 0:
                format = new SimpleDateFormat("HH:mm");
                data = "今天 " + format.format(dat);
                break;
            case 1:
                format = new SimpleDateFormat("HH:mm");
                data = "昨天 " + format.format(dat);
                break;
            case 2:
                format = new SimpleDateFormat("MM-dd HH:mm");
                data = format.format(dat);
                break;
            case 3:
                Calendar cal = Calendar.getInstance();
                String s = String.valueOf(cal.get(Calendar.YEAR));
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                data = format.format(dat);
                if (data.contains(s)) {
                    format = new SimpleDateFormat("MM-dd HH:mm");
                    data = format.format(dat);
                }
                break;
            case 4:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                data = format.format(dat);
                break;
        }
        return data;
    }

    public static void startActivity(Context context, Class classes) {
        startActivity(context, classes, null);
    }

    public static void startActivity(Context context, Class classes, Bundle bundle) {
        Intent intent = new Intent(context, classes);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        if (bitmap != null) {
            Matrix m = new Matrix();
            m.postRotate(degress);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
            return bitmap;
        }
        return bitmap;
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int tmp = 0;
        if (listAdapter.getCount() >= 3) {
            for (int i = 0; i < 3; i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
                tmp = listItem.getMeasuredHeight();
            }
        } else {
            for (int i = 0; i < listAdapter.getCount(); i++) {

                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
                tmp = listItem.getMeasuredHeight();
            }
        }

        // totalHeight += 10;
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private static Bitmap getScaleResourseImg(Context context, int resId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inDensity = 160;
        options.inScaled = true;
        BitmapFactory.decodeResource(context.getResources(), resId, options);
        if (options.outHeight > height) {
            options.inSampleSize = (int) (options.outHeight * 1.0 / height);
        }
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(context.getResources(), resId, options);
    }

    public static void shareWX(final Context context, String url, String title, String picurl, String desc, int flag) {
        final IWXAPI api = WXAPIFactory.createWXAPI(context, Global.APP_ID, false);
        api.registerApp(Global.APP_ID);
        if (api.isWXAppInstalled()) {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;

            final SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = "exiaoxin" + System.currentTimeMillis();
            if (flag == 0) {
                req.scene = SendMessageToWX.Req.WXSceneSession;// 好友
            } else if (flag == 1) {
                req.scene = SendMessageToWX.Req.WXSceneTimeline;// 朋友圈
            }

            final WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = title;
            msg.description = desc;

            if (picurl != null && !picurl.equals("")) {
                String imgs[] = picurl.split(";");
                String imgUrl;
                int resId = CommonTools.getFileBigImgResource(imgs[0]);
                if (resId > 0) {
                    msg.setThumbImage(getScaleResourseImg(context, resId, 80, 80));
                    sendReqToWX(api, req, msg);
                } else {
                    try {
                        if (!imgs[0].startsWith("http://") && !imgs[0].startsWith("https://")) {
                            imgUrl = Global.baseURL + imgs[0];
                        } else {
                            imgUrl = imgs[0];
                        }
                    } catch (Exception e) {
                        imgUrl = "";
                    }
                    LoadingImgUtil.getCacheImage(imgUrl, new Handler(Looper.getMainLooper()) {
                        @Override
                        public void handleMessage(Message message) {
                            if (message.what == 100) {
                                File file = new File(message.obj.toString());
                                if (file != null && file.exists() && file.isFile()) {
                                    BitmapFactory.Options options = new BitmapFactory.Options();
                                    options.inJustDecodeBounds = true;
                                    BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                                    if (options.outHeight > 80 || options.outWidth > 80) {
                                        options.inSampleSize = (int) Math.max(options.outHeight * 1.0 / 80, options.outWidth * 1.0 / 80);
                                    }
                                    options.inJustDecodeBounds = false;
                                    msg.setThumbImage(BitmapFactory.decodeFile(file.getAbsolutePath(), options));
                                } else {
                                    msg.setThumbImage(getScaleResourseImg(context, R.mipmap.common_icon_weixin, 80, 80));
                                }
                                sendReqToWX(api, req, msg);
                            }
                        }
                    }, null);
                }
            } else {
                msg.setThumbImage(getScaleResourseImg(context, R.mipmap.common_icon_weixin, 80, 80));
                sendReqToWX(api, req, msg);
            }

        } else {
            CommonTools.showToast("您的手机还未安装微信客户端,暂不能分享!");
        }
    }

    private static void sendReqToWX(IWXAPI api, SendMessageToWX.Req req, WXMediaMessage msg) {
        req.message = msg;
        api.sendReq(req);
    }

    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 判断邮箱是否合法
     *
     * @param email
     * @return
     */
    public static boolean isEmailOrPhone(String email) {
        if (null == email || "".equals(email))
            return false;
        // Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)|(1[3|4|5|7|8][0-9]\\d{8})");// 复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /**
     * 获得手机屏幕高度
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static String getFormatTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static Date string2Date(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static String outputError(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();

        if (cause != null) {
            cause.printStackTrace(printWriter);
        }
        Logger.e(writer.toString());
        // FileUtils.writeFileCache("DEBUG", writer.toString() + "==" + Utils.getCurrentTime() + "==" + Utils.getVersionName());
        return writer.toString();
    }

    /*
    * 在Android4.4及以上的手机上面使状态栏变成主色调
    * */
//    @TargetApi(19)
//    public static void initWindow(Activity activity) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
//            tintManager.setStatusBarTintColor(activity.getResources().getColor(R.color.account_orange));
//            tintManager.setStatusBarTintEnabled(true);
//        }
//    }

    //数据库批量插入可以用这个方法,添加的顺序按定义的顺序排列
    public static String[] getAllField(String className) {
        String[] strings = new String[2];
        StringBuilder sb = new StringBuilder("");
        StringBuilder sb2 = new StringBuilder("");
        try {
            Class clazz = Class.forName(className);
            Field[] field = clazz.getDeclaredFields();
            for (Field f : field) {
                sb.append(f.getName() + ",");
                sb2.append("?,");
            }
            strings[0] = sb.toString();
            strings[1] = sb2.toString();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static void backgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }

    public static boolean isEmpty(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    //把字符串里的多个换成换成一个
    public static String replaceEnter(String content) {
        String str = new String(content);
        try {
            str = str.replaceAll("[\\n]+", "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void setType(UserPreference userPreference, ArrayList<Clazz> userClazzs, ArrayList<Clazz> openClazzs) {
        int openTea = 0, openPar = 0, inputTea = 0, inputPar = 0, inputTeaNoClass = 0;
        for (Clazz clazz : openClazzs) {
            if (CommonTools.isEmpty(clazz.subjectId)) {
                openPar++;
            } else {
                openTea++;
            }
        }
        int openType = 0;
        if (openPar == 0 && openTea == 0) {
            openType = 0;
        }
        if (openPar > 0) {
            openType = 1;
        }
        if (openTea > 0) {
            openType = 2;
        }

        userPreference.storeOpenType(openType);

        for (Clazz clazz : userClazzs) {
            if ("教师".equals(clazz.role)) {
                if (!CommonTools.isEmpty(clazz.classId)) {
                    inputTea++;
                } else {
                    inputTeaNoClass++;
                }
            } else {
                inputPar++;
            }
        }
        int type = 0;
        if (inputTea == 0 && inputPar == 0) {
            if (inputTeaNoClass > 0) {
                type = 4;
            }
        } else if (inputPar > 0 && inputTea == 0) {
            type = 1;
        } else if (inputPar == 0 && inputTea > 0) {
            type = 2;
        } else {
            type = 3;
        }
        userPreference.storeType(type);
    }

    public static boolean containValue(int[] arrays, int value) {
        for (int i : arrays) {
            if (i == value)
                return true;
        }
        return false;
    }

    public static int getFileImgResource(String string) {
        if (string.equalsIgnoreCase("doc") || string.equalsIgnoreCase("docx") || string.equalsIgnoreCase("common_onlineschool_word2")) {
            return R.mipmap.common_onlineschool_word;
        } else if (string.equalsIgnoreCase("common_onlineschool_ppt2") || string.equalsIgnoreCase("pptx")) {
            return R.mipmap.common_onlineschool_ppt;
        } else if (string.equalsIgnoreCase("xls") || string.equalsIgnoreCase("xlsx") || string.equalsIgnoreCase("common_onlineschool_excel2")) {
            return R.mipmap.common_onlineschool_excel;
        } else if (string.equalsIgnoreCase("common_onlineschool_txt2")) {
            return R.mipmap.common_onlineschool_txt;
        } else if (string.equalsIgnoreCase("common_onlineschool_pdf2")) {
            return R.mipmap.common_onlineschool_pdf;
        } else {
            return 0;
        }
    }

    public static int getFileBigImgResource(String string) {
        if (string.equalsIgnoreCase("doc") || string.equalsIgnoreCase("docx") || string.equalsIgnoreCase("common_onlineschool_word2")) {
            return R.mipmap.common_onlineschool_wordbig;
        } else if (string.equalsIgnoreCase("common_onlineschool_ppt2") || string.equalsIgnoreCase("pptx")) {
            return R.mipmap.common_onlineschool_pptbig;
        } else if (string.equalsIgnoreCase("xls") || string.equalsIgnoreCase("xlsx") || string.equalsIgnoreCase("common_onlineschool_excel2")) {
            return R.mipmap.common_onlineschool_excelbig;
        } else if (string.equalsIgnoreCase("common_onlineschool_txt2")) {
            return R.mipmap.common_onlineschool_txtbig;
        } else if (string.equalsIgnoreCase("common_onlineschool_pdf2")) {
            return R.mipmap.common_onlineschool_pdfbig;
        } else {
            return 0;
        }
    }

    public static int getFileBigImgResource2(String string) {
        if (string.equalsIgnoreCase("doc") || string.equalsIgnoreCase("docx") || string.equalsIgnoreCase("common_onlineschool_word2")) {
            return R.mipmap.common_onlineschool_word2;
        } else if (string.equalsIgnoreCase("common_onlineschool_ppt2") || string.equalsIgnoreCase("pptx")) {
            return R.mipmap.common_onlineschool_ppt2;
        } else if (string.equalsIgnoreCase("xls") || string.equalsIgnoreCase("xlsx") || string.equalsIgnoreCase("common_onlineschool_excel2")) {
            return R.mipmap.common_onlineschool_excel2;
        } else if (string.equalsIgnoreCase("common_onlineschool_txt2")) {
            return R.mipmap.common_onlineschool_txt2;
        } else if (string.equalsIgnoreCase("common_onlineschool_pdf2")) {
            return R.mipmap.common_onlineschool_pdf2;
        } else {
            return 0;
        }
    }

    public static String getFileType(String string) {
        if (string.equalsIgnoreCase("doc") || string.equalsIgnoreCase("docx")) {
            return "common_onlineschool_word2";
        } else if (string.equalsIgnoreCase("common_onlineschool_ppt2") || string.equalsIgnoreCase("pptx")) {
            return "PPT";
        } else if (string.equalsIgnoreCase("xls") || string.equalsIgnoreCase("xlsx")) {
            return "common_onlineschool_excel2";
        } else if (string.equalsIgnoreCase("common_onlineschool_txt2")) {
            return "common_onlineschool_txt2";
        } else if (string.equalsIgnoreCase("common_onlineschool_pdf2")) {
            return "common_onlineschool_pdf2";
        } else {
            return "";
        }
    }

    /**
     * 检查权限，如果在fragment里面调用，把当前fragment传进来，否则传null
     */
    public static boolean checkPermission(Activity context, Fragment fragment, String[] permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(permission[0]) != PackageManager.PERMISSION_GRANTED) {
                if (fragment != null) {
                    fragment.requestPermissions(permission, requestCode);
                } else {
                    context.requestPermissions(permission, requestCode);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 兼容包里的权限检查
     *
     * @param context
     * @param permission
     * @param requestCode
     * @return
     */
    public static boolean checkPermissionCompat(Activity context, String[] permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(context, permission[0]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context, permission, requestCode);
            return true;
        }
        return false;
    }


    public static void updateAlbum(Context context) {
        context.sendBroadcast(new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(new File(Global.filePath))));
    }

    /**
     * 获取压缩后的Options的inSampleSize值
     */
    public static int getBitmapSampleSize(int imageWidth, int imageHeight) {
        int inSampleSize;
        int i = 0;
        while (true) {
            if ((imageWidth >> i <= Global.imgWidth)
                    && (imageHeight >> i <= Global.imgHeight)) {
                inSampleSize = (int) Math.pow(2.0D, i);
                break;
            }
            i += 1;
        }

        return inSampleSize;
    }

    public static void setAlarmParams(Notification notification, UserPreference userPreference) {
        // AudioManager provides access to volume and ringer mode control.
        String str = userPreference.getAudio();
        if (str != null && !"".equals(str)) {
            int a = Integer.parseInt(str);
            switch (a) {// 获取系统设置的铃声模式
                case 0:// 静音模式，值为0，这时候不震动，不响铃
                    notification.sound = null;
                    notification.vibrate = null;
                    break;
                case 1:// 震动模式，值为1，这时候震动，不响铃
                    notification.sound = null;
                    notification.defaults |= Notification.DEFAULT_VIBRATE;
                    break;
                case 2:// 获取软件的设置
                    notification.defaults = Notification.DEFAULT_SOUND;
                    notification.vibrate = null;
                    break;
                case 3:// 声音加振动
                    notification.defaults |= Notification.DEFAULT_VIBRATE;
                    notification.defaults |= Notification.DEFAULT_SOUND;
                    break;
                default:
                    break;
            }
        }
    }

    public static long getFileSizes(File f) {
        long s = 0;
        FileInputStream fis = null;
        try {
            if (f.exists()) {
                fis = new FileInputStream(f);
                s = fis.available();
            } else {
                f.createNewFile();
            }
        } catch (Exception e) {

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return s;
    }

    public static void deleteDir(String imgPath) {
        try {
            File dir = new File(imgPath);
            if (dir == null || !dir.exists() || !dir.isDirectory() || dir.listFiles() == null)
                return;
            for (File file : dir.listFiles()) {
                if (file.isFile())
                    file.delete(); // 删除所有文件
                else if (file.isDirectory())
                    deleteDir(file.getAbsolutePath()); // 递规的方式删除文件夹
            }
            dir.delete();// 删除目录本身
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteFilesInDir(String path) {
        File dir = new File(path);
        if (dir == null || !dir.exists() || !dir.isDirectory() || dir.listFiles() == null)
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                file.delete(); // 删除所有文件
            } else {
                deleteFilesInDir(file.getAbsolutePath());
            }
        }
    }

    public static String getRealPathFromURI(Context context, Uri contentUri) {
        String res = "";
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        }
        return res;
    }

    public static Bitmap getRoundRectBitmap(Context context, Bitmap srcBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
        RectF rectF = new RectF(rect);
        float raidus = CommonTools.dip2px(context, 3);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.BLACK);
        canvas.drawRoundRect(rectF, raidus, raidus, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, rect, rect, paint);
        return bitmap;
    }

    public static void scanSingleFile(final Context mContext, String filePath) {
        if (CommonTools.isEmpty(filePath)) {
            return;
        }
        File file = new File(filePath);
        MediaScannerConnection.scanFile(mContext,
                new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    @Override
                    public void onScanCompleted(String path, Uri uri) {
                        ContentResolver cr = mContext.getContentResolver();
                        long datemodified = 0;
                        long dateadded = 0;
                        if (uri == null) {
                            return;
                        }
                        Cursor cursor = cr.query(uri, null, null, null, null);
                        if (cursor != null && cursor.moveToFirst()) {
                            datemodified = cursor.getLong(cursor
                                    .getColumnIndex(MediaStore.MediaColumns.DATE_MODIFIED));
                            dateadded = cursor.getLong(cursor
                                    .getColumnIndex(MediaStore.MediaColumns.DATE_ADDED));
                            cursor.close();
                        }
                        ContentValues values = new ContentValues();
                        if (datemodified > 0
                                && String.valueOf(datemodified).length() > 10) {
                            values.put(MediaStore.MediaColumns.DATE_MODIFIED,
                                    datemodified / 1000);
                        }
                        if (dateadded > 0
                                && String.valueOf(dateadded).length() > 13) {
                            values.put(MediaStore.MediaColumns.DATE_ADDED,
                                    dateadded / 1000);
                        }
                        if (values.size() > 0) {
                            cr.update(uri, values, null, null);
                        }
                    }
                });
    }

    /**
     * 显示软键盘
     */
    public static void showInput(EditText et_msg) {
        try {
            et_msg.setFocusable(true);
            et_msg.setFocusableInTouchMode(true);
            et_msg.requestFocus();
            InputMethodManager inputManager = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.showSoftInput(et_msg, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏软键盘
     */
    public static void hideInput(EditText et_msg) {
        try {
            InputMethodManager imm = (InputMethodManager) et_msg.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(et_msg.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把字符串里的回车和空格都替换成一个空格
     */
    public static String replaceEnterAndSpace(String content) {
        String str = new String(content);
        try {
            str = str.replaceAll("[\\n]+", " ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean getFileSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return true;
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return true;
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            String s = String.valueOf((size / 100)) + "." + String.valueOf((size % 100));
            float v = Float.valueOf(s).floatValue();
            if (v <= 2) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void updateFile(Context context, File file) {
        try {
            context.sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //移除字符串中的html
    public static String RemoveHtml(String strHtml) {
        String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(strHtml);
        strHtml = m_html.replaceAll(""); //过滤html标签
        return strHtml.trim(); //返回文本字符串
    }

    public static boolean containInt(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i])
                return true;
        }
        return false;
    }

    public static boolean containString(String[] array, String value) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (array[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断字符串是否是汉字、字母、数字
     *
     * @return
     */
    public static boolean isChineseLetterNumber(String character) {
        return character.matches("[\u4e00-\u9fa5\\w\\s]+");
    }

    public static Bitmap drawableToBitamp(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        } else if (drawable instanceof GlideBitmapDrawable) {
            GlideBitmapDrawable bitmapDrawable = (GlideBitmapDrawable) drawable;
            return bitmapDrawable.getBitmap();
        }
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 获取外存剩余空间
     *
     * @param context
     * @param path
     * @param isFormat 是否需要转化成KB,MB,GB这种类型
     * @return
     */
    public static String getExternalStorageRemain(Context context, String path, boolean isFormat) {
        long blockSize;
        long totalBlockCount;
        long avaiLabelCount;
        StatFs stat = new StatFs(path);

        //检测系统版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            //获取每个扇区的大小
            blockSize = stat.getBlockSizeLong();
            //获取总共有多少扇区
            totalBlockCount = stat.getBlockCountLong();
            //获取可用扇区数量
            avaiLabelCount = stat.getAvailableBlocksLong();
        } else {
            blockSize = stat.getBlockSize();
            totalBlockCount = stat.getBlockCount();
            avaiLabelCount = stat.getAvailableBlocks();
        }
        if (isFormat) {
            // 磁盘总大小
            String totalMemory = Formatter.formatFileSize(context, blockSize * totalBlockCount);
            // 可用大小
            String availabelMemory = Formatter.formatFileSize(context, blockSize * avaiLabelCount);
            return availabelMemory;
        }
        return blockSize * avaiLabelCount + "";
    }

    /**
     * 比较两个版本号，思路挺好
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
        if (version1.equals(version2)) {
            return 0;
        }
        String[] version1Array = version1.split("//.");
        String[] version2Array = version2.split("//.");
        int index = 0;
        //获取最小长度值
        int minLen = Math.min(version1Array.length, version2Array.length);
        int diff = 0;
        //循环判断每位的大小
        while (index < minLen && (diff = Integer.parseInt(version1Array[index]) - Integer.parseInt(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            //如果位数不一致，比较多余位数
            for (int i = index; i < version1Array.length; i++) {
                if (Integer.parseInt(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Integer.parseInt(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

    public static boolean isClassOwnerOrSchoolOwner(String owner) {
        return owner.equals("class") || owner.equals("school");
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @return 当前日期是星期几
     */
    public static int getWeekOfDate(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        if (w == 0) {
            w = 7;
        }
        return w;
    }

    @SuppressWarnings("deprecation")
    public static ArrayList<String> dateToWeek(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> week = new ArrayList<>();
        try {
            Date parse = sdf.parse(s);
            int b = parse.getDay();
            Date fdate;
            List<Date> list = new ArrayList<Date>();
            Long fTime = parse.getTime() - b * 24 * 3600000;
            for (int a = 1; a <= 7; a++) {
                fdate = new Date();
                fdate.setTime(fTime + (a * 24 * 3600000)); //一周从周日开始算，则使用此方式
                //fdate.setTime(fTime + ((a-1) * 24*3600000)); //一周从周一开始算，则使用此方式
                list.add(a - 1, fdate);
            }
            for (Date date : list) {
                week.add(sdf.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return week;
        }


    }

    @SuppressWarnings("deprecation")
    public static ArrayList<String> dateToLastWeek(int c) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
//n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
        int n = c;
        String monday;
        cal.add(Calendar.DATE, n * 7);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        monday = sdf.format(cal.getTime());
        ArrayList<String> week = new ArrayList<>();
        try {
            Date parse = sdf.parse(monday);
            int b = parse.getDay();
            Date fdate;
            List<Date> list = new ArrayList<Date>();
            Long fTime = parse.getTime() - b * 24 * 3600000;
            for (int a = 1; a <= 7; a++) {
                fdate = new Date();
                fdate.setTime(fTime + (a * 24 * 3600000)); //一周从周日开始算，则使用此方式
                //fdate.setTime(fTime + ((a-1) * 24*3600000)); //一周从周一开始算，则使用此方式
                list.add(a - 1, fdate);
            }
            for (Date date : list) {
                week.add(sdf.format(date));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return week;
        }


    }

    public static String getCurrentTimeForDaily() {
        Date nowTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");// 可以方便地修改日期格式
        String nowTimeStr = dateFormat.format(nowTime);
        return nowTimeStr;
    }

    public static String lastOrNextWeek(String data, int flag) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(data));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, flag); // 向前一周；如果需要向后一周，用正数即可
        return sdf.format(cal.getTime());
    }

    public static ArrayMap<String, String> getVideoInfo(String path) {
        ArrayMap<String, String> map = new ArrayMap<>(10);
        try {
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(path);
            String width = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH); // 视频宽度
            map.put("width", width);
            String height = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT); // 视频高度
            map.put("height", height);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                String rotation = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION); // 视频旋转方向
                map.put("rotation", rotation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String currentTime() {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String str0 = String.valueOf(hour);
        String str = String.valueOf(minute);
        String str1 = String.valueOf(second);
        if (hour < 10) {
            str0 = "0" + hour;
        }
        if (minute < 10) {
            str = "0" + minute;
        }
        if (second < 10) {
            str1 = "0" + second;
        }
        return str0 + ":" + str + ":" + str1;
    }

    public static void copy(String content, Context context) {
        try {
            ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData textCd = ClipData.newPlainText(content, content);
            cmb.setPrimaryClip(textCd);
            CommonTools.showToast("已复制到剪切板");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取当前的版本号
    public static String getVersionName() {
        // 获取packagemanager的实例
        PackageManager packageManager = Global.application.getPackageManager();
        PackageInfo packInfo = null;
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        try {
            packInfo = packageManager.getPackageInfo(Global.application.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    public static void createFile(File file) {
        if (file.isDirectory()) {
            if (!file.exists()) {
                file.mkdirs();
            }
        } else {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFile(File from, File to) {
        try {
            if (to.exists()) {
                return;
            } else {
                createFile(to);
            }
            InputStream is = new FileInputStream(from);
            FileOutputStream fos = new FileOutputStream(to);
            byte[] buffer = new byte[1024];
            while (is.read(buffer) != -1) {
                fos.write(buffer);
            }
            is.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * inputStream转outputStream
     *
     * @param is 输入流
     * @return outputStream子类
     */
    public static ByteArrayOutputStream input2OutputStream(InputStream is) {
        if (is == null) return null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b, 0, 1024)) != -1) {
                os.write(b, 0, len);
            }
            return os;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * inputStream转byteArr
     *
     * @param is 输入流
     * @return 字节数组
     */
    public static byte[] inputStream2Bytes(InputStream is) {
        if (is == null) return null;
        return input2OutputStream(is).toByteArray();
    }


    public static void click(View v, Consumer<Object> onNext) {
        RxView.clicks(v).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(onNext);
    }

}
