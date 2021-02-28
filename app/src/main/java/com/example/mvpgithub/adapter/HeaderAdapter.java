package com.example.mvpgithub.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpgithub.R;
import com.example.mvpgithub.WebViewActivity;
import com.example.mvpgithub.bean.Article;
import com.example.mvpgithub.util.GlideImageLoader;
import com.example.mvpgithub.util.MyApplication;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //item类型
    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mHeaderCount=1;//头部View个数
    private int mBottomCount=0;//底部View个数
    private List<Article.DatasBean> mdatasList;
    private List<String> mimageTitle= new ArrayList<>();
    private List<String> mimagePath= new ArrayList<>();
    private List<String> murlList= new ArrayList<>();
    private static GlideImageLoader mImageLoader;
    private static Banner mBanner;

    public HeaderAdapter(Context context,List<Article.DatasBean> dataList,List<String> imageTitle,List<String> imagePath,List<String> urlList) {
        mdatasList = dataList;
        mimageTitle= imageTitle;
        mimagePath = imagePath;
        murlList = urlList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }


    //内容长度
    public int getContentItemCount(){
        return mdatasList.size();
    }
    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }
    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }


    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
            //头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        View articleView;
        ImageView iv_isLiked;
        TextView tv_article_title,tv_article_info;
        public ContentViewHolder(View itemView) {
            super(itemView);
            articleView = itemView;
            iv_isLiked = itemView.findViewById(R.id.iv_isLiked);
            tv_article_title = itemView.findViewById(R.id.tv_article_title);
            tv_article_info = itemView.findViewById(R.id.tv_article_info);
        }
    }
    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(View itemView) {
            super(itemView);
           mBanner = itemView.findViewById(R.id.bn_titler);
        }
    }
    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {

        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==ITEM_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_header,parent,false);
            final HeaderViewHolder holder = new HeaderViewHolder(view);
            initBanner(mimageTitle,mimagePath,murlList);
            return holder;
        } else if (viewType == ITEM_TYPE_CONTENT) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.article_item,parent,false);
            final ContentViewHolder holder = new ContentViewHolder(view);
            holder.articleView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Article.DatasBean datasBean = mdatasList.get(position-1);
                    Intent intent = new Intent();
                    intent.putExtra("url",datasBean.getLink());
                    intent.setClass(MyApplication.context, WebViewActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    MyApplication.context.startActivity(intent);
                }
            });
            holder.iv_isLiked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyApplication.context,"收藏功能暂未开启",Toast.LENGTH_LONG).show();
                }
            });
            return holder;
//            return  new ContentViewHolder(mLayoutInflater.inflate(R.layout.article_item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(mLayoutInflater.inflate(R.layout.recycle_header, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof ContentViewHolder) {
            Article.DatasBean datasBean = mdatasList.get(position-1);
//        Article.DatasBean datasBean = new Article.DatasBean();
            ((ContentViewHolder) holder).tv_article_info.setText("作者 "+datasBean.getAuthor()+"分享者 "+datasBean.getShareUser()+"时间 "+datasBean.getNiceDate());
            ((ContentViewHolder) holder).tv_article_title.setText(datasBean.getTitle().toString());

        } else if (holder instanceof BottomViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

    public static void initBanner(List<String> imageTitle,List<String> imagePath,List<String> urlList) {
        mImageLoader = new GlideImageLoader();
        //样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //加载器
        mBanner.setImageLoader(mImageLoader);
        //动画效果
        mBanner.setBannerAnimation(Transformer.ZoomOutSlide);
        //图片标题
        mBanner.setBannerTitles(imageTitle);
        //间隔时间
        mBanner.setDelayTime(4500);
        //是否为自动轮播
        mBanner.isAutoPlay(true);
        //图片小点显示所在位置
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //图片加载地址
        mBanner.setImages(imagePath);
        //启动轮播图。
        mBanner.start();
        //监听轮播图
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                //Toast.makeText(getActivity(), "点击了" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("url",urlList.get(position));
                intent.setClass(MyApplication.context, WebViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                MyApplication.context.startActivity(intent);
            }
        });
    }

}