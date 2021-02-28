package com.example.mvpgithub.model;

import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;
import com.example.mvpgithub.contract.RegisterContract;
import com.example.mvpgithub.net.RetrofitClient;

import io.reactivex.rxjava3.core.Observable;

public class RegisterModel implements RegisterContract.Model {
    @Override
    public Observable<BaseObjectBean<LoginBean>> register(String username, String password, String repassword) {
        return RetrofitClient.getInstance().getApi().register(username,password,repassword);
    }
}
