package com.tianjin.huanrong.markcenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.tianjin.huanrong.R;

/**
 * Created by Mr.zhang on 2017/9/7.
 */

public class DataChangeTextView extends TextView {
    private int upColor; // 涨时的字体颜色
    private int downColor; // 跌时的字体颜色
    private int constantColor; // 没有变化时的字体颜色
    private int normalColor; // 初始字体颜色

    private Drawable drawableUp; // 涨时的背景
    private Drawable drawableDown; // 跌时的背景
    private Drawable drawableNormal; // 正常时背景

    private int duration; // 持续时间

    private Handler handler;


    public DataChangeTextView(Context context) {
        super(context);

    }

    public DataChangeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initResources(context, attrs);
    }

    public DataChangeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initResources(context, attrs);
    }


    private void initResources(Context context, AttributeSet attrs) {

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.bcqCtv);
        upColor = a.getColor(R.styleable.bcqCtv_upTextColor, 0);
        downColor = a.getColor(R.styleable.bcqCtv_downTextColor, 0);
        constantColor = a.getColor(R.styleable.bcqCtv_constantTextColor, 0);
        normalColor = a.getColor(R.styleable.bcqCtv_normalTextColor, 0);
        drawableUp = a.getDrawable(R.styleable.bcqCtv_upBackgroundDrawable);
        drawableDown = a.getDrawable(R.styleable.bcqCtv_downBackgroundDrawable);
        drawableNormal = a.getDrawable(R.styleable.bcqCtv_normalBackgroundDrawable);
        duration = a.getInteger(R.styleable.bcqCtv_duration, 1000);

        handler= new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 1:
                        setBackgroundDrawable(drawableNormal);
                        setTextColor(upColor);
                        break;
                    case 2:
                        setBackgroundDrawable(drawableNormal);
                        setTextColor(downColor);
                        break;

                }
            }
        };
    }



    /**
     * 正常字体颜色
     *
     * @param upOrDown
     * @param text
     */
    public void setNormalText(int upOrDown, CharSequence text) {
        // 值不一样时改变
        if (!TextUtils.equals(text, getText())) {
            switch (upOrDown) {
                case 0:
                    setText(text);
                    setTextColor(constantColor);
                    break;
                case 1:
                    setText(text);
                    setTextColor(upColor);
                    break;
                case -1:
                    setText(text);
                    setTextColor(downColor);
                    break;
            }
        }
    }

    /**
     * 背景跳动
     *
     * @param upOrDown
     * @param text
     */
    public void setBeatText(int upOrDown, CharSequence text) {
        // 值不一样时改变
        if (!TextUtils.equals(text, getText())) {
            switch (upOrDown) {
                case 0:
                    setText(text);
                    setTextColor(constantColor);
                    setBackgroundDrawable(drawableNormal);
                    break;
                case 1:
                    setText(text);
                    setTextColor(normalColor);
                    setBackgroundDrawable(drawableUp);
                    Message message1 = new Message();
                    message1.what = 1;
                    handler.sendMessageDelayed(message1, duration);
                    break;
                case -1:
                    setText(text);
                    setTextColor(normalColor);
                    setBackgroundDrawable(drawableDown);
                    Message message2 = new Message();
                    message2.what = 2;
                    handler.sendMessageDelayed(message2, duration);
                    break;
            }
        }
    }
}
