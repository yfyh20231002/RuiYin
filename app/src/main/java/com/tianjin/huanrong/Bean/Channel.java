package com.tianjin.huanrong.Bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by Mr.zhang on 2017/7/3.
 */

public class Channel extends MultiItemEntity implements Serializable {
    public static final int TYPE_MY = 1;
    public static final int TYPE_OTHER = 2;
    public static final int TYPE_MY_CHANNEL = 3;
    public static final int TYPE_OTHER_CHANNEL = 4;
    public String Title;

    public Channel(String title) {
        Title = title;
    }

    public Channel(int type, String title) {
        Title = title;
        itemType = type;
    }
}
