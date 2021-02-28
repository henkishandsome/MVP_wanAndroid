package com.example.mvpgithub.contract;

import com.example.mvpgithub.base.baseView;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;

import io.reactivex.rxjava3.core.Observable;

public interface RegisterContract {
    interface Model {
        Observable<BaseObjectBean<LoginBean>> register(String username, String password,String repassword);
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
        void register(String username,String password,String repassword);
    }
}
