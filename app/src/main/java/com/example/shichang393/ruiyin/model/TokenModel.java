package com.example.shichang393.ruiyin.model;

import com.example.shichang393.ruiyin.ApiService.TokenService;
import com.example.shichang393.ruiyin.Bean.TokenBean;
import com.example.shichang393.ruiyin.listener.TokenListener;
import com.example.shichang393.ruiyin.utils.CommonUtil;
import com.example.shichang393.ruiyin.utils.ConstanceValue;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.zhang on 2017/7/6.
 */

public class TokenModel {
    TokenListener listener;

    public TokenModel(TokenListener listener) {
        this.listener = listener;
    }

    public void getToken(String userId, String name, String portraitUri) throws NoSuchAlgorithmException {
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(ConstanceValue.tokenBase)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TokenService tokenService = retrofit.create(TokenService.class);
        Map<String,String> map=new HashMap<>();
        String suijishu = CommonUtil.suijishu();
        String shijian = CommonUtil.shijian();
        String sign = CommonUtil.sha1(suijishu, shijian);
        map.put("App-Key",ConstanceValue.rongyun_appkey);
        map.put("Nonce", suijishu);
        map.put("Timestamp",shijian);
        map.put("Signature",sign);

        Map<String,String> map2=new HashMap<>();
        map2.put("userId", userId);
        map2.put("name",name);
        map2.put("portraitUri",portraitUri);
        Call<TokenBean> token = tokenService.getToken(map, map2);
        token.enqueue(new Callback<TokenBean>() {
            @Override
            public void onResponse(Call<TokenBean> call, Response<TokenBean> response) {
                TokenBean body = response.body();
                if (body == null) {
                    return;
                }
                listener.success(body);
            }

            @Override
            public void onFailure(Call<TokenBean> call, Throwable t) {
                listener.failde(t.toString());
            }
        });
    }
}
