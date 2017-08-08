package com.example.shichang393.ruiyin.presenter;

import com.example.shichang393.ruiyin.Bean.TokenBean;
import com.example.shichang393.ruiyin.listener.TokenListener;
import com.example.shichang393.ruiyin.model.TokenModel;
import com.example.shichang393.ruiyin.view.TokenView;

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
