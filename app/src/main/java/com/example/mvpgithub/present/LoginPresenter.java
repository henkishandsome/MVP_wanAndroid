package com.example.mvpgithub.present;


import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.mvpgithub.LoginActivity;
import com.example.mvpgithub.base.basePresenter;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;
import com.example.mvpgithub.contract.LoginContract;
import com.example.mvpgithub.model.LoginModel;
import com.example.mvpgithub.net.RxScheduler;
import com.example.mvpgithub.util.LoginShared;
import com.example.mvpgithub.util.MyApplication;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class LoginPresenter extends basePresenter<LoginContract.View> implements LoginContract.Presenter {

    private LoginContract.Model model;

    public LoginPresenter() {
        model = new LoginModel();
    }

    @Override
    public void login(String username,String password) {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.login(username,password)
                .compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())//解决内存泄漏
                .subscribe(new Observer<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
                        if (loginBeanBaseObjectBean.getErrorCode() == 0 ){
                            Boolean admin = loginBeanBaseObjectBean.getData().isAdmin();
                            int id = loginBeanBaseObjectBean.getData().getId();
                            int type = loginBeanBaseObjectBean.getData().getType();
                            String username = loginBeanBaseObjectBean.getData().getUsername();
                            String icon = loginBeanBaseObjectBean.getData().getIcon();
                            LoginShared.getInstance().saveLoginInfo(MyApplication.context,admin,id,type,username,icon);
                            mView.onSuccess(loginBeanBaseObjectBean);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.onError(e.getMessage());
                        mView.hideLoading();
                    }

                    @Override
                    public void onComplete() {
                        mView.hideLoading();
                    }
                });

    }
}
