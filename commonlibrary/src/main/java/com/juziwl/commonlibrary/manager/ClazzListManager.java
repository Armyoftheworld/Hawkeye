package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.Clazz;
import com.juziwl.commonlibrary.utils.PingYinUtil;

import java.util.ArrayList;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月22日
 * @description 管理班级数据库
 */
public class ClazzListManager {
    private String tableName = "CLAZZS";
    private static ClazzListManager clazzListManager = null;
    private static DbHelper dbHelper = null;

    private ClazzListManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context);
        }
    }

    public static ClazzListManager getInstance(Context context) {
        if (clazzListManager == null) {
            clazzListManager = new ClazzListManager(context);
        }
        return clazzListManager;
    }

    public ArrayList<Clazz> getAllClazz(String mid) {
        synchronized (dbHelper) {
            ArrayList<Clazz> clazzs = new ArrayList<>();
            Cursor cursor = null;
            try {
                String selection = "mid = ?";
                String selectionArgs[] = new String[1];
                selectionArgs[0] = mid;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        Clazz clazz = iteratorCursor(cursor);
                        clazzs.add(clazz);
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
            return clazzs;
        }
    }

    public ArrayList<Clazz> getAllClazzList(String mid, String flag) { //1 表示录入班级 2 表示开放班级
        synchronized (dbHelper) {
            ArrayList<Clazz> clazzs = new ArrayList<>();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and flag = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = flag;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, "schoolId asc,isTeacher asc,classNamePinyin asc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        Clazz clazz = iteratorCursor(cursor);
                        clazzs.add(clazz);
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
            return clazzs;
        }
    }

    public Clazz getOneClazz(String mid, String cid) {
        synchronized (dbHelper) {
            Clazz clazz = new Clazz();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and classId = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = cid;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        clazz = iteratorCursor(cursor);
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
            return clazz;
        }
    }

    public ArrayList<Clazz> getAllClazzByClazz(String mid, String classId) {
        synchronized (dbHelper) {
            ArrayList<Clazz> clazzs = new ArrayList<>();
            Cursor cursor = null;
            int i = 0;
            try {
                String selection = "mid = ? and classId = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = classId;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        Clazz clazz = iteratorCursor(cursor);
                        clazzs.add(clazz);
                        i++;
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
            return clazzs;
        }
    }

    public int getAllChild(String mid, String classId) {
        int number = 0;
        synchronized (dbHelper) {
            ArrayList<Clazz> clazzs = new ArrayList<>();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and classId = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = classId;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        Clazz clazz = iteratorCursor(cursor);
                        clazzs.add(clazz);
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

            for (int i = 0; i < clazzs.size(); i++) {
                if ("教师".equals(clazzs.get(i).role)) {
                    break;
                } else {
                    number += 1;
                }
            }
            return number;
        }
    }

    /**
     * 获取在当前班级任教的所有科目的第一个
     */
    public String getTeachingSubjects(String mid, String cid) {
        synchronized (dbHelper) {
            String builder = new String();
            Clazz clazz = new Clazz();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and classId = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = cid;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        clazz = iteratorCursor(cursor);
                        if (clazz.role.equals("教师")) {
                            builder = clazz.subjectName;
                            return builder;
                        }
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
            return builder;
        }
    }

    public void insertAllClazzs(ArrayList<Clazz> list, String mid, String flag) {
        synchronized (dbHelper) {
            SQLiteDatabase db = dbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into CLAZZS(userId,role,schoolId,schoolName,classId,className," +
                        "studentId,studentName,address,subjectName,subjectId,disflag,shiflag,isnickname," +
                        "classBlocked,flag,owner,classNo,mid,classNamePinyin,isTeacher,isChat) values(" +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                db.beginTransaction();
                for (int i = 0; i < list.size(); i++) {
                    Clazz clazz = list.get(i);
                    statement.bindString(1, clazz.userId);
                    statement.bindString(2, clazz.role);
                    statement.bindString(3, clazz.schoolId);
                    statement.bindString(4, clazz.schoolName);
                    statement.bindString(5, clazz.classId);
                    statement.bindString(6, clazz.className);
                    statement.bindString(7, clazz.studentId);
                    statement.bindString(8, clazz.studentName);
                    statement.bindString(9, clazz.address);
                    statement.bindString(10, clazz.subjectName);
                    statement.bindString(11, clazz.subjectId);
                    statement.bindString(12, clazz.chatFun);
                    statement.bindString(13, clazz.sendFun);
                    statement.bindString(14, "1");
                    statement.bindString(15, clazz.classBlocked);
                    statement.bindString(16, flag);
                    statement.bindString(17, clazz.owner);
                    statement.bindString(18, clazz.classNo);
                    statement.bindString(19, mid);
                    statement.bindString(20, PingYinUtil.getPingYin(clazz.className));
                    statement.bindLong(21, clazz.role.equals("教师") ? 1 : 0);
                    statement.bindLong(22, clazz.isChat);
                    statement.executeInsert();
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                // TODO: handle exception
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

    public void insertOneClazz(Clazz clazz, String mid, String flag) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                ContentValues value = new ContentValues();
                value.put("userId", clazz.userId);
                value.put("role", clazz.role);
                value.put("schoolId", clazz.schoolId);
                value.put("schoolName", clazz.schoolName);
                value.put("classId", clazz.classId);
                value.put("className", clazz.className);
                value.put("studentId", clazz.studentId);
                value.put("studentName", clazz.studentName);
                value.put("address", clazz.address);
                value.put("subjectName", clazz.subjectName);
                value.put("subjectId", clazz.subjectId);
                value.put("disflag", clazz.chatFun);
                value.put("shiflag", clazz.sendFun);
                value.put("isnickname", "1");
                value.put("classBlocked", clazz.classBlocked);
                value.put("owner", clazz.owner);
                value.put("classNo", clazz.classNo);
                value.put("flag", flag);
                value.put("mid", mid);
                value.put("isChat", clazz.isChat);
                value.put("classNamePinyin", PingYinUtil.getPingYin(clazz.className));
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

    public void updateClassBlocked(String classId, String classBlocked, String mid) {
        synchronized (dbHelper) {
            try {
                String a[] = new String[2];
                a[0] = mid;
                a[1] = classId;
                ContentValues value = new ContentValues();
                value.put("classBlocked", classBlocked);
                dbHelper.open();
                dbHelper.update(tableName, value, "mid=? and classId=? ",
                        a);

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void updateClassName(String classId, String className, String mid) {
        synchronized (dbHelper) {
            try {
                String a[] = new String[2];
                a[0] = mid;
                a[1] = classId;
                ContentValues value = new ContentValues();
                value.put("className", className);
                dbHelper.open();
                dbHelper.update(tableName, value, "mid=? and classId=? ",
                        a);

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }


    public void updateIsChat(String classId, int isChat, String mid) {
        synchronized (dbHelper) {
            try {
                String a[] = new String[2];
                a[0] = mid;
                a[1] = classId;
                ContentValues value = new ContentValues();
                value.put("isChat", isChat);
                dbHelper.open();
                dbHelper.update(tableName, value, "mid=? and classId=? ",
                        a);

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void updateClassSubject(String classId, String subjectId, String subjectName, String mid) {
        synchronized (dbHelper) {
            try {
                String a[] = new String[2];
                a[0] = mid;
                a[1] = classId;
                ContentValues value = new ContentValues();
                value.put("subjectId", subjectId);
                value.put("subjectName", subjectName);
                dbHelper.open();
                dbHelper.update(tableName, value, "mid=? and classId=? ",
                        a);

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void updateOneClazz(String classId, String mid, String disflag,
                               String shiflag, String isnickname) {
        synchronized (dbHelper) {
            try {
                String a[] = new String[2];
                a[0] = mid;
                a[1] = classId;
                ContentValues value = new ContentValues();
                value.put("disflag", disflag);
                value.put("shiflag", shiflag);
                value.put("isnickname", isnickname);
                dbHelper.open();
                dbHelper.update(tableName, value, "mid=? and classId=?",
                        a);

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }


    public void updateOneClazz(String classId, String mid, String disflag,
                               String shiflag) {
        synchronized (dbHelper) {
            try {
                String a[] = new String[2];
                a[0] = mid;
                a[1] = classId;
                ContentValues value = new ContentValues();
                value.put("disflag", disflag);
                value.put("shiflag", shiflag);
                dbHelper.open();
                dbHelper.update(tableName, value, "mid=? and classId=? ",
                        a);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteClazzs(String mid) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[1];
                delete[0] = mid;
                dbHelper.open();
                dbHelper.delete(tableName, "mid = ?", delete);

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteOneClazz(String mid, String clazzId) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[2];
                delete[0] = mid;
                delete[1] = clazzId;
                dbHelper.open();
                dbHelper.delete(tableName, "mid = ? and classId = ?", delete);

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public ArrayList<Clazz> getAllClassWithoutParent(String mid) {
        synchronized (dbHelper) {
            ArrayList<Clazz> clazzs = new ArrayList<Clazz>();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and ( role like ? or role like ? )";
                String selectionArgs[] = new String[3];
                selectionArgs[0] = mid;
                selectionArgs[1] = "教师";
                selectionArgs[2] = "open";
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        Clazz clazz = iteratorCursor(cursor);
                        clazzs.add(clazz);
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
            return clazzs;
        }
    }
    public ArrayList<Clazz> getAllClassOwnerInfo(String uid,String owner) {//得到班级或者学校负责人信息
        synchronized (dbHelper) {
            ArrayList<Clazz> clazzs = new ArrayList<Clazz>();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and owner = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = uid;
                selectionArgs[1] = owner;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection, selectionArgs,
                        null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        Clazz clazz = iteratorCursor(cursor);
                        clazzs.add(clazz);
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
            return clazzs;
        }
    }

    private Clazz iteratorCursor(Cursor cursor) {
        Clazz clazz = new Clazz();
        int userId = cursor.getColumnIndex("userId");
        int role = cursor.getColumnIndex("role");
        int schoolId = cursor.getColumnIndex("schoolId");
        int schoolName = cursor.getColumnIndex("schoolName");
        int classId = cursor.getColumnIndex("classId");
        int clazzName = cursor.getColumnIndex("className");
        int studentId = cursor.getColumnIndex("studentId");
        int studentName = cursor.getColumnIndex("studentName");
        int address = cursor.getColumnIndex("address");
        int subjectName = cursor.getColumnIndex("subjectName");
        int subjectId = cursor.getColumnIndex("subjectId");
        int disflag = cursor.getColumnIndex("disflag");
        int shiflag = cursor.getColumnIndex("shiflag");
        int isnickname = cursor.getColumnIndex("isnickname");
        int classBlocked = cursor.getColumnIndex("classBlocked");
        int owner = cursor.getColumnIndex("owner");
        int classNo = cursor.getColumnIndex("classNo");
        int isChat = cursor.getColumnIndex("isChat");
        clazz.userId = cursor.getString(userId);
        clazz.role = cursor.getString(role);
        clazz.schoolId = cursor.getString(schoolId);
        clazz.schoolName = cursor.getString(schoolName);
        clazz.classId = cursor.getString(classId);
        clazz.className = cursor.getString(clazzName);
        clazz.studentId = cursor.getString(studentId);
        clazz.studentName = cursor.getString(studentName);
        clazz.address = cursor.getString(address);
        clazz.subjectName = cursor.getString(subjectName);
        clazz.subjectId = cursor.getString(subjectId);
        clazz.chatFun = cursor.getString(disflag);
        clazz.sendFun = cursor.getString(shiflag);
        clazz.isnickname = cursor.getString(isnickname);
        clazz.classBlocked = cursor.getString(classBlocked);
        clazz.owner = cursor.getString(owner);
        clazz.classNo = cursor.getString(classNo);
        clazz.isChat = cursor.getInt(isChat);
        return clazz;
    }

    public String getAllData() {
        synchronized (dbHelper) {
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------CLAZZS---------------------------------------\n");
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, null, null, null, null, "ID asc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        Clazz clazz = iteratorCursor(cursor);
                        clazz.mid = cursor.getString(cursor.getColumnIndex("mid"));
                        sb.append(clazz.toString()).append("\n");
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
