package com.unitesoft.huanrong.widget.activity.home;

import android.content.Context;
import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.utils.ConstanceValue;
import com.unitesoft.huanrong.widget.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 开户的webview页
 */
public class KaiHuWebview extends BaseActivity {

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;
    @InjectView(R.id.webview)
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
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

        /**
         * 加载https网址需要加上两个设置
         * 1.
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webview.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
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
                view.loadUrl(ConstanceValue.kaihuurl);
                return true;
            }

            /**
             * 2.
             * @param view
             * @param handler
             * @param error
             */
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        webview.loadUrl(ConstanceValue.kaihuurl);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.kaihuwebview;
    }

    public static void startIntent(Context context) {
        Intent intent = new Intent(context, KaiHuWebview.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
}
