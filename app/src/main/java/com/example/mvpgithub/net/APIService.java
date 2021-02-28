package com.example.mvpgithub.net;

import com.example.mvpgithub.bean.Article;
import com.example.mvpgithub.bean.BannerBean;
import com.example.mvpgithub.bean.BaseArrayBean;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface APIService {

    /**
     * 登陆
     *
     * @param username 账号
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST("user/login")
    Observable<BaseObjectBean<LoginBean>> login(@Field("username") String username,
                                                @Field("password") String password);

    @FormUrlEncoded
    @POST("user/register")
    Observable<BaseObjectBean<LoginBean>> register(@Field("username") String username,
                                                @Field("password") String password,
                                                   @Field("repassword") String repassword);
//获取bannner
    @GET("/banner/json")
    Observable<BaseArrayBean<BannerBean>> getBanner();
//获取首页文章
    @GET("/article/list/{curPage}/json")
    Observable<BaseObjectBean<Article>> getArticleList(@Path("curPage") int curPage);

    //上传图片(私有接口)
//    @POST("index.php/PrivateApi/Goods/uploadPic")
//    @Multipart
//    Observable<BaseListModel<String>> upLoadImg(@Part MultipartBody.Part parts);
    //多图上传
//    @POST("index.php/PrivateApi/Goods/uploadPic")
//    @Multipart
//    Observable<BaseListModel<String>> upLoadImg(@Part MultipartBody.Part[] parts);
}
