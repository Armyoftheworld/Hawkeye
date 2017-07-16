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
public class ProvinceCityDistinct implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901488825514523870L;
    @Id(autoincrement = true)
    public Long id;
    public String provinceId = "";
    public String provinceName = "";
    public String cityId = "";
    public String cityName = "";
    public String districtId = "";
    public String districtName = "";
    @Generated(hash = 462084289)
    public ProvinceCityDistinct(Long id, String provinceId, String provinceName,
            String cityId, String cityName, String districtId,
            String districtName) {
        this.id = id;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cityId = cityId;
        this.cityName = cityName;
        this.districtId = districtId;
        this.districtName = districtName;
    }
    @Generated(hash = 1382072388)
    public ProvinceCityDistinct() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public String getCityId() {
        return this.cityId;
    }
    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public String getDistrictId() {
        return this.districtId;
    }
    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }
    public String getDistrictName() {
        return this.districtName;
    }
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
