package com.example.shichang393.ruiyin.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shichang393.ruiyin.utils.ConstanceValue;

/**
 * Created by Mr.zhang on 2017/7/3.
 */

public class SharedPreferencesMgr {
    private static Context context;
    private static SharedPreferences sPrefs ;

    private SharedPreferencesMgr(Context context, String fileName)
    {
        this.context=context;
        sPrefs= context.getSharedPreferences(
                fileName, Context.MODE_PRIVATE );
    }
    public static void init(Context context,String fileName)
    {
        new SharedPreferencesMgr(context,fileName);
    }
    public static String getString(String key,String defaultValue)
    {
        if(sPrefs ==null)
            return defaultValue;
        return sPrefs.getString(key, defaultValue);
    }

    public static void setString(String key,String value) {
        if(sPrefs ==null)
            return ;
        sPrefs.edit().putString(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return sPrefs.getBoolean(key, defValue);
    }
    public static boolean setBoolean(String key, boolean defValue) {
        return sPrefs.edit().putBoolean(key, defValue).commit();
    }

    /**
     * 保存直播室id
     * @param key
     * @param value
     */
    public static void  setZhiboshiid(String key,String value){
        if(sPrefs ==null)
            return ;
         sPrefs.edit().putString(key,value).commit();
    }
    public static String  getZhiboshiid(String key,String defaultvalue){
        if(sPrefs ==null)
            return defaultvalue;
      return   sPrefs.getString(key,defaultvalue);
    }

    /**
     * 保存用户ID
     * @param key
     * @param value
     */
    public static void  setuserid(String key,String value){
        if(sPrefs ==null)
            return ;
        sPrefs.edit().putString(key,value).commit();
    }
    public static String  getuserid(String key,String defaultvalue){
        if(sPrefs ==null)
            return defaultvalue;
        return   sPrefs.getString(key,defaultvalue);
    }


    /**
     * 保存用户头像
     *
     * @return
     */
    public void saveUserIcon(String userIcon) {
        if(sPrefs ==null)
            return ;
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString("USERICON", userIcon);
        editor.commit();
    }

    /**
     * 获取用户头像
     *
     * @return
     */
    public static String getUserIcon() {
        return sPrefs.getString("USERICON", ConstanceValue.DefaultUserIcon);
    }


    /**
     * 保存用户标注
     *
     * @return
     */
    public void saveUserMark(int mark) {
        if(sPrefs ==null)
            return ;
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putInt("USERMARK", mark);
        editor.commit();
    }

    /**
     * 获取用户标注
     *
     * @return
     */
    public static int getUserMark() {
        return sPrefs.getInt("USERMARK", ConstanceValue.DefaultUserMark);
    }

    /**
     * 在SharedPreferences中存储的登录的用户名称
     *
     * @param username
     */
    public void saveUsername(String username) {
        if(sPrefs ==null)
            return ;
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString("USERNAME", username);
        editor.commit();
    }

    /**
     * 获取SharedPreferences中登陆的用户名称
     *
     * @return
     */
    public static String getUsername() {
        return sPrefs.getString("USERNAME", ConstanceValue.DefaultUserName);
    }

    /**
     * 设置用户的权限
     * @param zhanghaoleixing
     */
    public static  void setZhanghaoleixing(int zhanghaoleixing){
        if(sPrefs ==null)
            return ;
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putInt("leixing", zhanghaoleixing);
        editor.commit();
    }
    /**
     * 获取用户的权限
     *
     */
    public static int getZhanghaoleixing(){
        return sPrefs.getInt("leixing", 1);
    }
}
