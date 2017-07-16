package com.juziwl.commonlibrary.model;

import android.support.v4.util.ArrayMap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年03月14日
 * @description 城市列表封装类
 */
public class AreaMap implements Serializable {
    public ArrayMap<String, Integer> map = new ArrayMap<String, Integer>();
    public ArrayList<Object> datas = new ArrayList<Object>();
    public ArrayList<Area> hotCity = new ArrayList<Area>();
}
