package com.unitesoft.huanrong.widget.activity.home;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.utils.ConstanceValue;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.zhang on 2017/6/21.
 */

public class CallDialog extends Activity {

    @InjectView(R.id.cancle)
    TextView cancle;
    @InjectView(R.id.call)
    TextView call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);

        setContentView(R.layout.calldialog);
        ButterKnife.inject(this);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int wid = metrics.widthPixels;
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (wid * 0.8);
        getWindow().setAttributes(params);
        setlistener();
    }

    private void setlistener() {
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(CallDialog.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CallDialog.this, new String[]{Manifest.permission.CALL_PHONE}, ConstanceValue.CALL_PHONE);
                } else {
                    doCallPhone();
                }
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ConstanceValue.CALL_PHONE:
                doCallPhone();
                break;
            default:
                break;
        }
    }

    public void doCallPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri parse = Uri.parse("tel:" + "022-95185");
        intent.setData(parse);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        PackageManager manager = getPackageManager();
        List<ResolveInfo> resolveInfos = manager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = resolveInfos.size() > 0;
        if (isIntentSafe) {
            startActivity(intent);
        }
    }
}
