package com.greatPlarm.seller.presenter;

import com.greatPlarm.seller.base.RxPresenter;
import com.greatPlarm.seller.http.request.paramsEntity.LoginParams;
import com.greatPlarm.seller.http.responseEntity.LoginEntity;
import com.greatPlarm.seller.http.responseEntity.RootResponse;
import com.greatPlarm.seller.ui.MainActivity;
import com.greatPlarm.seller.util.RxUtils;

import rx.Subscription;
import rx.functions.Action1;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public class LoginPresenter extends RxPresenter<MainActivity> {
    public LoginPresenter(MainActivity mView) {
        super(mView);
    }

    public void login(LoginParams params){
        Subscription subscription=mRetrofitHelper.login(params)
                .compose(RxUtils.<RootResponse<LoginEntity>>rxSchedulerHelper())
                .compose(RxUtils.<LoginEntity>handleResule())
                .subscribe(new Action1<LoginEntity>() {
                    @Override
                    public void call(LoginEntity loginEntity) {
                        mView.showMsg(loginEntity);
                    }
                },null);
    }
    public void logins(LoginParams params){
        Subscription subscription=mRetrofitHelper.logins(params)
                .compose(RxUtils.<RootResponse<LoginEntity>>rxSchedulerHelper())
                .compose(RxUtils.<LoginEntity>handleResule())
                .subscribe(new Action1<LoginEntity>() {
                    @Override
                    public void call(LoginEntity loginEntity) {
                        mView.showMsg(loginEntity);
                    }
                },null);
    }
}
