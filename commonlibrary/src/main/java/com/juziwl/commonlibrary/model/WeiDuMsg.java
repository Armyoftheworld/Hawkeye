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
 * @description 未读消息
 */
@Entity
public class WeiDuMsg implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901481249666236870L;
    @Id(autoincrement = true)
    public Long id;
    public String uid = "";
    public String mid = "";
    public String count = "";
    @Generated(hash = 1183927964)
    public WeiDuMsg(Long id, String uid, String mid, String count) {
        this.id = id;
        this.uid = uid;
        this.mid = mid;
        this.count = count;
    }
    @Generated(hash = 1562907082)
    public WeiDuMsg() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUid() {
        return this.uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getMid() {
        return this.mid;
    }
    public void setMid(String mid) {
        this.mid = mid;
    }
    public String getCount() {
        return this.count;
    }
    public void setCount(String count) {
        this.count = count;
    }
}
