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
public class ClazzStartEndTime implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901799545566236870L;
    @Id(autoincrement = true)
    public Long id;
    public String startTime = "";
    public String endTime = "";
    public String updateTime = "";
    public String classId = "";
    public String mid = "";
    @Generated(hash = 156145320)
    public ClazzStartEndTime(Long id, String startTime, String endTime,
            String updateTime, String classId, String mid) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.updateTime = updateTime;
        this.classId = classId;
        this.mid = mid;
    }
    @Generated(hash = 2113084010)
    public ClazzStartEndTime() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getStartTime() {
        return this.startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndTime() {
        return this.endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    public String getClassId() {
        return this.classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getMid() {
        return this.mid;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
}
