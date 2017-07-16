package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.ClassDynInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/29.
 *
 * @description 存储班级动态红点的状况
 */
public class ClassDynamicInfoManager {
    private String tableName = "CLASSDYNAMIC_NEW";
    private static ClassDynamicInfoManager cyim = null;
    private static DbHelper dbHelper = null;

    private ClassDynamicInfoManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context);
        }
    }

    public static ClassDynamicInfoManager getInstance(Context context) {
        if (cyim == null) {
            cyim = new ClassDynamicInfoManager(context);
        }
        return cyim;
    }

    public void insert(ClassDynInfo classDynInfo, String mid) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                dbHelper.getSqLiteDatabase().execSQL("delete from " + tableName + " where classId = '"
                        + classDynInfo.classId + "' and mid = '" + classDynInfo.mid + "'");
                ContentValues cv = new ContentValues();
                cv.put("classId", classDynInfo.classId);
                cv.put("mid", classDynInfo.mid);
                dbHelper.insert(tableName, cv);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }


    private boolean hasOne(ClassDynInfo classDynInfo, String mid) {
        synchronized (dbHelper) {
            String orderBy = "ID desc";
            boolean hasOne = false;
            Cursor checkCarCursor = null;
            try {
                dbHelper.open();
                checkCarCursor = dbHelper.select(tableName, null, "mid = ? and classId = ?", new String[]{mid, classDynInfo.classId}, null, null, orderBy, null);
                if (checkCarCursor != null && checkCarCursor.getCount() > 0) {
                    hasOne = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return hasOne;
        }
    }

    public List<ClassDynInfo> getList(String mid) {
        synchronized (dbHelper) {
            List<ClassDynInfo> list = new ArrayList<>();
            String orderBy = "ID desc";
            Cursor checkCarCursor = null;
            try {
                dbHelper.open();
                checkCarCursor = dbHelper.select(tableName, null, "mid=?", new String[]{mid}, null, null, orderBy, null);
                for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor
                        .moveToNext()) {
                    ClassDynInfo classDynInfo = new ClassDynInfo();
                    classDynInfo.mid = (checkCarCursor.getString(checkCarCursor.getColumnIndex("mid")));
                    classDynInfo.classId = (checkCarCursor.getString(checkCarCursor.getColumnIndex("classId")));
                    list.add(classDynInfo);
                }

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return list;
        }
    }

    public void deleteOne(String id, String mid) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                dbHelper.delete(tableName, "classId=? and mid=?", new String[]{id, mid});
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public String getAllData() {
        synchronized (dbHelper) {
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------CLASSDYNAMIC_NEW---------------------------------------\n");
            Cursor checkCarCursor = null;
            try {
                dbHelper.open();
                checkCarCursor = dbHelper.select(tableName, null, null, null, null, null, "ID asc");
                for (checkCarCursor.moveToFirst(); !checkCarCursor.isAfterLast(); checkCarCursor.moveToNext()) {
                    ClassDynInfo classDynInfo = new ClassDynInfo();
                    classDynInfo.mid = (checkCarCursor.getString(checkCarCursor.getColumnIndex("mid")));
                    classDynInfo.classId = (checkCarCursor.getString(checkCarCursor.getColumnIndex("classId")));
                    sb.append(classDynInfo.toString()).append("\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (checkCarCursor != null) {
                    checkCarCursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return sb.toString();
        }
    }
}
