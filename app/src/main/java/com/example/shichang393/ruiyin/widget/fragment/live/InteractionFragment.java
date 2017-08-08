package com.example.shichang393.ruiyin.widget.fragment.live;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shichang393.ruiyin.Bean.ChatPBean;
import com.example.shichang393.ruiyin.Bean.ReceiveBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.listener.OnChatRefreshListener;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.presenter.InteractionPresenter;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.InteractionView;
import com.example.shichang393.ruiyin.widget.adapter.live.InteractionAdapter;
import com.example.shichang393.ruiyin.widget.view.ChatListView;
import com.example.shichang393.ruiyin.widget.view.sweetdialog.SweetAlertDialog;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 互动
 */
public class InteractionFragment extends BaseFragment implements InteractionView ,OnChatRefreshListener{


    @InjectView(R.id.listview)
    ChatListView listview;
    InteractionPresenter presenter;
    InteractionAdapter adapter;
    @InjectView(R.id.content)
    EditText content;
    @InjectView(R.id.send)
    Button send;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    // 直播室id
    String liveid;
    // 用户id
    String userid;
    // 账号类型
    private String accountType;

    // debug tag
    private String TAG = getClass().getSimpleName();
    // 客户端实例
    private WebSocketClient webSocketClient;
    // 状态监听
//    private ChatListener chatListener;
    List<ChatPBean.DataBean.ChatBean> list = new ArrayList<>();
    View view;
    int  page=1;
    int count=10;
    SweetAlertDialog sweetAlertDialog; // 弹窗
    Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_interaction, container, false);
            isPrepared = true;
            ButterKnife.inject(this, view);
            lazyLoad();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        return view;
    }

    @Override
    protected void lazyLoad() {
        if (isPrepared && isVisible) {
            // 默认不弹出软键盘
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            // 设置键盘按键监听
            content.setOnEditorActionListener(editorActionListener);
            initDialog();
            liveid = SharedPreferencesMgr.getZhiboshiid("zhiboshiid", ConstanceValue.DefaultLiveId);
            userid = SharedPreferencesMgr.getuserid("userid", ConstanceValue.DefaultUserId);
            int leixing = SharedPreferencesMgr.getZhanghaoleixing();
            accountType = String.valueOf(leixing);
            startConnectSv(userid, liveid, accountType);
            presenter = new InteractionPresenter(this);
            presenter.postChatData(userid, liveid, 1, 0, page, count);
            dismissDialog();
            isPrepared=false;
        }
    }

    private void initDialog() {
        sweetAlertDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        sweetAlertDialog.setTitleText("加载中...");
        sweetAlertDialog.showContentText(false);
        sweetAlertDialog.showCancelButton(false);
        sweetAlertDialog.show();
    }

    /**
     * 监听键盘确认键
     */
    private EditText.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_DONE) {
                // 发送消息
                onViewClicked();
            }
            return true;
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onChatSuccess(List<ChatPBean.DataBean.ChatBean> chat) {
            list.clear();
            list.addAll(chat);
            if (adapter == null) {
                adapter = new InteractionAdapter(list, activity);
                listview.setAdapter(adapter);
                listview.setSelection(listview.getBottom());
                listview.setOnRefreshListener(this);
            } else {
                adapter.notifyDataSetChanged();
            }

    }


    @Override
    public void onChatFailed(String msg) {
        if (list.size()==0){
            ToastUtils.showToast(activity,msg);
        }
    }

    /**
     * 发送按钮
     */
    @OnClick(R.id.send)
    public void onViewClicked() {
        // 不可以发送空消息
        if (TextUtils.isEmpty(content.getText().toString())) {
            ToastUtils.showToast(activity, "不能发送空白消息");
            return;
        }
        // 保存发送内容并置输入框为空
        String chatContent = content.getText().toString();
        content.setText("");
        sendDefaultMessage(chatContent);
        content.setHint("");
    }

    private void sendDefaultMessage(String chatContent) {
        String userIcon = SharedPreferencesMgr.getUserIcon();
        String userName = SharedPreferencesMgr.getUsername();
        int userMark = SharedPreferencesMgr.getUserMark();
        presenter.postSendMessage(userid, liveid, userIcon, userName, userMark, 1, 0, chatContent);
    }


    final void startConnectSv(String yonghuid, String liveId, String zhanghaoleixing) {

        // 校验参数
        if (TextUtils.isEmpty(yonghuid) || TextUtils.isEmpty(liveId) || TextUtils.isEmpty(zhanghaoleixing))
//            startActivity(new Intent(WebSocketActivity.this, LoginDefaultDialog_.class));

            //        保存连接参数
            this.userid = yonghuid;
        this.liveid = liveId;
        this.accountType = zhanghaoleixing;
        // 拼接地址
        String url = "ws://zb.006006.cn/websocket.do?yonghuid=" + yonghuid + "&tozhiboshiid=" + liveId + "&zhanghaoleixing=" + zhanghaoleixing;
        // 开始连接
        try {
            webSocketClient = new WebSocketClient(new URI(url), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.e(TAG, "websocket connect is open.");
                }

                @Override
                public void onMessage(String s) {
                    Log.e(TAG, "receive message:" + s);
                    Gson gson = new Gson();
                    ReceiveBean receiveBean = gson.fromJson(s, ReceiveBean.class);
                    if (TextUtils.equals("message", receiveBean.getType())) {
                        ChatPBean.DataBean.ChatBean chatBean = gson.fromJson(s, ChatPBean.DataBean.ChatBean.class);
                        list.add(chatBean);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.notifyDataSetChanged();
                                listview.setSelection(listview.getBottom());
                            }
                        });
                    } else if (TextUtils.equals("action", receiveBean.getType())) {
                        // 提取消息索引并做响应处理
                        if (TextUtils.equals("kickout", receiveBean.getAction())) {
                            // 踢人动作
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showGetOut();
                                }
                            });

                        } else {
                        }
                    }
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    Log.e(TAG, "websocket connect is close:" + s);
                }

                @Override
                public void onError(Exception e) {
                    Log.e(TAG, "websocket onError:" + e);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showErrorDialog("连接聊天室失败");
                        }
                    });
                }
            };

            webSocketClient.connect();

        } catch (URISyntaxException e) {
            e.printStackTrace();
            Log.e(TAG, "websocket connect error.");
        }
    }
    void showErrorDialog(String msg) {
        if (sweetAlertDialog != null&& sweetAlertDialog.isShowing()) {
            sweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
            sweetAlertDialog.setTitleText("进入直播室失败");
            sweetAlertDialog.setContentText(msg);
            sweetAlertDialog.showCancelButton(false);
            sweetAlertDialog.showConfirmButton(true);
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    activity.finish();
                }
            });
        } else {
            ToastUtils.showToast(activity,msg);
        }
    }
    private void showGetOut() {
        if (!activity.isFinishing()) {
            sweetAlertDialog.changeAlertType(SweetAlertDialog.WARNING_TYPE);
            sweetAlertDialog.setConfirmText("暂时离开");
            sweetAlertDialog.setTitleText("多设备使用提示");
            sweetAlertDialog.setContentText("有另一个设备使用您的账号登录了此直播室,如非本人操作请尽快修改密码.");
            sweetAlertDialog.showCancelButton(false);
            sweetAlertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    activity.finish();
                }
            });
            if (!sweetAlertDialog.isShowing()) {
                sweetAlertDialog.show();
            }
        }
    }

    @Override
    public void loadMoreChat() {
        listview.showProgress();
        count+=10;
        presenter.postChatData(userid, liveid, 1, 0, page, count);
        listview.refreshComplete();
        adapter.notifyDataSetChanged();
    }

    void dismissDialog() {
        if (null != sweetAlertDialog && sweetAlertDialog.isShowing()) {
            sweetAlertDialog.dismiss();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (null != webSocketClient) {
            webSocketClient.close();
            webSocketClient = null;
        }
    }
}
