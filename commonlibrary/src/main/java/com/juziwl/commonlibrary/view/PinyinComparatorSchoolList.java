package com.juziwl.commonlibrary.view;

import android.text.TextUtils;

import com.juziwl.commonlibrary.model.OnlineSchool;

import java.io.Serializable;
import java.util.Comparator;

public class PinyinComparatorSchoolList implements Comparator<OnlineSchool>, Serializable {

    @Override
    public int compare(OnlineSchool onlineSchool1, OnlineSchool onlineSchool2) {
        try {
            //把所有非字母开头的拼音都放到最后，{比z大
            String p1 = "", p2 = "";
            if (!TextUtils.isEmpty(onlineSchool1.pinyin)) {
                if (onlineSchool1.pinyin.substring(0, 1).matches("[A-Z]|[a-z]")) {
                    p1 = onlineSchool1.pinyin;
                } else {
                    p1 = "{" + onlineSchool1.pinyin;
                }
            }
            if (!TextUtils.isEmpty(onlineSchool2.pinyin)) {
                if (onlineSchool2.pinyin.substring(0, 1).matches("[A-Z]|[a-z]")) {
                    p2 = onlineSchool2.pinyin;
                } else {
                    p2 = "{" + onlineSchool2.pinyin;
                }
            }
            return p1.compareTo(p2);
        } catch (Exception e) {
        }
        return 0;
    }
}
