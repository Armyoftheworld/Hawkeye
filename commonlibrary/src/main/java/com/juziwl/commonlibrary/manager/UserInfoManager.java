package com.juziwl.commonlibrary.manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.User;

import java.util.ArrayList;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月22日
 * @description 管理用户数据库
 */
public class UserInfoManager {
    private String tableName = "USERINFO";
    private static UserInfoManager userInfoManager = null;
    private static DbHelper dbHelper = null;

    private UserInfoManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context.getApplicationContext());//防止内存泄漏
        }
    }

    public static UserInfoManager getInstance(Context context) {
        if (userInfoManager == null) {
            userInfoManager = new UserInfoManager(context.getApplicationContext());
        }
        return userInfoManager;
    }

    public ArrayList<User> getAllContactsList(String mid, String flag) {
        synchronized (dbHelper) {
            ArrayList<User> users = new ArrayList<User>();
            Cursor cursor = null;
            try {
                String selection = "mid = ? and flag=?";
                String selectionArgs[] = new String[2];
                selectionArgs[0] = mid;
                selectionArgs[1] = flag;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection,
                        selectionArgs, null, null, null);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                        .moveToNext()) {
                    User user = new User();
                    int userId = cursor.getColumnIndex("userId");
                    int accessToken = cursor.getColumnIndex("accessToken");
                    int time = cursor.getColumnIndex("time");
                    int userName = cursor.getColumnIndex("userName");
                    int fullName = cursor.getColumnIndex("fullName");
                    int phoneNumber = cursor.getColumnIndex("phoneNumber");
                    int userImageUrl = cursor.getColumnIndex("userImageUrl");
                    int provinceName = cursor.getColumnIndex("provinceName");
                    int provinceId = cursor.getColumnIndex("provinceId");
                    int cityName = cursor.getColumnIndex("cityName");
                    int cityId = cursor.getColumnIndex("cityId");
                    int districtName = cursor.getColumnIndex("districtName");
                    int districtId = cursor.getColumnIndex("districtId");
                    int deviceToken = cursor.getColumnIndex("deviceToken");
                    int registered = cursor.getColumnIndex("registered");
                    int totalRole = cursor.getColumnIndex("totalRole");
                    int totalName = cursor.getColumnIndex("totalName");
                    int addGlag = cursor.getColumnIndex("addFlag");
                    int xxCode = cursor.getColumnIndex("xxCode");
                    int pinyin = cursor.getColumnIndex("pinyin");
                    int xlId = cursor.getColumnIndex("xlId");

                    user.userId = cursor.getString(userId);
                    user.accessToken = cursor.getString(accessToken);
                    user.time = cursor.getString(time);
                    user.userName = cursor.getString(userName);
                    user.fullName = cursor.getString(fullName);
                    user.phoneNumber = cursor.getString(phoneNumber);
                    user.userImageUrl = cursor.getString(userImageUrl);
                    user.provinceName = cursor.getString(provinceName);
                    user.provinceId = cursor.getString(provinceId);
                    user.cityName = cursor.getString(cityName);
                    user.cityId = cursor.getString(cityId);
                    user.districtName = cursor.getString(districtName);
                    user.districtId = cursor.getString(districtId);
                    user.deviceToken = cursor.getString(deviceToken);
                    user.registered = cursor.getString(registered);
                    user.totalRole = cursor.getString(totalRole);
                    user.totalName = cursor.getString(totalName);
                    user.addGlag = cursor.getString(addGlag);
                    user.xxCode = cursor.getString(xxCode);
                    user.pinyin = cursor.getString(pinyin);
                    user.xlId = cursor.getString(xlId);
                    users.add(user);
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
            return users;
        }
    }

    public User getSingleUser(String fid, String mid, String flag) {
        synchronized (dbHelper) {
            User user = new User();
            Cursor cursor = null;
            try {
                String selection = "userId =? and mid = ? and flag = ?";
                String selectionArg[] = new String[3];
                selectionArg[0] = fid;
                selectionArg[1] = mid;
                selectionArg[2] = flag;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection,
                        selectionArg, null, null, null);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                        .moveToNext()) {
                    int userId = cursor.getColumnIndex("userId");
                    int accessToken = cursor.getColumnIndex("accessToken");
                    int time = cursor.getColumnIndex("time");
                    int userName = cursor.getColumnIndex("userName");
                    int fullName = cursor.getColumnIndex("fullName");
                    int phoneNumber = cursor.getColumnIndex("phoneNumber");
                    int userImageUrl = cursor.getColumnIndex("userImageUrl");
                    int provinceName = cursor.getColumnIndex("provinceName");
                    int provinceId = cursor.getColumnIndex("provinceId");
                    int cityName = cursor.getColumnIndex("cityName");
                    int cityId = cursor.getColumnIndex("cityId");
                    int districtName = cursor.getColumnIndex("districtName");
                    int districtId = cursor.getColumnIndex("districtId");
                    int deviceToken = cursor.getColumnIndex("deviceToken");
                    int registered = cursor.getColumnIndex("registered");
                    int totalRole = cursor.getColumnIndex("totalRole");
                    int totalName = cursor.getColumnIndex("totalName");
                    int addGlag = cursor.getColumnIndex("addFlag");
                    int xxCode = cursor.getColumnIndex("xxCode");
                    int pinyin = cursor.getColumnIndex("pinyin");
                    int xlId = cursor.getColumnIndex("xlId");

                    user.userId = cursor.getString(userId);
                    user.accessToken = cursor.getString(accessToken);
                    user.time = cursor.getString(time);
                    user.userName = cursor.getString(userName);
                    user.fullName = cursor.getString(fullName);
                    user.phoneNumber = cursor.getString(phoneNumber);
                    user.userImageUrl = cursor.getString(userImageUrl);
                    user.provinceName = cursor.getString(provinceName);
                    user.provinceId = cursor.getString(provinceId);
                    user.cityName = cursor.getString(cityName);
                    user.cityId = cursor.getString(cityId);
                    user.districtName = cursor.getString(districtName);
                    user.districtId = cursor.getString(districtId);
                    user.deviceToken = cursor.getString(deviceToken);
                    user.registered = cursor.getString(registered);
                    user.totalRole = cursor.getString(totalRole);
                    user.totalName = cursor.getString(totalName);
                    user.addGlag = cursor.getString(addGlag);
                    user.xxCode = cursor.getString(xxCode);
                    user.pinyin = cursor.getString(pinyin);
                    user.xlId = cursor.getString(xlId);
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
            return user;
        }
    }


    public User getSingleNewUser(String fid, String mid, String flag, String addFlag) {
        synchronized (dbHelper) {
            User user = new User();
            Cursor cursor = null;
            try {
                String selection = "userId =? and mid = ? and flag = ? and addFlag = ?";
                String selectionArg[] = new String[4];
                selectionArg[0] = fid;
                selectionArg[1] = mid;
                selectionArg[2] = flag;
                selectionArg[3] = addFlag;
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, selection,
                        selectionArg, null, null, null);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor
                        .moveToNext()) {
                    int userId = cursor.getColumnIndex("userId");
                    int accessToken = cursor.getColumnIndex("accessToken");
                    int time = cursor.getColumnIndex("time");
                    int userName = cursor.getColumnIndex("userName");
                    int fullName = cursor.getColumnIndex("fullName");
                    int phoneNumber = cursor.getColumnIndex("phoneNumber");
                    int userImageUrl = cursor.getColumnIndex("userImageUrl");
                    int provinceName = cursor.getColumnIndex("provinceName");
                    int provinceId = cursor.getColumnIndex("provinceId");
                    int cityName = cursor.getColumnIndex("cityName");
                    int cityId = cursor.getColumnIndex("cityId");
                    int districtName = cursor.getColumnIndex("districtName");
                    int districtId = cursor.getColumnIndex("districtId");
                    int deviceToken = cursor.getColumnIndex("deviceToken");
                    int registered = cursor.getColumnIndex("registered");
                    int totalRole = cursor.getColumnIndex("totalRole");
                    int totalName = cursor.getColumnIndex("totalName");
                    int addGlag = cursor.getColumnIndex("addFlag");
                    int xxCode = cursor.getColumnIndex("xxCode");
                    int pinyin = cursor.getColumnIndex("pinyin");
                    int xlId = cursor.getColumnIndex("xlId");

                    user.userId = cursor.getString(userId);
                    user.accessToken = cursor.getString(accessToken);
                    user.time = cursor.getString(time);
                    user.userName = cursor.getString(userName);
                    user.fullName = cursor.getString(fullName);
                    user.phoneNumber = cursor.getString(phoneNumber);
                    user.userImageUrl = cursor.getString(userImageUrl);
                    user.provinceName = cursor.getString(provinceName);
                    user.provinceId = cursor.getString(provinceId);
                    user.cityName = cursor.getString(cityName);
                    user.cityId = cursor.getString(cityId);
                    user.districtName = cursor.getString(districtName);
                    user.districtId = cursor.getString(districtId);
                    user.deviceToken = cursor.getString(deviceToken);
                    user.registered = cursor.getString(registered);
                    user.totalRole = cursor.getString(totalRole);
                    user.totalName = cursor.getString(totalName);
                    user.addGlag = cursor.getString(addGlag);
                    user.xxCode = cursor.getString(xxCode);
                    user.pinyin = cursor.getString(pinyin);
                    user.xlId = cursor.getString(xlId);
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
            return user;
        }
    }

    public void insertAllContact(ArrayList<User> users, final String mid,
                                 String flag) {
        synchronized (dbHelper) {
            SQLiteDatabase db = dbHelper.open().getSqLiteDatabase();
            try {
                int length = users.size();
                String sql = "insert into USERINFO(userId,accessToken,time," +
                        "userName,fullName,phoneNumber,userImageUrl," +
                        "provinceName,provinceId,cityName,cityId," +
                        "districtName,districtId,deviceToken," +
                        "registered,totalRole,totalName,addFlag,xxCode,flag,mid,pinyin)" +
                        "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                //批量插入数据
                SQLiteStatement stat = db.compileStatement(sql);
                db.beginTransaction();
                for (int i = 0; i < length; i++) {
                    User user = users.get(i);
                    stat.bindString(1, user.userId);
                    stat.bindString(2, user.accessToken);
                    stat.bindString(3, user.time);
                    stat.bindString(4, user.userName);
                    stat.bindString(5, user.fullName);
                    stat.bindString(6, user.phoneNumber);
                    stat.bindString(7, user.userImageUrl);
                    stat.bindString(8, user.provinceName);
                    stat.bindString(9, user.provinceId);
                    stat.bindString(10, user.cityName);
                    stat.bindString(11, user.cityId);
                    stat.bindString(12, user.districtName);
                    stat.bindString(13, user.districtId);
                    stat.bindString(14, user.deviceToken);
                    stat.bindString(15, user.registered);
                    stat.bindString(16, user.totalRole);
                    stat.bindString(17, user.totalName);
                    stat.bindString(18, user.addGlag);
                    stat.bindString(19, user.xxCode);
                    stat.bindString(20, flag);
                    stat.bindString(21, mid);
                    stat.bindString(22, user.pinyin);
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

    public void insertOneContact(User user, String mid, String flag) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                ContentValues value = new ContentValues();
                value.put("userId", user.userId);
                value.put("xlId", user.xlId);
                value.put("accessToken", user.accessToken);
                value.put("time", user.time);
                value.put("userName", user.userName);
                value.put("fullName", user.fullName);
                value.put("phoneNumber", user.phoneNumber);
                value.put("userImageUrl", user.userImageUrl);
                value.put("provinceName", user.provinceName);
                value.put("provinceId", user.provinceId);
                value.put("cityName", user.cityName);
                value.put("cityId", user.cityId);
                value.put("districtName", user.districtName);
                value.put("districtId", user.districtId);
                value.put("deviceToken", user.deviceToken);
                value.put("registered", user.registered);
                value.put("totalRole", user.totalRole);
                value.put("totalName", user.totalName);
                value.put("addFlag", user.addGlag);
                value.put("xxCode", user.xxCode);
                value.put("pinyin", user.pinyin);
                value.put("type", user.type);
                value.put("openType", user.openType);
                value.put("flag", flag);
                value.put("mid", mid);
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

    public void insertAllFriReqList(ArrayList<User> users, String mid, String flag) {
        synchronized (dbHelper) {
            try {
                dbHelper.open();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    ContentValues value = new ContentValues();
                    value.put("userId", user.userId);
                    value.put("xlId", user.xlId);
                    value.put("accessToken", user.accessToken);
                    value.put("time", user.time);
                    value.put("userName", user.userName);
                    value.put("fullName", user.fullName);
                    value.put("phoneNumber", user.phoneNumber);
                    value.put("userImageUrl", user.userImageUrl);
                    value.put("provinceName", user.provinceName);
                    value.put("provinceId", user.provinceId);
                    value.put("cityName", user.cityName);
                    value.put("cityId", user.cityId);
                    value.put("districtName", user.districtName);
                    value.put("districtId", user.districtId);
                    value.put("deviceToken", user.deviceToken);
                    value.put("registered", user.registered);
                    value.put("totalRole", user.totalRole);
                    value.put("totalName", user.totalName);
                    value.put("addFlag", "0");
                    value.put("xxCode", user.xxCode);
                    value.put("pinyin", user.pinyin);
                    value.put("flag", flag);
                    value.put("mid", mid);
                    dbHelper.insert(tableName, value);
                }

            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteMoreFriReqList(String mid, String flag) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[2];
                delete[0] = mid;
                delete[1] = flag;
                dbHelper.open();
                dbHelper.delete(tableName, "mid = ? and flag = ?",
                        delete);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

//    public void updateMsgNum(String id, String mid){
//        synchronized (dbHelper) {
//            try {
//                dbHelper.open();
//                dbHelper.getSqLiteDatabase().execSQL("update ONLINESCHOOL set msgNum = msgNum+1 where p_id='" + id + "' and userId='" + mid + "'");
//            } catch (Exception e) {
//                // TODO: handle exception
//            }finally{
//                if(dbHelper!=null){
//                    dbHelper.close();
//                }
//            }
//        }
//    }

    public void deleteAllFriReqList(ArrayList<User> users, String mid, String flag) {
        synchronized (dbHelper) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < users.size(); i++) {
                    if (i == 0) {
                        stringBuffer.append("('");
                    }
                    if (i == users.size() - 1) {
                        stringBuffer.append(users.get(i).userId + "')");
                    } else {
                        stringBuffer.append(users.get(i).userId + "','");
                    }
                }
                dbHelper.open();
                String sql = "delete from USERINFO where mid='" + mid + "' and flag='" + flag + "' and userId in " + stringBuffer.toString();
                dbHelper.getSqLiteDatabase().execSQL(sql);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }


    public void deleteSingleContact(String uid, String mid, String flag) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[3];
                delete[0] = uid;
                delete[1] = mid;
                delete[2] = flag;
                dbHelper.open();
                dbHelper.delete(tableName, "userId=? and mid = ? and flag = ?",
                        delete);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteAllUser(String mid, String flag) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[2];
                delete[0] = mid;
                delete[1] = flag;
                dbHelper.open();
                dbHelper.delete(tableName, "mid=? and flag = ?", delete);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public void deleteAllFriUser(String mid, String flag, String addFlag) {
        synchronized (dbHelper) {
            try {
                String delete[] = new String[3];
                delete[0] = mid;
                delete[1] = flag;
                delete[2] = addFlag;
                dbHelper.open();
                dbHelper.delete(tableName, "mid=? and flag = ? and addFlag = ?", delete);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public int updateUserHeadImg(String fid, String value, String mid) {
        synchronized (dbHelper) {
            int count = 0;
            try {
                String updateKey[] = new String[1];
                updateKey[0] = "userImageUrl";
                String updateValue[] = new String[1];
                updateValue[0] = value;
                String where = "userId = ? and mid = ?";
                String whereValue[] = new String[2];
                whereValue[0] = fid;
                whereValue[1] = mid;
                dbHelper.open();
                count = dbHelper.update(tableName, updateKey, updateValue,
                        where, whereValue);
            } catch (Exception e) {
                // TODO: handle exception
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return count;
        }
    }

    public int updateUserUserName(String mid, String username, String flag) {
        synchronized (dbHelper) {
            int count = 0;
            try {
                String updateKey[] = new String[1];
                updateKey[0] = "fullname";
                String updateValue[] = new String[1];
                updateValue[0] = username;
                String where = "mid = ? and flag = ?";
                String whereValue[] = new String[2];
                whereValue[0] = mid;
                whereValue[1] = flag;
                dbHelper.open();
                count = dbHelper.update(tableName, updateKey, updateValue,
                        where, whereValue);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return count;
        }
    }

    public int updateUserAddress(String mid, String province, String provinceId,
                                 String city, String cityId, String area, String areaId,
                                 String flag) {
        synchronized (dbHelper) {
            int count = 0;
            try {
                String updateKey[] = new String[6];
                updateKey[0] = "provinceName";
                updateKey[1] = "provinceId";
                updateKey[2] = "cityName";
                updateKey[3] = "cityId";
                updateKey[4] = "districtName";
                updateKey[5] = "districtId";
                String updateValue[] = new String[6];
                updateValue[0] = province;
                updateValue[1] = provinceId;
                updateValue[2] = city;
                updateValue[3] = cityId;
                updateValue[4] = area;
                updateValue[5] = areaId;
                String where = "userId = ? and flag = ?";
                String whereValue[] = new String[2];
                whereValue[0] = mid;
                whereValue[1] = flag;
                dbHelper.open();
                count = dbHelper.update(tableName, updateKey, updateValue,
                        where, whereValue);
            } catch (Exception e) {
            } finally {
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return count;
        }
    }

    public void updateSingleAddFlag(String uid, String addflag, String mid,
                                    String flag) {
        synchronized (dbHelper) {
            try {
                String updateKey[] = new String[1];
                updateKey[0] = "addFlag";
                String updateValue[] = new String[1];
                updateValue[0] = addflag;
                String where = "userId = ? and mid = ? and flag = ?";
                String whereValue[] = new String[3];
                whereValue[0] = uid;
                whereValue[1] = mid;
                whereValue[2] = flag;
                dbHelper.open();
                dbHelper.update(tableName, updateKey, updateValue, where,
                        whereValue);

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
            StringBuffer sb = new StringBuffer();
            sb.append("\n----------------------------------USERINFO---------------------------------------\n");
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, null, null, null, null, null);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    User user = new User();
                    int userId = cursor.getColumnIndex("userId");
                    int accessToken = cursor.getColumnIndex("accessToken");
                    int time = cursor.getColumnIndex("time");
                    int userName = cursor.getColumnIndex("userName");
                    int fullName = cursor.getColumnIndex("fullName");
                    int phoneNumber = cursor.getColumnIndex("phoneNumber");
                    int userImageUrl = cursor.getColumnIndex("userImageUrl");
                    int provinceName = cursor.getColumnIndex("provinceName");
                    int provinceId = cursor.getColumnIndex("provinceId");
                    int cityName = cursor.getColumnIndex("cityName");
                    int cityId = cursor.getColumnIndex("cityId");
                    int districtName = cursor.getColumnIndex("districtName");
                    int districtId = cursor.getColumnIndex("districtId");
                    int deviceToken = cursor.getColumnIndex("deviceToken");
                    int registered = cursor.getColumnIndex("registered");
                    int totalRole = cursor.getColumnIndex("totalRole");
                    int totalName = cursor.getColumnIndex("totalName");
                    int addGlag = cursor.getColumnIndex("addFlag");
                    int xxCode = cursor.getColumnIndex("xxCode");
                    int pinyin = cursor.getColumnIndex("pinyin");
                    int xlId = cursor.getColumnIndex("xlId");

                    user.userId = cursor.getString(userId);
                    user.accessToken = cursor.getString(accessToken);
                    user.time = cursor.getString(time);
                    user.userName = cursor.getString(userName);
                    user.fullName = cursor.getString(fullName);
                    user.phoneNumber = cursor.getString(phoneNumber);
                    user.userImageUrl = cursor.getString(userImageUrl);
                    user.provinceName = cursor.getString(provinceName);
                    user.provinceId = cursor.getString(provinceId);
                    user.cityName = cursor.getString(cityName);
                    user.cityId = cursor.getString(cityId);
                    user.districtName = cursor.getString(districtName);
                    user.districtId = cursor.getString(districtId);
                    user.deviceToken = cursor.getString(deviceToken);
                    user.registered = cursor.getString(registered);
                    user.totalRole = cursor.getString(totalRole);
                    user.totalName = cursor.getString(totalName);
                    user.addGlag = cursor.getString(addGlag);
                    user.xxCode = cursor.getString(xxCode);
                    user.pinyin = cursor.getString(pinyin);
                    user.xlId = cursor.getString(xlId);
                    user.mid = cursor.getString(cursor.getColumnIndex("mid"));
                    user.flag = cursor.getString(cursor.getColumnIndex("flag"));
                    sb.append(user.toString()).append("\n");
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
            return sb.toString();
        }
    }
}
