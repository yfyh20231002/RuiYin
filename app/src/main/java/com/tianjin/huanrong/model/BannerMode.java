package com.tianjin.huanrong.model;

import android.content.Context;

import com.tianjin.huanrong.ApiService.BannerService;
import com.tianjin.huanrong.Bean.BannerBean;
import com.tianjin.huanrong.loader.GlideImageLoader;
import com.tianjin.huanrong.utils.ConstanceValue;
import com.tianjin.huanrong.widget.activity.home.LunBoActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/6/27.
 */

public class BannerMode {
    public static void getData(final Banner banner, final Context context){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(ConstanceValue.bannerurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BannerService bannerService = retrofit.create(BannerService.class);
        Call<BannerBean> call=bannerService.postService();
        call.enqueue(new Callback<BannerBean>() {
            @Override
            public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                BannerBean body = response.body();
                if (body==null){return;}
                final List<BannerBean.NewsBean> news = body.getNews();
                if (news==null){return;}
                List<String> images=new ArrayList<String>();
                for (int i = 0; i < news.size(); i++) {
                    images.add(news.get(i).getPicture());
                }
                banner.setImages(images);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                banner.start();
                banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        LunBoActivity.startIntent(context,news.get(position).getPicturecontent());
                    }
                });
            }

            @Override
            public void onFailure(Call<BannerBean> call, Throwable t) {

            }
        });
    }
}
