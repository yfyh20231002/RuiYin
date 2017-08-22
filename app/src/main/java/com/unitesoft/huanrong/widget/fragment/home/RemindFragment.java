package com.unitesoft.huanrong.widget.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.unitesoft.huanrong.Bean.FlashBean;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.presenter.FlashPresenter;
import com.unitesoft.huanrong.utils.ToastUtils;
import com.unitesoft.huanrong.view.FlashView;
import com.unitesoft.huanrong.widget.activity.home.NewsActivity;
import com.unitesoft.huanrong.widget.adapter.RemindAdapter;
import com.unitesoft.huanrong.widget.fragment.dialog.LoadDialog;
import com.unitesoft.huanrong.widget.fragment.live.BaseFragment;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * 提醒
 */
public class RemindFragment extends BaseFragment implements FlashView {

    FlashPresenter presenter;
    RemindAdapter adapter;
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;

    private LoadDialog loadDialog;
    private Context mContext;
    private  View view;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_remind, container, false);
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
        loadDialog=new LoadDialog();
        loadDialog.show(getChildFragmentManager(),"");
        presenter = new FlashPresenter(this);
        presenter.setModel();
        presenter.getData("市场播报#政经要闻");
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void success(final List<FlashBean.DataBean.DangtianshujuBean> list) {
        loadDialog.dismiss();
        if (adapter==null){
            adapter=new RemindAdapter(list);
        }else {
            adapter.notifyDataSetChanged();
        }
        recyclerview.setAdapter(adapter);
        adapter.setItemClickLitener(new RemindAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                String name=list.get(position).getClassname();
                if ("政经要闻".equals(name)) {
                    if (!TextUtils.isEmpty(list.get(position).getNewsreferurl())) {
                        NewsActivity.startIntent(mContext, list.get(position).getNewsreferurl());
                    }
                }
            }
        });
    }

    @Override
    public void failed(String errormessage) {
        loadDialog.dismiss();
        ToastUtils.showToast(mContext,errormessage);
    }


}
