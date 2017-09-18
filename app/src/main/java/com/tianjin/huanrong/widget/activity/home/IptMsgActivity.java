package com.tianjin.huanrong.widget.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.tianjin.huanrong.R;
import com.tianjin.huanrong.utils.ConstanceValue;
import com.tianjin.huanrong.widget.activity.BaseActivity;
import com.lidong.pdf.PDFView;
import com.lidong.pdf.listener.OnLoadCompleteListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 重要消息详情页
 */
public class IptMsgActivity extends BaseActivity implements OnLoadCompleteListener {

    @InjectView(R.id.pdfview)
    PDFView pdfview;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        getToolbarTitle().setText("斗金");
        url = ConstanceValue.iptmsgurl + getIntent().getStringExtra("path");
        pdfview.fileFromLocalStorage(this, url, url.substring(url.lastIndexOf("/") + 1));

    }

    public static void startIntent(Context context, String path) {
        Intent intent = new Intent(context, IptMsgActivity.class);
        intent.putExtra("path", path);
        context.startActivity(intent);
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_iptmsg;
    }

    @Override
    public void loadComplete(int nbPages) {

    }

}
