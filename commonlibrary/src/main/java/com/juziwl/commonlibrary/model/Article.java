package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016/3/22
 * @description 线上学堂文章
 */
@Entity
public class Article implements Serializable {
    @Transient
    private static final long serialVersionUID = -8785814825566236870L;
    @Id(autoincrement = true)
    public Long id;
    public String p_id = "";
    public String s_title = "";
    public String s_author = "";
    public String s_pic = "";
    public String s_category = "";
    public String s_tag = "";
    public String s_abstract = "";//摘要文章简介
    public String s_html = "";
    public String s_url = "";//文章课件路径，视频ID
    public String s_type = "";//0图文  1视频  2课件
    public String f_groups_id = "";//消息组ID
    public String f_groups_layer = "";//消息所在组层数 0为首条
    public String s_isdel = "";//0已删除   1未删除
    public Integer s_read_num = 0;//阅读量
    public Integer s_click_num = 0;//点击数
    public Integer s_forward_num = 0;//转发次数
    public Integer s_collect_num = 0;//收藏数
    public Integer s_like_num = 0;//点赞数
    public Integer s_adv_num = 0;//广告点击数
    public Integer s_reward_num = 0;//打赏次数
    public String s_creator = "";
    public String s_creater_time = "";
    public String s_update_time = "";
    public Integer s_isPerm = 0;
    public String fileName = "";
    public Integer fileSize = 0;
    public String platId = "";
    public String platName = "";
    public String platImg = "";
    public String collectId = "";
    public String likeId = "";
    public String readId = "";
    public String isTuiguang = ""; //是否是推广消息
    public Integer isLocalUpload = 0; // 判断数据库中有无本文件 0是没有
    public String mid = "";
    public String isFollow = "";
    public String commentId = "";
    public Integer s_comment_num = 0;

    @Generated(hash = 1054590447)
    public Article(Long id, String p_id, String s_title, String s_author,
            String s_pic, String s_category, String s_tag, String s_abstract,
            String s_html, String s_url, String s_type, String f_groups_id,
            String f_groups_layer, String s_isdel, Integer s_read_num,
            Integer s_click_num, Integer s_forward_num, Integer s_collect_num,
            Integer s_like_num, Integer s_adv_num, Integer s_reward_num,
            String s_creator, String s_creater_time, String s_update_time,
            Integer s_isPerm, String fileName, Integer fileSize, String platId,
            String platName, String platImg, String collectId, String likeId,
            String readId, String isTuiguang, Integer isLocalUpload, String mid,
            String isFollow, String commentId, Integer s_comment_num) {
        this.id = id;
        this.p_id = p_id;
        this.s_title = s_title;
        this.s_author = s_author;
        this.s_pic = s_pic;
        this.s_category = s_category;
        this.s_tag = s_tag;
        this.s_abstract = s_abstract;
        this.s_html = s_html;
        this.s_url = s_url;
        this.s_type = s_type;
        this.f_groups_id = f_groups_id;
        this.f_groups_layer = f_groups_layer;
        this.s_isdel = s_isdel;
        this.s_read_num = s_read_num;
        this.s_click_num = s_click_num;
        this.s_forward_num = s_forward_num;
        this.s_collect_num = s_collect_num;
        this.s_like_num = s_like_num;
        this.s_adv_num = s_adv_num;
        this.s_reward_num = s_reward_num;
        this.s_creator = s_creator;
        this.s_creater_time = s_creater_time;
        this.s_update_time = s_update_time;
        this.s_isPerm = s_isPerm;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.platId = platId;
        this.platName = platName;
        this.platImg = platImg;
        this.collectId = collectId;
        this.likeId = likeId;
        this.readId = readId;
        this.isTuiguang = isTuiguang;
        this.isLocalUpload = isLocalUpload;
        this.mid = mid;
        this.isFollow = isFollow;
        this.commentId = commentId;
        this.s_comment_num = s_comment_num;
    }

    @Generated(hash = 742516792)
    public Article() {
    }

