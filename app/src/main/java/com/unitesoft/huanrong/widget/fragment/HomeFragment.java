package com.unitesoft.huanrong.widget.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidkun.xtablayout.XTabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unitesoft.huanrong.Bean.Channel;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.listener.OnChannelListener;
import com.unitesoft.huanrong.manager.SharedPreferencesMgr;
import com.unitesoft.huanrong.utils.ConstanceValue;
import com.unitesoft.huanrong.widget.activity.home.KaiHuActivity;
import com.unitesoft.huanrong.widget.activity.live.StudioActivity;
import com.unitesoft.huanrong.widget.activity.mine.LoginActivity;
import com.unitesoft.huanrong.widget.fragment.home.AlertsFragment;
import com.unitesoft.huanrong.widget.fragment.home.CCTVFragment;
import com.unitesoft.huanrong.widget.fragment.home.CalendarFragment;
import com.unitesoft.huanrong.widget.fragment.home.DataFragment;
import com.unitesoft.huanrong.widget.fragment.home.EIAFragment;
import com.unitesoft.huanrong.widget.fragment.home.FeiNongFragment;
import com.unitesoft.huanrong.widget.fragment.home.Home;
import com.unitesoft.huanrong.widget.fragment.home.InternalFragment;
import com.unitesoft.huanrong.widget.fragment.home.NoticeFragment;
import com.unitesoft.huanrong.widget.fragment.home.RemindFragment;
import com.unitesoft.huanrong.widget.fragment.home.SuggestionsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.unitesoft.huanrong.utils.ConstanceValue.TITLE_SELECTED;
import static com.unitesoft.huanrong.utils.ConstanceValue.TITLE_UNSELECTED;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @InjectView(R.id.icon_category)
    ImageView iconCategory;
    @InjectView(R.id.xTab)
    XTabLayout xTab;
    //品种
    public List<Channel> mSelectedDatas = new ArrayList<>();
    public List<Channel> mUnSelectedDatas = new ArrayList<>();
    OnBaseTabListener onBaseTabListener;

    private Gson mGson = new Gson();

    //首页
    Home home;
    //日历
    CalendarFragment calendar;
    //公告
    NoticeFragment notice;
    //快讯
    AlertsFragment alerts;
    // 数据
    DataFragment data;
    //cctv
    CCTVFragment cctv;
    //策略
    InternalFragment internal;
    //狙击非农
    FeiNongFragment feinong;
    //EIA
    EIAFragment EIA;
    //提醒
    RemindFragment remind;
    //操作建议
    SuggestionsFragment suggestion;
    Context mContext;


    public interface OnBaseTabListener {

        void showHome();

        void showMarketCenter();

        void showLiveRoom();

        void showTrading();

        void showMy();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onBaseTabListener = (OnBaseTabListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + "必须实现onBaseTabListener接口");
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**
         * 第一个tablayout的事件
         */
        oneTabEvent();
        xTab.getTabAt(0).select();
        iconCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
                dialogFragment.setOnChannelListener(new OnChannelListener() {
                    @Override
                    public void onItemMove(int starPos, int endPos) {
                        listMove(mSelectedDatas, starPos, endPos);
                    }

                    @Override
                    public void onMoveToMyChannel(int starPos, int endPos) {
                        Channel channel = mUnSelectedDatas.remove(starPos);
                        mSelectedDatas.add(endPos, channel);
                    }

                    @Override
                    public void onMoveToOtherChannel(int starPos, int endPos) {
                        mUnSelectedDatas.add(endPos, mSelectedDatas.remove(starPos));
                    }
                });
                dialogFragment.show(getFragmentManager(), "channel");
                dialogFragment.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        xTab.removeAllTabs();
                        for (int i = 0; i < mSelectedDatas.size(); i++) {
                            xTab.addTab(xTab.newTab().setText(mSelectedDatas.get(i).Title));
                        }
                        //保存选中和未选中的channel
                        SharedPreferencesMgr.setString(ConstanceValue.TITLE_SELECTED, mGson.toJson(mSelectedDatas));
                        SharedPreferencesMgr.setString(ConstanceValue.TITLE_UNSELECTED, mGson.toJson(mUnSelectedDatas));
                    }
                });
            }
        });
    }


    private void listMove(List datas, int starPos, int endPos) {
        Object o = datas.get(starPos);
        //先删除之前的位置
        datas.remove(starPos);
        //添加到现在的位置
        datas.add(endPos, o);
    }


    private void oneTabEvent() {
        getTitleData();
        xTab.post(new Runnable() {
            @Override
            public void run() {
                //设置最小宽度，使其可以在滑动一部分距离
                ViewGroup slidingTabStrip = (ViewGroup) xTab.getChildAt(0);
                slidingTabStrip.setMinimumWidth(iconCategory.getMeasuredWidth());
            }
        });
        xTab.setOnTabSelectedListener(new XTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(XTabLayout.Tab tab) {
                selectab(tab);

            }

            @Override
            public void onTabUnselected(XTabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(XTabLayout.Tab tab) {
                selectab(tab);
            }
        });
    }

    private void getTitleData() {
        String selectTitle = SharedPreferencesMgr.getString(TITLE_SELECTED, "");
        String unselectTitle = SharedPreferencesMgr.getString(TITLE_UNSELECTED, "");
        if (TextUtils.isEmpty(selectTitle) || TextUtils.isEmpty(unselectTitle)) {
            //本地没有title
            String[] titleStr = getResources().getStringArray(R.array.home_title);
            String[] unselectitle = getResources().getStringArray(R.array.unselect);
            //默认添加了8个频道
            for (int i = 0; i < titleStr.length; i++) {
                String t = titleStr[i];
                mSelectedDatas.add(new Channel(t));
                xTab.addTab(xTab.newTab().setText(t));
            }
            for (int i = 0; i < unselectitle.length; i++) {
                String t = unselectitle[i];
                mUnSelectedDatas.add(new Channel(t));
            }

            String selectedStr = mGson.toJson(mSelectedDatas);
            String unselectedStr = mGson.toJson(mUnSelectedDatas);
            SharedPreferencesMgr.setString(TITLE_SELECTED, selectedStr);
            SharedPreferencesMgr.setString(TITLE_UNSELECTED, unselectedStr);
        } else {
            //之前添加过
            List<Channel> selecteData = mGson.fromJson(selectTitle, new TypeToken<List<Channel>>() {
            }.getType());
            List<Channel> unselecteData = mGson.fromJson(unselectTitle, new TypeToken<List<Channel>>() {
            }.getType());
            mSelectedDatas.addAll(selecteData);
            mUnSelectedDatas.addAll(unselecteData);
            for (int i = 0; i < mSelectedDatas.size(); i++) {
                xTab.addTab(xTab.newTab().setText(mSelectedDatas.get(i).Title));
            }
        }
    }


    private void selectab(XTabLayout.Tab tab) {
        String name = (String) tab.getText();
        switch (name) {
            case "首页":
                showHome();
                break;
            case "开户":
                KaiHuActivity.startIntent(getActivity());
                break;
            case "直播":
                onBaseTabListener.showLiveRoom();
                break;
            case "日历":
                showCalendar();
                break;
            case "数据":
                showData();
                break;
            case "央视":
                showCCTV();
                break;
            case "策略":
                showInternal();
                break;
            case "快讯":
                showFlash();
                break;
            case "行情":
                onBaseTabListener.showMarketCenter();
                break;
            case "交易":
                onBaseTabListener.showTrading();
                break;
            case "提醒":
                showRemind();
                break;
            case "公告":
                showNotice();
                break;
            case "我的":
                onBaseTabListener.showMy();
                break;
            case "天涯海阁":
                SharedPreferencesMgr.setZhiboshiid("FE7C1D82811A4E7DB4BA84D77E141F0A");
                if (TextUtils.isEmpty(SharedPreferencesMgr.getuserid())) {
                    LoginActivity.startIntent(mContext, false);
                } else {
                    StudioActivity.startIntent(mContext);
                }
                break;
            case "谈股论金":
                SharedPreferencesMgr.setZhiboshiid("DB9F8E38D4624A3997F2AAD34AEFCD0D");
                if (TextUtils.isEmpty(SharedPreferencesMgr.getuserid())) {
                    LoginActivity.startIntent(mContext, false);
                } else {
                    StudioActivity.startIntent(mContext);
                }
                break;
            case "狙击非农":
                showFeiNong();
                break;
            case "决战EIA":
                showEIA();
                break;
            case "操作建议":
                showSuggestion();
                break;
            case "现货黄金":
                break;
            case "现货白银":
                break;
            case "美原油连续":
                break;
            case "柏油":
                break;
            case "咖啡":
                break;
            case "现货铜":
                break;
            default:
        }
    }


    private void showHome() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (home == null) {
            home = new Home();
            transaction.add(R.id.home_content, home);
        } else {
            transaction.show(home);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showCalendar() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (calendar == null) {
            calendar = new CalendarFragment();
            transaction.add(R.id.home_content, calendar);
        } else {
            transaction.show(calendar);
        }
        transaction.commitAllowingStateLoss();
    }


    private void showRemind() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (remind == null) {
            remind = new RemindFragment();
            transaction.add(R.id.home_content, remind);
        } else {
            transaction.show(remind);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showNotice() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (notice == null) {
            notice = new NoticeFragment();
            transaction.add(R.id.home_content, notice);
        } else {
            transaction.show(notice);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showFlash() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (alerts == null) {
            alerts = new AlertsFragment();
            transaction.add(R.id.home_content, alerts);
        } else {
            transaction.show(alerts);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showData() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (data == null) {
            data = new DataFragment();
            transaction.add(R.id.home_content, data);
        } else {
            transaction.show(data);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showCCTV() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (cctv == null) {
            cctv = new CCTVFragment();
            transaction.add(R.id.home_content, cctv);
        } else {
            transaction.show(cctv);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showInternal() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (internal == null) {
            internal = new InternalFragment();
            transaction.add(R.id.home_content, internal);
        } else {
            transaction.show(internal);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showFeiNong() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (feinong == null) {
            feinong = new FeiNongFragment();
            transaction.add(R.id.home_content, feinong);
        } else {
            transaction.show(feinong);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showEIA() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (EIA == null) {
            EIA = new EIAFragment();
            transaction.add(R.id.home_content, EIA);
        } else {
            transaction.show(EIA);
        }
        transaction.commitAllowingStateLoss();
    }

    private void showSuggestion() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        if (suggestion == null) {
            suggestion = new SuggestionsFragment();
            transaction.add(R.id.home_content, suggestion);
        } else {
            transaction.show(suggestion);
        }
        transaction.commitAllowingStateLoss();
    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction) {
        if (home != null) {
            transaction.hide(home);
        }
        if (calendar != null) {
            transaction.hide(calendar);
        }
        if (notice != null) {
            transaction.hide(notice);
        }
        if (alerts != null) {
            transaction.hide(alerts);
        }
        if (data != null) {
            transaction.hide(data);
        }
        if (cctv != null) {
            transaction.hide(cctv);
        }
        if (internal != null) {
            transaction.hide(internal);
        }
        if (feinong != null) {
            transaction.hide(feinong);
        }
        if (EIA != null) {
            transaction.hide(EIA);
        }
        if (remind != null) {
            transaction.hide(remind);
        }
        if (suggestion != null) {
            transaction.hide(suggestion);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

}
