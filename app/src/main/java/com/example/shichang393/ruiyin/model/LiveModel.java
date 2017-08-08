package com.example.shichang393.ruiyin.model;

import com.example.shichang393.ruiyin.ApiService.LiveService;
import com.example.shichang393.ruiyin.Bean.LiveBean;
import com.example.shichang393.ruiyin.listener.OnLiveListener;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/6/29.
 */

public class LiveModel {
    OnLiveListener liveListener;

    public LiveModel(OnLiveListener liveListener) {
        this.liveListener = liveListener;
    }

    public void postData(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.bannerurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LiveService liveService = retrofit.create(LiveService.class);
        Call<LiveBean> liveBeanCall = liveService.postLive();
        liveBeanCall.enqueue(new Callback<LiveBean>() {
            @Override
            public void onResponse(Call<LiveBean> call, Response<LiveBean> response) {
                LiveBean body = response.body();
                if (body==null){return;}
                List<LiveBean.DataBean.LiveRoomsBaseInfoBean> liveRoomsBaseInfo = body.getData().getLiveRoomsBaseInfo();
                List<LiveBean.DataBean.LiveRoomsBaseInfoBean> list=new ArrayList<LiveBean.DataBean.LiveRoomsBaseInfoBean>();
                if (liveRoomsBaseInfo==null){return;}
                if (liveRoomsBaseInfo.size()>0){
                    for (int i = 0; i < liveRoomsBaseInfo.size(); i++) {
                        if (liveRoomsBaseInfo.get(i).getZhiboshileixing()==1){
                            list.add(liveRoomsBaseInfo.get(i));
                        }
                    }
                    Collections.reverse(list);
                    liveListener.success(list);
                }else {
                    liveListener.failed("数据解析失败");
                }
            }

            @Override
            public void onFailure(Call<LiveBean> call, Throwable t) {
                liveListener.failed("数据解析失败");
            }
        });
    }

//    public void postTeacherData(String liveid,String userid){
//        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.bannerurl);
//        LiveService liveService = retrofit.create(LiveService.class);
//        Call<TeacherBean> responseBodyCall = liveService.postTeacher(new LivePostData(liveid, userid));
//        responseBodyCall.enqueue(new Callback<TeacherBean>() {
//            @Override
//            public void onResponse(Call<TeacherBean> call, Response<TeacherBean> response) {
//                TeacherBean body = response.body();
//                List<?> analysts = body.getData().getAnalysts();
//                Log.e("TAG", "onResponse: "+analysts.toString());
//            }
//
//            @Override
//            public void onFailure(Call<TeacherBean> call, Throwable t) {
//
//            }
//        });
//
//    }


}
