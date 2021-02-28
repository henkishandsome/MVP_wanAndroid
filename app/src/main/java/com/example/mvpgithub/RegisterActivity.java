package com.example.mvpgithub;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvpgithub.base.baseMVPActivity;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.bean.LoginBean;
import com.example.mvpgithub.contract.RegisterContract;
import com.example.mvpgithub.present.RegisterPresenter;

public class RegisterActivity extends baseMVPActivity<RegisterPresenter> implements RegisterContract.View {

    EditText et_username,et_password,et_repassword;
    Button btn_register_submit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        et_repassword = findViewById(R.id.et_repassword);
        btn_register_submit = findViewById(R.id.btn_register_submit);

        mPresenter = new RegisterPresenter();
        mPresenter.attachView(this);

        btn_register_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String repassword = et_repassword.getText().toString();
                mPresenter.register(username,password,repassword);
            }
        });

    }

    @Override
    public void onSuccess(BaseObjectBean<LoginBean> bean) {
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
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
}