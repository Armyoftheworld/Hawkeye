package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.Article;

import java.util.ArrayList;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016/3/29
 * @description 操作线上学堂文章表
 */
public class OnlineSchoolArticleManager {
    private static OnlineSchoolArticleManager instance = null;
    private static DbHelper dbHelper = null;
    private final String tableName = "ONLINESCHOOLARTICLE";

    private OnlineSchoolArticleManager(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
    }

    public static OnlineSchoolArticleManager getInstance(Context context) {
        if (instance == null) {
            instance = new OnlineSchoolArticleManager(context);
        }
        return instance;
    }

    //isUploadFile   0：不是上传的文件    1：是上传的文件
    public void insertAllOnlineSchoolArticle(ArrayList<Object> articles, String userId, int isCollection) {
        synchronized (dbHelper) {
            SQLiteDatabase db = dbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into " + tableName + "(p_id,s_title,s_author,s_pic,s_category,s_tag," +
                        "s_abstract,s_html,s_url,s_type,f_groups_id,f_groups_layer,s_isdel,s_read_num," +
                        "s_click_num,s_forward_num,s_collect_num,s_like_num,s_adv_num,s_reward_num," +
                        "s_creator,s_creater_time,s_update_time,s_isPerm,mid,fileName,fileSize,platId," +
                        "platName,platImg,isUploadFile,collectId)values(?,?,?,?,?,?,?,?,?,?,?," +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                db.beginTransaction();
                for (Object object : articles) {
                    if (object instanceof Article) {
                        Article article = (Article) object;
                        executeInsert(statement, article, userId, isCollection);
                    } else {
                        ArrayList<Article> list = (ArrayList<Article>) object;
                        for (Article article : list) {
                            executeInsert(statement, article, userId, isCollection);
                        }
                    }
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {

            } finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    private void executeInsert(SQLiteStatement statement, Article article, String userId, int isCollection) {
        statement.bindString(1, article.p_id);
        statement.bindString(2, article.s_title);
        statement.bindString(3, article.s_author);
        statement.bindString(4, article.s_pic);
        statement.bindString(5, article.s_category);
        statement.bindString(6, article.s_tag);
        statement.bindString(7, article.s_abstract);
        statement.bindString(8, article.s_html);
        statement.bindString(9, article.s_url);
        statement.bindString(10, article.s_type);
        statement.bindString(11, article.f_groups_id);
        statement.bindString(12, article.f_groups_layer);
        statement.bindString(13, article.s_isdel);
        statement.bindLong(14, article.s_read_num);
        statement.bindLong(15, article.s_click_num);
        statement.bindLong(16, article.s_forward_num);
        statement.bindLong(17, article.s_collect_num);
        statement.bindLong(18, article.s_like_num);
        statement.bindLong(19, article.s_adv_num);
        statement.bindLong(20, article.s_reward_num);
        statement.bindString(21, article.s_creator);
        statement.bindString(22, article.s_creater_time);
        statement.bindString(23, article.s_update_time);
        statement.bindLong(24, article.s_isPerm);
        statement.bindString(25, userId);
        statement.bindString(26, article.fileName);
        statement.bindLong(27, article.fileSize);
        statement.bindString(28, article.platId);
        statement.bindString(29, article.platName);
        statement.bindString(30, article.platImg);
        statement.bindLong(31, isCollection);
        statement.bindString(32, article.collectId);
        statement.executeInsert();
    }

    public ArrayList<Object> getAllArticle(@NonNull String userId, @NonNull String pid) {
        synchronized (dbHelper) {
            ArrayList<Object> list = new ArrayList<Object>();
            ArrayList<Article> articles = new ArrayList<Article>();
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "mid = ? and platId = ? and isUploadFile=0", new String[]{userId, pid}, null, null, "f_groups_id desc , f_groups_layer asc");
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    Article article = getArticleItem(cursor);

                    if (article.s_type.equals("0")) {//如果是图文
                        if (articles.isEmpty()) {
                            articles.add(article);
                        } else {
                            if (articles.get(articles.size() - 1).f_groups_id.equals(article.f_groups_id)) {
                                articles.add(article);
                            } else {
                                list.add(articles);
                                articles = new ArrayList<Article>();
                                articles.add(article);
                            }
                        }
                        if (cursor.isLast()) {
                            list.add(articles);
                        }
                    } else {//视频或课件
                        if (!articles.isEmpty()) {
                            list.add(articles);
                            articles = new ArrayList<Article>();
                        }
                        list.add(article);
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
            return list;
        }
    }

    public void deleteSchoolArticle(@NonNull String userId, String pid) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                if (TextUtils.isEmpty(pid)) {
                    dbHelper.delete(tableName, "mid = ? and isUploadFile=0", new String[]{userId});
                } else {
                    dbHelper.delete(tableName, "mid = ? and platId = ? and isUploadFile=0", new String[]{userId, pid});
                }
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void updateArticle(@NonNull String userId, @NonNull String articleId, String collectId) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                ContentValues cv = new ContentValues();
                cv.put("collectId", collectId);
                dbHelper.update(tableName, cv, "mid = ? and p_id = ?", new String[]{userId, articleId});
            } catch (Exception e) {

            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public String getNewestArticleTime(@NonNull String userId, @NonNull String platId) {
        synchronized (dbHelper) {
            Cursor cursor = null;
            String time = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "mid = ? and platId = ? and isUploadFile=0", new String[]{userId, platId}, null, null, "f_groups_id desc", "0,1");
                if (cursor != null && cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    time = cursor.getString(cursor.getColumnIndex("f_groups_id"));
                } else {
                    time = "";
                }
            } catch (Exception e) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                dbHelper.close();
            }
            return time;
        }
    }

    public ArrayList<Object> getArticlesByPage(String mid, String pid, int newData, int currentPage, int count) {
        synchronized (dbHelper) {
            ArrayList<Object> list = new ArrayList<>();
            ArrayList<Article> articles = new ArrayList<>();
            Cursor cursor = null;
            try {
                String limit = (newData + (currentPage - 1) * count) + "," + count;
                String sql = "select * from " + tableName + " where f_groups_id in " +
                        "(select DISTINCT f_groups_id from " + tableName + " where mid = ? and platId = ? " +
                        "group by f_groups_id order by f_groups_id desc limit " + limit + ") and mid = ? " +
                        "order by f_groups_id desc,f_groups_layer asc";
                cursor = dbHelper.open().getSqLiteDatabase().rawQuery(sql, new String[]{mid, pid, mid});
                if (cursor != null && cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        Article article = getArticleItem(cursor);
                        if (article.s_type.equals("0")) {//如果是图文
                            if (articles.isEmpty()) {
                                articles.add(article);
                            } else {
                                if (articles.get(articles.size() - 1).f_groups_id.equals(article.f_groups_id)) {
                                    articles.add(article);
                                } else {
                                    list.add(articles);
                                    articles = new ArrayList<>();
                                    articles.add(article);
                                }
                            }
                            if (cursor.isLast()) {
                                list.add(articles);
                            }
                        } else {//视频或课件
                            if (!articles.isEmpty()) {
                                list.add(articles);
                                articles = new ArrayList<>();
                            }
                            list.add(article);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                dbHelper.close();
            }
            return list;
        }
    }

    private Article getArticleItem(Cursor cursor) {
        Article article = new Article();
        article.p_id = cursor.getString(cursor.getColumnIndex("p_id"));
        article.s_title = cursor.getString(cursor.getColumnIndex("s_title"));
        article.s_author = cursor.getString(cursor.getColumnIndex("s_author"));
        article.s_pic = cursor.getString(cursor.getColumnIndex("s_pic"));
        article.s_category = cursor.getString(cursor.getColumnIndex("s_category"));
        article.s_tag = cursor.getString(cursor.getColumnIndex("s_tag"));
        article.s_abstract = cursor.getString(cursor.getColumnIndex("s_abstract"));
        article.s_html = cursor.getString(cursor.getColumnIndex("s_html"));
        article.s_url = cursor.getString(cursor.getColumnIndex("s_url"));
        article.s_type = cursor.getString(cursor.getColumnIndex("s_type"));
        article.f_groups_id = cursor.getString(cursor.getColumnIndex("f_groups_id"));
        article.f_groups_layer = cursor.getString(cursor.getColumnIndex("f_groups_layer"));
        article.s_isdel = cursor.getString(cursor.getColumnIndex("s_isdel"));
        article.s_creator = cursor.getString(cursor.getColumnIndex("s_creator"));
        article.s_creater_time = cursor.getString(cursor.getColumnIndex("s_creater_time"));
        article.s_update_time = cursor.getString(cursor.getColumnIndex("s_update_time"));
        article.s_read_num = cursor.getInt(cursor.getColumnIndex("s_read_num"));
        article.s_click_num = cursor.getInt(cursor.getColumnIndex("s_click_num"));
        article.s_forward_num = cursor.getInt(cursor.getColumnIndex("s_forward_num"));
        article.s_collect_num = cursor.getInt(cursor.getColumnIndex("s_collect_num"));
        article.s_like_num = cursor.getInt(cursor.getColumnIndex("s_like_num"));
        article.s_adv_num = cursor.getInt(cursor.getColumnIndex("s_adv_num"));
        article.s_reward_num = cursor.getInt(cursor.getColumnIndex("s_reward_num"));
        article.s_isPerm = cursor.getInt(cursor.getColumnIndex("s_isPerm"));
        article.platId = cursor.getString(cursor.getColumnIndex("platId"));
        article.platName = cursor.getString(cursor.getColumnIndex("platName"));
        article.platImg = cursor.getString(cursor.getColumnIndex("platImg"));
        article.collectId = cursor.getString(cursor.getColumnIndex("collectId"));
        article.fileName = cursor.getString(cursor.getColumnIndex("fileName"));
        article.fileSize = cursor.getInt(cursor.getColumnIndex("fileSize"));
        article.mid = cursor.getString(cursor.getColumnIndex("mid"));
        return article;
    }

    public String getAllData() {
        synchronized (dbHelper) {
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------ONLINESCHOOLARTICLE---------------------------------------\n");
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, null, null, null, null, "ID asc");
                if (cursor != null && cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        Article article = getArticleItem(cursor);
                        article.mid = cursor.getString(cursor.getColumnIndex("mid"));
                        sb.append(article.toString()).append("\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                dbHelper.close();
            }
            return sb.toString();
        }
    }
}
