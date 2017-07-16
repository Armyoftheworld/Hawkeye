package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.ClassDynamic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月28日
 * @description 管理班级动态数据库
 */
public class ClassDynamicManager {
    private String tableName = "CLAZZDYNAMIC";
    private ArrayList<ClassDynamic> generalList;
    private static ClassDynamicManager clazzTimeManager = null;
    public static DbHelper cDbHelper = null;

    private ClassDynamicManager(Context context) {
        if (cDbHelper == null) {
            cDbHelper = DbHelper.getInstance(context);
        }
    }

    public static ClassDynamicManager getInstance(Context context) {
        if (clazzTimeManager == null) {
            clazzTimeManager = new ClassDynamicManager(context);
        }
        return clazzTimeManager;
    }

    public ArrayList<ClassDynamic> getAllClassDynamicList(String mid) {
        synchronized (cDbHelper) {
            Cursor cursor = null;
            try {
                generalList = new ArrayList<ClassDynamic>();
                String selection = "mid = ?";
                String selectionArgs[] = new String[1];
                selectionArgs[0] = mid;
                cDbHelper.open();
                cursor = cDbHelper.select(tableName, null, selection,
                        selectionArgs, null, null, null);
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        ClassDynamic clazzTime = new ClassDynamic();
                        int userId = cursor.getColumnIndex("userId");
                        int createTime = cursor.getColumnIndex("createTime");
                        int isFavourite = cursor.getColumnIndex("isFavourite");
                        int eventImage = cursor.getColumnIndex("eventImage");
                        int eventInfo = cursor.getColumnIndex("eventInfo");
                        int fullName = cursor.getColumnIndex("fullName");
                        int avatar = cursor.getColumnIndex("avatar");
                        int classId = cursor.getColumnIndex("classId");
                        int favouriteNum = cursor.getColumnIndex("favouriteNum");
                        int commentNum = cursor.getColumnIndex("commentNum");
                        int eventId = cursor.getColumnIndex("eventId");
                        int className = cursor.getColumnIndex("className");
                        int redNum = cursor.getColumnIndex("redNum");
                        int giftNum = cursor.getColumnIndex("giftNum");
                        int isSendScore = cursor.getColumnIndex("isSendScore");
                        int isSendGift = cursor.getColumnIndex("isSendGift");
                        int videoUrl = cursor.getColumnIndex("videoUrl");
                        int videoPicUrl = cursor.getColumnIndex("videoPicUrl");
                        int videoSize = cursor.getColumnIndex("videoSize");
                        int isPinglun = cursor.getColumnIndex("isPinglun");
                        clazzTime.userId = cursor.getString(userId);
                        clazzTime.createTime = cursor.getString(createTime)
                                .substring(0, 19);
                        clazzTime.isFavourite = cursor.getString(isFavourite);
                        clazzTime.eventImage = cursor.getString(eventImage);
                        clazzTime.eventInfo = cursor.getString(eventInfo);
                        clazzTime.fullName = cursor.getString(fullName);
                        clazzTime.avatar = cursor.getString(avatar);
                        clazzTime.classId = cursor.getString(classId);
                        clazzTime.eventId = cursor.getString(eventId);
                        clazzTime.className = cursor.getString(className);
                        clazzTime.favouriteNum = cursor.getString(favouriteNum);
                        clazzTime.commentNum = cursor.getString(commentNum);
                        clazzTime.type = cursor.getString(cursor.getColumnIndex("type"));
                        clazzTime.scoreCount = cursor.getString(redNum);
                        clazzTime.giftCount = cursor.getString(giftNum);
                        clazzTime.isSendScore = cursor.getString(isSendScore);
                        clazzTime.isSendGift = cursor.getString(isSendGift);
                        clazzTime.videoUrl = cursor.getString(videoUrl);
                        clazzTime.videoPicUrl = cursor.getString(videoPicUrl);
                        clazzTime.videoSize = cursor.getString(videoSize);
                        clazzTime.isPinglun = cursor.getString(isPinglun);
                        generalList.add(clazzTime);
                    }
                }
            } catch (Exception e) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
            return generalList;
        }
    }

    public ArrayList<ClassDynamic> getPortionClassDynamicList(String mid,
                                                              int currentPage, int count, String clazzId, int newdata) {
        synchronized (cDbHelper) {

            Cursor cursor = null;
            try {
                generalList = new ArrayList<ClassDynamic>();
                String selection = "mid = ? and classId = ?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = clazzId;
                String orderBy = "createTime desc";
                String limit = String.valueOf((currentPage - 1) * count
                        + newdata)
                        + "," + String.valueOf(count);
                cDbHelper.open();
                cursor = cDbHelper.select(tableName, null, selection,
                        selectionArgs, null, null, orderBy, limit);
                if (cursor != null && cursor.getCount() > 0) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        ClassDynamic clazzTime = new ClassDynamic();
                        int userId = cursor.getColumnIndex("userId");
                        int createTime = cursor.getColumnIndex("createTime");
                        int isFavourite = cursor.getColumnIndex("isFavourite");
                        int eventImage = cursor.getColumnIndex("eventImage");
                        int eventInfo = cursor.getColumnIndex("eventInfo");
                        int fullName = cursor.getColumnIndex("fullName");
                        int avatar = cursor.getColumnIndex("avatar");
                        int classId = cursor.getColumnIndex("classId");
                        int favouriteNum = cursor
                                .getColumnIndex("favouriteNum");
                        int commentNum = cursor.getColumnIndex("commentNum");
                        int eventId = cursor.getColumnIndex("eventId");
                        int className = cursor.getColumnIndex("className");
                        int redNum = cursor.getColumnIndex("redNum");
                        int giftNum = cursor.getColumnIndex("giftNum");
                        int isSendScore = cursor.getColumnIndex("isSendScore");
                        int isSendGift = cursor.getColumnIndex("isSendGift");
                        int videoUrl = cursor.getColumnIndex("videoUrl");
                        int videoPicUrl = cursor.getColumnIndex("videoPicUrl");
                        int videoSize = cursor.getColumnIndex("videoSize");
                        int isPinglun = cursor.getColumnIndex("isPinglun");

                        clazzTime.userId = cursor.getString(userId);
                        clazzTime.createTime = cursor.getString(createTime)
                                .substring(0, 19);
                        clazzTime.isFavourite = cursor.getString(isFavourite);
                        clazzTime.eventImage = cursor.getString(eventImage);
                        clazzTime.eventInfo = cursor.getString(eventInfo);
                        clazzTime.fullName = cursor.getString(fullName);
                        clazzTime.avatar = cursor.getString(avatar);
                        clazzTime.classId = cursor.getString(classId);
                        clazzTime.eventId = cursor.getString(eventId);
                        clazzTime.className = cursor.getString(className);
                        clazzTime.favouriteNum = cursor.getString(favouriteNum);
                        clazzTime.commentNum = cursor.getString(commentNum);
                        clazzTime.type = cursor.getString(cursor.getColumnIndex("type"));
                        clazzTime.scoreCount = cursor.getString(redNum);
                        clazzTime.giftCount = cursor.getString(giftNum);
                        clazzTime.isSendScore = cursor.getString(isSendScore);
                        clazzTime.isSendGift = cursor.getString(isSendGift);
                        clazzTime.videoUrl = cursor.getString(videoUrl);
                        clazzTime.videoPicUrl = cursor.getString(videoPicUrl);
                        clazzTime.videoSize = cursor.getString(videoSize);
                        clazzTime.isPinglun = cursor.getString(isPinglun);
                        generalList.add(clazzTime);
                    }
                    Collections.sort(generalList, new Comparator<ClassDynamic>() {
                        @Override
                        public int compare(ClassDynamic c1, ClassDynamic c2) {
                            return c2.createTime.compareTo(c1.createTime);
                        }
                    });
                }
            } catch (Exception e) {
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
            return generalList;
        }
    }

    public void insertClassDynamic(ClassDynamic clazzTime, String mid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                ContentValues value = new ContentValues();
                value.put("userId", clazzTime.userId);
                value.put("createTime", clazzTime.createTime.substring(0, 19));
                value.put("isFavourite", clazzTime.isFavourite);
                value.put("eventInfo", clazzTime.eventInfo);
                value.put("avatar", clazzTime.avatar);
                value.put("eventImage", clazzTime.eventImage);
                value.put("classId", clazzTime.classId);
                value.put("fullName", clazzTime.fullName);
                value.put("className", clazzTime.className);
                value.put("commentNum", clazzTime.commentNum);
                value.put("favouriteNum", clazzTime.favouriteNum);
                value.put("eventId", clazzTime.eventId);
                value.put("mid", mid);
                value.put("type", clazzTime.type);
                value.put("redNum", clazzTime.scoreCount);
                value.put("giftNum", clazzTime.giftCount);
                value.put("isSendScore", clazzTime.isSendScore);
                value.put("isSendGift", clazzTime.isSendGift);
                value.put("videoUrl", clazzTime.videoUrl);
                value.put("videoPicUrl", clazzTime.videoPicUrl);
                value.put("videoSize", clazzTime.videoSize);
                value.put("isPinglun", clazzTime.isPinglun);

                cDbHelper.insert(tableName, value);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void insertAllClassDynamic(ArrayList<ClassDynamic> list, String mid) {
        synchronized (cDbHelper) {
            SQLiteDatabase db = cDbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into " + tableName + "(userId,createTime,eventId,isFavourite,eventInfo," +
                        "fullName,avatar,eventImage,classId,favouriteNum,commentNum,className,mid,type,redNum,giftNum,isSendScore,isSendGift,videoUrl,videoPicUrl,videoSize,isPinglun) " +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                db.beginTransaction();
                for (int i = 0; i < list.size(); i++) {
                    ClassDynamic clazzTime = list.get(i);
                    statement.bindString(1, clazzTime.userId);
                    statement.bindString(2, clazzTime.createTime.substring(0, 19));
                    statement.bindString(3, clazzTime.eventId);
                    statement.bindString(4, clazzTime.isFavourite);
                    statement.bindString(5, clazzTime.eventInfo);
                    statement.bindString(6, clazzTime.fullName);
                    statement.bindString(7, clazzTime.avatar);
                    statement.bindString(8, clazzTime.eventImage);
                    statement.bindString(9, clazzTime.classId);
                    statement.bindString(10, clazzTime.favouriteNum);
                    statement.bindString(11, clazzTime.commentNum);
                    statement.bindString(12, clazzTime.className);
                    statement.bindString(13, mid);
                    statement.bindString(14, clazzTime.type);
                    statement.bindString(15, clazzTime.scoreCount);//红包
                    statement.bindString(16, clazzTime.giftCount);//礼物
                    statement.bindString(17, clazzTime.isSendScore);
                    statement.bindString(18, clazzTime.isSendGift);
                    statement.bindString(19, clazzTime.videoUrl);
                    statement.bindString(20, clazzTime.videoPicUrl);
                    statement.bindString(21, clazzTime.videoSize);
                    statement.bindString(22, clazzTime.isPinglun);
                    statement.executeInsert();
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
            } finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateClassDynamic(ArrayList<ClassDynamic> list, String mid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                for (int i = 0; i < list.size(); i++) {
                    ClassDynamic clazzTime = list.get(i);
                    if (clazzTime.isDelete.equals("0")) {
                        continue;
                    }
                    String a[] = new String[2];
                    a[0] = mid;
                    a[1] = clazzTime.eventId;
                    ContentValues value = new ContentValues();
                    value.put("userId", clazzTime.userId);
                    value.put("createTime",
                            clazzTime.createTime.substring(0, 19));
                    value.put("eventId", clazzTime.eventId);
                    value.put("isFavourite", clazzTime.isFavourite);
                    value.put("eventInfo", clazzTime.eventInfo);
                    value.put("fullName", clazzTime.fullName);
                    value.put("avatar", clazzTime.avatar);
                    value.put("eventImage", clazzTime.eventImage);
                    value.put("classId", clazzTime.classId);
                    value.put("favouriteNum", clazzTime.favouriteNum);
                    value.put("commentNum", clazzTime.commentNum);
                    value.put("className", clazzTime.className);
                    value.put("type", clazzTime.type);
                    value.put("redNum", clazzTime.scoreCount);
                    value.put("giftNum", clazzTime.giftCount);
                    value.put("isSendGift", clazzTime.isSendGift);
                    value.put("isSendScore", clazzTime.isSendScore);
                    value.put("videoUrl", clazzTime.videoUrl);
                    value.put("videoPicUrl", clazzTime.videoPicUrl);
                    value.put("videoSize", clazzTime.videoSize);
                    value.put("isPinglun", clazzTime.isPinglun);
                    cDbHelper
                            .update(tableName, value, "mid=? and eventId=?", a);
                }
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateZan(String id, String isFavourite, String favouriteNum,
                          String mid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                String a[] = new String[2];
                a[0] = mid;
                a[1] = id;
                ContentValues cv = new ContentValues();
                cv.put("isFavourite", isFavourite);
                cv.put("favouriteNum", favouriteNum);
                cDbHelper.update(tableName, cv, "mid=? and eventId=?", a);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateCommentNum(String id, String commentNum, String mid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                String a[] = new String[2];
                a[0] = mid;
                a[1] = id;
                ContentValues cv = new ContentValues();
                cv.put("commentNum", commentNum);
                cDbHelper.update(tableName, cv, "mid=? and eventId=?", a);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateRedNum(String id, String redNum, String mid) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                String a[] = new String[2];
                a[0] = mid;
                a[1] = id;
                ContentValues cv = new ContentValues();
                cv.put("redNum", redNum);
                boolean flag = cDbHelper.update(tableName, cv, "userId=? and eventId=?", a);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateRedState(String id, String isSendScore, String mid) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                String a[] = new String[2];
                a[0] = mid;
                a[1] = id;
                ContentValues cv = new ContentValues();
                cv.put("isSendScore", isSendScore);
                boolean flag = cDbHelper.update(tableName, cv, "userId=? and eventId=?", a);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateGiftState(String id, String isSendGift, String mid) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                String a[] = new String[2];
                a[0] = mid;
                a[1] = id;
                ContentValues cv = new ContentValues();
                cv.put("isSendGift", isSendGift);
                boolean flag = cDbHelper.update(tableName, cv, "userId=? and eventId=?", a);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateGiftNum(String id, String commentNum, String mid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                String a[] = new String[2];
                a[0] = mid;
                a[1] = id;
                ContentValues cv = new ContentValues();
                cv.put("giftNum", commentNum);
                cDbHelper.update(tableName, cv, "userId=? and eventId=?", a);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateEvent(String eventId, String isFavourite,
                            String favouriteNum, String commentNum, String mid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                String a[] = new String[2];
                a[0] = mid;
                a[1] = eventId;

                ContentValues cv = new ContentValues();
                cv.put("commentNum", commentNum);
                cv.put("isFavourite", isFavourite);
                cv.put("favouriteNum", favouriteNum);

                cDbHelper.update(tableName, cv, "mid=? and eventId=?", a);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void deleteClassDynamic(String eventId, String mid) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                String delete[] = new String[2];
                delete[0] = eventId;
                delete[1] = mid;
                cDbHelper.delete(tableName, "eventId=? and mid = ?", delete);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void deleteAllClassDynamic(String uid) {
        synchronized (cDbHelper) {

            try {
                cDbHelper.open();
                String delete[] = new String[1];
                delete[0] = uid;
                cDbHelper.delete(tableName, "mid=?", delete);

            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateUserName(String mid, String oldname, String newname) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                String[] n = new String[2];
                n[0] = mid;
                n[1] = oldname;
                ContentValues cv = new ContentValues();
                cv.put("fullName", newname);
                cDbHelper.update(tableName, cv, "userId = ? and fullName=?", n);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public void updateUserImageUrl(String mid, String newUrl) {
        synchronized (cDbHelper) {
            try {
                cDbHelper.open();
                String[] n = new String[1];
                n[0] = mid;
                ContentValues cv = new ContentValues();
                cv.put("avatar", newUrl);
                cDbHelper.update(tableName, cv, "userId = ?", n);
            } catch (Exception e) {
            } finally {
                if (cDbHelper != null) {
                    cDbHelper.close();
                }
            }
        }
    }

    public String getAllData() {
        synchronized (cDbHelper) {
            Cursor cursor = null;
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------CLAZZDYNAMIC---------------------------------------\n");
            try {
                cDbHelper.open();
                cursor = cDbHelper.select(tableName, null, null, null, null, null, "ID asc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                            .moveToNext()) {
                        ClassDynamic clazzTime = new ClassDynamic();
                        int userId = cursor.getColumnIndex("userId");
                        int createTime = cursor.getColumnIndex("createTime");
                        int isFavourite = cursor.getColumnIndex("isFavourite");
                        int eventImage = cursor.getColumnIndex("eventImage");
                        int eventInfo = cursor.getColumnIndex("eventInfo");
                        int fullName = cursor.getColumnIndex("fullName");
                        int avatar = cursor.getColumnIndex("avatar");
                        int classId = cursor.getColumnIndex("classId");
                        int favouriteNum = cursor.getColumnIndex("favouriteNum");
                        int commentNum = cursor.getColumnIndex("commentNum");
                        int eventId = cursor.getColumnIndex("eventId");
                        int className = cursor.getColumnIndex("className");
                        int redNum = cursor.getColumnIndex("redNum");
                        int giftNum = cursor.getColumnIndex("giftNum");
                        int isSendScore = cursor.getColumnIndex("isSendScore");
                        int isSendGift = cursor.getColumnIndex("isSendGift");
                        int videoUrl = cursor.getColumnIndex("videoUrl");
                        int videoPicUrl = cursor.getColumnIndex("videoPicUrl");
                        int videoSize = cursor.getColumnIndex("videoSize");
                        int isPinglun = cursor.getColumnIndex("isPinglun");
                        clazzTime.userId = cursor.getString(userId);
                        clazzTime.createTime = cursor.getString(createTime).substring(0, 19);
                        clazzTime.isFavourite = cursor.getString(isFavourite);
                        clazzTime.eventImage = cursor.getString(eventImage);
                        clazzTime.eventInfo = cursor.getString(eventInfo);
                        clazzTime.fullName = cursor.getString(fullName);
                        clazzTime.avatar = cursor.getString(avatar);
                        clazzTime.classId = cursor.getString(classId);
                        clazzTime.eventId = cursor.getString(eventId);
                        clazzTime.className = cursor.getString(className);
                        clazzTime.favouriteNum = cursor.getString(favouriteNum);
                        clazzTime.commentNum = cursor.getString(commentNum);
                        clazzTime.type = cursor.getString(cursor.getColumnIndex("type"));
                        clazzTime.mid = cursor.getString(cursor.getColumnIndex("mid"));
                        clazzTime.scoreCount = cursor.getString(redNum);
                        clazzTime.giftCount = cursor.getString(giftNum);
                        clazzTime.isSendScore = cursor.getString(isSendScore);
                        clazzTime.isSendGift = cursor.getString(isSendGift);
                        clazzTime.videoUrl = cursor.getString(videoUrl);
                        clazzTime.videoPicUrl = cursor.getString(videoPicUrl);
                        clazzTime.videoSize = cursor.getString(videoSize);
                        clazzTime.isPinglun = cursor.getString(isPinglun);
                        sb.append(clazzTime.toString()).append("\n");
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
            return sb.toString();
        }
    }
}
