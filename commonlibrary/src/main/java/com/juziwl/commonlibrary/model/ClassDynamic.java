package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年02月28日
 * @description 班级动态
 */
@Entity
public class ClassDynamic implements Serializable {
    @Transient
    private static final long serialVersionUID = -8901488823366236870L;
    @Id(autoincrement = true)
    public Long id;
    public String eventId;
    public String createTime;
    public String isFavourite;
    public String userId;
    public String eventInfo;
    public String fullName;
    public String avatar;
    public String eventImage;
    public String classId;
    public String favouriteNum;
    public String className;
    public String commentNum;
    public String isDelete = "";
    public String deleteTime;
    public String type;   //1 普通的班级动态 2 带有连接的班级动态
    public String articleTitle = "";
    public String articleId = "";
    public String articleUrl = "";
    public String articleImg = "";
    public String articleDesc = "";
    public String scoreCount;
    public String giftCount;
    public String isSendGift;
    public String isSendScore;
    public String videoUrl;
    public String videoPicUrl;
    public String videoSize;
    public String platform = "";//区分是ios和android，android是要上传的，为空的话默认是IOS和老版本
    public String isPinglun;
    public String mid = "";

    @Generated(hash = 1876552911)
    public ClassDynamic(Long id, String eventId, String createTime,
            String isFavourite, String userId, String eventInfo, String fullName,
            String avatar, String eventImage, String classId, String favouriteNum,
            String className, String commentNum, String isDelete, String deleteTime,
            String type, String articleTitle, String articleId, String articleUrl,
            String articleImg, String articleDesc, String scoreCount,
            String giftCount, String isSendGift, String isSendScore,
            String videoUrl, String videoPicUrl, String videoSize, String platform,
            String isPinglun, String mid) {
        this.id = id;
        this.eventId = eventId;
        this.createTime = createTime;
        this.isFavourite = isFavourite;
        this.userId = userId;
        this.eventInfo = eventInfo;
        this.fullName = fullName;
        this.avatar = avatar;
        this.eventImage = eventImage;
        this.classId = classId;
        this.favouriteNum = favouriteNum;
        this.className = className;
        this.commentNum = commentNum;
        this.isDelete = isDelete;
        this.deleteTime = deleteTime;
        this.type = type;
        this.articleTitle = articleTitle;
        this.articleId = articleId;
        this.articleUrl = articleUrl;
        this.articleImg = articleImg;
        this.articleDesc = articleDesc;
        this.scoreCount = scoreCount;
        this.giftCount = giftCount;
        this.isSendGift = isSendGift;
        this.isSendScore = isSendScore;
        this.videoUrl = videoUrl;
        this.videoPicUrl = videoPicUrl;
        this.videoSize = videoSize;
        this.platform = platform;
        this.isPinglun = isPinglun;
        this.mid = mid;
    }

    @Generated(hash = 875115111)
    public ClassDynamic() {
    }

    @Override
    public String toString() {
        return "ClassDynamic{" +
                "eventId='" + eventId + '\'' +
                ", createTime='" + createTime + '\'' +
                ", isFavourite='" + isFavourite + '\'' +
                ", userId='" + userId + '\'' +
                ", eventInfo='" + eventInfo + '\'' +
                ", fullName='" + fullName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", eventImage='" + eventImage + '\'' +
                ", classId='" + classId + '\'' +
                ", favouriteNum='" + favouriteNum + '\'' +
                ", className='" + className + '\'' +
                ", commentNum='" + commentNum + '\'' +
                ", isDelete='" + isDelete + '\'' +
                ", deleteTime='" + deleteTime + '\'' +
                ", type='" + type + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleId='" + articleId + '\'' +
                ", articleUrl='" + articleUrl + '\'' +
                ", articleImg='" + articleImg + '\'' +
                ", articleDesc='" + articleDesc + '\'' +
                ", mid='" + mid + '\'' +
                ", scoreCount='" + scoreCount + '\'' +
                ", giftCount='" + giftCount + '\'' +
                ", isSendGift='" + isSendGift + '\'' +
                ", isSendScore='" + isSendScore + '\'' +
                ",isPinglun='" + isPinglun + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEventId() {
        return this.eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIsFavourite() {
        return this.isFavourite;
    }

    public void setIsFavourite(String isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEventInfo() {
        return this.eventInfo;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEventImage() {
        return this.eventImage;
    }

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getClassId() {
        return this.classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getFavouriteNum() {
        return this.favouriteNum;
    }

    public void setFavouriteNum(String favouriteNum) {
        this.favouriteNum = favouriteNum;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCommentNum() {
        return this.commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getIsDelete() {
        return this.isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getDeleteTime() {
        return this.deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArticleTitle() {
        return this.articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleId() {
        return this.articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleUrl() {
        return this.articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleImg() {
        return this.articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public String getArticleDesc() {
        return this.articleDesc;
    }

    public void setArticleDesc(String articleDesc) {
        this.articleDesc = articleDesc;
    }

    public String getScoreCount() {
        return this.scoreCount;
    }

    public void setScoreCount(String scoreCount) {
        this.scoreCount = scoreCount;
    }

    public String getGiftCount() {
        return this.giftCount;
    }

    public void setGiftCount(String giftCount) {
        this.giftCount = giftCount;
    }

    public String getIsSendGift() {
        return this.isSendGift;
    }

    public void setIsSendGift(String isSendGift) {
        this.isSendGift = isSendGift;
    }

    public String getIsSendScore() {
        return this.isSendScore;
    }

    public void setIsSendScore(String isSendScore) {
        this.isSendScore = isSendScore;
    }

    public String getVideoUrl() {
        return this.videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoPicUrl() {
        return this.videoPicUrl;
    }

    public void setVideoPicUrl(String videoPicUrl) {
        this.videoPicUrl = videoPicUrl;
    }

    public String getVideoSize() {
        return this.videoSize;
    }

    public void setVideoSize(String videoSize) {
        this.videoSize = videoSize;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getIsPinglun() {
        return this.isPinglun;
    }

    public void setIsPinglun(String isPinglun) {
        this.isPinglun = isPinglun;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
