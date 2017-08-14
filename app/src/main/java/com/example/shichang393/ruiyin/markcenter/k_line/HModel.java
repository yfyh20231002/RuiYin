package com.example.shichang393.ruiyin.markcenter.k_line;

import android.util.Log;

import com.github.mikephil.charting.data.CandleEntry;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by naive on 2017/4/17.
 */
public class HModel {
    public static List<CandleEntry> getCandleEntries(List<NewBean> rawData, int startIndex) {
        List<CandleEntry> entries = new ArrayList<>();


        for (int i = 0; i < rawData.size(); i++) {
            NewBean stock = rawData.get(i);
            if (stock == null) {
                Log.e("xxx", "第" + i + "StockBean==null");
                continue;
            }
            CandleEntry entry = new CandleEntry(startIndex + i, stock.getH(), stock.getL(), stock.getO(), stock.getC());
            entries.add(entry);
        }

        return entries;

    }

    //时间戳转字符串
    public static String getStrTime(String timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long time=Long.parseLong(timeStamp);
        timeString = sdf.format(new Date(time*1000l));//单位秒
        return timeString;
    }

    //获取系统时间
    public static String getSystemTime(long timeStamp){
        String timeString = null;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        timeString = sdf.format(timeStamp);//单位秒
        return timeString;
    }
    /**
     *
     * @param list
     * @param number
     * @return
     */
    public static final Double getEXPMA(final List<Double> list, final int number) {
        // 开始计算EMA值，
        Double k = 2.0 / (number + 1.0);// 计算出序数
        Double ema = list.get(0);// 第一天ema等于当天收盘价
        for (int i = 1; i < list.size(); i++) {
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = list.get(i) * k + ema * (1 - k);
        }
        return ema;
    }

    /**
     * calculate MACD values
     *
     * @param list
     *            :Price list to calculate，the first at head, the last at tail.
     * @param shortPeriod
     *            :the short period value.
     * @param longPeriod
     *            :the long period value.
     * @param midPeriod
     *            :the mid period value.
     * @return
     */
    public static final HashMap<String, Double> getMACD(final List<Double> list, final int shortPeriod, final int longPeriod, int midPeriod) {
        HashMap<String, Double> macdData = new HashMap<String, Double>();
        List<Double> diffList = new ArrayList<Double>();
        Double shortEMA = 0.0;
        Double longEMA = 0.0;
        Double dif = 0.0;
        Double dea = 0.0;

        for (int i = list.size() - 1; i >= 0; i--) {
            List<Double> sublist = list.subList(0, list.size() - i);
            shortEMA = getEXPMA(sublist, shortPeriod);
            longEMA = getEXPMA(sublist, longPeriod);
            dif = shortEMA - longEMA;
            diffList.add(dif);
        }
        dea = getEXPMA(diffList, midPeriod);
        macdData.put("DIF", dif);
        macdData.put("DEA", dea);
        macdData.put("MACD", (dif - dea) * 2);
        return macdData;
    }



    //将double保留两位小数
    public static  double getDouble(double d){
        BigDecimal b   =   new   BigDecimal(d);
        double   f  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
        return f;
    }


    //将float保留两位小数
    public static  float getFloat(float d){
        BigDecimal b   =   new   BigDecimal(d);
        float   f  =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        return f;
    }
}

