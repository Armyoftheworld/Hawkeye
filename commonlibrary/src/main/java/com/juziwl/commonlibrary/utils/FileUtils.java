
package com.juziwl.commonlibrary.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.StatFs;
import android.provider.MediaStore;
import android.support.v4.util.ArrayMap;
import android.text.format.Formatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {


    public static void deleteDir(String imgPath) {
        File dir = new File(imgPath);
        if (!dir.exists() || !dir.isDirectory() || dir.listFiles() == null)
            return;

        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDir(file.getAbsolutePath()); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
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
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            if (to.exists()) {
                to.delete();
            }
            createFile(to);
            is = new FileInputStream(from);
            fos = new FileOutputStream(to);
            int length;
            byte[] buffer = new byte[1024];
            while ((length = is.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeStream(is);
            IOUtil.closeStream(fos);
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

    public static void scanSingleFile(final Context mContext, String filePath) {
        if (StringUtils.isEmpty(filePath)) {
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

}
