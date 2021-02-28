package com.example.mvpgithub.model;

import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;
import com.example.mvpgithub.contract.LoginContract;
import com.example.mvpgithub.net.RetrofitClient;

import io.reactivex.rxjava3.core.Observable;

public class LoginModel implements LoginContract.Model {
    @Override
    public Observable<BaseObjectBean<LoginBean>> login(String username,String password) {
        return RetrofitClient.getInstance().getApi().login(username,password);
    }
}
