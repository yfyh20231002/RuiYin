package com.example.shichang393.ruiyin.widget.fragment.live;

import android.support.v4.app.Fragment;

/**
 * Created by Mr.zhang on 2017/8/1.
 */

public abstract class BaseFragment extends Fragment {
    protected  boolean isVisible;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {

        if (isVisibleToUser){
            isVisible=true;
            onVisible();
        }else {
            isVisible=false;
            onInvisible();
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();
    protected void onInvisible(){}
}
