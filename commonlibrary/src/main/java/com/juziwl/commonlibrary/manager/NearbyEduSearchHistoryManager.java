package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.SearchHistory;

import java.util.ArrayList;


/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年03月14日
 * @description 管理周边教育搜索记录
 */
public class NearbyEduSearchHistoryManager {
    private String tableName = "NEARBYEDUSEARCHHISTORY";
    private static NearbyEduSearchHistoryManager nearbyEduSearchHistoryManager = null;
    public static DbHelper cDbHelper = null;
    boolean flag = false;


    private NearbyEduSearchHistoryManager(Context context) {
        if (cDbHelper == null) {
            cDbHelper = DbHelper.getInstance(context);
        }
    }

    public static NearbyEduSearchHistoryManager getInstance(Context context) {
        if (nearbyEduSearchHistoryManager == null) {
            nearbyEduSearchHistoryManager = new NearbyEduSearchHistoryManager(context);
        }
        return nearbyEduSearchHistoryManager;
    }

    public ArrayList<SearchHistory> getAllHistory(String userid) {
        synchronized (cDbHelper) {
            ArrayList<SearchHistory> list = new ArrayList<>();
            Cursor cursor = null;
            try {
                String selection = "userid = ?";
                String selectionArgs[] = new String[1];
                selectionArgs[0] = userid;
                cDbHelper.open();
                cursor = cDbHelper.select(tableName, null, selection,
                        selectionArgs, null, null, "ID desc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        SearchHistory sh = new SearchHistory();
                        sh.hId = cursor.getLong(cursor.getColumnIndex("ID"));
                        sh.content = cursor.getString(cursor.getColumnIndex("content"));
                        sh.userId = userid;
                        list.add(sh);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
            return list;
        }
    }

    public void deleteHistory(Context context, String content, String userid) {
        ArrayList<SearchHistory> allHistory = NearbyEduSearchHistoryManager.getInstance(context).getAllHistory(userid);
        if (!allHistory.isEmpty()) {
            for (int i = 0; i < allHistory.size(); i++) {
                if (allHistory.get(i).content.equals(content)) {
                    NearbyEduSearchHistoryManager.getInstance(context).deleteOneHistory(allHistory.get(i).hId);
                }
            }

        }
    }

    public void insertHistory(String content, String userid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                ContentValues cv = new ContentValues();
                cv.put("userid", userid);
                cv.put("content", content);
                cDbHelper.insert(tableName, cv);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void deleteOneHistory(Long id) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                cDbHelper.delete(tableName, "ID = ?", new String[]{"" + id});
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }

        }
    }

    public void deleteAllHistory(String userid) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                cDbHelper.delete(tableName, "userid = ?", new String[]{userid});
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }

        }
    }
}
