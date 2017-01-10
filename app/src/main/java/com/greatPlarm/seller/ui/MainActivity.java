package com.greatPlarm.seller.ui;

import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.greatPlarm.seller.R;
import com.greatPlarm.seller.base.BaseNetActivity;
import com.greatPlarm.seller.base.BaseView;
import com.greatPlarm.seller.http.request.paramsEntity.LoginParams;
import com.greatPlarm.seller.http.responseEntity.LoginEntity;
import com.greatPlarm.seller.presenter.LoginPresenter;
import com.wangku.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseNetActivity<LoginPresenter> implements BaseView {


    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btntest)
    Button btntest;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    public void initEventAndData() {
        setNoHeadLayout(R.layout.activity_main);
    }


    @OnClick(R.id.btntest)
    public void onClick() {
        ToastUtils.show(mContext, "真的喜欢你");
        LoginParams params=new LoginParams();
        params.setLoginName("123");
        params.setPassword("123");
        mPresenter.logins(params);
//        mPresenter.login(params);
    }

    @Override
    public LoginPresenter getmPresenter() {
        return new LoginPresenter(this);
    }

    public void showMsg(LoginEntity entity){

    }
}
