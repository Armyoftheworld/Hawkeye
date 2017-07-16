package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v4.util.ArrayMap;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.AreaMap;
import com.juziwl.commonlibrary.model.OnlineSchool;
import com.juziwl.commonlibrary.utils.CommonTools;
import com.juziwl.commonlibrary.utils.PingYinUtil;

import java.util.ArrayList;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016/4/12
 * @description 操作线上学堂数据库
 */
public class OnlineSchoolManager {
    private static OnlineSchoolManager instance = null;
    private DbHelper dbHelper = null;
    private final String tableName = "ONLINESCHOOL";

    public static OnlineSchoolManager getInstance(Context context) {
        if (instance == null) {
            instance = new OnlineSchoolManager(context);
        }
        return instance;
    }

    private OnlineSchoolManager(Context context) {
        if (dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
    }

    public ArrayList<OnlineSchool> getAllOnlineSchool(String userId) {
        synchronized (dbHelper) {
            ArrayList<OnlineSchool> schools = new ArrayList<>();
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "userId = ?", new String[]{userId}, null, null, "pinyin asc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        OnlineSchool onlineSchool = new OnlineSchool();
                        onlineSchool.isFollow = cursor.getString(cursor.getColumnIndex("isFollow"));
                        onlineSchool.s_auth_status = cursor.getString(cursor.getColumnIndex("s_auth_status"));
                        onlineSchool.receiveMsg = cursor.getInt(cursor.getColumnIndex("receiveMsg"));
                        onlineSchool.p_id = cursor.getString(cursor.getColumnIndex("p_id"));
                        onlineSchool.s_account = cursor.getString(cursor.getColumnIndex("s_account"));
                        onlineSchool.s_addr = cursor.getString(cursor.getColumnIndex("s_addr"));
                        onlineSchool.s_area = cursor.getString(cursor.getColumnIndex("s_area"));
                        onlineSchool.s_auth_time = cursor.getString(cursor.getColumnIndex("s_auth_time"));
                        onlineSchool.s_blance = cursor.getString(cursor.getColumnIndex("s_blance"));
                        onlineSchool.s_city = cursor.getString(cursor.getColumnIndex("s_city"));
                        onlineSchool.s_create_time = cursor.getString(cursor.getColumnIndex("s_create_time"));
                        onlineSchool.s_desc = cursor.getString(cursor.getColumnIndex("s_desc"));
                        onlineSchool.s_grand = cursor.getString(cursor.getColumnIndex("s_grand"));
                        onlineSchool.s_identity = cursor.getString(cursor.getColumnIndex("s_identity"));
                        onlineSchool.s_img = cursor.getString(cursor.getColumnIndex("s_img"));
                        onlineSchool.s_isAudit = cursor.getString(cursor.getColumnIndex("s_isAudit"));
                        onlineSchool.s_login_time = cursor.getString(cursor.getColumnIndex("s_login_time"));
                        onlineSchool.s_mail = cursor.getString(cursor.getColumnIndex("s_mail"));
                        onlineSchool.s_name = cursor.getString(cursor.getColumnIndex("s_name"));
                        onlineSchool.s_phone = cursor.getString(cursor.getColumnIndex("s_phone"));
                        onlineSchool.s_point = cursor.getString(cursor.getColumnIndex("s_point"));
                        onlineSchool.s_principal = cursor.getString(cursor.getColumnIndex("s_principal"));
                        onlineSchool.s_province = cursor.getString(cursor.getColumnIndex("s_province"));
                        onlineSchool.s_rank = cursor.getString(cursor.getColumnIndex("s_rank"));
                        onlineSchool.s_report_num = cursor.getString(cursor.getColumnIndex("s_report_num"));
                        onlineSchool.s_state = cursor.getString(cursor.getColumnIndex("s_state"));
                        onlineSchool.s_type = cursor.getString(cursor.getColumnIndex("s_type"));
                        onlineSchool.s_update_time = cursor.getString(cursor.getColumnIndex("s_update_time"));
                        onlineSchool.s_user_num = cursor.getString(cursor.getColumnIndex("s_user_num"));
                        onlineSchool.s_xiaoxin_code = cursor.getString(cursor.getColumnIndex("s_xiaoxin_code"));
                        onlineSchool.tedian = cursor.getString(cursor.getColumnIndex("tedian"));
                        onlineSchool.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                        onlineSchool.staticPath = cursor.getString(cursor.getColumnIndex("staticPath"));
                        onlineSchool.msgNum = cursor.getInt(cursor.getColumnIndex("msgNum"));
                        schools.add(onlineSchool);
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
            return schools;
        }
    }

    public void insertAllOnlineSchool(ArrayList<OnlineSchool> schools, String userId) {
        synchronized (dbHelper) {
            SQLiteDatabase db = dbHelper.open().getSqLiteDatabase();
            try {
                String sql = "insert into " + tableName + "(p_id,s_mail,s_account,s_phone,s_xiaoxin_code,s_name,s_img,s_type,s_principal,s_identity," +
                        "s_auth_status,s_auth_time,s_desc,s_blance,s_province,s_city,s_area,s_addr,s_state,s_login_time,s_create_time," +
                        "s_update_time,s_isAudit,s_user_num,s_report_num,s_point,s_rank,s_grand,isFollow,tedian,receiveMsg,userId,pinyin,staticPath,msgNum)values(" +
                        "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                SQLiteStatement statement = db.compileStatement(sql);
                db.beginTransaction();
                for (OnlineSchool onlineSchool : schools) {
                    statement.bindString(1, onlineSchool.p_id);
                    statement.bindString(2, onlineSchool.s_mail);
                    statement.bindString(3, onlineSchool.s_account);
                    statement.bindString(4, onlineSchool.s_phone);
                    statement.bindString(5, onlineSchool.s_xiaoxin_code);
                    statement.bindString(6, onlineSchool.s_name);
                    statement.bindString(7, onlineSchool.s_img);
                    statement.bindString(8, onlineSchool.s_type);
                    statement.bindString(9, onlineSchool.s_principal);
                    statement.bindString(10, onlineSchool.s_identity);
                    statement.bindString(11, onlineSchool.s_auth_status);
                    statement.bindString(12, onlineSchool.s_auth_time);
                    statement.bindString(13, onlineSchool.s_desc);
                    statement.bindString(14, onlineSchool.s_blance);
                    statement.bindString(15, onlineSchool.s_province);
                    statement.bindString(16, onlineSchool.s_city);
                    statement.bindString(17, onlineSchool.s_area);
                    statement.bindString(18, onlineSchool.s_addr);
                    statement.bindString(19, onlineSchool.s_state);
                    statement.bindString(20, onlineSchool.s_login_time);
                    statement.bindString(21, onlineSchool.s_create_time);
                    statement.bindString(22, onlineSchool.s_update_time);
                    statement.bindString(23, onlineSchool.s_isAudit);
                    statement.bindString(24, onlineSchool.s_user_num);
                    statement.bindString(25, onlineSchool.s_report_num);
                    statement.bindString(26, onlineSchool.s_point);
                    statement.bindString(27, onlineSchool.s_rank);
                    statement.bindString(28, onlineSchool.s_grand);
                    statement.bindString(29, onlineSchool.isFollow);
                    statement.bindString(30, onlineSchool.tedian);
                    statement.bindLong(31, onlineSchool.receiveMsg);
                    statement.bindString(32, userId);
                    String pinyin = PingYinUtil.getPingYin(onlineSchool.s_name);
                    StringBuilder sb = new StringBuilder(100);
                    if (!CommonTools.isEmpty(pinyin) && pinyin.substring(0, 1).matches("[A-Z]")) {
                        sb.append(pinyin);
                    } else {
                        sb.append("{").append(pinyin);
                    }
                    statement.bindString(33, sb.toString());
                    statement.bindString(34, onlineSchool.staticPath);
                    statement.bindLong(35, onlineSchool.msgNum);
                    statement.executeInsert();
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (db != null) {
                    if (db.isOpen()) {
                        try {
                            db.endTransaction();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void insertOneOnlineSchool(OnlineSchool onlineSchool, String userId) {
        ArrayList<OnlineSchool> schools = new ArrayList<OnlineSchool>();
        schools.add(onlineSchool);
        insertAllOnlineSchool(schools, userId);
    }

    public void deleteOneOnlineSchool(String schoolId, String userId) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                dbHelper.delete(tableName, "p_id = ? and userId = ?", new String[]{schoolId, userId});
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dbHelper.close();
            }
        }
    }

    public void deleteAllOnlineSchool(String userId) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                dbHelper.delete(tableName, "userId = ?", new String[]{userId});
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dbHelper.close();
            }
        }
    }

    public AreaMap getOnlineSchool(String userId) {
        synchronized (dbHelper) {
            AreaMap areaMap = new AreaMap();
            ArrayMap<String, Integer> map = new ArrayMap<String, Integer>();
            ArrayList<Object> datas = new ArrayList<Object>();
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "userId = ?", new String[]{userId}, null, null, "pinyin asc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        OnlineSchool onlineSchool = new OnlineSchool();
                        onlineSchool.isFollow = cursor.getString(cursor.getColumnIndex("isFollow"));
                        onlineSchool.s_auth_status = cursor.getString(cursor.getColumnIndex("s_auth_status"));
                        onlineSchool.receiveMsg = cursor.getInt(cursor.getColumnIndex("receiveMsg"));
                        onlineSchool.p_id = cursor.getString(cursor.getColumnIndex("p_id"));
                        onlineSchool.s_account = cursor.getString(cursor.getColumnIndex("s_account"));
                        onlineSchool.s_addr = cursor.getString(cursor.getColumnIndex("s_addr"));
                        onlineSchool.s_area = cursor.getString(cursor.getColumnIndex("s_area"));
                        onlineSchool.s_auth_time = cursor.getString(cursor.getColumnIndex("s_auth_time"));
                        onlineSchool.s_blance = cursor.getString(cursor.getColumnIndex("s_blance"));
                        onlineSchool.s_city = cursor.getString(cursor.getColumnIndex("s_city"));
                        onlineSchool.s_create_time = cursor.getString(cursor.getColumnIndex("s_create_time"));
                        onlineSchool.s_desc = cursor.getString(cursor.getColumnIndex("s_desc"));
                        onlineSchool.s_grand = cursor.getString(cursor.getColumnIndex("s_grand"));
                        onlineSchool.s_identity = cursor.getString(cursor.getColumnIndex("s_identity"));
                        onlineSchool.s_img = cursor.getString(cursor.getColumnIndex("s_img"));
                        onlineSchool.s_isAudit = cursor.getString(cursor.getColumnIndex("s_isAudit"));
                        onlineSchool.s_login_time = cursor.getString(cursor.getColumnIndex("s_login_time"));
                        onlineSchool.s_mail = cursor.getString(cursor.getColumnIndex("s_mail"));
                        onlineSchool.s_name = cursor.getString(cursor.getColumnIndex("s_name"));
                        onlineSchool.s_phone = cursor.getString(cursor.getColumnIndex("s_phone"));
                        onlineSchool.s_point = cursor.getString(cursor.getColumnIndex("s_point"));
                        onlineSchool.s_principal = cursor.getString(cursor.getColumnIndex("s_principal"));
                        onlineSchool.s_province = cursor.getString(cursor.getColumnIndex("s_province"));
                        onlineSchool.s_rank = cursor.getString(cursor.getColumnIndex("s_rank"));
                        onlineSchool.s_report_num = cursor.getString(cursor.getColumnIndex("s_report_num"));
                        onlineSchool.s_state = cursor.getString(cursor.getColumnIndex("s_state"));
                        onlineSchool.s_type = cursor.getString(cursor.getColumnIndex("s_type"));
                        onlineSchool.s_update_time = cursor.getString(cursor.getColumnIndex("s_update_time"));
                        onlineSchool.s_user_num = cursor.getString(cursor.getColumnIndex("s_user_num"));
                        onlineSchool.s_xiaoxin_code = cursor.getString(cursor.getColumnIndex("s_xiaoxin_code"));
                        onlineSchool.tedian = cursor.getString(cursor.getColumnIndex("tedian"));
                        onlineSchool.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                        onlineSchool.staticPath = cursor.getString(cursor.getColumnIndex("staticPath"));
                        onlineSchool.msgNum = cursor.getInt(cursor.getColumnIndex("msgNum"));
                        String letter = onlineSchool.pinyin.substring(0, 1);
                        if (!map.containsKey(letter)) {
                            map.put(letter, datas.size());
                            datas.add(letter);
                        }
                        datas.add(onlineSchool);
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
            areaMap.datas = datas;
            areaMap.map = map;
            return areaMap;
        }
    }

    public OnlineSchool getOneSchool(String userId, String pid) {
        synchronized (dbHelper) {
            OnlineSchool onlineSchool = new OnlineSchool();
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "userId = ? and p_id = ?", new String[]{userId, pid}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    onlineSchool.isFollow = cursor.getString(cursor.getColumnIndex("isFollow"));
                    onlineSchool.s_auth_status = cursor.getString(cursor.getColumnIndex("s_auth_status"));
                    onlineSchool.receiveMsg = cursor.getInt(cursor.getColumnIndex("receiveMsg"));
                    onlineSchool.p_id = cursor.getString(cursor.getColumnIndex("p_id"));
                    onlineSchool.s_account = cursor.getString(cursor.getColumnIndex("s_account"));
                    onlineSchool.s_addr = cursor.getString(cursor.getColumnIndex("s_addr"));
                    onlineSchool.s_area = cursor.getString(cursor.getColumnIndex("s_area"));
                    onlineSchool.s_auth_time = cursor.getString(cursor.getColumnIndex("s_auth_time"));
                    onlineSchool.s_blance = cursor.getString(cursor.getColumnIndex("s_blance"));
                    onlineSchool.s_city = cursor.getString(cursor.getColumnIndex("s_city"));
                    onlineSchool.s_create_time = cursor.getString(cursor.getColumnIndex("s_create_time"));
                    onlineSchool.s_desc = cursor.getString(cursor.getColumnIndex("s_desc"));
                    onlineSchool.s_grand = cursor.getString(cursor.getColumnIndex("s_grand"));
                    onlineSchool.s_identity = cursor.getString(cursor.getColumnIndex("s_identity"));
                    onlineSchool.s_img = cursor.getString(cursor.getColumnIndex("s_img"));
                    onlineSchool.s_isAudit = cursor.getString(cursor.getColumnIndex("s_isAudit"));
                    onlineSchool.s_login_time = cursor.getString(cursor.getColumnIndex("s_login_time"));
                    onlineSchool.s_mail = cursor.getString(cursor.getColumnIndex("s_mail"));
                    onlineSchool.s_name = cursor.getString(cursor.getColumnIndex("s_name"));
                    onlineSchool.s_phone = cursor.getString(cursor.getColumnIndex("s_phone"));
                    onlineSchool.s_point = cursor.getString(cursor.getColumnIndex("s_point"));
                    onlineSchool.s_principal = cursor.getString(cursor.getColumnIndex("s_principal"));
                    onlineSchool.s_province = cursor.getString(cursor.getColumnIndex("s_province"));
                    onlineSchool.s_rank = cursor.getString(cursor.getColumnIndex("s_rank"));
                    onlineSchool.s_report_num = cursor.getString(cursor.getColumnIndex("s_report_num"));
                    onlineSchool.s_state = cursor.getString(cursor.getColumnIndex("s_state"));
                    onlineSchool.s_type = cursor.getString(cursor.getColumnIndex("s_type"));
                    onlineSchool.s_update_time = cursor.getString(cursor.getColumnIndex("s_update_time"));
                    onlineSchool.s_user_num = cursor.getString(cursor.getColumnIndex("s_user_num"));
                    onlineSchool.s_xiaoxin_code = cursor.getString(cursor.getColumnIndex("s_xiaoxin_code"));
                    onlineSchool.tedian = cursor.getString(cursor.getColumnIndex("tedian"));
                    onlineSchool.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                    onlineSchool.staticPath = cursor.getString(cursor.getColumnIndex("staticPath"));
                    onlineSchool.msgNum = cursor.getInt(cursor.getColumnIndex("msgNum"));
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
            return onlineSchool;
        }
    }

    public void updateMsgNum(String id, String mid) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                dbHelper.getSqLiteDatabase().execSQL("update ONLINESCHOOL set msgNum = msgNum+1 where p_id='" + id + "' and userId='" + mid + "'");
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void clearMsgNum(String id, String mid) {
        synchronized (dbHelper) {
            try {
                ContentValues cv = new ContentValues();
                cv.put("msgNum", 0);
                dbHelper.open();
                dbHelper.update(tableName, cv, "p_id=? and userId=?", new String[]{id, mid});
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public int getCount(String mid) {
        synchronized (dbHelper) {
            Cursor cursor = null;
            int count = 0;
            try {
                dbHelper.open();
                String sql = "select sum(msgNum) count from ONLINESCHOOL where userId='" + mid + "'";
                cursor = dbHelper.getSqLiteDatabase().rawQuery(sql, null);
                if (cursor != null) {
                    cursor.moveToFirst();
                    count = cursor.getInt(0);
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
            return count;
        }
    }

    public void updateOnlineSchoolTime(String time, String mid) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                ContentValues cv = new ContentValues();
                cv.put("s_create_time", time);
                dbHelper.update(tableName, cv, "userId = ?", new String[]{mid});
            } catch (Exception e) {

            } finally {
                dbHelper.close();
            }
        }
    }

    public void updateOneOnlineSchoolInfo(String key, Object time, String pid, String mid) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                ContentValues cv = new ContentValues();
                if (time instanceof String) {
                    cv.put(key, (String) time);
                } else if (time instanceof Integer) {
                    cv.put(key, (int) time);
                }
                dbHelper.update(tableName, cv, "userId = ? and p_id = ?", new String[]{mid, pid});
            } catch (Exception e) {

            } finally {
                dbHelper.close();
            }
        }
    }

    public String getAllData() {
        synchronized (dbHelper) {
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------ONLINESCHOOL---------------------------------------\n");
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, null, null, null, null, "ID asc");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        OnlineSchool onlineSchool = new OnlineSchool();
                        onlineSchool.isFollow = cursor.getString(cursor.getColumnIndex("isFollow"));
                        onlineSchool.s_auth_status = cursor.getString(cursor.getColumnIndex("s_auth_status"));
                        onlineSchool.receiveMsg = cursor.getInt(cursor.getColumnIndex("receiveMsg"));
                        onlineSchool.p_id = cursor.getString(cursor.getColumnIndex("p_id"));
                        onlineSchool.s_account = cursor.getString(cursor.getColumnIndex("s_account"));
                        onlineSchool.s_addr = cursor.getString(cursor.getColumnIndex("s_addr"));
                        onlineSchool.s_area = cursor.getString(cursor.getColumnIndex("s_area"));
                        onlineSchool.s_auth_time = cursor.getString(cursor.getColumnIndex("s_auth_time"));
                        onlineSchool.s_blance = cursor.getString(cursor.getColumnIndex("s_blance"));
                        onlineSchool.s_city = cursor.getString(cursor.getColumnIndex("s_city"));
                        onlineSchool.s_create_time = cursor.getString(cursor.getColumnIndex("s_create_time"));
                        onlineSchool.s_desc = cursor.getString(cursor.getColumnIndex("s_desc"));
                        onlineSchool.s_grand = cursor.getString(cursor.getColumnIndex("s_grand"));
                        onlineSchool.s_identity = cursor.getString(cursor.getColumnIndex("s_identity"));
                        onlineSchool.s_img = cursor.getString(cursor.getColumnIndex("s_img"));
                        onlineSchool.s_isAudit = cursor.getString(cursor.getColumnIndex("s_isAudit"));
                        onlineSchool.s_login_time = cursor.getString(cursor.getColumnIndex("s_login_time"));
                        onlineSchool.s_mail = cursor.getString(cursor.getColumnIndex("s_mail"));
                        onlineSchool.s_name = cursor.getString(cursor.getColumnIndex("s_name"));
                        onlineSchool.s_phone = cursor.getString(cursor.getColumnIndex("s_phone"));
                        onlineSchool.s_point = cursor.getString(cursor.getColumnIndex("s_point"));
                        onlineSchool.s_principal = cursor.getString(cursor.getColumnIndex("s_principal"));
                        onlineSchool.s_province = cursor.getString(cursor.getColumnIndex("s_province"));
                        onlineSchool.s_rank = cursor.getString(cursor.getColumnIndex("s_rank"));
                        onlineSchool.s_report_num = cursor.getString(cursor.getColumnIndex("s_report_num"));
                        onlineSchool.s_state = cursor.getString(cursor.getColumnIndex("s_state"));
                        onlineSchool.s_type = cursor.getString(cursor.getColumnIndex("s_type"));
                        onlineSchool.s_update_time = cursor.getString(cursor.getColumnIndex("s_update_time"));
                        onlineSchool.s_user_num = cursor.getString(cursor.getColumnIndex("s_user_num"));
                        onlineSchool.s_xiaoxin_code = cursor.getString(cursor.getColumnIndex("s_xiaoxin_code"));
                        onlineSchool.tedian = cursor.getString(cursor.getColumnIndex("tedian"));
                        onlineSchool.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                        onlineSchool.staticPath = cursor.getString(cursor.getColumnIndex("staticPath"));
                        onlineSchool.msgNum = cursor.getInt(cursor.getColumnIndex("msgNum"));
                        onlineSchool.mid = cursor.getString(cursor.getColumnIndex("userId"));
                        sb.append(onlineSchool.toString()).append("\n");
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
