package com.juziwl.commonlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @author ztn
 * @version V_5.0.0
 * @date 2016年2月21日
 * @description 存储用户信息
 */
public class UserPreference {

    private SharedPreferences settings;

    public UserPreference(Context ctx) {
        this.settings = ctx.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
    }

    public void storeAudio(String isin) {
        SharedPreferences.Editor edit = settings.edit();
        edit.putString("audio", isin);
        edit.commit();
    }

    public String getAudio() {
        return settings.getString("audio", "2");
    }


}
