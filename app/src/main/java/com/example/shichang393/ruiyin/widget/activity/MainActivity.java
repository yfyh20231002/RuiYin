package com.example.shichang393.ruiyin.widget.activity;

import android.os.Bundle;
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

    private View lastSelectedIcon;
    private View lastSelectedText;
    private HomeFragment homeFragment;
    private LiveFragment liveFragment;
    private MarkCenterFragment markCenterFragment;
    private TradingFragment tradingFragment;
    private MyFragment myFragment;
    long firstime = 0;
    TokenPresenter presenter;
    boolean isChange;
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isChange){
            if (myFragment == null) {
                myFragment = new MyFragment();
                transaction.add(R.id.fl_content, myFragment);
            } else {
                transaction.show(myFragment);
            }
            transaction.commitAllowingStateLoss();
            //默认选中我的
            setSelectIcon(ivIconMy, tvTextMy);
        }else {
            if (homeFragment == null) {
                homeFragment = new HomeFragment();
                transaction.add(R.id.fl_content, homeFragment);
            } else {
                transaction.show(homeFragment);
            }
            transaction.commitAllowingStateLoss();
            //默认选中首页
            setSelectIcon(ivIconHome, tvTextHome);
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


    public void setColor(View v) {
        if (lastSelectedIcon != null) lastSelectedIcon.setSelected(false);
        if (lastSelectedText != null) lastSelectedText.setSelected(false);
        TextView tv = (TextView) lastSelectedText;
        tv.setTextColor(getResources().getColor(R.color.unselecttextcolor));

        RelativeLayout rl = (RelativeLayout) v;
        ImageView icon = (ImageView) rl.getChildAt(0);
        TextView text = (TextView) rl.getChildAt(1);
        setSelectIcon(icon, text);
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

    private void setSelectIcon(ImageView iv, TextView tv) {
        iv.setSelected(true);
        tv.setSelected(true);
        tv.setTextColor(getResources().getColor(R.color.selecttextcolor));
        lastSelectedIcon = iv;
        lastSelectedText = tv;
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


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondtime = System.currentTimeMillis();
            if (secondtime - firstime > 2000) {
                ToastUtils.showToast(MainActivity.this, "再按一次退出程序！");
                firstime = secondtime;
                return true;
            } else {
                System.exit(0);
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void showHome() {
        setColor(home);
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
    }

    @Override
    public void showMarketCenter() {
        setColor(markcenter);
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
    }

    @Override
    public void showLiveRoom() {
        setColor(live);
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
    }

    @Override
    public void showTrading() {
        setColor(trading);
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
    }

    @Override
    public void showMy() {
        setColor(my);
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
