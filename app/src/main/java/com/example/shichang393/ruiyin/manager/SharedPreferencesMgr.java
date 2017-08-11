package com.example.shichang393.ruiyin.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.shichang393.ruiyin.utils.ConstanceValue;

/**
 * Created by Mr.zhang on 2017/7/3.
 */

public class SharedPreferencesMgr {
    private Context context;
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
     * @param value
     */
    public static void  setZhiboshiid(String value){
        if(sPrefs ==null)
            return ;
         sPrefs.edit().putString("zhiboshiid",value).commit();
    }
    public static String  getZhiboshiid(){
        if(sPrefs ==null)
            return ConstanceValue.DefaultLiveId;
      return   sPrefs.getString("zhiboshiid",ConstanceValue.DefaultLiveId);
    }

    /**
     * 保存用户ID
     * @param value
     */
    public static void  setuserid(String value){
        if(sPrefs ==null)
            return ;
        sPrefs.edit().putString("userId",value).commit();
    }
    public static String  getuserid(){
        if(sPrefs ==null)
            return ConstanceValue.DefaultUserId;
        return   sPrefs.getString("userId","");
    }


    /**
     * 保存用户头像
     *
     * @return
     */
    public static  void saveUserIcon(String userIcon) {
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
        return sPrefs.getString("USERICON","");
    }


    /**
     * 保存用户标注
     *
     * @return
     */
    public static void saveUserMark(int mark) {
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
    public static void saveUsername(String username) {
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
        return sPrefs.getString("USERNAME", "");
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
        return sPrefs.getInt("leixing", 0);
    }



    /**
     * 保存用户是否激活状态
     *
     * @return
     */
    public static void saveUserActivation(int activation) {
        if(sPrefs ==null)
            return ;
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putInt("ACTIVATION", activation);
        editor.commit();
    }

    /**
     * 获取用户是否激活状态
     *
     * @return
     */
    public static int getUserActivation() {
        return sPrefs.getInt("ACTIVATION",0);
    }


    /**
     * 在SharedPreferences中存储的登录的用户账号
     *
     * @param zhanghao
     */
    public static void saveZhanghao(String zhanghao) {
        if(sPrefs ==null)
            return ;
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString("zhanghao", zhanghao);
        editor.commit();
    }

    /**
     * 获取SharedPreferences中登陆的用户账号
     *
     * @return
     */
    public static String getZhanghao() {
        return sPrefs.getString("zhanghao", "");
    }

    /**
     * 删除全部
     */
    public static void clearAll() {
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.remove("userId");
        editor.remove("zhanghao");
        editor.remove("USERICON");
        editor.remove("USERNAME");
        editor.remove(ConstanceValue.TITLE_SELECTED);
        editor.remove(ConstanceValue.TITLE_UNSELECTED);
        editor.commit();
    }
    /**
     * 在SharedPreferences中存储的登录的用户密码
     *
     * @param mima
     */
    public static void saveMima(String mima) {
        if(sPrefs ==null)
            return ;
        SharedPreferences.Editor editor = sPrefs.edit();
        editor.putString("mima", mima);
        editor.commit();
    }

    /**
     * 获取SharedPreferences中登陆的用户密码
     *
     * @return
     */
    public static String getMima() {
        return sPrefs.getString("mima", "");
    }
}
