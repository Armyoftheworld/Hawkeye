package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016/3/25
 * @description 公众号
 */
@Entity
public class OnlineSchool implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901488745636236870L;
    @Id(autoincrement = true)
    public Long id;
    public String p_id = "";
    public String s_mail = "";
    public String s_account = "";
    public String s_phone = "";
    public String s_xiaoxin_code = "";
    public String s_name = "";
    public String s_img = "";
    public String s_type = "";//公众号类型  0个人   1企业
    public String s_principal = "";//账号主体
    public String s_identity = "";//0普通公众号  1广告主  2流量主  3 广告主和流量主
    public String s_auth_status = "";//0已认证   1未认证   2申请认证   3认证驳回
    public String s_auth_time = "";
    public String s_desc = "";
    public String s_blance = "";
    public String s_province = "";
    public String s_city = "";
    public String s_area = "";
    public String s_addr = "";
    public String s_state = "";
    public String s_login_time = "";
    public String s_create_time = "";
    public String s_update_time = "";
    public String s_isAudit = "";
    public String s_user_num = "";
    public String s_report_num = "";
    public String s_point = "";
    public String s_rank = "";
    public String s_grand = "";
    public String isFollow = "";//1 是没关注 -1 是关注
    public String tedian = "";
    public String pinyin = "";
    public String staticPath = "";
    public Integer receiveMsg = -1;//-1 接收  1不接受
    public Boolean isFromHistory = false;
    public Integer msgNum = 0;
    public Boolean isDoCancelFollow = false;
    public String mid = "";

    @Generated(hash = 1300693491)
    public OnlineSchool(Long id, String p_id, String s_mail, String s_account,
            String s_phone, String s_xiaoxin_code, String s_name, String s_img,
            String s_type, String s_principal, String s_identity,
            String s_auth_status, String s_auth_time, String s_desc,
            String s_blance, String s_province, String s_city, String s_area,
            String s_addr, String s_state, String s_login_time,
            String s_create_time, String s_update_time, String s_isAudit,
            String s_user_num, String s_report_num, String s_point, String s_rank,
            String s_grand, String isFollow, String tedian, String pinyin,
            String staticPath, Integer receiveMsg, Boolean isFromHistory,
            Integer msgNum, Boolean isDoCancelFollow, String mid) {
        this.id = id;
        this.p_id = p_id;
        this.s_mail = s_mail;
        this.s_account = s_account;
        this.s_phone = s_phone;
        this.s_xiaoxin_code = s_xiaoxin_code;
        this.s_name = s_name;
        this.s_img = s_img;
        this.s_type = s_type;
        this.s_principal = s_principal;
        this.s_identity = s_identity;
        this.s_auth_status = s_auth_status;
        this.s_auth_time = s_auth_time;
        this.s_desc = s_desc;
        this.s_blance = s_blance;
        this.s_province = s_province;
        this.s_city = s_city;
        this.s_area = s_area;
        this.s_addr = s_addr;
        this.s_state = s_state;
        this.s_login_time = s_login_time;
        this.s_create_time = s_create_time;
        this.s_update_time = s_update_time;
        this.s_isAudit = s_isAudit;
        this.s_user_num = s_user_num;
        this.s_report_num = s_report_num;
        this.s_point = s_point;
        this.s_rank = s_rank;
        this.s_grand = s_grand;
        this.isFollow = isFollow;
        this.tedian = tedian;
        this.pinyin = pinyin;
        this.staticPath = staticPath;
        this.receiveMsg = receiveMsg;
        this.isFromHistory = isFromHistory;
        this.msgNum = msgNum;
        this.isDoCancelFollow = isDoCancelFollow;
        this.mid = mid;
    }

    @Generated(hash = 167687319)
    public OnlineSchool() {
    }

    @Override
    public String toString() {
        return "OnlineSchool{" +
                "p_id='" + p_id + '\'' +
                ", s_mail='" + s_mail + '\'' +
                ", s_account='" + s_account + '\'' +
                ", s_phone='" + s_phone + '\'' +
                ", s_xiaoxin_code='" + s_xiaoxin_code + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_img='" + s_img + '\'' +
                ", s_type='" + s_type + '\'' +
                ", s_principal='" + s_principal + '\'' +
                ", s_identity='" + s_identity + '\'' +
                ", s_auth_status='" + s_auth_status + '\'' +
                ", s_auth_time='" + s_auth_time + '\'' +
                ", s_desc='" + s_desc + '\'' +
                ", s_blance='" + s_blance + '\'' +
                ", s_province='" + s_province + '\'' +
                ", s_city='" + s_city + '\'' +
                ", s_area='" + s_area + '\'' +
                ", s_addr='" + s_addr + '\'' +
                ", s_state='" + s_state + '\'' +
                ", s_login_time='" + s_login_time + '\'' +
                ", s_create_time='" + s_create_time + '\'' +
                ", s_update_time='" + s_update_time + '\'' +
                ", s_isAudit='" + s_isAudit + '\'' +
                ", s_user_num='" + s_user_num + '\'' +
                ", s_report_num='" + s_report_num + '\'' +
                ", s_point='" + s_point + '\'' +
                ", s_rank='" + s_rank + '\'' +
                ", s_grand='" + s_grand + '\'' +
                ", isFollow='" + isFollow + '\'' +
                ", tedian='" + tedian + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", staticPath='" + staticPath + '\'' +
                ", receiveMsg=" + receiveMsg +
                ", isFromHistory=" + isFromHistory +
                ", msgNum=" + msgNum +
                ", isDoCancelFollow=" + isDoCancelFollow +
                ", mid='" + mid + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getP_id() {
        return this.p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public String getS_mail() {
        return this.s_mail;
    }

    public void setS_mail(String s_mail) {
        this.s_mail = s_mail;
    }

    public String getS_account() {
        return this.s_account;
    }

    public void setS_account(String s_account) {
        this.s_account = s_account;
    }

    public String getS_phone() {
        return this.s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_xiaoxin_code() {
        return this.s_xiaoxin_code;
    }

    public void setS_xiaoxin_code(String s_xiaoxin_code) {
        this.s_xiaoxin_code = s_xiaoxin_code;
    }

    public String getS_name() {
        return this.s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_img() {
        return this.s_img;
    }

    public void setS_img(String s_img) {
        this.s_img = s_img;
    }

    public String getS_type() {
        return this.s_type;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

    public String getS_principal() {
        return this.s_principal;
    }

    public void setS_principal(String s_principal) {
        this.s_principal = s_principal;
    }

    public String getS_identity() {
        return this.s_identity;
    }

    public void setS_identity(String s_identity) {
        this.s_identity = s_identity;
    }

    public String getS_auth_status() {
        return this.s_auth_status;
    }

    public void setS_auth_status(String s_auth_status) {
        this.s_auth_status = s_auth_status;
    }

    public String getS_auth_time() {
        return this.s_auth_time;
    }

    public void setS_auth_time(String s_auth_time) {
        this.s_auth_time = s_auth_time;
    }

    public String getS_desc() {
        return this.s_desc;
    }

    public void setS_desc(String s_desc) {
        this.s_desc = s_desc;
    }

    public String getS_blance() {
        return this.s_blance;
    }

    public void setS_blance(String s_blance) {
        this.s_blance = s_blance;
    }

    public String getS_province() {
        return this.s_province;
    }

    public void setS_province(String s_province) {
        this.s_province = s_province;
    }

    public String getS_city() {
        return this.s_city;
    }

    public void setS_city(String s_city) {
        this.s_city = s_city;
    }

    public String getS_area() {
        return this.s_area;
    }

    public void setS_area(String s_area) {
        this.s_area = s_area;
    }

    public String getS_addr() {
        return this.s_addr;
    }

    public void setS_addr(String s_addr) {
        this.s_addr = s_addr;
    }

    public String getS_state() {
        return this.s_state;
    }

    public void setS_state(String s_state) {
        this.s_state = s_state;
    }

    public String getS_login_time() {
        return this.s_login_time;
    }

    public void setS_login_time(String s_login_time) {
        this.s_login_time = s_login_time;
    }

    public String getS_create_time() {
        return this.s_create_time;
    }

    public void setS_create_time(String s_create_time) {
        this.s_create_time = s_create_time;
    }

    public String getS_update_time() {
        return this.s_update_time;
    }

    public void setS_update_time(String s_update_time) {
        this.s_update_time = s_update_time;
    }

    public String getS_isAudit() {
        return this.s_isAudit;
    }

    public void setS_isAudit(String s_isAudit) {
        this.s_isAudit = s_isAudit;
    }

    public String getS_user_num() {
        return this.s_user_num;
    }

    public void setS_user_num(String s_user_num) {
        this.s_user_num = s_user_num;
    }

    public String getS_report_num() {
        return this.s_report_num;
    }

    public void setS_report_num(String s_report_num) {
        this.s_report_num = s_report_num;
    }

    public String getS_point() {
        return this.s_point;
    }

    public void setS_point(String s_point) {
        this.s_point = s_point;
    }

    public String getS_rank() {
        return this.s_rank;
    }

    public void setS_rank(String s_rank) {
        this.s_rank = s_rank;
    }

    public String getS_grand() {
        return this.s_grand;
    }

    public void setS_grand(String s_grand) {
        this.s_grand = s_grand;
    }

    public String getIsFollow() {
        return this.isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }

    public String getTedian() {
        return this.tedian;
    }

    public void setTedian(String tedian) {
        this.tedian = tedian;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getStaticPath() {
        return this.staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public Integer getReceiveMsg() {
        return this.receiveMsg;
    }

    public void setReceiveMsg(Integer receiveMsg) {
        this.receiveMsg = receiveMsg;
    }

    public Boolean getIsFromHistory() {
        return this.isFromHistory;
    }

    public void setIsFromHistory(Boolean isFromHistory) {
        this.isFromHistory = isFromHistory;
    }

    public Integer getMsgNum() {
        return this.msgNum;
    }

    public void setMsgNum(Integer msgNum) {
        this.msgNum = msgNum;
    }

    public Boolean getIsDoCancelFollow() {
        return this.isDoCancelFollow;
    }

    public void setIsDoCancelFollow(Boolean isDoCancelFollow) {
        this.isDoCancelFollow = isDoCancelFollow;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
