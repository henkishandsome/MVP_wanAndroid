package com.example.mvpgithub.present;

import android.util.Log;

import com.example.mvpgithub.base.basePresenter;
import com.example.mvpgithub.bean.Article;
import com.example.mvpgithub.bean.BannerBean;
import com.example.mvpgithub.bean.BaseArrayBean;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.contract.BannerContract;
import com.example.mvpgithub.model.BannerModel;
import com.example.mvpgithub.net.RxScheduler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class BannerPresenter extends basePresenter<BannerContract.View> implements BannerContract.Presenter {
    private BannerContract.Model model;
    private List<String> titlelist= new ArrayList<>();
    private List<String> imgpath= new ArrayList<>();
    private List<String> urllist= new ArrayList<>();

//    private List<String> articleTitle = new ArrayList<>();
//    private List<String> articleInfo = new ArrayList<>();
//    private List<String> articleLink = new ArrayList<>();
    private List<Article.DatasBean> articleList = new ArrayList<>();

    public BannerPresenter() {
        model = new BannerModel();
    }

    private static final String TAG = "BannerPresenter";
    @Override
    public void getBanner() {
        //View是否绑定 如果没有绑定，就不执行网络请求
        if (!isViewAttached()) {
            return;
        }
        model.getBanner()
                .compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<BaseArrayBean<BannerBean>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseArrayBean<BannerBean> bannerBeanBaseArrayBean) {
//                        Gson gson = new Gson();
//                        Data data = gson.fromJson(jsonData, Data.class);
//                        // data就是整个JSON数据映射的一个对象
//                        String result = data.getResult(); // 取result字段的值
//                        String pwd = data.getPwd();   // 取pwd字段的值
//                        String user = data.getUser();  // 取user字段的值
//
//                        // 因为jsonArray字段对应的是一个JSON数组，所以要用一个List来对应
//                        List<Data.JsonArrayBean> list = data.getJsonArray();
//                        //取值
//                        for (int i = 0; i < list.size(); i++) {
//                            Data.JsonArrayBean jsonArrayBean = list.get(i);
//                            String user1 = jsonArrayBean.getUser();
//                            String pwd1 = jsonArrayBean.getPwd();
//                        }
                        List<BannerBean> bannerBeans = bannerBeanBaseArrayBean.getData();
                        for (int i = 0; i<bannerBeans.size(); i++) {
                            BannerBean bannerBean = bannerBeans.get(i);
                            String title = bannerBean.getTitle();
                            String img = bannerBean.getImagePath();
                            String url = bannerBean.getUrl();
                            titlelist.add(title);
                            imgpath.add(img);
                            urllist.add(url);
                        }
                        mView.onSuccess(bannerBeanBaseArrayBean,titlelist,imgpath,urllist);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getArticleList(int curPage) {
        if (!isViewAttached()) {
            return;
        }
        model.getArticleList(curPage)
                .compose(RxScheduler.Obs_io_main())
                .to(mView.bindAutoDispose())
                .subscribe(new Observer<BaseObjectBean<Article>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull BaseObjectBean<Article> articleBaseObjectBean) {
                        List<Article.DatasBean> articlelist = articleBaseObjectBean.getData().getDatas();
                        for (int i = 0; i<articlelist.size(); i++){
                            Article.DatasBean datasBean = articlelist.get(i);
                            articleList.add(datasBean);
                        }
                        mView.onGetArticleListSuccess(articleBaseObjectBean,articleList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onGetArticleError: "+e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
