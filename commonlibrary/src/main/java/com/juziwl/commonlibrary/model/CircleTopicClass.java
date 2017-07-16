package com.juziwl.commonlibrary.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author 王晓清
 * @version V_1.0.0
 * @date 2017/6/13
 * @description 话题类型  需要缓存
 */
@Entity
public class CircleTopicClass {

    String topicId;
    String topicName;

    @Generated(hash = 878765164)
    public CircleTopicClass(String topicId, String topicName) {
        this.topicId = topicId;
        this.topicName = topicName;
    }

    @Generated(hash = 960236750)
    public CircleTopicClass() {
    }

    public String getTopicId() {
        return this.topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicName() {
        return this.topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }



}
