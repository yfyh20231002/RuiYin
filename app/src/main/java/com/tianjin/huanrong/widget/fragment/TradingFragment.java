package com.tianjin.huanrong.widget.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tianjin.huanrong.R;
import com.tianjin.huanrong.utils.CommonUtil;
import com.tianjin.huanrong.widget.activity.home.KaiHuWebview;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TradingFragment extends Fragment {


    @InjectView(R.id.apk)
    TextView apk;
    @InjectView(R.id.text)
    TextView text;
    @InjectView(R.id.line)
    View line;
    @InjectView(R.id.kaihu)
    LinearLayout kaihu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trading, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextPaint paint = text.getPaint();
        paint.setTextSize(text.getTextSize());
        int  v = (int) paint.measureText(text.getText().toString());
        line.setLayoutParams(new LinearLayout.LayoutParams(v,CommonUtil.dip2px(2)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private void downLoadApk(String downloadUrl) {
        Uri uri = Uri.parse(downloadUrl);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick({R.id.apk, R.id.kaihu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.apk:
                // 应用未安装就去下载
                if (CommonUtil.checkApkExist(getActivity(), "com.happyinsource.shanxi.prd.sp")) {
                    Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage("com.happyinsource.shanxi.prd.sp");
                    startActivity(intent);
                } else {
                    downLoadApk("http://d9.muzisoft.com/1606/com.happyinsource.shanxi.prd.sp.apk?force=1");
                }
                break;
            case R.id.kaihu:
                KaiHuWebview.startIntent(getActivity());
                break;
        }
    }
}
