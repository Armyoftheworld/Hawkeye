package com.juziwl.commonlibrary.model;

/**
 * @author Army
 * @version V_1.0.0
 * @date 2017/5/1
 * @description RxBus传递的数据和类型
 */
public class Event {

    public Event(String action) {
        this.action = action;
    }

    public String action = "";

    private Object t;

    public <T extends Object> T getObject() {
        return (T) t;
    }
}
