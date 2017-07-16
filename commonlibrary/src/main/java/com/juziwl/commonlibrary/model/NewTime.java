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
public class NewTime implements Serializable {
    @Transient
    private static final long serialVersionUID = -8998765825566236870L;
    @Id(autoincrement = true)
    public Long id;
    public String userId = "";
    public String time = "";
    @Generated(hash = 1857005803)
    public NewTime(Long id, String userId, String time) {
        this.id = id;
        this.userId = userId;
        this.time = time;
    }
    @Generated(hash = 408118194)
    public NewTime() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
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
}
