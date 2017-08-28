package com.unitesoft.huanrong.widget.fragment.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.utils.Utility;
import com.tencent.connect.share.QQShare;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.utils.TencentUtil;
import com.unitesoft.huanrong.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr.zhang on 2017/8/22.
 */

public class ShareDialog extends DialogFragment implements IWXAPIEventHandler, IWeiboHandler.Response,IUiListener {
    @InjectView(R.id.weixin)
    LinearLayout weixin;
    @InjectView(R.id.pengyouquan)
    LinearLayout pengyouquan;
    @InjectView(R.id.weibo)
    LinearLayout weibo;
    @InjectView(R.id.qq)
    LinearLayout qq;
    @InjectView(R.id.delete)
    ImageView delete;
    private Context mContext;
    private Activity activity;

    // 微信分享api
    private IWXAPI iwxapi;

    // 微博分享api
    private IWeiboShareAPI weiboShareAPI;
//qq分享
    private Tencent mTencent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width= ViewGroup.LayoutParams.MATCH_PARENT;
        attributes.height= ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(attributes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        弹窗位置
        getDialog().getWindow().setGravity(Gravity.CENTER);
//        设置背景色
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        getDialog().setCanceledOnTouchOutside(false);

        View view = inflater.inflate(R.layout.sharedialog, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 初始化微信api
        iwxapi = WXAPIFactory.createWXAPI(mContext, "wx9a491d58dda54389");
        iwxapi.registerApp("wx9a491d58dda54389");

        // 初始化微博api
        weiboShareAPI = WeiboShareSDK.createWeiboAPI(mContext, "392385374");
        weiboShareAPI.registerApp();

//        初始化qq
        mTencent = Tencent.createInstance("101424350", mContext);
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 分享的网址
     * http://appinterface.yun066.com/addpages/doujin.html
     *
     */
    @OnClick({R.id.weixin, R.id.pengyouquan, R.id.weibo, R.id.qq, R.id.delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.weixin:
                weChatShare(SendMessageToWX.Req.WXSceneSession);
                break;
            case R.id.pengyouquan:
                weChatShare(SendMessageToWX.Req.WXSceneTimeline);
                break;
            case R.id.weibo:
                if (!weiboShareAPI.isWeiboAppInstalled()) {
                    ToastUtils.showToast(mContext,"手机还没有安装微博!");
                    return;
                }
                WeiboMessage weiboMessage = new WeiboMessage();
                WebpageObject mediaObject = new WebpageObject();
                mediaObject.identify = Utility.generateGUID();
                mediaObject.title = "斗金直播-让每一分钟更有价值";
                mediaObject.description = "首旭财经秉承“诚信经营，规范服务”的宗旨，以遵守交易准则为重，以服务客户为本，注重互联网端的发展，联合业内资深金融研究所，打造了集官方网站＆PC直播＆手机APP于一体的首旭财经互联网服务平台，走出了一条风险低、见效快、回报稳、可持续的现代企业发展之路";

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.doujin);
                // 设置 Bitmap 类型的图片到视频对象里         设置缩略图。 注意：最终压缩过的缩略图大小不得超过 32kb。
                mediaObject.setThumbImage(bitmap);
//                mediaObject.actionUrl = "http://v.shouxu518.com/?time=" + System.currentTimeMillis();
                mediaObject.actionUrl = "http://appinterface.yun066.com/addpages/doujin.html";
                mediaObject.defaultText = "";
                weiboMessage.mediaObject = mediaObject;

                SendMessageToWeiboRequest request = new SendMessageToWeiboRequest();
                // 用transaction唯一标识一个请求
                request.transaction = String.valueOf(System.currentTimeMillis());
                request.message = weiboMessage;

                // 3. 发送请求消息到微博，唤起微博分享界面
                weiboShareAPI.sendRequest(activity, request);
                break;
            case R.id.qq:
                shareToQZone();
                break;
            case R.id.delete:
                dismiss();
                break;
        }
    }




    /**
     * 微信分享
     *
     * @param shareType
     */
    private void weChatShare(int shareType) {
        if (!iwxapi.isWXAppInstalled()) {
            ToastUtils.showToast(mContext,"手机还没有安装微信!");
            return;
        }
        try {
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl ="http://appinterface.yun066.com/addpages/doujin.html";
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = "斗金直播-让每一分钟更有价值";
            msg.description = "首旭财经秉承“诚信经营，规范服务”的宗旨，以遵守交易准则为重，以服务客户为本，注重互联网端的发展，联合业内资深金融研究所，打造了集官方网站＆PC直播＆手机APP于一体的首旭财经互联网服务平台，走出了一条风险低、见效快、回报稳、可持续的现代企业发展之路";
            Bitmap thumb = BitmapFactory.decodeResource(getResources(), R.mipmap.doujin);
            msg.thumbData = TencentUtil.bmpToByteArray(thumb, true);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = shareType;
            iwxapi.sendReq(req);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.showToast(mContext,"分享失败");
        }
    }
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }



    /**
     * QQ分享
     */
    private void shareToQZone() {
        Bundle params = new Bundle();
        params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_DEFAULT);
        params.putString(QQShare.SHARE_TO_QQ_TITLE, "斗金直播-让每一分钟更有价值");
        params.putString(QQShare.SHARE_TO_QQ_SUMMARY, "首旭财经秉承“诚信经营，规范服务”的宗旨，以遵守交易准则为重，以服务客户为本，注重互联网端的发展，联合业内资深金融研究所，打造了集官方网站＆PC直播＆手机APP于一体的首旭财经互联网服务平台，走出了一条风险低、见效快、回报稳、可持续的现代企业发展之路");
        params.putString(QQShare.SHARE_TO_QQ_TARGET_URL, "http://appinterface.yun066.com/addpages/doujin.html");
        params.putString(QQShare.SHARE_TO_QQ_IMAGE_URL, "http://imgsrc.baidu.com/forum/w%3D580/sign=8a4f16f2b4096b6381195e583c328733/168bd0edab64034ff8f913dba5c379310b551da3.jpg");
        params.putString(QQShare.SHARE_TO_QQ_APP_NAME, "斗金直播");
//        加上这句话只能分享到QQ空间
//        params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);
        mTencent.shareToQQ(activity, params, this);
    }



    @Override
    public void onResponse(BaseResponse baseResponse) {
        String result;
        switch (baseResponse.errCode) {
            case WBConstants.ErrorCode.ERR_OK:
                result = "分享成功";
                break;
            case WBConstants.ErrorCode.ERR_CANCEL:
                result = "分享返回";
                break;
            case WBConstants.ErrorCode.ERR_FAIL:
                result = "分享失败";
                break;
            default:
                result = "分享失败";
                break;
        }
        ToastUtils.showToast(mContext,result);
    }



    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        String result;
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "分享成功";
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "分享取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "分享被拒绝";
                break;
            default:
                result = "分享返回";
                break;
        }
        ToastUtils.showToast(mContext,result);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mTencent != null) {
            Tencent.onActivityResultData(requestCode, resultCode, data, this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onComplete(Object o) {
        ToastUtils.showToast(mContext,o.toString());

    }

    @Override
    public void onError(UiError uiError) {
        ToastUtils.showToast(mContext,uiError.errorMessage);
    }

    @Override
    public void onCancel() {
        ToastUtils.showToast(mContext,"取消");
    }
}
