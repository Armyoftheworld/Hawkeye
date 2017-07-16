package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

@Entity
public class ServiceItem implements Serializable{
    @Transient
    private static final long serialVersionUID = -8915975325566236870L;
    @Id(autoincrement = true)
    public Long ID;
    public String appName;
    private Integer appIcomResId = -1;
    public Integer id;
    public Integer orderId;
    public Integer selectedone;
    public Boolean selected = false;
    public Integer isHasNew = 0;//0 没有新的      1 有新的
    public String userId = "";

    @Generated(hash = 223246903)
    public ServiceItem() {
    }
//
//    @Generated(hash = 525421857)
//    public ServiceItem(Long ID, String appName, Integer appIcomResId, Integer id,
//            Integer orderId, Integer selectedone, Boolean selected,
//            Integer isHasNew, String userId) {
//        this.ID = ID;
//        this.appName = appName;
//        this.appIcomResId = appIcomResId;
//        this.id = id;
//        this.orderId = orderId;
//        this.selectedone = selectedone;
//        this.selected = selected;
//        this.isHasNew = isHasNew;
//        this.userId = userId;
//    }

    @Generated(hash = 525421857)
    public ServiceItem(Long ID, String appName, Integer appIcomResId, Integer id,
            Integer orderId, Integer selectedone, Boolean selected,
            Integer isHasNew, String userId) {
        this.ID = ID;
        this.appName = appName;
        this.appIcomResId = appIcomResId;
        this.id = id;
        this.orderId = orderId;
        this.selectedone = selectedone;
        this.selected = selected;
        this.isHasNew = isHasNew;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ServiceItem{" +
                "appName='" + appName + '\'' +
                ", appIcomResId=" + appIcomResId +
                ", id=" + id +
                ", orderId=" + orderId +
                ", selectedone=" + selectedone +
                ", selected=" + selected +
                ", isHasNew=" + isHasNew +
                ", userId='" + userId + '\'' +
                '}';
    }

    public Long getID() {
        return this.ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppIcomResId() {
        return this.appIcomResId;
    }

    public void setAppIcomResId(Integer appIcomResId) {
        this.appIcomResId = appIcomResId;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getSelectedone() {
        return this.selectedone;
    }

    public void setSelectedone(Integer selectedone) {
        this.selectedone = selectedone;
    }

    public Boolean getSelected() {
        return this.selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Integer getIsHasNew() {
        return this.isHasNew;
    }

    public void setIsHasNew(Integer isHasNew) {
        this.isHasNew = isHasNew;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
