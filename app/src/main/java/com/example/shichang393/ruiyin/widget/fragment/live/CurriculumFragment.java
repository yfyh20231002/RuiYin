package com.example.shichang393.ruiyin.widget.fragment.live;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shichang393.ruiyin.Bean.CurriculumBean;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.manager.SharedPreferencesMgr;
import com.example.shichang393.ruiyin.presenter.CurriculumPresenter;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.utils.ToastUtils;
import com.example.shichang393.ruiyin.view.CurriculumView;
import com.example.shichang393.ruiyin.widget.adapter.live.CurriculumAdapter;
import com.example.shichang393.ruiyin.widget.view.DividerItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 课程表
 */
public class CurriculumFragment extends BaseFragment implements CurriculumView{


    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    CurriculumPresenter presenter;
    CurriculumAdapter adapter;
    int  selectid;

    String[] names={"星期一","星期二","星期三","星期四","星期五",};
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null) {
            view = inflater.inflate(R.layout.fragment_curriculum, container, false);
            isPrepared = true;
            ButterKnife.inject(this, view);
            initab();
            initRecyclerview();
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
        presenter=new CurriculumPresenter(this);
        String id = SharedPreferencesMgr.getZhiboshiid("zhiboshiid", ConstanceValue.DefaultLiveId);
        presenter.postData(id);

    }

    private void initab() {
        for (int i = 0; i < names.length; i++) {
            tablayout.addTab(tablayout.newTab().setText(names[i]),i);
        }
        tablayout.getTabAt(0).select();
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectid=tab.getPosition();
                adapter.setSelectid(selectid);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                selectid=tab.getPosition();
                adapter.setSelectid(selectid);
            }
        });
    }
    private void initRecyclerview() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void success(List<CurriculumBean.PaibanBean> list, List<CurriculumBean.YhtxBean> yhtx) {
        if (adapter==null){
            adapter=new CurriculumAdapter(list,yhtx);
        }else {
            adapter.notifyDataSetChanged();
        }
        adapter.setSelectid(0);
        recyclerview.setAdapter(adapter);
    }

    @Override
    public void failed(String msg) {
        ToastUtils.showToast(getActivity(),msg);
    }


}
