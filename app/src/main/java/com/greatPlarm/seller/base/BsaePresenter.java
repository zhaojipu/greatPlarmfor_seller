package com.greatPlarm.seller.base;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 */

public interface BsaePresenter<T extends BaseView> {
    /**
     * 绑定视图view
     * @param view
     */
    void attachView(T view);

    void detachView();
}
