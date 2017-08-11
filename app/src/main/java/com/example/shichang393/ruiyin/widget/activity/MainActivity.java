package com.example.shichang393.ruiyin.widget.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.shichang393.ruiyin.Bean.TokenBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.presenter.TokenPresenter;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.TokenView;
import com.example.shichang393.ruiyin.widget.activity.home.CallDialog;
import com.example.shichang393.ruiyin.widget.fragment.HomeFragment;
import com.example.shichang393.ruiyin.widget.fragment.LiveFragment;
import com.example.shichang393.ruiyin.widget.fragment.MarkCenterFragment;
import com.example.shichang393.ruiyin.widget.fragment.MyFragment;
import com.example.shichang393.ruiyin.widget.fragment.TradingFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.CSCustomServiceInfo;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

public class MainActivity extends BaseActivity implements View.OnClickListener, HomeFragment.OnBaseTabListener,TokenView {

    @InjectView(R.id.fl_content)
    FrameLayout flContent;
    @InjectView(R.id.ivIconHome)
    ImageView ivIconHome;
    @InjectView(R.id.tvTextHome)
    TextView tvTextHome;
    @InjectView(R.id.ivIconMarkCenter)
    ImageView ivIconMarkCenter;
    @InjectView(R.id.tvTextMarkCenter)
    TextView tvTextMarkCenter;
    @InjectView(R.id.ivIconLive)
    ImageView ivIconLive;
    @InjectView(R.id.tvTextLive)
    TextView tvTextLive;
    @InjectView(R.id.ivIconTrading)
    ImageView ivIconTrading;
    @InjectView(R.id.tvTextTrading)
    TextView tvTextTrading;
    @InjectView(R.id.ivIconMy)
    ImageView ivIconMy;
    @InjectView(R.id.tvTextMy)
    TextView tvTextMy;
    @InjectView(R.id.llBottom)
    LinearLayout llBottom;
    @InjectView(R.id.home)
    RelativeLayout home;
    @InjectView(R.id.markcenter)
    RelativeLayout markcenter;
    @InjectView(R.id.live)
    RelativeLayout live;
    @InjectView(R.id.trading)
    RelativeLayout trading;
    @InjectView(R.id.my)
    RelativeLayout my;
    @InjectView(R.id.call)
    ImageView call;
    @InjectView(R.id.infomation)
    ImageView infomation;

