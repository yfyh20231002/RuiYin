package com.tianjin.huanrong.markcenter;


import com.tianjin.huanrong.markcenter.ziye.SwipeListLayout;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by shichang387 on 2017-7-19.
 */

public class MyOnSlipStatusListener implements SwipeListLayout.OnSwipeStatusListener {

    private SwipeListLayout slipListLayout;
    private Set<SwipeListLayout> sets = new HashSet<>();
    public MyOnSlipStatusListener(SwipeListLayout slipListLayout) {
        this.slipListLayout = slipListLayout;
    }

    @Override
    public void onStatusChanged(SwipeListLayout.Status status) {
        if (status == SwipeListLayout.Status.Open) {
            //若有其他的item的状态为Open，则Close，然后移除
            if (sets.size() > 0) {
                for (SwipeListLayout s : sets) {
                    s.setStatus(SwipeListLayout.Status.Close, true);
                    sets.remove(s);
                }
            }
            sets.add(slipListLayout);
        } else {
            if (sets.contains(slipListLayout))
                sets.remove(slipListLayout);
        }
    }

    @Override
    public void onStartCloseAnimation() {

    }

    @Override
    public void onStartOpenAnimation() {

    }

}