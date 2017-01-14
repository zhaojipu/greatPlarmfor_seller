package com.greatPlarm.seller.ui.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseNetActivity;
import com.greatPlarm.seller.presenter.LoginPresenter;
import com.greatPlarm.seller.ui.MainActivity;
import com.wangku.library.utils.IntentUtils;
import com.wangku.library.utils.NotNull;
import com.wangku.library.utils.ViewUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public class LoginActivity extends BaseNetActivity<LoginPresenter>  {


    @BindView(R.id.iv_logo)
    ImageView ivLogo;
    @BindView(R.id.et_login_userName)
    EditText etLoginUserName;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.iv_login_psw)
    ImageView ivLoginPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.sl_login)
    ScrollView slLogin;

    boolean isPswVisible=false;//默认不可见
    String loginName;
    String passWord;
    @Override
    public void initEventAndData() {
        setStandardLayout(R.layout.activity_welcome);
        setTitle("登录");
        btnBack.setVisibility(View.GONE);
        MyTextWatcher watcher=new MyTextWatcher();
        etLoginPassword.addTextChangedListener(watcher);
        etLoginUserName.addTextChangedListener(watcher);
    }





    @OnClick({R.id.iv_login_psw, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_login_psw:
                ViewUtils.isPswVisiable(isPswVisible, etLoginPassword);
                ivLoginPsw.setSelected(isPswVisible);
                if(isPswVisible){
                    isPswVisible=false;
                }else{
                    isPswVisible=true;
                }
                break;
            case R.id.btn_login:
                IntentUtils.showActivity(this, MainActivity.class);
                break;
        }
    }


    @Override
    public LoginPresenter getmPresenter() {
        return new LoginPresenter(this);
    }

    class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            loginName = etLoginUserName.getText().toString();
            passWord= etLoginPassword.getText().toString();
            if (NotNull.isNotNull(loginName, passWord) && passWord.length() >= 6) {
                btnLogin.setSelected(true);
                btnLogin.setEnabled(true);
            } else {
                btnLogin.setSelected(false);
                btnLogin.setEnabled(false);
            }
        }
    }
}