    private HomeFragment homeFragment;
    private LiveFragment liveFragment;
    private MarkCenterFragment markCenterFragment;
    private TradingFragment tradingFragment;
    private MyFragment myFragment;
    TokenPresenter presenter;
    boolean isChange=false;
    // 标识是否退出
    private boolean isExit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolBarTitle("斗金");
        ButterKnife.inject(this);
        isChange=getIntent().getBooleanExtra("change",false);
        setDefaultFragment();
        setViewListener();
        initRongIM();
    }


    public void setDefaultFragment() {
        if (isChange){
            showMy();
        }else {
           showHome();
        }
    }

    private void setViewListener() {
        home.setOnClickListener(this);
        markcenter.setOnClickListener(this);
        live.setOnClickListener(this);
        trading.setOnClickListener(this);
        my.setOnClickListener(this);
    }

    private void initRongIM() {
        presenter = new TokenPresenter(this);
        presenter.getToken("18338699733","zhangsan","");
//        String token = "qtccfUjmRpgMnSPBnZOD7mKQ0bZ8bvt+BvddLdqnXtk1B2SajtIMs8HS2Q5tSufIo7ofUeU37lJ78tCYRZXJ4Pz5iH5GTIII";
//        connect(token);
    }

    private void connect(String token) {

        if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    /**
     * 设置不显示返回按钮
     *
     * @return
     */
    protected boolean isShowBacking() {
        return false;
    }



    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (markCenterFragment != null) {
            transaction.hide(markCenterFragment);
        }
        if (liveFragment != null) {
            transaction.hide(liveFragment);
        }
        if (tradingFragment != null) {
            transaction.hide(tradingFragment);
        }
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
    }
    /**
     * aty与fra发生关联时调用。防止frg重叠
     *
     * @param fragment
     */
    @Override
    public void onAttachFragment(android.support.v4.app.Fragment fragment) {
        super.onAttachFragment(fragment);
        if (null == homeFragment && fragment instanceof HomeFragment) {
            homeFragment = (HomeFragment) fragment;
        } else if (null == markCenterFragment && fragment instanceof MarkCenterFragment) {
            markCenterFragment = (MarkCenterFragment) fragment;
        } else if (null == liveFragment && fragment instanceof LiveFragment) {
            liveFragment = (LiveFragment) fragment;
        } else if (null == tradingFragment && fragment instanceof TradingFragment) {
            tradingFragment = (TradingFragment) fragment;
        } else if (null == myFragment && fragment instanceof MyFragment) {
            myFragment = (MyFragment) fragment;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.home:
                showHome();
                break;
            case R.id.markcenter:
                showMarketCenter();
                break;
            case R.id.trading:
                showTrading();
                break;
            case R.id.live:
                showLiveRoom();
                break;
            case R.id.my:
                showMy();
                break;
            default:
                break;
        }

    }


    /**
     * 监听返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 返回
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return true;
            // 菜单
        } else if (keyCode == KeyEvent.KEYCODE_MENU) {
            return false;
            // home键
        } else if (keyCode == KeyEvent.KEYCODE_HOME) {
            //由于Home键为系统键，此处不能捕获，需要重写onAttachedToWindow()
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    /**
     * 退出动作
     */
    private void exit() {
        if (!isExit) {
            isExit = true;
            ToastUtils.showToast(MainActivity.this,"再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            // 关闭已经打开过的aty
            // TODO
            // 返回桌面
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
    }

    @Override
    public void showHome() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
            transaction.add(R.id.fl_content, homeFragment);
        } else {
            transaction.show(homeFragment);
        }
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
        ivIconHome.setSelected(true);
        tvTextHome.setTextColor(getResources().getColor(R.color.selecttextcolor));
        ivIconLive.setSelected(false);
        tvTextLive.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMarkCenter.setSelected(false);
        tvTextMarkCenter.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconTrading.setSelected(false);
        tvTextTrading.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMy.setSelected(false);
        tvTextMy.setTextColor(getResources().getColor(R.color.unselecttextcolor));

    }

    @Override
    public void showMarketCenter() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (markCenterFragment == null) {
            markCenterFragment = new MarkCenterFragment();
            transaction.add(R.id.fl_content, markCenterFragment);
        } else {
            transaction.show(markCenterFragment);
        }
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
        ivIconHome.setSelected(false);
        tvTextHome.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconLive.setSelected(false);
        tvTextLive.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMarkCenter.setSelected(true);
        tvTextMarkCenter.setTextColor(getResources().getColor(R.color.selecttextcolor));
        ivIconTrading.setSelected(false);
        tvTextTrading.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMy.setSelected(false);
        tvTextMy.setTextColor(getResources().getColor(R.color.unselecttextcolor));
    }

    @Override
    public void showLiveRoom() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (liveFragment == null) {
            liveFragment = new LiveFragment();
            transaction.add(R.id.fl_content, liveFragment);
        } else {
            transaction.show(liveFragment);
        }
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
        ivIconHome.setSelected(false);
        tvTextHome.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconLive.setSelected(true);
        tvTextLive.setTextColor(getResources().getColor(R.color.selecttextcolor));
        ivIconMarkCenter.setSelected(false);
        tvTextMarkCenter.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconTrading.setSelected(false);
        tvTextTrading.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMy.setSelected(false);
        tvTextMy.setTextColor(getResources().getColor(R.color.unselecttextcolor));
    }

    @Override
    public void showTrading() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (tradingFragment == null) {
            tradingFragment = new TradingFragment();
            transaction.add(R.id.fl_content, tradingFragment);
        } else {
            transaction.show(tradingFragment);
        }
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
        ivIconHome.setSelected(false);
        tvTextHome.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconLive.setSelected(false);
        tvTextLive.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMarkCenter.setSelected(false);
        tvTextMarkCenter.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconTrading.setSelected(true);
        tvTextTrading.setTextColor(getResources().getColor(R.color.selecttextcolor));
        ivIconMy.setSelected(false);
        tvTextMy.setTextColor(getResources().getColor(R.color.unselecttextcolor));
    }

    @Override
    public void showMy() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (myFragment == null) {
            myFragment = new MyFragment();
            transaction.add(R.id.fl_content, myFragment);
        } else {
            transaction.show(myFragment);
        }
        transaction.addToBackStack(null);
        transaction.commitAllowingStateLoss();
        ivIconHome.setSelected(false);
        tvTextHome.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconLive.setSelected(false);
        tvTextLive.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMarkCenter.setSelected(false);
        tvTextMarkCenter.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconTrading.setSelected(false);
        tvTextTrading.setTextColor(getResources().getColor(R.color.unselecttextcolor));
        ivIconMy.setSelected(true);
        tvTextMy.setTextColor(getResources().getColor(R.color.selecttextcolor));
    }

    @OnClick({R.id.call, R.id.infomation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.call:
                intentActivity(CallDialog.class);
                break;
            case R.id.infomation:
                // 首先需要构造使用客服者的用户信息
                CSCustomServiceInfo.Builder csBuilder = new CSCustomServiceInfo.Builder();
                CSCustomServiceInfo csInfo = csBuilder.nickName("融云").build();

/**
 * 启动客户服聊天界面。
 *
 * @param context           应用上下文。
 * @param customerServiceId 要与之聊天的客服 Id。
 * @param title             聊天的标题，如果传入空值，则默认显示与之聊天的客服名称。
 * @param customServiceInfo 当前使用客服者的用户信息。{@link io.rong.imlib.model.CSCustomServiceInfo}
 */
                RongIM.getInstance().startCustomerServiceChat(MainActivity.this, "KEFU149924387858873", "在线客服", csInfo);
                break;
            default:
                break;
        }
    }

    @Override
    public void success(TokenBean bean) {
        connect(bean.getToken());
    }

    @Override
    public void failde(String message) {
        ToastUtils.showToast(MainActivity.this,message);

    }
}
