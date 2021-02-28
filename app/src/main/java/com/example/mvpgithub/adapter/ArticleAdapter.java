package com.example.mvpgithub.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvpgithub.R;
import com.example.mvpgithub.WebViewActivity;
import com.example.mvpgithub.bean.Article;
import com.example.mvpgithub.util.MyApplication;
import com.youth.banner.Banner;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    private List<Article.DatasBean> mdatasList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View articleView;
        ImageView iv_isLiked;
        TextView tv_article_title,tv_article_info;

        public ViewHolder(View view) {
            super(view);
            articleView = view;
            iv_isLiked = view.findViewById(R.id.iv_isLiked);
            tv_article_title = view.findViewById(R.id.tv_article_title);
            tv_article_info = view.findViewById(R.id.tv_article_info);
        }
    }



    public ArticleAdapter(List<Article.DatasBean> dataList) {
        mdatasList = dataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.articleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Article.DatasBean datasBean = mdatasList.get(position);
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
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        Article.DatasBean datasBean = mdatasList.get(position);
//        Article.DatasBean datasBean = new Article.DatasBean();
        holder.tv_article_info.setText("作者 "+datasBean.getAuthor()+"分享者 "+datasBean.getShareUser()+"时间 "+datasBean.getNiceDate());
        holder.tv_article_title.setText(datasBean.getTitle().toString());
    }

    @Override
    public int getItemCount() {
        return mdatasList.size();
    }

}
