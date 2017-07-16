package com.juziwl.commonlibrary.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.juziwl.commonlibrary.R;
import com.orhanobut.logger.Logger;

/**
 * Created by ztn on
 * 数据库帮助类分装数据库操作
 */
public class DbHelper {
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    private static DbHelper openHelper = null;
    private static int version = 8;

    private static String myDBName = "exiaoxin.db";
    private final Context mCtx;

    public DbHelper(Context ctx) {
        Context applicationContext = ctx.getApplicationContext();

        this.mCtx = applicationContext;
    }

    public synchronized static DbHelper getInstance(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (openHelper == null) {
            openHelper = new DbHelper(applicationContext);
        }
        return openHelper;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        Context context;

        DatabaseHelper(Context context) {
            super(context, myDBName, null, version);
            Context applicationContext = context.getApplicationContext();
            this.context = applicationContext;
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DBManager.CREATE_TBL_USERMODEL);
            db.execSQL(DBManager.CREATE_TAB_WEIDUMSG);
            db.execSQL(DBManager.CREATE_TAB_SERVICE_ITEM);
            db.execSQL(DBManager.CREATE_TAB_CLAZZS);
            db.execSQL(DBManager.CREATE_TBL_USERINFOINFO);
            db.execSQL(DBManager.CREATE_TAB_SCHOOLNEWS);
            db.execSQL(DBManager.CREATE_TAB_CLAZZDYNAMIC);
            db.execSQL(DBManager.CREATE_TAB_STARTENDTIME);
            db.execSQL(DBManager.CREATE_TBL_HISMSG);
            db.execSQL(DBManager.CREATE_TBL_MSG);
            db.execSQL(DBManager.CREATE_TAB_NEARBYEDUSEARCHHISTORY);
            db.execSQL(DBManager.CREATE_TAB_CITYLIST);
            db.execSQL(DBManager.CREATE_TAB_NEWTIME);
            db.execSQL(DBManager.CREATE_TAB_ONLINESCHOOLARTICLE);
            db.execSQL(DBManager.CREATE_TAB_MYCOURSEFILE);
            db.execSQL(DBManager.CREATE_TAB_ONLINESCHOOL);
            db.execSQL(DBManager.CREATE_TAB_SNHIS);
            db.execSQL(DBManager.CREATE_TAB_CLASSDYNAMIC_NEW);
            db.execSQL(DBManager.CREATE_TAB_GONGZAI);
            db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
        }

        //把襄樊市改成襄阳市
        private void updateCity(SQLiteDatabase db) {
            db.execSQL("update CITYLIST set areaName = ?, pinyin = ? where areaid = ?", new String[]{"襄阳市", "XIANGYANG SHI", "208"});
            db.execSQL("update CITYLIST set parentCityName = ? where parentCityId = ?", new String[]{"襄阳市", "208"});
        }

        private void updateMyCourseFile(SQLiteDatabase db) {
            db.execSQL("ALTER TABLE MYCOURSEFILE RENAME TO MYCOURSEFILE_S");
            db.execSQL(DBManager.CREATE_TAB_MYCOURSEFILE);
            db.execSQL("insert into MYCOURSEFILE(ID,fileName,fileSize,filePath,userId,time,downloadUrl,isLocalUpload) select ID,fileName,fileSize,filePath,userId,time,\"\",0 from MYCOURSEFILE_S");
            db.execSQL("drop table MYCOURSEFILE_S");
        }

        private void updateClazz(SQLiteDatabase db) {
            db.execSQL("ALTER TABLE CLAZZS RENAME TO CLAZZS_S");
            db.execSQL(DBManager.CREATE_TAB_CLAZZS);
            db.execSQL("insert into CLAZZS(ID,userId,role,schoolId,schoolName,classId,className," +
                    "studentId,studentName,address,subjectName,subjectId,disflag,shiflag,isnickname,classBlocked," +
                    "flag,pingyin,owner,classNo,classNamePinyin,isTeacher,mid,isChat) " +
                    "select ID,userId,role,schoolId,schoolName,classId,className," +
                    "studentId,studentName,address,subjectName,subjectId,disflag,shiflag,isnickname,classBlocked," +
                    "flag,pingyin,owner,classNo,classNamePinyin,isTeacher,mid,1 from CLAZZS_S");
            db.execSQL("drop table CLAZZS_S");
        }

