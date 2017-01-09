package com.greatPlarm.seller.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author:zhao
 * VersionCode:1.0
 * Created by Administrator on 2017/1/9 0009.
 * MVP需要连接网络从服务器获取数据的基类
 */

public abstract class BaseNetActivity<T extends BsaePresenter > extends BaseActivity {
    protected T mPresenter;

    public abstract T getmPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=getmPresenter();
    }
}
