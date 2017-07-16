package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2017年04月25日
 * @description 动态消息的个数
 */
@Entity
public class ClassDynInfo implements Serializable{
    @Transient
    private static final long serialVersionUID = -8901487846966236870L;
    @Id(autoincrement = true)
    public Long id;
    public String classId;
    public String mid;

    @Generated(hash = 1473201711)
    public ClassDynInfo(Long id, String classId, String mid) {
        this.id = id;
        this.classId = classId;
        this.mid = mid;
    }

    @Generated(hash = 73092294)
    public ClassDynInfo() {
    }

    @Override
    public String toString() {
        return "ClassDynInfo{" +
                "id='" + classId + '\'' +
                ", mid='" + mid + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
