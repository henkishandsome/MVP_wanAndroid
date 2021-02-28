package com.example.mvpgithub.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.mvpgithub.bean.LoginBean;

import java.util.List;

public class LoginShared {
    private static LoginShared instance;

    private LoginShared() {
    }

    public static LoginShared getInstance() {
        if (instance == null) {
            instance = new LoginShared();
        }
        return instance;
    }


    /**
     * 保存自动登录的用户信息
     */
    public void saveLoginInfo(Context context, boolean admin, int id, int type, String username,String icon) {
        SharedPreferences sp = context.getSharedPreferences("loginbean", Context.MODE_PRIVATE);//Context.MODE_PRIVATE表示SharePrefences的数据只有自己应用程序能访问。
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("admin",admin);
        editor.putInt("id",id);
        editor.putInt("type",type);
        editor.putString("username",username);
        editor.putString("icon",icon);
        editor.commit();
    }


    /**
     * 获取用户信息model
     *
     * @param context
     * @param
     * @param
     */
    public LoginBean getLoginInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginbean", Context.MODE_PRIVATE);
        LoginBean loginBean = new LoginBean();
        loginBean.setAdmin(sp.getBoolean("admin", false));
        loginBean.setId(sp.getInt("id",0));
        loginBean.setType(sp.getInt("type",0));
        loginBean.setUsername(sp.getString("username",""));
        loginBean.setIcon(sp.getString("icon",""));
        return loginBean;
    }


    /**
     * loginInfo中是否有数据
     */
    public boolean hasLoginInfo(Context context) {
        LoginBean loginBean = getLoginInfo(context);
        if (loginBean != null) {
            if ((!TextUtils.isEmpty(loginBean.getUsername()))) {//有数据
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
