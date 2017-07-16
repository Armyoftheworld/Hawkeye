package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月22日
 * @description 周边教育的地区的实体类
 */
@Entity
public class Area implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901488825566236870L;
    @Id(autoincrement = true)
    public Long id;
    //周边教育切换城市需要
    public String pinyin = "";// 第一个拼音字母用来排序
    public String firstLetter = "";
    public String areaName = "";
    public String areaid = "";
    public Integer isHotCity = 0;//0不是热门城市  1是
    public String parentCityName = "";
    public String parentCityId = "";
    @Generated(hash = 1478749740)
    public Area(Long id, String pinyin, String firstLetter, String areaName,
            String areaid, Integer isHotCity, String parentCityName,
            String parentCityId) {
        this.id = id;
        this.pinyin = pinyin;
        this.firstLetter = firstLetter;
        this.areaName = areaName;
        this.areaid = areaid;
        this.isHotCity = isHotCity;
        this.parentCityName = parentCityName;
        this.parentCityId = parentCityId;
    }
    @Generated(hash = 179626505)
    public Area() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPinyin() {
        return this.pinyin;
    }
    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    public String getFirstLetter() {
        return this.firstLetter;
    }
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
    public String getAreaName() {
        return this.areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public String getAreaid() {
        return this.areaid;
    }
    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }
    public Integer getIsHotCity() {
        return this.isHotCity;
    }
    public void setIsHotCity(Integer isHotCity) {
        this.isHotCity = isHotCity;
    }
    public String getParentCityName() {
        return this.parentCityName;
    }
    public void setParentCityName(String parentCityName) {
        this.parentCityName = parentCityName;
    }
    public String getParentCityId() {
        return this.parentCityId;
    }
    public void setParentCityId(String parentCityId) {
        this.parentCityId = parentCityId;
    }
}
