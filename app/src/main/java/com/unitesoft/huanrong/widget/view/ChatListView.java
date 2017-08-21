package com.unitesoft.huanrong.widget.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.listener.OnChatRefreshListener;


/**
 * 概要：聊天室listView
 * 版本：V1.0
 */

public class ChatListView extends ListView {

    private Context context;
    private TextView tvRemind;
    private ProgressBar pbLoading;
    private View headerView;

    private int state;

    private OnChatRefreshListener onChatRefreshListener;


    private boolean refreshing = false;

    public ChatListView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ChatListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ChatListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ChatListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
        init();
    }

    private void init() {

        headerView = LayoutInflater.from(context).inflate(R.layout.header_chatlist, null);
        tvRemind = (TextView) headerView.findViewById(R.id.tvRemind);
        pbLoading = (ProgressBar) headerView.findViewById(R.id.pbLoading);
        addHeaderView(headerView);
        state = 0;

        this.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    // 静止状态
                    case OnScrollListener.SCROLL_STATE_IDLE:
                        if (!refreshing) {
                            refreshing = true;
                            loadMore();
                        }
                        break;
                    // 手指滚动状态
                    case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                        break;
                    // 手指不动了但屏幕还在滚动
                    case OnScrollListener.SCROLL_STATE_FLING:
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    // 停止加载
                    tvRemind.setVisibility(GONE);
                    pbLoading.setVisibility(GONE);
                    refreshing = false;
                    setEnabled(true);
                    break;
                case 2:
                    // 滑动到底部
                    setSelection(getBottom());
                    break;
            }
        }
    };


    /**
     * 加载更多
     */
    private void loadMore() {
        if (null != onChatRefreshListener && state != 1) {
            // 判断滚动到顶部
            if (getFirstVisiblePosition() == 0) {
                onChatRefreshListener.loadMoreChat();
            } else {
                refreshing = false;
            }
        }
    }

    /**
     * 如果用户看的是最后一项内容就滑动到最底部,其他不管
     */
    public void scrollToBottomBeforeBottom() {
        // 阈值
        int threshold = getAdapter().getCount() - getLastVisiblePosition() - 1;
        if (threshold < 2) {
            Message message = new Message();
            message.what = 2;
            handler.sendMessageDelayed(message, 200);
        }
    }

    /**
     * 滑动到底部
     */
    public void scrollToBottomAlways() {
        Message message = new Message();
        message.what = 2;
        handler.sendMessageDelayed(message, 300);
    }

    public void setOnRefreshListener(OnChatRefreshListener onRefreshListener) {
        state = 0;
        this.onChatRefreshListener = onRefreshListener;
    }

    public void showProgress() {
        tvRemind.setVisibility(GONE);
        pbLoading.setVisibility(VISIBLE);

        setEnabled(false);
    }

    public void showNoData() {
        tvRemind.setText("没有更多消息了");
        tvRemind.setVisibility(VISIBLE);
        pbLoading.setVisibility(GONE);
        state = 1;
        setEnabled(true);

    }

    public void refreshComplete() {
        Message message = new Message();
        message.what = 1;
        handler.sendEmptyMessageDelayed(1, 1000);
    }

}
