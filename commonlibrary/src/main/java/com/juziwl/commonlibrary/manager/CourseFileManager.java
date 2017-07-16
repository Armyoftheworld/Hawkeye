package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.Article;

import java.util.ArrayList;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016/4/6
 * @description 我的课件数据库
 */
public class CourseFileManager {
    private static CourseFileManager instance = null;
    private DbHelper dbHelper = null;
    private final String tableName = "MYCOURSEFILE";

    private CourseFileManager(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
    }

    public static CourseFileManager getInstance(Context context) {
        if (instance == null) {
            instance = new CourseFileManager(context);
        }
        return instance;
    }

    public void insertCourseFile(Article article, String userId) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                ContentValues cv = new ContentValues();
                cv.put("fileName", article.fileName);
                cv.put("fileSize", article.fileSize);
                cv.put("filePath", article.s_pic);
                cv.put("userId", userId);
                cv.put("time", article.s_creater_time);
                cv.put("downloadUrl", article.s_url);
                cv.put("isLocalUpload", article.isLocalUpload);
                dbHelper.insert(tableName, cv);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public ArrayList<Article> getCourseFiles(@NonNull String userId) {
        synchronized (dbHelper) {
            ArrayList<Article> articles = new ArrayList<Article>();
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "userId = ? and isLocalUpload = 0", new String[]{userId}, null, null, "time desc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        Article article = new Article();
                        article.fileSize = cursor.getInt(cursor.getColumnIndex("fileSize"));
                        article.fileName = cursor.getString(cursor.getColumnIndex("fileName"));
                        article.s_pic = cursor.getString(cursor.getColumnIndex("filePath"));
                        article.s_creater_time = cursor.getString(cursor.getColumnIndex("time"));
                        article.s_url = cursor.getString(cursor.getColumnIndex("downloadUrl"));
                        article.p_id = cursor.getInt(cursor.getColumnIndex("ID"))+"";
                        articles.add(article);
                    }
                }
            } catch (Exception e) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return articles;
        }
    }

    public void deleteCourseFile(String finaName) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                dbHelper.delete(tableName, "fileName=?", new String[]{finaName});
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }


    public void updateDownloadUrl(String url,String ID){  // 本地文件ID传"1"
        synchronized (dbHelper){
            try {
                dbHelper.open();
                dbHelper.update(tableName, new String[]{"downloadUrl"}, new String[]{url}, "ID = ?", new String[]{ID});
            }catch (Exception e){
            }finally {
                if(dbHelper!=null){
                    dbHelper.close();
                }
            }

        }
    }

    public Article getFileInfo(String userId, String filePath) {
        synchronized (dbHelper) {
            Article article = new Article();
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "userId = ? and filePath = ?", new String[]{userId, filePath}, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    article.fileSize = cursor.getInt(cursor.getColumnIndex("fileSize"));
                    article.fileName = cursor.getString(cursor.getColumnIndex("fileName"));
                    article.s_pic = cursor.getString(cursor.getColumnIndex("filePath"));
                    article.s_creater_time = cursor.getString(cursor.getColumnIndex("time"));
                    article.s_url = cursor.getString(cursor.getColumnIndex("downloadUrl"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return article;
        }
    }

    public Article getFileInfo(String userId,String fileName,String url) {
        synchronized (dbHelper) {
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "userId = ? and fileName = ? and downloadUrl = ?", new String[]{userId,fileName,url}, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    Article article = new Article();
                    article.fileSize = cursor.getInt(cursor.getColumnIndex("fileSize"));
                    article.fileName = cursor.getString(cursor.getColumnIndex("fileName"));
                    article.s_pic = cursor.getString(cursor.getColumnIndex("filePath"));
                    article.s_creater_time = cursor.getString(cursor.getColumnIndex("time"));
                    article.s_url = cursor.getString(cursor.getColumnIndex("downloadUrl"));
                    return article;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return null;
        }
    }

    public void deleteFile(String userId,String fileName,String url){
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                dbHelper.delete(tableName, "userId = ? and fileName = ? and downloadUrl = ?", new String[]{userId,fileName,url});
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                dbHelper.close();
            }
        }
    }
}
