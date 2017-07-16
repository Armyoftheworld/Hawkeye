package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

@Entity
public class Msg implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901480000566236870L;
    @Id(autoincrement = true)
    public Long id;
    public String fromUser = ""; //来自哪个用户
    public String toUser = ""; //发给哪个用户
    public String msgtext = ""; //如果是文件或者语音为本地路径
    public String datetime = "";
    public String isFrom = ""; //
    public String isRead = "";
    public Boolean isLoadOver = true;
    public String msgtype = "";//文件类型  0 文字 1图片 2语音 3名片 4文章 7课件
    public String cardId = "";//名片的id
    public String title = "";//名片的标题
    public String iconurl = "";//名片的头像
    public String filepath = ""; // 本地路径，图片、语音、文章的链接
    public Long speechlength = 0l; //语音的长度
    public String whoid = "";
    public String fid = "";
    public String username = "";
    public String face = "";
    public String clazzid = "";
    public String clazzimg = "";
    public String clazzname = "";
    public String chatflag = "";
    public String role = "";
    public String isLocal = ""; //是否是本地的文件，本地为1
    public String courseId = "";

    @Generated(hash = 1444151296)
    public Msg(Long id, String fromUser, String toUser, String msgtext,
            String datetime, String isFrom, String isRead, Boolean isLoadOver,
            String msgtype, String cardId, String title, String iconurl,
            String filepath, Long speechlength, String whoid, String fid,
            String username, String face, String clazzid, String clazzimg,
            String clazzname, String chatflag, String role, String isLocal,
            String courseId) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.msgtext = msgtext;
        this.datetime = datetime;
        this.isFrom = isFrom;
        this.isRead = isRead;
        this.isLoadOver = isLoadOver;
        this.msgtype = msgtype;
        this.cardId = cardId;
        this.title = title;
        this.iconurl = iconurl;
        this.filepath = filepath;
        this.speechlength = speechlength;
        this.whoid = whoid;
        this.fid = fid;
        this.username = username;
        this.face = face;
        this.clazzid = clazzid;
        this.clazzimg = clazzimg;
        this.clazzname = clazzname;
        this.chatflag = chatflag;
        this.role = role;
        this.isLocal = isLocal;
        this.courseId = courseId;
    }

    @Generated(hash = 23037457)
    public Msg() {
    }

    @Override
    public String toString() {
        return "Msg{" +
                "id='" + id + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", msgtext='" + msgtext + '\'' +
                ", datetime='" + datetime + '\'' +
                ", isFrom='" + isFrom + '\'' +
                ", isRead='" + isRead + '\'' +
                ", isLoadOver=" + isLoadOver +
                ", msgtype='" + msgtype + '\'' +
                ", cardId='" + cardId + '\'' +
                ", title='" + title + '\'' +
                ", iconurl='" + iconurl + '\'' +
                ", filepath='" + filepath + '\'' +
                ", speechlength=" + speechlength +
                ", whoid='" + whoid + '\'' +
                ", fid='" + fid + '\'' +
                ", username='" + username + '\'' +
                ", face='" + face + '\'' +
                ", clazzid='" + clazzid + '\'' +
                ", clazzimg='" + clazzimg + '\'' +
                ", clazzname='" + clazzname + '\'' +
                ", chatflag='" + chatflag + '\'' +
                ", role='" + role + '\'' +
                ", isLocal='" + isLocal + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromUser() {
        return this.fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return this.toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsgtext() {
        return this.msgtext;
    }

    public void setMsgtext(String msgtext) {
        this.msgtext = msgtext;
    }

    public String getDatetime() {
        return this.datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getIsFrom() {
        return this.isFrom;
    }

    public void setIsFrom(String isFrom) {
        this.isFrom = isFrom;
    }

    public String getIsRead() {
        return this.isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsLoadOver() {
        return this.isLoadOver;
    }

    public void setIsLoadOver(Boolean isLoadOver) {
        this.isLoadOver = isLoadOver;
    }

    public String getMsgtype() {
        return this.msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getCardId() {
        return this.cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconurl() {
        return this.iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getFilepath() {
        return this.filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Long getSpeechlength() {
        return this.speechlength;
    }

    public void setSpeechlength(Long speechlength) {
        this.speechlength = speechlength;
    }

    public String getWhoid() {
        return this.whoid;
    }

    public void setWhoid(String whoid) {
        this.whoid = whoid;
    }

    public String getFid() {
        return this.fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFace() {
        return this.face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getClazzid() {
        return this.clazzid;
    }

    public void setClazzid(String clazzid) {
        this.clazzid = clazzid;
    }

    public String getClazzimg() {
        return this.clazzimg;
    }

    public void setClazzimg(String clazzimg) {
        this.clazzimg = clazzimg;
    }

    public String getClazzname() {
        return this.clazzname;
    }

    public void setClazzname(String clazzname) {
        this.clazzname = clazzname;
    }

    public String getChatflag() {
        return this.chatflag;
    }

    public void setChatflag(String chatflag) {
        this.chatflag = chatflag;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIsLocal() {
        return this.isLocal;
    }

    public void setIsLocal(String isLocal) {
        this.isLocal = isLocal;
    }

    public String getCourseId() {
        return this.courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
