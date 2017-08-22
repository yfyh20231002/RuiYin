package com.unitesoft.huanrong.widget.fragment.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.unitesoft.huanrong.R;

/**
 * Created by Mr.zhang on 2017/8/22.
 */

public class LoadDialog extends DialogFragment {
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width= ViewGroup.LayoutParams.MATCH_PARENT;
        attributes.height=ViewGroup.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(attributes);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View view=inflater.inflate(R.layout.load,container,false);
        return view;
    }
}
