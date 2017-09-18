package com.tianjin.huanrong.widget.fragment.live;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Selection;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.tianjin.huanrong.ApiService.LiveService;
import com.tianjin.huanrong.Bean.ChatPBean;
import com.tianjin.huanrong.Bean.ReceiveBean;
import com.tianjin.huanrong.Bean.live.InsertCaoZuoPostBean;
import com.tianjin.huanrong.R;
import com.tianjin.huanrong.event.OnAdviseEvent;
import com.tianjin.huanrong.event.OnCheckEvent;
import com.tianjin.huanrong.listener.OnChatRefreshListener;
import com.tianjin.huanrong.listener.OnLiveRoomChatTalkListener;
import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.presenter.InteractionPresenter;
import com.tianjin.huanrong.utils.CommonUtil;
import com.tianjin.huanrong.utils.ConstanceValue;
import com.tianjin.huanrong.utils.ToastUtils;
import com.tianjin.huanrong.view.InteractionView;
import com.tianjin.huanrong.widget.activity.MainActivity;
import com.tianjin.huanrong.widget.activity.live.ChatMenuDialog;
import com.tianjin.huanrong.widget.adapter.live.InteractionAdapter;
import com.tianjin.huanrong.widget.fragment.dialog.HintDialog;
import com.tianjin.huanrong.widget.fragment.dialog.LoadDialog;
import com.tianjin.huanrong.widget.view.ChatListView;
import com.tianjin.huanrong.widget.view.sweetdialog.SweetAlertDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * 互动
 */
public class InteractionFragment extends BaseFragment implements InteractionView, OnChatRefreshListener, OnLiveRoomChatTalkListener, OnLayoutChangeListener, AdapterView.OnItemLongClickListener {


    @InjectView(R.id.listview)
    ChatListView listview;
    @InjectView(R.id.content)
    EditText content;
    @InjectView(R.id.send)
    TextView send;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    private int listViewHeight = 0;

    // 直播室id
    String liveid;
    // 用户id
    String userid;
    // 用户头像
    String usericon;
    // 账号类型
    private String accountType;

    // debug tag
    private String TAG = getClass().getSimpleName();
    // 客户端实例
    private WebSocketClient webSocketClient;
    // 状态监听
//    private ChatListener chatListener;
    private InteractionPresenter presenter;
    private InteractionAdapter adapter;
    private List<ChatPBean.DataBean.ChatBean> list = new ArrayList<>();
    View view;
    int page = 1;
    int count = 10;
    SweetAlertDialog sweetAlertDialog; // 弹窗
    Activity activity;
    private SpannableStringBuilder replyBuilder;  // 回复字体样式组
    private String otherId = ""; // 他人id
    private String otherName = ""; // 他人姓名
    private int leibie = 0;
    String tyonghutouxiang = "";
    String relation = "";
    //    用户类型
    int leixing;
    //    建仓品种，建仓价格，建仓方向，目标点位，止损点位，加仓价格
    private String pinzhong;
    private String price;
    private String direction;
    private String mubiao;
    private String zhisun;
    private String jiacang = "";
    private String baodan;


