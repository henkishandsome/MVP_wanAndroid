package com.example.mvpgithub.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.mvpgithub.R;
import com.example.mvpgithub.WebViewActivity;
import com.example.mvpgithub.adapter.ArticleAdapter;
import com.example.mvpgithub.adapter.HeaderAdapter;
import com.example.mvpgithub.base.baseMVPFragment;
import com.example.mvpgithub.bean.Article;
import com.example.mvpgithub.bean.BannerBean;
import com.example.mvpgithub.bean.BaseArrayBean;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.contract.BannerContract;
import com.example.mvpgithub.present.BannerPresenter;
import com.example.mvpgithub.present.LoginPresenter;
import com.example.mvpgithub.util.GlideImageLoader;
import com.example.mvpgithub.util.MyApplication;
import com.scwang.smartrefresh.header.DeliveryHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;


public class First_1 extends baseMVPFragment<BannerPresenter> implements BannerContract.View {
    private static final String TAG = "First_1";
    private Banner mBanner;
    private RecyclerView mRecycleView;
    private ProgressBar mProgressBar;
    private SmartRefreshLayout refreshLayout;
    private int CurPage = 0;

//    private ArrayList<String> imagePath;
//    private ArrayList<String> imageTitle;
    private List<String> imageTitle= new ArrayList<>();
    private List<String> imagePath= new ArrayList<>();
    private List<String> urlList= new ArrayList<>();

    private List<Article.DatasBean> articleList = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.fg1_1;
    }

    @Override
    protected void initView(View view) {
        mPresenter = new BannerPresenter();
        mPresenter.attachView(this);

        mBanner = view.findViewById(R.id.bn_titler);
        mRecycleView = view.findViewById(R.id.rv_article_list);
        mProgressBar = view.findViewById(R.id.pb_re);
        refreshLayout = view.findViewById(R.id.refreshLayout);

        mPresenter.getBanner();

        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.context);
        mRecycleView.setLayoutManager(layoutManager);

        refreshLayout.setRefreshHeader(new MaterialHeader(MyApplication.context).setShowBezierWave(true));
        refreshLayout.setRefreshFooter(new BallPulseFooter(MyApplication.context).setSpinnerStyle(SpinnerStyle.Scale));

        //刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                articleList.clear();
                imagePath.clear();
                imageTitle.clear();
                urlList.clear();
                mPresenter.getBanner();
                refreshLayout.finishRefresh();
            }
        });

        //加载更多
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                CurPage = CurPage + 1;
                mPresenter.getArticleList(CurPage);
                refreshLayout.finishLoadMore();
            }
        });

    }


    @Override
    public void onSuccess(BaseArrayBean<BannerBean> bean, List<String> tiltelist, List<String> imgpath,List<String> urllist) {
        imagePath = imgpath;
        imageTitle = tiltelist;
        urlList = urllist;
        mPresenter.getArticleList(0);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(String errMessage) {

    }

    @Override
    public void onGetArticleListSuccess(BaseObjectBean<Article> articleBaseObjectBean, List<Article.DatasBean> articlelist) {
        articleList.addAll(articlelist);
//        ArticleAdapter articleAdapter = new ArticleAdapter(articleList);
//        mRecycleView.setAdapter(articleAdapter);
        HeaderAdapter headerAdapter = new HeaderAdapter(getContext(),articleList,imageTitle,imagePath,urlList);
        mRecycleView.setAdapter(headerAdapter);
    }
}
