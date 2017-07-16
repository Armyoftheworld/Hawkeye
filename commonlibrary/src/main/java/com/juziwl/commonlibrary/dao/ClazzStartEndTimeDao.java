package com.juziwl.commonlibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.juziwl.commonlibrary.model.ClazzStartEndTime;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CLAZZ_START_END_TIME".
*/
public class ClazzStartEndTimeDao extends AbstractDao<ClazzStartEndTime, Long> {

    public static final String TABLENAME = "CLAZZ_START_END_TIME";

    /**
     * Properties of entity ClazzStartEndTime.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StartTime = new Property(1, String.class, "startTime", false, "START_TIME");
        public final static Property EndTime = new Property(2, String.class, "endTime", false, "END_TIME");
        public final static Property UpdateTime = new Property(3, String.class, "updateTime", false, "UPDATE_TIME");
        public final static Property ClassId = new Property(4, String.class, "classId", false, "CLASS_ID");
        public final static Property Mid = new Property(5, String.class, "mid", false, "MID");
    }


    public ClazzStartEndTimeDao(DaoConfig config) {
        super(config);
    }
    
    public ClazzStartEndTimeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLAZZ_START_END_TIME\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"START_TIME\" TEXT," + // 1: startTime
                "\"END_TIME\" TEXT," + // 2: endTime
                "\"UPDATE_TIME\" TEXT," + // 3: updateTime
                "\"CLASS_ID\" TEXT," + // 4: classId
                "\"MID\" TEXT);"); // 5: mid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLAZZ_START_END_TIME\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ClazzStartEndTime entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(2, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(3, endTime);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(4, updateTime);
        }
 
        String classId = entity.getClassId();
        if (classId != null) {
            stmt.bindString(5, classId);
        }
 
        String mid = entity.getMid();
        if (mid != null) {
            stmt.bindString(6, mid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ClazzStartEndTime entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String startTime = entity.getStartTime();
        if (startTime != null) {
            stmt.bindString(2, startTime);
        }
 
        String endTime = entity.getEndTime();
        if (endTime != null) {
            stmt.bindString(3, endTime);
        }
 
        String updateTime = entity.getUpdateTime();
        if (updateTime != null) {
            stmt.bindString(4, updateTime);
        }
 
        String classId = entity.getClassId();
        if (classId != null) {
            stmt.bindString(5, classId);
        }
 
        String mid = entity.getMid();
        if (mid != null) {
            stmt.bindString(6, mid);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ClazzStartEndTime readEntity(Cursor cursor, int offset) {
        ClazzStartEndTime entity = new ClazzStartEndTime( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // startTime
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // endTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // updateTime
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // classId
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // mid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ClazzStartEndTime entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStartTime(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEndTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUpdateTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setClassId(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setMid(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ClazzStartEndTime entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ClazzStartEndTime entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ClazzStartEndTime entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}