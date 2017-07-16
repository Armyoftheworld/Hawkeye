package com.juziwl.commonlibrary.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.juziwl.commonlibrary.config.Global;
import com.juziwl.commonlibrary.db.DbHelper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月21日
 * @description 未读消息数据库管理
 */
public class WeiDuMsgManager {
    private String tableName = "WEIDUMSG";
    private static WeiDuMsgManager weiDuMsgManager = null;
    private static DbHelper dbHelper = null;

    private WeiDuMsgManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context);
        }
    }

    public static WeiDuMsgManager getInstance(Context context) {
        if (weiDuMsgManager == null) {
            weiDuMsgManager = new WeiDuMsgManager(context);
        }
        return weiDuMsgManager;
    }

    public HashMap<String, Integer> getAllWeiDuMsgList(String mid) {
        synchronized (dbHelper) {
            Cursor cursor = null;
            try {
                String selection = "mid = ? ";
                String selectionArgs[] = new String[1];
                selectionArgs[0] = mid;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection,
                        selectionArgs, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        int uuid = cursor.getColumnIndex("uid");
                        int count = cursor.getColumnIndex("count");
                        Global.UpdateMsg.put(cursor.getString(uuid),
                                Integer.parseInt(cursor.getString(count)));
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return Global.UpdateMsg;
        }
    }

    public void deleteAll(String uid) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[1];
                delete[0] = uid;
                dbHelper.open();
                dbHelper.delete(tableName, "mid=?", delete);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void insertAllWeiDuMsg(HashMap<String, Integer> updateMsg, String mid) {
        synchronized (dbHelper) {
            SQLiteDatabase db = dbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into " + tableName + "(uid,count,mid) values(?,?,?)";
                SQLiteStatement stat = db.compileStatement(sql);
                db.beginTransaction();
                Set set = updateMsg.entrySet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry me = (Map.Entry) it.next();
                    stat.bindString(1, me.getKey() + "");
                    stat.bindString(2, me.getValue() + "");
                    stat.bindString(3, mid);
                    stat.executeInsert();
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

    public void insertOneWeiDuMsg(HashMap<String, Integer> updateMsg, String mid) {
        synchronized (dbHelper) {
            SQLiteDatabase db = dbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into " + tableName + "(uid,count,mid) values(?,?,?)";
                SQLiteStatement stat = db.compileStatement(sql);
                db.beginTransaction();
                Set set = updateMsg.entrySet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry me = (Map.Entry) it.next();
                    stat.bindString(1, me.getKey() + "");
                    stat.bindString(2, me.getValue() + "");
                    stat.bindString(3, mid);
                    stat.executeInsert();
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

}
