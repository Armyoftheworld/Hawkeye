package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年03月14日
 * @description 历史记录
 */
@Entity
public class SearchHistory implements Serializable {
    @Transient
    private static final long serialVersionUID = -8908526325566236870L;
    @Id(autoincrement = true)
    public Long hId;
    public String userId = "";
    public String content = "";
    @Generated(hash = 1965835509)
    public SearchHistory(Long hId, String userId, String content) {
        this.hId = hId;
        this.userId = userId;
        this.content = content;
    }
    @Generated(hash = 1905904755)
    public SearchHistory() {
    }
    public Long getHId() {
        return this.hId;
    }
    public void setHId(Long hId) {
        this.hId = hId;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
