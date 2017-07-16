package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/04/25
 * @description
 */
@Entity
public class CourseFile implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901488825566237415L;
    @Id(autoincrement = true)
    public Long id;
    public String fileName = "";
    public String filePath = "";
    public String userId = "";
    public String time = "";
    public String downloadUrl = "";
    public Integer fileSize = 0;
    public Integer isLocalUpload = 0;
    @Generated(hash = 861952921)
    public CourseFile(Long id, String fileName, String filePath, String userId,
            String time, String downloadUrl, Integer fileSize,
            Integer isLocalUpload) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.userId = userId;
        this.time = time;
        this.downloadUrl = downloadUrl;
        this.fileSize = fileSize;
        this.isLocalUpload = isLocalUpload;
    }
    @Generated(hash = 704207156)
    public CourseFile() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return this.fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return this.filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getDownloadUrl() {
        return this.downloadUrl;
    }
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
    public Integer getFileSize() {
        return this.fileSize;
    }
    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }
    public Integer getIsLocalUpload() {
        return this.isLocalUpload;
    }
    public void setIsLocalUpload(Integer isLocalUpload) {
        this.isLocalUpload = isLocalUpload;
    }
}
