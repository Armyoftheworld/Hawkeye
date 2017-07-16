package com.juziwl.commonlibrary.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.News;
import com.juziwl.commonlibrary.model.OutNews;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月24日
 * @description 管理校信资讯数据库
 */
public class NewsManager {
    private String table = "SCHOOLNEWS";
    private static NewsManager schoolNewsManager = null;
    private static DbHelper dbHelper = null;

    private NewsManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context);
        }
    }

    public static NewsManager getInstance(Context context) {
        if (schoolNewsManager == null) {
            schoolNewsManager = new NewsManager(context);
        }
        return schoolNewsManager;
    }

    public ArrayList<News> getAllXXNewsList(String mid, String flag) {
        synchronized (dbHelper) {
            Cursor cursor = null;
            ArrayList<News> generalList = new ArrayList<>();
            try {
                String selection = "mid = ? and flag=?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = flag;
                String orderBy = "news_time desc";
                dbHelper.open();
                cursor = dbHelper.select(table, null, selection, selectionArgs,
                        null, null, orderBy);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        News news = new News();
                        int news_code = cursor.getColumnIndex("news_code");
                        int news_url = cursor.getColumnIndex("news_url");
                        int news_picUrl2 = cursor
                                .getColumnIndex("news_picUrl2");
                        int news_picUrl = cursor.getColumnIndex("news_picUrl");
                        int news_title = cursor.getColumnIndex("news_title");
                        int news_time = cursor.getColumnIndex("news_time");
                        int desc = cursor.getColumnIndex("desc");
                        news.newsCode = String
                                .valueOf(cursor.getInt(news_code));
                        news.url = cursor.getString(news_url);
                        news.picUrl2 = cursor.getString(news_picUrl2);
                        news.picUrl = cursor.getString(news_picUrl);
                        news.newsTitle = cursor.getString(news_title);
                        news.newsTime = cursor.getString(news_time).substring(
                                0, 19);
                        news.desc = cursor.getString(desc);
                        generalList.add(news);
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
            return generalList;
        }
    }

    public static ArrayList<OutNews> groupByDate(ArrayList<News> generalList) {
        ArrayList<OutNews> outNews = new ArrayList<OutNews>();
        // 从数据库中获取要导入的结果数据
        ArrayList<News> list = generalList;

        // 定义一个map集合用于分组
        Map<String, ArrayList<News>> mapList = new HashMap<String, ArrayList<News>>();
        // 遍历集合以date为键，以chinese为值保存到mapList中
        for (Iterator it = list.iterator(); it.hasNext(); ) {
            // 将循环读取的结果放入对象中
            News synsetcn = (News) it.next();

            // 如果在这个map中包含有相同的键，这创建一个集合将其存起来
            if (mapList.containsKey(synsetcn.newsTime.substring(0, 10))) {
                mapList.get(synsetcn.newsTime.substring(0, 10)).add(synsetcn);
                // 如果没有包含相同的键，在创建一个集合保存数据
            } else {
                ArrayList<News> syns = new ArrayList<News>();
                syns.add(synsetcn);
                mapList.put(synsetcn.newsTime.substring(0, 10), syns);
            }
        }
        List<Map.Entry<String, ArrayList<News>>> mappingList = new ArrayList<Map.Entry<String, ArrayList<News>>>(
                mapList.entrySet());
        Collections.sort(mappingList,
                new Comparator<Map.Entry<String, ArrayList<News>>>() {
                    public int compare(
                            Map.Entry<String, ArrayList<News>> mapping1,
                            Map.Entry<String, ArrayList<News>> mapping2) {
                        return mapping2.getKey().compareTo(mapping1.getKey());
                    }
                });
        // 遍历map集合
        for (Map.Entry<String, ArrayList<News>> m : mappingList) {
            // 获取所有的值
            ArrayList<News> synList = m.getValue();
            OutNews oNews = new OutNews();
            oNews.listNews = synList;
            outNews.add(oNews);
        }
        return outNews;
    }

    public ArrayList<News> getPortionXxNewsList(String mid, int currentPage,
                                                int count, String flag, int newdata) {
        synchronized (dbHelper) {
            ArrayList<News> generalList = new ArrayList<News>();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and flag = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = flag;
                String orderBy = "news_time desc";
                String limit = String.valueOf((currentPage - 1) * count
                        + newdata)
                        + "," + String.valueOf(count);
                dbHelper.open();
                cursor = dbHelper.select(table, null, selection, selectionArgs,
                        null, null, orderBy, limit);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        News news = new News();
                        int news_code = cursor.getColumnIndex("news_code");
                        int news_url = cursor.getColumnIndex("news_url");
                        int news_picUrl2 = cursor
                                .getColumnIndex("news_picUrl2");
                        int news_picUrl = cursor.getColumnIndex("news_picUrl");
                        int news_title = cursor.getColumnIndex("news_title");
                        int news_time = cursor.getColumnIndex("news_time");
                        int desc = cursor.getColumnIndex("desc");
                        news.newsCode = String
                                .valueOf(cursor.getInt(news_code));
                        news.url = cursor.getString(news_url);
                        news.picUrl2 = cursor.getString(news_picUrl2);
                        news.picUrl = cursor.getString(news_picUrl);
                        news.newsTitle = cursor.getString(news_title);
                        news.newsTime = cursor.getString(news_time).substring(
                                0, 19);
                        news.desc = cursor.getString(desc);
                        generalList.add(news);
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
            return generalList;
        }
    }

    public void insertAllXXNews(ArrayList<News> list, String mid, String flag) {
        synchronized (dbHelper) {
            SQLiteDatabase db = dbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into " + table + "(news_code,news_url,news_picUrl2,news_picUrl," +
                        "news_title,desc,news_time,flag,mid) values(?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                db.beginTransaction();
                for (int i = 0; i < list.size(); i++) {
                    News news = list.get(i);
                    statement.bindString(1, news.newsCode);
                    statement.bindString(2, news.url);
                    statement.bindString(3, news.picUrl2);
                    statement.bindString(4, news.picUrl);
                    statement.bindString(5, news.newsTitle);
                    statement.bindString(6, news.desc);
                    statement.bindString(7, news.newsTime.substring(0, 19));
                    statement.bindString(8, flag);
                    statement.bindString(9, mid);
                    statement.executeInsert();
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

    public void deleteXXNews(String date, String mid, String flag) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                String delete[] = new String[3];
                delete[0] = "%" + date + "%";
                delete[1] = mid;
                delete[2] = flag;
                dbHelper.open();
                dbHelper.delete(table,
                        "news_time like ? and mid = ? and flag=?", delete);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteAllXXNews(String mid) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[1];
                delete[0] = mid;
                dbHelper.open();
                dbHelper.delete(table, "mid = ?", delete);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public boolean deleteIdXXNews(String id, String mid, String flag) {
        boolean f = false;
        synchronized (dbHelper) {
            try {
                String delete[] = new String[3];
                delete[0] = mid;
                delete[1] = id;
                delete[2] = flag;
                dbHelper.open();
                f = dbHelper.delete(table, "mid = ? and news_code = ? and flag = ?", delete);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
        return f;
    }

    public String getAllData() {
        synchronized (dbHelper) {
            Cursor cursor = null;
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------SCHOOLNEWS---------------------------------------\n");
            try {
                dbHelper.open();
                cursor = dbHelper.select(table, null, null, null, null, null, "ID asc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        News news = new News();
                        int news_code = cursor.getColumnIndex("news_code");
                        int news_url = cursor.getColumnIndex("news_url");
                        int news_picUrl2 = cursor.getColumnIndex("news_picUrl2");
                        int news_picUrl = cursor.getColumnIndex("news_picUrl");
                        int news_title = cursor.getColumnIndex("news_title");
                        int news_time = cursor.getColumnIndex("news_time");
                        int desc = cursor.getColumnIndex("desc");
                        news.newsCode = String.valueOf(cursor.getInt(news_code));
                        news.url = cursor.getString(news_url);
                        news.picUrl2 = cursor.getString(news_picUrl2);
                        news.picUrl = cursor.getString(news_picUrl);
                        news.newsTitle = cursor.getString(news_title);
                        news.newsTime = cursor.getString(news_time).substring(0, 19);
                        news.desc = cursor.getString(desc);
                        news.flag = cursor.getString(cursor.getColumnIndex("flag"));
                        news.mid = cursor.getString(cursor.getColumnIndex("mid"));
                        sb.append(news.toString()).append("\n");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return sb.toString();
        }
    }

}
