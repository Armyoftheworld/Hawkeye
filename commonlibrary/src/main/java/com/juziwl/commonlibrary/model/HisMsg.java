package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

@Entity
public class HisMsg implements Serializable{
	@Transient
	private static final long serialVersionUID = -8901474136966236870L;
	@Id(autoincrement = true)
	public Long id;
	public String fromId="";
	public String msgImg=""; 
	public String msgText="";
	public String msgTitle="";
	public String msgTime="";
	public String chatType="";//1代表单聊，2代表群聊
	public String isDel="";//0代表正常聊天，1代表不能聊天
	public String newNums="";
	public String flag1=""; 
	public String flag2=""; 
	public String flag3="";//代表是否是开放班级
	public String mid="";

	@Generated(hash = 697032166)
	public HisMsg(Long id, String fromId, String msgImg, String msgText,
									String msgTitle, String msgTime, String chatType, String isDel,
									String newNums, String flag1, String flag2, String flag3, String mid) {
					this.id = id;
					this.fromId = fromId;
					this.msgImg = msgImg;
					this.msgText = msgText;
					this.msgTitle = msgTitle;
					this.msgTime = msgTime;
					this.chatType = chatType;
					this.isDel = isDel;
					this.newNums = newNums;
					this.flag1 = flag1;
					this.flag2 = flag2;
					this.flag3 = flag3;
					this.mid = mid;
	}

	@Generated(hash = 1702274521)
	public HisMsg() {
	}

	@Override
	public String toString() {
		return "HisMsg{" +
				"id='" + id + '\'' +
				", fromId='" + fromId + '\'' +
				", msgImg='" + msgImg + '\'' +
				", msgText='" + msgText + '\'' +
				", msgTitle='" + msgTitle + '\'' +
				", msgTime='" + msgTime + '\'' +
				", chatType='" + chatType + '\'' +
				", isDel='" + isDel + '\'' +
				", newNums='" + newNums + '\'' +
				", flag1='" + flag1 + '\'' +
				", flag2='" + flag2 + '\'' +
				", flag3='" + flag3 + '\'' +
				", mid='" + mid + '\'' +
				'}';
	}

	public Long getId() {
					return this.id;
	}

	public void setId(Long id) {
					this.id = id;
	}

	public String getFromId() {
					return this.fromId;
	}

	public void setFromId(String fromId) {
					this.fromId = fromId;
	}

	public String getMsgImg() {
					return this.msgImg;
	}

	public void setMsgImg(String msgImg) {
					this.msgImg = msgImg;
	}

	public String getMsgText() {
					return this.msgText;
	}

	public void setMsgText(String msgText) {
					this.msgText = msgText;
	}

	public String getMsgTitle() {
					return this.msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
					this.msgTitle = msgTitle;
	}

	public String getMsgTime() {
					return this.msgTime;
	}

	public void setMsgTime(String msgTime) {
					this.msgTime = msgTime;
	}

	public String getChatType() {
					return this.chatType;
	}

	public void setChatType(String chatType) {
					this.chatType = chatType;
	}

	public String getIsDel() {
					return this.isDel;
	}

	public void setIsDel(String isDel) {
					this.isDel = isDel;
	}

	public String getNewNums() {
					return this.newNums;
	}

	public void setNewNums(String newNums) {
					this.newNums = newNums;
	}

	public String getFlag1() {
					return this.flag1;
	}

	public void setFlag1(String flag1) {
					this.flag1 = flag1;
	}

	public String getFlag2() {
					return this.flag2;
	}

	public void setFlag2(String flag2) {
					this.flag2 = flag2;
	}

	public String getFlag3() {
					return this.flag3;
	}

	public void setFlag3(String flag3) {
					this.flag3 = flag3;
	}

	public String getMid() {
					return this.mid;
	}

	public void setMid(String mid) {
					this.mid = mid;
	}
}
