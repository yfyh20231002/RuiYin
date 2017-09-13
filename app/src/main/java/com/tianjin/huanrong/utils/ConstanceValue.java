package com.tianjin.huanrong.utils;

/**
 * Created by Mr.zhang on 2017/6/22.
 */

public interface ConstanceValue {
    String DATA_SELECTED = "dataSelected";
    String DATA_UNSELECTED = "dataUnselected";
    String TITLE_SELECTED = "explore_title_selected";
    String TITLE_UNSELECTED = "explore_title_unselected";
    String SP_IS_FIRST_LOGIN = "isFirstLogin";
    //    FE7C1D82811A4E7DB4BA84D77E141F0A
//    DB9F8E38D4624A3997F2AAD34AEFCD0D
    String DefaultLiveId = "FE7C1D82811A4E7DB4BA84D77E141F0A";

    String DefaultUserId = "8AB54D148A4A4238B6E99792096791C8";
    String DefaultUserIcon = "templates/wdcs/image/icon/0002.jpg";
    String DefaultUserName = "liuzhoglin";
    int DefaultUserMark = 0;
    int CALL_PHONE = 1;
    int UPDATA_UI = 0;
    /**
     * 财经日历接口
     */
    String calendarurl = "http://www.ruiyin999.com/HtmlInterface/appEconomicCalendar1.html";
    /**
     * 财经快讯接口（市场播报和政经要闻）
     */
    String flashurl = "http://zb.006006.cn:10001/appsv/financialNewsView/getData.do";
    /**
     * 交易公告
     * http://appinterface.yun066.com/Home/GetNewList?id=49&pageindex=1&pagesize=1000
     * <p>
     * 重要消息
     * http://appinterface.yun066.com/Home/GetNewArrey?ids=29,32,33&pageindex=1&pagesize=3
     * <p>
     * <p>
     * 央视
     * http://appinterface.yun066.com/home/getVideoPage?videotype=%E9%B2%B8%E9%B1%BCAPP%E8%A7%86%E9%A2%91
     */
    String baseurl = "http://appinterface.yun066.com";

    /**
     * 轮播图地址（POST）
     * http://zb.006006.cn:10001/appsv/liveRoomListView/getLunBo.do
     * <p>
     * 直播室列表（post）
     * http://zb.006006.cn:10001/appsv/liveRoomListView/getNoVipLiveRooms.do
     * <p>
     * liveRoomListView/gotoLiveRoom.do
     * <p>
     * liveRoomView/filterChatHistory.do
     */
    String bannerurl = "http://zb.006006.cn:10001";

    /**
     * 重要消息详情页
     * http://admin.yun066.com/UploadFiles/Images/20170627083348_288.pdf
     */
    String iptmsgurl = "http://admin.yun066.com";
    /**
     * 开户链接
     */
    String kaihuurl = "https://openaccount.sxbrme.com:15588/Pc/Index/DC45B311726848A08F6DEABD82731F0C";
    /**
     * 融云客服功能获取token
     * http://api.cn.ronghub.com/user/getToken.json
     */
    String tokenBase = "http://api.cn.ronghub.com";

    String rongyun_appkey = "3argexb63d7te";
    String rongyun_appsecret = "xccKHRdGfN2LZ";


    /**
     * 操作建议中分析师头像的前缀地址
     * 更换头像的前缀
     */
    String baseImage = "http://zb.006006.cn";
    //    String testurl="http://192.168.1.22:8080/";
    String testurl = "http://zb.006006.cn:10001/appsv/";


}
