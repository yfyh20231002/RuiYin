package com.tianjin.huanrong.widget.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.tianjin.huanrong.R;
import com.tianjin.huanrong.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 政经要闻详情页
 */
public class NewsActivity extends BaseActivity {

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @InjectView(R.id.webview)
    WebView webview;
    String newsreferurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        getToolbarTitle().setText("要闻");
        newsreferurl = "http://appinterface.yun066.com/home/GetContentByUrl?url="+getIntent().getStringExtra("newsreferurl");
        init();
    }

    private void init() {
        //js交互
        webview.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放 
        webview.getSettings().setSupportZoom(true);
        // 设置出现缩放工具 
        webview.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(newProgress);
                }
            }

        });
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(newsreferurl);
                return true;
            }
        });
        webview.loadUrl(newsreferurl);
    }

    public static void startIntent(Context context, String url) {
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("newsreferurl", url);
        context.startActivity(intent);
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

}
