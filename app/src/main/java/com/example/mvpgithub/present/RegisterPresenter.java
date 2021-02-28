package com.example.mvpgithub.present;

import com.example.mvpgithub.base.basePresenter;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;
import com.example.mvpgithub.contract.RegisterContract;
import com.example.mvpgithub.model.RegisterModel;
import com.example.mvpgithub.net.RxScheduler;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


public class RegisterPresenter extends basePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private RegisterContract.Model model;

    public RegisterPresenter() {
        model = new RegisterModel();
    }

    @Override
    public void register(String username, String password, String repassword) {
        if (!isViewAttached()) {
            return;
        }
        model.register(username,password,repassword)
                .compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<BaseObjectBean<LoginBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mView.showLoading();
                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<LoginBean> loginBeanBaseObjectBean) {
                        mView.onSuccess(loginBeanBaseObjectBean);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mView.onError(e.getMessage());
                        mView.hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
