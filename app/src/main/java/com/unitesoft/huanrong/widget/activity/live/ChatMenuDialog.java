package com.unitesoft.huanrong.widget.activity.live;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.event.OnCheckEvent;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;
import com.unitesoft.huanrong.utils.CommonUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Mr.zhang on 2017/8/17.
 */

public class ChatMenuDialog extends Activity {
    @InjectView(R.id.tvChatContent)
    TextView tvChatContent;
    @InjectView(R.id.btnShenHe)
    Button btnShenHe;
    @InjectView(R.id.btnHuiFu)
    Button btnHuiFu;
    @InjectView(R.id.btnShanChu)
    Button btnShanChu;
    @InjectView(R.id.btnCancel)
    Button btnCancel;
    @InjectView(R.id.exit_layout2)
    LinearLayout exitLayout2;

    private String userName;
    private String userId;
    private String usericon;
    private String chatContent;
    private String messageId;
    private int chatSticky;
    private String chatReply;

    private int position;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_liveroom_chat_menu);
        ButterKnife.inject(this);
        init();
    }

    private void init() {
        userName = getIntent().getStringExtra("userName");
        userId = getIntent().getStringExtra("userId");
        usericon=getIntent().getStringExtra("usericon");
        chatContent = CommonUtil.removeHtmlTag(getIntent().getStringExtra("chatContent"));
        messageId = CommonUtil.removeHtmlTag(getIntent().getStringExtra("msgId"));
        chatSticky = getIntent().getIntExtra("chatSticky", 0);
        chatReply = getIntent().getStringExtra("chatReply");
        position = getIntent().getIntExtra("position", 0);
        // 标题是当前信息的姓名+说话内容
        tvChatContent.setText(userName + ":" + chatContent);
        if (TextUtils.equals(userId, SharedPreferencesMgr.getuserid())) {
            // 分析师操作自己说的话 只能删除
            btnShenHe.setClickable(false);
            btnShenHe.setBackgroundResource(R.drawable.bg_yishenhe);
            btnHuiFu.setClickable(false);
            btnHuiFu.setBackgroundResource(R.drawable.bg_yishenhe);
        } else {
            int messageType = getIntent().getIntExtra("messageType", 1);
            if (3 == messageType || 2 == messageType) {
                // 私信或建议====不可以审核
                btnShenHe.setText("审 核");
                btnShenHe.setClickable(false);
                btnShenHe.setBackgroundResource(R.drawable.bg_yishenhe);
            } else {
                // 判断是否审核过
                btnShenHe.setText(chatSticky == 0 ? "审 核" : "已审核");
                btnShenHe.setClickable(chatSticky == 0 ? true : false);
                btnShenHe.setBackgroundResource(chatSticky == 0 ? R.drawable.exit_dialog_from_settings_btnselector : R.drawable.bg_yishenhe);
            }
            // 判断是否回复
            btnHuiFu.setText(TextUtils.equals(chatReply, "0") ? "回 复" : "已回复");
            btnHuiFu.setClickable(TextUtils.equals(chatReply, "0") ? true : false);
            btnHuiFu.setBackgroundResource(TextUtils.equals(chatReply, "0") ? R.drawable.exit_dialog_from_settings_btnselector : R.drawable.bg_yishenhe);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && isOutOfBounds(event)) {
            finish();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private boolean isOutOfBounds(MotionEvent event) {
        final int y = (int) event.getY();
        int layoutHeight = exitLayout2.getLayoutParams().height;
        int appHeight = CommonUtil.getAppHeight(this);
        int outHeight = appHeight - layoutHeight;
        if (y < outHeight) {
            return true;
        }
        return false;
    }

    @OnClick({R.id.btnShenHe, R.id.btnHuiFu, R.id.btnShanChu, R.id.btnCancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnShenHe:
                EventBus.getDefault().post(new OnCheckEvent(0, position, messageId, userName, userId,usericon,chatContent));
                finish();
                break;
            case R.id.btnHuiFu:
                EventBus.getDefault().post(new OnCheckEvent(1, position, messageId, userName, userId,usericon,chatContent));
                finish();
                break;
            case R.id.btnShanChu:
                EventBus.getDefault().post(new OnCheckEvent(2, position, messageId, userName, userId,usericon,chatContent));
                finish();
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }
}
