package com.example.shichang393.ruiyin.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/6/22.
 */

public class CommonUtil {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(float dpValue) {

        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static Date LongToDate(long dateString, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String d = format.format(dateString);
        Date dateValue = null;
        try {
            dateValue = format.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateValue;
    }

    public static String LongToTime(long millSec) {
        if(millSec<1){
            return "00:00";
        }else {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date date = new Date(millSec);
            String format = sdf.format(date);
            return format;
        }
    }

    public static String timedate(long time) {
        Date d = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd HH:mm");
        return dateFormat.format(d);
    }

    public static String timedate(long time, String pattern) {
        Date d = new Date(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(d);

    }

    /**
     * 清楚html标签
     *
     * @param content
     * @return
     */
    public static String removeHtmlTag(String content) {
        Pattern pText = Pattern.compile("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>");
        Matcher mText = pText.matcher(content);
        if (mText.find()) {
            content = content.replaceAll("<([a-zA-Z]+)[^<>]*>(.*?)</\\1>", "$2");
            content = removeHtmlTag(content);
        }
        /**
         * 查找img标签
         */
        Pattern pImg = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");//<img[^<>]*src=[\'\"]([0-9A-Za-z.\\/]*)[\'\"].(.*?)>");
        Matcher mImg = pImg.matcher(content);
        if (mImg.find()) {
            content = mImg.group(1);
        }
        return content.replaceAll("(?:<br/>|&nbsp;|<br />)", "");
    }


    /*随机数，无长度限制*/
    public static String suijishu() {
        String a = null;
        Random random = new Random();
        a = String.valueOf(random.nextInt(10));
        return a;
    }

    /*时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数*/
    public static String shijian() {
        Long l = System.currentTimeMillis();
        String stf = String.valueOf(l);
        String times = stf.substring(0, 10);
        return times;
    }

    /*将交易所分配的 AppSecret、Nonce、Time三个字符串按先后顺序拼接成一个字符串并进行SHA1 哈希计算*/
    public static String sha1(String suijishu, String shijian) throws NoSuchAlgorithmException {
        String data = ConstanceValue.rongyun_appsecret + suijishu + shijian;
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0) a += 256;
            if (a < 16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }

    /**
     * 检查应用是否存在
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean checkApkExist(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    public static Retrofit retrofit(String baseUrl) {
        Retrofit retro = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retro;
    }


    /**
     * 获得一个UUID
     *
     * @return String FUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号，全转成大写
        return (s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24)).toUpperCase();
    }


    public static Boolean isImg(String content) {

        String last = content.substring(content.lastIndexOf(".") + 1);
        if (last.equals("jpg") || last.equals("gif")
                || last.equals("png") ||
                last.equals("jpeg") ||
                last.equals("bmp")) {
            return true;
        }
        return false;
    }
}
