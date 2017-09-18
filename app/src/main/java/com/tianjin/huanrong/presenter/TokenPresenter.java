package com.tianjin.huanrong.presenter;

import com.tianjin.huanrong.Bean.TokenBean;
import com.tianjin.huanrong.listener.TokenListener;
import com.tianjin.huanrong.model.TokenModel;
import com.tianjin.huanrong.view.TokenView;

import java.security.NoSuchAlgorithmException;

/**
 * Created by Mr.zhang on 2017/7/6.
 */

public class TokenPresenter implements TokenListener {
    TokenModel model;
    TokenView view;

    public TokenPresenter(TokenView view) {
        this.view = view;
        model = new TokenModel(this);
    }

    public void getToken(String userId, String name, String portraitUri) {
        try {
            model.getToken(userId, name, portraitUri);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void success(TokenBean bean) {
        view.success(bean);
    }

    @Override
    public void failde(String message) {
        view.failde(message);
    }
}
