package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.juziwl.commonlibrary.db.DbHelper;


/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月29日
 * @description 管理班级动态的时间
 */
public class ClazzStartEndTimeManager {
    private String tableName = "STARTENDTIME";
    private static ClazzStartEndTimeManager clazzStartEndTimeManager = null;
    private static DbHelper dbHelper = null;

    private ClazzStartEndTimeManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context);
        }
    }

    public static ClazzStartEndTimeManager getInstance(Context context) {
        if (clazzStartEndTimeManager == null) {
            clazzStartEndTimeManager = new ClazzStartEndTimeManager(context);
        }
        return clazzStartEndTimeManager;
    }

    public String[] getTime(String uid, String classId) {
        synchronized (dbHelper) {
            Cursor cursor = null;
            String[] time = new String[3];
            try {
                String selection = "mid = ? and classId= ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = uid;
                selectionArgs[1] = classId;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection,
                        selectionArgs, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        time[0] = cursor.getString(cursor
                                .getColumnIndex("startTime"));
                        time[1] = cursor.getString(cursor
                                .getColumnIndex("endTime"));
                        time[2] = cursor.getString(cursor
                                .getColumnIndex("updateTime"));
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
            return time;
        }
    }

    public void updateTime(String uid, String classId, String startTime,
                           String endTime, String updateTime) {
        synchronized (dbHelper) {
            try {
                String a[] = new String[2];
                a[0] = uid;
                a[1] = classId;
                ContentValues value = new ContentValues();
                value.put("startTime", startTime);
                value.put("endTime", endTime);
                value.put("updateTime", updateTime);
                dbHelper.open();
                dbHelper.update(tableName, value, "mid=? and classId=?", a);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void insertStartEndUpdateTime(String mid, String classId,
                                         String startTime, String endTime, String updateTime) {
        synchronized (dbHelper) {
            try {
                ContentValues value = new ContentValues();
                value.put("mid", mid);
                value.put("classId", classId);
                value.put("startTime", startTime);
                value.put("endTime", endTime);
                value.put("updateTime", updateTime);
                dbHelper.open();
                dbHelper.insert(tableName, value);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteStartEndUpdateTime(String uid, String classId) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[2];
                delete[0] = uid;
                delete[1] = classId;
                dbHelper.open();
                dbHelper.delete(tableName, "mid = ? and classId = ?", delete);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteStartEndUpdateTimeAll(String uid) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[1];
                delete[0] = uid;
                dbHelper.open();
                dbHelper.delete(tableName, "mid = ?", delete);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public String getAllData() {
        synchronized (dbHelper) {
            Cursor cursor = null;
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------STARTENDTIME---------------------------------------\n");
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, null, null, null, null, "ID asc");
                if (cursor != null && cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        sb.append("mid='").append(cursor.getString(cursor.getColumnIndex("mid"))).append("',")
                                .append("classId='").append(cursor.getString(cursor.getColumnIndex("classId"))).append("',")
                                .append("startTime='").append(cursor.getString(cursor.getColumnIndex("startTime"))).append("',")
                                .append("endTime='").append(cursor.getString(cursor.getColumnIndex("endTime"))).append("',")
                                .append("updateTime='").append(cursor.getString(cursor.getColumnIndex("updateTime"))).append("'").append("\n");
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
