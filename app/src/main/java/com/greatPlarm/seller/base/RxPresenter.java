package com.greatPlarm.seller.base;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public class RxPresenter<T extends BaseView> implements BsaePresenter<T> {
    protected T mView;
//    protected
    public RxPresenter(T mView) {
        attachView(mView);
    }


    @Override
    public void attachView(T view) {

    }

    @Override
    public void detachView() {

    }
}
