package com.unitesoft.huanrong.widget.activity.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class ServiceSetActivity extends BaseActivity {
    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;
    @InjectView(R.id.set_on)
    ImageButton setOn;
    @InjectView(R.id.set_off)
    ImageButton setOff;
    @InjectView(R.id.checkbtn_set_on)
    ImageButton checkbtnSetOn;
    @InjectView(R.id.checkbtn_set_off)
    ImageButton checkbtnSetOff;
    @InjectView(R.id.clearchat)
    RelativeLayout clearchat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("设置");
        ButterKnife.inject(this);
        call.setVisibility(View.GONE);
        infomation.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_serviceset;
    }

    @OnClick({R.id.set_on, R.id.set_off, R.id.checkbtn_set_on, R.id.checkbtn_set_off,R.id.clearchat})
    public void onViewClicked(View view) {
        final String userid= SharedPreferencesMgr.getuserid();
        switch (view.getId()) {
            case R.id.set_on:
                setOn.setVisibility(View.GONE);
                setOff.setVisibility(View.VISIBLE);
                RongIM.getInstance().setConversationToTop(Conversation.ConversationType.CUSTOMER_SERVICE, userid, false, new RongIMClient.ResultCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
//                        ToastUtils.showToast(ServiceSetActivity.this,"置顶取消！");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case R.id.set_off:
                setOn.setVisibility(View.VISIBLE);
                setOff.setVisibility(View.GONE);
                RongIM.getInstance().setConversationToTop(Conversation.ConversationType.CUSTOMER_SERVICE, userid, true, new RongIMClient.ResultCallback<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
//                        ToastUtils.showToast(ServiceSetActivity.this,"置顶成功！");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case R.id.checkbtn_set_on:
                checkbtnSetOn.setVisibility(View.GONE);
                checkbtnSetOff.setVisibility(View.VISIBLE);
                RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.CUSTOMER_SERVICE, userid, Conversation.ConversationNotificationStatus.DO_NOT_DISTURB, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                    @Override
                    public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
//                        ToastUtils.showToast(ServiceSetActivity.this,"关闭通知！");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case R.id.checkbtn_set_off:
                checkbtnSetOn.setVisibility(View.VISIBLE);
                checkbtnSetOff.setVisibility(View.GONE);
                RongIM.getInstance().setConversationNotificationStatus(Conversation.ConversationType.CUSTOMER_SERVICE, userid, Conversation.ConversationNotificationStatus.NOTIFY, new RongIMClient.ResultCallback<Conversation.ConversationNotificationStatus>() {
                    @Override
                    public void onSuccess(Conversation.ConversationNotificationStatus conversationNotificationStatus) {
//                        ToastUtils.showToast(ServiceSetActivity.this,"开启通知！");
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode errorCode) {

                    }
                });
                break;
            case R.id.clearchat:
                ToastUtils.showToast(ServiceSetActivity.this,"清除完成！");
//                RongIM.getInstance().clearMessages(Conversation.ConversationType.CUSTOMER_SERVICE, userid, new RongIMClient.ResultCallback<Boolean>() {
//                    @Override
//                    public void onSuccess(Boolean aBoolean) {
//                        ToastUtils.showToast(ServiceSetActivity.this,"清除完成！");
//                        startActivity(new Intent(ServiceSetActivity.this,ServiceActivity.class));
//                    }
//
//                    @Override
//                    public void onError(RongIMClient.ErrorCode errorCode) {
//
//                    }
//                });
                break;
        }
    }

}
