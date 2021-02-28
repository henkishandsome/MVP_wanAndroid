package com.example.mvpgithub.model;

import com.example.mvpgithub.bean.Article;
import com.example.mvpgithub.bean.BannerBean;
import com.example.mvpgithub.bean.BaseArrayBean;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.contract.BannerContract;
import com.example.mvpgithub.net.RetrofitClient;

import io.reactivex.rxjava3.core.Observable;

public class BannerModel implements BannerContract.Model {
    @Override
    public Observable<BaseArrayBean<BannerBean>> getBanner() {
        return RetrofitClient.getInstance().getApi().getBanner();
    }

    @Override
    public Observable<BaseObjectBean<Article>> getArticleList(int curPage) {
        return RetrofitClient.getInstance().getApi().getArticleList(curPage);
    }
}
