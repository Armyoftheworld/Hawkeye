package com.juziwl.commonlibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.juziwl.commonlibrary.model.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ClassBlocked = new Property(1, String.class, "classBlocked", false, "CLASS_BLOCKED");
        public final static Property UserId = new Property(2, String.class, "userId", false, "USER_ID");
        public final static Property XlId = new Property(3, String.class, "xlId", false, "XL_ID");
        public final static Property AccessToken = new Property(4, String.class, "accessToken", false, "ACCESS_TOKEN");
        public final static Property Time = new Property(5, String.class, "time", false, "TIME");
        public final static Property UserName = new Property(6, String.class, "userName", false, "USER_NAME");
        public final static Property FullName = new Property(7, String.class, "fullName", false, "FULL_NAME");
        public final static Property PhoneNumber = new Property(8, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property UserImageUrl = new Property(9, String.class, "userImageUrl", false, "USER_IMAGE_URL");
        public final static Property ProvinceName = new Property(10, String.class, "provinceName", false, "PROVINCE_NAME");
        public final static Property ProvinceId = new Property(11, String.class, "provinceId", false, "PROVINCE_ID");
        public final static Property CityName = new Property(12, String.class, "cityName", false, "CITY_NAME");
        public final static Property CityId = new Property(13, String.class, "cityId", false, "CITY_ID");
        public final static Property DistrictName = new Property(14, String.class, "districtName", false, "DISTRICT_NAME");
        public final static Property DistrictId = new Property(15, String.class, "districtId", false, "DISTRICT_ID");
        public final static Property DeviceToken = new Property(16, String.class, "deviceToken", false, "DEVICE_TOKEN");
        public final static Property Registered = new Property(17, String.class, "registered", false, "REGISTERED");
        public final static Property XxCode = new Property(18, String.class, "xxCode", false, "XX_CODE");
        public final static Property Role = new Property(19, String.class, "role", false, "ROLE");
        public final static Property StudentName = new Property(20, String.class, "studentName", false, "STUDENT_NAME");
        public final static Property TotalRole = new Property(21, String.class, "totalRole", false, "TOTAL_ROLE");
        public final static Property Type = new Property(22, Integer.class, "type", false, "TYPE");
        public final static Property OpenType = new Property(23, Integer.class, "openType", false, "OPEN_TYPE");
        public final static Property TotalName = new Property(24, String.class, "totalName", false, "TOTAL_NAME");
        public final static Property AddGlag = new Property(25, String.class, "addGlag", false, "ADD_GLAG");
        public final static Property Pinyin = new Property(26, String.class, "pinyin", false, "PINYIN");
        public final static Property FriendsTimeNows = new Property(27, String.class, "friendsTimeNows", false, "FRIENDS_TIME_NOWS");
        public final static Property IsChat = new Property(28, Integer.class, "isChat", false, "IS_CHAT");
        public final static Property Ischecked = new Property(29, Boolean.class, "ischecked", false, "ISCHECKED");
        public final static Property IsTeacher = new Property(30, Boolean.class, "isTeacher", false, "IS_TEACHER");
        public final static Property IsSchoolOwner = new Property(31, String.class, "isSchoolOwner", false, "IS_SCHOOL_OWNER");
        public final static Property IsClassOwner = new Property(32, String.class, "isClassOwner", false, "IS_CLASS_OWNER");
        public final static Property Mid = new Property(33, String.class, "mid", false, "MID");
        public final static Property Flag = new Property(34, String.class, "flag", false, "FLAG");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CLASS_BLOCKED\" TEXT," + // 1: classBlocked
                "\"USER_ID\" TEXT," + // 2: userId
                "\"XL_ID\" TEXT," + // 3: xlId
                "\"ACCESS_TOKEN\" TEXT," + // 4: accessToken
                "\"TIME\" TEXT," + // 5: time
                "\"USER_NAME\" TEXT," + // 6: userName
                "\"FULL_NAME\" TEXT," + // 7: fullName
                "\"PHONE_NUMBER\" TEXT," + // 8: phoneNumber
                "\"USER_IMAGE_URL\" TEXT," + // 9: userImageUrl
                "\"PROVINCE_NAME\" TEXT," + // 10: provinceName
                "\"PROVINCE_ID\" TEXT," + // 11: provinceId
                "\"CITY_NAME\" TEXT," + // 12: cityName
                "\"CITY_ID\" TEXT," + // 13: cityId
                "\"DISTRICT_NAME\" TEXT," + // 14: districtName
                "\"DISTRICT_ID\" TEXT," + // 15: districtId
                "\"DEVICE_TOKEN\" TEXT," + // 16: deviceToken
                "\"REGISTERED\" TEXT," + // 17: registered
                "\"XX_CODE\" TEXT," + // 18: xxCode
                "\"ROLE\" TEXT," + // 19: role
                "\"STUDENT_NAME\" TEXT," + // 20: studentName
                "\"TOTAL_ROLE\" TEXT," + // 21: totalRole
                "\"TYPE\" INTEGER," + // 22: type
                "\"OPEN_TYPE\" INTEGER," + // 23: openType
                "\"TOTAL_NAME\" TEXT," + // 24: totalName
                "\"ADD_GLAG\" TEXT," + // 25: addGlag
                "\"PINYIN\" TEXT," + // 26: pinyin
                "\"FRIENDS_TIME_NOWS\" TEXT," + // 27: friendsTimeNows
                "\"IS_CHAT\" INTEGER," + // 28: isChat
                "\"ISCHECKED\" INTEGER," + // 29: ischecked
                "\"IS_TEACHER\" INTEGER," + // 30: isTeacher
                "\"IS_SCHOOL_OWNER\" TEXT," + // 31: isSchoolOwner
                "\"IS_CLASS_OWNER\" TEXT," + // 32: isClassOwner
                "\"MID\" TEXT," + // 33: mid
                "\"FLAG\" TEXT);"); // 34: flag
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String classBlocked = entity.getClassBlocked();
        if (classBlocked != null) {
            stmt.bindString(2, classBlocked);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(3, userId);
        }
 
        String xlId = entity.getXlId();
        if (xlId != null) {
            stmt.bindString(4, xlId);
        }
 
        String accessToken = entity.getAccessToken();
        if (accessToken != null) {
            stmt.bindString(5, accessToken);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
 
        String fullName = entity.getFullName();
        if (fullName != null) {
            stmt.bindString(8, fullName);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(9, phoneNumber);
        }
 
        String userImageUrl = entity.getUserImageUrl();
        if (userImageUrl != null) {
            stmt.bindString(10, userImageUrl);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(11, provinceName);
        }
 
        String provinceId = entity.getProvinceId();
        if (provinceId != null) {
            stmt.bindString(12, provinceId);
        }
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(13, cityName);
        }
 
        String cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindString(14, cityId);
        }
 
        String districtName = entity.getDistrictName();
        if (districtName != null) {
            stmt.bindString(15, districtName);
        }
 
        String districtId = entity.getDistrictId();
        if (districtId != null) {
            stmt.bindString(16, districtId);
        }
 
        String deviceToken = entity.getDeviceToken();
        if (deviceToken != null) {
            stmt.bindString(17, deviceToken);
        }
 
        String registered = entity.getRegistered();
        if (registered != null) {
            stmt.bindString(18, registered);
        }
 
        String xxCode = entity.getXxCode();
        if (xxCode != null) {
            stmt.bindString(19, xxCode);
        }
 
        String role = entity.getRole();
        if (role != null) {
            stmt.bindString(20, role);
        }
 
        String studentName = entity.getStudentName();
        if (studentName != null) {
            stmt.bindString(21, studentName);
        }
 
        String totalRole = entity.getTotalRole();
        if (totalRole != null) {
            stmt.bindString(22, totalRole);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(23, type);
        }
 
        Integer openType = entity.getOpenType();
        if (openType != null) {
            stmt.bindLong(24, openType);
        }
 
        String totalName = entity.getTotalName();
        if (totalName != null) {
            stmt.bindString(25, totalName);
        }
 
        String addGlag = entity.getAddGlag();
        if (addGlag != null) {
            stmt.bindString(26, addGlag);
        }
 
        String pinyin = entity.getPinyin();
        if (pinyin != null) {
            stmt.bindString(27, pinyin);
        }
 
        String friendsTimeNows = entity.getFriendsTimeNows();
        if (friendsTimeNows != null) {
            stmt.bindString(28, friendsTimeNows);
        }
 
        Integer isChat = entity.getIsChat();
        if (isChat != null) {
            stmt.bindLong(29, isChat);
        }
 
        Boolean ischecked = entity.getIschecked();
        if (ischecked != null) {
            stmt.bindLong(30, ischecked ? 1L: 0L);
        }
 
        Boolean isTeacher = entity.getIsTeacher();
        if (isTeacher != null) {
            stmt.bindLong(31, isTeacher ? 1L: 0L);
        }
 
        String isSchoolOwner = entity.getIsSchoolOwner();
        if (isSchoolOwner != null) {
            stmt.bindString(32, isSchoolOwner);
        }
 
        String isClassOwner = entity.getIsClassOwner();
        if (isClassOwner != null) {
            stmt.bindString(33, isClassOwner);
        }
 
        String mid = entity.getMid();
        if (mid != null) {
            stmt.bindString(34, mid);
        }
 
        String flag = entity.getFlag();
        if (flag != null) {
            stmt.bindString(35, flag);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String classBlocked = entity.getClassBlocked();
        if (classBlocked != null) {
            stmt.bindString(2, classBlocked);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(3, userId);
        }
 
        String xlId = entity.getXlId();
        if (xlId != null) {
            stmt.bindString(4, xlId);
        }
 
        String accessToken = entity.getAccessToken();
        if (accessToken != null) {
            stmt.bindString(5, accessToken);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(6, time);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
 
        String fullName = entity.getFullName();
        if (fullName != null) {
            stmt.bindString(8, fullName);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(9, phoneNumber);
        }
 
        String userImageUrl = entity.getUserImageUrl();
        if (userImageUrl != null) {
            stmt.bindString(10, userImageUrl);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(11, provinceName);
        }
 
        String provinceId = entity.getProvinceId();
        if (provinceId != null) {
            stmt.bindString(12, provinceId);
        }
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(13, cityName);
        }
 
        String cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindString(14, cityId);
        }
 
        String districtName = entity.getDistrictName();
        if (districtName != null) {
            stmt.bindString(15, districtName);
        }
 
        String districtId = entity.getDistrictId();
        if (districtId != null) {
            stmt.bindString(16, districtId);
        }
 
        String deviceToken = entity.getDeviceToken();
        if (deviceToken != null) {
            stmt.bindString(17, deviceToken);
        }
 
        String registered = entity.getRegistered();
        if (registered != null) {
            stmt.bindString(18, registered);
        }
 
        String xxCode = entity.getXxCode();
        if (xxCode != null) {
            stmt.bindString(19, xxCode);
        }
 
        String role = entity.getRole();
        if (role != null) {
            stmt.bindString(20, role);
        }
 
        String studentName = entity.getStudentName();
        if (studentName != null) {
            stmt.bindString(21, studentName);
        }
 
        String totalRole = entity.getTotalRole();
        if (totalRole != null) {
            stmt.bindString(22, totalRole);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(23, type);
        }
 
        Integer openType = entity.getOpenType();
        if (openType != null) {
            stmt.bindLong(24, openType);
        }
 
        String totalName = entity.getTotalName();
        if (totalName != null) {
            stmt.bindString(25, totalName);
        }
 
        String addGlag = entity.getAddGlag();
        if (addGlag != null) {
            stmt.bindString(26, addGlag);
        }
 
        String pinyin = entity.getPinyin();
        if (pinyin != null) {
            stmt.bindString(27, pinyin);
        }
 
        String friendsTimeNows = entity.getFriendsTimeNows();
        if (friendsTimeNows != null) {
            stmt.bindString(28, friendsTimeNows);
        }
 
        Integer isChat = entity.getIsChat();
        if (isChat != null) {
            stmt.bindLong(29, isChat);
        }
 
        Boolean ischecked = entity.getIschecked();
        if (ischecked != null) {
            stmt.bindLong(30, ischecked ? 1L: 0L);
        }
 
        Boolean isTeacher = entity.getIsTeacher();
        if (isTeacher != null) {
            stmt.bindLong(31, isTeacher ? 1L: 0L);
        }
 
        String isSchoolOwner = entity.getIsSchoolOwner();
        if (isSchoolOwner != null) {
            stmt.bindString(32, isSchoolOwner);
        }
 
        String isClassOwner = entity.getIsClassOwner();
        if (isClassOwner != null) {
            stmt.bindString(33, isClassOwner);
        }
 
        String mid = entity.getMid();
        if (mid != null) {
            stmt.bindString(34, mid);
        }
 
        String flag = entity.getFlag();
        if (flag != null) {
            stmt.bindString(35, flag);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // classBlocked
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // userId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // xlId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // accessToken
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // time
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // userName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // fullName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // phoneNumber
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // userImageUrl
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // provinceName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // provinceId
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // cityName
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // cityId
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // districtName
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // districtId
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // deviceToken
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // registered
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // xxCode
            cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // role
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // studentName
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // totalRole
            cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22), // type
            cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23), // openType
            cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24), // totalName
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // addGlag
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26), // pinyin
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // friendsTimeNows
            cursor.isNull(offset + 28) ? null : cursor.getInt(offset + 28), // isChat
            cursor.isNull(offset + 29) ? null : cursor.getShort(offset + 29) != 0, // ischecked
            cursor.isNull(offset + 30) ? null : cursor.getShort(offset + 30) != 0, // isTeacher
            cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31), // isSchoolOwner
            cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32), // isClassOwner
            cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33), // mid
            cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34) // flag
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setClassBlocked(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setUserId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setXlId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAccessToken(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTime(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUserName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFullName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setPhoneNumber(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setUserImageUrl(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setProvinceName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setProvinceId(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCityName(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setCityId(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setDistrictName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setDistrictId(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setDeviceToken(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setRegistered(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setXxCode(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setRole(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setStudentName(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setTotalRole(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setType(cursor.isNull(offset + 22) ? null : cursor.getInt(offset + 22));
        entity.setOpenType(cursor.isNull(offset + 23) ? null : cursor.getInt(offset + 23));
        entity.setTotalName(cursor.isNull(offset + 24) ? null : cursor.getString(offset + 24));
        entity.setAddGlag(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setPinyin(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
        entity.setFriendsTimeNows(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setIsChat(cursor.isNull(offset + 28) ? null : cursor.getInt(offset + 28));
        entity.setIschecked(cursor.isNull(offset + 29) ? null : cursor.getShort(offset + 29) != 0);
        entity.setIsTeacher(cursor.isNull(offset + 30) ? null : cursor.getShort(offset + 30) != 0);
        entity.setIsSchoolOwner(cursor.isNull(offset + 31) ? null : cursor.getString(offset + 31));
        entity.setIsClassOwner(cursor.isNull(offset + 32) ? null : cursor.getString(offset + 32));
        entity.setMid(cursor.isNull(offset + 33) ? null : cursor.getString(offset + 33));
        entity.setFlag(cursor.isNull(offset + 34) ? null : cursor.getString(offset + 34));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
