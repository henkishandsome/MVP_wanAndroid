package com.example.mvpgithub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mvpgithub.Service.DownloadService;
import com.example.mvpgithub.base.baseMVPActivity;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;
import com.example.mvpgithub.contract.LoginContract;
import com.example.mvpgithub.fragment.FirstFragment;
import com.example.mvpgithub.fragment.ForthFragment;
import com.example.mvpgithub.fragment.SecondFragment;
import com.example.mvpgithub.fragment.ThirdFragment;
import com.example.mvpgithub.present.LoginPresenter;
import com.example.mvpgithub.util.LoginShared;
import com.example.mvpgithub.util.MyApplication;

public class SuccessActivity extends baseMVPActivity<LoginPresenter> implements  LoginContract.View{
    ImageButton btn_opendraw,btn_setting;
    DrawerLayout drawerLayout;
    TextView tv_showname,tv_showemail;

    private DownloadService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private FirstFragment fg1;
    private SecondFragment fg2;
    private ThirdFragment fg3;
    private ForthFragment fg4;
    private FrameLayout frameLayout;
    private RelativeLayout firstLayout;
    private RelativeLayout secondLayout;
    private RelativeLayout thirdLayout;
    private RelativeLayout forthLayout;
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private ImageView forthImage;
    private TextView firstText;
    private TextView secondText;
    private TextView thirdText;
    private TextView forthText;
    private FragmentManager fragmentManager;
    private int white=0xFFFFFFFF;
    private int gray=0xFF7597B3;
    private int dark=0xff000000;

    @Override
    public int getLayoutId() {
        return R.layout.activity_success;
    }

    @Override
    public void initView() {
        btn_opendraw = findViewById(R.id.btn_opendraw);
        btn_setting = findViewById(R.id.btn_setting);
        drawerLayout = findViewById(R.id.DrawerLayout);
        tv_showname = findViewById(R.id.tv_showname);
        tv_showemail = findViewById(R.id.tv_showemail);
        firstImage= findViewById(R.id.first_image);
        secondImage= findViewById(R.id.second_image);
        thirdImage= findViewById(R.id.third_image);
        forthImage= findViewById(R.id.forth_image);
        firstText= findViewById(R.id.first_text);
        secondText= findViewById(R.id.second_text);
        thirdText= findViewById(R.id.third_text);
        forthText= findViewById(R.id.forth_text);
        firstLayout= findViewById(R.id.first_layout);
        secondLayout= findViewById(R.id.second_layout);
        thirdLayout= findViewById(R.id.third_layout);
        forthLayout= findViewById(R.id.forth_layout);
        fragmentManager=getSupportFragmentManager();
        setChioceItem(0);  //初始化页面加载时显示第一个选项卡
        tv_showname.setText("用户名:"+LoginShared.getInstance().getLoginInfo(MyApplication.context).getUsername());
        tv_showemail.setText("email:"+LoginShared.getInstance().getLoginInfo(MyApplication.context).getEmail());
        btn_opendraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://download.sdk.mob.com/apkbus.apk";
                downloadBinder.startDownload(url);
            }
        });

        Intent intent=new Intent(SuccessActivity.this,DownloadService.class);
        startService(intent); //启动服务
        bindService(intent,connection,BIND_AUTO_CREATE); //绑定服务
        if (ContextCompat.checkSelfPermission(SuccessActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SuccessActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

        firstLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(0);
            }
        });

        secondLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(1);
            }
        });

        thirdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(2);
            }
        });

        forthLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChioceItem(3);
            }
        });

    }

    @Override
    public void onSuccess(BaseObjectBean<LoginBean> bean) {

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

    //设置点击选项卡的事件处理
    private void setChioceItem(int index){
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        clearChioce();  //清空，重置选项，隐藏所有Fragment
        hideFragments(fragmentTransaction);
        switch (index){
            case 0:
                // firstImage.setImageResource(R.drawable.);
                firstText.setTextColor(dark);
                firstLayout.setBackgroundColor(gray);
                //如果fg1为空，则创建一个并添加到界面上
                if (fg1==null){
                    fg1=new FirstFragment();
                    fragmentTransaction.add(R.id.content,fg1);
                }else {
                    //如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg1);

                }
                break;
            case 1:
                // firstImage.setImageResource(R.drawable....);
                secondText.setTextColor(dark);
                secondLayout.setBackgroundColor(gray);
                //如果fg1为空，则创建一个并添加到界面上
                if (fg2==null){
                    fg2=new SecondFragment();
                    fragmentTransaction.add(R.id.content,fg2);
                }else {
                    //如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg2);

                }
                break;
            case 2:
                // firstImage.setImageResource(R.drawable....);
                thirdText.setTextColor(dark);
                thirdLayout.setBackgroundColor(gray);
                //如果fg1为空，则创建一个并添加到界面上
                if (fg3==null){
                    fg3=new ThirdFragment();
                    fragmentTransaction.add(R.id.content,fg3);
                }else {
                    //如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg3);

                }
                break;
            case 3:
                // firstImage.setImageResource(R.drawable....);
                forthText.setTextColor(dark);
                forthLayout.setBackgroundColor(gray);
                //如果fg1为空，则创建一个并添加到界面上
                if (fg4==null){
                    fg4=new ForthFragment();
                    fragmentTransaction.add(R.id.content,fg4);
                }else {
                    //如果不为空，则直接将它显示出来
                    fragmentTransaction.show(fg4);

                }
                break;
        }
        fragmentTransaction.commit();  //提交

    }
    //当选中其中一个选项卡时，其它选项卡重置为默认
    private void clearChioce(){
        // firstImage.setImageResource(R.drawable....);
        firstText.setTextColor(gray);
        firstLayout.setBackgroundColor(white);
        // firstImage.setImageResource(R.drawable....);
        secondText.setTextColor(gray);
        secondLayout.setBackgroundColor(white);
        // firstImage.setImageResource(R.drawable....);
        thirdText.setTextColor(gray);
        thirdLayout.setBackgroundColor(white);
        // firstImage.setImageResource(R.drawable....);
        forthText.setTextColor(gray);
        forthLayout.setBackgroundColor(white);
    }
    //隐藏Fragment
    private void hideFragments(FragmentTransaction fragmentTransaction){
        if (fg1!=null){
            fragmentTransaction.hide(fg1);
        }
        if (fg2!=null){
            fragmentTransaction.hide(fg2);
        }
        if (fg3!=null){
            fragmentTransaction.hide(fg3);
        }
        if (fg4!=null){
            fragmentTransaction.hide(fg4);
        }
    }

    protected long exitTime;//记录第一次点击时的时间
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(SuccessActivity.this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                SuccessActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}