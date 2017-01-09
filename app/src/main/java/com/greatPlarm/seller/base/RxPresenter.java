package com.greatPlarm.seller.base;

import com.greatPlarm.seller.http.RetrofitHelper;
import com.wangku.library.utils.NotNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public class RxPresenter<T extends BaseView> implements BsaePresenter<T> {
    protected T mView;
    protected RetrofitHelper mRetrofitHelper;
    protected CompositeSubscription mSubScription;
    public RxPresenter(T mView) {
        attachView(mView);
    }


    @Override
    public void attachView(T view) {
        this.mView=view;
        mRetrofitHelper=RetrofitHelper.get();
    }

    @Override
    public void detachView() {
        this.mView=null;
        unSubscribe();
    }

    /**
     * 添加订阅事件
     * @param subscription
     */
    protected void addSubscribe(Subscription subscription){
        if(mSubScription==null){
            mSubScription=new CompositeSubscription();
        }
        mSubScription.add(subscription);
    }
    /**
     * 解除订阅关系
     */
    private void unSubscribe() {
        if(NotNull.isNotNull(mSubScription)){
            mSubScription.unsubscribe();;
        }
    }
}
