package com.example.mvpgithub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.mvpgithub.util.LoginShared;

import java.lang.ref.WeakReference;

public class WelcomeActivity extends AppCompatActivity {
    private static final int GO_HOME = 0;//去主页
    private static final int GO_LOGIN = 1;//去登录页
    private MHandler mhandler;
    /**
     * 跳转判断
     */
//    private Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case GO_HOME://去主页
//                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                    break;
//                case GO_LOGIN://去登录页
//                    Intent intent2 = new Intent(WelcomeActivity.this, LoginActivity.class);
//                    startActivity(intent2);
//                    finish();
//                    break;
//            }
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mhandler = new MHandler(this);


//        if (UserManager.getInstance().hasUserInfo(this))//自动登录判断，SharePrefences中有数据，则跳转到主页，没数据则跳转到登录页
//        {
//            mHandler.sendEmptyMessageDelayed(GO_HOME, 2000);
//        } else {
//            mHandler.sendEmptyMessageAtTime(GO_LOGIN, 2000);
//        }

                if (LoginShared.getInstance().hasLoginInfo(this))//自动登录判断，SharePrefences中有数据，则跳转到主页，没数据则跳转到登录页
                  {
                   mhandler.sendEmptyMessageDelayed(GO_HOME, 2000);
                  } else {
                      mhandler.sendEmptyMessageAtTime(GO_LOGIN, 2000);
                 }

    }

    private static class MHandler extends Handler {
        private final WeakReference<WelcomeActivity> mActivity;

        public MHandler(WelcomeActivity activity) {
            mActivity = new WeakReference<WelcomeActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity.get() == null) {
                return;
            }
            //mActivity.get().todo();
            switch (msg.what) {
                case GO_HOME:
                    mActivity.get().startActivity(new Intent(mActivity.get().getApplicationContext(),SuccessActivity.class));
                    mActivity.get().finish();
                    break;
                case GO_LOGIN:
                    mActivity.get().startActivity(new Intent(mActivity.get().getApplicationContext(), LoginActivity.class));
                    mActivity.get().finish();
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mhandler.removeCallbacksAndMessages(null);
    }

}
