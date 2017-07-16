package com.juziwl.commonlibrary.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.juziwl.commonlibrary.model.CourseFile;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COURSE_FILE".
*/
public class CourseFileDao extends AbstractDao<CourseFile, Long> {

    public static final String TABLENAME = "COURSE_FILE";

    /**
     * Properties of entity CourseFile.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FileName = new Property(1, String.class, "fileName", false, "FILE_NAME");
        public final static Property FilePath = new Property(2, String.class, "filePath", false, "FILE_PATH");
        public final static Property UserId = new Property(3, String.class, "userId", false, "USER_ID");
        public final static Property Time = new Property(4, String.class, "time", false, "TIME");
        public final static Property DownloadUrl = new Property(5, String.class, "downloadUrl", false, "DOWNLOAD_URL");
        public final static Property FileSize = new Property(6, Integer.class, "fileSize", false, "FILE_SIZE");
        public final static Property IsLocalUpload = new Property(7, Integer.class, "isLocalUpload", false, "IS_LOCAL_UPLOAD");
    }


    public CourseFileDao(DaoConfig config) {
        super(config);
    }
    
    public CourseFileDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COURSE_FILE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"FILE_NAME\" TEXT," + // 1: fileName
                "\"FILE_PATH\" TEXT," + // 2: filePath
                "\"USER_ID\" TEXT," + // 3: userId
                "\"TIME\" TEXT," + // 4: time
                "\"DOWNLOAD_URL\" TEXT," + // 5: downloadUrl
                "\"FILE_SIZE\" INTEGER," + // 6: fileSize
                "\"IS_LOCAL_UPLOAD\" INTEGER);"); // 7: isLocalUpload
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COURSE_FILE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CourseFile entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(2, fileName);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(3, filePath);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(4, userId);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String downloadUrl = entity.getDownloadUrl();
        if (downloadUrl != null) {
            stmt.bindString(6, downloadUrl);
        }
 
        Integer fileSize = entity.getFileSize();
        if (fileSize != null) {
            stmt.bindLong(7, fileSize);
        }
 
        Integer isLocalUpload = entity.getIsLocalUpload();
        if (isLocalUpload != null) {
            stmt.bindLong(8, isLocalUpload);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CourseFile entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String fileName = entity.getFileName();
        if (fileName != null) {
            stmt.bindString(2, fileName);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(3, filePath);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(4, userId);
        }
 
        String time = entity.getTime();
        if (time != null) {
            stmt.bindString(5, time);
        }
 
        String downloadUrl = entity.getDownloadUrl();
        if (downloadUrl != null) {
            stmt.bindString(6, downloadUrl);
        }
 
        Integer fileSize = entity.getFileSize();
        if (fileSize != null) {
            stmt.bindLong(7, fileSize);
        }
 
        Integer isLocalUpload = entity.getIsLocalUpload();
        if (isLocalUpload != null) {
            stmt.bindLong(8, isLocalUpload);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CourseFile readEntity(Cursor cursor, int offset) {
        CourseFile entity = new CourseFile( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // fileName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // filePath
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // userId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // time
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // downloadUrl
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // fileSize
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7) // isLocalUpload
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CourseFile entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFileName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFilePath(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setUserId(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDownloadUrl(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFileSize(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setIsLocalUpload(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CourseFile entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CourseFile entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CourseFile entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}