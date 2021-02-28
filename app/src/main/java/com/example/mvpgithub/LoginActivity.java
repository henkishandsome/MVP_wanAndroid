package com.example.mvpgithub;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mvpgithub.base.baseMVPActivity;
import com.example.mvpgithub.bean.BaseObjectBean;
import com.example.mvpgithub.contract.LoginContract;
import com.example.mvpgithub.present.LoginPresenter;
import com.example.mvpgithub.util.LoginShared;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends baseMVPActivity<LoginPresenter> implements LoginContract.View {

    TextInputEditText etUsernameLogin;
    TextInputEditText etPasswordLogin;
    Button btnSigninLogin,btn_register;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        etUsernameLogin = findViewById(R.id.et_username_login);
        etPasswordLogin = findViewById(R.id.et_password_login);
        btnSigninLogin = findViewById(R.id.btn_signin_login);
        btn_register = findViewById(R.id.btn_register);

        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
        btnSigninLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getUsername().isEmpty() || getPassword().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "帐号密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                mPresenter.login(getUsername(),getPassword());
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }

    /**
     * @return 帐号
     */
    private String getUsername() {
        return etUsernameLogin.getText().toString().trim();
    }

    /**
     * @return 密码
     */
    private String getPassword() {
        return etPasswordLogin.getText().toString().trim();
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        startActivity(new Intent(LoginActivity.this,SuccessActivity.class));
        finish();
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