        private void updateBabyWatchName(SQLiteDatabase db) {
            db.execSQL("update SERVICE_ITEM set itemName = ? where itemName = ?", new String[]{context.getString(R.string.common_babywatch), "宝宝直播"});
        }

        private void deleteClassDynamicData(SQLiteDatabase db) {
            db.delete("CLAZZDYNAMIC", null, null);
        }

        private void updateClazzdynamic(SQLiteDatabase db) {
            db.execSQL("drop table CLAZZDYNAMIC");
            db.execSQL(DBManager.CREATE_TAB_CLAZZDYNAMIC);
        }

        private void addNewIcon(SQLiteDatabase db) {
            Cursor userCursor = db.rawQuery("select distinct userId from SERVICE_ITEM", null);
            if (userCursor != null && userCursor.getCount() > 0) {
                for (userCursor.moveToFirst(); !userCursor.isAfterLast(); userCursor.moveToNext()) {
                    String userId = userCursor.getString(userCursor.getColumnIndex("userId"));
                    Cursor cursor = db.rawQuery("select count(*) as c from SERVICE_ITEM where selected = 1 and userId = ?", new String[]{userId});
                    if (cursor != null && cursor.moveToFirst()) {
                        int count = cursor.getInt(cursor.getColumnIndex("c"));
                        db.execSQL("update SERVICE_ITEM set orderId = " + (count + 1)
                                + " where orderId = " + (count - 1) + " and userId = '" + userId + "'");
                        db.execSQL("insert into SERVICE_ITEM(ID, itemName, orderId, isHasNew, selected, userId) " +
                                "values(16, '" + context.getString(R.string.common_menu) + "', " + (count - 1) + ", 0, 1, '" + userId + "')");
                        db.execSQL("insert into SERVICE_ITEM(ID, itemName, orderId, isHasNew, selected, userId) " +
                                "values(17, '" + context.getString(R.string.common_circle) + "', " + count + ", 0, 1, '" + userId + "')");
                    }
                }
                userCursor.close();
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Logger.e("oldVersion=" + oldVersion + "\tnewVersion=" + newVersion);
            if (oldVersion == 1) {
                db.delete("SERVICE_ITEM", null, null);
                db.execSQL(DBManager.CREATE_TAB_CLASSDYNAMIC_NEW);
                updateMyCourseFile(db);
                updateClazz(db);
                updateCity(db);
                db.execSQL(DBManager.CREATE_TAB_GONGZAI);
//                updateBabyWatchName(db);
                updateClazzdynamic(db);

                db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
            } else if (oldVersion == 2) {
                db.delete("SERVICE_ITEM", null, null);
                db.execSQL(DBManager.CREATE_TAB_CLASSDYNAMIC_NEW);
                updateMyCourseFile(db);
                updateClazz(db);
                updateCity(db);
                db.execSQL(DBManager.CREATE_TAB_GONGZAI);
//                updateBabyWatchName(db);
                db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
                updateClazzdynamic(db);
            } else if (oldVersion == 3) {
                db.delete("SERVICE_ITEM", null, null);
                updateMyCourseFile(db);
                updateClazz(db);
                updateCity(db);
                db.execSQL(DBManager.CREATE_TAB_GONGZAI);
//                updateBabyWatchName(db);
                db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
                updateClazzdynamic(db);
            } else if (oldVersion == 4) {
                db.delete("SERVICE_ITEM", null, null);
                updateCity(db);
                db.execSQL(DBManager.CREATE_TAB_GONGZAI);
//                updateBabyWatchName(db);
                updateClazzdynamic(db);
                db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
            } else if (oldVersion == 5) {
                db.delete("SERVICE_ITEM", null, null);
                db.execSQL(DBManager.CREATE_TAB_GONGZAI);
//                updateBabyWatchName(db);
                updateClazzdynamic(db);

                db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
            } else if (oldVersion == 6) {
                updateBabyWatchName(db);
                updateClazzdynamic(db);
                db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
                addNewIcon(db);
            } else if (oldVersion == 7) {
                updateClazzdynamic(db);
                db.execSQL(DBManager.CREATE_TAB_PROVINCECITYDISTRICT);
                addNewIcon(db);
            }
        }
    }

