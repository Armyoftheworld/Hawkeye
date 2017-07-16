package com.juziwl.commonlibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.juziwl.commonlibrary.model.ProvinceCityDistinct;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PROVINCE_CITY_DISTINCT".
*/
public class ProvinceCityDistinctDao extends AbstractDao<ProvinceCityDistinct, Long> {

    public static final String TABLENAME = "PROVINCE_CITY_DISTINCT";

    /**
     * Properties of entity ProvinceCityDistinct.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ProvinceId = new Property(1, String.class, "provinceId", false, "PROVINCE_ID");
        public final static Property ProvinceName = new Property(2, String.class, "provinceName", false, "PROVINCE_NAME");
        public final static Property CityId = new Property(3, String.class, "cityId", false, "CITY_ID");
        public final static Property CityName = new Property(4, String.class, "cityName", false, "CITY_NAME");
        public final static Property DistrictId = new Property(5, String.class, "districtId", false, "DISTRICT_ID");
        public final static Property DistrictName = new Property(6, String.class, "districtName", false, "DISTRICT_NAME");
    }


    public ProvinceCityDistinctDao(DaoConfig config) {
        super(config);
    }
    
    public ProvinceCityDistinctDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PROVINCE_CITY_DISTINCT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PROVINCE_ID\" TEXT," + // 1: provinceId
                "\"PROVINCE_NAME\" TEXT," + // 2: provinceName
                "\"CITY_ID\" TEXT," + // 3: cityId
                "\"CITY_NAME\" TEXT," + // 4: cityName
                "\"DISTRICT_ID\" TEXT," + // 5: districtId
                "\"DISTRICT_NAME\" TEXT);"); // 6: districtName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PROVINCE_CITY_DISTINCT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ProvinceCityDistinct entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String provinceId = entity.getProvinceId();
        if (provinceId != null) {
            stmt.bindString(2, provinceId);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(3, provinceName);
        }
 
        String cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindString(4, cityId);
        }
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(5, cityName);
        }
 
        String districtId = entity.getDistrictId();
        if (districtId != null) {
            stmt.bindString(6, districtId);
        }
 
        String districtName = entity.getDistrictName();
        if (districtName != null) {
            stmt.bindString(7, districtName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ProvinceCityDistinct entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String provinceId = entity.getProvinceId();
        if (provinceId != null) {
            stmt.bindString(2, provinceId);
        }
 
        String provinceName = entity.getProvinceName();
        if (provinceName != null) {
            stmt.bindString(3, provinceName);
        }
 
        String cityId = entity.getCityId();
        if (cityId != null) {
            stmt.bindString(4, cityId);
        }
 
        String cityName = entity.getCityName();
        if (cityName != null) {
            stmt.bindString(5, cityName);
        }
 
        String districtId = entity.getDistrictId();
        if (districtId != null) {
            stmt.bindString(6, districtId);
        }
 
        String districtName = entity.getDistrictName();
        if (districtName != null) {
            stmt.bindString(7, districtName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ProvinceCityDistinct readEntity(Cursor cursor, int offset) {
        ProvinceCityDistinct entity = new ProvinceCityDistinct( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // provinceId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // provinceName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // cityId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // cityName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // districtId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // districtName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ProvinceCityDistinct entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setProvinceId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setProvinceName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCityId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCityName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDistrictId(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDistrictName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ProvinceCityDistinct entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ProvinceCityDistinct entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ProvinceCityDistinct entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}