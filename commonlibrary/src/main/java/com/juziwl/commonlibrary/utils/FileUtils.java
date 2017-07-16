
package com.juziwl.commonlibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import com.juziwl.commonlibrary.config.Global;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static final String FILE = "";
    public Context ctx;
    public static String imgPath = Global.filePath + "pickImgCache/";

    public FileUtils(Context ctx) {
        super();
        this.ctx = ctx;
    }

    public FileUtils() {
        super();
    }

    public static void delFile(String fileName) {
        File file = new File(imgPath + fileName);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
//		file.exists();
    }

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

    public static void saveBitmap(Bitmap bm, String picName) {
        if (bm != null) {
            try {
                if (!isFileExist("")) {
                    File tempf = createSDDir("");
                }
                File f = new File(imgPath, picName + ".JPEG");
                if (f.exists()) {
                    f.delete();
                }
                FileOutputStream out = new FileOutputStream(f);
                bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!bm.isRecycled()) {
                    bm.recycle();
                }
                System.gc();
            }
        }
    }

    public static void savePNGBitmap(Bitmap bm, String picName) {
        if (bm != null) {
            try {
                if (!isFileExist("")) {
                    createSDDir("");
                }
                File f = new File(imgPath, picName + ".png");
                if (f.exists()) {
                    f.delete();
                }
                FileOutputStream out = new FileOutputStream(f);
                bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!bm.isRecycled()) {
                    bm.recycle();
                }
                bm = null;
                System.gc();
            }
        }
    }

    public static File createSDDir(String dirName) throws IOException {
        File dir = new File(imgPath + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            dir.mkdir();
        }
        return dir;
    }

    public static boolean isFileExist(String fileName) {
        File file = new File(imgPath + fileName);
        file.isFile();
        return file.exists();
    }

//java文件操作之移动文件到指定的目录
    public static void  movePicToDir(File file, String fileDir) {
        try {
            File afile = file;
            if (afile.renameTo(new File(fileDir + CommonTools.getMsgCurrentTime().trim()+".png"))) {
                System.out.println("File is moved successful!");
                afile.delete();
            } else {
                System.out.println("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    // 删除文件夹下所有临时图片
    public static void recurDelete(File f){
        for(File fi:f.listFiles()){
            if(fi.isDirectory()){
                recurDelete(fi);
            }
            else{
                fi.delete();
            }
        }
        f.delete();
    }

    public static  void deleteAll(File path) {

        if (!path.exists())   //路径存在
            return;
        if (path.isFile()) {  //是文件
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            deleteAll(files[i]);
        }
        path.delete();
    }




}
