package com.juziwl.commonlibrary.manager;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v4.util.ArrayMap;

import com.juziwl.commonlibrary.db.DbHelper;
import com.juziwl.commonlibrary.model.Area;
import com.juziwl.commonlibrary.model.AreaMap;

import java.util.ArrayList;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016年03月14日
 * @description 城市列表管理类
 */
public class CityListManager {
    private static CityListManager instance = null;
    private DbHelper dbHelper = null;
    private final String tableName = "CITYLIST";
    private String id;
    private CityListManager(Context context) {
        if (dbHelper == null) {
            dbHelper = DbHelper.getInstance(context);
        }
    }

    public static CityListManager getInstance(Context context) {
        if (instance == null) {
            instance = new CityListManager(context);
        }
        return instance;
    }

    public void insertCityList(ArrayList<Area> list) {
        synchronized (dbHelper) {
            SQLiteDatabase db = null;
            try {
                db = dbHelper.open().getSqLiteDatabase();
                String sql = "insert into " + tableName + "(areaid,areaName,firstLetter,pinyin,hotcity,parentCityName,parentCityId)values(?,?,?,?,?,?,?)";
                SQLiteStatement stat = db.compileStatement(sql);
                db.beginTransaction();
                for (int i = 0; i < list.size(); i++) {
                    Area area = list.get(i);
                    stat.bindString(1, area.areaid);
                    stat.bindString(2, area.areaName);
                    stat.bindString(3, area.firstLetter);
                    stat.bindString(4, area.pinyin);
                    stat.bindLong(5, area.isHotCity);
                    stat.bindString(6, area.parentCityName);
                    stat.bindString(7, area.parentCityId);
                    stat.executeInsert();
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (db != null) {
                    db.endTransaction();
                    db.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
        }
    }

    public AreaMap getAllCityList(boolean isListViewHasHeaderView) {
        synchronized (dbHelper) {
            AreaMap areaMap = new AreaMap();
            ArrayMap<String, Integer> map = new ArrayMap<String, Integer>();
            ArrayList<Object> datas = new ArrayList<Object>();
            ArrayList<Area> hot = new ArrayList<Area>();
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "parentCityName = ?", new String[]{"juziwl"}, null, null, "pinyin");
                if (cursor != null) {
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        Area area = new Area();
                        area.areaid = cursor.getString(cursor.getColumnIndex("areaid"));
                        area.areaName = cursor.getString(cursor.getColumnIndex("areaName"));
                        area.firstLetter = cursor.getString(cursor.getColumnIndex("firstLetter"));
                        area.pinyin = cursor.getString(cursor.getColumnIndex("pinyin"));
                        area.isHotCity = cursor.getInt(cursor.getColumnIndex("hotcity"));
                        area.parentCityName = cursor.getString(cursor.getColumnIndex("parentCityName"));
                        area.parentCityId = cursor.getString(cursor.getColumnIndex("parentCityId"));
                        if (area.isHotCity == 0) {
                            if (!map.containsKey(area.firstLetter)) {
                                map.put(area.firstLetter, datas.size());
                                datas.add(area.firstLetter);
                            }
                            datas.add(area);
                        } else {
                            hot.add(area);
                        }
                    }
                    areaMap.datas = datas;
                    areaMap.map = map;
                    areaMap.hotCity = hot;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return areaMap;
        }
    }

    public boolean isEmpty() {
        synchronized (dbHelper) {
            Cursor cursor = null;
            boolean isEmpty = true;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, null, null, null, null, null);
                if (cursor != null) {
                    isEmpty = cursor.getCount() > 0 ? false : true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return isEmpty;
        }
    }

    public Area[] getAreaByCity(String cityName) {
        synchronized (dbHelper) {
            Area[] list = null;
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "parentCityName like ?", new String[]{"%" + cityName + "%"}, null, null, null);
                if (cursor != null) {
                    list = new Area[cursor.getCount()+1];
                    Area area0=new Area();
                    area0.areaid="";
                    area0.parentCityName="";
                    list[0]=area0;
                    int i = 1;
                    for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                        Area area = new Area();
                        if(i==1){
                            area0.areaName=cursor.getString(cursor.getColumnIndex("parentCityName"));
                        }
                        area.areaName = cursor.getString(cursor.getColumnIndex("areaName"));
                        area.areaid = cursor.getString(cursor.getColumnIndex("areaid"));
                        area.parentCityId = cursor.getString(cursor.getColumnIndex("parentCityId"));
                        area.parentCityName = cursor.getString(cursor.getColumnIndex("parentCityName"));
                        list[i] = area;
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return list;
        }
    }

    public String getCityId(String cityName) {
        synchronized (dbHelper) {
            String cityId = "";
            Cursor cursor = null;
            try {
                dbHelper.open();
                cursor = dbHelper.select(tableName, null, "parentCityName like ?", new String[]{"%" + cityName + "%"}, null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    cityId = cursor.getString(cursor.getColumnIndex("parentCityId"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (dbHelper != null) {
                    dbHelper.close();
                }
            }
            return cityId;
        }
    }

}
