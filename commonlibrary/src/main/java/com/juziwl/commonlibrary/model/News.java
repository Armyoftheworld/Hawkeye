package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月24日
 * @description 校园通知的实体类
 */
public class News implements Serializable{
    @Transient
    private static final long serialVersionUID = -8901481478523236870L;
    @Id(autoincrement = true)
    public Long id;
    public String newsCode;
    public String newsTitle;
    public String newsTime;
    public String url;
    public String picUrl2;
    public String picUrl;
    public String desc;
    public String mid;
    public String flag;

    @Override
    public String toString() {
        return "News{" +
                "newsCode='" + newsCode + '\'' +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsTime='" + newsTime + '\'' +
                ", url='" + url + '\'' +
                ", picUrl2='" + picUrl2 + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
