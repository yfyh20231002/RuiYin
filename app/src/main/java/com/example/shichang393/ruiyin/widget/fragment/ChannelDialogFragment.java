package com.example.shichang393.ruiyin.widget.fragment;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseViewHolder;
import com.example.shichang393.ruiyin.Bean.Channel;
import com.example.shichang393.ruiyin.R;
import com.example.shichang393.ruiyin.listener.ItemDragHelperCallBack;
import com.example.shichang393.ruiyin.listener.OnChannelDragListener;
import com.example.shichang393.ruiyin.listener.OnChannelListener;
import com.example.shichang393.ruiyin.utils.ConstanceValue;
import com.example.shichang393.ruiyin.widget.adapter.home.ChoiceTitleAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.example.shichang393.ruiyin.Bean.Channel.TYPE_MY_CHANNEL;

/**
 * A simple {@link Fragment} subclass.
 * 频道选择
 */
public class ChannelDialogFragment extends DialogFragment implements OnChannelDragListener {
    private List<Channel> mDatas = new ArrayList<>();
    List<Channel> selectlist = new ArrayList<>();
    List<Channel> unselsectlist = new ArrayList<>();
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    ChoiceTitleAdapter adapter;
    private ItemTouchHelper mHelper;
    private OnChannelListener mOnChannelListener;

    public void setOnChannelListener(OnChannelListener onChannelListener) {
        mOnChannelListener = onChannelListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Dialog dialog = getDialog();
        if (dialog != null) {
            //添加动画
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        View view = inflater.inflate(R.layout.fragment_channel_dialog, container, false);
        ButterKnife.inject(this, view);
        return view;
    }


    public static ChannelDialogFragment newInstance(List<Channel> selectedDatas, List<Channel> unselectedDatas) {
        ChannelDialogFragment dialogFragment = new ChannelDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ConstanceValue.DATA_SELECTED, (Serializable) selectedDatas);
        bundle.putSerializable(ConstanceValue.DATA_UNSELECTED, (Serializable) unselectedDatas);
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initdata();
        initview();
    }

    private void initdata() {
        Bundle bundle = getArguments();
        selectlist = (List<Channel>) bundle.getSerializable(ConstanceValue.DATA_SELECTED);
        unselsectlist = (List<Channel>) bundle.getSerializable(ConstanceValue.DATA_UNSELECTED);
    }

    private void initview() {
        mDatas.add(new Channel(Channel.TYPE_MY, "编辑"));
        setDataType(selectlist, TYPE_MY_CHANNEL);
        setDataType(unselsectlist, Channel.TYPE_OTHER_CHANNEL);
        mDatas.addAll(selectlist);
        mDatas.add(new Channel(Channel.TYPE_OTHER, "点击添加更多"));
        mDatas.addAll(unselsectlist);
        adapter = new ChoiceTitleAdapter(mDatas, this);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 4);
        recyclerview.setLayoutManager(manager);
        recyclerview.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int itemViewType = adapter.getItemViewType(position);
                return itemViewType == TYPE_MY_CHANNEL || itemViewType == Channel.TYPE_OTHER_CHANNEL ? 1 : 4;
            }
        });
        ItemDragHelperCallBack callBack = new ItemDragHelperCallBack(this);
        mHelper = new ItemTouchHelper(callBack);
        adapter.setOnChannelDragListener(this);
        mHelper.attachToRecyclerView(recyclerview);

    }

    private void setDataType(List<Channel> datas, int type) {
        for (int i = 0; i < datas.size(); i++) {
            datas.get(i).setItemType(type);
        }
    }

    @Override
    public void onItemMove(int starPos, int endPos) {
        if (mOnChannelListener != null)
            mOnChannelListener.onItemMove(starPos - 1, endPos - 1);//去除标题所占的一个index
        onMove(starPos, endPos);
    }

    private void onMove(int starPos, int endPos) {
        Channel startChannel = mDatas.get(starPos);
        //先删除之前的位置
        mDatas.remove(starPos);
        //添加到现在的位置
        mDatas.add(endPos, startChannel);
        adapter.notifyItemMoved(starPos, endPos);
    }

    @Override
    public void onMoveToMyChannel(int starPos, int endPos) {
        //移动到我的频道
        onMove(starPos, endPos);

        if (mOnChannelListener != null)
            mOnChannelListener.onMoveToMyChannel(starPos - 1 - adapter.getMyChannelSize(), endPos - 1);
    }

    @Override
    public void onMoveToOtherChannel(int starPos, int endPos) {
        //移动到推荐频道
        onMove(starPos, endPos);
        if (mOnChannelListener != null)
            mOnChannelListener.onMoveToOtherChannel(starPos - 1, endPos - 2 - adapter.getMyChannelSize());
    }

    @Override
    public void onStarDrag(BaseViewHolder baseViewHolder) {
        mHelper.startDrag(baseViewHolder);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private DialogInterface.OnDismissListener mOnDismissListener;

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        mOnDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (mOnDismissListener != null)
            mOnDismissListener.onDismiss(dialog);
    }
}
