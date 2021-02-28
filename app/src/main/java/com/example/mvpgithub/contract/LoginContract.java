package com.example.mvpgithub.contract;


import com.example.mvpgithub.base.baseView;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;

import io.reactivex.rxjava3.core.Observable;

public interface LoginContract {
    interface Model {
        Observable<BaseObjectBean<LoginBean>> login(String username,String password);
    }

    interface View extends baseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(String errMessage);

        void onSuccess(BaseObjectBean<LoginBean> bean);
    }

    interface Presenter {
        /**
         * 登陆
         *
         * @param username
         * @param password
         */
        void login(String username,String password);
    }
}