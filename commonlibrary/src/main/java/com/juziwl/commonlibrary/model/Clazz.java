package com.juziwl.commonlibrary.model;

import com.alibaba.fastjson.annotation.JSONField;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月21日
 * @description 班级类
 */
@Entity
public class Clazz implements Serializable {
    @Transient
    private static final long serialVersionUID = -1425388825566236870L;
    @Id(autoincrement = true)
    public Long id;
    public String userId = "";
    public String role = "open";
    public String schoolId = "";
    public String schoolName = "";
    public String classId = "";
    public String className = "";
    public String studentId = "";
    public String studentName = "";
    public String address = "";
    public String subjectName = "";
    @JSONField(name = "subJectId")
    public String subjectId = "";
    public String chatFun = "0";
    public String sendFun = "0";
    public String isnickname = "";
    public String classBlocked = "false";
    public String owner = "false";
    public String classNo = "";
    public Boolean isSelected = false;//班级选择要用
    public String createTime = "";//创建时间
    public String memsNum = "";//成员人数
    public Boolean hasNew = false;
    public Integer isChat = 1;  //判断用户在当前班级是否被禁言，-1是被禁言了，1是没有被禁言
    public Integer isTeacher = 0; //1是老师
    public String classNamePinyin = "";
    public Integer flag = 1;
    public String mid = "";

    @Generated(hash = 13014758)
    public Clazz(Long id, String userId, String role, String schoolId,
            String schoolName, String classId, String className, String studentId,
            String studentName, String address, String subjectName,
            String subjectId, String chatFun, String sendFun, String isnickname,
            String classBlocked, String owner, String classNo, Boolean isSelected,
            String createTime, String memsNum, Boolean hasNew, Integer isChat,
            Integer isTeacher, String classNamePinyin, Integer flag, String mid) {
        this.id = id;
        this.userId = userId;
        this.role = role;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.classId = classId;
        this.className = className;
        this.studentId = studentId;
        this.studentName = studentName;
        this.address = address;
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.chatFun = chatFun;
        this.sendFun = sendFun;
        this.isnickname = isnickname;
        this.classBlocked = classBlocked;
        this.owner = owner;
        this.classNo = classNo;
        this.isSelected = isSelected;
        this.createTime = createTime;
        this.memsNum = memsNum;
        this.hasNew = hasNew;
        this.isChat = isChat;
        this.isTeacher = isTeacher;
        this.classNamePinyin = classNamePinyin;
        this.flag = flag;
        this.mid = mid;
    }

    @Generated(hash = 1166360579)
    public Clazz() {
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "userId='" + userId + '\'' +
                ", role='" + role + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", address='" + address + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", disflag='" + chatFun + '\'' +
                ", shiflag='" + sendFun + '\'' +
                ", isnickname='" + isnickname + '\'' +
                ", classBlocked='" + classBlocked + '\'' +
                ", owner='" + owner + '\'' +
                ", classNo='" + classNo + '\'' +
                ", isSelected=" + isSelected +
                ", createTime='" + createTime + '\'' +
                ", memsNum='" + memsNum + '\'' +
                ", hasNew=" + hasNew +
                '}';
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

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSchoolId() {
        return this.schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubjectName() {
        return this.subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectId() {
        return this.subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getChatFun() {
        return this.chatFun;
    }

    public void setChatFun(String chatFun) {
        this.chatFun = chatFun;
    }

    public String getSendFun() {
        return this.sendFun;
    }

    public void setSendFun(String sendFun) {
        this.sendFun = sendFun;
    }

    public String getIsnickname() {
        return this.isnickname;
    }

    public void setIsnickname(String isnickname) {
        this.isnickname = isnickname;
    }

    public String getClassBlocked() {
        return this.classBlocked;
    }

    public void setClassBlocked(String classBlocked) {
        this.classBlocked = classBlocked;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getClassNo() {
        return this.classNo;
    }

    public void setClassNo(String classNo) {
        this.classNo = classNo;
    }

    public Boolean getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMemsNum() {
        return this.memsNum;
    }

    public void setMemsNum(String memsNum) {
        this.memsNum = memsNum;
    }

    public Boolean getHasNew() {
        return this.hasNew;
    }

    public void setHasNew(Boolean hasNew) {
        this.hasNew = hasNew;
    }

    public Integer getIsChat() {
        return this.isChat;
    }

    public void setIsChat(Integer isChat) {
        this.isChat = isChat;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public Integer getIsTeacher() {
        return this.isTeacher;
    }

    public void setIsTeacher(Integer isTeacher) {
        this.isTeacher = isTeacher;
    }

    public String getClassNamePinyin() {
        return this.classNamePinyin;
    }

    public void setClassNamePinyin(String classNamePinyin) {
        this.classNamePinyin = classNamePinyin;
    }

    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
