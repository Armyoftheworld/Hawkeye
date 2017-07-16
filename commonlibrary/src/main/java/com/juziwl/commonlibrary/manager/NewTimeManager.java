package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.juziwl.commonlibrary.db.DbHelper;


public class NewTimeManager {
    private String tableName = "NEWTIME";

    private static NewTimeManager newTimeManager = null;

    private static DbHelper dbHelper = null;

    private NewTimeManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context);
        }
    }

    public static NewTimeManager getInstance(Context context) {
        if (newTimeManager == null) {
            newTimeManager = new NewTimeManager(context);
        }
        return newTimeManager;
    }

    public void insertTime(String uid, String time) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                ContentValues value = new ContentValues();
                value.put("uid", uid);
                value.put("time", time);
                dbHelper.insert(tableName, value);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void updateTime(String uid, String time) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                String updateKey[] = new String[1];
                updateKey[0] = "time";
                String updateValue[] = new String[1];
                updateValue[0] = time;
                String where = "uid = ?";
                String whereValue[] = new String[1];
                whereValue[0] = uid;
                dbHelper.open();
                dbHelper.update(tableName, updateKey, updateValue,
                        where, whereValue);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public String getTime(String uid) {
        String time = "";
        synchronized (dbHelper) {
            Cursor cursor = null;
            try {
                String selection = "uid =?";
                String selectionArg[] = new String[1];
                selectionArg[0] = uid;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection,
                        selectionArg, null, null, null);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                        .moveToNext()) {
                    int tutorScope = cursor.getColumnIndex("time");
                    time = cursor.getString(tutorScope);
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
        }
        return time;
    }

    public String getAllData() {
        synchronized (dbHelper) {
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------NEWTIME---------------------------------------\n");
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, null, null, null, null, "ID asc");
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    sb.append("time='").append(cursor.getString(cursor.getColumnIndex("time"))).append("',")
                            .append("uid='").append(cursor.getString(cursor.getColumnIndex("uid"))).append("'").append("\n");
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
            return sb.toString();
        }
    }
}
