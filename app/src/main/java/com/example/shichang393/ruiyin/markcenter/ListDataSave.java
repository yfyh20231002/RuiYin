package com.example.shichang393.ruiyin.markcenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shichang393.ruiyin.markcenter.ziye.ZiXuanBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naive on 2017-7-24.
 */

public class ListDataSave {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;


    public ListDataSave(Context mContext, String preferenceName) {
        preferences = mContext.getSharedPreferences(preferenceName, mContext.MODE_PRIVATE);
        editor = preferences.edit();
    }

    /**
     * 保存List
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;

        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.clear();
        editor.putString(tag, strJson);
        editor.commit();

    }

    /**
     * 获取List
     * @param tag
     * @return
     */
    public <T> List<ZiXuanBean> getDataList(String tag) {
        List<ZiXuanBean> datalist=new ArrayList<>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return datalist;
        }
        Gson gson = new Gson();
        datalist = gson.fromJson(strJson, new TypeToken<List<ZiXuanBean>>() {
        }.getType());
        return datalist;

    }
}