    public synchronized DbHelper open() throws SQLException {
        if (mDbHelper == null) {
            mDbHelper = new DatabaseHelper(mCtx);
        }
        try {
            mDb = mDbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void close() {
        try {
            if (mDb != null && mDb.isOpen()) {
                mDb.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public SQLiteDatabase getSqLiteDatabase() {
        try {
            if (mDb != null && mDb.isOpen()) {
                return mDb;
            } else {
                if (mDbHelper == null) {
                    mDbHelper = new DatabaseHelper(mCtx);
                }
                try {
                    mDb = mDbHelper.getWritableDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mDb;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public int queryId() {
        int id = 0;
        try {
            mDb.beginTransaction();
            String sql = "select _id from MSG order by _id desc";
            Cursor cursor = mDb.rawQuery(sql, null);
            cursor.moveToFirst();
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                mDb.endTransaction();
            } catch (Exception e2) {
                // TODO: handle exception
            }
        }
        return id;
    }

    public Cursor select(String table, String[] columns, String selection,
                         String[] selectionArgs, String groupBy, String having,
                         String orderBy) {
        Cursor cursor = null;
        try {
            mDb.beginTransaction();
            cursor = mDb.query(table, columns, selection, selectionArgs,
                    groupBy, having, orderBy);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            mDb.endTransaction();
        }
        return cursor;
    }

    public Cursor select(String table, String[] columns, String selection,
                         String[] selectionArgs, String groupBy, String having,
                         String orderBy, String limit) {
        Cursor cursor = null;
        try {
            mDb.beginTransaction();
            cursor = mDb.query(table, columns, selection, selectionArgs,
                    groupBy, having, orderBy, limit);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            mDb.endTransaction();
        }
        return cursor;
    }

    public long insert(String table, String fields[], String[] modelFieldtypes) {
        long count = 0;
        try {
            mDb.beginTransaction();
            ContentValues cv = new ContentValues();
            for (int i = 0; i < fields.length; i++) {
                cv.put(fields[i], modelFieldtypes[i]);
            }
            count = mDb.insert(table, null, cv);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDb.endTransaction();
        }
        return count;
    }

    //只要没有报错都是插入成功。一般会返回执行的sql语句影响的记录数
    public long insert(String table, ContentValues cv) {
        long count = 0;
        try {
            mDb.beginTransaction();
            count = mDb.insert(table, null, cv);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDb.endTransaction();
        }
        return count;
    }

    public boolean delete(String tableName, String where, String[] whereValue) {
        boolean flag = false;
        try {
            int count = mDb.delete(tableName, where, whereValue);
            flag = (count > 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return flag;
    }

    public int update(String table, String updateFields[],
                      String updateValues[], String where, String[] whereValue) {
        ContentValues cv = new ContentValues();
        for (int i = 0; i < updateFields.length; i++) {
            cv.put(updateFields[i], updateValues[i]);
        }
        int count = 0;
        try {
            count = mDb.update(table, cv, where, whereValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean update(String tableName, ContentValues cv, String where,
                          String[] whereValue) {
        boolean flag = false;
        try {
            flag = mDb.update(tableName, cv, where, whereValue) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean update(String tableName, String whereClause,
                          ContentValues args) {
        boolean flag = false;
        try {
            flag = mDb.update(tableName, args, whereClause, null) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Cursor queryMsgLists(String mid) {
        Cursor cursor = null;
        try {
            mDb.beginTransaction();
            cursor = mDb.rawQuery("select * from MSG where whoid = '" + mid
                    + "' group by clazzid,chatflag", null);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDb.endTransaction();
        }
        return cursor;
    }

    public void clearFeedTable(String tableName) {
        String sql = "DELETE FROM " + tableName + ";";
        mDb = mDbHelper.getWritableDatabase();
        mDb.execSQL(sql);
        revertSeq(tableName);
    }

    private void revertSeq(String tableName) {
        String sql = "update sqlite_sequence set seq=0 where name='"
                + tableName + "'";
        mDb = mDbHelper.getWritableDatabase();
        mDb.execSQL(sql);
    }

}
