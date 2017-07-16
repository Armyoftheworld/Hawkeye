package com.juziwl.commonlibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.juziwl.commonlibrary.model.ClassDynInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CLASS_DYN_INFO".
*/
public class ClassDynInfoDao extends AbstractDao<ClassDynInfo, Long> {

    public static final String TABLENAME = "CLASS_DYN_INFO";

    /**
     * Properties of entity ClassDynInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property ClassId = new Property(1, String.class, "classId", false, "CLASS_ID");
        public final static Property Mid = new Property(2, String.class, "mid", false, "MID");
    }


    public ClassDynInfoDao(DaoConfig config) {
        super(config);
    }
    
    public ClassDynInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CLASS_DYN_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CLASS_ID\" TEXT," + // 1: classId
                "\"MID\" TEXT);"); // 2: mid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CLASS_DYN_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ClassDynInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String classId = entity.getClassId();
        if (classId != null) {
            stmt.bindString(2, classId);
        }
 
        String mid = entity.getMid();
        if (mid != null) {
            stmt.bindString(3, mid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ClassDynInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String classId = entity.getClassId();
        if (classId != null) {
            stmt.bindString(2, classId);
        }
 
        String mid = entity.getMid();
        if (mid != null) {
            stmt.bindString(3, mid);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ClassDynInfo readEntity(Cursor cursor, int offset) {
        ClassDynInfo entity = new ClassDynInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // classId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // mid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ClassDynInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setClassId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMid(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ClassDynInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ClassDynInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ClassDynInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}