    @Override
    public String toString() {
        return "Article{" +
                "p_id='" + p_id + '\'' +
                ", s_title='" + s_title + '\'' +
                ", s_author='" + s_author + '\'' +
                ", s_pic='" + s_pic + '\'' +
                ", s_category='" + s_category + '\'' +
                ", s_tag='" + s_tag + '\'' +
                ", s_abstract='" + s_abstract + '\'' +
                ", s_html='" + s_html + '\'' +
                ", s_url='" + s_url + '\'' +
                ", s_type='" + s_type + '\'' +
                ", f_groups_id='" + f_groups_id + '\'' +
                ", f_groups_layer='" + f_groups_layer + '\'' +
                ", s_isdel='" + s_isdel + '\'' +
                ", s_read_num=" + s_read_num +
                ", s_click_num=" + s_click_num +
                ", s_forward_num=" + s_forward_num +
                ", s_collect_num=" + s_collect_num +
                ", s_like_num=" + s_like_num +
                ", s_adv_num=" + s_adv_num +
                ", s_reward_num=" + s_reward_num +
                ", s_creator='" + s_creator + '\'' +
                ", s_creater_time='" + s_creater_time + '\'' +
                ", s_update_time='" + s_update_time + '\'' +
                ", s_isPerm=" + s_isPerm +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", platId='" + platId + '\'' +
                ", platName='" + platName + '\'' +
                ", platImg='" + platImg + '\'' +
                ", collectId='" + collectId + '\'' +
                ", likeId='" + likeId + '\'' +
                ", readId='" + readId + '\'' +
                ", isTuiguang='" + isTuiguang + '\'' +
                ", isLocalUpload=" + isLocalUpload +
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

    public String getS_title() {
        return this.s_title;
    }

    public void setS_title(String s_title) {
        this.s_title = s_title;
    }

    public String getS_author() {
        return this.s_author;
    }

    public void setS_author(String s_author) {
        this.s_author = s_author;
    }

    public String getS_pic() {
        return this.s_pic;
    }

    public void setS_pic(String s_pic) {
        this.s_pic = s_pic;
    }

    public String getS_category() {
        return this.s_category;
    }

    public void setS_category(String s_category) {
        this.s_category = s_category;
    }

    public String getS_tag() {
        return this.s_tag;
    }

    public void setS_tag(String s_tag) {
        this.s_tag = s_tag;
    }

    public String getS_abstract() {
        return this.s_abstract;
    }

    public void setS_abstract(String s_abstract) {
        this.s_abstract = s_abstract;
    }

    public String getS_html() {
        return this.s_html;
    }

    public void setS_html(String s_html) {
        this.s_html = s_html;
    }

    public String getS_url() {
        return this.s_url;
    }

    public void setS_url(String s_url) {
        this.s_url = s_url;
    }

    public String getS_type() {
        return this.s_type;
    }

    public void setS_type(String s_type) {
        this.s_type = s_type;
    }

    public String getF_groups_id() {
        return this.f_groups_id;
    }

    public void setF_groups_id(String f_groups_id) {
        this.f_groups_id = f_groups_id;
    }

    public String getF_groups_layer() {
        return this.f_groups_layer;
    }

    public void setF_groups_layer(String f_groups_layer) {
        this.f_groups_layer = f_groups_layer;
    }

    public String getS_isdel() {
        return this.s_isdel;
    }

    public void setS_isdel(String s_isdel) {
        this.s_isdel = s_isdel;
    }

    public Integer getS_read_num() {
        return this.s_read_num;
    }

    public void setS_read_num(Integer s_read_num) {
        this.s_read_num = s_read_num;
    }

    public Integer getS_click_num() {
        return this.s_click_num;
    }

    public void setS_click_num(Integer s_click_num) {
        this.s_click_num = s_click_num;
    }

    public Integer getS_forward_num() {
        return this.s_forward_num;
    }

    public void setS_forward_num(Integer s_forward_num) {
        this.s_forward_num = s_forward_num;
    }

    public Integer getS_collect_num() {
        return this.s_collect_num;
    }

    public void setS_collect_num(Integer s_collect_num) {
        this.s_collect_num = s_collect_num;
    }

    public Integer getS_like_num() {
        return this.s_like_num;
    }

    public void setS_like_num(Integer s_like_num) {
        this.s_like_num = s_like_num;
    }

    public Integer getS_adv_num() {
        return this.s_adv_num;
    }

    public void setS_adv_num(Integer s_adv_num) {
        this.s_adv_num = s_adv_num;
    }

    public Integer getS_reward_num() {
        return this.s_reward_num;
    }

    public void setS_reward_num(Integer s_reward_num) {
        this.s_reward_num = s_reward_num;
    }

    public String getS_creator() {
        return this.s_creator;
    }

    public void setS_creator(String s_creator) {
        this.s_creator = s_creator;
    }

    public String getS_creater_time() {
        return this.s_creater_time;
    }

    public void setS_creater_time(String s_creater_time) {
        this.s_creater_time = s_creater_time;
    }

    public String getS_update_time() {
        return this.s_update_time;
    }

    public void setS_update_time(String s_update_time) {
        this.s_update_time = s_update_time;
    }

    public Integer getS_isPerm() {
        return this.s_isPerm;
    }

    public void setS_isPerm(Integer s_isPerm) {
        this.s_isPerm = s_isPerm;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getPlatId() {
        return this.platId;
    }

    public void setPlatId(String platId) {
        this.platId = platId;
    }

    public String getPlatName() {
        return this.platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getPlatImg() {
        return this.platImg;
    }

    public void setPlatImg(String platImg) {
        this.platImg = platImg;
    }

    public String getCollectId() {
        return this.collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getLikeId() {
        return this.likeId;
    }

    public void setLikeId(String likeId) {
        this.likeId = likeId;
    }

    public String getReadId() {
        return this.readId;
    }

    public void setReadId(String readId) {
        this.readId = readId;
    }

    public String getIsTuiguang() {
        return this.isTuiguang;
    }

    public void setIsTuiguang(String isTuiguang) {
        this.isTuiguang = isTuiguang;
    }

    public Integer getIsLocalUpload() {
        return this.isLocalUpload;
    }

    public void setIsLocalUpload(Integer isLocalUpload) {
        this.isLocalUpload = isLocalUpload;
    }

    public String getMid() {
        return this.mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIsFollow() {
        return this.isFollow;
    }

    public void setIsFollow(String isFollow) {
        this.isFollow = isFollow;
    }

    public String getCommentId() {
        return this.commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public Integer getS_comment_num() {
        return this.s_comment_num;
    }

    public void setS_comment_num(Integer s_comment_num) {
        this.s_comment_num = s_comment_num;
    }
}