    private Context mContext;
    private LoadDialog loadDialog;
    private HintDialog hintDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
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
            liveid = SharedPreferencesMgr.getZhiboshiid();
            userid = SharedPreferencesMgr.getuserid();
            usericon = SharedPreferencesMgr.getUserIcon();
            leixing = SharedPreferencesMgr.getZhanghaoleixing();
            accountType = String.valueOf(leixing);
            startConnectSv(userid, liveid, accountType);
            loadDialog=new LoadDialog();
            loadDialog.show(getChildFragmentManager(),"");
            presenter = new InteractionPresenter(this);
            presenter.postChatData(userid, liveid, leixing, leibie, page, count);
            dismissDialog();
            isPrepared = false;
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
        EventBus.getDefault().unregister(this);
        ButterKnife.reset(this);
    }

    @Override
    public String getUserId() {
        return SharedPreferencesMgr.getuserid();
    }

    @Override
    public void onChatSuccess(List<ChatPBean.DataBean.ChatBean> chat) {
        loadDialog.dismiss();
        list.clear();
        list.addAll(chat);
        if (adapter == null) {
            adapter = new InteractionAdapter(list, activity, this);
            listview.setAdapter(adapter);
            listViewHeight = listview.getHeight();
            listview.setSelection(listview.getBottom());
            listview.setOnRefreshListener(this);
            listview.addOnLayoutChangeListener(this);
            listview.setOnItemLongClickListener(this);
        } else {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onSendSuccess() {
        loadDialog.dismiss();
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        LiveService liveService = retrofit.create(LiveService.class);
        String messageid = SharedPreferencesMgr.getMessageid();
        Call<ResponseBody> responseBodyCall = liveService.postInsertCaozuo(new InsertCaoZuoPostBean(pinzhong, price, direction, mubiao, zhisun, userid, baodan, messageid, "", usericon));
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }


    @Override
    public void onChatFailed(String msg) {
        loadDialog.dismiss();
        if (list.size() == 0) {
            ToastUtils.showToast(activity, msg);
        }
    }

    @Override
    public void tanchuang() {
        hintDialog=new HintDialog();
        hintDialog.setIsSingleButton(true);
        hintDialog.setOnSingleClickListener(new HintDialog.HintSingleCallback() {
            @Override
            public void onClick() {
                activity.finish();
            }
        });
        hintDialog.setContent("直播室升级中，精彩敬请期待");
        hintDialog.show(activity.getFragmentManager(),"");
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
        presenter.postSendMessage(userid, liveid, userIcon, userName, userMark, 1, leibie, otherId, otherName, chatContent, tyonghutouxiang, relation);
        leibie = 0;
        tyonghutouxiang = "";
        relation = "";
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
        if (sweetAlertDialog != null && sweetAlertDialog.isShowing()) {
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
            ToastUtils.showToast(activity, msg);
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
                    SharedPreferencesMgr.clearAll();
                    activity.startActivity(new Intent(mContext, MainActivity.class));
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
        count += 10;
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

    /**
     * 回复
     * this method will change inputHint like : 对 xxx 说
     *
     * @param userName 用户姓名
     * @param userId   用户Id
     */
    @Override
    public void onClickTalkTo(String msgId, String userName, String userId, String userIcon, String shuohuaneirong, int action) {
// 设置输入框提示内容
        // 回复的样式
        if (null == replyBuilder) {
            replyBuilder = new SpannableStringBuilder();
        } else {
            replyBuilder.clear();
        }
        String reply = "对 ";

        SpannableString replySpannable = new SpannableString(userName);
        int replyLen = userName.length();
        ForegroundColorSpan autoChatColSpan = new ForegroundColorSpan(Color.parseColor("#FF8000"));
        replySpannable.setSpan(autoChatColSpan, 0, replyLen, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        replyBuilder.append(reply).append(replySpannable).append(" 说");
        content.setHint(replyBuilder);
        tyonghutouxiang = userIcon;
        relation = shuohuaneirong;
        alertKeyBoard(userName, userId);
    }

    /**
     * 弹出键盘
     */
    private void alertKeyBoard(String userName, String userId) {
        leibie = 1;
        otherId = userId;
        otherName = userName;
        // 设置焦点
        Selection.setSelection(content.getText(), content.getText().length());
        // 弹出键盘
        content.setFocusable(true);
        content.setFocusableInTouchMode(true);
        content.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) content.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(content, 0);
    }

    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

        if (listViewHeight == (bottom - top)) {
            // 键盘收起时
            leibie = 0;
            content.setHint("");
        } else if (listViewHeight > (bottom - top)) {
            listview.scrollToBottomAlways();
        }

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        position = position - 1;
        // 自己是普通用户或者对方是分析师 不弹出菜单
        if (isGeneral(SharedPreferencesMgr.getZhanghaoleixing())
                || (!isGeneral(list.get(position).getFzhanghaoleixing()) && !TextUtils.equals(list.get(position).getFyonghuid(), SharedPreferencesMgr.getuserid()))) {
            return false;
        }
        String messageid = list.get(position).getMessageid();
        Intent intent = new Intent(mContext, ChatMenuDialog.class);
        intent.putExtra("messageType", list.get(position).getXiaoxileibie());
        intent.putExtra("userName", list.get(position).getFyonghunicheng());
        intent.putExtra("userId", list.get(position).getFyonghuid());
        intent.putExtra("usericon", list.get(position).getFyonghutouxiang());
        intent.putExtra("chatContent", list.get(position).getShuohuaneirong());
        intent.putExtra("chatSticky", list.get(position).getYishenhe());
        intent.putExtra("chatReply", TextUtils.isEmpty(list.get(position).getYihuifu()) ? "0" : list.get(position).getYihuifu());
        intent.putExtra("msgId", messageid);
        intent.putExtra("position", position);
        startActivity(intent);
        return true;
    }

    /**
     * 是否是普通用户
     *
     * @param permission 用户权限
     * @return
     */
    private boolean isGeneral(int permission) {
        if (2 == permission || 3 == permission || 4 == permission || 5 == permission || 6 == permission) {
            return false;
        }
        return true;
    }

    @Subscribe
    public void onMessageEvent(OnAdviseEvent onAdviseEvent) {
        pinzhong = onAdviseEvent.getTypes();
        price = onAdviseEvent.getJianCang();
        direction = onAdviseEvent.getRound();
        mubiao = onAdviseEvent.getMuBiao();
        zhisun = onAdviseEvent.getZhiSun();
        jiacang = onAdviseEvent.getJianCangUpdate();
        baodan = onAdviseEvent.getBaodan();
        StringBuffer stringBuffer = new StringBuffer();
        if (1 == onAdviseEvent.getSendType()) {
            stringBuffer.append(onAdviseEvent.getTypes());
            stringBuffer.append("操作建议：");
            stringBuffer.append("可于");
            stringBuffer.append(onAdviseEvent.getJianCang());
            stringBuffer.append("价格附近");
            stringBuffer.append(onAdviseEvent.getRound());
            stringBuffer.append("，盈利目标点为");
            stringBuffer.append(onAdviseEvent.getMuBiao());
            stringBuffer.append("，建议将止损价格设置为");
            stringBuffer.append(onAdviseEvent.getZhiSun());
            stringBuffer.append("。本操作建议为分析师个人意见，仅供参考。");
            leibie = 2;
        } else {
            stringBuffer.append(onAdviseEvent.getTypes());
            stringBuffer.append("跟踪建议：");
            stringBuffer.append("可于");
            stringBuffer.append(onAdviseEvent.getJianCang());
            stringBuffer.append("价格附近");
            stringBuffer.append(onAdviseEvent.getRound());
            stringBuffer.append("，加仓价格为");
            stringBuffer.append(onAdviseEvent.getJianCangUpdate());
            stringBuffer.append("，盈利目标点为");
            stringBuffer.append(onAdviseEvent.getMuBiao());
            stringBuffer.append("，建议将止损价格设置为");
            stringBuffer.append(onAdviseEvent.getZhiSun());
            stringBuffer.append("。本操作建议为分析师个人意见，仅供参考。");
            leibie = 4;
        }
        sendDefaultMessage(stringBuffer.toString());
    }


    @Subscribe
    public void onEventBackgroundThread(OnCheckEvent onCheckEvent) {
        // 审核
        if (onCheckEvent.getClick() == 0) {
            presenter.saveCheck(onCheckEvent.getMessageId());
        }
        // 回复
        else if (onCheckEvent.getClick() == 1) {
            onClickTalkTo(onCheckEvent.getMessageId(), onCheckEvent.getUserName(), onCheckEvent.getUserId(), onCheckEvent.getUserIcon(), onCheckEvent.getChatContent(), 2);
        }
        // 删除
        else if (onCheckEvent.getClick() == 2) {
            ChatPBean.DataBean.ChatBean chatBean = list.get(onCheckEvent.getPosition());
            presenter.deleteMessage(chatBean);
            presenter.postChatData(userid, liveid, leixing, leibie, page, count);
            adapter.notifyDataSetChanged();
        }
    }



}
