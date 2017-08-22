package com.unitesoft.huanrong.widget.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.utils.ConstanceValue;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 日历
 */
public class CalendarFragment extends BaseFragment {


    @InjectView(R.id.webview)
    WebView webview;

    private  View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_calendar, container, false);
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
        if(!isPrepared || !isVisible) {
            return;
        }
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        settings.setLoadWithOverviewMode(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(ConstanceValue.calendarurl);
                return true;
            }

        });
        webview.loadUrl(ConstanceValue.calendarurl);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


}
